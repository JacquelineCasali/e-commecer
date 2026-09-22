package com.app.ecom.service;

import com.app.ecom.dto.OrderItemDTO;
import com.app.ecom.dto.OrderResponseDTO;
import com.app.ecom.enums.OrderStatus;
import com.app.ecom.model.CartItem;
import com.app.ecom.model.Order;
import com.app.ecom.model.OrderItem;
import com.app.ecom.model.User;
import com.app.ecom.repository.OrderRepository;
import com.app.ecom.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
@Transactional
    public Optional<OrderResponseDTO> createOrder(String userId) {
        // validação de intems carrinho
        List<CartItem> cartItems = cartService.getCart(userId);
        if (cartItems.isEmpty()) {
            return Optional.empty();
        }
        // valodação usuario
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }
        User user = userOptional.get();

        // calculo total preço
        BigDecimal totalPrice = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // creat order (pedido)
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        List<OrderItem> orderItems = cartItems.stream()
                .map(item -> new OrderItem(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                )).toList();

        order.setItems(orderItems);

        Order saveOrder = orderRepository.save(order);
        // limpar o carrinho
        cartService.clearCart(userId);
        return Optional.of(mapToOrderResponse(saveOrder));
    }

    private OrderResponseDTO mapToOrderResponse(Order order) {
        return  new OrderResponseDTO(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getItems().stream().map(ordemItem->new OrderItemDTO(
                        ordemItem.getId(),
                        ordemItem.getProduct().getId(),
                        ordemItem.getQuantity(),
                        ordemItem.getPrice(),
                        ordemItem.getPrice().multiply(new BigDecimal(ordemItem.getQuantity()))
                       )).toList(),
                order.getCreatedAt()


        );

    }
}

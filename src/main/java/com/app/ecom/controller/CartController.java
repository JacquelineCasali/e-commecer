package com.app.ecom.controller;

import com.app.ecom.dto.CartItemRequestDTO;
import com.app.ecom.dto.UserResponseDTO;
import com.app.ecom.model.CartItem;
import com.app.ecom.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    /*@RequestHeader("X-User-ID") String userId serve para capturar o valor de um cabeçalho HTTP
    específico enviado na requisição e injetá-lo diretamente como um parâmetro no seu método do Controller.
    * */

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<String> addToCart(
            @RequestHeader("X-User-ID") String userId,
            @RequestBody CartItemRequestDTO cartItemRequestDTO) {
        if (!cartService.addToCart(userId, cartItemRequestDTO)) {
            return ResponseEntity.badRequest().body("Product Out of Stock or User not found or Product not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromCart(
            @RequestHeader("X-User-ID") String userId,
            @PathVariable Long productId
    ) {
        boolean deleted = cartService.deleteItemFromCart(userId, productId);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getCart(
            @RequestHeader("X-User-ID") String userId
    ) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }
}



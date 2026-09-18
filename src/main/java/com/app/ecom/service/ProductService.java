package com.app.ecom.service;

import com.app.ecom.dto.ProductRequestDTO;
import com.app.ecom.dto.ProductResponseDTO;
import com.app.ecom.model.Product;
import com.app.ecom.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        updateProductFromRequest(product, productRequestDTO);
        return mapToProductResponse(productRepository.save(product));
    }

    private ProductResponseDTO mapToProductResponse(Product save) {

        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setId(save.getId());
        responseDTO.setName(save.getName());
        responseDTO.setDescrition(save.getDescrition());
        responseDTO.setPrice(save.getPrice());
        responseDTO.setStockQuantity(save.getStockQuantity());
        responseDTO.setCategory(save.getCategory());
        responseDTO.setImageUrl(save.getImageUrl());
        responseDTO.setActive(save.getActive());
        return responseDTO;

    }

    private void updateProductFromRequest(Product product, ProductRequestDTO productRequestDTO) {


        product.setName(productRequestDTO.getName());
        product.setDescrition(productRequestDTO.getDescrition());
        product.setPrice(productRequestDTO.getPrice());
        product.setStockQuantity(productRequestDTO.getStockQuantity());
        product.setCategory(productRequestDTO.getCategory());
        product.setImageUrl(productRequestDTO.getImageUrl());
        if (productRequestDTO.getActive() != null) {
            product.setActive(productRequestDTO.getActive());
        }

    }

    public Optional<ProductResponseDTO>
    upedateProduct(Long id, ProductRequestDTO productRequestDTO) {
        return productRepository.findById(id).map(existingProduct -> {
            updateProductFromRequest(existingProduct, productRequestDTO);

            return mapToProductResponse(productRepository.save(existingProduct));
        });


    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findByActiveTrue().stream().map(this::mapToProductResponse)
                .collect(Collectors.toList());

    }

    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setActive(false);
                    productRepository.save(product);
                    return true;
                })
                .orElse(false);

    }

    /*busca produto */
    public List<ProductResponseDTO> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword).stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());

    }
}

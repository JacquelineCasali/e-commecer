package com.app.ecom.repository;

import com.app.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByActiveTrue();

    /* busca filtrada e flexível de produtos
    * retorna lista com 3 condições
    * 1 ativo =true
    * 2 quantidade maior que 0
    * 3 LOWER(p.name) like lower(concat('%',:keyword,'%')):
    * Realiza uma busca parcial por texto no nome do produto (name),
    * ignorando diferenças entre letras maiúsculas e minúsculas (LOWER).
    * */

@Query("Select p from Product p where p.active = true and p.stockQuantity > 0 and LOWER(p.name) like lower(concat('%',:keyword,'%'))")
    List<Product> searchProducts(@Param("keyword") String keyword);
}

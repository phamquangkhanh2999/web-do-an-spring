package application.data.repository;

import application.data.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartProductRepository extends JpaRepository<CartProduct,Integer> {
    @Query(value = "SELECT * FROM tbl_product_cart pc " +
            "WHERE pc.cart_id = :cartId  " +
            "AND pc.product_id = :productId " +
            "ORDER BY pc.product_cart_id DESC LIMIT 1",nativeQuery = true)
    CartProduct findFirstCartProductByCartIdAndProductId(@Param("cartId")int cartId, @Param("productId") int productId);
}

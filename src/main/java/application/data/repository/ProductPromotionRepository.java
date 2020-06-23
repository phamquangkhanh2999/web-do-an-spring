package application.data.repository;

import application.data.model.ProductPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPromotionRepository extends JpaRepository<ProductPromotion,Integer> {
}

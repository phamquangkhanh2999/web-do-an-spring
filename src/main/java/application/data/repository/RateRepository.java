package application.data.repository;

import application.data.model.Product;
import application.data.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface RateRepository extends JpaRepository<Rate,Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT r.star FROM tbl_rate  r " +
            "WHERE r.product = :product")
    List<Integer> getRateByProduct(@Param("product") Product product);
}

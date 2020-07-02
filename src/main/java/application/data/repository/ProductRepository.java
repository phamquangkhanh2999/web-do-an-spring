package application.data.repository;

import application.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("select count(p.id) from tbl_product p")
    long getTotalProducts();

    @Query(value ="SELECT * FROM \n" +
            "(SELECT p2.product_id,p2.name, p2.short_desc,p2.product_main_image,sc.price,sc.color,sc.amount,pp2.discount,pp2.start_date,pp2.end_date , r2.sl_rate, r2.tb_rate,p2.category_id\n" +
            "FROM tbl_product AS p2\n" +
            "LEFT JOIN tbl_size_color AS sc ON p2.product_id = sc.product_id\n" +
            "LEFT JOIN (SELECT * \n" +
            "FROM ( SELECT pp.id,pp.discount,pp.promotion_id,pp.product_id,pr.name,pr.start_date,pr.end_date\n" +
            "FROM tbl_product_promotion pp\n" +
            "INNER JOIN tbl_promotion pr ON pp.promotion_id = pr.promotion_id) AS pp1\n" +
            "WHERE start_date <= (SELECT CURDATE()) AND (end_date) >= (SELECT CURDATE())) AS pp2 ON pp2.product_id = p2.product_id\n" +
            "LEFT JOIN (SELECT r1.product_id,count(r1.product_id) as sl_rate ,avg(r1.star) AS tb_rate FROM (SELECT p1.product_id,r.star FROM tbl_product AS p1 \n" +
            "INNER JOIN tbl_rate AS r ON p1.product_id = r.product_id) AS r1\n" +
            "GROUP BY r1.product_id) AS r2 ON p2.product_id = r2.product_id)  AS p2  "
            , nativeQuery = true)
    List<Object[]> getProductByPromotion();









}

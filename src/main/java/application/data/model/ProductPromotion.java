package application.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="tbl_product_promotion")
public class ProductPromotion {
	
	@GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "id")
    @Id
    private int id;
	
	@Column(name="discount")
	private int discount;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    private PromotionModel promotion;

    @Column(name = "promotion_id", insertable = false, updatable = false)
    private int promotion_id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @Column(name = "product_id", insertable = false, updatable = false)
    private int productId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public PromotionModel getPromotion() {
		return promotion;
	}

	public void setPromotion(PromotionModel promotion) {
		this.promotion = promotion;
	}

	public int getPromotion_id() {
		return promotion_id;
	}

	public void setPromotion_id(int promotion_id) {
		this.promotion_id = promotion_id;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
    
    

}

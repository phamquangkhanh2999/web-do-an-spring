package application.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="tbl_product_order")
public class OrderProduct {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_order_id")
    @Id
    private int id;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    
    @Column(name = "amount")
    private int amount;

    @Column(name = "price")
    private double price;
    
    @Column(name="status")
    private String status;
    
    @Column(name="date_payment")
    private Date date_payment;
    
    @Column(name="year_guaratee")
    private String year_guaratee;
    
    @Column(name="color")
    private String color;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate_payment() {
		return date_payment;
	}

	public void setDate_payment(Date date_payment) {
		this.date_payment = date_payment;
	}

	public String getYear_guaratee() {
		return year_guaratee;
	}

	public void setYear_guaratee(String year_guaratee) {
		this.year_guaratee = year_guaratee;
	}


	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public OrderProduct() {
	}
}

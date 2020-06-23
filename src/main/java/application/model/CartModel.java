package application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_cart")
public class CartModel {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
}

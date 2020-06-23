package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_rate")
public class Rate {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rate_id")
    @Id
    private int id;

    @Column(name = "star")
    private String star;

    @OneToOne
    @Column(name = "username")
    private User userName;

    @OneToOne
    @Column(name = "product_id")
    private Product products;

    public Rate() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }
}

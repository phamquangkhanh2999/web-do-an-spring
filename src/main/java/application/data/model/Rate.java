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

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

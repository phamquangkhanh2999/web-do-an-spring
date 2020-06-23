package application.data.model;

import javax.persistence.*;

@Entity(name = "tbl_size_color")
public class SizeColor {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "size_color_id")
    @Id
    private int id;

    @Column(name = "price")
    private double price;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;

    @Column(name = "amount")
    private int amount;

    @OneToOne
    @Column(name = "product_id")
    private Product products;

    public SizeColor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }
}

package entity;

import java.io.Serializable;

public class Ware implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String category;
    private String title;
    private Integer price;
    private String status;

    public Ware() {
    }

    public Ware(Long id, String category, String title, Integer price, String status) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.price = price;
        this.status = status;
    }

    public Ware(String category, String title, Integer price, String status) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ware{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}

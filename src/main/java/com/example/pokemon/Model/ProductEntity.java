package com.example.pokemon.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.List;


@Entity
@Table(name = "Product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proId")
    private int proId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "color")
    private String color;
    @Column(name = "size")
    private String size;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "sale")
    private int sale;
    @Column(name = "image")
    private String image;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "catId", referencedColumnName = "catId")
    private CategoryEntity category;


    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<ReviewsEntity> reviewsEntities;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<Order_itemsEntity>  orderItemsEntities;


    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<Cart_itemsEntity> cartItemsEntities;



    public ProductEntity() {
    }

    public double getSale() {
        return sale;
    }

    // Hàm lấy mảng màu
    public String[] getColorArray() {
        if (color == null || color.isEmpty()) return new String[]{};
        return color.split(",");
    }


    // Enum cho màu sắc (nếu cần)
    public enum ColorEnum {
        RED("Red"),
        BLUE("Blue"),
        GREEN("Green"),
        BLACK("Black"),
        WHITE("White"),
        YELLOW("Yellow");

        private final String value;

        ColorEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


    // Thêm hàm hỗ trợ: Lấy list size dạng int[]
    public int[] getSizeArray() {
        if (size == null || size.isEmpty()) return new int[]{};
        String[] parts = size.split(",");
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i]);
        }
        return result;
    }

    // Hàm lấy size text (S, M, L, XL, XXL)
    public String getSizeText() {
        if (size == null || size.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int val : getSizeArray()) {
            sb.append(SizeEnum.fromValue(val).getLabel()).append(", ");
        }
        return sb.toString().replaceAll(", $", ""); // Xóa dấu phẩy cuối
    }

    public enum SizeEnum {
        S(0, "S"),
        M(1, "M"),
        L(2, "L"),
        XL(3, "XL"),
        XXL(4, "XXL");

        private final int value;
        private final String label;

        SizeEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public int getValue() {
            return value;
        }

        public String getLabel() {
            return label;
        }

        public static SizeEnum fromValue(int value) {
            for (SizeEnum size : SizeEnum.values()) {
                if (size.value == value) {
                    return size;
                }
            }
            return S;
        }
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<ReviewsEntity> getReviewsEntities() {
        return reviewsEntities;
    }

    public void setReviewsEntities(List<ReviewsEntity> reviewsEntities) {
        this.reviewsEntities = reviewsEntities;
    }

    public List<Order_itemsEntity> getOrderItemsEntities() {
        return orderItemsEntities;
    }

    public void setOrderItemsEntities(List<Order_itemsEntity> orderItemsEntities) {
        this.orderItemsEntities = orderItemsEntities;
    }

    public List<Cart_itemsEntity> getCartItemsEntities() {
        return cartItemsEntities;
    }

    public void setCartItemsEntities(List<Cart_itemsEntity> cartItemsEntities) {
        this.cartItemsEntities = cartItemsEntities;
    }
}

package com.epam.cinema.dto;

public class UserDiscount {
    private Long userId;
    private String email;
    private Long discountId;
    private String discountName;
    private Double price;

    public UserDiscount() {
    }

    public UserDiscount(Long userId, Long discountId, String discountName, Double price) {
        this.userId = userId;
        this.discountId = discountId;
        this.discountName = discountName;
        this.price = price;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

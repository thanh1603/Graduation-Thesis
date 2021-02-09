package utt.doantotnghiep.bean;

import utt.doantotnghiep.entities.Product;

public class Cart {
    Product product;
    int amount;

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
}

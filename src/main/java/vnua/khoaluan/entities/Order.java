package vnua.khoaluan.entities;

import vnua.khoaluan.bean.Cart;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Cart> carts;
    private String createDate;
    private String updateDate;
    private String userID;
    private int status;
    private String code;

    public List<Cart> getCarts() {
        if(carts == null) {
            carts = new ArrayList<Cart>();
        }
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

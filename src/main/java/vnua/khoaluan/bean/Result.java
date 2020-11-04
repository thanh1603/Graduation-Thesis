package vnua.khoaluan.bean;

import vnua.khoaluan.entities.Order;
import vnua.khoaluan.entities.Product;

import java.util.*;

public class Result {
    private int status;
    private List<String> messgaes;
    private Map<String, String> mesStringByKey  = new LinkedHashMap<String, String>();
    private int total;
    public  List<Object> data;
    public  String idScroll;
    public List<Order> orders;

    public  Set<Product> products;

    public  Map<String, Cart> carts;

    private  String fileName;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getMessgaes() {
        if(messgaes == null) {
            return new ArrayList<String>();
        }
        return messgaes;
    }

    public void setMessgaes(List<String> messgaes) {
        this.messgaes = messgaes;
    }

    public Map<String, String> getMesStringByKey() {
        return mesStringByKey;
    }

    public void setMesStringByKey(Map<String, String> mesStringByKey) {
        this.mesStringByKey = mesStringByKey;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Object> getData() {
        if(data == null) {
            data =  new ArrayList<Object>();
        }
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public Set<Product> getProducts() {
        if(products == null) {
            products = new HashSet<Product>();
        }
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getIdScroll() {
        return idScroll;
    }

    public void setIdScroll(String idScroll) {
        this.idScroll = idScroll;
    }

    public Map<String, Cart> getCarts() {
        if(carts == null) {
             carts = new TreeMap<String, Cart>();
        }
        return carts;
    }

    public void setCarts(Map<String, Cart> carts) {
        this.carts = carts;
    }

    public List<Order> getOrders() {
        if(orders == null) {
            orders =  new ArrayList<Order>();
        }
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

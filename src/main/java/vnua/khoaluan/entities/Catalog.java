package vnua.khoaluan.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@Document
public class Catalog{
    @Id
    private  String id;
    private  String code;
    private  String name;

    Set<Product> products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}

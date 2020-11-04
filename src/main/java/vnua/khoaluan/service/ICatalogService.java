package vnua.khoaluan.service;

import vnua.khoaluan.bean.Result;
import vnua.khoaluan.entities.Catalog;
import vnua.khoaluan.entities.Product;
import vnua.khoaluan.form.ProductForm;

import java.util.List;

public interface ICatalogService {
    List<Catalog> findALL();

    void insert(Catalog catalog);

    void update(Catalog catalog);

    void deleteCatalog(Catalog catalog);

    public  Catalog findByCode(String Code);

    public  boolean addProduct(ProductForm product);

    public  boolean updateProduct(ProductForm product);

    public Product findProductByProductCode(String catalogCode, String productCode);

    public Result saveCatalog(Catalog catalog, boolean insert);
}

package utt.doantotnghiep.service;

import utt.doantotnghiep.bean.Result;
import utt.doantotnghiep.form.ProductForm;

public interface IProductService {
    Result getProductByCatalogCode(String catalogCode, int offset, int limit);

    Result getProductByCatalogCode(String catalogCode, String textSearch, int offset, int limit);

    Result validateProduct(ProductForm productForm);

    Result validateProductUpdate(ProductForm productForm);

    Result deleteProduct(String catalogCode, String productCode);

    Result getProduct(String textSearch, int offset, int limit);

}

package vnua.khoaluan.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnua.khoaluan.bean.Cart;
import vnua.khoaluan.bean.Result;
import vnua.khoaluan.common.Constant;
import vnua.khoaluan.common.StringUtil;
import vnua.khoaluan.entities.Catalog;
import vnua.khoaluan.entities.Product;
import vnua.khoaluan.form.ProductForm;
import vnua.khoaluan.service.ICatalogService;
import vnua.khoaluan.service.IProductService;
import vnua.khoaluan.service.ServiceCommon;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Transactional
public class ProductServiceImpl extends ServiceCommon implements IProductService {
    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
    @Autowired(required = true)
    private MongoTemplate mongoTemplate;

    @Autowired
    private ICatalogService iCatalogService;

    public Result getProductByCatalogCode(String catalogCode, int offset, int limit) {
        Result  result = new Result();
       try{
           List<Product> products;
            Catalog catalog = this.iCatalogService.findByCode(catalogCode);
            if(catalog.getProducts().size() < offset) {
                products = new ArrayList<Product>();
            }
            if(offset == 0 && catalog.getProducts().size() < offset + limit) {
                limit = catalog.getProducts().size();
            }else  if(offset > catalog.getProducts().size()) {
                offset = catalog.getProducts().size() - 1;
                limit = 0;
            } else if(catalog.getProducts().size() < offset + limit) {
                limit = catalog.getProducts().size() - offset;
            }
            products = new ArrayList<Product>(catalog.getProducts()).subList(offset, offset + limit);
            result.getProducts().addAll(products);
            result.setTotal(catalog.getProducts().size());
            return  result;
       }catch (Exception ex) {
           logger.error(ex.getMessage(), ex);
       }
       return result;
    }

    public Result getProductByCatalogCode(String catalogCode, String textSearch, int offset, int limit) {
        Result  result = new Result();
        String text = textSearch.toLowerCase();
        try{
            Catalog catalog = this.iCatalogService.findByCode(catalogCode);
            List<Product> productsDB = new ArrayList<Product>();
            for(Product product: catalog.getProducts()) {
                if(product.getName().toLowerCase().indexOf(text) != -1 ||
                   product.getCode().toLowerCase().indexOf(text) != -1 ||
                   product.getDiscription().toLowerCase().indexOf(text) != -1 ||
                    (product.getPrice() + "").indexOf(text) != -1 ||
                    (product.getDiscount() + "").indexOf(text) != -1 ||
                    (product.getAmount() + "").indexOf(text) != -1
                ) {
                    productsDB.add(product);
                }
            }

            List<Product> products;

            if(productsDB.size() < offset) {
                products = new ArrayList<Product>();
            }
            if(offset == 0 && productsDB.size() < offset + limit) {
                limit = productsDB.size();
            }else  if(offset > productsDB.size()) {
                offset = productsDB.size() - 1;
                limit = 0;
            } else if(productsDB.size() < offset + limit) {
                limit = productsDB.size() - offset;
            }
            products = new ArrayList<Product>(productsDB).subList(offset, offset + limit);
            result.getProducts().addAll(products);
            result.setTotal(productsDB.size());
            return  result;
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    public Result validateProduct(ProductForm product) {
        Result  result = new Result();
        try{
            // Valid loai san pham
            if(product.getCatalogCode() == null || Constant.BLANK.equals(product.getCatalogCode().trim())) {
                result.getMesStringByKey().put("catalogCodeError", this.getMessage(Constant.MSG_CODE.MSG_011));
                result.setIdScroll("catalogCodeError");
                result.setStatus(Constant.STATUS.ERROR);
            }
            //valid ma san pham
            if(StringUtil.isEmptyWithTrim(product.getCode())) {
                result.getMesStringByKey().put("codeError", this.getMessage(Constant.MSG_CODE.MSG_012));
                if(result.getIdScroll() == null) {
                    result.setIdScroll("codeError");
                }
                result.setStatus(Constant.STATUS.ERROR);
            }else  {
                Product productDb = this.iCatalogService.findProductByProductCode(product.getCatalogCode(), product.getCode()) ;
                if(productDb != null) {
                    result.getMesStringByKey().put("codeError", this.getMessage(Constant.MSG_CODE.MSG_013,
                            new String[] {this.iCatalogService.findByCode(product.getCatalogCode()).getName()}));
                    result.setStatus(Constant.STATUS.ERROR);
                    if(result.getIdScroll() == null) {
                        result.setIdScroll("codeError");
                    }
                }
            }

            // valid ten san pham
            if(StringUtil.isEmptyWithTrim(product.getName())) {
                result.getMesStringByKey().put("nameError", this.getMessage(Constant.MSG_CODE.MSG_017));
                result.setStatus(Constant.STATUS.ERROR);
                if(result.getIdScroll() == null) {
                    result.setIdScroll("nameError");
                }
            }

            // valid so luong
            if(product.getAmount() < 0) {
                result.getMesStringByKey().put("amountError", this.getMessage(Constant.MSG_CODE.MSG_014));
                if(result.getIdScroll() == null) {
                    result.setIdScroll("amountError");
                }
            }

            // valid gia
            if(product.getPrice() < 0) {
                result.getMesStringByKey().put("priceError", this.getMessage(Constant.MSG_CODE.MSG_015));
                result.setStatus(Constant.STATUS.ERROR);
                if(result.getIdScroll() == null) {
                    result.setIdScroll("priceError");
                }
            }

            // valid giam gia
            if(product.getDiscount() < 0) {
                result.getMesStringByKey().put("discountError", this.getMessage(Constant.MSG_CODE.MSG_016 ));
                result.setStatus(Constant.STATUS.ERROR);
                result.setStatus(Constant.STATUS.ERROR);
                if(result.getIdScroll() == null) {
                    result.setIdScroll("discountError");
                }
            }

            if(StringUtil.isEmptyWithTrim(product.getImageLargeFile().getOriginalFilename())
                    || StringUtil.isEmptyWithTrim(product.getImageSmallFile().getOriginalFilename())) {
                result.getMesStringByKey().put("imageError", this.getMessage(Constant.MSG_CODE.MSG_018 ));
                result.setStatus(Constant.STATUS.ERROR);
                if(result.getIdScroll() == null) {
                        result.setIdScroll("imageError");
                }
            }else{
                if(product.getImageLargeFile().getOriginalFilename().toLowerCase().indexOf("jpg") == -1
                        && product.getImageLargeFile().getOriginalFilename().toLowerCase().indexOf("jpeg") == -1
                         && product.getImageLargeFile().getOriginalFilename().toLowerCase().indexOf("png") == -1){
                    result.getMesStringByKey().put("imageError", this.getMessage(Constant.MSG_CODE.MSG_019 ));
                    result.setStatus(Constant.STATUS.ERROR);
                    if(result.getIdScroll() == null) {
                        result.setIdScroll("imageError");
                    }
                }

                if(product.getImageSmallFile().getOriginalFilename().toLowerCase().indexOf("jpg") == -1
                        && product.getImageSmallFile().getOriginalFilename().toLowerCase().indexOf("jpeg") == -1
                        && product.getImageSmallFile().getOriginalFilename().toLowerCase().indexOf("png") == -1){
                    result.getMesStringByKey().put("imageError", this.getMessage(Constant.MSG_CODE.MSG_019 ));
                    result.setStatus(Constant.STATUS.ERROR);
                    if(result.getIdScroll() == null) {
                        result.setIdScroll("imageError");
                    }
                }
            }


            if(result.getMesStringByKey().size() == 0) {
                result.setStatus(Constant.STATUS.OK);
            }
        }catch (Exception ex) {
            result.setStatus(Constant.STATUS.ERROR);
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    public Result validateProductUpdate(ProductForm product) {
        Result  result = new Result();
        try{
            // valid ten san pham
            if(StringUtil.isEmptyWithTrim(product.getName())) {
                result.getMesStringByKey().put("nameError", this.getMessage(Constant.MSG_CODE.MSG_017));
                result.setStatus(Constant.STATUS.ERROR);
                result.setIdScroll("nameError");

            }

            // valid so luong
            if(product.getAmount() < 0) {
                result.getMesStringByKey().put("amountError", this.getMessage(Constant.MSG_CODE.MSG_014));
                result.setStatus(Constant.STATUS.ERROR);
                if(result.getIdScroll() == null) {
                    result.setIdScroll("amountError");
                }
            }

            // valid gia
            if(product.getPrice() < 0) {
                result.getMesStringByKey().put("priceError", this.getMessage(Constant.MSG_CODE.MSG_015));
                result.setStatus(Constant.STATUS.ERROR);
                if(result.getIdScroll() == null) {
                    result.setIdScroll("priceError");
                }
            }

            // valid giam gia
            if(product.getDiscount() < 0) {
                result.getMesStringByKey().put("discountError", this.getMessage(Constant.MSG_CODE.MSG_016 ));
                result.setStatus(Constant.STATUS.ERROR);
                if(result.getIdScroll() == null) {
                    result.setIdScroll("discountError");
                }
            }


        if(!Constant.BLANK.equals(product.getImageLargeFile().getOriginalFilename().trim()) && product.getImageLargeFile().getOriginalFilename().toLowerCase().indexOf("jpg") == -1
                && product.getImageLargeFile().getOriginalFilename().toLowerCase().indexOf("jpeg") == -1
                && product.getImageLargeFile().getOriginalFilename().toLowerCase().indexOf("png") == -1){
            result.getMesStringByKey().put("imageError", this.getMessage(Constant.MSG_CODE.MSG_019 ));
            result.setStatus(Constant.STATUS.ERROR);
            if(result.getIdScroll() == null) {
                result.setIdScroll("imageError");
            }
        }

        if(!Constant.BLANK.equals(product.getImageSmallFile().getOriginalFilename().trim()) && product.getImageSmallFile().getOriginalFilename().toLowerCase().indexOf("jpg") == -1
                && product.getImageSmallFile().getOriginalFilename().toLowerCase().indexOf("jpeg") == -1
                && product.getImageSmallFile().getOriginalFilename().toLowerCase().indexOf("png") == -1){
            result.getMesStringByKey().put("imageError", this.getMessage(Constant.MSG_CODE.MSG_019 ));
            result.setStatus(Constant.STATUS.ERROR);
            if(result.getIdScroll() == null) {
                result.setIdScroll("imageError");
            }
        }


            if(result.getMesStringByKey().size() == 0) {
                result.setStatus(Constant.STATUS.OK);
            }
        }catch (Exception ex) {
            result.setStatus(Constant.STATUS.ERROR);
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    public Result deleteProduct(String catalogCode, String productCode) {
        Result  result = new Result();
        result.setStatus(Constant.STATUS.OK);
        try{
            Catalog catalog = this.iCatalogService.findByCode(catalogCode);
            Product product = null;
            for(Product item: catalog.getProducts()) {
                if(item.getCatalogCode().equals(catalogCode) && item.getCode().equals(productCode)) {
                    product = item;
                    continue;
                }
            }

            if(product != null) {
                catalog.getProducts().remove(product);
                mongoTemplate.save(catalog);
            }
        }catch (Exception ex) {
            result.setStatus(Constant.STATUS.ERROR);
            logger.error(ex.getMessage(), ex);
        }
        return  result;
    }

    public Result getProduct(String textSearch, int offset, int limit) {
        Result  result = new Result();
        try{
            List<Product> productDB = new ArrayList<Product>();
            List<Catalog> catalogs = this.iCatalogService.findALL();
            for(Catalog catalog: catalogs) {
                productDB.addAll(catalog.getProducts());
            }

            List<Product> productResult = new ArrayList<Product>();

            for(Product product: productDB) {
                if(textInProduct(product, textSearch)
                ) {
                    productResult.add(product);
                }
            }

            if(productResult.size() < offset) {
                productResult = new ArrayList<Product>();
            }

            if(offset == 0 && productResult.size() < offset + limit) {
                limit = productResult.size();
            }else if(offset > productResult.size()) {
                offset = productResult.size() - 1;
                limit = 0;
            } else if(productResult.size() < offset + limit) {
                limit = productResult.size() - offset;
            }

            result.setTotal(productResult.size());
            productResult = new ArrayList<Product>(productResult).subList(offset, offset + limit);
            result.getProducts().addAll(productResult);
            result.setStatus(Constant.STATUS.OK);
            return  result;
        }catch (Exception ex) {
            result.setStatus(Constant.STATUS.ERROR);
            result.setProducts(new HashSet<Product>());
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    private boolean textInProduct(Product product, String textSearch) {
        String text = textSearch.toLowerCase();
        return product.getName().toLowerCase().indexOf(text) != -1 ||
                product.getCode().toLowerCase().indexOf(text) != -1 ||
                product.getDiscription().toLowerCase().indexOf(text) != -1 ||
                (product.getPrice() + "").indexOf(text) != -1 ||
                (product.getDiscount() + "").indexOf(text) != -1 ||
                (product.getAmount() + "").indexOf(text) != -1;
    }

}

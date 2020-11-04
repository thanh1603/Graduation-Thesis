package vnua.khoaluan.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnua.khoaluan.bean.Result;
import vnua.khoaluan.common.Constant;
import vnua.khoaluan.common.StringUtil;
import vnua.khoaluan.common.Utils;
import vnua.khoaluan.entities.Catalog;
import vnua.khoaluan.entities.Product;
import vnua.khoaluan.form.ProductForm;
import vnua.khoaluan.service.ICatalogService;
import vnua.khoaluan.service.ServiceCommon;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class CatalogServiceImpl extends ServiceCommon implements ICatalogService {
    private static final Logger logger = Logger.getLogger(CatalogServiceImpl.class);
    @Autowired(required = true)
    private MongoTemplate mongoTemplate;

    public List<Catalog> findALL() {
        try {
            List<Catalog> catalogs = mongoTemplate.findAll(Catalog.class);
            Collections.sort(catalogs, new Comparator<Catalog>() {
                public int compare(Catalog o1, Catalog o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            return catalogs;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return new ArrayList<Catalog>();
        }
    }

    public void insert(Catalog catalog) {
        try {
            mongoTemplate.save(catalog, "catalog");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public Catalog findByCode(String code) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("code").is(code));
            List<Catalog> catalog =  mongoTemplate.find(query, Catalog.class);
            if(catalog.size() > 0) {
                return catalog.get(0);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return new Catalog();
    }

    public boolean addProduct(ProductForm product) {
        try {
            Catalog catalog = this.findByCode(product.getCatalogCode());
            if (product.getImageLargeFile() != null && !Constant.BLANK.equals(product.getImageLargeFile().getOriginalFilename())) {
                Utils.multipartToFile(product.getImageLargeFile(), Constant.ROOT_IMAGE_URL + product.getCode() +
                        "\\" );
                product.setImageLarge(product.getCode() + "\\" + product.getImageLargeFile().getOriginalFilename());
            }

            if (product.getImageSmallFile() != null && !Constant.BLANK.equals(product.getImageSmallFile().getOriginalFilename())) {
                Utils.multipartToFile(product.getImageSmallFile(), Constant.ROOT_IMAGE_URL + product.getCode() + "\\");
                product.setImageSmall(product.getCode() + "\\" + product.getImageSmallFile().getOriginalFilename());
            }

            product.setImageSmallFile(null);
            product.setImageLargeFile(null);
            catalog.getProducts().add(product);

            mongoTemplate.save(catalog);
            return true;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return false;
        }
    }

    public boolean updateProduct(ProductForm product) {
        try{
            Catalog catalog = this.findByCode(product.getCatalogCode());
            if(catalog != null) {
                for(Product item: catalog.getProducts()) {
                    if(item.getCode() != null && item.getCode().equals(product.getCode())) {
                        item.setDiscount(product.getDiscount());
                        item.setAmount(product.getAmount());
                        item.setName(product.getName());
                        item.setDiscription(product.getDiscription());
                        item.setPrice(product.getPrice());
                        if (product.getImageLargeFile() != null && !Constant.BLANK.equals(product.getImageLargeFile().getOriginalFilename())) {
                            Utils.multipartToFile(product.getImageLargeFile(), Constant.ROOT_IMAGE_URL + product.getCode() +
                                    "\\" );
                            item.setImageLarge(product.getCode() + "\\" + product.getImageLargeFile().getOriginalFilename());
                        }

                        if (product.getImageSmallFile() != null && !Constant.BLANK.equals(product.getImageSmallFile().getOriginalFilename())) {
                            Utils.multipartToFile(product.getImageSmallFile(), Constant.ROOT_IMAGE_URL + product.getCode() + "\\");
                            item.setImageSmall(product.getCode() + "\\" + product.getImageSmallFile().getOriginalFilename());
                        }
                        mongoTemplate.save(catalog);
                        break;
                    }
                }
            }
            return true;
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return false;
        }
    }

    public Product findProductByProductCode(String catalogCode, String productCode) {
       try{
           Catalog catalog = this.findByCode(catalogCode);
           for(Product product: catalog.getProducts()) {
               if(product.getCode() != null && product.getCode().equals(productCode)) {
                   return product;
               }
           }
           return null;
       }catch (Exception ex) {
           logger.error(ex.getMessage(), ex);
           return null;
       }
    }

    public Result saveCatalog(Catalog catalog, boolean insert) {
        Result result = new Result();
        try{
            Catalog catalogDb = this.findByCode(catalog.getCode());
            result.setStatus(Constant.STATUS.OK);
            if(insert) {
                if(StringUtil.isEmptyWithTrim(catalog.getCode())) {
                    result.getMesStringByKey().put("codeError", this.getMessage(Constant.MSG_CODE.MSG_020));
                    result.setStatus(Constant.STATUS.ERROR);
                }else if(catalogDb.getCode() != null) {
                    result.getMesStringByKey().put("codeError", this.getMessage(Constant.MSG_CODE.MSG_021, new String[] {catalogDb.getCode()}));
                    result.setStatus(Constant.STATUS.ERROR);
                }
            }

            if(StringUtil.isEmptyWithTrim(catalog.getName())) {
                result.getMesStringByKey().put("nameError", this.getMessage(Constant.MSG_CODE.MSG_022));
                result.setStatus(Constant.STATUS.ERROR);
            }

            if(result.getStatus() != Constant.STATUS.ERROR) {
                catalogDb.setName(catalog.getName());
                if(insert) {
                    catalogDb.setCode(catalog.getCode());
                }
                mongoTemplate.save(catalogDb, "catalog");
            }
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.setStatus(Constant.STATUS.ERROR);
        }
        return  result;
    }


    public void update(Catalog catalog) {

    }

    public void deleteCatalog(Catalog catalog) {

    }


}

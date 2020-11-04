package vnua.khoaluan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vnua.khoaluan.bean.Result;
import vnua.khoaluan.common.Constant;
import vnua.khoaluan.entities.Catalog;
import vnua.khoaluan.entities.Product;
import vnua.khoaluan.form.ProductForm;
import vnua.khoaluan.service.ICatalogService;
import vnua.khoaluan.service.IProductService;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class ProductController extends BaseController {
    @Autowired
    ICatalogService catalogService;

    @Autowired
    IProductService iProductService;

    @RequestMapping(value = {"/product"}, method = RequestMethod.GET)
    public String product(Model model, @RequestParam(value = "textSearch", required =  false, defaultValue = "")
                                        String textSearch) {
        try{
            pargingInfo.pageCurrent = 1;
            Result result = this.iProductService.getProduct(textSearch,(pargingInfo.pageCurrent - 1)
                                    * pargingInfo.maxItemView, pargingInfo.maxItemView);
            pargingInfo.total = result.getTotal();
            model.addAttribute("textSearch", textSearch);
            model.addAttribute("viewFlag", 1);
            model.addAttribute("result", result);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.PRODUCT;
    }

    @RequestMapping(value = {"/product/search"}, method = RequestMethod.GET)
    public String productSearch(Model model, @RequestParam(value = "textSearch", required =  false, defaultValue = "")
                                            String textSearch,
                                @RequestParam(value = "page", required =  false, defaultValue = "1")
                                        int page) {
        try{
            if(page < 0) {
                page = 1;
            }

            pargingInfo.pageCurrent = page;
            Result result = this.iProductService.getProduct(textSearch,(pargingInfo.pageCurrent - 1)
                    * pargingInfo.maxItemView, pargingInfo.maxItemView);
            pargingInfo.total = result.getTotal();
            model.addAttribute("textSearch", textSearch);
            model.addAttribute("viewFlag", 1);
            model.addAttribute("result", result);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.PRODUCT;
    }

    @RequestMapping(value = {"/product/detail/{catalogCode}/{productCode}"}, method = RequestMethod.GET)
    public String productDetail(Model model, @PathVariable(value = "catalogCode") String catalogCode,
                                @PathVariable(value = "productCode") String productCode) {
        try{
            Product product = this.catalogService.findProductByProductCode(catalogCode, productCode);
            if(product  == null) {
                return "redirect:/";
            }
            model.addAttribute("product", product);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.PRODUCT_DETAIL;
    }

    @RequestMapping(value = {"/{catalogCode}/product"}, method = RequestMethod.GET)
    public String productList(Model model,
                        @PathVariable(value = "catalogCode") String catalogCode,
                        @RequestParam(value = "page", required =  false, defaultValue = "1") int page) {
        try{
            Result result = this.iProductService.getProductByCatalogCode(catalogCode,
                    (page - 1)* this.pargingInfo.maxItemView,  pargingInfo.maxItemView);

            this.pargingInfo.pageCurrent = page;
            this.pargingInfo.total = result.getTotal();
            if(result.getProducts().size() == 0) {
                this.pargingInfo.total = 0;
            }
            model.addAttribute("catalogCode", catalogCode);
            model.addAttribute("result", result);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.PRODUCT;
    }

    @RequestMapping(value = {"/product/imageURL/{catalogCode}/{productCode}/{option}"}, method = RequestMethod.GET)
    public void viewProduct(@PathVariable(value = "catalogCode") String catalogCode,
                            @PathVariable(value = "productCode") String productCode,
                            @PathVariable(value = "option") String option,
                            HttpServletResponse response) {
        try {
            Product product = this.catalogService.findProductByProductCode(catalogCode, productCode);
            if(product != null) {
                InputStream is ;
                if("small".equals(option)) {
                    is = loadImage(Constant.ROOT_IMAGE_URL + product.getImageSmall());
                }else{
                    is = loadImage(Constant.ROOT_IMAGE_URL + product.getImageLarge());
                }

                response.setContentType("image/jpeg");
                response.setHeader("Content-Disposition", String.format("inline; filename=\"" + productCode + "\""));

                OutputStream outputStream;

                outputStream = response.getOutputStream();
                FileCopyUtils.copy(is, outputStream);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private InputStream loadImage(String url) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(url));
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "gif", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ------------------- Phan code admin ---------------------------------------
    @RequestMapping(value = {"/admin/product"}, method = RequestMethod.GET)
    public String adSearchProduct(Model model,
                                  HttpSession session,
                                  @RequestParam(value = "catalogCode",defaultValue = "", required = false) String catalogCode
                    ) {
       try{
           List<Catalog> catalogList = catalogService.findALL();

           if(Constant.BLANK.equals(catalogCode)) {
               for(Catalog catalog:catalogList) {
                   if(catalog.getProducts().size() > 0) {
                       catalogCode = catalog.getCode();
                       break;
                   }
               }
           }
           session.setAttribute(Constant.SESSION_CODE.AD_CATALOG_CODE, catalogCode);
           session.setAttribute(Constant.SESSION_CODE.AD_PAGE_CURRENT, 1);
           session.setAttribute(Constant.SESSION_CODE.AD_TEXT_SEARCH, "");

           model.addAttribute("catalogCode", catalogCode);
           model.addAttribute("catalogList", catalogList);
           Result result = this.iProductService.getProductByCatalogCode(catalogCode,
                   (pargingInfo.pageCurrent - 1) * pargingInfo.maxItemView, pargingInfo.maxItemView);
           pargingInfo.setTotal(result.getTotal());
           pargingInfo.setPageCurrent(1);
           model.addAttribute("result", result);

       }catch (Exception ex) {
           logger.error(ex.getMessage(), ex);
       }
       return Constant.TEMPLATE_VIEW.ADMIN_PRODUCT;
    }


    @RequestMapping(value = {"/admin/product/search"}, method = RequestMethod.GET)
    public String adProduct(Model model,
                            HttpSession session,
                            @RequestParam(value = "catalogCode", required =  false,
                            defaultValue = "") String catalogCode,
                            @RequestParam(value = "textSearch", required =  false,
                                    defaultValue = "") String textSearch,
                            @RequestParam(value = "page", required =  false,
                                    defaultValue = "-1") int page,
                            @RequestParam(value = "redirect", required =  false,
                                    defaultValue = "false") boolean  redirect) {
        try{
            if(!redirect) {
                if(Constant.BLANK.equals(catalogCode)) {
                    catalogCode  = (String) session.getAttribute(Constant.SESSION_CODE.AD_CATALOG_CODE);
                    page = ((Integer) session.getAttribute(Constant.SESSION_CODE.AD_PAGE_CURRENT)).intValue();
                }else{
                    if(!catalogCode.equals(session.getAttribute(Constant.SESSION_CODE.AD_CATALOG_CODE))) {
                        session.setAttribute(Constant.SESSION_CODE.AD_CATALOG_CODE, catalogCode);
                        page = 1;
                        session.setAttribute(Constant.SESSION_CODE.AD_PAGE_CURRENT, page);
                    }
                    if(page == -1) {
                        page = 1;
                    }
                }
            }else{
                page = 1;
            }

            model.addAttribute("flag",session.getAttribute(Constant.SESSION_CODE.AD_FLAG));
            session.removeAttribute(Constant.SESSION_CODE.AD_FLAG);
            List<Catalog> catalogList = catalogService.findALL();
            Collections.sort(catalogList, new Comparator<Catalog>() {
                public int compare(Catalog o1, Catalog o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            model.addAttribute("catalogList", catalogList);
            model.addAttribute("catalogCode", catalogCode);
            Result result = this.iProductService.getProductByCatalogCode(catalogCode,textSearch,
                    (page - 1) * pargingInfo.maxItemView, pargingInfo.maxItemView);
            pargingInfo.setTotal(result.getTotal());
            pargingInfo.setPageCurrent(page);
            model.addAttribute("result", result);

            session.setAttribute(Constant.SESSION_CODE.AD_CATALOG_CODE, catalogCode);
            session.setAttribute(Constant.SESSION_CODE.AD_PAGE_CURRENT, page);
            session.setAttribute(Constant.SESSION_CODE.AD_TEXT_SEARCH, textSearch);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.ADMIN_PRODUCT;
    }





    //
    @ModelAttribute(value = "productForm")
    public ProductForm initProduct() {
        return new ProductForm();
    }

    @RequestMapping(value = {"/admin/product/add"}, method = RequestMethod.GET)
    public String adProductAdd(Model model) {
        model.addAttribute("catalogList", catalogService.findALL());
        return Constant.TEMPLATE_VIEW.ADMIN_ADD_PRODUCT;
    }

    @RequestMapping(value = {"/admin/product/add"}, method = RequestMethod.POST)
    public String adProductAddProcess(@ModelAttribute(value = "productForm") ProductForm productForm,
                                      HttpSession session,
                                      Model model) {
        try {
            model.addAttribute("catalogList", catalogService.findALL());
            Result result = this.iProductService.validateProduct(productForm);
            if(result.getStatus() == Constant.STATUS.OK) {
               if( catalogService.addProduct(productForm)) {
                   model.addAttribute("addSuccess", true);
                   session.setAttribute(Constant.SESSION_CODE.AD_FLAG, Constant.FLAG_CODE.INSERT);
                   return "redirect:/admin/product/search?flag="+  Constant.FLAG_CODE.INSERT
                                            + "&catalogCode=" + productForm.getCatalogCode()
                                            + "&textSearch=" + session.getAttribute(Constant.SESSION_CODE.AD_TEXT_SEARCH)
                                            + "&page=" + session.getAttribute(Constant.SESSION_CODE.AD_PAGE_CURRENT);
               }
            }
            model.addAttribute("result", result);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.ADMIN_ADD_PRODUCT;
    }

    @RequestMapping(value = {"/admin/product/update/{catalogCode}/{productCode}"}, method = RequestMethod.GET)
    public String adProductUpdate(@PathVariable(value = "productCode") String productCode,
                                  @PathVariable(value = "catalogCode") String catalogCode,
                                  Model model) {
        try{
            model.addAttribute("catalogList", catalogService.findALL());
            ProductForm productForm = (ProductForm) this.catalogService.findProductByProductCode(catalogCode,productCode );
            model.addAttribute("productForm", productForm);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.ADMIN_EDIT_PRODUCT;
    }

    @RequestMapping(value = {"/admin/product/update"}, method = RequestMethod.POST)
    public String adProductUpdateProcess(@ModelAttribute(value = "productForm") ProductForm productForm,
                                      HttpSession session,
                                      Model model) {
        try {
            model.addAttribute("catalogList", catalogService.findALL());
            Result result = iProductService.validateProductUpdate(productForm);
            if(result.getStatus() == Constant.STATUS.OK){
                if(this.catalogService.updateProduct(productForm)) {
                    session.setAttribute(Constant.SESSION_CODE.AD_FLAG , Constant.FLAG_CODE.UPDATE);
                    return "redirect:/admin/product/search?flag=" + Constant.FLAG_CODE.UPDATE
                            + "&catalogCode=" + productForm.getCatalogCode()
                            + "&textSearch=" + session.getAttribute(Constant.SESSION_CODE.AD_TEXT_SEARCH)
                            + "&page=" + session.getAttribute(Constant.SESSION_CODE.AD_PAGE_CURRENT);
                }
            }

            model.addAttribute("result", result);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.ADMIN_EDIT_PRODUCT;
    }

    @RequestMapping(value = {"/admin/product/delete/{catalogCode}/{productCode}"}, method = RequestMethod.GET)
    public String adProductUpdateProcess(@PathVariable(value = "catalogCode") String catalogCode,
                                         @PathVariable(value = "productCode") String productCode,
                                         HttpSession session) {
        try{
           Result result =  this.iProductService.deleteProduct(catalogCode, productCode);
            session.setAttribute(Constant.SESSION_CODE.AD_FLAG, Constant.FLAG_CODE.DELETE);
        }catch (Exception ex ){
            session.setAttribute(Constant.SESSION_CODE.AD_FLAG, Constant.FLAG_CODE.DELETE_ERROR);
            return "redirect:/admin/product/search?flag=" + Constant.FLAG_CODE.DELETE_ERROR
                    + "&catalogCode=" + session.getAttribute(Constant.SESSION_CODE.AD_CATALOG_CODE)
                    + "&textSearch=" + session.getAttribute(Constant.SESSION_CODE.AD_TEXT_SEARCH)
                    + "&page=" + session.getAttribute(Constant.SESSION_CODE.AD_PAGE_CURRENT);
        }
       return  "redirect:/admin/product/search?flag=" + Constant.FLAG_CODE.DELETE
                + "&catalogCode=" + catalogCode + "&page=" + 1;
    }

}

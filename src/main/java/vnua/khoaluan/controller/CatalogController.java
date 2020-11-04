package vnua.khoaluan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vnua.khoaluan.bean.Result;
import vnua.khoaluan.common.Constant;
import vnua.khoaluan.entities.Catalog;
import vnua.khoaluan.service.ICatalogService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CatalogController extends BaseController {
    @Autowired
    ICatalogService iCatalogService;

     @ModelAttribute("catalogList")
     public List<Catalog> catalogList() {
         return  iCatalogService.findALL();
     }

    @ModelAttribute("catalogForm")
    public Catalog initCatalog() {
        return  new Catalog();
    }


    @RequestMapping(value = {"/admin/catalog"}, method = RequestMethod.GET)
    public String adCatalog(Model model, HttpSession session) {
        try{
            model.addAttribute("catalogList", iCatalogService.findALL());
            model.addAttribute("flag", session.getAttribute("catalogFlag"));
            session.removeAttribute("catalogFlag");
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return Constant.TEMPLATE_VIEW.ADMIN_CATALOG;
    }

    @RequestMapping(value = {"/admin/catalog/add"}, method = RequestMethod.GET)
    public String adCatalogAdd(Model model, HttpSession session) {
        try{

        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return Constant.TEMPLATE_VIEW.ADMIN_ADD_CATALOG;
    }

    @RequestMapping(value = {"/admin/catalog/add"}, method = RequestMethod.POST)
    public String adCatalogAddProcess(Model model,
                                      @ModelAttribute(value = "catalogForm") Catalog catalog,
                                      HttpSession httpSession) {
        try{
            Result result =  this.iCatalogService.saveCatalog(catalog, true);
            if(result.getStatus() == Constant.STATUS.OK) {
                httpSession.setAttribute("catalogFlag", Constant.FLAG_CODE.INSERT);
                return "redirect:/admin/catalog";
            }
            model.addAttribute("result", result);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return Constant.TEMPLATE_VIEW.ADMIN_ADD_CATALOG;
    }

    @RequestMapping(value = {"/admin/catalog/update/{catalogCode}"}, method = RequestMethod.GET)
    public String adCataloUpdate(Model model, HttpSession session,
                                 @PathVariable(value = "catalogCode") String catalogCode) {
        try{
            Catalog catalog = this.iCatalogService.findByCode(catalogCode);
            if(catalog.getCode() == null) {
                return "redirect:/admin/catalog";
            }
            model.addAttribute("catalogForm", catalog);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return Constant.TEMPLATE_VIEW.ADMIN_EDIT_CATALOG;
    }

    @RequestMapping(value = {"/admin/catalog/update"}, method = RequestMethod.POST)
    public String adCataloUpdateProcess(Model model,
                                      @ModelAttribute(value = "catalogForm") Catalog catalog,
                                      HttpSession httpSession) {
        try{
            Result result =  this.iCatalogService.saveCatalog(catalog, false);
            if(result.getStatus() == Constant.STATUS.OK) {
                httpSession.setAttribute("catalogFlag", Constant.FLAG_CODE.UPDATE);
                return "redirect:/admin/catalog";
            }
            model.addAttribute("result", result);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

        return Constant.TEMPLATE_VIEW.ADMIN_EDIT_CATALOG;
    }

    @RequestMapping(value = {"/admin/catalog/insertTest"}, method = RequestMethod.GET)
    public  String insertTest() {
        try{
            Catalog catalog = new Catalog();
            catalog.setCode("CP00001");
            catalog.setName("Hai san tuoi song");
            this.iCatalogService.insert(catalog);

            catalog.setCode("CP00002");
            catalog.setName("Do uong");
            this.iCatalogService.insert(catalog);

            catalog.setCode("CP00003");
            catalog.setName("Trai cay nhap khau");
            this.iCatalogService.insert(catalog);
        }catch (Exception ex) {

        }
        return Constant.TEMPLATE_VIEW.ADMIN_CATALOG;
    }

}

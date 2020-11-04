package vnua.khoaluan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vnua.khoaluan.common.Constant;
import vnua.khoaluan.entities.Catalog;
import vnua.khoaluan.service.ICatalogService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class MainController extends  BaseController{
    @Autowired
    private ICatalogService iCatalogService;

    @RequestMapping(value = "/")
    public String index(Model model){
       try{
           List<Catalog> catalogs = this.iCatalogService.findALL();
           Collections.sort(catalogs, new Comparator<Catalog>() {
               public int compare(Catalog o1, Catalog o2) {
                   return o1.getName().compareTo(o2.getName());
               }
           });
           model.addAttribute("catalogList", catalogs);
           model.addAttribute("catalogSize", catalogs.size());
       }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
       }
       return Constant.TEMPLATE_VIEW.INDEX;
    }
}

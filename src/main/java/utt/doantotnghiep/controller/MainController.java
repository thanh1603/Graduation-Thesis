package utt.doantotnghiep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import utt.doantotnghiep.common.Constant;
import utt.doantotnghiep.entities.Catalog;
import utt.doantotnghiep.service.ICatalogService;

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

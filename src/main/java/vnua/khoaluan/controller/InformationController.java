package vnua.khoaluan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vnua.khoaluan.common.Constant;

@Controller
public class InformationController  extends  BaseController{
    @RequestMapping(value = {"/information"}, method = RequestMethod.GET)
    public String information() {
        return Constant.TEMPLATE_VIEW.INFOMATION;
    }

    @RequestMapping(value = {"/contact"}, method = RequestMethod.GET)
    public String contact() {
        return Constant.TEMPLATE_VIEW.CONTACT;
    }

    @RequestMapping(value = {"/introduce"}, method = RequestMethod.GET)
    public String introduce() {
        return Constant.TEMPLATE_VIEW.INTRODUCE;
    }
}

package utt.doantotnghiep.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import utt.doantotnghiep.bean.Cart;
import utt.doantotnghiep.common.Constant;
import utt.doantotnghiep.common.PargingInfo;
import utt.doantotnghiep.form.CartForm;
import utt.doantotnghiep.entities.User;
import utt.doantotnghiep.service.IUserService;
import utt.doantotnghiep.service.MongoUserDetailsService;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class BaseController {
    protected static final Logger logger = Logger.getLogger(BaseController.class);

    @Autowired
    IUserService iUserService;

    public PargingInfo pargingInfo = new PargingInfo();

    @Autowired
    protected MongoUserDetailsService mongoUserDetailsService;

    @ModelAttribute("user")
    public User userCurrent() {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null) {
                // authentication.getName() là cái username luc dang nhap, thuc te la email trong bang user, spring no luu trong Authentication
                // O day tim nguoc lai database trong bang user
                return this.iUserService.findByEmail(authentication.getName());
            }
            return null;
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }
        return null;
    }

    @ModelAttribute("cartSize")
    public Integer cartSize(HttpSession httpSession) {
        Map<String, Cart> carts = (Map<String, Cart>) httpSession.getAttribute(Constant.SESSION_CODE.CART);
        if(carts == null) {
            carts = new TreeMap<String, Cart>();
        }
        return carts.keySet().size();
    }

   protected boolean isAdmin() {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null) {
                User user =  this.iUserService.findByEmail(authentication.getName());
                if(user.getRoles() != null) {
                    for (String role: user.getRoles()) {
                        if(role.indexOf(Constant.ROLE.ADMIN) != -1) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return false;
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            ex.printStackTrace();
        }
        return false;
    }

    @ModelAttribute("pagingData")
    public PargingInfo informationParging() {
        return pargingInfo;
    }

    @ModelAttribute(value = "cartForm")
    public CartForm initCartForm() {
        return   new CartForm();
    }

}

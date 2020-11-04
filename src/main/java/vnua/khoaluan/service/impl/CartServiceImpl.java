package vnua.khoaluan.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vnua.khoaluan.bean.Cart;
import vnua.khoaluan.bean.Result;
import vnua.khoaluan.common.Constant;
import vnua.khoaluan.entities.Product;
import vnua.khoaluan.service.ICartService;
import vnua.khoaluan.service.ServiceCommon;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

@Service
@Transactional
public class CartServiceImpl extends ServiceCommon implements ICartService {
    private static final Logger logger = Logger.getLogger(CartServiceImpl.class);

    public Result getProductBySession(HttpSession httpSession, int offset, int limit) {
        Result  result = new Result();
        result.setStatus(Constant.STATUS.OK);
        try{
            Map<String, Cart> carts = (Map<String, Cart>) httpSession.getAttribute(Constant.SESSION_CODE.CART);
            result.setCarts(carts);
            httpSession.setAttribute(Constant.SESSION_CODE.CART, carts);
        }catch (Exception ex) {
            result.setStatus(Constant.STATUS.ERROR);
            result.setProducts(new HashSet<Product>());
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    public Result getProductBySession(HttpSession httpSession) {
        Result  result = new Result();
        result.setStatus(Constant.STATUS.OK);
        try{
            Map<String, Cart> carts = (Map<String, Cart>) httpSession.getAttribute(Constant.SESSION_CODE.CART);
            result.setCarts(carts);
            httpSession.setAttribute(Constant.SESSION_CODE.CART, carts);
        }catch (Exception ex) {
            result.setStatus(Constant.STATUS.ERROR);
            result.setProducts(new HashSet<Product>());
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    public Result removeProductInCart(HttpSession httpSession, String productCode) {
        Result  result = new Result();
        result.setStatus(Constant.STATUS.OK);
        try{
            Map<String, Cart> carts = (Map<String, Cart>) httpSession.getAttribute(Constant.SESSION_CODE.CART);
            if(carts != null) {
                carts.remove(productCode);
            }
            result.setCarts(carts);
            httpSession.setAttribute(Constant.SESSION_CODE.CART, carts);
        }catch (Exception ex) {
            result.setStatus(Constant.STATUS.ERROR);
            result.setProducts(new HashSet<Product>());
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

    public Result updateCart(HttpSession httpSession, Cart cart) {
        Result  result = new Result();
        result.setStatus(Constant.STATUS.OK);
        try{
            Map<String, Cart> carts = (Map<String, Cart>) httpSession.getAttribute(Constant.SESSION_CODE.CART);
            if(carts == null) {
                carts = new TreeMap<String, Cart>();
            }
            // TODO: validate 
            if(cart.getProduct() != null) {
                if(carts.containsKey(cart.getProduct().getCode())) {
                    carts.get(cart.getProduct().getCode()).setAmount(cart.getAmount());
                }else{
                    carts.put(cart.getProduct().getCode(), cart);
                }
            }
            result.setCarts(carts);
            httpSession.setAttribute(Constant.SESSION_CODE.CART, carts);
        }catch (Exception ex) {
            result.setStatus(Constant.STATUS.ERROR);
            result.setProducts(new HashSet<Product>());
            logger.error(ex.getMessage(), ex);
        }
        return result;
    }

}

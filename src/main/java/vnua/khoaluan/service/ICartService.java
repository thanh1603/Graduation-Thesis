package vnua.khoaluan.service;

import vnua.khoaluan.bean.Cart;
import vnua.khoaluan.bean.Result;

import javax.servlet.http.HttpSession;

public interface ICartService {

    Result getProductBySession(HttpSession httpSession, int offset, int limit);


    Result getProductBySession(HttpSession httpSession);

    Result removeProductInCart(HttpSession httpSession, String productCode);

    Result updateCart(HttpSession httpSession, Cart cart);
}

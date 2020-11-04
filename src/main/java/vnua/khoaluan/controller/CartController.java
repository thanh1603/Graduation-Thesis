package vnua.khoaluan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import vnua.khoaluan.bean.Cart;
import vnua.khoaluan.bean.Result;
import vnua.khoaluan.common.Constant;
import vnua.khoaluan.common.StringUtil;
import vnua.khoaluan.entities.Config;
import vnua.khoaluan.entities.Order;
import vnua.khoaluan.entities.Product;
import vnua.khoaluan.form.CartForm;
import vnua.khoaluan.service.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Controller
public class CartController extends BaseController {

    @Autowired
    ICartService iCartService;

    @Autowired
    IProductService iProductService;

    @Autowired
    ICatalogService iCatalogService;

    @Autowired
    IConfigService iConfigService;

    @Autowired
    IOrderService iOrderService;

    @ModelAttribute("feeTransfer")
    public  String initFeeTransfer(){
        String feeTransfer = this.iConfigService.getConfigByKey(Constant.CONFIG.FEE_TRANSFER);
        if(feeTransfer == null || StringUtil.isEmptyWithTrim(feeTransfer)) {
            feeTransfer = Constant.FEE_TRANSFER_DEFAULT + "";
        }
        return feeTransfer;
    }

    @RequestMapping(value = {"/cart"}, method = RequestMethod.GET)
    public String product(HttpSession httpSession, Model model) {
      try{
            Result result = iCartService.getProductBySession(httpSession, 0, 0);
            model.addAttribute("result", result);
            model.addAttribute("carts", result.getCarts().values());

      }catch (Exception ex) {
        logger.error(ex.getMessage(), ex);
      }
      return Constant.TEMPLATE_VIEW.CARD;
    }


    @RequestMapping(value = {"/cart/add/{catalogId}/{product_id}"}, method = RequestMethod.GET)
    public String addProduct(HttpSession session, @PathVariable(value = "catalogId") String catalogId,
                            @PathVariable(value = "product_id") String productId ) {
        try{
            Product product = this.iCatalogService.findProductByProductCode(catalogId, productId);
            if(product != null) {
                Cart cart = new Cart();
                cart.setAmount(1);
                cart.setProduct(product);
                Result result =  this.iCartService.updateCart(session, cart);
                session.setAttribute(Constant.SESSION_CODE.AD_FLAG, Constant.CART_CODE.ADD);
            }
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = {"/cart/remove/{product_id}"}, method = RequestMethod.GET)
    public String removeProduct(HttpSession session,
                                @PathVariable(value = "product_id") String productId ) {
        try{
            this.iCartService.removeProductInCart(session, productId);
            session.setAttribute(Constant.SESSION_CODE.AD_FLAG, Constant.CART_CODE.DELETE);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = {"/cart/update"}, method = RequestMethod.POST)
    public String updateProduct(HttpSession httpSession, @ModelAttribute(value = "cartForm")CartForm cartForm) {
        try{
            Product product = this.iCatalogService.findProductByProductCode(cartForm.getCatalogCode(), cartForm.getProductCode());
            if(product != null) {
                Cart cart = new Cart();
                cart.setAmount(cartForm.getAmount());
                cart.setProduct(product);
                Result result =  this.iCartService.updateCart(httpSession, cart);
                httpSession.setAttribute(Constant.SESSION_CODE.AD_FLAG, Constant.CART_CODE.UPDATE);
            }
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = {"/cart/checkout"}, method = RequestMethod.GET)
    public String checkout(HttpSession httpSession) {
        try{
            //TODO validate
            if(this.userCurrent() == null) {
                httpSession.setAttribute(Constant.SESSION_CODE.CART_CHECKOUT_REDIRIECT, "true");
                return "redirect:/login";
            }else{
                Order order = new Order();
                Result result = this.iCartService.getProductBySession(httpSession);
                order.getCarts().addAll(result.getCarts().values());

                result =  this.iOrderService.saveOrder(this.userCurrent(), order);
                if(result.getStatus() != Constant.STATUS.ERROR) {
                    httpSession.removeAttribute(Constant.SESSION_CODE.CART);
                    httpSession.setAttribute(Constant.SESSION_CODE.ORDER_FLAG, Constant.FLAG_CODE.INSERT);
                }
                return "redirect:/cart/order";
            }
            //--------------
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.CARD;
    }

    @RequestMapping(value = {"/cart/order"}, method = RequestMethod.GET)
    public String listProduct(HttpSession session, Model model, @RequestParam(value = "page", required =  false, defaultValue = "1") int page) {
        try{
            if(session.getAttribute(Constant.SESSION_CODE.ORDER_FLAG) != null) {
                model.addAttribute("flag", session.getAttribute(Constant.SESSION_CODE.ORDER_FLAG));
                session.removeAttribute(Constant.SESSION_CODE.ORDER_FLAG);
            }

            List<Order> orders = this.userCurrent().getOrders();
            Collections.sort(orders, new Comparator<Order>() {
                public int compare(Order o1, Order o2) {
                   return o1.getCode().compareTo(o2.getCode());
                }
            });
            Collections.reverse(orders);
            this.pargingInfo.setPageCurrent(page);
            this.pargingInfo.setTotal(orders.size());

            int offset = (this.pargingInfo.getPageCurrent() - 1) * this.pargingInfo.maxItemView;
            int limit = this.pargingInfo.maxItemView;

            if(offset == 0 && orders.size() < offset + this.pargingInfo.maxItemView) {
                limit = orders.size();
            }else  if(offset > orders.size()) {
                offset = orders.size() - 1;
                limit = 0;
            } else if(orders.size() < offset + limit) {
                limit = orders.size() - offset;
            }
            orders = orders.subList(offset, offset + limit);
            model.addAttribute("orders", orders);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.LIST_ORDER;
    }

    @RequestMapping(value = {"/cart/order/{orderCode}"}, method = RequestMethod.GET)
    public  String orderDetail(Model model, @PathVariable(value = "orderCode") String orderCode,
                               HttpSession session){
        try{

            Order order = this.iOrderService.findOrderByOrderCode(userCurrent().getEmail(),
                    orderCode);
            if(order == null) {
                return "redirect:/";
            }
            model.addAttribute("order",
                    order);
            model.addAttribute("flag", session.getAttribute(Constant.SESSION_CODE.ORDER_FLAG));
            session.removeAttribute(Constant.SESSION_CODE.ORDER_FLAG);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.ORDER_DETAIL;
    }

    @RequestMapping(value = {"/cart/order/{orderCode}/cancel"}, method = RequestMethod.GET)
    public  String orderDetailProcess(Model model, @PathVariable(value = "orderCode") String orderCode,
                                      HttpSession session){
        try{

            Order order = this.iOrderService.findOrderByOrderCode(userCurrent().getEmail(),
                    orderCode);
            if(order == null) {
                return "redirect:/";
            }

            if(order.getStatus() > 1) {
                session.setAttribute(Constant.SESSION_CODE.ORDER_FLAG, 1);
            }else{
                this.iOrderService.updateStatusOrder(order.getUserID(), order.getCode(), Constant.ORDER_STATUS.CANCEL);
                session.setAttribute(Constant.SESSION_CODE.ORDER_FLAG, 2);
            }

        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return "redirect:/cart/order/" + orderCode;
    }

    @RequestMapping(value = {"/cart/{orderCode}/print"}, method = RequestMethod.GET)
    public  void printOrder(@PathVariable(value = "orderCode") String orderCode,
                            HttpServletResponse response) {
        try{
            Result result = this.iOrderService.printOrder(this.userCurrent().getEmail(), orderCode );
//            Result result = this.iOrderService.printOrder("linh12@gmail.com", orderCode );

            InputStream is = new FileInputStream(result.getFileName());

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + result.getFileName() + "\""));

            OutputStream outputStream;

            outputStream = response.getOutputStream();
            FileCopyUtils.copy(is, outputStream);

        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    // Admin -----------------------------
    @RequestMapping(value = {"/admin/orders"}, method = RequestMethod.GET)
    public  String adminGetOrders(Model model, HttpSession session) {
        try{
            pargingInfo.setPageCurrent(1);
            Result result = this.iOrderService.getOrders(null, null, null, -1,
                    (pargingInfo.pageCurrent - 1) * pargingInfo.maxItemView, pargingInfo.maxItemView);
            pargingInfo.setTotal(result.getTotal());
            model.addAttribute("result", result);

            //
            session.setAttribute(Constant.SESSION_CODE.AD_PAGE_CURRENT, 1);
            session.setAttribute(Constant.SESSION_CODE.AD_ORDER_STATUS, -1);
            session.setAttribute(Constant.SESSION_CODE.AD_ORDER_TEXT_SEARCH, "");
            session.setAttribute(Constant.SESSION_CODE.AD_ORDER_FROM_DATE, null);
            session.setAttribute(Constant.SESSION_CODE.AD_ORDER_TO_DATE, null);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.ADMIN_LIST_ORDER;
    }

    @RequestMapping(value = {"/admin/orders/search"}, method = RequestMethod.GET)
    public  String adminGetOrdersSearch(Model model,
                                        HttpSession session,
                                        @RequestParam(value = "orderCode", defaultValue = "", required = false) String orderCode,
                                        @RequestParam(value = "fromDate", defaultValue = "", required = false) String fromDate,
                                        @RequestParam(value = "toDate", defaultValue = "", required = false) String toDate,
                                        @RequestParam(value = "status", defaultValue = "-1", required = false) int status,
                                        @RequestParam(value = "redirect", defaultValue = "false", required = false) boolean redirect,
                                        @RequestParam(value = "page", required =  false,
                                                defaultValue = "1") int page) {
        try{
            if(redirect) {
                orderCode = (String) session.getAttribute(Constant.SESSION_CODE.AD_ORDER_TEXT_SEARCH);
                fromDate = (String) session.getAttribute(Constant.SESSION_CODE.AD_ORDER_FROM_DATE);
                toDate = (String) session.getAttribute(Constant.SESSION_CODE.AD_ORDER_TO_DATE);
                status = (Integer) session.getAttribute(Constant.SESSION_CODE.AD_ORDER_STATUS);
//                page = (Integer) session.getAttribute(Constant.SESSION_CODE.AD_PAGE_CURRENT);
            }
            pargingInfo.setPageCurrent(page);

            Result result = this.iOrderService.getOrders(orderCode, fromDate, toDate, status,
                    (pargingInfo.pageCurrent - 1) * pargingInfo.maxItemView, pargingInfo.maxItemView);

            pargingInfo.setTotal(result.getTotal());
            model.addAttribute("result", result);
            model.addAttribute("orderCode", orderCode);
            model.addAttribute("fromDate", fromDate);
            model.addAttribute("toDate", toDate);
            model.addAttribute("status", status);

            //
            session.setAttribute(Constant.SESSION_CODE.AD_PAGE_CURRENT, pargingInfo.getPageCurrent());
            session.setAttribute(Constant.SESSION_CODE.AD_ORDER_STATUS, status);
            session.setAttribute(Constant.SESSION_CODE.AD_ORDER_TEXT_SEARCH, orderCode);
            session.setAttribute(Constant.SESSION_CODE.AD_ORDER_FROM_DATE, fromDate);
            session.setAttribute(Constant.SESSION_CODE.AD_ORDER_TO_DATE, toDate);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.ADMIN_LIST_ORDER;
    }

    @RequestMapping(value = {"/admin/orders/detail/{userID}/{orderCode}"}, method = RequestMethod.GET)
    public String adminOrderDetail(Model model, @PathVariable(value = "userID") String userID,
                                   @PathVariable(value = "orderCode") String orderCode,
                                   HttpSession session) {
        try{
            Order order  = this.iOrderService.findOrderByOrderCode(userID, orderCode);
            if(order == null) {
                return "redirect:/admin/orders";
            }
            model.addAttribute("userInfo", this.iUserService.findByEmail(order.getUserID()));
            model.addAttribute("order", order);
            model.addAttribute("flag", session.getAttribute(Constant.SESSION_CODE.AD_ORDER_FLAG));
            session.removeAttribute(Constant.SESSION_CODE.AD_ORDER_FLAG);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return Constant.TEMPLATE_VIEW.ADMIN_ORDER_DETAIL;
    }

    @RequestMapping(value = {"/admin/orders/detail/{userID}/{orderCode}"}, method = RequestMethod.POST)
    public String adminOrderDetail(Model model, @PathVariable(value = "userID") String userID,
                                   @PathVariable(value = "orderCode") String orderCode,
                                   @RequestParam( value = "status", defaultValue = "0") int status,
                                   HttpSession session) {
        try{
            Order order  = this.iOrderService.findOrderByOrderCode(userID, orderCode);
            if(order == null) {
                return "redirect:/admin/orders";
            }
            this.iOrderService.updateStatusOrder(userID, orderCode, status);
            session.setAttribute(Constant.SESSION_CODE.AD_ORDER_FLAG, Constant.FLAG_CODE.UPDATE);
        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return "redirect:/admin/orders/detail/"+ userID + "/" + orderCode;
    }
}

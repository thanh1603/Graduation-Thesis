package vnua.khoaluan.service;

import vnua.khoaluan.entities.User;
import vnua.khoaluan.bean.Result;
import vnua.khoaluan.entities.Order;

public interface IOrderService {
    Result saveOrder(User userInfo, Order order);

    Result updateStatusOrder(String userId, String orderCode, int status);

    Order findOrderByOrderCode(String userID, String orderCode);

    Result printOrder(String userName, String orderCode);

    Result getOrders(String textSearch, String fromDate, String toDate, int status, int offset,int limit);
}

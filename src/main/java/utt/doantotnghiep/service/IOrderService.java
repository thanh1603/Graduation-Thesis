package utt.doantotnghiep.service;

import utt.doantotnghiep.bean.Result;
import utt.doantotnghiep.entities.Order;
import utt.doantotnghiep.entities.User;

public interface IOrderService {
    Result saveOrder(User userInfo, Order order);

    Result updateStatusOrder(String userId, String orderCode, int status);

    Order findOrderByOrderCode(String userID, String orderCode);

    Result printOrder(String userName, String orderCode);

    Result getOrders(String textSearch, String fromDate, String toDate, int status, int offset,int limit);
}

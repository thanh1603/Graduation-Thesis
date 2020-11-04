<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../template/admin_header.jsp"/>
<script type="text/javascript" src="<c:url value="/resources/vendor/datatables/datatables.min.js"/>"></script>
<style>
    td {
        vertical-align: middle !important;
    }
    .info {
        border: 1px solid #ccccdc;
        padding: 10px;
        margin-bottom: 15px;
    }

</style>
<div class="dashboard-wrapper">
    <div class="dashboard-ecommerce">
        <div class="container-fluid dashboard-content " style="padding-top: 15px">

            <div class="row">
                <div class="clearfix"></div>
                <div class="col-sm-12 text-center">
                    <h3>DH - ${order.code}</h3>
                </div>
                <div class="col-sm-12">
                    <div class="panel panel-default">
                        <div class="info">
                            <div class="row">
                                <div class="col-sm-4" style="text-transform: capitalize">Họ tên: ${userInfo.fullName}</div>
                                <div class="col-sm-4">Email: ${userInfo.email}</div>
                                <div class="col-sm-4">Số điện thoại: ${userInfo.phone}</div>
                                <div class="col-sm-12" style="margin-top: 10px">Địa chỉ: ${userInfo.address}</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                      <form method="post">
                          <div class="row">
                              <div class="col-sm-2" style="margin-left: 5px">
                                  Trạng thái
                              </div>
                              <div class="col-sm-6">
                                  <select name="status" class="form-control" id="status">
                                      <option value="1" <c:if test="${order.status eq 1}"> selected</c:if>>Đang chờ xử lý</option>
                                      <option value="2"  <c:if test="${order.status eq 2}"> selected</c:if>> Đã tiếp nhận</option>
                                      <option value="3"  <c:if test="${order.status eq 3}"> selected</c:if>> Đơn hàng đang được giao tới khách hàng</option>
                                      <option value="100"  <c:if test="${order.status eq 100}"> selected</c:if>>Đã kết thúc</option>
                                      <option value="-99"  <c:if test="${order.status eq -99}"> selected</c:if>> Đã hủy</option>
                                  </select>
                              </div>
                             <div class="col-sm-3">
                                 <button class="btn btn-warning"
                                         onclick="window.location = '${pageContext.request.contextPath}/cart/${order.code}/print'"><i
                                         class="fa fa-refresh"></i> Cập nhật
                                 </button>
                             </div>
                          </div>
                      </form>
                </div>
                <div class="col-sm-6 text-right" style="margin-bottom: 15px">
                    <button class="btn btn-success"
                            onclick="window.location = '${pageContext.request.contextPath}/cart/${order.code}/print'"><i
                            class="fa fa-print"></i> In hóa đơn
                    </button>
                </div>
                <div class="col-sm-12">
                    <c:if test="${requestScope.flag eq 2}">
                        <div class="alert alert-success">
                            Cập nhật trạng thái thành công
                        </div>
                    </c:if>
                </div>
                <div class="col-sm-12 col-xs-12 col-md-12">
                    <table class="table table-bordered" id="dataTable">
                        <thead>
                        <tr>
                            <td class="text-center"><strong>Ảnh</strong></td>
                            <td class="text-center"><strong>Sản phẩm</strong></td>
                            <td class="text-center"><strong>Đơn giá</strong></td>
                            <td class="text-center"><strong>Số lượng</strong></td>
                            <td class="text-center"><strong>Tổng</strong></td>
                        </tr>
                        </thead>
                        <tbody id="10">
                        <c:forEach items="${order.carts}" var="cart">
                            <tr>
                                <td class="text-center"><img
                                        src="${pageContext.request.contextPath}/product/imageURL/${cart.product.catalogCode}/${cart.product.code}/small"
                                        alt="${cart.product.name}" title="${cart.product.name}" width="80"/></td>
                                <td class="text-left">${cart.product.name}</td>
                                <td class="text-right"><fmt:formatNumber
                                        type="number"
                                        maxFractionDigits="3"
                                        value="${cart.product.price - (cart.product.price * cart.product.discount)/100}"/>đ
                                    <input type="hidden"
                                           value="${(cart.product.price - (cart.product.price * cart.product.discount)/100)}"
                                           id="${cart.product.code}-price">
                                </td>
                                <td class="text-right" style="width: 20%">
                                        ${cart.amount}
                                    <input type="hidden" value="${cart.product.price}" id="${cart.product.code}-amount">
                                </td>
                                <td class="text-right" id="${cart.product.code}-total"><fmt:formatNumber
                                        type="number"
                                        maxFractionDigits="3"
                                        value="${(cart.product.price - (cart.product.price * cart.product.discount)/100) * cart.amount}"/>đ
                                </td>
                            </tr>

                        </c:forEach>
                        </tbody>
                    </table>
                    <%--</form>--%>
                </div>
                <div class="col-sm-12"></div>
                <div class="col-sm-8"></div>
                <div class="col-sm-4 ">
                    <table class="table table-bordered">
                        <tr>
                            <td class="text-right">Thành tiền:</td>
                            <td class="text-right"><strong> <span id="totalMoneyAll">0đ</span></strong></td>
                        </tr>
                        <tr>
                            <td class="text-right">Phí vận chuyển Toàn Quốc:</td>
                            <td class="text-right"><strong><fmt:formatNumber
                                    type="number"
                                    maxFractionDigits="3"
                                    value="${feeTransfer}"/>đ</strong></td>
                        </tr>
                        <tr>
                            <td class="text-right">Tổng số:</td>
                            <td class="text-right"><strong> <span id="totalPay">0đ</span></strong></td>
                        </tr>
                    </table>
                </div>
                <div class="clearfix"></div>
                <div class="col-sm-12">
                    <div class="row">
                        <div class="col-sm-6 col-xs-6 col_button_shopping"><a
                                href="${pageContext.request.contextPath}/admin/orders/search?redirect=true"
                                class="btn btn-default pull-left button_shopping">Quay lại</a></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var productID = [];
    var feeTransfer = Number("${feeTransfer}");
    var totalMoney = 0;
    <c:forEach items="${order.carts}" var="item">
    productID.push("${item.product.code}");
    var price = Number("${item.product.price}");
    var discount = Number("${item.product.discount}");
    var amount = Number("${item.amount}");
    totalMoney += (price - price * discount / 100) * amount;

    </c:forEach>

    $(document).ready(function (event) {
        $('#dataTable').DataTable({
            searching: false,
            ordering: true,
            paging: false,
            emptyTable: "Không có sản phẩm nào"
        });
        total();
    });

    function total() {
        $('#totalMoneyAll').text(formatMoney(totalMoney) + "đ");
        $('#totalPay').text(formatMoney(totalMoney + feeTransfer) + "đ");
    }

</script>


<jsp:include page="../template/admin_footer.jsp"/>
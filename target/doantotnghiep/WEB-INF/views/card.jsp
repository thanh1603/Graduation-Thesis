<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="template/header.jsp"/>
<script type="text/javascript" src="<c:url value="/resources/vendor/datatables/datatables.min.js"/>"></script>
<style>
    td{
        vertical-align: middle !important;
    }
</style>
<div class="container" >
    <div class="row">
        <div class="clearfix"></div>
        <div class="col-sm-12 col-xs-12 col-md-12"><h4>Giỏ hàng</h4>
            <table class="table table-bordered" id="dataTable">
                        <thead>
                        <tr>
                            <td class="text-center"><strong>Ảnh</strong></td>
                            <td class="text-center"><strong>Sản phẩm</strong></td>
                            <td class="text-center"><strong>Đơn giá</strong></td>
                            <td class="text-center"><strong>Số lượng</strong></td>
                            <td class="text-center"><strong>Tổng</strong></td>
                            <td class="text-center"><strong>Xóa</strong></td>
                        </tr>
                        </thead>
                        <tbody id="10">
                        <c:forEach items="${carts}" var="cart">
                            <tr>
                                <td class="text-center"><img
                                        src="${pageContext.request.contextPath}/product/imageURL/${cart.product.catalogCode}/${cart.product.code}/small"
                                        alt="${cart.product.name}" title="${cart.product.name}" width="80"/></td>
                                <td class="text-left"><a href="#">${cart.product.name}</a></td>
                                <td class="text-right"> <fmt:formatNumber
                                        type="number"
                                        maxFractionDigits="3"
                                        value="${cart.product.price - (cart.product.price * cart.product.discount)/100}"/>đ
                                    <input type="hidden" value="${(cart.product.price - (cart.product.price * cart.product.discount)/100)}" id="${cart.product.code}-price">
                                </td>
                                <td class="text-left" style="width: 20%">
                                    <form:form method="post" modelAttribute="cartForm" action="${pageContext.request.contextPath}/cart/update">
                                        <div class="input-group btn-block" style="top: 15px">
                                                <form:input path="amount" type="number" cssStyle="height: 40px"
                                                            value="${cart.amount}" min="1" size="1"
                                                            maxlength="12"
                                                            class="form-control amount" id="${cart.product.code}-amount"
                                                            onchange="amountChange(this)"/>

                                            <span class="input-group-btn"> <button type="submit" data-toggle="tooltip"
                                                                                   title="Cập nhật" class="btn btn-primary"><i
                                                    class="fa fa-refresh"></i></button> </span></div>
                                        <form:hidden path="catalogCode" value="${cart.product.catalogCode}" />
                                        <form:hidden path="productCode" value="${cart.product.code}" />
                                    </form:form>
                                </td>
                                <td class="text-right" id="${cart.product.code}-total"> <fmt:formatNumber
                                        type="number"
                                        maxFractionDigits="3"
                                        value="${(cart.product.price - (cart.product.price * cart.product.discount)/100) * cart.amount}"/>đ</td>

                                <td class="text-center">
                                    <button type="button" data-toggle="tooltip" title="Xóa" class="btn btn-danger"
                                            onclick="window.location = '${pageContext.request.contextPath}/cart/remove/${cart.product.code}'"><i class="fa fa-times-circle"></i></button>
                                </td>
                            </tr>

                        </c:forEach>
                        </tbody>
                    </table>
            <%--</form>--%>
            <div class="row">
                <div class="col-sm-12"></div>
                <div class="col-sm-4 col-sm-offset-8 ${cartSize == 0 ?'hidden': ''}" id="info">
                    <table class="table table-bordered">
                        <tr>
                            <td class="text-right">Thành tiền:</td>
                            <td class="text-right" ><strong > <span id="totalMoneyAll">0đ</span></strong></td>
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
                            <td class="text-right"><strong > <span id="totalPay">0đ</span></strong></td>
                        </tr>
                    </table>
                </div>
                <div class="clearfix"></div>
                <div class="col-sm-12">
                    <div class="row">
                        <div class="col-sm-6 col-xs-6 col_button_shopping"><a href="${pageContext.request.contextPath}/"
                                                                              class="btn btn-default pull-left button_shopping">Tiếp
                            tục mua hàng</a></div>
                        <div class="col-sm-6 col-xs-6 col_button_checkout">
                            <c:if test="${user != null && user.orders.size() != 0}" >
                                <button
                                        onclick="window.location = '${pageContext.request.contextPath}/cart/order' "
                                        class="btn  btn-warning" style="right: -110px;
    position: relative;">Danh danh sách đơn hàng đã đặt</button>
                            </c:if>

                            <button <c:if test="${cartSize == 0}" > disabled = "disabled"</c:if>
                                    <c:if test="${cartSize != 0}" > onclick="window.location = '${pageContext.request.contextPath}/cart/checkout' "</c:if>
                                class="btn btn-primary pull-right button_checkout">Tiến hành đặt hàng</button></div>
                        <%--href="${pageContext.request.contextPath}/cart/checkout"--%>
                    </div>
                </div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<script type="text/javascript">
    var productID = [];
    var feeTransfer =  Number("${feeTransfer}");
    <c:forEach items="${carts}" var="item">
         productID.push("${item.product.code}");
    </c:forEach>

    function amountChange(dom) {
        total();
    }

    $(document).ready(function (event) {
        $('#dataTable').DataTable( {
            searching: false,
            ordering:  true,
            paging: false,
            emptyTable: "Không có sản phẩm nào"
        });
        total();
    });

    function total() {
        var totalMoneyAll = 0;
        for(var i = 0; i< productID.length; i++) {
            var price = $("#"+productID[i] + "-price").val();
            var amount = $("#"+productID[i] + "-amount").val();
            var totalMoney = Number(price) * Number(amount);
            totalMoneyAll += totalMoney;
            $("#" + productID[i] + "-total").text(formatMoney(totalMoney) + "đ") ;
        }
        $('#totalMoneyAll').text(formatMoney(totalMoneyAll) + "đ");
        $('#totalPay').text(formatMoney(totalMoneyAll + feeTransfer) + "đ");
    }

    function formatMoney(amount, decimalCount = 0, decimal = ".", thousands = ".") {
        try {
            decimalCount = Math.abs(decimalCount);
            decimalCount = isNaN(decimalCount) ? 2 : decimalCount;

            var negativeSign = amount < 0 ? "-" : "";

            var i = parseInt(amount = Math.abs(Number(amount) || 0).toFixed(decimalCount)).toString();
            var j = (i.length > 3) ? i.length % 3 : 0;

            return negativeSign + (j ? i.substr(0, j) + thousands : '') + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousands) + (decimalCount ? decimal + Math.abs(amount - i).toFixed(decimalCount).slice(2) : "");
        } catch (e) {
            console.log(e)
        }
    };

</script>



<jsp:include page="template/footer.jsp"/>
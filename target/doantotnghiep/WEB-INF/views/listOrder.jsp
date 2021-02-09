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
<div class="container">
    <div class="row">
        <div class="clearfix"></div>
        <div class="col-sm-12 col-xs-12 col-md-12"><h4>Danh dách đơn hàng</h4>
            <c:if test="${requestScope.flag eq 1}">
                <div class="alert alert-success">
                   Đặt hàng thành công
                </div>
            </c:if>
            <table class="table table-bordered" id="dataTable">
                <thead>
                    <tr>
                        <th class="text-center no-sort" style="width: 100px;"><strong>STT </strong></th>
                        <th class="text-center"><strong>Mã đơn hàng </strong></th>
                        <th class="text-center"><strong>Thời gian đặt hàng</strong></th>
                        <th class="text-center"><strong>Trạng thái</strong></th>
                    </tr>
                </thead>
                <tbody id="10">
                <c:forEach items="${orders}" var="order" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td><a href="${pageContext.request.contextPath}/cart/order/${order.code}">DH - ${order.code}</a></td>
                        <td>${order.createDate}</td>
                        <td>
                            <c:if test="${order.status eq 1}">
                                Đang chờ xử lý
                            </c:if>
                            <c:if test="${order.status eq 2}">
                                Đơn hàng đã tiếp nhận
                            </c:if>
                            <c:if test="${order.status eq 3}">
                                Đơn hàng đang được vận chuyển
                            </c:if>
                            <c:if test="${order.status eq -99}">
                                Đơn hàng đã hủy
                            </c:if>
                            <c:if test="${order.status eq 100}">
                                Đơn hàng đã hoàn thành
                            </c:if>
                        </td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
            <%--</form>--%>
        </div>
        <div class="clearfix"></div>
        <div class="col-xs-12 text-center" style="margin-left: auto; margin-right: auto">
            <nav aria-label="Page navigation example" >
                <ul class="pagination" id="page">

                </ul>
            </nav>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(document).ready(function (event) {
        $('#dataTable').DataTable( {
            searching: false,
            ordering:  true,
            paging: false,
            emptyTable: "Không có sản phẩm nào",
            "columnDefs": [ {
                "targets"  : 'no-sort',
                "orderable": false,
                "order": []
            }]
        });

        paging(pargigInfo.pageCurrent,pargigInfo.total ,
            pargigInfo.maxShow, pargigInfo.maxPage, onViewPage);
    });

    function onViewPage(page) {
            window.location = "${pageContext.request.contextPath}/cart/order?page=" + page;
    }

</script>



<jsp:include page="template/footer.jsp"/>
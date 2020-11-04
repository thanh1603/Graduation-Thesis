<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../template/admin_header.jsp"/>
<script type="text/javascript" src="<c:url value="/resources/vendor/datatables/datatables.min.js"/>"></script>
<style>
    td{
        vertical-align: middle !important;
    }
    input{
        height: 41px !important;
    }
    input[type='radio'] {
        width: 25px !important;
        position: relative!important;
        top: 15px!important;
        display: inline-block;
    }
</style>
<div class="dashboard-wrapper" >
    <div class="dashboard-ecommerce" >
        <div class="container-fluid dashboard-content " style="padding-top: 0">
            <form action="${pageContext.request.contextPath}/admin/orders/search" method="get" id="formOrderSearch">
                <div class="row">
                <div class="clearfix"></div>

                <%--<div class="col-sm-12 col-xs-12 col-md-12 text-center"><h3>Danh dách đơn hàng</h3></div>--%>

                <div class="col-sm-12">
                    <input type="radio" name="typeSearch" value="1"  <c:if test="${not empty orderCode}"> checked="checked" </c:if> class="form-control typeSearch" id="search">
                    <label for="search" style="color: #000">Tìm kiếm theo mã đơn hàng</label>
                </div>


                <div class="col-sm-2" style="margin-left: 50px;margin-right: 15px; position: relative;top: 10px;color: #000;font-size: 15px;">Mã đơn hàng: </div>

                <div class="col-sm-2" style="margin-right: 15px">
                    <input type="text" autocomplete="off" value="${orderCode}" name="orderCode" class="form-control" id="orderCode">
                </div>

                <div class="col-sm-12">
                    <input type="radio"  name="typeSearch"  <c:if test="${empty orderCode}"> checked="checked" </c:if>value="2" class="form-control typeSearch" id="search1">
                    <label for="search1"  style="color: #000">Tìm kiếm theo khoảng thời gian và trạng thái</label>
                </div>


                <div class="ui-helper-clearfix"></div>

                <div class="col-sm-2" style="margin-left: 50px; margin-right: 15px; position: relative;top: 10px;color: #000;font-size: 15px;">Khoảng thời gian: </div>

                <div class="col-sm-2" style="margin-right: 15px">
                    <input type="text" autocomplete="off" value="${fromDate}" placeholder="dd/MM/yyyy"  name="fromDate" class="form-control" id="fromDate">
                </div>
                <div class="col-sm-1" style="text-align: center;
    right: -20px;"> <span style="    position: relative;
                                                        width: 21px;
                                                        font-size: 25px;
                                                        display: block; ">~</span></div>
                <div class="col-sm-2" style="margin-right: 15px">
                    <input type="text" autocomplete="off"  value="${toDate}"  placeholder="dd/MM/yyyy"  name="toDate" class="form-control" id="toDate">
                </div>

                <div class="col-sm-1" style="margin-right: 15px; position: relative;top: 10px;color: #000;font-size: 15px;">
                   Trạng thái:
                </div>

                <div class="col-sm-2" style="margin-right: 15px">
                    <select name="status" id="" class="form-control" id="status">
                        <option value="-1">-----------------------------------</option>
                        <option value="1" <c:if test="${status eq 1}"> selected</c:if>>Đơn hàng đang chờ xử lý</option>
                        <option value="2"  <c:if test="${status eq 2}"> selected</c:if>> Đơn hàng đã tiếp nhận</option>
                        <option value="3"  <c:if test="${status eq 3}"> selected</c:if>> Đơn hàng đang được giao tới khách hàng</option>
                        <option value="100"  <c:if test="${status eq 100}"> selected</c:if>>Đơn hàng đã kết thúc</option>
                        <option value="-99"  <c:if test="${status eq -99}"> selected</c:if>> Đơn hàng đã hủy</option>
                    </select>
                </div>

                <div class="col-sm-12 text-right" style="margin-top: 15px">
                    <button class="btn btn-warning" type="button" onclick="clearSearch()"><i class="fa fa-times-circle"></i> Xóa tìm kiếm</button>
                    <button class="btn btn-success" type="button" onclick=" onSearch(this)"><i class="fa fa-search"></i> Tìm kiếm</button>
                </div>
                <div class="clearfix"></div>
                <div class="col-sm-12 col-xs-12 col-md-12 text-center" style="padding-left: 0; margin-top: 15px">
                <table class="table table-bordered" id="dataTable" style="margin-bottom: 15px">
                    <thead>
                        <tr>
                            <th class="text-center no-sort" style="width: 100px;"><strong>STT </strong></th>
                            <th class="text-center"><strong>Mã đơn hàng </strong></th>
                            <th class="text-center"><strong>Thời gian đặt hàng</strong></th>
                            <th class="text-center"><strong>Trạng thái</strong></th>
                        </tr>
                    </thead>
                    <tbody id="10">
                    <c:forEach items="${result.orders}" var="order" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td><a href="${pageContext.request.contextPath}/admin/orders/detail/${order.userID}/${order.code}">DH - ${order.code}</a></td>
                            <td>${order.createDate}</td>
                            <td>
                                <c:if test="${order.status eq 1}">
                                    Đơn hàng đang chờ xử lý
                                </c:if>
                                <c:if test="${order.status eq 2}">
                                    Đơn hàng đã tiếp nhận
                                </c:if>
                                <c:if test="${order.status eq 3}">
                                   Đơn hàng đang được giao tới khách hàng
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
            </form>
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

        //------DATEPICER
        var dateFormat =  'dd/mm/yy';
        $('#fromDate').datepicker({dateFormat: dateFormat});
        $('#toDate').datepicker({dateFormat: dateFormat});
    });

    function onViewPage(page) {
            window.location = "${pageContext.request.contextPath}/admin/orders/search?page=" + page + "&redirect=true";
    }
    
    function clearSearch() {
        $("#toDate").val("");
        $("#fromDate").val("");
        $("#status").val("-1");
        $("#orderCode").val("");
        $(".typeSearch").val("2");
        $('#formOrderSearch').trigger('submit');
    }
    
    function onSearch(dom) {
        // $(dom).preventDefault();
        var typeSearch = $("input[name='typeSearch']:checked"). val();
       if( typeSearch == 1) {
           $("#toDate").val("");
           $("#fromDate").val("");
           $("#status").val("-1");
       }else{
           $("#orderCode").val("");
       }
       $('#formOrderSearch').trigger('submit');
       return true;
    }

</script>
<jsp:include page="../template/admin_footer.jsp"/>
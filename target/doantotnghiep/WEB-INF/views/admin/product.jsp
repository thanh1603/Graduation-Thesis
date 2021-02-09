<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../template/admin_header.jsp"/>
<script type="text/javascript" src="<c:url value="/resources/vendor/datatables/datatables.min.js"/>"></script>

<div class="dashboard-wrapper">
    <div class="dashboard-ecommerce">
        <div class="container-fluid dashboard-content ">
            <div class="col-xs-12">
                <c:if test="${requestScope.flag eq 1}">
                    <div class="alert alert-success">
                        Thêm sản phẩm thành công
                    </div>
                </c:if>
                <c:if test="${requestScope.flag eq 2}">
                    <div class="alert alert-success">
                        Cập nhật sản phẩm thành công
                    </div>
                </c:if>
                <c:if test="${requestScope.flag eq 3}">
                    <div class="alert alert-success">
                        Xóa sản phẩm thành công
                    </div>
                </c:if>
                <c:if test="${requestScope.flag eq 30}">
                    <div class="alert alert-warning">
                        Xóa sản phẩm không thành công, vui lòng thử lại
                    </div>
                </c:if>
            </div>
           <form action="${pageContext.request.contextPath}/admin/product/search" method="get">
               <div class="row" style="margin-left: 5px">
                   <div class="col-xs-3 " style="margin-bottom: 15px; width: 300px; margin-right: 15px">
                       <select  id="catalogCode" class="form-control" name="catalogCode">
                           <c:forEach items="${catalogList}" var="item">
                               <option value="${item.code}" ${item.code eq param['catalogCode'] ? 'selected' : ''}>${item.name}</option>
                           </c:forEach>
                       </select>
                   </div>
                   <div class="col-xs-3" style="margin-bottom: 15px; width: 300px; margin-right: 15px; height: 30px">
                       <input type="text" name="textSearch" style="height: 38px;" value="${param['textSearch']}" class="form-control" placeholder="Nhập từ khóa tìm kiếm">
                   </div>

                   <div class="col-xs-3">
                       <button class="btn btn-default">Tìm kiếm</button>
                   </div>
               </div>
           </form>
            <div class="clearfix"></div>



            <div class="col-xs-12" style="margin-top: 10px;margin-bottom: 10px">
                <button class="btn btn-primary " style="margin-bottom: 10px" onclick="window.location = '${pageContext.request.contextPath}/admin/product/add'">
                    Thêm sản phẩm
                </button>
                <table class="table table-bordered" id="dataTable">
                    <thead>
                    <tr>
                        <td class="text-center"><strong>Ảnh</strong></td>
                        <td class="text-center"><strong>Mã sản phẩm</strong></td>
                        <td class="text-center"><strong>Tên sản phẩm</strong></td>
                        <td class="text-center"><strong>Đơn giá</strong></td>
                        <td class="text-center"><strong>Số lượng</strong></td>
                        <td class="text-center"><strong>Giảm giá</strong></td>
                        <td class="text-center"><strong>Hoạt động</strong></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${result.products}" var="product">
                        <tr>
                            <td class="text-center"><img
                                src="${pageContext.request.contextPath}/product/imageURL/${product.catalogCode}/${product.code}/small"
                                alt="${product.name}" title="${product.name}" width="100"/></td>
                            <td class="text-left"><a href="${pageContext.request.contextPath}/admin/product/update/${product.catalogCode}/${product.code}">${product.code}</a></td>
                            <td class="text-left"><a href="${pageContext.request.contextPath}/admin/product/update/${product.catalogCode}/${product.code}">${product.name}</a></td>
                            <td class="text-right"><span>${product.price}</span></td>
                            <td class="text-right"><span>${product.amount}</span></td>
                            <td class="text-right"><span>${product.discount}</span></td>
                            <td class="text-center">
                                <button type="button" data-toggle="tooltip" title="Cập nhật" class="btn btn-success"
                                        onclick="window.location = '${pageContext.request.contextPath}/admin/product/update/${product.catalogCode}/${product.code}'">
                                    <i class="fa fa-edit"></i></button>
                                <button type="button" data-toggle="tooltip" title="Xoá" class="btn btn-danger"
                                        onclick="deleteProduct('${product.catalogCode}','${product.code}')">
                                    <i class="fa fa-times-circle"></i></button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="col-xs-12 text-center" style="width: 500px; margin-left: auto; margin-right: auto">
                <nav aria-label="Page navigation example" >
                    <ul class="pagination" id="page">

                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <!-- ============================================================== -->
</div>

<script type="text/javascript">

    $(document).ready(function (event) {
        paging(pargigInfo.pageCurrent,pargigInfo.total ,
                    pargigInfo.maxShow, pargigInfo.maxPage, onViewPage);

        $('#dataTable').DataTable( {
            searching: false,
            ordering:  true,
            paging: false,
            emptyTable: "Không có sản phẩm nào"
        });
    });

    function onViewPage(page) {
        window.location = "${pageContext.request.contextPath}/admin/product/search?page=" + page
                            + "&catalogCode="
                            + "${catalogCode}&textSearch=" + "${param['textSearch']}" ;
    }
    
    function  deleteProduct(catalogCode, productCode) {
        $( "#dialog" ).dialog({
            title: "Bạn có muốn xóa sản phẩm không?",
            minWidth: "300",
            buttons: [
                {
                    text: "Không",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                },
                {
                    text: "Có",
                    click: function() {
                        $( this ).dialog( "close" );
                        window.location = '${pageContext.request.contextPath}' + "/admin/product/delete/" + catalogCode + "/" + productCode;
                    }
                }
            ]
        });
    }
</script>


<jsp:include page="../template/admin_footer.jsp"/>
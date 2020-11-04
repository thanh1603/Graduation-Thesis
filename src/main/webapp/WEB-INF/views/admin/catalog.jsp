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
                        Thêm loại sản phẩm thành công
                    </div>
                </c:if>
                <c:if test="${requestScope.flag eq 2}">
                    <div class="alert alert-success">
                        Cập nhật loại sản phẩm thành công
                    </div>
                </c:if>
            </div>
            <%--<form action="${pageContext.request.contextPath}/admin/product/search" method="get">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-xs-3">--%>
                        <%--<button class="btn btn-default">Tìm kiếm ${requestScope.flag}</button>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</form>--%>
            <%--<div class="clearfix"></div>--%>



            <div class="col-xs-12" style="margin-top: 10px;margin-bottom: 10px">
                <button class="btn btn-primary " style="margin-bottom: 10px" onclick="window.location = '${pageContext.request.contextPath}/admin/catalog/add'">
                    Thêm loại sản phẩm
                </button>
                <table class="table table-bordered" id="dataTable">
                    <thead>
                    <tr>
                        <td class="text-left"><strong>Mã loại sản phẩm</strong></td>
                        <td class="text-left"><strong>Tên loại sản phẩm</strong></td>
                        <td class="text-left"><strong>Hoạt động</strong></td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${catalogList}" var="catalog">
                        <tr>
                            <td class="text-left"><a href="${pageContext.request.contextPath}/admin/product?catalogCode=${catalog.code}">${catalog.code}</a></td>
                            <td class="text-left"><a href="${pageContext.request.contextPath}/admin/product?catalogCode=${catalog.code}">${catalog.name}</a></td>
                            <td class="text-center">
                                <button type="button" data-toggle="tooltip" title="Cập nhật" class="btn btn-success"
                                        onclick="window.location = '${pageContext.request.contextPath}/admin/catalog/update/${catalog.code}'">
                                    <i class="fa fa-edit"></i></button>
                                <%--<button type="button" data-toggle="tooltip" title="Xoá" class="btn btn-danger"--%>
                                        <%--onclick="window.location = '${pageContext.request.contextPath}/admin/catalog/delete/${catalog.code}'">--%>
                                    <%--<i class="fa fa-times-circle"></i></button>--%>
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
    $(document).ready( function () {
        $('#dataTable').DataTable( {
            searching: false,
            ordering:  true,
            paging: false,
            emptyTable : "Không có loại sản phẩm nào"
        });
    } );

    function onViewPage(page) {
        window.location = "${pageContext.request.contextPath}/admin/product/search?page=" + page
            + "&catalogCode="
            + "${catalogCode}&textSearch=" + "${param['textSearch']}" ;
    }
</script>


<jsp:include page="../template/admin_footer.jsp"/>
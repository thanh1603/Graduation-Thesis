<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../template/admin_header.jsp"/>
<script type="text/javascript" src="<c:url value="/resources/vendor/datatables/datatables.min.js"/>"></script>


<div class="dashboard-wrapper">
    <div class="dashboard-ecommerce">
        <div class="container-fluid dashboard-content ">

            <div class="col-xs-12" style="margin-top: 10px;margin-bottom: 10px">
                <table class="table table-bordered" id="dataTable">
                    <thead>
                    <tr>
                        <td class="text-left"><strong>Mã loại sản phẩm</strong></td>
                        <td class="text-left"><strong>Tên loại sản phẩm</strong></td>
                        <td class="text-left"><strong>Hoạt động</strong></td>
                    </tr>
                    </thead>
                    <tbody>
<%--                    <c:forEach items="${catalogList}" var="catalog">--%>
                        <tr>
                            <td class="text-left"><a >aaaaaa</a></td>
                            <td class="text-left"><a >bbbbbbbbbb</a></td>
                            <td class="text-center" > cccccccccccc</td>
                        </tr>
<%--                    </c:forEach>--%>
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
</script>


<jsp:include page="../template/admin_footer.jsp"/>
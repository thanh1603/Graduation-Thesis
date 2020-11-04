<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../template/admin_header.jsp"/>

<div class="dashboard-wrapper">
    <div class="dashboard-ecommerce">
        <div class="container-fluid dashboard-content ">
            <form:form modelAttribute="catalogForm"
                       action="${pageContext.request.contextPath}/admin/catalog/add" method="post">
                <div class="form-group"><label class="control-label" for="catalogCode">
                    Mã loại sản phẩm</label>
                    <form:input  path="code" type="text" class="form-control" id="catalogCode"/>
                </div>

                <c:if test="${result.mesStringByKey['codeError'] != null}">
                    <div class="form-group" id="codeError">
                        <p class="error">
                                ${result.mesStringByKey['codeError']}
                        </p>
                    </div>
                </c:if>

                <div class="form-group"><label class="control-label" for="catalogName">
                    Tên loại sản phẩm</label>
                    <form:input path="name" type="text" class="form-control" id="catalogName"/>
                </div>

                <c:if test="${result.mesStringByKey['nameError'] != null}">
                    <div class="form-group" id="nameError">
                        <p class="error">
                                ${result.mesStringByKey['nameError']}
                        </p>
                    </div>
                </c:if>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Thêm loại sản phẩm</button>
                    <button type="button" class="btn btn-success" onclick="window.location = '${pageContext.request.contextPath}/admin/catalog'">
                        Quay lại
                    </button>
                </div>
            </form:form>

        </div>
    </div>
    <!-- ============================================================== -->
</div>

<script type="text/javascript">
    $(document).ready(function () {
        if("${result.idScroll}") {
            $('html, body').animate({
                scrollTop: $("#" + "${result.idScroll}").offset().top - 300
            }, 1000);
        }
    });
</script>

<jsp:include page="../template/admin_footer.jsp"/>
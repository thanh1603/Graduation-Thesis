<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../template/admin_header.jsp"/>

<div class="dashboard-wrapper">
    <div class="dashboard-ecommerce">
        <div class="container-fluid dashboard-content ">
            <form:form modelAttribute="productForm"
                       action="${pageContext.request.contextPath}/admin/product/update" enctype="multipart/form-data">
                <div class="form-group"><label class="control-label" for="catalog">
                    Chọn loại sản phẩm</label>
                    <form:hidden path="catalogCode" class="form-control"/>
                    <input type="text" class="form-control" value="${productForm.catalogCode}" id="catalog" disabled="disabled">
                </div>

                <c:if test="${result.mesStringByKey['catalogCodeError'] != null}">
                    <div class="form-group" id="catalogCodeError">
                        <p class="error">
                                ${result.mesStringByKey['catalogCodeError']}
                        </p>
                    </div>
                </c:if>


                <div class="form-group"><label class="control-label" for="productCode">
                    Mã sản phẩm</label>
                    <form:hidden path="code" class="form-control"/>
                    <input type="text" class="form-control" value="${productForm.code}" id="productCode" disabled="disabled">
                </div>

                <c:if test="${result.mesStringByKey['codeError'] != null}">
                    <div class="form-group" id="codeError">
                        <p class="error">
                                ${result.mesStringByKey['codeError']}
                        </p>
                    </div>
                </c:if>

                <div class="form-group"><label class="control-label" for="productName">
                    Tên sản phẩm</label>
                    <form:input path="name" type="text" class="form-control" id="productName"/>
                </div>

                <c:if test="${result.mesStringByKey['nameError'] != null}">
                    <div class="form-group" id="nameError">
                        <p class="error">
                                ${result.mesStringByKey['nameError']}
                        </p>
                    </div>
                </c:if>

                <div class="form-group"><label class="control-label" for="productMount">
                    Số lượng</label>
                    <form:input path="amount" type="number" min="0" class="form-control" id="productMount"/>
                </div>

                <c:if test="${result.mesStringByKey['amountError'] != null}">
                    <div class="form-group" id="amountError">
                        <p class="error">
                                ${result.mesStringByKey['amountError']}
                        </p>
                    </div>
                </c:if>

                <div class="form-group"><label class="control-label" for="price">
                    Giá</label>
                    <form:input path="price" type="number" class="form-control" min="0" id="price"/>
                </div>

                <c:if test="${result.mesStringByKey['priceError'] != null}">
                    <div class="form-group" id="priceError">
                        <p class="error">
                                ${result.mesStringByKey['priceError']}
                        </p>
                    </div>
                </c:if>

                <div class="form-group"><label class="control-label" for="discount">
                    Giảm giá</label>
                    <form:input path="discount" type="number" min="0" max="100" class="form-control" id="discount"/>
                </div>

                <c:if test="${result.mesStringByKey['discountError'] != null}">
                    <div class="form-group" id="discountError">
                        <p class="error">
                                ${result.mesStringByKey['discountError']}
                        </p>
                    </div>
                </c:if>

                <div class="form-group"><label class="control-label" for="description">
                    Mô tả </label>
                    <form:textarea path="discription" name="" id="description" class="form-control" cols="30" rows="10"></form:textarea>
                </div>

               <div class="row">
                   <div class="col-xs-6">
                       <div class="form-group"><label class="control-label" for="imageSmallFile">
                           Ảnh hiện thị </label>
                           <form:input path="imageSmallFile" type="file"  id="imageSmallFile"/>
                       </div>
                       <div class="form-group text-center">
                           <img
                                   src="${pageContext.request.contextPath}/product/imageURL/${productForm.catalogCode}/${productForm.code}/small"
                                   alt="${productForm.name}" title="${productForm.name}" width="200"/>
                       </div>
                   </div>

                   <div class="col-xs-6">
                       <div class="form-group"><label class="control-label" for="imageLargeFile">
                           Ảnh hiện thị chi tiết sản phẩm: </label>
                           <form:input path="imageLargeFile" type="file" id="imageLargeFile"/>
                       </div>
                       <div class="form-group text-center">
                           <img
                                   src="${pageContext.request.contextPath}/product/imageURL/${productForm.catalogCode}/${productForm.code}/large"
                               alt="${productForm.name}" title="${productForm.name}" width="200"/>
                       </div>

                   </div>
               </div>

                <c:if test="${result.mesStringByKey['imageError'] != null}">
                    <div class="form-group" id="imageError">
                        <p class="error">
                                ${result.mesStringByKey['imageError']}
                        </p>
                    </div>
                </c:if>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary"> Cập nhật sản phẩm</button>
                    <button type="button" class="btn btn-success" onclick="window.location = '${pageContext.request.contextPath}/admin/product/search'">
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
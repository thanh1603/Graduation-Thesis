<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="../template/admin_header.jsp"/>

<div class="dashboard-wrapper">
    <div class="dashboard-ecommerce">
        <div class="container-fluid dashboard-content ">
            <form:form modelAttribute="productForm"
                       action="${pageContext.request.contextPath}/admin/product/add" enctype="multipart/form-data">
                <div class="form-group"><label class="control-label" for="catalog">
                    Chọn loại sản phẩm</label>
                    <form:select path="catalogCode" name="catalog" id="catalog" class="form-control">
                       <c:forEach items="${catalogList}" var="item">
                           <option value="${item.code}" ${item.code eq productForm.catalogCode ? 'selected' : ''}>${item.name}</option>
                       </c:forEach>
                    </form:select>
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
                    <form:input  path="code" type="text" class="form-control" id="productCode"/>
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

                <div class="form-group"><label class="control-label" for="imageSmallFile">
                    Ảnh hiện thị </label>
                    <form:input path="imageSmallFile" type="file"  id="imageSmallFile"/>
                </div>

                <div class="form-group"><label class="control-label" for="imageLargeFile">
                    Ảnh hiện thị chi tiết sản phẩm: </label>
                    <form:input path="imageLargeFile" type="file" id="imageLargeFile"/>
                </div>

                <c:if test="${result.mesStringByKey['imageError'] != null}">
                    <div class="form-group" id="imageError">
                        <p class="error">
                                ${result.mesStringByKey['imageError']}
                        </p>
                    </div>
                </c:if>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Thêm sản phẩm</button>
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
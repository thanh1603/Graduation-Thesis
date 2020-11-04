<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="template/header.jsp"/>

<div class="container " style="margin-top: 25px;">

</div>

<div class="container">
  <div class="row">
      <c:if test="${result.products == null || result.products.size() eq 0}">
          <div class="col-xs-12">Không có sản phẩm nào</div>
      </c:if>
      <c:forEach items="${result.products}" var="product">
          <div class="col-xs-3">
              <jsp:include page="template/product_view_2.jsp">
                  <jsp:param name="index" value="1"/>
                  <jsp:param name="catalogCode" value="${product.catalogCode}"/>
                  <jsp:param name="code" value="${product.code}"/>
                  <jsp:param name="name" value="${product.name}"/>
                  <jsp:param name="price" value="${product.price}"/>
                  <jsp:param name="discount" value="${product.discount}"/>
              </jsp:include>
          </div>
      </c:forEach>
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
    var textSearch = "${textSearch}";
    var viewFlag = "${viewFlag}";

    $(document).ready(function (event) {
        paging(pargigInfo.pageCurrent,pargigInfo.total ,
            pargigInfo.maxShow, pargigInfo.maxPage, onViewPage);
    });

    function onViewPage(page) {
        if(viewFlag === "1") {
            window.location = "${pageContext.request.contextPath}/product/search?page=" + page
                    + "&textSearch="+textSearch;
        }else{
            window.location = "${pageContext.request.contextPath}/${catalogCode}/product?page=" + page;
        }
    }
</script>


<jsp:include page="template/partner.jsp"/>
<jsp:include page="template/footer.jsp"/>
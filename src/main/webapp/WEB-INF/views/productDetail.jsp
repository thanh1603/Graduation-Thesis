<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="template/header.jsp"/>


<div class="container" >
    <div class="row">
        <div class="clearfix"></div>
        <div id="content" class="col-sm-12 col-xs-12 col-md-12 " style="margin-top: 50px;">
            <div class="details-product">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-5">
                        <div class="large-image"><a
                                href="<c:url value='/product/imageURL/${product.catalogCode}/${product.code}/large'/>"
                                data-rel="prettyPhoto[product-gallery]">
                                <img  id="zoom_01"
                                      src="<c:url value='/product/imageURL/${product.catalogCode}/${product.code}/large'/>"
                                      alt="${product.name}"> </a>
                            <div class="hidden"></div>
                        </div>
                        <div id="gallery_01" class="owl-carousel owl-theme thumbnail-product" data-md-items="4"
                             data-sm-items="4" data-xs-items="4" data-xss-items="2" data-margin="10"
                             data-nav="true">
                            <div class="item"><a class="clearfix" href="#"
                                                 data-image="/product/imageURL/${product.catalogCode}/${product.code}/large">
                                <img src="${pageContext.request.contextPath}/product/imageURL/${product.catalogCode}/${product.code}/small"
                                     alt="${product.name}"/> </a></div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-7 details-pro"><h1 class="title-head" itemprop="name">
                        ${product.name}</h1>
                        <div class="reviews clearfix">
                            <div class="f-left margin-right-10">
                                <div class="zozoweb-product-reviews-badge">
                                    <div class="zozoweb-product-reviews-star" style="color: rgb(255, 190, 0);"><i
                                            data-alt="1" class="star-off-png"></i>&nbsp; <i data-alt="2"
                                                                                            class="star-off-png"></i>&nbsp;
                                        <i data-alt="3" class="star-off-png"></i>&nbsp; <i data-alt="4"
                                                                                           class="star-off-png"></i>&nbsp;
                                        <i data-alt="5" class="star-off-png"></i>&nbsp;
                                    </div>
                                </div>
                            </div> <!--<div class="f-left iddanhgia" onclick="scrollToxx();">-->
                            <div class="f-left iddanhgia"
                                 onclick="$('a[href=\'#tab-review\']').trigger('click'); return false;"><span>Viết đánh giá</span>
                            </div>
                        </div>
                        <div class="status clearfix">
                            <div class="clearfix"> Mã sản phẩm: <strong itemprop="model">${product.code}</strong></div>
                            <div class="clearfix"> Tình trạng: <span class="inventory">${product.amount > 0 ? 'Còn trong kho' : 'Hết hàng'}</span></div>
                        </div>
                        <div class="price-box clearfix">
                            <div class="special-price"><span class="price product-price"> <fmt:formatNumber
                                    type="number"
                                    maxFractionDigits="3"
                                    value="${(product.price - (product.price * product.discount)/100)}"/>đ</span></div>
                            <span itemprop="offers" itemscope itemtype="http://schema.org/Offer"> <meta
                                    itemprop="price" content="${product.price - (product.price * product.discount)/100}"/> <meta itemprop="priceCurrency"
                                                                              content="VND"/> </span>
                            <ul class="list-unstyled"></ul>
                        </div>
                        <div class="product-summary product_description margin-bottom-15"></div>
                        <div id="product" class="form-product">
                            <div class="margin-bottom-10 dqdt-form form">
                                <div class="box-variant clearfix "></div>
                                <form:form method="post" modelAttribute="cartForm" action="${pageContext.request.contextPath}/cart/update">
                                    <div class="form-group form-groupx form-detail-action clearfix"><label
                                            class="f-left" for="input-quantity">Số lượng</label>
                                        <div class="custom custom-btn-number"><span class="qtyminus"
                                                                                    data-field="quantity" <c:if test="${product.amount != 0}">onclick="minusAmount()"</c:if>>-</span>
                                            <form:input path="amount" type="text" class="input-text qty"
                                                   title="Số lượng" maxlength="12" id="input-quantity"
                                                   value="1"
                                                   onkeypress="if ( isNaN(this.value + String.fromCharCode(event.keyCode) )) return false;"
                                                   onchange="if(this.value == '')this.value=1;"/>
                                                        <span <c:if test="${product.amount != 0}">onclick="plusAmount()"</c:if> class="qtyplus"
                                                                                                        data-field="quantity">+</span>
                                            <form:hidden path="catalogCode" value="${product.catalogCode}"/>
                                            <form:hidden path="productCode" value="${product.code}"/>

                                        </div>
                                        <button type="submit"  <c:if test="${product.amount == 0}">disabled="disabled"</c:if>
                                                class="btn btn-lg btn-primary btn-cart btn-cart2 add_to_cart btn_buy add_to_cart"
                                                type="button" id="button-cart" data-loading-text="Đang tải..."><span>Thêm vào giỏ <i
                                                class="fa .fa-caret-right"></i></span></button>
                                    </div>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function minusAmount() {
        var amount = Number($("#input-quantity").val());
        if(!amount) {
            amount = 1;
        }
        if(amount > 1) {
            amount = amount - 1;
            $("#input-quantity").val(amount)
        }
    }
    function plusAmount() {
        var amount = Number($("#input-quantity").val());
        if(!amount) {
            amount = 1;
        }else{
            amount = amount + 1;
        }
        $("#input-quantity").val(amount)
    }
</script>
<jsp:include page="template/partner.jsp"/>
<jsp:include page="template/footer.jsp"/>

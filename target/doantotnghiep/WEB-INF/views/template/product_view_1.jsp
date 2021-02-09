<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="owl-item ${param.index == 0 ? 'active' :''}"
     style="width: 262.5px; margin-right: 30px;">
    <div class="item">
        <div class="product-box"
             itemscope=""
             itemtype="/Product">
            <div class="product-thumbnail flexbox-grid">
                <a href="${pageContext.request.contextPath}/product/${param.code}"
                   title="Kiwi xanh">
                    <img
                            src="<c:url value='/product/imageURL/${param.catalogCode}/${param.code}/small'/>"
                            alt="${param.name}"
                            itemprop="image">
                </a>
                <div class="product-action hidden-md hidden-sm hidden-xs clearfix">
                    <form class="form variants form-nut-grid margin-bottom-0">
                        <div>
                            <a class="btn-buy btn-cart btn btn-primary left-to add_to_cart"
                               data-toggle="tooltip"
                               title=""
                               href="${pageContext.request.contextPath}/card/add/${param.code}&amp;redirect=true"
                               data-original-title="Mua ngay">
                                <i class="fa fa-shopping-bag"></i>
                            </a> <a
                                href="${pageContext.request.contextPath}/product/${param.code}"
                                data-toggle="tooltip"
                                title=""
                                class="btn-gray btn_view btn right-to"
                                data-original-title="Xem"><i
                                class="fa fa-eye"></i></a>
                        </div>
                    </form>
                </div>
            </div>
            <div class="product-info a-center">
                <h3 class="product-name">
                    <a
                            href="${pageContext.request.contextPath}/product/${param.code}"
                            title="${param.name}"
                            itemprop="url">
                        <span itemprop="name">${param.name}</span>
                    </a></h3>
                <div class="price-box clearfix"
                     itemprop="offers"
                     itemscope=""
                     itemtype="/Offer">
    <div class="special-price">
        <span class="price product-price">
            <fmt:formatNumber
                    type="number"
                    maxFractionDigits="3"
                    value="${param.price}"/>đ
        </span>
                    </div>
                    <meta itemprop="price"
                          content="${param.price}">
                </div>
            </div>
        </div>
    </div>
</div>

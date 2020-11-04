<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="template/header.jsp"/>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<section class="awe-section-1" id="awe-section-1">
    <div class="section_category_slider mt-md-5">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-xs-12 col-md-9 col-md-push-3 px-md-4 px-0 mb-5">
                    <script type="text/javascript"> $('.header .header-content .header_services').append($('#header_services-0')); </script>
                    <div class="home-slider owl-carousel owl-loaded owl-drag" id="gallery-0" data-margin="0"
                         data-nav="true" data-lg-items="1" data-md-items="1" data-sm-items="1" data-xs-items="1">
                        <div class="owl-stage-outer">
                            <div class="owl-stage"
                                 style="transform: translate3d(0px, 0px, 0px); transition: all 0s ease 0s; width: 1695px;">
                                <div class="owl-item active" style="width: 847.5px;">
                                    <div class="item"><a href="#" class="clearfix"> <img
                                            src="<c:url value='/resources/images/banner/banner 1-1360x577.png'/>"
                                            alt="Banner #1"> </a></div>
                                </div>
                                <div class="owl-item" style="width: 847.5px;">
                                    <div class="item"><a href="#" class="clearfix"> <img
                                            src="<c:url value='/resources/images/banner/banner 2-1360x577.png'/>"
                                            alt="Banner #2"> </a></div>
                                </div>
                            </div>
                        </div>
                        <div class="owl-nav">
                            <button type="button" role="presentation" class="owl-prev disabled"><i
                                    class="fa fa-angle-left"></i></button>
                            <button type="button" role="presentation" class="owl-next"><i class="fa fa-angle-right"></i>
                            </button>
                        </div>
                        <div class="owl-dots disabled"></div>
                    </div>
                </div>
                <div class="col-sm-12 col-xs-12 col-md-3 sidebar col-left clearfix col-md-pull-9">
                    <aside id="column-left" class="left-column compliance aside-vetical-menu">
                        <aside class="blog-aside aside-item sidebar-category module_category category_custom"
                               id="category_custom-1">
                            <div class="aside-title text-center text-xl-left"><h2 class="title-head"><span>Danh mục sản phẩm</span>
                            </h2></div>
                            <div class="aside-content">
                                <div class="nav-category navbar-toggleable-md">
                                    <ul class="nav navbar-pills">
                                        <c:forEach items="${catalogList}" var="catalog">
                                            <li class="nav-item"><i class="fa fa-arrow-circle-right"
                                                                    aria-hidden="true"></i>
                                                <a href="${pageContext.request.contextPath}/${catalog.code}/product"
                                                   class="nav-link">
                                                        ${catalog.name}
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </aside>
                    </aside>
                </div>
            </div>
        </div>
    </div>
</section>
<div class="container-fluid">
    <div class="row">
        <div id="content" class="main">
            <section class="awe-section-3 module_category category_custom" id="category_custom-2">
                <div class="section section-collection section-collection-1">
                    <div class="container">
                        <div class="collection-border">
                            <div class="collection-main">
                                <div class="row ">
                                    <div class="col-lg-12 col-sm-12">
                                        <div class="e-tabs not-dqtab ajax-tab-1" data-section="ajax-tab-1">
                                            <div class="row row-noGutter">
                                                <div class="col-sm-12">
                                                    <div class="content">
                                                        <div class="section-title"><h2>Trái cây nhập
                                                            khẩu</h2></div>
                                                        <div>
                                                            <ul class="tabs tabs-title ajax clearfix">
                                                                <c:if test="${catalogSize >= 1}">
                                                                    <li class="tab-link has-content active current"
                                                                        data-tab="${catalogList.get(0).code}"
                                                                        href="#${catalogList.get(0).code}"
                                                                        data-toggle="tab">
                                                                        <span>${catalogList.get(0).name}</span>
                                                                    </li>
                                                                </c:if>

                                                                <c:if test="${catalogSize >= 2}">
                                                                    <li class="tab-link has-content "
                                                                        data-tab="${catalogList.get(1).code}"
                                                                        href="#${catalogList.get(1).code}"
                                                                        data-toggle="tab">
                                                                        <span>${catalogList.get(1).name}</span>
                                                                    </li>
                                                                </c:if>

                                                                <c:if test="${catalogSize >= 3}">
                                                                    <li class="tab-link has-content  "
                                                                        data-tab="${catalogList.get(2).code}"
                                                                        href="#${catalogList.get(2).code}"
                                                                        data-toggle="tab">
                                                                        <span>${catalogList.get(2).name}</span>
                                                                    </li>
                                                                </c:if>
                                                            </ul>
                                                            <%----%>
                                                            <c:if test="${catalogSize >= 1}">
                                                                <div class="category_custom20 tab-content active current"
                                                                     id="${catalogList.get(0).code}">
                                                                    <div class="products products-view-grid owl-carousel owl-loaded owl-drag"
                                                                         data-nav="true" data-lgg-items="4"
                                                                         data-lg-items="4" data-md-items="4"
                                                                         data-sm-items="3" data-xs-items="2"
                                                                         data-xss-items="2">
                                                                        <div class="owl-stage-outer">
                                                                            <div class="owl-stage"
                                                                                 style="transform: translate3d(0px, 0px, 0px); transition: all 0s ease 0s; width: 2340px;">
                                                                                <c:forEach
                                                                                        items="${catalogList.get(0).products}"
                                                                                        var="product" varStatus="status">
                                                                                    <jsp:include page="template/product_view_2.jsp">
                                                                                        <jsp:param name="index" value="${status.index}"/>
                                                                                        <jsp:param name="catalogCode" value="${product.catalogCode}"/>
                                                                                        <jsp:param name="code" value="${product.code}"/>
                                                                                        <jsp:param name="name" value="${product.name}"/>
                                                                                        <jsp:param name="price" value="${product.price}"/>
                                                                                        <jsp:param name="discount" value="${product.discount}"/>
                                                                                    </jsp:include>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                        <div class="owl-nav">
                                                                            <button type="button" role="presentation"
                                                                                    class="owl-prev disabled"><i
                                                                                    class="fa fa-angle-left"></i>
                                                                            </button>
                                                                            <button type="button" role="presentation"
                                                                                    class="owl-next"><i
                                                                                    class="fa fa-angle-right"></i>
                                                                            </button>
                                                                        </div>
                                                                        <div class="owl-dots disabled"></div>
                                                                    </div>
                                                                </div>
                                                            </c:if>
                                                            <%----%>
                                                            <c:if test="${catalogSize >= 2}">
                                                                <div class="category_custom21 tab-content "
                                                                     id="${catalogList.get(1).code}">
                                                                    <div class="products products-view-grid owl-carousel owl-loaded owl-drag"
                                                                         data-nav="true" data-lgg-items="4"
                                                                         data-lg-items="4" data-md-items="4"
                                                                         data-sm-items="3" data-xs-items="2"
                                                                         data-xss-items="2">
                                                                        <div class="owl-stage-outer">
                                                                            <div class="owl-stage"
                                                                                 style="transform: translate3d(0px, 0px, 0px); transition: all 0s ease 0s; width: 2340px;">
                                                                                <c:forEach
                                                                                        items="${catalogList.get(1).products}"
                                                                                        var="product" varStatus="status">
                                                                                    <jsp:include page="template/product_view_2.jsp">
                                                                                        <jsp:param name="index" value="${status.index}"/>
                                                                                        <jsp:param name="catalogCode" value="${product.catalogCode}"/>
                                                                                        <jsp:param name="code" value="${product.code}"/>
                                                                                        <jsp:param name="name" value="${product.name}"/>
                                                                                        <jsp:param name="price" value="${product.price}"/>
                                                                                        <jsp:param name="discount" value="${product.discount}"/>
                                                                                    </jsp:include>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                        <div class="owl-nav">
                                                                            <button type="button" role="presentation"
                                                                                    class="owl-prev disabled"><i
                                                                                    class="fa fa-angle-left"></i>
                                                                            </button>
                                                                            <button type="button" role="presentation"
                                                                                    class="owl-next"><i
                                                                                    class="fa fa-angle-right"></i>
                                                                            </button>
                                                                        </div>
                                                                        <div class="owl-dots disabled"></div>
                                                                    </div>
                                                                </div>
                                                            </c:if>
                                                            <%----%>
                                                            <c:if test="${catalogSize >= 3}">
                                                                <div class="category_custom22 tab-content "
                                                                     id="${catalogList.get(2).code}">
                                                                    <div class="products products-view-grid owl-carousel owl-loaded owl-drag"
                                                                         data-nav="true" data-lgg-items="4"
                                                                         data-lg-items="4" data-md-items="4"
                                                                         data-sm-items="3" data-xs-items="2"
                                                                         data-xss-items="2">
                                                                        <div class="owl-stage-outer">
                                                                            <div class="owl-stage"
                                                                                 style="transform: translate3d(0px, 0px, 0px); transition: all 0s ease 0s; width: 2340px;">
                                                                                <c:forEach
                                                                                        items="${catalogList.get(2).products}"
                                                                                        var="product" varStatus="status">
                                                                                    <jsp:include page="template/product_view_2.jsp">
                                                                                        <jsp:param name="index" value="${status.index}"/>
                                                                                        <jsp:param name="catalogCode" value="${product.catalogCode}"/>
                                                                                        <jsp:param name="code" value="${product.code}"/>
                                                                                        <jsp:param name="name" value="${product.name}"/>
                                                                                        <jsp:param name="price" value="${product.price}"/>
                                                                                        <jsp:param name="discount" value="${product.discount}"/>
                                                                                    </jsp:include>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                        <div class="owl-nav">
                                                                            <button type="button" role="presentation"
                                                                                    class="owl-prev disabled"><i
                                                                                    class="fa fa-angle-left"></i>
                                                                            </button>
                                                                            <button type="button" role="presentation"
                                                                                    class="owl-next"><i
                                                                                    class="fa fa-angle-right"></i>
                                                                            </button>
                                                                        </div>
                                                                        <div class="owl-dots disabled"></div>
                                                                    </div>
                                                                </div>
                                                            </c:if>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <%--Block SP 2--%>

            <section class="awe-section-3 module_category category_custom" id="category_custom-3">
                <div class="section section-collection section-collection-1">
                    <div class="container">
                        <div class="collection-border">
                            <div class="collection-main">
                                <div class="row ">
                                    <div class="col-lg-12 col-sm-12">
                                        <div class="e-tabs not-dqtab ajax-tab-1" data-section="ajax-tab-1">
                                            <div class="row row-noGutter">
                                                <div class="col-sm-12">
                                                    <div class="content">
                                                        <div class="section-title"><h2>Thực phẩm sạch</h2></div>
                                                        <div>
                                                            <ul class="tabs tabs-title ajax clearfix">
                                                                <c:if test="${catalogSize >= 4}">
                                                                    <li class="tab-link has-content active current"
                                                                        data-tab="${catalogList.get(3).code}"
                                                                        href="#${catalogList.get(3).code}"
                                                                        data-toggle="tab">
                                                                        <span>${catalogList.get(3).name}</span>
                                                                    </li>
                                                                </c:if>

                                                                <c:if test="${catalogSize >= 5}">
                                                                    <li class="tab-link has-content "
                                                                        data-tab="${catalogList.get(4).code}"
                                                                        href="#${catalogList.get(4).code}"
                                                                        data-toggle="tab">
                                                                        <span>${catalogList.get(4).name}</span>
                                                                    </li>
                                                                </c:if>

                                                                <c:if test="${catalogSize >= 6}">
                                                                    <li class="tab-link has-content  "
                                                                        data-tab="${catalogList.get(5).code}"
                                                                        href="#${catalogList.get(5).code}"
                                                                        data-toggle="tab">
                                                                        <span>${catalogList.get(5).name}</span>
                                                                    </li>
                                                                </c:if>
                                                            </ul>
                                                            <c:if test="${catalogSize >= 4}">
                                                                <div class="category_custom30 tab-content active current"
                                                                     id="${catalogList.get(3).code}">
                                                                    <div class="products products-view-grid owl-carousel owl-loaded owl-drag"
                                                                         data-nav="true" data-lgg-items="4"
                                                                         data-lg-items="4" data-md-items="4"
                                                                         data-sm-items="3" data-xs-items="2"
                                                                         data-xss-items="2">
                                                                        <div class="owl-stage-outer">
                                                                            <div class="owl-stage"
                                                                                 style="transform: translate3d(0px, 0px, 0px); transition: all 0s ease 0s; width: 2340px;">
                                                                                <c:forEach
                                                                                        items="${catalogList.get(3).products}"
                                                                                        var="product" varStatus="status">
                                                                                    <jsp:include page="template/product_view_2.jsp">
                                                                                        <jsp:param name="index" value="${status.index}"/>
                                                                                        <jsp:param name="catalogCode" value="${product.catalogCode}"/>
                                                                                        <jsp:param name="code" value="${product.code}"/>
                                                                                        <jsp:param name="name" value="${product.name}"/>
                                                                                        <jsp:param name="price" value="${product.price}"/>
                                                                                        <jsp:param name="discount" value="${product.discount}"/>
                                                                                    </jsp:include>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                        <div class="owl-nav">
                                                                            <button type="button" role="presentation"
                                                                                    class="owl-prev disabled"><i
                                                                                    class="fa fa-angle-left"></i></button>
                                                                            <button type="button" role="presentation"
                                                                                    class="owl-next"><i
                                                                                    class="fa fa-angle-right"></i></button>
                                                                        </div>
                                                                        <div class="owl-dots disabled"></div>
                                                                    </div>
                                                                </div>
                                                            </c:if>
                                                            <c:if test="${catalogSize >= 5}">
                                                                <div class="category_custom31 tab-content "
                                                                     id="${catalogList.get(4).code}">
                                                                    <div class="products products-view-grid owl-carousel owl-loaded owl-drag"
                                                                         data-nav="true" data-lgg-items="4"
                                                                         data-lg-items="4" data-md-items="4"
                                                                         data-sm-items="3" data-xs-items="2"
                                                                         data-xss-items="2">
                                                                        <div class="owl-stage-outer">
                                                                            <div class="owl-stage"
                                                                                 style="transform: translate3d(0px, 0px, 0px); transition: all 0s ease 0s; width: 2340px;">
                                                                                <c:forEach
                                                                                        items="${catalogList.get(4).products}"
                                                                                        var="product" varStatus="status">
                                                                                    <jsp:include page="template/product_view_2.jsp">
                                                                                        <jsp:param name="index" value="${status.index}"/>
                                                                                        <jsp:param name="catalogCode" value="${product.catalogCode}"/>
                                                                                        <jsp:param name="code" value="${product.code}"/>
                                                                                        <jsp:param name="name" value="${product.name}"/>
                                                                                        <jsp:param name="price" value="${product.price}"/>
                                                                                        <jsp:param name="discount" value="${product.discount}"/>
                                                                                    </jsp:include>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                        <div class="owl-nav">
                                                                            <button type="button" role="presentation"
                                                                                    class="owl-prev disabled"><i
                                                                                    class="fa fa-angle-left"></i></button>
                                                                            <button type="button" role="presentation"
                                                                                    class="owl-next"><i
                                                                                    class="fa fa-angle-right"></i></button>
                                                                        </div>
                                                                        <div class="owl-dots disabled"></div>
                                                                    </div>
                                                                </div>
                                                            </c:if>

                                                            <c:if test="${catalogSize >= 6}">
                                                                <div class="category_custom32 tab-content "
                                                                     id="${catalogList.get(5).code}">
                                                                    <div class="products products-view-grid owl-carousel owl-loaded owl-drag"
                                                                         data-nav="true" data-lgg-items="4"
                                                                         data-lg-items="4" data-md-items="4"
                                                                         data-sm-items="3" data-xs-items="2"
                                                                         data-xss-items="2">
                                                                        <div class="owl-stage-outer">
                                                                            <div class="owl-stage"
                                                                                 style="transform: translate3d(0px, 0px, 0px); transition: all 0s ease 0s; width: 2340px;">
                                                                                <c:forEach
                                                                                        items="${catalogList.get(5).products}"
                                                                                        var="product" varStatus="status">
                                                                                    <jsp:include page="template/product_view_2.jsp">
                                                                                        <jsp:param name="index" value="${status.index}"/>
                                                                                        <jsp:param name="catalogCode" value="${product.catalogCode}"/>
                                                                                        <jsp:param name="code" value="${product.code}"/>
                                                                                        <jsp:param name="name" value="${product.name}"/>
                                                                                        <jsp:param name="price" value="${product.price}"/>
                                                                                        <jsp:param name="discount" value="${product.discount}"/>
                                                                                    </jsp:include>
                                                                                </c:forEach>
                                                                            </div>
                                                                        </div>
                                                                        <div class="owl-nav">
                                                                            <button type="button" role="presentation"
                                                                                    class="owl-prev disabled"><i
                                                                                    class="fa fa-angle-left"></i></button>
                                                                            <button type="button" role="presentation"
                                                                                    class="owl-next"><i
                                                                                    class="fa fa-angle-right"></i></button>
                                                                        </div>
                                                                        <div class="owl-dots disabled"></div>
                                                                    </div>
                                                                </div>
                                                            </c:if>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section class="awe-section-2 " id="service_gallery-2">
                <div class="section_banner">
                    <div class="container"><h2 class="hidden"></h2>
                        <div class="banner-slider owl-carousel owl-loaded owl-drag" data-nav="true" data-lg-items="2"
                             data-md-items="2" data-sm-items="2" data-xs-items="2">
                            <div class="owl-stage-outer">
                                <div class="owl-stage"
                                     style="transform: translate3d(0px, 0px, 0px); transition: all 0s ease 0s; width: 1170px;">
                                    <div class="owl-item active" style="width: 555px; margin-right: 30px;">
                                        <div class="item"><a href="javascript:void(0)" class="clearfix"> <img
                                                src="./Freshfood - Hệ thống cửa hàng thực phẩm sạch VietGAP_files/bn1-0x0.png"
                                                alt=""> </a></div>
                                    </div>
                                    <div class="owl-item active" style="width: 555px; margin-right: 30px;">
                                        <div class="item"><a href="javascript:void(0)" class="clearfix"> <img
                                                src="./Freshfood - Hệ thống cửa hàng thực phẩm sạch VietGAP_files/bn2-0x0.jpg"
                                                alt=""> </a></div>
                                    </div>
                                </div>
                            </div>
                            <div class="owl-nav disabled">
                                <button type="button" role="presentation" class="owl-prev disabled"><i
                                        class="fa fa-angle-left"></i></button>
                                <button type="button" role="presentation" class="owl-next disabled"><i
                                        class="fa fa-angle-right"></i></button>
                            </div>
                            <div class="owl-dots disabled"></div>
                        </div>
                    </div>
                </div>
            </section>
            <%--infomation--%>
<%--             <jsp:include page="template/infomation.jsp"/> --%>
            <%--feeback--%>
            <%--<jsp:include page="template/feedback.jsp"/>--%>
            <%--partner--%>
            <jsp:include page="template/partner.jsp"/>
        </div>
    </div>
</div>

<jsp:include page="template/footer.jsp"/>
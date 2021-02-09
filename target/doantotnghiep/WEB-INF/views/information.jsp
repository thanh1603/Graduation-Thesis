<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="template/header.jsp"/>

<div class="container " style="margin-top: 25px;">
    <div class="row">
        <div class="col-xs-12">
            <ul class="breadcrumb" itemscope="" itemtype="http://schema.org/BreadcrumbList">
                <li itemprop="itemListElement" itemscope="" itemtype="http://schema.org/ListItem"><a itemprop="item"
                                                                                                     href="http://ggffg.myzozo.net/">
                    <span itemprop="name"><i class="fa fa-home"></i></span> </a> <span><i class="fa fa-angle-right"></i></span>
                    <meta itemprop="position" content="1">
                </li>
                <li itemprop="itemListElement" itemscope="" itemtype="http://schema.org/ListItem"><a itemprop="item"
                                                                                                     href="http://ggffg.myzozo.net/gioi-thieu">
                    <strong itemprop="name">Giới thiệu</strong> </a>
                    <meta itemprop="position" content="2">
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-xs-12">
                noi dung Tin tuc
        </div>
    </div>
</div>

<jsp:include page="template/partner.jsp"/>
<jsp:include page="template/footer.jsp"/>
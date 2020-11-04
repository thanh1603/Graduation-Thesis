<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <title>Khoa Luan</title>
    <%--<link rel="stylesheet" type="text/css"--%>
          <%--href="<c:url value='/resources/css/stylesheet.min.css'/>" >--%>
    <link href="<c:url value="/resources/vendor/fonts/circular-std/style.css"/>" rel="stylesheet">

    <link rel="stylesheet" href="<c:url value="/resources/libs/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/vendor/bootstrap/css/bootstrap.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/vendor/fonts/fontawesome/css/fontawesome-all.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/vendor/charts/chartist-bundle/chartist.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/vendor/charts/morris-bundle/morris.css"/>">
    <link rel="stylesheet"
          href="<c:url value="/resources/vendor/fonts/material-design-iconic-font/css/materialdesignicons.min.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/vendor/charts/c3charts/c3.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/resources/vendor/fonts/flag-icon-css/flag-icon.min.css"/>"/>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/css/mycss.css'/>" >
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/vendor/datatables/datatables.min.css"/>">
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/resources/vendor/jquery-ui-1.12.1/jquery-ui.min.css'/>">

    <script src="<c:url value="/resources/vendor/jquery/jquery-3.3.1.min.js"/>"></script>
    <script type="text/javascript"  src="<c:url value='/resources/vendor/jquery-ui-1.12.1/jquery-ui.min.js'/>"></script>
    <!-- bootstap bundle js -->
    <script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.js"/>"></script>
    <!-- slimscroll js -->
    <script src="<c:url value="/resources/vendor/slimscroll/jquery.slimscroll.js"/>"></script>
    <!-- main js -->

    <script type="text/javascript" src="<c:url value="/resources/vendor/datatables/datatables.min.js"/>"></script>
    <script src="<c:url value="/resources/libs/js/main-js.js"/>"></script>
    <script src="<c:url value="/resources/js/parging.js"/>"></script>
    <script src="<c:url value="/resources/js/common.js"/>"></script>
    <script type="text/javascript">
        var pargigInfo = {};
        pargigInfo.maxPage = ${pagingData.maxPage};
        pargigInfo.maxShow = ${pagingData.maxItemView};
        pargigInfo.pageCurrent = ${pagingData.pageCurrent};
        pargigInfo.total = ${pagingData.total};
    </script>
</head>
<body>
<div id="dialog"></div>
<!-- ============================================================== -->
<!-- main wrapper -->
<!-- ============================================================== -->
<div class="dashboard-main-wrapper">
    <!-- ============================================================== -->
    <!-- navbar -->
    <!-- ============================================================== -->
    <div class="dashboard-header">
        <nav class="navbar navbar-expand-lg bg-white fixed-top">
            <a class="navbar-brand" href="#">Khoa Luan</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse " id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto navbar-right-top">

                    <li class="nav-item dropdown nav-user">
                        <a class="nav-link nav-user-img" href="#" id="navbarDropdownMenuLink2" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"><img
                                src="<c:url value="/resources/images/avatar.jpg"/>" alt=""
                                class="user-avatar-md rounded-circle"></a>
                        <div class="dropdown-menu dropdown-menu-right nav-user-dropdown"
                             aria-labelledby="navbarDropdownMenuLink2">
                            <div class="nav-user-info">
                                <h5 class="mb-0 text-white nav-user-name">${requestScope.user.name}</h5>
                                <span class="status"></span><span class="ml-2">Available</span>
                            </div>
                            <%--<a class="dropdown-item" href="#"><i class="fas fa-user mr-2"></i>Account</a>--%>
                            <%--<a class="dropdown-item" href="#"><i class="fas fa-cog mr-2"></i>Setting</a>--%>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout"><i class="fas fa-power-off mr-2"></i>Logout</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
    <!-- ============================================================== -->
    <!-- end navbar -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- left sidebar -->
    <!-- ============================================================== -->
    <div class="nav-left-sidebar sidebar-dark">
        <div class="menu-list">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="d-xl-none d-lg-none" href="#">Dashboard</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav flex-column">
                        <li class="nav-divider">
                            Menu
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false"
                               data-target="#submenu-1" aria-controls="submenu-2"><i class="fa fa-fw fa-rocket"></i>Dashboard</a>
                            <div id="submenu-1" class="collapse submenu show" style="">
                                <ul class="nav flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/catalog">Danh muc san pham</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/product">Danh sach san pham</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/#">Danh sach tai khoan</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/#">Danh sach bai viet</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#" data-toggle="collapse" aria-expanded="false"
                               data-target="#submenu-2" aria-controls="submenu-2"><i class="fa fa-fw fa-rocket"></i>Thong
                                ke bao cao</a>
                            <div id="submenu-2" class="collapse submenu" style="">
                                <ul class="nav flex-column">
                                    <li class="nav-item">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/orders">Danh sach don hang</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/admin/#">Thong ke bao cao</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<footer class="footer" style="margin-top: 20px;">
    <div class="content">
        <div class="site-footer">
            <div class="footer-inner padding-top-35 pb-lg-5">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12 col-sm-6 col-lg-5">
                            <div class="footer-widget"><h3 class="hastog"><span>Liên hệ</span></h3>
                                <ul class="list-menu list-showroom">
                                    <li class="clearfix"><i class="block_icon fa fa-map-marker"></i>
                                        <p>Ngõ 54 Triều Khúc, Q. Thanh Xuân, Hà Nội</p></li>
                                    <li class="clearfix"><i class="block_icon fa fa-phone"></i> <a
                                            href="tel:0123456789">0123456789</a></li>
                                    <li class="clearfix"><i class="block_icon fa fa-envelope"></i> <a
                                            href="mailto:abc@gmail.com">abc@gmail.com</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-lg-2">
                            <div class="footer-widget"><h3 class="hastog"><span>Thông tin</span></h3>
                                <ul class="list-menu list-blogs">
                                    <li><a href="#">Giới thiệu</a></li>
                                    <li><a href="#">Thông tin vận chuyển</a>
                                    </li>
                                    <li><a href="#">Quy định và Điều
                                        khoản</a></li>
                                    <li><a href="#">Chính sách bảo mật</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-lg-2">
                            <div class="footer-widget"></div>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-lg-3">
                            <div class="footer-widget"><h3 class="margin-bottom-20 hastog"><span>Facebook</span></h3>
                                <div class="list-menu">
                                    <div class="footerText">
                                        <div class="fb-page fb_iframe_widget"
                                             data-href="https://www.facebook.com/sinhtu99"
                                             data-small-header="false" data-adapt-container-width="true"
                                             data-hide-cover="false" data-show-facepile="false"
                                             fb-xfbml-state="rendered"
                                             fb-iframe-plugin-query="adapt_container_width=true&amp;app_id=829732533863539&amp;container_width=262&amp;hide_cover=false&amp;href=https%3A%2F%2Fwww.facebook.com%2FFacebookVietNam&amp;locale=vi_VN&amp;sdk=joey&amp;show_facepile=false&amp;small_header=false">
                                            <span style="vertical-align: bottom; width: 262px; height: 130px;"><iframe
                                                    name="fad6e53b9c90a4" width="1000px" height="1000px" frameborder="0"
                                                    allowtransparency="true" allowfullscreen="true" scrolling="no"
                                                    allow="encrypted-media" title="fb:page Facebook Social Plugin"
                                                    src="./Freshfood - Hệ thống cửa hàng thực phẩm sạch VietGAP_files/page.html"
                                                    style="border: none; visibility: visible; width: 262px; height: 130px;"
                                                    class=""></iframe></span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="copyright clearfix">
                <div class="container">
                    <div class="inner clearfix">
                        <div class="row">
                            <div class="col-md-6 text-center text-lg-left"><span>© Copyright 2020 TSTHANH. Thiết kế bởi <a
                                    target="_blank"
                                    href="#">TRẦN SINH THÀNH</a></span>
                            </div>
                            <div class="col-md-6 text-center text-lg-right hidden-xs">
                                <ul class="list-menu-footer"></ul>
                            </div>
                        </div>
                    </div>
                    <div class="back-to-top show"><i class="fa fa-angle-up"></i></div>
                    <a href="tel:0123456789" class="suntory-alo-phone bottom-left suntory-alo-green"
                       id="suntory-alo-phoneIcon">
                        <div class="suntory-alo-ph-img-circle"><i class="fa fa-phone"></i></div>
                    </a></div>
            </div>
        </div>
    </div>
</footer>
<script type="text/javascript"
        src="<c:url value='/resources/js/zozo-main.min.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/resources/css/social_login_button.css'/>">
<!-- Facebook Login --> <!-- Google Login --> <!-- Facebook script -->
<%--<div id="fb-root" class=" fb_reset">--%>
    <%--<div style="position: absolute; top: -10000px; width: 0px; height: 0px;">--%>
        <%--<div></div>--%>
        <%--<div>--%>
            <%--<iframe name="fb_xdm_frame_https" frameborder="0" allowtransparency="true" allowfullscreen="true"--%>
                    <%--scrolling="no" allow="encrypted-media" id="fb_xdm_frame_https" aria-hidden="true"--%>
                    <%--title="Facebook Cross Domain Communication Frame" tabindex="-1"--%>
                    <%--src="./Freshfood - Hệ thống cửa hàng thực phẩm sạch VietGAP_files/d_vbiawPdxB.html"--%>
                    <%--style="border: none;"></iframe>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<script type="text/javascript"> (function (d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s);
    js.id = id;
    js.src = "//connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.10&appId=829732533863539";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk')); </script> <!-- Show Popup Cart -->
</body>
</html>
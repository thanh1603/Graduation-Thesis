<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="template/header.jsp"/>

<div class="container" style="margin-top: 20px; ">
    <div class="row">
        <div class="clearfix"></div>
        <div id="content" class="col-sm-12 col-xs-12 col-md-12">
            <div class="row">
                <form method="post" action="" name="checkout_form" id="checkout_form" enctype="multipart/form-data"
                      class="form-horizontal">

                    <div class="col-sm-8">
                        <div class="panel panel-default">
                            <div class="panel-heading"><h3 class="panel-title"><i class="fa fa-info-circle"
                                                                                  aria-hidden="true"></i> Địa chỉ
                                nhận hàng </h3></div>
                            <div class="panel-body"> <!-- Apply for VN -->
                                <div class="form-group required"><label class="control-label col-md-2"
                                                                        for="input-firstname">Tên đầy đủ</label>
                                    <div class="col-sm-10"><input type="text" name="firstname" id="input-firstname"
                                                                  value="" placeholder="Ví dụ: Nguyễn Văn A"
                                                                  class="form-control"/></div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group required"><label class="control-label col-sm-4"
                                                                                for="input-email">Email</label>
                                            <div class="col-sm-8"><input type="email" name="email" id="input-email"
                                                                         value=""
                                                                         placeholder="contact@yourdomain.com"
                                                                         class="form-control"/></div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group required"><label class="control-label col-sm-4"
                                                                                for="input-telephone">Điện
                                            thoại</label>
                                            <div class="col-sm-8"><input type="text" name="telephone"
                                                                         id="input-telephone" value=""
                                                                         placeholder="Ví dụ: 01234567890"
                                                                         class="form-control"/></div>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group"><label class="control-label col-md-2"
                                                               id="label-zone">Lời nhắn</label>
                                    <div class="col-sm-10"><textarea name="comment" id="input-comment" rows="3"
                                                                     class="form-control"
                                                                     placeholder="Ví dụ: Chuyển hàng ngoài giờ hành chính"></textarea>
                                    </div>
                                </div>
                                <hr/>
                        </div>
                    </div>
                    </div>
                    <div class="col-md-4">
                        <div class="panel panel-default">
                            <div class="panel-heading"><h3 class="panel-title"><i class="fa fa-truck"
                                                                                  aria-hidden="true"></i> Vận chuyển
                            </h3></div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="col-sm-12"><span id="ajax-load-shipping-method"> <div><strong>Phí giao hàng tận nơi</strong></div> <div
                                            class="radio"> <label> <input type="radio" name="shipping_method"
                                                                          onclick="updateFee()" value="geo.geo"
                                                                          checked/> Phí giao hàng tận nơi - 0đ </label> </div> </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default" id="ajax-load-total">
                            <div class="panel-body">
                                <table class="adr-oms table">
                                    <tbody class="orderSummary">
                                    <tr class="row-total-amount">
                                        <td class="text-left">Thành tiền</td>
                                        <td class="text-right"><strong class="">119,000đ</strong></td>
                                    </tr>
                                    <tr class="row-total-amount">
                                        <td class="text-left">Phí giao hàng tận nơi</td>
                                        <td class="text-right"><strong class="">0đ</strong></td>
                                    </tr>
                                    <tr class="row-total-amount">
                                        <td class="text-left">Tổng số</td>
                                        <td class="text-right"><strong class="text-danger">119,000đ</strong></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="text-center">
                            <a class="pull-left" href="${pageContext.request.contextPath}/cart"
                                                    title="Quay lại giỏ hàng"> <i class="fa fa-mail-reply-all"
                                                                                  aria-hidden="true"></i> Quay lại
                            giỏ hàng </a>
                            <button class="btn btn-primary pull-right" type="button" id="submit_form_button"
                                    onclick="$('form#checkout_form').submit();">Đặt hàng <i
                                    class="fa fa-angle-right"></i></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="clearfix"></div>
        <section class="awe-section-12 carousel_partner module_carousel " id="carousel-0">
        </section>
    </div>
</div>
<jsp:include page="template/partner.jsp"/>
<jsp:include page="template/footer.jsp"/>
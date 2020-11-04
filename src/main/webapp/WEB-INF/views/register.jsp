<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="template/header.jsp"/>
<style>
    .form-group .form-control {
        margin-bottom: 15px;
    }
</style>
<div class="container " style="margin-top: 25px;">
    <div class="row">
        <div class="col-xs-12">
            <form:form modelAttribute="userForm" action="${pageContext.request.contextPath}/register">
                <fieldset id="account">
                    <legend>Chi tiết tài khoản</legend>
                    <div class="form-group required"><label class="col-sm-2 control-label " for="input-firstname">Họ
                        &amp;
                        Tên đệm <span class="control-label-rq"></span></label>
                        <div class="col-sm-10">
                            <form:input path="fullName" type="text" name="fullName" value=""
                                        placeholder="Họ Và Tên đệm"
                                        id="input-firstname" class="form-control"/></div>
                    </div>

                    <c:if test="${result.mesStringByKey['fullNameError'] != null}">
                        <div class="form-group required"><label class="col-sm-2 control-label"></label></div>
                        <div class="form-group required">
                        <div class="col-sm-10">
                            <p class="error">
                                    ${result.mesStringByKey['fullNameError']}
                            </p>
                        </div>
                        </c:if>

                        <div class="form-group required"><label class="col-sm-2 control-label "
                                                                for="input-email">Email <span class="control-label-rq"></span></label>
                            <div class="col-sm-10">
                                <form:input type="email" path="email" name="email" value="" placeholder="Email"
                                            id="input-email" class="form-control"/></div>
                        </div>

                        <c:if test="${result.mesStringByKey['emailError'] != null}">
                            <div class="form-group required"><label class="col-sm-2 control-label"></label></div>
                            <div class="col-sm-10">
                                <p class="error">
                                        ${result.mesStringByKey['emailError']}
                                </p>
                            </div>
                        </c:if>

                            <div class="form-group required"><label class="col-sm-2 control-label"
                                                                    for="input-telephone">Điện
                                thoại <span class="control-label-rq"></span></label>
                                <div class="col-sm-10">
                                    <form:input path="phone" type="tel" name="telephone" value=""
                                                placeholder="Điện thoại"
                                                id="input-telephone" class="form-control"/></div>
                            </div>

                            <c:if test="${result.mesStringByKey['phoneError'] != null}">
                                <div class="form-group required"><label class="col-sm-2 control-label"></label></div>
                                <div class="col-sm-10">
                                    <p class="error">
                                            ${result.mesStringByKey['phoneError']}
                                    </p>
                                </div>
                            </c:if>

                            <div class="form-group required"><label class="col-sm-2 control-label "
                                                                    for="input-address-1">Địa
                                chỉ <span class="control-label-rq"></span></label>
                                <div class="col-sm-10">
                                    <form:input path="address" type="text" name="address_1" value=""
                                                placeholder="Địa chỉ"
                                                id="input-address-1" class="form-control"/></div>
                            </div>

                            <c:if test="${result.mesStringByKey['addressError'] != null}">
                                <div class="form-group required"><label class="col-sm-2 control-label"></label></div>
                                    <div class="col-sm-10">
                                        <p class="error">
                                                ${result.mesStringByKey['addressError']}
                                        </p>
                                     </div>
                            </c:if>
                </fieldset>
                <%--Dia chi--%>
                <%-- Mat khau--%>
                <fieldset style="margin-bottom: 15px;">
                    <legend>Mật khẩu</legend>
                    <div class="form-group required"><label class="col-sm-2 control-label" for="input-password">Mật
                        khẩu <span class="control-label-rq"></span></label>
                        <div class="col-sm-10">
                            <form:input path="passWord" type="password" name="password" value="" placeholder="Mật khẩu"
                                        id="input-password" class="form-control"/> </div>
                    </div>

                    <c:if test="${result.mesStringByKey['passWordError'] != null}">
                        <div class="form-group required"><label class="col-sm-2 control-label"></label></div>
                        <div class="col-sm-10">
                            <p class="error">
                                    ${result.mesStringByKey['passWordError']}
                            </p>
                         </div>
                    </c:if>

                    <div class="form-group required"><label class="col-sm-2 control-label" for="input-confirm">Mật khẩu
                        xác
                        nhận <span class="control-label-rq"></span></label>
                        <div class="col-sm-10">
                            <form:input path="rePassWord" type="password" name="confirm" value=""
                                        placeholder="Mật khẩu xác nhận" id="input-confirm"
                                        class="form-control"/></div>
                    </div>
                    <c:if test="${result.mesStringByKey['rePassWordError'] != null}">
                        <div class="form-group required"><label class="col-sm-2 control-label"></label></div>
                        <div class="col-sm-10">
                            <p class="error">
                                ${result.mesStringByKey['rePassWordError']}
                            </p>
                        </div>
                    </c:if>
                </fieldset>
                <%----%>
                <div class="buttons">
                    <input type="submit" value="Tiếp tục" class="btn btn-primary">
                </div>

            </form:form>
        </div>

    </div>
</div>
</div>


<jsp:include page="template/partner.jsp"/>
<jsp:include page="template/footer.jsp"/>

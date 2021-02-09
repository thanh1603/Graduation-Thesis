<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="template/header.jsp"/>

<div class="container " style="margin-top: 25px;">
    <div class="row">
        <div class="col-xs-12">
            <div class="well"><h2>Thông tin đăng nhập</h2>
                <c:if test="${not empty param['error']}">
                    <p class="error">
                        Thông tin đăng nhập không chính sác
                    </p>
                </c:if>
                <p class="error"></p>
                <form name="f" action="${pageContext.request.contextPath}/j_spring_security_check" method="POST">
                    <div class="form-group"><label class="control-label" for="input-email">Địa chỉ Email</label> <input
                            type="text" name="username" value="" placeholder="Địa chỉ Email" id="input-email"
                            class="form-control"></div>
                    <div class="form-group"><label class="control-label" for="input-password">Mật khẩu</label> <input
                            type="password" name="password" value="" placeholder="Mật khẩu" id="input-password"
                            class="form-control"> <a href="">Quên mật khẩu?</a>
                    </div>
                    <input type="submit" value="Đăng nhập" class="btn btn-primary"></form>
            </div>
        </div>
    </div>
</div>


<jsp:include page="template/partner.jsp"/>
<jsp:include page="template/footer.jsp"/>

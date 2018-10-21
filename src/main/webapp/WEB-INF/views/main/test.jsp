<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="custom" uri="/tags/custom-taglib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>
<c:set var="REQUEST_CONTEXT_PATH" value="${pageContext.request.contextPath}" />


<section id="container" class="main_bg">

    <a href="http://thecoke.tk:8080/main?p=jwE95sDWhHTjZEd0YF0VYBQJ5DjmBHqcljKEsbOe8Cz%2BSHYC9Kr7qYSgh2mbcCdBRHv2xthip1VdoAVsRoOQnN27Oz%2FVqqNfo5aJkRhMlDznAAaN1Ixh0A26nGTkXdEP">미션렛저로 이동(WEB)</a>
    <br />
    <a href="returm://main?type=main&p=jwE95sDWhHTjZEd0YF0VYBQJ5DjmBHqcljKEsbOe8Cz%2BSHYC9Kr7qYSgh2mbcCdBRHv2xthip1VdoAVsRoOQnN27Oz%2FVqqNfo5aJkRhMlDznAAaN1Ixh0A26nGTkXdEP">미션렛저로 이동(Android)</a>
    <br />
    <a href="intent://scan/#Intent;scheme=zxing;package=com.google.zxing.client.android;end"> Take a QR code </a>
</section>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="custom" uri="/tags/custom-taglib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/" %>
<%@ page trimDirectiveWhitespaces="true" %>
<c:set var="REQUEST_CONTEXT_PATH" value="${pageContext.request.contextPath}" />

<section id="popup_container2"><!-- 적립 완료시 팝업 -->

    <div class="popup_content">
        <img src="${REQUEST_CONTEXT_PATH}/resources/images/returm.png" alt="" />
        <h3>적립중입니다.<br>기다려주세요</h3>
        <p>${rmPoint}RM 전송중</p>
    </div>

</section>

<script type="text/javascript">

    document.addEventListener("DOMContentLoaded", function() {

        setTimeout(function() {
            location.replace('${linkUrl}');
        }, 3000);
    });

</script>
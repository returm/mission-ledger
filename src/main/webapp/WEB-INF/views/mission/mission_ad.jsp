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
    <article id="wrapper">
        <div class="sub_header">
            <figure><img src="${REQUEST_CONTEXT_PATH}/resources/images/sub5_contmain4.png" alt="" /></figure>
            <h2>가입/설치</h2>
        </div>
        <div class="pr_mission_wrap">
            <h2 class="sub_title">미션목록 > 광고미션</h2>
            <ul>
                <li><a href="#" id="igaworks"><span><img src="${REQUEST_CONTEXT_PATH}/resources/images/charge_img1.png"></span>광고미션1</a></li>
                <li><a href="#" id="tnkfactory"><span><img src="${REQUEST_CONTEXT_PATH}/resources/images/charge_img2.png"></span>광고미션2</a></li>
                <li><a href="#" id="appall"><span><img src="${REQUEST_CONTEXT_PATH}/resources/images/charge_img3.png"></span>광고미션3</a></li>
                <li><a href="#" id="appang"><span><img src="${REQUEST_CONTEXT_PATH}/resources/images/charge_img4.png"></span>광고미션4</a></li>
            </ul>
        </div>
    </article>
</section>

<script type="text/javascript">

    document.addEventListener("DOMContentLoaded", function() {

        <c:if test="${not empty message}">
            alert('${message}');
        </c:if>

        $(".pr_mission_wrap ul li a").on('click', function(e) {
            e.preventDefault();

            var address = '${address}';
            fnOpenAdMission($(this).attr("id"), address, '${parameter}');
        })
    });

</script>
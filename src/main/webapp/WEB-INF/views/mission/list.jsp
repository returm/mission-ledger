<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="custom" uri="/tags/custom-taglib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/" %>
<%@ page trimDirectiveWhitespaces="true" %>
<c:set var="REQUEST_CONTEXT_PATH" value="${pageContext.request.contextPath}" />

<!-- Messages -->
<c:if test="${not empty message}">
    <script type="text/javascript">
        alert('${message}');
        //location.href = "list";
    </script>
</c:if>

<section id="container" class="main_bg">
    <article id="wrapper">
        <div class="sub_header">
            <figure><img src="${REQUEST_CONTEXT_PATH}/resources/images/sub5_contmain4.png" alt="" /></figure>

            <h2>미션 목록</h2>
        </div>
        <div class="mission_list_wrap">
            <h2 class="sub_title">리텀미션</h2>
            <c:forEach var="item" items="${returmMissionList}" varStatus="status">
                <table class="mission_list2">
                    <tbody>
                    <tr data-index="${status.index}"
                        data-title="${item.title}"
                        data-contents="${item.contents}"
                        data-imagefullfilepath="${item.fileEntity.handleFileNm}"
                        data-linkurl="${item.linkUrl}"
                        data-missionSeq="${item.missionSeq}"
                        data-missiontypecd="${item.missionTypeCd}">
                        <td class="img_wrap">
                            <c:choose>
                                <c:when test="${not empty item.fileEntity.handleFileNm}">
                                    <img src="${REQUEST_CONTEXT_PATH}/file/download/MISSION_IMAGE/${item.fileEntity.handleFileNm }" alt=""/>
                                </c:when>
                                <c:otherwise>
                                    <img src="${REQUEST_CONTEXT_PATH}/resources/images/sample_img.jpg" alt=""/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="title">
                            <a href="#">
                                <h3>${item.title}</h3>
                                <%--<div class="list_subtitle">${item.contents}</div>--%>
                            </a>
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div class="line"></div>
            </c:forEach>
        </div>
        <div class="mission_cate_wrap">
            <h2 class="sub_title">미션채널</h2>
            <ul>
                <c:forEach items="${cmCodeList}" var="item" varStatus="status">
                    <c:if test="${'RT001' eq item.cdgrpCd}">
                        <c:if test="${item.codeCd ne '105'}">
                            <li><a href="#" onclick="link('${REQUEST_CONTEXT_PATH}/mission/list/${item.codeCd}'); return false;"><img src="${REQUEST_CONTEXT_PATH}/resources/images/mission_icon${item.codeCd}.png" alt=""><p>${item.codeNm}</p></a></li>
                        </c:if>
                    </c:if>
                    <c:set var="cmCodeInfo" value="${item }"/>
                </c:forEach>
            </ul>
        </div>
    </article>
</section>

<jsp:include page="popup_detail.jsp" flush="true" />

<script type="text/javascript">

    var context = {
        data: {}
    };

    document.addEventListener("DOMContentLoaded", function() {

        $('.mission_list2 tr').on('click', function(e) {

            context.data.title = $(this).data('title');
            context.data.contents = $(this).data('contents');
            context.data.imageFullFilePath = $(this).data('imagefullfilepath');
            context.data.linkUrl = $(this).data('linkurl');
            context.data.missionSeq = $(this).data('missionseq');
            context.data.missionTypeCd = $(this).data('missiontypecd');

            fnPopupRender(context.data);

            $('#popup_container').modal();
        });
    });

</script>
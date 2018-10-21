<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="custom" uri="/tags/custom-taglib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page trimDirectiveWhitespaces="true" %>
<c:set var="REQUEST_CONTEXT_PATH" value="${pageContext.request.contextPath}" />

<section id="container" class="main_bg">
    <article id="wrapper">
        <div class="sub_header">
            <figure><img src="${REQUEST_CONTEXT_PATH}/resources/images/sub5_contmain4.png" alt="" /></figure>
            <h1>언제나 어디서나 <bold>미션을 수행</bold>하고 리텀을 보상받으세요!<br>리텀을 보상으로 지급하고 <bold>미션을 등록</bold>하세요</h1>
            <input class="ledger_search" value="${address}" disabled><a href="#" onclick="fnOpenPopup(); return false;" class="ledger_search_go">내지갑주소</a>
        </div>
        <%--<div class="ledger_intro">--%>
            <%--<h1>미션렛저란--%>
                <%--?</h1>--%>
            <%--<p>--%>
                <%--- 스몰 비즈니스 운영자에게 <strong>꼭 필요한 작은 과제(Mission)</strong>를 등록하고, 리텀을 스테이킹 하는 방식으로 과제를 수행할 컨시어지를 모집하고 보상을 지급하는 블록체인 기반의 바운티 플랫폼입니다.<br><br>--%>

                <%--- 댓글이나 메시지, 경험기록 등 지금까지 아무 의미없이 소비되던 작은 행동들이 과제(Mission)의 형태로 변환됨으로써 <strong>가치가 유동화</strong>되어집니다.<br><br>--%>

                <%--- 보상의 크기는 자발적으로 결정하지만, 더 간단하고 쉬운 과제(Mission)일수록 <strong>더 자주 활발히 거래될 것이며,</strong> 더 많은 보상을 주는 과제에 구성원들이 집중하는 것은 매우 당연한 일입니다.--%>
            <%--</p>--%>
        <%--</div>--%>
        <div class="mission_list_wrap">
            <h2 class="sub_title">추천미션</h2>
            <table class="mission_list3">
                <tbody>
                <c:forEach var="item" items="${recomMissionList}">
                    <tr data-title="${item.title}"
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
                                <p>(${item.count}) 등록일 : ${item.insertDt} ~</p>
                                <%--<div class="list_subtitle">${item.contents}</div>--%>
                            </a>
                        </td>
                        <td class="point">${item.rewardPoint} RM</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="mission_list_wrap">
            <a href="${REQUEST_CONTEXT_PATH}/mission/list/110?p=${parameter}"><h2 class="sub_title">구매하기(더보기)</h2></a>
            <table class="mission_list2">
                <tbody>
                <c:forEach var="item" items="${newMissionList}">
                    <c:if test="${item.missionTypeCd eq '110'}">
                        <tr data-title="${item.title}"
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
                                    <p>(${item.count}) 등록일 : ${item.insertDt} ~</p>
                                        <%--<div class="list_subtitle">${item.contents}</div>--%>
                                </a>
                            </td>
                            <td class="point">${item.rewardPoint} RM</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="mission_list_wrap">
            <a href="${REQUEST_CONTEXT_PATH}/mission/list/160?p=${parameter}"><h2 class="sub_title">영상미션1(더보기)</h2></a>
            <table class="mission_list2">
                <tbody>
                <c:forEach var="item" items="${newMissionList}">
                    <c:if test="${item.missionTypeCd eq '160'}">
                        <tr data-title="${item.title}"
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
                                    <p>(${item.count}) 등록일 : ${item.insertDt} ~</p>
                                        <%--<div class="list_subtitle">${item.contents}</div>--%>
                                </a>
                            </td>
                            <td class="point">${item.rewardPoint} RM</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="mission_list_wrap">
            <a href="${REQUEST_CONTEXT_PATH}/mission/list/170?p=${parameter}"><h2 class="sub_title">구독미션1(더보기)</h2></a>
            <table class="mission_list2">
                <tbody>
                <c:forEach var="item" items="${newMissionList}">
                    <c:if test="${item.missionTypeCd eq '170'}">
                        <tr data-title="${item.title}"
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
                                    <p>(${item.count}) 등록일 : ${item.insertDt} ~</p>
                                        <%--<div class="list_subtitle">${item.contents}</div>--%>
                                </a>
                            </td>
                            <td class="point">${item.rewardPoint} RM</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="mission_list_wrap">
            <a href="${REQUEST_CONTEXT_PATH}/mission/list/180?p=${parameter}"><h2 class="sub_title">방문미션1(더보기)</h2></a>
            <table class="mission_list2">
                <tbody>
                <c:forEach var="item" items="${newMissionList}">
                    <c:if test="${item.missionTypeCd eq '180'}">
                        <tr data-title="${item.title}"
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
                                    <p>(${item.count}) 등록일 : ${item.insertDt} ~</p>
                                        <%--<div class="list_subtitle">${item.contents}</div>--%>
                                </a>
                            </td>
                            <td class="point">${item.rewardPoint} RM</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="mission_list_wrap">
            <a href="${REQUEST_CONTEXT_PATH}/mission/list/900?p=${parameter}"><h2 class="sub_title">기타미션1(더보기)</h2></a>
            <table class="mission_list2">
                <tbody>
                <c:forEach var="item" items="${newMissionList}">
                    <c:if test="${item.missionTypeCd eq '900'}">
                        <tr data-title="${item.title}"
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
                                    <p>(${item.count}) 등록일 : ${item.insertDt} ~</p>
                                        <%--<div class="list_subtitle">${item.contents}</div>--%>
                                </a>
                            </td>
                            <td class="point">${item.rewardPoint} RM</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="mission_list_wrap">
            <a href="${REQUEST_CONTEXT_PATH}/mission/list/140?p=${parameter}"><h2 class="sub_title">영상미션2(더보기)</h2></a>
            <table class="mission_list2">
                <tbody>
                <c:forEach var="item" items="${newMissionList}">
                    <c:if test="${item.missionTypeCd eq '140'}">
                        <tr data-title="${item.title}"
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
                                    <p>(${item.count}) 등록일 : ${item.insertDt} ~</p>
                                        <%--<div class="list_subtitle">${item.contents}</div>--%>
                                </a>
                            </td>
                            <td class="point">${item.rewardPoint} RM</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="mission_list_wrap">
            <a href="${REQUEST_CONTEXT_PATH}/mission/list/150?p=${parameter}"><h2 class="sub_title">구독미션2(더보기)</h2></a>
            <table class="mission_list2">
                <tbody>
                <c:forEach var="item" items="${newMissionList}">
                    <c:if test="${item.missionTypeCd eq '150'}">
                        <tr data-title="${item.title}"
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
                                    <p>(${item.count}) 등록일 : ${item.insertDt} ~</p>
                                        <%--<div class="list_subtitle">${item.contents}</div>--%>
                                </a>
                            </td>
                            <td class="point">${item.rewardPoint} RM</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="mission_list_wrap">
            <a href="${REQUEST_CONTEXT_PATH}/mission/list/130?p=${parameter}"><h2 class="sub_title">방문미션2(더보기)</h2></a>
            <table class="mission_list2">
                <tbody>
                <c:forEach var="item" items="${newMissionList}">
                    <c:if test="${item.missionTypeCd eq '130'}">
                        <tr data-title="${item.title}"
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
                                    <p>(${item.count}) 등록일 : ${item.insertDt} ~</p>
                                        <%--<div class="list_subtitle">${item.contents}</div>--%>
                                </a>
                            </td>
                            <td class="point">${item.rewardPoint} RM</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="mission_list_wrap">
            <a href="${REQUEST_CONTEXT_PATH}/mission/list/120?p=${parameter}"><h2 class="sub_title">기타미션2(더보기)</h2></a>
            <table class="mission_list2">
                <tbody>
                <c:forEach var="item" items="${newMissionList}">
                    <c:if test="${item.missionTypeCd eq '120'}">
                        <tr data-title="${item.title}"
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
                                    <p>(${item.count}) 등록일 : ${item.insertDt} ~</p>
                                        <%--<div class="list_subtitle">${item.contents}</div>--%>
                                </a>
                            </td>
                            <td class="point">${item.rewardPoint} RM</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </article>
</section>

<jsp:include page="../mission/popup_detail.jsp" flush="true" />

<section id="popup_container3"><!--앱이나 웹 시작시 팝업 -->
    <article class="popup_wrap">
        <h1 class="pop_title">지갑주소가 안보일때 해결법</h1>
        <a href="#" onclick="fnClosePopup(); return false;" class="close"><img src="${REQUEST_CONTEXT_PATH}/resources/images/close.png" alt=""/></a>
        <div class="popup_con">

            <div class="mission_info2">
                1. 미션렛저의 지갑주소 등록은 자동으로 처리됩니다.<br><br>
                2. 내 지갑주소가 등록되어 있지 않다면,<br><br>
                3. 우선 미션렛저 앱을 닫아주세요.<br><br>
                4. 리텀월렛(returm.io)로 이동합니다.<br><br>
                5. 지갑잠금을 해제하여 로그인하세요.<br><br>
                6. 잠금해제 후 채굴하기 버튼을 클릭하시고, 미션렛저 홈 버튼을 클릭하세요.<br><br>
                7. 미션렛저 사이트에 접속하셨다면, 미션목록에 들어가셔서  "가입/설치"미션중 하나를 선택하여 클릭하시면 앱이 자동으로 실행되면서 지갑주소가 등록되는 것을 확인하실 수 있습니다.
            </div>


        </div>
    </article>
</section>

<section id="popup_container4"><!--앱이나 웹 시작시 팝업 -->
    <article class="popup_wrap">
        <img style="max-width: 100%; max-height: 100%;" src="${REQUEST_CONTEXT_PATH}/resources/images/popup.jpg" />
        <a onclick="fnCloseEventPopup(); return false;" style="float: right; margin-top: 8px; margin-left: 8px" href="#">닫기</a>
        <a onclick="fnCloseEventCookiePopup(); return false;" style="float: left; margin-top: 8px; margin-right: 8px" href="#">오늘 하루 열지않기</a>
    </article>
</section>


<script type="text/javascript">

    var context = {
        data: {}
    };

    document.addEventListener("DOMContentLoaded", function() {

        <c:if test="${not empty message}">
            alert('${message}');
        </c:if>

        $('.mission_list_wrap tr').on('click', function(e) {

            context.data.title = $(this).data('title');
            context.data.contents = $(this).data('contents');
            context.data.imageFullFilePath = $(this).data('imagefullfilepath');
            context.data.linkUrl = $(this).data('linkurl');
            context.data.missionSeq = $(this).data('missionseq');
            context.data.missionTypeCd = $(this).data('missiontypecd');

            fnPopupRender(context.data);

            $('#popup_container').modal();
        });

        if(!localStorage.getItem('main:popup:intro')) {
            $('#popup_container3').show();
            localStorage.setItem('main:popup:intro',true);
        }

        console.log($.cookie('eventPopup'));
        if ($.cookie('eventPopup') == undefined) {
            $("#popup_container4").show();
        }

    });

    function fnClosePopup() {
        $('#popup_container3').hide();
    }

    function fnOpenPopup() {
        $('#popup_container3').show();
    }

    function fnCloseEventPopup() {
        $('#popup_container4').hide();
    }

    function fnCloseEventCookiePopup() {
        $.cookie('eventPopup', '1', { expires: 1, path : '/' });
        fnCloseEventPopup();
    }

</script>
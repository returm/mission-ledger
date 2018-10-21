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

<section id="popup_container">
    <article class="popup_wrap" class="modal"></article>
</section>

<script type="text/javascript">

    var detailPopupTemplate = null;
    var popupContext = {
        data: {}
    }

    document.addEventListener("DOMContentLoaded", function() {
        $(".popup_wrap").on('click', '.close', function(e) {
            $.modal.close();
        })

        fnPopupInit();
    });

    function fnPopupRender(data) {

        popupContext.data = data;

        var html = detailPopupTemplate(popupContext);
        $('.popup_wrap').empty();
        $('.popup_wrap').html(html);
    }

    function fnPopupInit() {

        Handlebars.registerHelper('ifNotEmpty', function(conditional, options) {
            if(conditional != undefined && conditional.length != 0 && conditional != null) {
                return options.fn(this);
            }else {
                return options.inverse(this);
            }
        });

        detailPopupTemplate = Handlebars.compile($('#detail-popup-template').html());
    }

    function openUrl(missionSeq, missionTypeCd, linkUrl) {

        var parameter = '${parameter}';
        if(!parameter) {
            alert("지갑주소를 찾을 수 없습니다. returm.io로 이동합니다.");
            fnOpen("http://returm.io");
            return;
        }

        if(missionTypeCd == '160' || missionTypeCd == '170' || missionTypeCd == '180' || missionTypeCd == '900') {

            try {
                if(fnIsIos()){
                    webkit.messageHandlers.openMission.postMessage('');
                }else {
                    window.app.openMission('returm://missionledger.net/front/rest/charge/transaction?p='+encodeURIComponent(parameter).replace(/[!\'()]/g, escape).replace(/\*/g, "%2A")+'&missionSeq='+missionSeq);
                }

            }catch(e){
                if(fnIsIos()){
                    alert("안드로이드 리텀앱에서만 사용가능합니다.");
                }else {
                    location.href = "intent://main?type=mission&missionTypeCd=" + missionTypeCd + "&id=" + encodeURIComponent(parameter).replace(/[!'()]/g, escape).replace(/\*/g, "%2A") + "#Intent;scheme=returm;package=io.returm.android;end";
                }
            }
        }else if(missionTypeCd == '105') {
            fnOpen(linkUrl);
        }else {
            fnOpen('${REQUEST_CONTEXT_PATH}/rest/charge/transaction?p='+encodeURIComponent(parameter).replace(/[!'()]/g, escape).replace(/\*/g, "%2A")+"&missionSeq="+missionSeq);
        }
    }

</script>

<script id="detail-popup-template" type="text/x-handlebars-template">
    {{#data}}
    <h1 class="pop_title">미션상세내용</h1>
    <a href="#" class="close"><img src="${REQUEST_CONTEXT_PATH}/resources/images/close.png" alt=""/></a>
    <div class="popup_con">
        <table class="popup_img_title">
            <tr>
                <td class="img_wrap">
                    {{#if imageFullFilePath}}
                    <img src="${REQUEST_CONTEXT_PATH}/file/download/MISSION_IMAGE/{{imageFullFilePath}}" alt=""/>
                    {{else}}
                    <img src="${REQUEST_CONTEXT_PATH}/resources/images/sample_img.jpg" alt=""/>
                    {{/if}}
                </td>
                <td class="title">
                    <a href="#">
                        <h3>{{title}}</h3>
                    </a>
                </td>
            </tr>
        </table>
        <div class="mission_info">
            ${missionComm.contents}
        </div>
        <div class="mission_info">
            {{{contents}}}
        </div>
        <a href="#" onclick="openUrl('{{missionSeq}}', '{{missionTypeCd}}', '{{linkUrl}}'); return false;" class="mission_doit">미션 수행하러 가기</a>
    </div>
    {{/data}}
</script>
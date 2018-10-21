<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="custom" uri="/tags/custom-taglib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags/" %>
<%@ page trimDirectiveWhitespaces="true" %>
<c:set var="REQUEST_CONTEXT_PATH" value="${pageContext.request.contextPath}" />

<section id="container" class="main_bg">
<article id="wrapper">
    <div class="sub_header">
        <figure><img src="${REQUEST_CONTEXT_PATH}/resources/images/sub5_contmain4.png" alt="" /></figure>
        <h2>${missionTypeCdNm}</h2>
    </div>
    <div class="mission_list_wrap">
        <h2 class="sub_title">미션목록 > ${missionTypeCdNm}</h2>
        <table class="mission_list2">
            <tbody></tbody>
        </table>
    </div>
    <a href="#" onclick="fnScrollTop(); return false;" class="top_btn">top</a>
</article>
</section>

<jsp:include page="popup_detail.jsp" flush="true" />

<script type="text/javascript">

    var missionTemplate = null;
    var context = {
        list: [],
        requestPage: 0,
        page: 1,
        pageSize: 15
    }

    document.addEventListener("DOMContentLoaded", function() {

        // 스크롤 이벤트
        $(window).scroll(function() {
            var scrollFactor = 300;

            if ($(window).scrollTop() + scrollFactor >= $(document).height() - $(window).height()) {
                var page = context.page + 1;
                if(context.requestPage != page){
                    var pageSize = context.pageSize;
                    fnLoadData(page,pageSize);
                }
            }
        });

        $('.mission_list_wrap .mission_list2 tbody').on('click', 'tr', function(e) {

            var index = $(this).data("index");
            context.list[index].imageFullFilePath = context.list[index].fileEntity.handleFileNm;
            fnPopupRender(context.list[index]);


            $('#popup_container').modal();

            // var openUrl = $(this).data('openurl');
            // fnOpen(openUrl);
        })

        fnInit();
        fnLoadData(context.page, context.pageSize);
    });

    function fnInit() {

        Handlebars.registerHelper('ifNotEmpty', function(conditional, options) {
            if(conditional != undefined && conditional.length != 0 && conditional != null) {
                return options.fn(this);
            }else {
                return options.inverse(this);
            }
        });

        missionTemplate = Handlebars.compile($('#mission-template').html());
    }

    function fnLoadData(page, pageSize) {

        console.log('test', page, pageSize);

        context.requestPage = page;

        $.ajax({
            type : "POST",
            url : '${REQUEST_CONTEXT_PATH}/mission/list/${missionTypeCd}',
            data : {
                page: page,
                pageSize: pageSize
            },
            dataType:"json",
            success : function(data) {
                if(data.rsltDescTp == '1000') {

                    if(data.list && data.list.length > 0) {
                        context.list = context.list.concat(data.list);
                    }

                    context.page = data.page.currentIndex;

                    fnRender();
                }
            },error : function(obj) {
                return;
            }
        });
    }

    function fnRender() {

        var html = missionTemplate(context);
        $('.mission_list_wrap .mission_list2 tbody').empty();
        $('.mission_list_wrap .mission_list2 tbody').html(html);
    }

</script>

<script id="mission-template" type="text/x-handlebars-template">
    {{#list}}
    <tr data-openurl="{{linkUrl}}" data-index="{{@index}}">
        <td class="img_wrap">
            {{#if fileEntity.handleFileNm}}
            <img src="${REQUEST_CONTEXT_PATH}/file/download/MISSION_IMAGE/{{fileEntity.handleFileNm}}" alt=""/>
            {{else}}
            <img src="${REQUEST_CONTEXT_PATH}/resources/images/sample_img.jpg" alt=""/>
            {{/if}}
        </td>
        <td class="title">
            <a href="#">
                <h3>{{title}}</h3>
                <p>({{count}}) 등록일 : {{insertDt}} ~</p>
                <%--<div class="list_subtitle">{{{contents}}}</div>--%>
            </a>
        </td>
        <td class="point">{{rewardPoint}} RM</td>
    </tr>
    {{/list}}
</script>
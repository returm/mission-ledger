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
            <h2>나의 미션</h2>
        </div>
        <div class="mission_list_wrap">
            <h2 class="sub_title">미션목록 > 나의미션</h2>
            <table class="my_mission_list">
                <tbody>
                </tbody>
            </table>
        </div>
        <a class="top_btn">top</a>
    </article>
</section>

<script type="text/javascript">

    var missionTemplate = null;
    var context = {
        list: [],
        requestPage: 0,
        page: 0,
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

        fnInit();
        fnLoadData();
    });

    function fnInit() {
        missionTemplate = Handlebars.compile($('#mission-template').html());
    }

    function fnLoadData(page, pageSize) {

        var insertId = '${address}';
        if(insertId) {
            context.requestPage = page;

            $.ajax({
                type : "POST",
                url : '${REQUEST_CONTEXT_PATH}/mission/mymission',
                data : {
                    page: page,
                    pageSize: pageSize,
                    walletAddrTo: insertId
                },
                dataType:"json",
                success : function(data) {
                    if(data.rsltDescTp == '1000') {

                        if(data.list && data.list.length > 0 && data.page.currentIndex > context.page
                            && data.page.currentIndex - context.page == 1) {
                            if(data.page.currentIndex == 1) {
                                context.list = [].concat(data.list);
                            }else {
                                context.list = context.list.concat(data.list);
                            }

                            context.page = data.page.currentIndex;
                        }

                        fnRender();
                    }
                },error : function(obj) {
                    alert("");
                    return;
                }
            });
        }
    }

    function fnRender() {

        var html = missionTemplate(context);
        $('.mission_list_wrap .my_mission_list tbody').empty();
        $('.mission_list_wrap .my_mission_list tbody').html(html);
    }

</script>

<script id="mission-template" type="text/x-handlebars-template">
    <tr>
        <th>일시</th>
        <th>내용</th>
        <th>수량</th>
        <th>상태</th>
    </tr>
    {{#list}}
    <tr>
        <td>{{insertDt}}</td>
        <td>{{contents}}</td>
        <td>{{point}}</td>
        <td>{{result}}</td>
    </tr>
    {{/list}}
</script>
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
            <figure><img src="${REQUEST_CONTEXT_PATH}/resources/images/sub5_contmain3.png" alt="" /></figure>
            <h2>미션등록하기</h2>
        </div>

            <div class="mission_entry_wrap">
                <table class="popup_img_title">
                    <tr>
                        <td class="img_wrap"><img src="${REQUEST_CONTEXT_PATH}/resources/images/sample_img.jpg" alt=""/></td>
                        <td class="title">
                            <style type="text/css">
                                input[type='file']{ display: none }
                            </style>
                            <input type='text' class='upload-name' id='upload-name' disabled />
                            <label for='upload' class='upload-hidden'>업로드</label>
                            <form id="fileUploadForm" name="fileUploadForm" action="${REQUEST_CONTEXT_PATH}/common/fileUpload">
                                <input type="hidden" name="fileCd" value="MISSION_IMAGE" />
                                <input type='file' id='upload' name='upload'/><br>
                            </form>
                            <a href="#" class="btn_imgup">이미지 업로드</a>
                            <%--<script>--%>
                                <%--/* IE 8 이하 버전에서는 document.getElementById 를 사용해주어야 한다. */--%>
                                <%--var uploadname = document.querySelector('#upload-name')--%>

                                <%--/* 이미지가 들어오면 발현 */--%>
                                <%--upload.addEventListener('change',function (e) {--%>
                                    <%--var files = e.target.files;--%>
                                    <%--uploadname.value = files[0].name--%>
                                <%--})--%>
                            <%--</script>--%>
                        </td>
                    </tr>
                </table>
                <form:form name="inputForm" method="post" action="form" modelAttribute="missionEntity" role="form">

                <input type="hidden" name="imageFileSq" class="required" id="imageFileSq" />

                <table class="mission_entry_table">
                    <tr>
                        <th>미션등록</th>
                        <td>
                            <form:select path="missionTypeCd" cssClass="mission_select required" id="missionTypeCd">
                                <option value="">선택하세요.</option>
                                <tags:cmcode code="RT001" items="${cmCodeList}">
                                    <option value="${cmCodeInfo.codeCd}">${cmCodeInfo.codeNm}</option>
                                </tags:cmcode>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <th>미션제목</th>
                        <td><form:input path="title" cssClass="input_mission_title required" id="title" placeholder="미션제목을 입력하세요." /></td>
                    </tr>
                    <tr>
                        <th>미션내용</th>
                        <td><form:textarea path="contents" cssClass="entry_textarea required" id="contents" placeholder="미션의 내용과 수행조건, 완료조건등을 입려하세요." /></td>
                    </tr>
                    <tr>
                        <th>미션링크</th>
                        <td><form:input path="linkUrl" cssClass="input_mission_link required" id="linkUrl" placeholder="미션을 수행할 수 있는 링크를 입력하세요." /></td>
                    </tr>
                    <tr>
                        <th>미션보상</th>
                        <td><form:input path="rewardPoint" cssClass="input_mission_point required" id="rewardPoint"  placeholder="보상으로 지급될 리텀의 개수를 입력하세요." /> RM</td>
                    </tr>
                    <tr>
                        <th>리텀예치</th>
                        <td><input class="input_mission_deposit" placeholder="예치할 리텀의 개수를 입력하세요."> RM <a href="#" class="btn_deposit">예치하기</a></td>
                    </tr>
                    <tr>
                        <th></th>
                        <td>
                            <a href="#" onclick="fnSubmit(); return false;" class="entry_btn">미션등록하기</a>
                        </td>
                    </tr>
                </table>
            </div>
        </form:form>
    </article>
</section>

<script type="text/javascript">

    document.addEventListener("DOMContentLoaded", function() {

        $('form[name=inputForm]').validate({
            onfocusout: false,
            onkeyup: false,
            onclick: false,
            onsubmit: false,
            showErrors: function(errorMap, errorList) {
                if (!$.isEmptyObject(errorList)) {
                    var error = errorList[0];
                    alert(error.message);
                    try {
                        error.element.focus();
                    } catch (e) {
                    }
                }
            },
            messages: {
                missionTypeCd: {
                    required: "타입을 선택해주세요"
                },
                title: {
                    required: "제목을 입력해주세요"
                },
                contents: {
                    required: "내용을 입력해주세요"
                },
                linkUrl: {
                    required: "링크를 입력해주세요"
                },
                rewardPoint: {
                    required: "보상포인트를 입력해주세요"
                },
                imageFileSq: {
                    required: "이미지를 등록해주세요"
                }
            }
        })

        $('input[name=upload]').change(function() {

            var obj={
                timeout : 120000 //30000
            };

            $("#fileUploadForm").ajaxForm({
                timeout: obj.timeout,
                type: 'POST',
                cache: false,
                dataType:"json",
                beforeSend: function(xmlHttpRequest) {
                    console.log("before");
                },
                complete: function(xhr, textStatus) {
                },
                success:function(data){
                    if(data.result == true){
                        $('.img_wrap img').attr('src', data.downloadUrl);
                        $('input[name=imageFileSq]').val(data.fileSq)
                    }
                },
                error:function(error){
                    console.log("error");
                }
            }).submit();
        });
    });

    function fnSubmit() {

        var f = $('form[name=inputForm]');
        if(f.valid()) {

            var imageFileSq = $('input[name=imageFileSq]').val();
            if(imageFileSq == null || imageFileSq == '' || imageFileSq == undefined) {
                alert("이미지를 등록해주세요");
                return;
            }

            f.submit();
        }
    }

</script>

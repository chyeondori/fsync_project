<%--
  Created by IntelliJ IDEA.
  User: JINSU
  Date: 2022-07-15
  Time: 오전 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false" %>
<%
  request.setCharacterEncoding("utf-8");
%>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <title>Special Test HTML</title>
  <!-- Favicon-->
  <link rel="icon" type="image/x-icon" href="../../../images/fsync/inside.ico" />
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="../../../css/egovframework/bootstrap.css" rel="stylesheet" />
  <%--    <link href="./../../resources/css/styles2.css" rel="stylesheet" />--%>
  <script>
    function closePopup() {
      //자신을 실행시킨 본 창은 opener 객체로 인식된다
      opener.$('#mask, #loadingImg').hide();
      opener.$('#mask, #loadingImg').remove();
      //value 초기화
      // opener.document.[Form 이름].[value이름].value = "";

      //마우스 커서 옮기기
      // opener.document.[Form 이름].[value이름].focus();

      //자신(팝업)을 종료한다.
      self.close();
    }
  </script>
</head>
<body onunload="closePopup()">
<!-- Page Content-->
<div class="container-fluid px-5">
  <!-- 해드 -->
  <jsp:include page="../header.jsp">
    <jsp:param name="title" value="에이전트 선택"/>
  </jsp:include>
  <!-- Content Row-->
  <!-- 프레임 -->
  <div class="px-5 py-3 my-5" style="border: 2px solid #dee2e6;">
    <!-- 테이블 -->
    <div class="table-responsive gx-4 gx-lg-5 align-items-center my-2">
      <table class="table table-bordered  table-hover align-middle">
        <thead class="text-center align-middle" style="height: 5em;">
        <tr>
          <th scope="col"></th>
          <th scope="col" class="col">에이전트 명</th>
          <th scope="col" class="col">대기 작업 수</th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <c:forEach var="list" items="${serverInfo}">
          <tr>
            <td class="text-center"><input class="form-check-input" type="radio" name="radio-agent" id="agent-01" value="${list.serverId}" checked></td>
            <td>${list.serverNm}</td>
            <td>${list.serverOrderCnt}</td>
          <tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <div class="jm-loading" style="display:none">
      <div class="lds-spinner"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div>
    </div>
    <!-- 버튼 -->
    <div class="row gx-4 gx-lg-5 align-items-center my-2 px-0">
      <div class="text-end">
        <a class="btn btn-primary col-2 py-2 ms-auto" onclick="startSync()">
          확인
        </a>
        <div class="btn btn-dark col-2 py-2" onclick="closePopup()">
          취소
        </div>
      </div>
    </div>
  </div>

</div>
<!-- Footer-->
<%--<jsp:include page="footer.jsp"/>--%>
<!-- Bootstrap core JS-->
<script src="../../../js/bootstrap.bundle.min.js"></script>
<script src="../../../js/jquery/jquery.js"></script>
<link rel="stylesheet" href="../../../js/jstree/themes/default/style.css" />
<script type="text/javascript">
  var params;
  $(document).ready(function() {
    // $(".jm-loading").show();

    params = JSON.parse($(opener.document).find("#jsonString").val());

  });

  function reload(){
    window.opener.location.reload();
    window.close();
  }

  function startSync() {

    var serverId = $("input[name=radio-agent]:checked").val();

    //오더 작업에 따른 url
    var url = '';

    if($(opener.document).find("#orderDiv").val() == 'veri'){
      url = 'sizeVerification.do';
    }
    else if($(opener.document).find("#orderDiv").val() == "initConfig"){ // 22.07.25 ldh 초기환경 구성 에이전트 선택 재사용
        url = 'initConfig.do';
    }
    else if($(opener.document).find("#orderDiv").val() == "sync"){
      url = 'insertSyncOrder.do';
    }
    else{
      url = 'insertUpdateSync.do';
    }

    var serverIdObj = new Object();
    var allSel = false;
    serverIdObj.serverId = serverId;
    $.each(params,function (index) {
      if (params[index].id == ""){
        params = params.slice(1,params.length);
      }
      params[index].serverId = serverId;
    })
    if (allSel){
    }
    // alert(JSON.stringify(params));

    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(params),
        contentType : "application/json",
        dataType: "json",
        async: true,
        success: function (result) {
            alert("요청에 성공하였습니다.");
            closePopup();
            reload();
        },
        error: function (data) {
            alert("오류가 발생했습니다.");
            reload();
        }
    });
  }

</script>
</body>
</html>

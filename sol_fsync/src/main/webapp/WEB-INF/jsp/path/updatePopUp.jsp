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
  <title>동기화 수정 팝업 창</title>
  <!-- Favicon-->
  <link rel="icon" type="image/x-icon" href="../../../images/fsync/inside.ico" />
  <!-- Core theme CSS (includes Bootstrap)-->
  <link href="../../../css/egovframework/bootstrap.css" rel="stylesheet" />
  <%--    <link href="./../../resources/css/styles2.css" rel="stylesheet" />--%>

  <script src="../../../js/jquery/jquery.min.js"></script>

  <script>
    //현재창 닫기
    function closePopup() {
      //자신(팝업)을 종료한다.
      self.close();
    }

    //부모창 닫기
    function reload(){
      // window.opener.document.location.href=window.opener.document.URL;
      // opener.location.reload(true);
      window.opener.location.reload();
      window.close();
    }

    //form 유효성 검사 및 수정 컨트롤러 호출
    function chkForm(){

      if(document.getElementById("srcpath").value===""){
        alert("원본 경로를 입력해주세요.")
        return false;
      }
      if(document.getElementById("despath").value===""){
        alert("대상 경로를 입력해주세요.")
        return false;
      }
      if(document.getElementById("logpath").value===""){
        alert("로그 경로를 입력해주세요.")
        return false;
      }
      var requestData =  $("#frm").serialize();


      $.ajax({
          type: "POST",
          url: "modifyPath.do",
          data: requestData,
          // contentType : "application/json",
        dataType: "text",
        success: function (res) {
          if(res == "success"){
            alert("정상적으로 수정 처리 요청 되었습니다.");
            reload();
          }
          else if (res === "duplicate"){
            alert("이미 등록된 동기화 경로입니다.");
            $("#srcpath").focus();
            $("#despath").focus();
          }
        },
        error: function (res) {
            if (res == "error"){
              alert("오류가 발생했습니다.");
            }
            reload();
          },
        });
    }

  </script>
</head>
<body onunload="closePopup()">
<!-- Page Content-->
<div class="container-fluid px-5">
  <!-- 해드 -->
  <jsp:include page="../header.jsp">
    <jsp:param name="title" value="동기화 경로 수정"/>
  </jsp:include>
  <!-- Content Row-->
  <!-- 프레임 -->
  <form  id="frm" method="post">
  <div class="px-5 py-3 my-5" style="border: 2px solid #dee2e6;">
    <div class="row my-5">
      <div class="col-4 fs-5 py-3 fw-bold">
        원본 경로 (Source)
      </div>
      <div class="col-8">
        <input name ="srcpath" id="srcpath" class="col-12 fs-5 px-3 py-3 rounded-3" value="${srcpath}"/>
      </div>
    </div>
    <div class="row my-5">
      <div class="col-4 fs-5 py-3 fw-bold">
        대상 경로 (Destination)
      </div>
      <div class="col-8">
        <input name="despath" id="despath" class="col-12 fs-5 px-3 py-3 rounded-3" value="${despath}"/>
      </div>
    </div>
    <div class="row my-5">
      <div class="col-4 fs-5 py-3 fw-bold">
        로그 경로 (Log)
      </div>
      <div class="col-8">
        <input name ="logpath" id="logpath" class="col-12 fs-5 px-3 py-3 rounded-3" value="${logpath}"/>
      </div>
    </div>
    <!-- 구분선 -->
    <hr class="border border-1 opacity-100" style="border: none; border-top: dotted;">

    <div class="row my-5">
      <div class="col-4 fs-5 py-3 fw-bold">
        설명
      </div>
      <div class="col-8">
        <input name="remark" id="remark" class="col-12 fs-5 px-3 py-3 rounded-3" value="${remark}" />
        <input type="hidden" name="id" id="id" value="${id}" />
      </div>
    </div>
    <!-- 버튼 -->
    <div class="row gx-4 gx-lg-5 align-items-center my-2 px-0">
      <div class="text-end">
        <div class="btn btn-primary col-2 py-2 ms-auto" onclick="chkForm()">
          확인
        </div>
        <div class="btn btn-dark col-2 py-2" onclick="closePopup()">
          취소
        </div>
      </div>
    </div>
  </div>
  </form>
</div>
<!-- Footer-->
<%--<jsp:include page="footer.jsp"/>--%>
<!-- Bootstrap core JS-->
<script src="../../../js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<%--<script src="./../../resources/js/scripts.js"></script>--%>
</body>
</html>

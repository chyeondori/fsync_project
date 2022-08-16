<%--
  Created by IntelliJ IDEA.
  User: ysy
  Date: 2022-07-28
  Time: 오전 9:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page session="false" %>
<%
    request.setCharacterEncoding("utf-8");
%>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>Special Test HTML</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="../../../images/fsync/inside.ico"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../../../css/egovframework/bootstrap.css" rel="stylesheet"/>
    <link href="../../../css/egovframework/style.css" rel="stylesheet"/>
</head>

<body onunload="closePopup()">
<!-- Page Content-->
<div class="container-fluid px-5">
    <!-- 헤더 -->
    <jsp:include page="../header.jsp">
        <jsp:param name="title" value="등록된 서버 수정"/>
    </jsp:include>

    <!-- Content Row-->
    <form id="sForm" method="post">
        <%-- <form id="sForm" action="/updateServer.do" method="post">--%>
        <div class="px-5 py-3 my-5" style="border: 2px solid #dee2e6;">
            <div class="row my-5">
                <div class="col-4 fs-5 py-3 fw-bold">
                    서버 ID
                </div>
                <div class="col-8">
                    <div style="font-size: 1.3em; font-weight: 500; text-align: left; padding: 12px">
                        ${serverId}
                    </div>
                </div>
            </div>

            <%--<div class="row my-5">
                <div class="col-4 fs-5 py-3 fw-bold">
                    서버 타입
                </div>
                <div class="col-8">
                    <select name="serverTy" id="serverTy" class="col-12 fs-5 px-3 py-3 rounded-3"
                            style="font-size: inherit;">
                        <option value="A" ${serverTy == 'Agent' ? 'selected="selected"' : '' }>Agent</option>
                        <option value="M" ${serverTy == 'Manager' ? 'selected="selected"' : '' }>Manager</option>
                    </select>
                </div>
            </div>--%>

            <div class="row my-5">
                <div class="col-4 fs-5 py-3 fw-bold">
                    에이전트 명
                </div>
                <div class="col-8">
                    <input name="serverNm" id="serverNm" class="col-12 fs-5 px-3 py-3 rounded-3" value="${serverNm}"/>
                </div>
            </div>

            <div class="row my-5">
                <div class="col-4 fs-5 py-3 fw-bold">
                    서버 IP
                </div>
                <div class="col-8">
                    <input name="serverIp" id="serverIp" class="col-12 fs-5 px-3 py-3 rounded-3" value="${serverIp}"/>
                </div>
            </div>

            <div class="row my-5">
                <div class="col-4 fs-5 py-3 fw-bold">
                    호스트 명
                </div>
                <div class="col-8">
                    <input name="hostNm" id="hostNm" class="col-12 fs-5 px-3 py-3 rounded-3" value="${hostNm}"/>
                </div>
            </div>

            <div class="row my-5">
                <div class="col-4 fs-5 py-3 fw-bold">
                    최대작업 수
                </div>
                <div class="col-8">
                    <input name="maxJobCnt" id="maxJobCnt" class="col-12 fs-5 px-3 py-3 rounded-3"
                           value="${maxJobCnt}"/>
                    <input type="hidden" name="serverId" id="serverId" value="${serverId}"/>
                </div>
            </div>

            <!-- 버튼 -->
            <div class="row gx-4 gx-lg-5 align-items-center my-2 px-0">
                <div class="text-end">
                    <div class="btn btn-primary col-2 py-2 ms-auto" onclick="checkValid()">
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

<script src="../../../js/jquery/jquery.min.js"></script>
<script src="../../../js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
    // 현재창 닫기
    function closePopup() {
        self.close();
    }

    // 부모창 닫기
    function reload() {
        window.opener.location.reload();
        window.close();
    }

    // 유효성 검사
    function checkValid() {

        const serverNm = document.getElementById("serverNm").value
        const serverIp = document.getElementById("serverIp").value
        const hostNm = document.getElementById("hostNm").value
        const maxJobCnt = document.getElementById("maxJobCnt").value

        const regServerNm = /^[\d|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
        const regServerIp = /^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$/;
        const regHostNm = /^[\d|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
        const regMaxJobCnt = /^\d*$/;

        if (serverNm === "") {
            alert("에이전트 명을 입력해주세요.");
            $("#serverNm").focus();
            return false;
        } else {
            if (!regServerNm.test(serverNm)) {
                alert("에이전트 명은 문자, 숫자로 작성해주세요.");
                $("#serverNm").focus();
                return;
            }
            console.log("에이전트 명 유효성 테스트" + regServerNm.test(serverNm));
        }

        if (serverIp === "") {
            alert("서버 IP를 입력해주세요.")
            $("#serverIp").focus();
            return false;
        } else {
            if (!regServerIp.test(serverIp)) {
                alert("서버 IP는 정해진 형식의 숫자로 작성해주세요.");
                $("#serverIp").focus();
                return;
            }
            console.log("서버IP 유효성 테스트" + regServerIp.test(serverIp));
        }

        if (hostNm === "") {
            alert("호스트 명을 입력해주세요.");
            $("#hostNm").focus();
            return false;
        } else {
            if (!regHostNm.test(hostNm)) {
                alert("호스트 명은 문자, 숫자로 작성해주세요.");
                $("#hostNm").focus();
                return;
            }
            console.log("호스트 명 유효성 테스트" + regHostNm.test(hostNm));
        }

        if (maxJobCnt === "") {
            alert("최대 작업수량을 입력해주세요.");
            $("#maxJobCnt").focus();
            return false;
        } else {
            if (!regMaxJobCnt.test(maxJobCnt)) {
                alert("최대 작업수량은 숫자로 작성해주세요.");
                $("#maxJobCnt").focus();
                return;
            }
            console.log("최대 작업수량 유효성 테스트" + regMaxJobCnt.test(maxJobCnt));
        }

        var requestData = $("#sForm").serialize();

        $.ajax({
            type: "POST",
            url: "updateServer.do",
            data: requestData,
            // contentType: "application/json",
            dataType: "text",
            success: function (resp) {
                if (resp === "success") {
                    alert("정상적으로 수정 처리 요청 되었습니다.");
                    reload();
                } else if (resp === "duplicateNm") {
                    alert("이미 존재하는 에이전트 명 입니다.");
                    $("#serverNm").focus();
                } else if (resp === "duplicateIp") {
                    alert("이미 존재하는 서버 IP 입니다.");
                    $("#serverIp").focus();
                } else if (resp === "duplicate") {
                    alert("이미 존재하는 에이전트 명과 IP 입니다.");
                    $("#serverNm").focus();
                }
            },
            error: function (resp) {
                if (resp == "error") {
                    alert("수정 작업 중 오류가 발생했습니다.");
                }
                reload();
            },
        });
    }
</script>
</body>
</html>

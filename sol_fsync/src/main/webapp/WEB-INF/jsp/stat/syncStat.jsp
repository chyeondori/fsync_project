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
</head>
<body>
<!-- Responsive navbar-->
<nav class="navbar navbar-expand-lg navbar-light bg-info bg-opacity-50">
    <div class="container px-5 fw-bold">
        <a class="navbar-brand" href="${ pageContext.request.contextPath }/serverList.do">파일 동기화 솔루션</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item border-end"><a class="nav-link" href="${ pageContext.request.contextPath }/serverList.do">서버 정보관리</a></li>
                <li class="nav-item border-end"><a class="nav-link" href="${ pageContext.request.contextPath }/syncPathSelect.do">동기화 경로 관리</a></li>
                <li class="nav-item border-end"><a class="nav-link" href="${ pageContext.request.contextPath }/fileSyncList.do">동기화 실행할 목록 선택</a></li>
                <li class="nav-item border-end"><a class="nav-link active" aria-current="page" href="${ pageContext.request.contextPath }/syncStat.do">동기화 현황 조회</a></li>
                <li class="nav-item"><a class="nav-link" href="${ pageContext.request.contextPath }/syncOrder.do">에이전트 작업 조회</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- Page Content-->
<div class="container-fluid">
    <!-- 해드 -->
    <jsp:include page="../header.jsp">
        <jsp:param name="title" value="동기화 현황 조회"/>
    </jsp:include>
    <!-- Content Row-->
    <!-- 디렉터리 셀렉트 박스 -->
    <div id="depthParentNode" class="row gx-2 align-items-center my-2 px-0 mx-0">
        디렉터리 :
        <div id="depth1" name="dir-depth-1" class="col-2">
            <label class="col-12">
                <select class="col-12" name="dir-depth-1" onchange="setDirDepth(this)">
                    <option name="dir-depth-1" value="all">전체</option>
                    <c:forEach var="topDir" items="${topDirVOList}">
                        <option name="dir-depth-1" value="<c:out value="${topDir.dirId}"/>"><c:out value="${topDir.srcTopDir}"/></option>
                    </c:forEach>
                </select>
            </label>
        </div>
        <div id="depth2" name="dir-depth-2" class="col-sm-2" style="display: none; white-space: nowrap">
            >&nbsp;
            <label class="col-11">
                <select class="col-12" name="dir-depth-2" onchange="setDirDepth(this)">
                    <option name="dir-depth-2" value="all" selected>전체</option>
                </select>
            </label>
        </div>
        <div id="depth3" name="dir-depth-3" class="col-2" style="display: none; white-space: nowrap">
            >&nbsp;
            <label class="col-11">
                <select class="col-12" name="dir-depth-3" onchange="setDirDepth(this)">
                    <option name="dir-depth-3" value="all" selected>전체</option>
                </select>
            </label>
        </div>
    </div>
    <!-- 테이블 -->
    <div class="table-responsive gx-4 gx-lg-5 align-items-center my-2">
        <table class="table table-bordered  table-hover align-middle">
            <thead class="text-center align-middle" style="height: 5em;">
            <tr>
                <th scope="col" colspan="3" class="col-4">원본</th>
                <th scope="col" colspan="3" class="col-4">대상</th>
                <th scope="col" rowspan="2" class="col-1">진행률</th>
                <th scope="col" rowspan="2" class="col-1">진행 상태</th>
                <th scope="col" rowspan="2" class="col-1">요청 일시</th>
            </tr>
            <tr>
                <th scope="col" class="col-3 ">동기화 목록</th>
                <th scope="col" class="col ">크기</th>
                <th scope="col" class="col ">수</th>
                <th scope="col" class="col-3 ">동기화 목록</th>
                <th scope="col" class="col ">크기</th>
                <th scope="col" class="col ">수</th>
            </tr>
            </thead>
            <tbody class="table-group-divider" id="dataList">
            <c:forEach var="syncStatus" items="${syncStatusVOList}">
                <tr>
                    <td><c:out value="${syncStatus.srcFDirNm}"/></td>
                    <td><c:out value="${syncStatus.totFileSize}"/></td>
                    <td><c:out value="${syncStatus.totFileCnt}"/></td>
                    <td><c:out value="${syncStatus.syncdDirNm}"/></td>
                    <td><c:out value="${syncStatus.syncdFileSize}"/></td>
                    <td><c:out value="${syncStatus.syncdFileCnt}"/></td>
                    <td><c:out value="${syncStatus.progressing}"/></td>
                    <td><c:out value="${syncStatus.procStat}"/></td>
                    <td><c:out value="${syncStatus.procDtm}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- Footer-->
<jsp:include page="../footer.jsp"/>
<!-- Bootstrap core JS-->
<script src="../../../js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<%--<script src="./../../resources/js/scripts.js"></script>--%>
<script src="../../../js/jquery/jquery.js"></script>
<script>
    // 상단 디렉터리 셀렉트 박스 설정
    function setDirDepth(target) {
        // const myDepthName = $('div[name='+target.name+']').attr("name");
        const dirId = target.options[target.selectedIndex].value;   // 선택된 디렉터리의 id값
        const divTarget = $('div[name='+target.name+']');   // 선택한 셀렉트 박스가 존재하는 div 태그
        // ajax - 선택된 디렉터리 id로 목록 조회
        $.ajax({
            type : "POST",
            url : "ajax/depthSelectEvent.do",
            data : { "dirId" : dirId },
            dataType: "json",
            success : function(res) {
                if(dirId === $("select[name="+target.name+"] option:first").val()) {
                    // 전체 옵션 선택시 하위 셀렉트 박스 숨김
                    divTarget.nextAll().css("display", "none");
                } else {
                    // 하위 뎁스 셀렉트 박스 추가
                    divTarget.next().css("display", "block");
                    divTarget.next().nextAll().css("display", "none");

                    let selectBox = $('div[name='+target.name+']').next().children().eq(0).children().eq(0)
                    setSelectBoxOption(selectBox, dirId, res);
                }
                const tbody = document.getElementById("dataList");
                const listSize = tbody.rows.length;
                // 목록 지우기
                for (let i=0; i<listSize; i++) {
                    tbody.deleteRow(-1);
                }
                // 데이터 리스트 추가
                for (let i=0; i<res.length; i++) {
                    const newRow = tbody.insertRow();
                    newRow.insertCell(0).textContent = res[i].srcFDirNm;
                    newRow.insertCell(1).textContent = res[i].totFileSize;
                    newRow.insertCell(2).textContent = res[i].totFileCnt;
                    newRow.insertCell(3).textContent = res[i].syncdDirNm;
                    newRow.insertCell(4).textContent = res[i].syncdFileSize;
                    newRow.insertCell(5).textContent = res[i].syncdFileCnt;
                    newRow.insertCell(6).textContent = res[i].progressing;
                    newRow.insertCell(7).textContent = res[i].procStat;
                    newRow.insertCell(8).textContent = res[i].procDtm;
                }
            },
            error : function() {
                alert("???");
            }
        });
    }

    // SelectBox Option에 "전체" 및 하위 디렉토리 목록 추가
    function setSelectBoxOption(selectBox, pDirId, SyncStatusVOList) {
        // 옵션 초기화
        selectBox.children('option').remove();

        // 기본 옵션 추가
        selectBox.append($("<option></option>").attr("value", pDirId).text("전체"));
        for (let i=0; i<SyncStatusVOList.length; i++) {
            if(!SyncStatusVOList[i].fileTypeCd) {
                selectBox.append($("<option></option>").attr("value", SyncStatusVOList[i].dirId).text(SyncStatusVOList[i].cdirNm));
            }
        }
    }
</script>
</body>
</html>

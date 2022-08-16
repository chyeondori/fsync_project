<%@ page import="fsync.business.path.vo.TopDirInfoVo" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page session="false" %>
<%
    request.setCharacterEncoding("utf-8");
    List<TopDirInfoVo> list = (List<TopDirInfoVo>) request.getAttribute("resultList");

%>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <%--    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />--%>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>동기화 경로 관리</title>
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="../../../images/fsync/inside.ico"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../../../css/egovframework/bootstrap.css" rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <%--    <script src="../../js/jquery/jquery.min.js"></script>--%>

    <%--    <link href="./../../resources/css/styles2.css" rel="stylesheet" />--%>
</head>
<body>
<!-- Responsive navbar-->
<nav class="navbar navbar-expand-lg navbar-light bg-info bg-opacity-50">
    <div class="container px-5 fw-bold">
        <a class="navbar-brand" href="${ pageContext.request.contextPath }/serverList.do"/>
        파일 동기화 솔루션
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item border-end">
                    <a class="nav-link" href="${ pageContext.request.contextPath }/serverList.do">서버 정보 관리</a>
                </li>
                <li class="nav-item border-end">
                    <a class="nav-link active" aria-current="page"
                       href="${ pageContext.request.contextPath }/syncPathSelect.do">
                        동기화 경로 관리</a>
                </li>
                <li class="nav-item border-end">
                    <a class="nav-link" href="${ pageContext.request.contextPath }/fileSyncList.do">
                        동기화 실행할 목록 선택</a>
                </li>
                <li class="nav-item border-end">
                    <a class="nav-link" href="${ pageContext.request.contextPath }/syncStat.do">동기화 현황 조회</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${ pageContext.request.contextPath }/syncOrder.do">
                        에이전트 작업 조회</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Page Content-->
<div class="container-fluid">
    <!-- 해드 -->
    <jsp:include page="../header.jsp">
        <jsp:param name="title" value="동기화 경로 관리"/>
    </jsp:include>
    <style>
        table {
            width: 100%;
            table-layout: fixed;
            table-layout: auto;
        }
    </style>
    <!-- Content Row-->
    <div class="row gx-4 gx-lg-5 align-items-between my-2 px-0 mx-0">
        <div class="col-3 align-items-start px-0">
            <a class="btn btn-primary" id="initsetting" onclick="initId(this.id)" style="float:left">
                초기환경 구성
            </a>
            <div class="btn btn-primary col-3" onclick=window.location.reload() style="float:left; margin-left:10px">
                새로고침
            </div>
        </div>

        <div class="col-3 offset-9 text-end px-0">
            <div class="btn btn-primary col-3" onclick="insertPopUpOpen()">
                등록
            </div>
            <a class="btn btn-primary col-3 text" onclick="updatePopUpOpen()">
                <span style="color:white">수정</span>
            </a>
            <a class="btn btn-danger col-3 ms-auto" id="delete" onclick="deleteId(this.id)">
                <span style="color:white">삭제</span>
            </a>
        </div>
    </div>

    <!-- 테이블 -->
    <div class="table-responsive gx-4 gx-lg-5 align-items-center my-2">
        <table class="table table-bordered  table-hover align-middle">
            <thead class="text-center align-middle" style="height: 5em;">
            <tr>
                <th scope="col" rowspan="2" class="col"><input type="checkbox" name="checkSelect"
                                                               onclick="selectAll(this)"/></th>
                <th scope="col" rowspan="2" class="col">ID</th>
                <th scope="col" colspan="3" class="col-2">원본</th>
                <th scope="col" colspan="3" class="col-2">대상</th>
                <th scope="col" rowspan="2" class="col-2">로그경로</th>
                <th scope="col" rowspan="2" class="col-1">초기환경<br/>구성상태</th>
                <th scope="col" rowspan="2" class="col-2">설명</th>
            </tr>
            <tr>
                <th scope="col" class="col-3 ">경로</th>
                <th scope="col" class="col-1 ">크기</th>
                <th scope="col" class="col ">수</th>
                <th scope="col" class="col-3 ">경로</th>
                <th scope="col" class="col-1 ">크기</th>
                <th scope="col" class="col ">수</th>
            </tr>
            </thead>
            <tbody class="table-group-divider">
            <c:forEach var="list" items="${resultList}">
                <tr>
                    <td class="text-center"><input type="checkbox" name="source" id="chk1" value="'+ typeId +'"
                                                   onclick="checkList()"/></td>
                    <td>
                            ${list.syncId}
                    </td>
                    <td>
                            ${list.srcTopDir}
                    </td>
                    <td>
                            ${list.srcTotFileSize}
                    </td>
                    <td>
                            ${list.srcTotFileCnt}
                    </td>
                    <td>
                            ${list.dstTopDir}
                    </td>
                    <td>
                            ${list.dstTotFileSize}
                    </td>
                    <td>
                            ${list.dstTotFileCnt}
                    </td>
                    <td>
                            ${list.logDir}
                    </td>
                    <td>
                            ${list.procStat}
                    </td>
                    <td>
                            ${list.remark}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form name="frm" data-ajax="false">
            <input type=hidden name="id"/>
            <input type=hidden name="srcpath"/>
            <input type=hidden name="despath"/>
            <input type=hidden name="logpath"/>
            <input type=hidden name="remark"/>
        </form>
        <input type="hidden" id="jsonString" value="">
        <input type="hidden" id="orderDiv" value="">
    </div>
</div>
<!-- Footer-->
<jsp:include page="../footer.jsp"/>
<!-- Bootstrap core JS-->
<script src="../../../js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<%--<script src="./../../resources/js/scripts.js"></script>--%>
<script type="text/javascript">

    // 하위 체크박스 체크 여부
    function checkList() {
        const checkboxes
            = document.querySelectorAll('input[name="source"]');
        const checked
            = document.querySelectorAll('input[name="source"]:checked');
        const selectAll
            = document.querySelector('input[name="checkSelect"]');

        if (checkboxes.length === checked.length) {
            selectAll.checked = true;
        } else {
            selectAll.checked = false;
        }
    }

    // 전체 체크박스
    function selectAll(selectAll) {
        const checkboxes = document.getElementsByName('source');

        checkboxes.forEach((checkbox) => {
            checkbox.checked = selectAll.checked;
        })
    }

    //등록 팝업 창 열기
    function insertPopUpOpen() {
        window.open("/insertPopUp.do", "popup1", "width=1200, height=800, top=150, left=200");
    }

    function reload() {
        window.document.location.href = window.document.URL;
    }

    //등록된 동기화 경로 삭제
    function deleteId(tagId) {
        let input = confirm("선택된 항목을 작업 삭제 요청하시겠습니까?\n계속하시려면 확인을 눌러주세요");
        if (input === true) {
            //체크박스 전체
            var checkbox = $("input:checkbox[name='source']")

            //체크 된 것이 없을 시
            if ((checkbox.filter(":checked").length) === 0) {
                alert('체크 된 항목이 없습니다.');

                return false;
            }

            //체크 된 것이 있을 시
            else {
                var checkbox = $("input:checkbox[name='source']:checked")
                var chkId = [];
                var checkstatus;

                checkbox.each(function (i) {
                    var tr = checkbox.parent().parent().eq(i);
                    var td = tr.children();

                    console.log(tr.text());
                    console.log(td.eq(i).text());
                    console.log(td.eq(1).text());
                    console.log(td.eq(9).text().trim());

                    chkId.push(td.eq(1).text());

                    if (td.eq(9).text().trim() != "미처리" && td.eq(9).text().trim() != "환경구성완료") {
                        alert("미처리, 환경구성완료 상태의 항목만 삭제 가능합니다.");
                        checkstatus = false;
                        return false;
                    }
                })

                if (checkstatus == false) {
                    return false;
                }

                var url = "delDir.do";
                var message = "정상적으로 삭제 요청 처리 되었습니다.";

                $.ajax({
                    type: 'post',   //
                    url: url,  //이동할 jsp 파일 주소
                    data: {chkid: chkId},
                    traditional: true,

                    dataType: "json",   //문자형식으로 받기
                    success: function (data) {
                        console.log(data);
                        alert(message);
                        reload();

                    },
                    error: function (request, status, error) {
                        alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                        reload();

                    }
                })
                // window.location.reload();
            }
        }
    }

    //초기 환경 구성 컨트롤러 호출
    function initId(tagId) {

        var checkbox = $("input:checkbox[name='source']")
        var checkstatus;
        if ((checkbox.filter(":checked").length) === 0) {
            alert('체크 된 항목이 없습니다.');
            return false;
        } else {
            var rowData = new Array();
            var tdArr = new Array();
            var checkbox = $("input:checkbox[name='source']:checked");

            // 체크된 체크박스 값을 가져온다
            checkbox.each(function (i) {

                // checkbox.parent() : checkbox의 부모는 <td>이다.
                // checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
                var tr = checkbox.parent().parent().eq(i);
                var td = tr.children();
                var data = new Object();

                // if (td.eq(9).text().trim() != "미처리") {
                if (td.eq(9).text().trim() != "미처리" && td.eq(9).text().trim() != "환경구성중단") {
                    alert("미처리 / 환경구성중단 상태일 경우만 초기환경구성이 가능합니다.");
                    checkstatus = false;
                    return false;
                }

                var id = td.eq(1).text().trim();
                var srcpath = td.eq(2).text().trim();
                var despath = td.eq(5).text().trim();
                var logpath = td.eq(8).text().trim();

                data.id = id;
                data.srcpath = srcpath;
                data.despath = despath;
                data.logpath = logpath;
                tdArr.push(data);

            });
            $('#jsonString').val(JSON.stringify(tdArr));
            $('#orderDiv').val("initConfig");

            if (checkstatus === false) {
                return false;
            }
            // alert(JSON.stringify(tdArr));
            window.open("${ pageContext.request.contextPath }/selectAgent.do", "popup2", "width=1200, height=800, top=150, left=200");
            //     var url = "initConfig.do";
            //     var message = "정상적으로 초기환경 구성 요청 처리되었습니다.";
            //
            //     $.ajax({
            //         type: 'post',   //
            //         url : "initConfig.do",  //이동할 jsp 파일 주소
            //         data: JSON.stringify(tdArr),
            //         traditional: true,
            //         dataType: "json",
            //         contentType: 'application/json',
            //         success: function (data) {
            //             console.log(data);
            //         },
            //         error:function(request,status,error){
            //             alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            //         }
            //     })
            // }

        }
    }

    //수정 팝업 창 열기
    function updatePopUpOpen() {

        var checkbox = $("input:checkbox[name='source']")

        if ((checkbox.filter(":checked").length) === 0) {
            alert('체크 된 항목이 없습니다.');

            return false;
        } else if ((checkbox.filter(":checked").length) >= 2) {
            alert('수정은 1개의 항목만 가능합니다.');

            return false;
        } else {

            var rowData = new Array();
            var checked = $("input[name='source']:checked");

            var id;
            var srcpath;
            var despath;
            var logpath;
            var remark;
            var checkedStatus;
            checked.each(function (i) {

                // checkbox.parent() : checkbox의 부모는 <td>이다.
                // checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
                var tr = checked.parent().parent().eq(i);
                var td = tr.children();

                // 체크된 row의 모든 값을 배열에 담는다.
                rowData.push(tr.text());

                // td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
                id = td.eq(1).text().trim();
                srcpath = td.eq(2).text().trim();
                despath = td.eq(5).text().trim();
                logpath = td.eq(8).text().trim();
                remark = td.eq(10).text().trim();
                checkedStatus = td.eq(9).text().trim();
            });

            if (checkedStatus != "미처리") {
                alert("미처리 상태인 항목만 수정 가능합니다.");
                return false;
            }

            const form = document.frm;
            const url = "/updatePopUp.do";

            form.action = url;
            form.method = "post";
            form.target = "frm";
            form.id.value = id;
            form.srcpath.value = srcpath;
            form.despath.value = despath;
            form.logpath.value = logpath;
            form.remark.value = remark;
            window.open("", "frm", "width=1200, height=800, top=150, left=200");
            form.submit();

        }
    }
</script>

</body>

</html>

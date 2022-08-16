<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <%--    <link href="./../../resources/css/styles2.css" rel="stylesheet" />--%>
</head>
<body>
<!-- Responsive navbar-->
<nav class="navbar navbar-expand-lg navbar-light bg-info bg-opacity-50">
    <div class="container px-5 fw-bold">
        <a class="navbar-brand" href="${ pageContext.request.contextPath }/serverList.do">파일 동기화 솔루션</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
                class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item border-end"><a class="nav-link"
                                                   href="${ pageContext.request.contextPath }/serverList.do">서버 정보
                    관리</a></li>
                <li class="nav-item border-end"><a class="nav-link"
                                                   href="${ pageContext.request.contextPath }/syncPathSelect.do">동기화 경로 관리</a></li>
                <li class="nav-item border-end"><a class="nav-link"
                                                   href="${ pageContext.request.contextPath }/fileSyncList.do">동기화 실행할
                    목록 선택</a></li>
                <li class="nav-item border-end"><a class="nav-link"
                                                   href="${ pageContext.request.contextPath }/syncStat.do">동기화 현황 조회</a>
                </li>
                <li class="nav-item "><a class="nav-link active" aria-current="page"
                                                   href="${ pageContext.request.contextPath }/syncOrder.do">에이전트 작업
                    조회</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- Page Content-->
<div class="container-fluid">
    <!-- 해드 -->
    <jsp:include page="../header.jsp">
        <jsp:param name="title" value="에이전트 작업 조회"/>
    </jsp:include>
    <!-- Content Row-->
    <!-- 버튼 -->
    <div class="row col-12  mx-2 my-2 px-0">
        <div class="col-5 offset-7 align-items-center text-end px-0">
            <div class="btn btn-primary col-4" onclick="syncCancel()" title="취소는 처리상태가 미처리 상태이고 작업 명이 요청일때 가능합니다.">
                선택된 작업 취소
            </div>
            <div class="btn btn-danger col-4 " onclick="syncDelete()"title="삭제는 미처리 상태일때 가능합니다.">
                선택된 작업 삭제
            </div>
        </div>
        <div class="align-items-bae float-start">
            <select class="col-1" id=selectANm name ="selectANm" onchange ="changeStat(this)">
                <option value="all">전체</option>
                <c:forEach var="list" items="${agentNmList}">
                    <option value="${list}">${list}</option>
                </c:forEach>
            </select>
            <select class="col-1" id=selectStat name="selectStat">
                <option value="all">전체</option>
            </select>
            <button class="btn btn-primary btn-sm" id=selectBtn onclick="viewSelect()")>조회</button>
        </div>
    </div>
    <!-- 테이블 -->
    <div class="table-responsive gx-4 gx-lg-5 align-items-center my-2">
        <table class="table table-bordered  table-hover align-middle">
            <thead class="text-center align-middle" style="height: 5em;">
            <tr>
                <th scope="col" rowspan="2" class="col"><input type="checkbox" name="checkSelect" id="chk0"
                                                               onclick="selectAll(this)"/></th>
                <th scope="col" class="col-sm" style="white-space: nowrap">번호</th>
                <th scope="col" class="col-1">할당 에이전트</th>
                <th scope="col" class="col-1">작업 명</th>
                <th scope="col" class="col-4">원본 경로</th>
                <th scope="col" class="col-4">대상 경로</th>
                <th scope="col" class="col-1">처리 상태</th>
                <th scope="col" class="col-1">처리 일시</th>
            </tr>
            </thead>
            <tbody class="table-group-divider" id="datalist">
            <c:forEach varStatus="ordVoStatus" var="ordVo" items="${agentOrderList}">
                <tr>
                        <%--                    <c:when test="${fn:substring(ordVo.wkOrdCd, 2, 3) ne 'C'}">--%>
                    <td class="text-center"><input class="${ordVo.procStat}" type="checkbox" name="source" id="<c:out value="${ordVo.wkOrdId}"/>" value="${ordVo.wkOrdCd}" onclick="checkList()" /></td>
                    <td><c:out value="${ordVoStatus.count}"/></td>
                    <td><c:out value="${ordVo.serverNm}"/></td>
                    <td><c:out value="${ordVo.wkOrdCdNm}"/></td>
                    <td><c:out value="${ordVo.source}"/></td>
                    <td><c:out value="${ordVo.target}"/></td>
                    <td><c:out value="${ordVo.procStat}"/></td>
                    <td><c:out value="${ordVo.procDtm}"/></td>
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
<script type="text/javascript">
    // 체크박스 전체 선택/해제 구현
    function selectAll(selectAll) {
        const checkboxes = document.getElementsByName('source');

        checkboxes.forEach((checkbox) => {
            if (!checkbox.disabled) {
                checkbox.checked = selectAll.checked;
            }
        })
    }

    // 조건에 따른 검색조회
    function viewSelect(){
        const selectNm = document.getElementById("selectANm").value;
        const selectStat = document.getElementById("selectStat").value;
        console.log(selectNm+selectStat);

        $.ajax({
            type : "POST",
            url : "ajax/selectView.do",
            data : {"serverNm" : selectNm ,"workCd" : selectStat},
            dataType: "json",
            success : function(res) {
                const tbody = document.getElementById("datalist");
                const listSize = tbody.rows.length;
                // 목록 지우기
                for (let i=0; i<listSize; i++) {
                    tbody.deleteRow(-1);
                }
                // 데이터 리스트 추가
                for (let i=0; i<res.length; i++) {
                    const newRow = tbody.insertRow();
                    val1 = res[i].wkOrdCd;
                    val2 = res[i].wkOrdId;
                    newRow.insertCell(0).innerHTML = '<td class="text-center"><input type="checkbox" name= "source" id= '+val2+' value = '+val1+' onclick="checkList()"/></td>';
                    newRow.insertCell(1).textContent = i+1;
                    newRow.insertCell(2).textContent = res[i].serverNm;
                    newRow.insertCell(3).textContent = res[i].wkOrdCdNm;
                    newRow.insertCell(4).textContent = res[i].source;
                    newRow.insertCell(5).textContent = res[i].target;
                    newRow.insertCell(6).textContent = res[i].procStat;
                    newRow.insertCell(7).textContent = res[i].procDtm;
                }
            },
            error : function() {
                alert("잘못조회하셨습니다.");
            }
        });
        const selectAll
            = document.querySelector('input[name="checkSelect"]');
            selectAll.checked = false; // 전체 셀렉트박스 초기화
    }
    // 작업 삭제기능
    function syncDelete() {
        let input = confirm("선택된 항목을 작업 삭제 요청하시겠습니까?\n계속하시려면 확인을 눌러주세요");
        var result = 0;
        var checkbox = $("input:checkbox[name='source']:checked")
        var check = $("input:checkbox[name='source']")

        if (input === true) {
            let idList = [];

            if ((check.filter(":checked").length) === 0) {
                console.log("체크된 항목이 없습니다. ");
                alert('체크된 항목이 없습니다.');
                return false;
            }

            checkbox.each(function (i) {
                var tr = checkbox.parent().parent().eq(i);
                var td = tr.children();
                if (this.checked && this.id !== "chk0") {//checked 처리된 항목을 list에 추가
                    if (td.eq(6).text().trim()==("미처리")) {
                        let listdata = {"id" : this.id ,"seq" : '1'};
                        idList.push(listdata);
                    } else if(td.eq(6).text().trim()==("취소 미처리")){
                        let listdata = {"id" : this.id ,"seq" : '2'};
                        idList.push(listdata);
                    }
                    else {result = 1;alert("미처리상태의 것만 삭제할수있습니다. error code = moon01");return false;}
                }

            });
            if (result == 0){
                $.ajax({
                    contentType:'application/json',
                    type: "POST",
                    url: "ajax/syncOrderDelete.do",
                    data: JSON.stringify(idList),
                    dataType: "json",
                    success: function (res) {
                        if (res) {
                            window.location.reload();   //페이지 새로고침
                            alert("작업 삭제 요청이 수행되었습니다.");
                        } else {
                            alert("작업 삭제 요청이 실패되었습니다.");
                        }
                    }
                });
            } else if(result !== 1) {
                alert("작업 삭제 요청이 수행되지 않았습니다.");
            }
        }
    }
    // 서버이름에 따른 2번째 셀렉트박스 변경
    function changeStat(target){
        const serverNm = target.options[target.selectedIndex].value; // 선택된 디렉토리의 id값
        var id = document.getElementById("selectStat");
        $("select#selectStat option").remove(); // option값 초기화
        $("#selectStat").prepend("<option value='all'>전체</option>"); //기본값 넣기
        // if(e.value=="all"){
        //
        // }
        $.ajax({
            type : "POST",
            url: "ajax/getOrderCd.do",
            data: {"serverNm" : serverNm},
            dataType: "json",
            success:function(data){
                /*  const tbody = document.getElementById("selectStat");
                  const listSize = tbody.rows.length;
                  // 목록 지우기
                  for (let i=0; i<listSize; i++) {
                      tbody.deleteRow(-1);
                  }*/
                for(let i=0;i<data.length;i++){
                    var opt = document.createElement("option");
                    opt.value = data[i].wkOrdCd;
                    opt.innerHTML = data[i].wkOrdCdNm;
                    id.appendChild(opt);
                }


            },
            error : function(){
                alert("셀렉트하는 과정에서 오류가 발생하였습니다. code - changeStat ajax"+data);
            }
        });
    }


    // 동기화 취소 기능
    function syncCancel() {
        let input = confirm("선택된 항목을 작업 취소 요청하시겠습니까?\n계속하시려면 확인을 눌러주세요");
        let result = 0;
        var check = $('input:checkbox[name="source"]');

        if (input === true) {
            let idList = [];

            if ((check.filter(":checked").length) === 0) {
                console.log("체크된 항목이 없습니다. ");
                alert('체크된 항목이 없습니다.');
                return false;
            }

            $('input:checkbox[name="source"]').each(function () {
                if (this.checked && this.id !== "chk0") {//checked 처리된 항목을 list에 추가
                    if (this.value.includes('C')) {
                        result = 1;
                        alert("취소된 작업은 재취소할 수 없습니다.");
                        return false;
                    } else if(this.classList.contains('완료')){
                        result = 1;
                        alert("완료된 작업은 취소할 수 없습니다.");
                        return false;
                    }
                    else{
                        idList.push(this.id);
                    }
                }

            });
            if (result == 0){
                $.ajax({
                    type: "POST",
                    url: "ajax/syncOrderCancel.do",
                    data: {"wkOrdIdList": idList},
                    dataType: "json",
                    success: function (res) {
                        if (res) {
                            window.location.reload();   //페이지 새로고침
                            alert("작업 취소 요청이 수행되었습니다.");
                        } else {
                            alert("작업 취소 요청이 수행될수 없습니다.");
                        }
                    }
                });
            } else if (result ==1){
            }
            else
                alert("작업 취소 요청이 수행되지 않았습니다.");
        }
    }
    function checkList() { // 체크박스 확인 후 all 체크박스 체크확인
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
</script>
</body>
</html>

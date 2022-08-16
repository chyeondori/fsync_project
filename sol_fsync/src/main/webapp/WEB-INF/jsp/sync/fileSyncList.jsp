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
    <%--    <link href="./../../resources/css/styles2.css" rel="stylesheet" />--%>
</head>
<body>
    <!-- Responsive navbar-->
    <nav class="navbar navbar-expand-lg navbar-light bg-info bg-opacity-50">
        <div class="container px-5 fw-bold">
            <a class="navbar-brand" href="${ pageContext.request.contextPath }/serverList.do">파일 동기화 솔루션</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item border-end">
                        <a class="nav-link" href="${ pageContext.request.contextPath }/serverList.do">서버 정보 관리</a>
                    </li>
                    <li class="nav-item border-end">
                        <a class="nav-link" aria-current="page"
                           href="${ pageContext.request.contextPath }/syncPathSelect.do">
                            동기화 경로 관리</a>
                    </li>
                    <li class="nav-item border-end">
                        <a class="nav-link active" href="${ pageContext.request.contextPath }/fileSyncList.do">
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
            <jsp:param name="title" value="동기화 실행할 목록 선택"/>
        </jsp:include>
        <%--    <div class="row gx-4 gx-lg-5 align-items-center my-3 px-0 mx-0">--%>
        <%--        <div class="fs-1">--%>
        <%--            동기화 실행할 목록 선택--%>
        <%--        </div>--%>
        <%--    </div>--%>
        <!-- Content Row-->
        <div class="row gx-4 gx-lg-5 align-items-center mx-1 my-5">
            <!-- 좌측 컨텐츠 -->
            <div class="col-3 align-self-start">
            <%--<div class="col-2 align-self-start">--%>
                <label class="col-12 my-2">
                    <select class="col-12" name="tree-name" id="topDirSelect">
                        <option name="tree-name" value="null" selected>선택</option>
                        <c:forEach var="list" items="${topDir}">
                            <option name="tree-name" value=${list.syncId}>
                                    ${list.syncId}. ${list.srcTopDir}->${list.dstTopDir}>
                            </option>
                        </c:forEach>
                    </select>
                </label>
                <div class="col-12 py-3" style="overflow:auto; height:700px;" id="tree">

                </div>
            </div>

            <%--        <div class="vr border border-secondary px-0"></div>--%>
            <!-- 우측 컨텐츠 -->
            <div class="col-9 border-start align-self-start">
            <%--<div class="col-10 border-start align-self-start">--%>
                <!-- 버튼 -->
                <div class="col-10 offset-2 text-end my-2">
                    <div class="btn col-2 btn-primary" onclick="sizeVeriBtn()">
                        크기 검증
                    </div>
                    <div class="btn col-2 btn-primary" onclick="syncOrdBtn()">
                        (재)동기화
                    </div>
                    <div class="btn col-2 btn-primary" onclick="syncUpBtn()">
                        변경분 동기화
                    </div>
                </div>
                <!-- 경로 레이블 -->
                <div class="row my-2 align-middle">
                    <div class="col-1 align-self-center text-end">
                       <label class="col-12 my-1 fw-bold" for="nowPath" style="text-align: center; font-size: 0.95rem;">현재 경로</label>
                    </div>
                    <div class="col-11"><input class="col-12 px-3 py-1 my-1 border-2" type="text" id="nowPath"
                                               readonly/>
                    </div>
                </div>
                <%--            <form class="form-floating">--%>
                <%--                <input type="text" class="form-control col-12" id="경로2" placeholder="c/test/test/test" value="c/test/test/test">--%>
                <%--                <label for="경로2">현재 경로</label>--%>
                <%--            </form>--%>

                <!-- 테이블 -->
                <table class="table table-bordered  table-hover align-middle">
                    <thead class="text-center align-middle" style="height: 5em;">
                    <tr>
                        <th scope="col" class="col">
                            <input type="checkbox" name="checkSelect" onclick="selectAll(this)"/>
                        </th>
                        <th scope="col" class="col-3">원천 디렉토리명</th>
                        <th scope="col" class="col-3">대상 디렉토리명</th>
                        <th scope="col" class="col-2">일시</th>
                        <th scope="col" class="col-1">유형</th>
                        <th scope="col" class="col-1">크기</th>
                        <th scope="col" class="col-1">파일 개수</th>
                        <th scope="col" class="col-1">동기화<br/>상태</th>

                        <%--    /* ysy */--%>
                        <th scope="col" class="col">디렉토리/<br>파일 이름</th>

                    </tr>
                    </thead>
                    <tbody class="table-group-divider" id="allFileTable">

                    </tbody>
                </table>
            </div>
        </div>
        <div class="jm-loading" style="display:none">
            <div class="lds-spinner">
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>

        </div>
        <form id="jsonForm" name="jsonForm">
            <input type="hidden" id="jsonString" value="">
            <input type="hidden" id="orderDiv" value="">
        </form>
        <jsp:include page="../footer.jsp"/>
    </div>

</div>
<!-- Footer-->
<!-- Bootstrap core JS-->
<script src="../../../js/bootstrap.bundle.min.js"></script>
<%-- 임시 jquery--%>
<script src="../../../js/jquery/jquery.js"></script>
<%--jstree--%>
<link rel="stylesheet" href="../../../js/jstree/themes/default/style.css"/>
<script src="../../../js/jstree/jstree.js"></script>

<script type="text/javascript">

    //최대 보여줄 개수 설정
    let maxLine = 20;

    //선택된 노드 ID
    var oldNodeId = 0;
    //페이징 위한 변수
    var nowPage = 0;
    //maxLine 미만인지 체크
    var maxLineBool = false;

    //버튼 작업구분
    var orderDiv = '';

    $(document).ready(function () {

    });

    var previous;
    $('#topDirSelect').on('focus', function () {
        //변경하기 전 선택 값
        previous = this.value;
    }).change(function () {

        if (checkAlert()) {   //이동 확인 선택
            //기존트리 제거
            $("#tree").empty().jstree("destroy");
            //현재 경로 제거
            $('#nowPath').val("");
            //목록 제거
            $("#allFileTable").html("");
            //선택된 노드 id 제거
            oldNodeId = 0;

            if (this.value !== 'null') {
                loadTree(this.value);
            }
        } else {     //이동 취소 선택
            //취소 선택시 이동 안함
            $('#topDirSelect').val(previous).prop("selected", true);
        }

    })

    // 트리 생성
    function loadTree(syncId) {
        var treeUrl = 'dirToJson.do?syncId=' + syncId;

        console.log("＠ 트리 loadTree treeUrl : " + treeUrl);

        $('#tree').jstree({
            'core': {
                'themes': {"stripes": true},
                'data': {
                    'url': function (node) {
                        return treeUrl;
                    },
                    'data': function (node) {
                        return node;
                    },
                },

            }, 'types': {
                'default': {
                    'icon': 'jstree-folder'
                }
            },
            // 'plugins': ["wholerow", "types"]
        }).bind('loaded.jstree', function (event, data) {     //트리 로딩 완료 이벤트

            console.log("＠ 트리 로딩 완료 이벤트");

        }).bind('select_node.jstree', function (event, data) {    //노드 선택 이벤트
            // checkAlert() : 다른 노드로 이동 시 현재 체크된 게 있는지 없는지 판단
            if (checkAlert()) {

                //이동 확인 선택
                $('#nowPath').val($('#tree').jstree(true).get_path(data.node, "/"));

                //다른 노드 선택시에만 ajax 호출
                var dirId = data.instance.get_node(data.selected).id;
                if (oldNodeId !== dirId) {
                    nowPage = 0;
                    maxLineBool = false;
                    oldNodeId = dirId;
                    callAllFile(dirId);
                }
            } else {      //이동취소 선택
                // var dirId = data.instance.get_node(data.selected).id;
                $('#tree').jstree("deselect_all");
                // $('#tree').jstree(true).get_node(dirId).prop('aria-selected',true);
                // data.instance.get_node(data.selected).css()
                // $('#tree').find(dirId).addClass('jstree-clicked');
                // $('#tree').jstree('toggle_node',dirId);
                // data.instance.get_node(data.selected).attr('aria-selected',true);
            }

        });
    }

    //디렉토리+파일 불러오는 ajax
    function callAllFile(dirId) {
        $(".jm-loading").show();
        $.ajax({
            type: "GET",
            url: "allFileToJson.do?dirId=" + dirId + "&nowPage=" + nowPage + "&maxLine=" + maxLine,
            // data: {"dirId": dirId},
            // contentType : "application/json",
            dataType: "json",
            async: true,
            success: function (result) {
                $(".jm-loading").hide();
                var makehtml;

                //비어있는 값 검사
                if (Array.isArray(result) && result.length === 0) {
                    if (!maxLineBool) {   //처음일경우
                        // $('#nowPath').val($('#tree').jstree(true).get_path(data.node,"/"));
                        $("#allFileTable").html("");
                    } else {
                        //다불러왔을 경우
                        maxLineBool = false;
                    }
                } else {
                    $('#nowPath').val(result[0].exDirNm);

                    console.log($('#nowPath').val(result[0].exDirNm).val());

                    $.each(result, function (i) {

                        //디렉토리, 파일 구분
                        var typeId = result[i].typeCd + result[i].exId;

                        makehtml += '<tr>';
                        makehtml += '<input type="hidden" name="trTypeCd" value = "' + result[i].typeCd + '"/>';
                        makehtml += '<input type="hidden" name="trExId" value = "' + result[i].exId + '"/>';
                        makehtml += '<input type="hidden" name="trProcStat" value = "' + result[i].procStat + '"/>';
                        // makehtml += '<td class="text-center"><input type="checkbox" name="source" id="chk1" value="'+ typeId +'"/></td>';
                        // makehtml += '<td class="text-center"><input type="checkbox" name="checkSelect" id="chk1" value="'+ typeId +'" onclick="checkList()"/></td>';
                        makehtml += '<td class="text-center"><input type="checkbox" name="source" id="chk1" value="' + typeId + '" onclick="checkList()"/></td>';
                        makehtml += '<td>' + result[i].exSrcFName + '</td>';
                        makehtml += '<td>' + result[i].exDstFName + '</td>';

                        makehtml += '<td>' + result[i].exDtm + '</td>';
                        makehtml += '<td>' + result[i].exType + '</td>';
                        makehtml += '<td class="text-end">' + result[i].exSize + '</td>';
                        makehtml += '<td class="text-end">' + result[i].exCnt + '</td>';
                        makehtml += '<td class="text-center">' + result[i].exProcStat + '</td>';

                        /* ysy */
                        makehtml += '<td>' + result[i].exName + '</td>';

                        makehtml += '</tr>';
                    });
                    //기존에 추가
                    if (maxLineBool) {
                        $("#allFileTable").append(makehtml);
                    } else { //페이지 새로 불러올때
                        $("#allFileTable").html(makehtml);
                    }

                    //불러온 데이터가 설정한 maxLine 이면 다음데이터 호출
                    if (Array.isArray(result) && result.length >= maxLine) {
                        nowPage++;
                        maxLineBool = true;
                    } else { //설정한 maxLine 미만이면 데이터 호출 안함
                        maxLineBool = false;
                    }
                }
            },
            error: function (data) {
                alert("오류가 발생했습니다.");
            }

        });
    }

    //스크롤 페이징
    window.addEventListener('scroll', () => {
        let val = window.innerHeight + window.scrollY;

        if (val >= document.body.offsetHeight) {
            if (maxLineBool) {
                callAllFile(oldNodeId);
            }
        }
    });

    //체크상태에서 이동시 알림
    function checkAlert() {
        // const checkboxes = document.getElementsByName('source');
        var checked = $("input[name=source]:checked").length;
        if (checked > 0) {
            var checkText = '현재 디렉터리를 벗어나면, 선택된 내용이 사라집니다. 계속하시겠습니까?';
            let checkConfirm = confirm(checkText);
            if (checkConfirm) {
                //체크 초기화
                $("input[name=source]").prop("checked", false);
                // 전체체크되는 체크박스도 false로 지정
                $("input[name=checkSelect]").prop("checked", false);
            }
            return checkConfirm;
        } else {

            return true;
        }
    }

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

    function popupAgentSelect(jsonString) {
        //화면의 높이와 너비를 구합니다.
        var maskHeight = $(document).height();
        var maskWidth = window.document.body.clientWidth;
        //화면에 출력할 마스크를 설정해줍니다.
        var mask = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
        // var loadingImg = '';
        // loadingImg += "<div id='loadingImg'>";
        // loadingImg += " <img src='../805.gif' style='position: absolute; z-index: 1; display: block; left:50%; top:50%; margin: 0 auto;' alt='test'/>";
        // loadingImg += "</div>";
        //화면에 레이어 추가
        // $('body').append(mask).append(loadingImg);
        //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
        $('#mask').css({'width': maskWidth, 'height': maskHeight, 'opacity': '0.3'});
        //마스크 표시
        $('#mask').show();
        //로딩중 이미지 표시
        // $('#loadingImg').show();
        $('#jsonString').val(jsonString);
        window.open("${ pageContext.request.contextPath }/selectAgent.do", "popup2", "width=1200, height=800, top=150, left=200");
        <%--document.jsonForm.action = "${ pageContext.request.contextPath }/selectAgent.do";--%>
        <%--document.jsonForm.target = "popup2";--%>
        <%--document.jsonForm.method="post";--%>
        <%--document.jsonForm.submit();--%>
    }

    //Json데이터 생성 - 크기 검증 및 (재)동기화
    function setDataJson() {
        var params = new Array();

        // var checkedNodes =  $("input[name=source]").;
        if (checkedValidation()) {
            $('input[name=source]').each(function (index) {
                if ($(this).is(":checked") == true) {

                    //선택된 체크박스의 tr
                    var selectedTr = $(this).parent().parent();
                    //선택된 체크박스의 td
                    var selectedTd = selectedTr.children();
                    //선택된 체크박스의 type
                    var selectedType = selectedTd.eq(0).val();
                    //선택된 체크박스의 id
                    var selectedId = selectedTd.eq(1).val();
                    //선택된 체크박스의 procstat
                    var selectedStat = selectedTd.eq(2).val();

                    var typeId = new Object();
                    typeId.type = selectedType;
                    typeId.id = selectedId;
                    // alert(typeId.type + typeId.id);
                    params.push(typeId);
                }
            });
            popupAgentSelect(JSON.stringify(params));
            // alert(JSON.stringify(params));
            // $.ajax({
            //     type: "POST",
            //     url: "sizeVerification.do",
            //     data: JSON.stringify(params),
            //     contentType : "application/json",
            //     dataType: "json",
            //     async: true,
            //     success: function (result) {
            //         alert("검증 요청 성공하였습니다." + JSON.stringify(result));
            //     },
            //     error: function (data) {
            //         alert("오류가 발생했습니다.");
            //     }
            //
            // });
        }
    }

    //Json데이터 생성 - 변경분 동기화
    function setUpdateDataJson() {

        console.log('setUpdateDataJson 시작');
        var params = new Array();

        // var checkedNodes =  $("input[name=source]").;
        if (checkedUpdate()) {
            $('input[name=source]').each(function (index) {
                if ($(this).is(":checked") == true) {

                    //선택된 체크박스의 tr
                    var selectedTr = $(this).parent().parent();
                    //선택된 체크박스의 td
                    var selectedTd = selectedTr.children();
                    //선택된 체크박스의 type
                    var selectedType = selectedTd.eq(0).val();
                    //선택된 체크박스의 id
                    var selectedId = selectedTd.eq(1).val();
                    //선택된 체크박스의 procstat
                    var selectedStat = selectedTd.eq(2).val();

                    var typeId = new Object();
                    typeId.type = selectedType;
                    typeId.id = selectedId;
                    params.push(typeId);
                }
            });
            console.log('setUpdateDataJson params : ' + params);

            popupAgentSelect(JSON.stringify(params));
        }
    }

    //크기 검증 버튼 클릭
    function sizeVeriBtn() {
        $('#orderDiv').val('veri');
        setDataJson();
    }

    //동기화 버튼 클릭
    function syncOrdBtn() {
        $('#orderDiv').val('sync');
        setDataJson();
    }

    //변경분 동기화 버튼 클릭
    function syncUpBtn() {
        $('#orderDiv').val('upSync');
        setUpdateDataJson();
    }

    //선택된 체크 박스 검사 - 크기 검증 및 (재)동기화
    function checkedValidation() {
        var valiBool = false;
        var valiMsg = '동기화중 또는 검증중인 디렉토리 또는 파일이 선택되었습니다.';
        if ($('input[name=source]:checked').length === 0) {   //선택 없을 시
            valiMsg = '선택된 디렉토리 또는 파일이 없습니다.';
            valiBool = false;
        } else {
            valiBool = true;
            $('input[name=source]:checked').each(function (index) {
                //선택된 체크박스의 tr
                var selectedTr = $(this).parent().parent();
                //선택된 체크박스의 td
                var selectedTd = selectedTr.children();

                //선택된 체크박스의 procstat
                var selectedStat = selectedTd.eq(2).val();
                if (selectedStat == 'FN' || selectedStat == 'SN' || selectedStat == 'NO') {  //동기화중, 검증중, 미처리
                    valiBool = false;
                }
            });
        }
        if (valiBool == false) {
            alert(valiMsg);
        } else {
            // var checkText = '';
            // let checkConfirm = confirm(checkText);
            // if(checkConfirm){
            //     //체크 초기화
            //     $("input[name=source]").prop("checked", false);
            // }
            // return checkConfirm;
        }
        return valiBool;
    }

    // 선택된 체크 박스 검사 - 변경분 동기화(업데이트)
    function checkedUpdate() {
        var valiBool;
        var valiMsg = '동기화중인 디렉토리 또는 파일이 선택되었습니다.';

        if ($('input[name=source]:checked').length === 0) {   //선택 없을 시
            valiMsg = '선택된 디렉토리 또는 파일이 없습니다.';
            valiBool = false;
        } else {
            valiBool = true;

            $('input[name=source]:checked').each(function (index) {
                //선택된 체크박스의 tr
                var selectedTr = $(this).parent().parent();
                //선택된 체크박스의 td
                var selectedTd = selectedTr.children();
                //선택된 체크박스의 procstat
                var selectedStat = selectedTd.eq(2).val();
                if (selectedStat == 'FN') {  //동기화중
                    valiBool = false;
                }
            });
        }
        if (valiBool === false) {
            alert(valiMsg);
        }
        return valiBool;
    }


</script>
</body>
</html>
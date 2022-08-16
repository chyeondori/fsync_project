package fsync.business.sync.controller;

import fsync.cmm.utils.StringUtils;
import fsync.business.sync.vo.*;
import fsync.business.sync.service.FileSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @FileName : FileSyncController.java
 * @Date : 2022. 7. 19.
 * @Author : kimhyeonjung, ysy
 * @Description : 동기화 실행 목록 선택 컨트롤러
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * ================================================================
 * 2022.07.19   kimhyeonjung    최초생성
 * 2022.08.02   ysy             변경분 동기화 기능 추가
 */
@Slf4j
@Controller
public class FileSyncController {
    @Autowired
    FileSyncService fileSyncService;

    /**
     * 동기화 실행할 목록 선택 페이지 호출(트리)
     *
     * @param request, model
     * @return String
     * @Method Name   : sampleMain
     * @Date / Author : 2022. 7. 19.  kimhyeonjung
     * @History 2022.07.19 kimhyeonjung 최초생성
     */
    @RequestMapping(value = "/fileSyncList.do", method = RequestMethod.GET)
    public String sampleMain(HttpServletRequest request, Model model) throws Exception {
//        log.debug("######### fileSyncList.do #########");

        //동기화 파일, 디렉토리 선택
        List<TopDirInfoVO> topDir = fileSyncService.selectTopDir();
        //1. 동기화 경로 관리
        model.addAttribute("topDir", topDir);
        log.debug("■■■■ fileSyncList.do topDir : " + topDir.toString());

        return "sync/fileSyncList";

    }

    /**
     * 트리 Json 호출 AJAX
     *
     * @param request, syncId
     * @return List
     * @Method Name   : dirToJson
     * @Date / Author : 2022. 7. 19.  kimhyeonjung
     * @History 2022.07.19 kimhyeonjung 최초생성
     */
    @RequestMapping(value = "/dirToJson.do", method = RequestMethod.GET)
    @ResponseBody
    public List<DirTreeJsonVO> dirToJson(HttpServletRequest request, String syncId) throws Exception {
        log.debug("######### dirToJson.do syncId : " + syncId);
        //1. 트리구조 JSON 생성
//        DirTreeJsonVO dirTreeJsonVO = fileSyncService.makeDirJson(syncId);
        return fileSyncService.makeDirJson(syncId);
    }

    /**
     * 디렉토리, 파일 전체 호출 AJAX
     *
     * @param request, dirId, nowPage, maxLine
     * @return List
     * @Method Name   : allFileToJson
     * @Date / Author : 2022. 7. 19.  kimhyeonjung
     * @History 2022.07.19 kimhyeonjung 최초생성
     */
    @RequestMapping(value = "/allFileToJson.do", method = RequestMethod.GET)
    @ResponseBody
    public List<AllFileListVO> allFileToJson(HttpServletRequest request, @RequestParam String dirId, String nowPage, String maxLine) throws Exception {
        log.debug("######### allFileToJson.do ######### dirId : [" + dirId + "] nowPage : [" + nowPage + "] maxLine : [" + maxLine + "]");
        log.debug("★★★★★★★★★★ 트리 옆 파일목록 ★★★★★★★★★★");
        log.debug("dirId : [" + dirId + "] nowPage : [" + nowPage + "] maxLine : [" + maxLine + "]");

        int nowPageNo = 0;
        if (!StringUtils.isNullOrEmpty(nowPage)) {
            try {
                nowPageNo = Integer.parseInt(nowPage);
            } catch (Exception e) {
                log.error("nowPage 변환 에러 - 기본값으로 진행 (0)");
            }
        }

        int maxLineNo = 30;
        if (!StringUtils.isNullOrEmpty(maxLine)) {
            try {
                maxLineNo = Integer.parseInt(maxLine);
            } catch (Exception e) {
                log.error("maxLine 변환 에러 - 기본값으로 진행 (30)");
            }
        }

        //1. 트리구조 JSON 생성
//        DirTreeJsonVO dirTreeJsonVO = fileSyncService.makeDirJson(syncId);
        log.debug("######### allFileToJson.do2 ######### nowPageNo : [" + nowPageNo + "] maxLineNo : [" + maxLineNo + "]");
        return fileSyncService.selectAllFiles(dirId, nowPageNo, maxLineNo);
    }

    /**
     * 디렉토리, 파일 크기 검증 insert ajax
     *
     * @param request, params
     * @return int
     * @Method Name   : sizeVerification
     * @Date / Author : 2022. 7. 21.  kimhyeonjung
     * @History 2022.07.21 kimhyeonjung 최초생성
     */
    @RequestMapping(value = "/sizeVerification.do", method = RequestMethod.POST)
    @ResponseBody
    public int sizeVerification(HttpServletRequest request, @RequestBody List<SyncVO> params) throws Exception {
        log.debug("######### sizeVerification.do ######### params : [" + params.toString() + "]");
        //파일 검증
        return fileSyncService.sizeVerification(params);
    }

    /**
     * 디렉토리, 파일 동기화 insert ajax
     *
     * @param request, params
     * @return int
     * @Method Name   : insertSyncOrder
     * @Date / Author : 2022. 7. 21.  kimhyeonjung
     * @History 2022.07.21 kimhyeonjung 최초생성
     */
    @RequestMapping(value = "/insertSyncOrder.do", method = RequestMethod.POST)
    @ResponseBody
    public int insertSyncOrder(HttpServletRequest request, @RequestBody List<SyncVO> params) throws Exception {
        log.debug("######### insertSyncOrder.do ######### params : [" + params.toString() + "]");
        // 작업테이블에 넣기
        return fileSyncService.insertSyncOrder(params);
    }

    /**
     * 에이전트 선택 페이지 호출
     *
     * @param request, model
     * @return String
     * @Method Name   : selectAgent
     * @Date / Author : 2022. 7. 22.  kimhyeonjung
     * @History 2022.07.22 kimhyeonjung 최초생성
     */
    @RequestMapping(value = "/selectAgent.do", method = RequestMethod.GET)
    public String selectAgent(HttpServletRequest request, Model model) throws Exception {
        log.debug("######### selectAgent.do ######### params : [ " + model.toString() + " ]");
        model.addAttribute("serverInfo", fileSyncService.selectServerInfo());

        return "sync/selectAgent";
    }

    /**
     * 변경분 동기화 업데이트 insert
     *
     * @param request, params
     * @return int
     * @Method Name   : insertUpdateSync
     * @Date / Author : 2022.08.02  ysy
     * @History 2022.08.02 ysy 최초생성
     */
    @RequestMapping(value = "/insertUpdateSync.do", method = RequestMethod.POST)
    @ResponseBody
    public int insertUpdateSync(HttpServletRequest request, @RequestBody List<SyncVO> params) throws Exception {
        log.debug("■■■ insertUpdateSync.do params : " + params.toString());
        return fileSyncService.insertUpdateSync(params);
    }


}

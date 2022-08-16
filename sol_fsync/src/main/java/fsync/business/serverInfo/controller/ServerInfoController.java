package fsync.business.serverInfo.controller;

import fsync.business.serverInfo.service.ServerInfoService;
import fsync.business.serverInfo.vo.ServerInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @FileName : ServerInfoController.java
 * @Date : 2022. 7. 26.
 * @Author : ysy
 * @Description : ServerInfoController
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.26       ysy       최초생성
 */


@Slf4j
@Controller
public class ServerInfoController {

    @Autowired
    ServerInfoService serverInfoService;

    /**
     * 전체 서버 목록 조회
     *
     * @param model
     * @return String
     * @throws Exception
     * @Method Name   : serverList
     * @Date / Author : 2022. 07. 28 / ysy
     * @History 2022.07.27 ysy 최초생성
     */
    @RequestMapping(value = "/serverList.do", method = RequestMethod.GET)
    public String serverList(Model model) throws Exception {

        List<ServerInfoVO> sList = serverInfoService.selectAllServer();
        model.addAttribute("sList", sList);
        String result = null;

        try {

            log.debug("■■■■■ 서버 리스트 출력 : " + sList.toString());
            result = "serverInfo/serverList";
        } catch (Exception e) {
            log.error("■■■■■ ServerInfoController serverList 에러");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 서버 등록 팝업창 호출
     *
     * @param
     * @return String
     * @throws Exception
     * @Method Name   : insertServerForm
     * @Date / Author : 2022. 07. 28 / ysy
     * @History 2022.07.27 ysy 최초생성
     */
    @RequestMapping(value = "/insertServerForm.do", method = RequestMethod.GET)
    public String insertServerForm(){

        String result = null;
        try {
            log.debug("■■■■■ 서버 등록 팝업 호출 insertServerForm.do");
            result = "serverInfo/insertServer";
        } catch (Exception e) {
            log.error("■■■■■ ServerInfoController insertServerForm 에러");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 서버 등록 작업
     *
     * @param serverInfoVO
     * @return String
     * @throws Exception
     * @Method Name   : insertServer
     * @Date / Author : 2022. 07. 28 / ysy
     * @History 2022.07.27 ysy 최초생성
     */
    @RequestMapping(value = "/insertServer.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String insertServer(ServerInfoVO serverInfoVO){

        String resp = null;

        try {
            resp = serverInfoService.insertServer(serverInfoVO);
            log.debug("■■■■■ insertServer.do serverInfoVO : " + serverInfoVO.toString());
        } catch (Exception e) {
            log.error("■■■■■ ServerInfoController insertServer 에러");
            e.printStackTrace();
        }
        log.debug("■■■■■ 서버컨트롤러 insertServer.do resp : " + resp);
        return resp;
    }

    /**
     * 서버 수정 팝업창 호출
     *
     * @param request, model
     * @return String
     * @throws Exception
     * @Method Name   : updateServerForm
     * @Date / Author : 2022. 07. 28 / ysy
     * @History 2022.07.27 ysy 최초생성
     */
    @RequestMapping(value = "/updateServerForm.do", method = RequestMethod.POST)
    public String updateServerForm(HttpServletRequest request, Model model){

        String serverId = request.getParameter("serverId");
       /* String serverTy = request.getParameter("serverTy");*/
        String serverNm = request.getParameter("serverNm");
        String serverIp = request.getParameter("serverIp");
        String hostNm = request.getParameter("hostNm");
        String maxJobCnt = request.getParameter("maxJobCnt");
        String result = null;

        try {
            model.addAttribute("serverId", serverId);
           /* model.addAttribute("serverTy", serverTy);*/
            model.addAttribute("serverNm", serverNm);
            model.addAttribute("serverIp", serverIp);
            model.addAttribute("hostNm", hostNm);
            model.addAttribute("maxJobCnt", maxJobCnt);
            log.debug("■■■■■ updateServerForm.do model : " + model);

            result = "serverInfo/updateServer";
        } catch (Exception e) {
            log.error("■■■■■ ServerInfoController updateServerForm 에러");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 서버 수정 작업
     *
     * @param serverInfoVO
     * @return String
     * @throws Exception
     * @Method Name   : updateServer
     * @Date / Author : 2022. 07. 28 / ysy
     * @History 2022.07.27 ysy 최초생성
     */
    @RequestMapping(value = "/updateServer.do", method = RequestMethod.POST)
    @ResponseBody
    public String updateServer(ServerInfoVO serverInfoVO){
        log.debug("■■■■■ updateServer.do serverInfoVO : [" + serverInfoVO.toString() + "]");

        String resp = null;
        try {
            resp = serverInfoService.updateServer(serverInfoVO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("■■■■■ ServerInfoController updateServer 에러");
        }
        log.debug("■■■■■ updateServer.do resp : " + resp);
        return resp;
    }

    /**
     * 서버 삭제 작업
     *
     * @param request, model
     * @return Object
     * @throws Exception
     * @Method Name   : deleteS
     * @Date / Author : 2022. 07. 28 / ysy
     * @History 2022.07.27 ysy 최초생성
     */
    @ResponseBody
    @RequestMapping(value = "/deleteServer.do", method = RequestMethod.POST)
    public String deleteServer(HttpServletRequest request){

        // jsp에서 체크된 체크박스 아이디를 json으로 받음
        String[] checkId = request.getParameterValues("checked");
        int checkIdLength = checkId.length;

        log.debug("■■■■■ deleteServer.do String[] checkId : " + checkId.toString());
        log.debug("■■■■■ deleteServer.do checkIdLength : " + checkIdLength);

        String resp = null;

        try {
            if (checkId != null && 0 < checkId.length) {
                for (int i = 0; i < checkId.length; i++) {
                    // json으로 받아온 배열을 trim해서 checkId 하나씩 빼기
                    log.debug("■■■■■ deleteServer.do json 배열 : " + i + " = " + checkId[i].trim());
                }
                // json으로 받은 배열의 크기와 db에서 받은 삭제 배열 크기 비교
                int deleteLength = serverInfoService.deleteServer(checkId);

                if (deleteLength != -10) {
                    if (checkIdLength == deleteLength) {
                        log.debug("■■■■■ deleteServer.do 비교 checkIdLength, deleteLength : " + checkIdLength + deleteLength);
                        resp = "success";
                    } else {
                        resp = "fail";
                    }
                } else {
                    resp = "dataException";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("■■■■■ ServerInfoController deleteServer.do 에러");
        }
        log.debug("■■■■■ ServerInfoController deleteServer.do resp : " + resp);

        return resp;
    }

}

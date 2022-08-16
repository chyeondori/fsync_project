package fsync.business.path.controller;

import fsync.business.path.service.FilePathService;
import fsync.business.path.vo.RegRequestVo;
import fsync.business.path.vo.TopDirInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class FilePathController {

    @Autowired
    FilePathService filePathService;

    /**
     * 동기화 경로 관리 화면 호출
     * @Method Name   : pathSelect
     * @Date / Author : 2022. 7. 19.  leedohyun
     * @param model
     * @param req
     * @return
     * @throws Exception
     * @History
     * 2022.07.19 leedohyun
     */
    @RequestMapping(value = "/syncPathSelect.do", method = RequestMethod.GET)
    public String pathSelect(Model model, HttpServletRequest req)throws Exception{
        log.debug("###syncPathSelect.do ### 동기화 경로 관리 화면 호출 ###");
        model.addAttribute("resultList", filePathService.topDirInfoList());
        return "path/syncPathSelect";
    }

    /**
     * 동기화 경로 등록 팝업 화면 호출
     * @Method Name   : insertPopUp
     * @Date / Author : 2022. 7. 20.  leedohyun
     * @param req
     * @return
     * @throws Exception
     * @History
     * 2022.07.20 leedohyun
     */
    @RequestMapping(value = "/insertPopUp.do", method = RequestMethod.GET)
    public String insertPopUp(HttpServletRequest req)throws Exception{
        log.debug("###insertPopUp.do ### 동기화 경로 등록 팝업 화면 호출 ###");
        return "path/insertPopUp";
    }


    /**
     * 동기화 경로 수정 팝업 화면 호출
     * @Method Name   : updatePopUp
     * @Date / Author : 2022. 7. 20.  leedohyun
     * @param model
     * @param req
     * @return
     * @throws Exception
     * @History
     * 2022.07.20 leedohyun
     */
    @RequestMapping(value = "/updatePopUp.do", method = RequestMethod.POST)
    public String updatePopUp(Model model, HttpServletRequest req)throws Exception{

        log.debug("###updatePopUp.do ### 동기화 경로 수정 팝업 화면 호출 ###");

        //1. 요청 파라미터 변수에 저장
        int id = Integer.parseInt(req.getParameter("id"));
        String srcpath = req.getParameter("srcpath").trim();
        String despath = req.getParameter("despath").trim();
        String logpath = req.getParameter("logpath").trim();
        String remark = req.getParameter("remark").trim();

        //2. model에 추가
        model.addAttribute("id", id);
        model.addAttribute("srcpath",srcpath);
        model.addAttribute("despath",despath);
        model.addAttribute("logpath",logpath);
        model.addAttribute("remark", remark);

        return "path/updatePopUp";
    }

    /**
     * 동기화 경로 등록
     * @Method Name   : register
     * @Date / Author : 2022. 7. 20.  leedohyun
     * @param model
     * @param req
     * @return
     * @throws Exception
     * @History
     * 2022.07.20 leedohyun
     */
    @RequestMapping(value = "/syncRegister.do", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody String register(Model model, HttpServletRequest req, RegRequestVo regRequestVo)throws Exception{

        log.debug("###syncRegister.do ### 동기화 경로 등록 호출 ###");

        //1. 요청 파라미터 변수에 저장
        String srcpath = regRequestVo.getSrcpath().trim();
        String despath = regRequestVo.getDespath().trim();
        String logpath = regRequestVo.getLogpath().trim();
        String remark = regRequestVo.getRemark().trim();

        //2. DB에 매핑할 객체 생성
        TopDirInfoVo topDirInfoVo = TopDirInfoVo.builder().
                srcTopDir(srcpath).
                dstTopDir(despath).
                logDir(logpath).
                procStat("NO").  //초기 값 미처리
                remark(remark).
                build();

        String resp = null;
        try {
            resp = filePathService.initRegister(topDirInfoVo);

        } catch (Exception e) {
            e.printStackTrace();

        }
        log.debug("■■■■■ 동기화경로관리 initRegister.do resp : " + resp);
        return resp;
    }

    /**
     * 등록된 동기화 경로 수정
     * @Method Name   : register
     * @Date / Author : 2022. 7. 20.  leedohyun
     * @param model
     * @param req
     * @return
     * @throws Exception
     * @History
     * 2022.07.20 leedohyun
     */
    @RequestMapping(value = "/modifyPath.do", method = RequestMethod.POST)
    public @ResponseBody String modifyPath(Model model, HttpServletRequest req, RegRequestVo regRequestVo)throws Exception{

        log.debug("###modfyPath.do ### 등록된 동기화 경로 수정 호출 ###");

        //1. 요청 파라미터 변수에 저장
        int id = Integer.parseInt(regRequestVo.getId());
        String srcpath = regRequestVo.getSrcpath();
        String despath = regRequestVo.getDespath();
        String logpath = regRequestVo.getLogpath();
        String remark = regRequestVo.getRemark();

        //2. DB에 매핑할 객체 생성
        TopDirInfoVo topDirInfoVo = TopDirInfoVo.builder().
                syncId(id).
                srcTopDir(srcpath).
                dstTopDir(despath).
                logDir(logpath).
                procStat("NO").
                remark(remark).
                build();

        String resp = null;
        try {
            //3. 수정 서비스 호출
            resp = filePathService.modifyPath(topDirInfoVo);

        }catch (Exception e) {
            e.printStackTrace();
            log.error("### FilePathController modifyPath 에러");
        }

        log.debug("### modifyPath.do resp : " + resp);
        return resp;
    }



    /**
     * 등록된 동기화 경로 삭제
     * @Method Name   : delPath
     * @Date / Author : 2022. 7. 20.  leedohyun
     * @param model
     * @param req
     * @return
     * @throws Exception
     * @History
     * 2022.07.20 leedohyun
     */
    @ResponseBody
    @RequestMapping(value = "/delDir.do", method = RequestMethod.POST)
    public Object delPath(Model model, HttpServletRequest req)throws Exception{

        log.debug("###delDir.do ### 등록된 동기화 경로 삭제 호출 ###");


        Map<String, Object> resultMap = new HashMap<String, Object>();
        // ajax를 통해 넘어온 배열 데이터 선언
        String[] chkId = req.getParameterValues("chkid");
        try {

            //유효성 검사
            if(chkId !=null && chkId.length > 0) {
                for(int i = 0; i< chkId.length; i++) {
                    System.out.println("ajax 배열 원소 : " + i +" : "+ chkId[i].trim());
                }

                filePathService.delPath(chkId);


                resultMap.put("result", "success");
            } else {
                resultMap.put("result", "false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }


    /**
     * 초기 환경 구성 (오더 테이블에 Insert)
     * @Method Name   : initConfig
     * @Date / Author : 2022. 7. 21.  leedohyun
     * @param req
     * @param model
     * @return
     * @throws Exception
     * @History
     * 2022.07.21 leedohyun
     */

    @RequestMapping(value = "/initConfig.do", method = RequestMethod.POST)
    @ResponseBody
    public int initConfig(Model model, HttpServletRequest req, @RequestBody List<RegRequestVo> regRequestVo) throws Exception {

        log.debug("###modPopUp.do ### 초기 환경 구성 호출 ###");

        log.debug(regRequestVo.toString());

        return filePathService.initStatConf(regRequestVo);
    }

    @RequestMapping(value = "/testlee.do", method = RequestMethod.POST)
    public ResponseEntity test(){
        return new ResponseEntity(new RegRequestVo(),HttpStatus.FORBIDDEN);
    }

}

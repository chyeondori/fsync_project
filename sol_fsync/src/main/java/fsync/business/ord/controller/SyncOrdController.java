package fsync.business.ord.controller;

import fsync.business.ord.service.SyncOrdService;
import fsync.business.ord.vo.SyncOrdVO;
import fsync.business.stat.service.SyncStatService;
import fsync.business.stat.vo.SyncStatusVO;
import fsync.business.stat.vo.TopDirVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.*;


/**
 *
 * @FileName     : SyncStatusController.java
 * @Date         : 2022. 7. 19.
 * @Author       : LimJinsu
 * @Description  : 동기화 현황 컨트롤러
 * @History
 * =======================================================
 *   DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.19      LimJinsu		최초생성
 *
 */
@Slf4j
@Controller
public class SyncOrdController {

    @Autowired
    SyncOrdService soService;

    /**
     * 에이전트 작업 조회 화면 호출
     * @Method Name   : syncStatusCheck
     * @Date / Author : 2022. 7. 21.  LimJinsu
     * @return
     * @throws Exception
     * @History
     * 2022.07.21 LimJinsu 최초생성
     */
    @RequestMapping(value="/syncOrder.do", method= RequestMethod.GET)
    public String syncOrderCheck(Model model) throws Exception {
        List<SyncOrdVO> agentOrderList = soService.getAgentOrderList();
        /*해쉬셋을 이용해서 셀렉트박스에 들어갈 이름리스트중복을 없애서 리턴*/
        HashSet<String> agentNmList1 = new HashSet<String>();
        for(int i =0;i< agentOrderList.size();i++){
            agentNmList1.add(agentOrderList.get(i).getServerNm());
        }
        ArrayList<String> agentNmList = new ArrayList<>(agentNmList1);
        Collections.sort(agentNmList);
        model.addAttribute("agentOrderList", agentOrderList);
        model.addAttribute("agentNmList", agentNmList);
        return "stat/syncOrder";
    }

    /**
     * ajax - 동기화 작업 취소
     * @Method Name   : syncOrderCancel
     * @Date / Author : 2022. 7. 21.  LimJinsu
     * @return
     * @throws Exception
     * @History
     * 2022.07.21 LimJinsu 최초생성
     */
    @RequestMapping(value= "/ajax/syncOrderCancel.do", method= RequestMethod.POST)
    @ResponseBody
    public boolean syncOrderCancel(HttpServletRequest request, @RequestParam(value = "wkOrdIdList[]") List<String> wkOrdIdList) throws Exception {
        log.debug("########syncOrderCancel######## wkOrdIdList : " + wkOrdIdList);
        // todo : 현재 : id 1개당 1번씩 insert -> 추후 : idList로 한번에 insert
        int success = 0;
        for(String wkOrdId : wkOrdIdList) {
            System.out.println(wkOrdId);
            success += soService.syncOrdCancel(wkOrdId);
        }

        return success == wkOrdIdList.size();
    }
    /**
     * ajax - 동기화 작업 삭제
     * @Method Name   : syncOrderCancel
     * @Date / Author : 2022. 8. 03.  Moonjunhyeon
     * @return
     * @throws Exception
     * @History
     * 2022. 8. 03.  Moonjunhyeon 최초생성
     */
    @RequestMapping(value= "/ajax/syncOrderDelete.do", method= RequestMethod.POST)
    @ResponseBody
    public boolean syncOrderDelete(@RequestBody List<Map<String, Object>>  idList) throws Exception {
        log.debug("########syncOrderDelete########  idList: " + idList);
        int success = 0;
        for(int i=0;i<idList.size();i++) {
            String delId = idList.get(i).get("id").toString();
            String seq = idList.get(i).get("seq").toString();
            if(soService.syncOrdDelete(delId,seq)!=0 )
                success += 1;
        }
        return success == idList.size();
    }
    /**
     * ajax -조건에 따른 작업조회
     * @Method Name   : syncOrderCancel
     * @Date / Author : 2022. 08. 03.  Moon junhyeon
     * @return
     * @throws Exception
     * @History
     * 2022. 08. 03.  Moon junhyeon 최초생성
     */
    @RequestMapping(value = "/ajax/selectView.do", method = RequestMethod.POST)
    @ResponseBody
    public List<SyncOrdVO> getSelectOrderList(HttpServletRequest request, @Param("serverNm")String serverNm,@Param("workCd") String workCd) throws Exception{
        log.debug("########getSelectOrderList######## TEST!!!!! : " + serverNm+workCd);
        if(serverNm.equals("all")){
            if(workCd.equals("all")){
                return soService.getAgentOrderList();
            }
        }

        return soService.getSelectOrderList(serverNm, workCd);
    }

    /**
     * ajax -서버이름에 따른 작업코드조회
     * @Method Name   : getSelectOrdCdList
     * @Date / Author : 2022. 08. 06.  Moon junhyeon
     * @return
     * @throws Exception
     * @History
     * 2022. 08. 06.  Moon junhyeon 최초생성
     */
    @RequestMapping(value="/ajax/getOrderCd.do", method= RequestMethod.POST)
    @ResponseBody
    public List<SyncOrdVO> getSelectOrdCdList(Model model, String serverNm) throws Exception {
        log.debug("########getSelectOrdCdList######## TEST!!!!! : " + serverNm);
        List<SyncOrdVO> workCdList = soService.getSelectOrdCdList(serverNm);
        model.addAttribute("workCdList", workCdList);
        return workCdList;
    }

}

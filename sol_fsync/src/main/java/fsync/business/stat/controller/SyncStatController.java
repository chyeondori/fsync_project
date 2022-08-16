package fsync.business.stat.controller;

import fsync.business.ord.vo.SyncOrdVO;
import fsync.business.stat.vo.SyncStatusVO;
import fsync.business.stat.vo.TopDirVO;
import fsync.business.ord.service.SyncOrdService;
import fsync.business.stat.service.SyncStatService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;


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
public class SyncStatController {

    @Autowired
    SyncStatService syncStatService;

    @Autowired
    SyncOrdService soService;

    /**
     * 동기화 현황 조회 화면 호출
     * @Method Name   : syncStatusCheck
     * @Date / Author : 2022. 7. 19.  LimJinsu
     * @return
     * @throws Exception
     * @History
     * 2022.07.19 LimJinsu 최초생성
     */
    @RequestMapping(value="/syncStat.do", method= RequestMethod.GET)
    public String syncStatusCheck(Model model) throws Exception {
        List<TopDirVO> topDirVOList = syncStatService.getTopDirList();
        List<SyncStatusVO> syncStatusVOList = syncStatService.getTopAllStatList();

        model.addAttribute("topDirVOList", topDirVOList);
        model.addAttribute("syncStatusVOList", syncStatusVOList);
        return "stat/syncStat";
    }

    /**
     * ajax - select change event
     * @Method Name   : setDepth
     * @Date / Author : 2022. 7. 20.  LimJinsu
     * @return
     * @throws Exception
     * @History
     * 2022.07.20 LimJinsu 최초생성
     */
    @RequestMapping(value = "/ajax/depthSelectEvent.do", method = RequestMethod.POST)
    @ResponseBody
    public List<SyncStatusVO> setDepth(HttpServletRequest request, String dirId) {
        log.debug("########setDepth######## pDirId : " + dirId);
        return syncStatService.getAllStatList(dirId);
    }


}

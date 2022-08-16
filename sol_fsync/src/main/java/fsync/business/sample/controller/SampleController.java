package fsync.business.sample.controller;

import fsync.business.sample.service.SampleService;
import fsync.business.sample.vo.SampleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


/**
 * @FileName : SampleController.java
 * @Date : 2021. 9. 24.
 * @Author : kimindeok
 * @Description : 샘플컨트롤러
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2021.09.24      kimindeok		최초생성
 */
@Slf4j
@Controller
public class SampleController {

    @Autowired
    SampleService sampleService;

    /**
     * 모바일 메인 화면 호출
     *
     * @param model
     * @param req
     * @return
     * @throws Exception
     * @Method Name   : sampleMain
     * @Date / Author : 2021. 9. 24.  kimindeok
     * @History 2021.09.24 kimindeok 최초생성
     */
    @RequestMapping(value = "/sampleMain2.do", method = RequestMethod.GET)
    public String sampleMain(HttpServletRequest req) throws Exception {
        log.debug("sampleMain.do start");
        SampleVO sampleVO = new SampleVO();
        sampleService.printQ();
//        log.debug(mapper.selSampleList().toString());
        return "sample/sampleMain";

    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test2222() {
        return "fileSyncList";

    }

}
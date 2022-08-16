package fsync.business.sample.service.impl;

import fsync.business.sample.mapper.SampleMapper;
import fsync.business.sample.service.SampleService;
import fsync.business.sample.vo.SampleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @FileName : SampleServiceImpl.java
 * @Date : 2021. 9. 24.
 * @Author : kimindeok
 * @Description : Sample ServiceImpl
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2021.09.24       kimindeok       최초생성
 */
@Slf4j
@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    SampleMapper sampleMapper;

    /**
     * 샘플리스트 조회
     *
     * @param sampleVO
     * @return List
     * @throws Exception
     * @Method Name   : selSampleList
     * @Date / Author : 2021. 7. 30.  kimindeok
     * @History 2021.07.30 최초생성
     */
    @Override
    public List<SampleVO> selSampleList(SampleVO sampleVO) throws Exception {
        return null;
    }

    @Override
    public void printQ() throws Exception {
        List<SampleVO> sampleVO = new ArrayList<>();
        sampleVO = sampleMapper.selSampleList();
        log.info(sampleVO.toString());

        log.info("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
    }


}

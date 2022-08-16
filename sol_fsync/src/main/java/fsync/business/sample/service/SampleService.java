package fsync.business.sample.service;


import fsync.business.sample.vo.SampleVO;

import java.util.List;

/**
 * @FileName : SampleService.java
 * @Date : 2021. 9. 24.
 * @Author : kimindeok
 * @Description : Sample Service
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2021.09.24       kimindeok       최초생성
 */
public interface SampleService {
    /**
     * 샘플리스트 조회
     *
     * @param sampleVO
     * @return List
     * @throws Exception
     * @Method Name   : selSampleList
     * @Date / Author : 2021. 09. 24.  kimindeok
     * @History 2021.09.24 kimindeok 최초생성
     */
    public List<SampleVO> selSampleList(SampleVO sampleVO) throws Exception;

    public void printQ() throws Exception;

}

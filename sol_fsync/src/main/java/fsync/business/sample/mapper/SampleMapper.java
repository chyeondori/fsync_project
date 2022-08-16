package fsync.business.sample.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fsync.business.sample.vo.SampleVO;

import java.util.List;

/**
 *
 * @FileName     : SampleMapper.java
 * @Date         : 2021. 9. 24.
 * @Author       : kimindeok
 * @Description  : Sample Mapper
 * @History
 * =======================================================
 *   DATE			AUTHOR			NOTE
 * =======================================================
 * 2021.09.24.       kimindeok       최초생성
 *
 */
@Mapper
public interface SampleMapper {
    /**
     * 샘플리스트 조회
     * @Method Name   : selSampleList
     * @Date / Author : 2021. 9. 24.  kimindeok
     * @param sampleVO
     * @return List
     * @throws Exception
     * @History
     * 2021.09.24 kimindeok 최초생성
     */
    public List<SampleVO> selSampleList();
}

package fsync.business.ord.service;

import fsync.business.ord.vo.SyncOrdVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SyncOrdService {
    /**
     * 에이전트 작업 리스트 조회
     *
     * @param
     * @return List<SyncOrderVO>
     * @throws Exception
     * @Method Name   : getAgentOrderList
     * @Date / Author : 2022. 07. 21.  LimJinsu
     * @History 2022.07.21 LimJinsu 최초생성
     */
    public List<SyncOrdVO> getAgentOrderList() throws Exception;

    /**
     * 동기화 작업 취소
     *
     * @param
     * @return int
     * @throws Exception
     * @Method Name   : syncOrdCancel
     * @Date / Author : 2022. 07. 21.  LimJinsu
     * @History 2022.07.21 LimJinsu 최초생성
     */
    public int syncOrdCancel(String wkOrdId) throws Exception;

    /**
     * 동기화 작업 삭제
     *
     * @param
     * @return int
     * @throws Exception
     * @Method Name   : syncOrdCancel
     * @Date / Author : 2022. 08. 01.  Moonjunhyeon
     * @History 2022. 08. 01.  Moonjunhyeon 최초생성
     */
    public int syncOrdDelete(@Param("delId") String delId ,@Param("seq") String seq) throws Exception;
    /**
     * 셀렉트박스 값에 따라 조회리스트 받아오기
     *
     * @param
     * @return list
     * @throws Exception
     * @Method Name   : getSelectOrderList
     * @Date / Author : 2022. 08. 01.  Moonjunhyeon
     * @History 2022. 08. 01.  Moonjunhyeon 최초생성
     */
    public List<SyncOrdVO> getSelectOrderList(@Param("serverNm") String serverNm, @Param("workCd")String workCd) throws Exception;
    /**
     * 두번째 셀렉트박스에 들어갈 작업코드를 서버이름에따라 받아오기
     *
     * @param
     * @return list
     * @throws Exception
     * @Method Name   : getSelectOrdCdList
     * @Date / Author : 2022. 08. 01.  Moonjunhyeon
     * @History 2022. 08. 01.  Moonjunhyeon 최초생성
     */
    public List<SyncOrdVO> getSelectOrdCdList(String serverNm) throws Exception;

}

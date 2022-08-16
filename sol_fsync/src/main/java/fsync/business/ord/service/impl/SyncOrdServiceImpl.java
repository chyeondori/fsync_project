package fsync.business.ord.service.impl;

import fsync.business.ord.mapper.SyncOrdMapper;
import fsync.business.ord.vo.SyncOrdVO;
import fsync.business.ord.service.SyncOrdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SyncOrdServiceImpl implements SyncOrdService {

    @Autowired
    SyncOrdMapper soMapper;
    /**
     * 에이전트 작업 리스트 조회
     * @Method Name   : getAgentOrderList
     * @Date / Author : 2022. 07. 21.  LimJinsu
     * @param
     * @return List<SyncOrderVO>
     * @throws Exception
     * @History
     * 2022.07.21 LimJinsu 최초생성
     */
    @Override
    public List<SyncOrdVO> getAgentOrderList() throws Exception {
        return soMapper.getAgentOrderList();
    }

    /**
     * 동기화 작업 취소
     * @Method Name   : syncOrdCancel
     * @Date / Author : 2022. 07. 21.  LimJinsu
     * @param
     * @return int
     * @throws Exception
     * @History
     * 2022.07.21 LimJinsu 최초생성
     */
    @Override
    public int syncOrdCancel(String wkOrdId) throws Exception {
        System.out.println(wkOrdId);
        return soMapper.syncOrdCancel(wkOrdId);
    }

    /**
     * 동기화 작업 삭제
     * @Method Name   : syncOrdDelete
     * @Date / Author : 2022. 08. 01.  Moonjunhyeon
     * @param
     * @return int
     * @throws Exception
     * @History
     * 2022. 08. 01.  Moonjunhyeon 최초생성
     */
    @Override
    public int syncOrdDelete(@Param("delId") String delId ,@Param("seq") String seq) throws Exception {
        log.debug("In orderserviceimpl################삭제문 수행중 id :" + delId);
        return soMapper.syncOrdDelete(delId , seq);
    }

    @Override
    public List<SyncOrdVO> getSelectOrderList(String serverNm,String workCd) throws Exception{
        System.out.println(workCd);
        return soMapper.getSelectOrderList(serverNm,workCd);
    }

    @Override
    public List<SyncOrdVO> getSelectOrdCdList(String serverNm) throws Exception{
        return soMapper.getSelectOrdCdList(serverNm);
    }

}

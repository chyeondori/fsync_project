package fsync.business.stat.service.impl;

import fsync.business.stat.mapper.SyncStatMapper;
import fsync.business.stat.service.SyncStatService;
import fsync.business.stat.vo.SyncStatusVO;
import fsync.business.stat.vo.TopDirVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @FileName     : SampleServiceImpl.java
 * @Date         : 2022. 7. 19.
 * @Author       : LimJinsu
 * @Description  : Sample ServiceImpl
 * @History
 * =======================================================
 *   DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.19       LimJinsu       최초생성
 *
 */
@Slf4j
@Service
public class SyncStatServiceImpl implements SyncStatService {

    @Autowired
    SyncStatMapper syncStatMapper;

    /**
     * 최상위 디렉터리 목록 조회
     * @Method Name   : getTopDirList
     * @Date / Author : 2022. 07. 19.  LimJinsu
     * @param
     * @return List
     * @History
     * 2022.07.19 LimJinsu 최초생성
     */
    @Override
    public List<TopDirVO> getTopDirList() throws Exception {
        return syncStatMapper.getTopDirList();
    }

    /**
     * 최상위 디렉터리 현황 목록 조회 조회
     * @Method Name   : getTopDirList
     * @Date / Author : 2022. 07. 19.  LimJinsu
     * @param
     * @return List
     * @History
     * 2022.07.19 LimJinsu 최초생성
     */
    @Override
    public List<SyncStatusVO> getTopAllStatList() throws Exception {
        return syncStatMapper.getTopAllDirStatList();
    }

    /**
     * 하위 현황 목록 조회
     * @Method Name   : getAllStatList
     * @Date / Author : 2022. 07. 20.  LimJinsu
     * @param pDirId
     * @return List
     * @throws Exception
     * @History
     * 2022.07.20 LimJinsu 최초생성
     * 2022.07.26 MoonJunhyeon else문 쿼리 수정 및 함수통합.
     */
    @Override
    public List<SyncStatusVO> getAllStatList(String pDirId) {
        List<SyncStatusVO> list;
        if(pDirId.equals("all")) {      // 최상위 뎁스 전체 선택시...
            list = syncStatMapper.getTopAllDirStatList();
        } else  {
            list = syncStatMapper.getAllDirList(pDirId);
        }
        return list;
    }
}

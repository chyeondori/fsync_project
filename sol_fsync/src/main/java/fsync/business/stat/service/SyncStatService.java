package fsync.business.stat.service;


import fsync.business.stat.vo.SyncStatusVO;
import fsync.business.stat.vo.TopDirVO;

import java.util.List;

/**
 *
 * @FileName     : SyncStatusService.java
 * @Date         : 2022. 7. 19.
 * @Author       : LimJinsu
 * @Description  : SyncStatus Service
 * @History
 * =======================================================
 *   DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.19       LimJinsu       최초생성
 *
 */
public interface SyncStatService {

    /**
     * 최상위 디렉터리 목록 조회
     * @Method Name   : getTopDirList
     * @Date / Author : 2022. 07. 19.  LimJinsu
     * @param
     * @return List
     * @throws Exception
     * @History
     * 2022.07.19 LimJinsu 최초생성
     */
    public List<TopDirVO> getTopDirList() throws Exception;

    /**
     * 최상위 디렉터리 현황 목록 조회 조회
     * @Method Name   : getTopAllStatList
     * @Date / Author : 2022. 07. 19.  LimJinsu
     * @param
     * @return List
     * @throws Exception
     * @History
     * 2022.07.19 LimJinsu 최초생성
     */
    public List<SyncStatusVO> getTopAllStatList() throws Exception;

    /**
     * 하위 현황 목록 조회
     * @Method Name   : getAllStatList
     * @Date / Author : 2022. 07. 20.  LimJinsu
     * @param String
     * @return List
     * @throws Exception
     * @History
     * 2022.07.20 LimJinsu 최초생성
     */
    public List<SyncStatusVO> getAllStatList(String pDirId);
}

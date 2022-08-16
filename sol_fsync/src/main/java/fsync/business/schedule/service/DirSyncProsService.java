package fsync.business.schedule.service;


import fsync.business.schedule.vo.DirSyncProsVO;

import java.util.List;

/**
 * @FileName : DirSyncdProsService.java
 * @Date : 2022. 7. 20.
 * @Author : mhj
 * @Description : DirSyncProsService
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.20       mhj       최초생성
 */
public interface DirSyncProsService {
    /**
     * 디렉토리별 하위 동기화 완료 파일갯수, 파일용량 조회
     *
     * @param
     * @return List
     * @throws Exception
     * @Method Name   : selDirSyncdList
     * @Date / Author : 2022. 07. 20.  mhj
     * @History 2022.07.20 mhj 최초생성
     */
    public List<DirSyncProsVO> selDirSyncList() throws Exception;

    /**
     * 디렉토리별 하위 동기화 완료 파일갯수, 파일용량 업데이트
     *
     * @param dirSyncProsVO
     * @return int
     * @throws Exception
     * @Method Name   : updateDirSyncdList
     * @Date / Author : 2022. 07. 20.  mhj
     * @History 2022.07.20 mhj 최초생성
     */
    public int updateDirSyncList(List<DirSyncProsVO> dirSyncProsVO) throws Exception;

}

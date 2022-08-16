package fsync.business.schedule.service.impl;

import fsync.business.schedule.mapper.DirSyncdProsMapper;
import fsync.business.schedule.service.DirSyncProsService;
import fsync.business.schedule.vo.DirSyncProsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FileName : DirSyncProsServiceImpl.java
 * @Date : 2022. 7. 21.
 * @Author : mhj
 * @Description : 폴더별 동기화 건수,용량 업데이트 관련 서비스
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.21       mhj       최초생성
 */
@Slf4j
@Service
public class DirSyncProsServiceImpl implements DirSyncProsService {

    @Autowired
    DirSyncdProsMapper dirSyncProsMapper;

    /**
     * 디렉토리별 하위 동기화 완료 파일갯수, 파일용량 조회(갱신할 대상 조회)
     *
     * @param
     * @return List
     * @throws Exception
     * @Method Name   : selDirSyncdList
     * @Date / Author : 2022. 07. 20.  mhj
     * @History 2022.07.20 mhj 최초생성
     */
    @Override
    public List<DirSyncProsVO> selDirSyncList() throws Exception {
        return dirSyncProsMapper.selDirSyncList();
    }

    /**
     * 디렉토리별 하위 동기화 완료 파일갯수, 파일용량 업데이트
     *
     * @param dirSyncVO
     * @return int
     * @throws Exception
     * @Method Name   : updateDirSyncdList
     * @Date / Author : 2022. 07. 20.  mhj
     * @History 2022.07.20 mhj 최초생성
     */
    @Override
    public int updateDirSyncList(List<DirSyncProsVO> dirSyncVO) throws Exception {
        return dirSyncProsMapper.updateDirSyncList(dirSyncVO);
    }

}

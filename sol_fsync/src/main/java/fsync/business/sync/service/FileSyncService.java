package fsync.business.sync.service;

import fsync.business.serverInfo.vo.ServerInfoVO;
import fsync.business.sync.vo.*;

import java.util.List;

/**
 * @FileName : FileSyncService.java
 * @Date : 2022. 7. 19.
 * @Author : kimhyeonjung, ysy
 * @Description : 파일 동기화 선택 서비스
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.19   kimhyeonjung    최초생성
 * 2022.08.02   ysy             변경분 동기화 기능 추가
 */
public interface FileSyncService {
    /**
     * 트리 JSON 생성
     *
     * @param syncId
     * @return List
     * @throws Exception
     * @Method Name   : makeDirJson
     * @Date / Author : 2022. 7. 20.  kimhyeonjung
     * @History 2022.07.20 kimhyeonjung 최초생성
     */
    public List<DirTreeJsonVO> makeDirJson(String syncId) throws Exception;

    /**
     * 최상위 디렉토리 정보 조회
     *
     * @param
     * @return List
     * @Method Name   : selectTopDir
     * @Date / Author : 2022. 7. 20.  kimhyeonjung
     * @History 2022.07.20 kimhyeonjung 최초생성
     */
    public List<TopDirInfoVO> selectTopDir() throws Exception;

    /**
     * 디렉토리, 파일 전체 조회
     *
     * @param dirId, nowPage, maxLine
     * @return List
     * @Method Name   : selectAllFiles
     * @Date / Author : 2022. 7. 20.  kimhyeonjung
     * @History 2022.07.20 kimhyeonjung 최초생성
     */
    public List<AllFileListVO> selectAllFiles(String dirId, int nowPage, int maxLine) throws Exception;

    /**
     * 서버정보(에이전트) Select
     *
     * @param
     * @return List
     * @Method Name   : selectServerInfo
     * @Date / Author : 2022. 7. 22.  kimhyeonjung
     * @History 2022.07.22 kimhyeonjung 최초생성
     */
    public List<ServerInfoVO> selectServerInfo() throws Exception;

    /**
     * 디렉토리, 파일 크기 검증 insert
     *
     * @param syncVO
     * @return int
     * @Method Name   : sizeVerification
     * @Date / Author : 2022. 7. 21.  kimhyeonjung
     * @History 2022.07.21 kimhyeonjung 최초생성
     */
    public int sizeVerification(List<SyncVO> syncVO) throws Exception;

    /**
     * 디렉토리, 파일 동기화 insert
     *
     * @param syncVO
     * @return int
     * @Method Name   : insertSyncOrder
     * @Date / Author : 2022. 7. 21.  kimhyeonjung
     * @History 2022.07.21 kimhyeonjung 최초생성
     */
    public int insertSyncOrder(List<SyncVO> syncVO) throws Exception;

    /**
     * 변경분 동기화 업데이트 insert
     * @param syncVO
     * @return int
     * @Method Name   : insertUpdateSync
     * @Date / Author : 2022.08.02 yoonsuyeon
     * @History 2022.08.02 ysy 최초생성
     */
    public int insertUpdateSync(List<SyncVO> syncVO) throws Exception;
}

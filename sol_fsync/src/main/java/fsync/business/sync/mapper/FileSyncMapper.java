package fsync.business.sync.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fsync.business.serverInfo.vo.ServerInfoVO;
import fsync.business.sync.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @FileName : FileSyncMapper.java
 * @Date : 2022. 7. 19.
 * @Author : kimhyeonjung, ysy
 * @Description : 파일 동기화 선택 mapper
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.19   kimhyeonjung    최초생성
 * 2022.08.02   ysy             변경분 동기화 기능 추가
 */
@Mapper
public interface FileSyncMapper {
    /**
     * 디렉토리 정보 조회 Mapper
     *
     * @param syncId
     * @return List
     * @Method Name   : selectDirTreeInfo
     * @Date / Author : 2022. 7. 19.  kimhyeonjung
     * @History 2022.07.19 kimhyeonjung 최초생성
     */
    public List<DirTreeInfoVO> selectDirTreeInfo(String syncId);

    /**
     * 최상위 디렉토리 정보 조회 Mapper
     *
     * @param
     * @return List
     * @Method Name   : selectTopDir
     * @Date / Author : 2022. 7. 20.  kimhyeonjung
     * @History 2022.07.20 kimhyeonjung 최초생성
     */
    public List<TopDirInfoVO> selectTopDir();


    /**
     * 디렉토리, 파일 전체 조회 Mapper
     *
     * @param searchMap
     * @return List
     * @Method Name   : selectAllFiles
     * @Date / Author : 2022. 7. 20.  kimhyeonjung
     * @History 2022.07.20 kimhyeonjung 최초생성
     */
    public List<AllFileListVO> selectAllFiles(Map<String, Object> searchMap);


    /**
     * 서버정보(에이전트) Select Mapper
     *
     * @param
     * @return List
     * @Method Name   : selectServerInfo
     * @Date / Author : 2022. 7. 22.  kimhyeonjung
     * @History 2022.07.22 kimhyeonjung 최초생성
     */
    public List<ServerInfoVO> selectServerInfo();

    /**
     * 검증 작업 추가
     *
     * @param orderMap
     * @return int
     * @Method Name   : insertVerificationOrder
     * @Date / Author : 2022. 7. 22.  kimhyeonjung
     * @History 2022.07.22 kimhyeonjung 최초생성
     */
    public int insertVerificationOrder(List<SyncVO> orderMap);


    /**
     * 동기화 작업 추가
     *
     * @param orderMap
     * @return int
     * @Method Name   : insertSyncOrder
     * @Date / Author : 2022. 7. 22.  kimhyeonjung
     * @History 2022.07.22 kimhyeonjung 최초생성
     */
    public int insertSyncOrder(List<SyncVO> orderMap);

    /**
     * 변경분 동기화 업데이트 추가
     * @param orderMap
     * @return int
     * @Method Name   : insertUpdateSync
     * @Date / Author : 2022.08.02  yoonsuyeon
     * @History 2022.08.02 ysy 최초생성
     */
    public int insertUpdateSync(List<SyncVO> orderMap);

    /**
     * 작업 후 업데이트
     *
     * @param orderMap
     * @return int
     * @Method Name   : updateInfo
     * @Date / Author : 2022. 7. 22.  kimhyeonjung
     * @History 2022.07.22 kimhyeonjung 최초생성
     */
    public int updateInfo(List<SyncVO> orderMap);

    /**
     * 변경분 동기화 작업 후 업데이트
     *
     * @param orderMap
     * @return int
     * @Method Name   : updateChangeInfo
     * @Date / Author : 2022. 08. 08  ysy
     * @History 2022. 08. 08  ysy 최초생성
     */
    public int updateChangeInfo(List<SyncVO> orderMap);


}

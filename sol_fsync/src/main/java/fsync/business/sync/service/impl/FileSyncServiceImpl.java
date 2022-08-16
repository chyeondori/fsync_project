package fsync.business.sync.service.impl;

import fsync.business.serverInfo.vo.ServerInfoVO;
import fsync.cmm.utils.StringUtils;
import fsync.business.sync.mapper.FileSyncMapper;
import fsync.business.sync.vo.*;

import fsync.business.sync.service.FileSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class FileSyncServiceImpl implements FileSyncService {

    @Autowired
    FileSyncMapper fileSyncMapper;

    /**
     * jsTree에서 사용할 Json 생성 - 디렉토리 조회
     *
     * @param syncId
     * @return List
     * @Method Name   : seletDirTreeInfo
     * @Date / Author : 2022. 7. 19.  kimhyeonjung
     * @History 2022.07.19 kimhyeonjung 최초생성
     */
    @Override
    public List<DirTreeJsonVO> makeDirJson(String syncId) throws Exception{
        //1. db조회
        List<DirTreeInfoVO> dirTreeList = fileSyncMapper.selectDirTreeInfo(syncId);

        DirTreeJsonVO dirTreeJsonVO = new DirTreeJsonVO();
        List<DirTreeJsonVO> resJson = new ArrayList<>();
        //2. 객체 매핑
        if (dirTreeList.size() != 0) {

            for (int i = 0; i < dirTreeList.size(); i++) {

                //부모 디렉토리가 없을경우 (최상위 디렉토리일 경우) #으로 세팅
                if (StringUtils.isNullOrEmpty(dirTreeList.get(i).getPDirId())) {
                    dirTreeList.get(i).setPDirId("#");
                }
                //노드 생성
                DirTreeJsonVO node = DirTreeJsonVO.builder()
                        .id(dirTreeList.get(i).getDirId())
                        .parent(dirTreeList.get(i).getPDirId())
                        .text(dirTreeList.get(i).getCDirNm().replace("/", "").replace("\\", ""))
                        .build();
                //노드 추가
                resJson.add(node);
            }
        } else {
            log.error("잘못된 syncId (값없음)");
        }
        log.debug("#########" + resJson);
        return resJson;
    }

    /**
     * 최상위 디렉토리 정보 조회
     *
     * @param
     * @return List
     * @Method Name   : selectTopDir
     * @Date / Author : 2022. 7. 20.  kimhyeonjung
     * @History 2022.07.20 kimhyeonjung 최초생성
     */
    @Override
    public List<TopDirInfoVO> selectTopDir() throws Exception{
        return fileSyncMapper.selectTopDir();
    }

    /**
     * 디렉토리, 파일 전체 조회
     *
     * @param
     * @return List
     * @Method Name   : selectAllFiles
     * @Date / Author : 2022. 7. 20.  kimhyeonjung
     * @History 2022.07.20 kimhyeonjung 최초생성
     */
    public List<AllFileListVO> selectAllFiles(String dirId, int nowPage, int maxLine) throws Exception{

        int startLine = nowPage * maxLine;

        Map<String, Object> searchMap = new HashMap<>();

        searchMap.put("dirId", dirId);
        searchMap.put("startLine", startLine);
        searchMap.put("maxLine", maxLine);

        List<AllFileListVO> asd = fileSyncMapper.selectAllFiles(searchMap);
        log.debug("########### List<AllFileListVO> asd : " + asd.toString());
        return asd;
    }

    /**
     * 서버정보(에이전트) Select
     *
     * @param
     * @return List
     * @Method Name   : selectServerInfo
     * @Date / Author : 2022. 7. 22.  kimhyeonjung
     * @History 2022.07.22 kimhyeonjung 최초생성
     */
    @Override
    public List<ServerInfoVO> selectServerInfo() throws Exception{
        return fileSyncMapper.selectServerInfo();
    }

    /**
     * 디렉토리, 파일 크기 검증 insert
     *
     * @param
     * @return List
     * @Method Name   : sizeVerification
     * @Date / Author : 2022. 7. 21.  kimhyeonjung
     * @History 2022.07.21 kimhyeonjung 최초생성
     */
    @Override
    public int sizeVerification(List<SyncVO> syncVO) throws Exception{
        //디렉토리, 파일 구분
//        Map<String,Object> map = new HashMap<>();
//        map.put("syncVO",syncVO);
        int result = fileSyncMapper.insertVerificationOrder(syncVO);
        fileSyncMapper.updateInfo(syncVO);
        return result;
    }

    /**
     * 디렉토리, 파일 동기화 insert
     *
     * @param
     * @return List
     * @Method Name   : sizeVerification
     * @Date / Author : 2022. 7. 21.  kimhyeonjung
     * @History 2022.07.21 kimhyeonjung 최초생성
     */
    @Override
    public int insertSyncOrder(List<SyncVO> syncVO) throws Exception{
        int result = fileSyncMapper.insertSyncOrder(syncVO);
        fileSyncMapper.updateInfo(syncVO);
        return result;
    }

    /**
     * 변경분 동기화 업데이트 insert
     * @param
     * @return List
     * @Method Name   : insertUpdateSync
     * @Date / Author : 2022.08.02  yoonsuyeon
     * @History 2022.08.02 ysy 최초생성
     */
    @Override
    public int insertUpdateSync(List<SyncVO> syncVO) throws Exception{
        int result = fileSyncMapper.insertUpdateSync(syncVO);
        fileSyncMapper.updateChangeInfo(syncVO);
        return result;
    }

}

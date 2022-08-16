package fsync.business.path.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fsync.business.path.vo.RegRequestVo;
import fsync.business.path.vo.TopDirInfoVo;


import java.util.List;

@Mapper
public interface FilePathMapper {

    /**등록된 동기화 경로 관리**/
    public List<TopDirInfoVo> topDirList();

    /**동기화 경로 등록**/
    public int initRegister(TopDirInfoVo topDirInfoVo);

    /**동기화 경로 중복 검사**/
    public int checkPathDir(TopDirInfoVo topDirInfoVo);

    /**해당 경로 조회**/
    public int selectPath(TopDirInfoVo topDirInfoVo);

    /**등록된 동기화 경로 수정**/
    public int modifyPath(TopDirInfoVo topDirInfoVo);

    /**등록된 동기화 경로 삭제**/
    public int delPath(String[] chkId);

    /**초기 환경 구성**/
    public int initStatConf(List<RegRequestVo> regRequestVos);

    /**환경구성 이후 상태업데이트**/
    public int updatePath(List<RegRequestVo> regRequestVos);
}

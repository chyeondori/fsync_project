package fsync.business.schedule.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fsync.business.schedule.vo.DirSyncProsVO;

import java.util.List;

/**
 * @FileName     : DirSyncdProsMapper.java
 * @Date         : 2022. 07. 21.
 * @Author       : mhj
 * @Description  :
 * @History
 * =======================================================
 *   DATE			AUTHOR			NOTE
 *
 * =======================================================
 * 22.07.21       mhj        최초생성
 *
 */
@Mapper
public interface DirSyncdProsMapper {
    public List<DirSyncProsVO> selDirSyncList();

    public int updateDirSyncList(List<DirSyncProsVO> dirSyncProsVO);
}

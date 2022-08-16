package fsync.business.stat.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fsync.business.stat.vo.SyncStatusVO;
import fsync.business.stat.vo.TopDirVO;


import java.util.List;

@Mapper
public interface SyncStatMapper {

    public List<TopDirVO> getTopDirList();

    public List<SyncStatusVO> getTopAllDirStatList();

    public List<SyncStatusVO> getAllDirList(String pDirId);

}

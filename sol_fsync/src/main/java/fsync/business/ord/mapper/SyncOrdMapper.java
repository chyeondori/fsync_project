package fsync.business.ord.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fsync.business.ord.vo.SyncOrdVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SyncOrdMapper {
    public List<SyncOrdVO> getAgentOrderList();
    public int syncOrdCancel(String wkOrdId);
    public int syncOrdDelete(@Param("delId") String delId, @Param("seq")String seq);
    public List<SyncOrdVO> getSelectOrderList(@Param("serverNm") String serverNm,@Param("workCd") String workCd);
    public List<SyncOrdVO> getSelectOrdCdList(String serverNm);

}

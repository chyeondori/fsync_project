package fsync.business.serverInfo.mapper;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import fsync.business.serverInfo.vo.ServerInfoVO;

import java.util.List;

/**
 * @FileName : ServerInfoMapper.java
 * @Date : 2022. 7. 26
 * @Author : ysy
 * @Description : 서버 정보 관리 mapper
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.26       ysy       최초생성
 */

@Mapper
public interface ServerInfoMapper {

    /**
     * 전체 서버 조회
     */
    public List<ServerInfoVO> selectAllServer();

    /**
     * 서버 이름 중복 체크
     */
    public int checkServerNm(ServerInfoVO serverInfoVO);

    /**
     * 서버 ip 중복 체크
     */
    public int checkServerIp(ServerInfoVO serverInfoVO);

    /**
     * 서버 등록
     */
    public int insertServer(ServerInfoVO serverInfoVO);

    /**
     * 서버 수정
     */
    public int updateServer(ServerInfoVO serverInfoVO);

    /**
     * 서버 삭제
     */
    public int deleteServer(String[] checkId);

}

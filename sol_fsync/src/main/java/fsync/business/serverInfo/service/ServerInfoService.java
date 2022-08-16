package fsync.business.serverInfo.service;

import fsync.business.serverInfo.vo.ServerInfoVO;

import java.util.List;

/**
 * @FileName : ServerInfoService.java
 * @Date : 2022. 7. 26.
 * @Author : ysy
 * @Description : ServerInfoService
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.26       ysy       최초생성
 */

public interface ServerInfoService {

    /**
     * 전체 서버 목록 조회
     *
     * @param
     * @return List
     * @throws Exception
     * @Method Name   : selectAllServer
     * @Date / Author : 2022. 07. 27 / ysy
     * @History 2022.07.27 ysy 최초생성
     */
    public List<ServerInfoVO> selectAllServer() throws Exception;

    /**
     * 서버 등록
     *
     * @param serverInfoVO
     * @return String
     * @throws Exception
     * @Method Name   : insertServer
     * @Date / Author : 2022. 07. 27 / ysy
     * @History 2022.07.27 ysy 최초생성
     */
    public String insertServer(ServerInfoVO serverInfoVO) throws Exception;

    /**
     * 서버 수정
     *
     * @param serverInfoVO
     * @return String
     * @throws Exception
     * @Method Name   : updateServer
     * @Date / Author : 2022. 07. 27 / ysy
     * @History 2022.07.27 ysy 최초생성
     */
    public String updateServer(ServerInfoVO serverInfoVO) throws Exception;


    /**
     * 서버 삭제
     *
     * @param checkId
     * @return void
     * @throws Exception
     * @Method Name   : deleteServer
     * @Date / Author : 2022. 07. 27 / ysy
     * @History 2022.07.27 ysy 최초생성
     */
    public int deleteServer(String[] checkId) throws Exception;
}

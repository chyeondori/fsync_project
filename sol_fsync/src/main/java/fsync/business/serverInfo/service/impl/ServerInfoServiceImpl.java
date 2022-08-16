package fsync.business.serverInfo.service.impl;

import fsync.business.serverInfo.mapper.ServerInfoMapper;
import fsync.business.serverInfo.service.ServerInfoService;
import fsync.business.serverInfo.vo.ServerInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FileName : ServerServiceImpl.java
 * @Date : 2022. 7. 26.
 * @Author : ysy
 * @Description : 서버 정보 관리
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.26       ysy       최초생성
 */

@Slf4j
@Service
public class ServerInfoServiceImpl implements ServerInfoService {

    @Autowired
    ServerInfoMapper serverInfoMapper;

    /**
     * 전체 서버 목록 조회
     *
     * @param
     * @return List
     * @Method Name   : selectAllServer
     * @Date / Author : 2022. 07. 27 / ysy
     * @History 2022.07.27 ysy 최초생성
     */
    @Override
    public List<ServerInfoVO> selectAllServer() {

        try {
            log.debug("★★ ServerImpl serverInfoMapper.selectAllServer() 리턴 :" + serverInfoMapper.selectAllServer());

        } catch (Exception e) {
            log.error("★★ ServerImpl selectAllServer 에러");
            e.printStackTrace();
        }
        return serverInfoMapper.selectAllServer();
    }

    /**
     * 서버 등록
     *
     * @param serverInfoVO
     * @return String
     * @Method Name   : insertServer
     * @Date / Author : 2022. 07. 27 / ysy
     * @History 2022.07.27 ysy 최초생성
     * 2022.08.03 ysy 리턴형 변경
     */
    @Override
    public String insertServer(ServerInfoVO serverInfoVO) {

        // int inputServerNm : 중복값 있으면 1, 없으면 0 반환
        // => int inResult : 중복값 있으면 0, 없으면 1 반환
        int inputServerNm = serverInfoMapper.checkServerNm(serverInfoVO);
        int inputServerIp = serverInfoMapper.checkServerIp(serverInfoVO);

        log.debug("■■■ 중복값유무 inputServerNm : " + inputServerNm);
        log.debug("■■■ 중복값유무 inputServerIp : " + inputServerIp);

        String result;

        try {
            if (inputServerNm == 0) {
                if (inputServerIp == 0) {
                    //둘 다 중복 안 될 경우
                    serverInfoMapper.insertServer(serverInfoVO);
                    result = "success";
                } else {
                    //ip 중복
                    result = "duplicateIp";
                }
            } else {
                if (inputServerIp == 0) {
                    // 이름 중복
                    result = "duplicateNm";
                } else {
                    // 둘 다 중복
                    result = "duplicate";
                }
            }
        } catch (Exception e) {
            result = "error";
            e.printStackTrace();
        }
        log.debug("■■■ ServerImpl insertServer result : " + result);
        return result;
    }

    /**
     * 서버 수정
     *
     * @param serverInfoVO
     * @return String
     * @Method Name   : updateServer
     * @Date / Author : 2022. 07. 27 / ysy
     * @History 2022.07.27 ysy 최초생성
     * 2022.08.03 ysy 리턴형 변경
     */
    @Override
    public String updateServer(ServerInfoVO serverInfoVO) {

        int updateServerNm = serverInfoMapper.checkServerNm(serverInfoVO);
        int updateServerIp = serverInfoMapper.checkServerIp(serverInfoVO);

        log.debug("■■■ 중복값유무 updateServerNm : " + updateServerNm);
        log.debug("■■■ 중복값유무 updateServerIp : " + updateServerIp);

        String result;

        try {
            if (updateServerNm == 0) {
                if (updateServerIp == 0) {
                    //둘 다 중복 안 될 경우
                    serverInfoMapper.updateServer(serverInfoVO);
                    result = "success";
                } else {
                    //ip 중복
                    result = "duplicateIp";
                }
            } else {
                if (updateServerIp == 0) {
                    // 이름 중복
                    result = "duplicateNm";
                } else {
                    // 둘 다 중복
                    result = "duplicate";
                }
            }
        } catch (Exception e) {
            result = "error";
            e.printStackTrace();
        }

        log.debug("■■■ ServerImpl updateServer result : " + result);
        return result;
    }

    /**
     * 서버 삭제
     *
     * @param checkId
     * @return int
     * @Method Name   : deleteServer
     * @Date / Author : 2022. 07. 27 / ysy
     * @History 2022.07.27 ysy 최초생성
     * 2022.08.03 ysy 리턴형 변경
     */
    @Override
    public int deleteServer(String[] checkId) {
        int result;
        try {
            result = serverInfoMapper.deleteServer(checkId);
            log.debug("★★★ ServerImpl deleteServer result : " + result);
        } catch (DataAccessException e) {
            e.printStackTrace();
            result = -10;
            log.debug("★★★ ServerImpl deleteServer DataAccessException : " + result);
        }
        return result;

    }
}
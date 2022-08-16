package fsync.business.path.service;


import fsync.business.path.vo.RegRequestVo;
import fsync.business.path.vo.TopDirInfoVo;

import java.util.List;

/**
 * @FileName : PathService.java
 * @Date : 2022. 7. 19.
 * @Author : leedohyun
 * @Description : Path Service
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2021.07.19       leedohyun       최초생성
 */

public interface FilePathService {

    /**
     * 등록된 동기화 경로 관리
     *
     * @param
     * @return List
     * @throws Exception
     * @Method Name   : topDirInfoList
     * @Date / Author : 2022. 07. 19.  leedohyun
     * @History 2022.07.19 leedohyun 최초생성
     */
    public List<TopDirInfoVo> topDirInfoList() throws Exception;

    /**
     * 동기화 경로 등록
     *
     * @param topDirInfoVo
     * @return void
     * @throws Exception
     * @Method Name   : initRegister
     * @Date / Author : 2022. 07. 21.  leedohyun
     * @History 2022.07.21 leedohyun 최초생성
     */
    public String initRegister(TopDirInfoVo topDirInfoVo) throws Exception;

    /**
     * 등록된 동기화 경로 수정 (Update)
     *
     * @param topDirInfoVo
     * @return void
     * @throws Exception
     * @Method Name   : modifyPath
     * @Date / Author : 2022. 07. 21.  leedohyun
     * @History 2022.07.21 leedohyun 최초생성
     */
    public String modifyPath(TopDirInfoVo topDirInfoVo) throws Exception;

    /**
     * 등록된 동기화 경로 삭제 (Delete)
     *
     * @param chkId
     * @return List
     * @throws Exception
     * @Method Name   : delPath
     * @Date / Author : 2022. 07. 21.  leedohyun
     * @History 2022.07.21 leedohyun 최초생성
     */
    public void delPath(String[] chkId) throws Exception;


    /**
     * 초기 환경 구성 (오더 테이블 Insert)
     *
     * @param regRequestVos
     * @return List
     * @throws Exception
     * @Method Name   : initStatConf
     * @Date / Author : 2022. 07. 21.  leedohyun
     * @History 2022.07.21 leedohyun 최초생성
     */
    public int initStatConf(List<RegRequestVo> regRequestVos) throws Exception;


}

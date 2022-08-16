package fsync.business.path.service.impl;

import fsync.business.path.mapper.FilePathMapper;
import fsync.business.path.service.FilePathService;
import fsync.business.path.vo.RegRequestVo;
import fsync.business.path.vo.TopDirInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @FileName : PathServiceImpl.java
 * @Date : 2022. 7. 19.
 * @Author : leedohyun
 * @Description : Path ServiceImpl
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.19       leedohyun       최초생성
 */
@Slf4j
@Service
public class FilePathServiceImpl implements FilePathService {

    @Autowired
    FilePathMapper filePathMapper;

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
    @Override
    public List<TopDirInfoVo> topDirInfoList() throws Exception {

        //1. DB 조회 (Select *) return
        List<TopDirInfoVo> topDirInfoVos = filePathMapper.topDirList();

        return topDirInfoVos;
    }


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
    @Override
    public String initRegister(TopDirInfoVo topDirInfoVo) throws Exception {

        log.debug("### initRegister ### 동기화 경로 등록 서비스 ###");

        int inputPathDir = filePathMapper.checkPathDir(topDirInfoVo);
        log.debug("### 동기화 경로 중복 유무 inputPathDir : " + inputPathDir);

        String result;

        try {
            if (inputPathDir == 0) {
                // 기존 등록된 원천경로, 대상경로 안겹치는 경우
                filePathMapper.initRegister(topDirInfoVo);
                result = "success";
            } else { // 기존 등록된 원천경로, 대상경로와 겹치는 경우
                result = "duplicate";
            }
        } catch (Exception e) {
            result = "error";
            e.printStackTrace();
        }

        log.debug("### FilePathServiceImpl initRegister result : " + result);
        return result;
    }


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
    @Override
    public String modifyPath(TopDirInfoVo topDirInfoVo) throws Exception {
        log.debug("### modifyPath ### 등록된 동기화 경로 수정 ### ");

        int updatePathDir = filePathMapper.checkPathDir(topDirInfoVo);
        log.debug("### 중복값유무 updatePathDir : " + updatePathDir);

        //1. 해당 ID가진 경로 조회
//        int selectResult = filePathMapper.selectPath(topDirInfoVo);

//        int updateResult = 0;
        String result;

//        if (selectResult != topDirInfoVo.getSyncId()) {
//            log.error(topDirInfoVo.getSyncId() + "# 해당 ID를 가진 정보가 없음.");

        try {
            if (updatePathDir == 0) {
                // 기존 등록된 원천경로, 대상경로 안겹치는 경우
                filePathMapper.modifyPath(topDirInfoVo);
                result = "success";
            } else { // 기존 등록된 원천경로, 대상경로와 겹치는 경우
                result = "duplicate";
            }
        } catch (Exception e) {
            result = "error";
            e.printStackTrace();
        }

//            try {
//                //2. 경로 수정 쿼리호출
//                updateResult = filePathMapper.modifyPath(topDirInfoVo);
//
//
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//            if (updateResult != 1)
//                log.error(topDirInfoVo.getSyncId() + "# 업데이트 실패.");
        return result;
    }


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
    @Override
    public void delPath(String[] chkId) throws Exception {

        log.debug("### delpath ### 등록된 동기화 경로 삭제 ###");
        int result = filePathMapper.delPath(chkId);

    }

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
    @Override
    public int initStatConf(List<RegRequestVo> regRequestVos) throws Exception {
        log.debug("### initStatConf ### 초기 환경 구성 ###");

        /** 초기 환경 구성 Query 실행**/
        int result = filePathMapper.initStatConf(regRequestVos);

        /** 해당 VO Status 업데이트**/
        int updateResult = filePathMapper.updatePath(regRequestVos);

        return result;
    }


}

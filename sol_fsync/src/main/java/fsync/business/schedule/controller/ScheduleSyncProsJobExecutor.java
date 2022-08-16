package fsync.business.schedule.controller;

import fsync.business.schedule.service.DirSyncProsService;
import fsync.business.schedule.vo.DirSyncProsVO;
import fsync.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * @FileName : ScheduleSyncProsJobExecutor.java
 * @Date : 2022. 7. 21.
 * @Author : mhj
 * @Description : [배치]동기화 건수, 용량  폴더별 갱신
 * @History =======================================================
 * DATE			AUTHOR			NOTE
 * =======================================================
 * 2022.07.21      mhj		최초생성
 */
@Slf4j
@DisallowConcurrentExecution
public class ScheduleSyncProsJobExecutor extends QuartzJobBean {

    private DirSyncProsService dirSyncProsService;

    @Autowired
    public void setDirSyncProsService(DirSyncProsService dirSyncProsService) {
        this.dirSyncProsService = dirSyncProsService;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        log.info("### [동기화 건수, 용량 배치] Start Time >>> " + DateUtils.getCurrentDateTime());

        // 배치 시작
        try {
            process(context);
        } catch (Exception e) {
            log.error("#######[동기화 건수, 용량 배치] : SchedulSyncdProsJobExecutor > Message : " + e.getMessage());
            throw new RuntimeException(e);
        }

        // 배치 종료
        log.info("### [동기화 건수, 용량 배치] End Time >>> " + DateUtils.getCurrentDateTime());

    }

    // 배치 내용
    public void process(JobExecutionContext context) throws Exception {
        // 1. (폴더별)하위 동기화된 파일 갯수, 용량 계산해서 조회
        List<DirSyncProsVO> dirSyncProsVOList = dirSyncProsService.selDirSyncList();

        if (!dirSyncProsVOList.isEmpty()) {
            log.info("### [동기화 건수, 용량 배치]  update 예정 ROW수 >>> " + dirSyncProsVOList.size());

            // 2. (폴더별)하위 동기화된 파일 갯수, 용량 갱신
            int updateResult = dirSyncProsService.updateDirSyncList(dirSyncProsVOList);

            log.info("### [동기화 건수, 용량 배치]  update 실제 ROW수 >>> " + updateResult);
        } else {
            log.info("### [동기화 건수, 용량 배치]  update 대상 0건 ");
        }
    }
}

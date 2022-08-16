package fsync.business.schedule.vo;

import lombok.*;

/**
 * @FileName     : DirSyncProsVO.java
 * @Date         : 2022. 07. 21.
 * @Author       : mhj
 * @Description  : 폴더별 동기화 건수,용량 업데이트 관련 VO
 * @History
 * =======================================================
 *   DATE			AUTHOR			NOTE
 * =======================================================
 * 22.07.21       mhj        최초생성
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DirSyncProsVO {

    /** 디렉토리 식별자 */
    private String dirId;

    /** 동기화 파일 사이즈 합계 */
    private String sumFileSize;

    /** 동기화 파일 갯수 합계*/
    private String sumFileCnt;
}

package fsync.business.stat.vo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SyncStatusVO {

    /** 식별자 */
    private String dirId;

    /** 부모 식별자 */
    private String pDirId;

    /** 싱크 식별자 */
    private String syncId;

    /** 경로 명 */
    private String cDirNm;

    /** 원천 풀 경로 명 */
    private String srcFDirNm;

    /** 크기 */
    private String totFileSize;

    /** 수 */
    private String totFileCnt;

    /** 동기화 대상 경로 */
    private String syncdDirNm;

    /** 크기 */
    private String syncdFileSize;

    /** 수 */
    private String syncdFileCnt;

    /** 진행률 */
    private String progressing;

    /** 진행 상태 */
    private String procStat;

    /** 요청 일시 */
    private String procDtm;

    /** 파일 유형 */
    private String fileTypeCd;

}

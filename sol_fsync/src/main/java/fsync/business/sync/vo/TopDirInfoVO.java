package fsync.business.sync.vo;

import lombok.*;

/**
 * @packageName      : fsync.model.vo.sync
 * @fileName         : TopDirInfoVO.java
 * @author           : kimhyeonjung
 * @date             : 2022.07.20
 * @description      : 최상위 디렉토리 조회 VO
 * =======================================================
 * DATE          AUTHOR          NOTE
 * =======================================================
 * 2022.07.20    kimhyeonjung       최초생성
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TopDirInfoVO {

    /** 동기화 식별자 */
    private String syncId;

    /** 원천 최상위 디렉터리 */
    private String srcTopDir;

    /** 원천 전체 파일 개수 */
    private String srcTotFileCnt;
    
    /** 원천 전체 파일 용량 */
    private String srtTotFileSize;

    /** 대상 최상위 디렉터리 */
    private String dstTopDir;

    /** 대상 전체 파일 갯수 */
    private String dstTotFileCnt;

    /** 대상 전체 파일 용량 */
    private String dstTotFileSize;

    /** 로그 디렉토리 */
    private String logDir;

    /** 처리상태 */
    private String procStat;

    /** 등록일시 */
    private String regDtm;

    /** 비고 */
    private String reMark;
    

}

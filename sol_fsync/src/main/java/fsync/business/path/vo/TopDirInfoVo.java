package fsync.business.path.vo;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 첫번 째 화면 조회용 DB VO
 * 2022.07.19 leedohyun
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TopDirInfoVo {

    /**동기화 식별자**/
    private int syncId;

    /**원천 최상위 디렉터리**/
    private String srcTopDir;

    /**원천 전체 파일 갯수**/
    private BigDecimal srcTotFileCnt;

    /**원천 전체 파일 용량**/
    private BigDecimal srcTotFileSize;

    /**대상 최상위 디렉토리**/
    private String dstTopDir;

    /**대상 전체 파일 갯수**/
    private BigDecimal dstTotFileCnt;

    /**대상 전체 파일 용량**/
    private BigDecimal dstTotFileSize;

    /**로그 디렉토리**/
    private String logDir;

    /**처리상태**/
    private String procStat;

    /**등록일시**/
    private Timestamp regDtm;

    /**비고**/
    private String remark;

}

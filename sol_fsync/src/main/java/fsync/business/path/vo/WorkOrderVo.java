package fsync.business.path.vo;

import lombok.*;

import java.sql.Timestamp;

/**
 * 오더 테이블 DB VO
 * 2022.07.19 leedohyun
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WorkOrderVo {

    /**작업오더 식별자**/
    private int wkOrdId;

    /**작업오더 식별자 순번**/
    private int wkOrdSeq;

    /**작업오더 코드**/
    private String wkOrdCd;

    /**소스**/
    private String source;

    /**대상**/
    private String target;

    /**작업서버 식별자**/
    private int wkServerId;

    /**작업 PID**/
    private int wkPid;

    /**처리상태**/
    private String procStat;

    /**처리시각**/
    private Timestamp procDtm;

    /**에러코드**/
    private String errCd;

    /**에러메세지**/
    private String errMsg;

    /**에러정보**/
    private String errInfo;

}

package fsync.business.sync.vo;

import lombok.*;

/**
 * @author : kimhyeonjung
 * @packageName : fsync.model.vo.sync
 * @fileName : WorkOrderVO.java
 * @date : 2022.07.22
 * @description : 작업오더 Insert 위한 VO
 * =======================================================
 * DATE          AUTHOR          NOTE
 * =======================================================
 * 2022.07.22    kimhyeonjung       최초생성
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WorkOrderVO {

    /**
     * 워크 오더 식별자
     */
    private String wkOrdId;

    /**
     * 작업오더 코드
     */
    private String wkOrdCd;

    /**
     * 원본 경로
     */
    private String source;

    /**
     * 대상 경로
     */
    private String target;

    /**
     * 작업서버 식별자
     */
    private String wkServerId;

    /**
     * 처리 상태
     */
    private String procStat;

    /**
     * 처리 일시
     */
    private String procDtm;

    /**
     * 작업할 디렉토리 또는 파일 구분 f,d
     */
    private String fileType;

    /**
     * 작업할 디렉토리 또는 파일 식별자
     */
    private String fileId;

}

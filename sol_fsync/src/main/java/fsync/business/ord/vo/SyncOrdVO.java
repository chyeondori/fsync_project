package fsync.business.ord.vo;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SyncOrdVO {

    /** 워크 오더 식별자 */
    private String wkOrdId;

    /** 원본 경로 */
    private String source;

    /** 대상 경로 */
    private String target;

    /** 할당 에이전트 */
    private String serverNm;

    /** 작업 오더 코드 */
    private String wkOrdCd;

    /** 작업 오더 코드 명 */
    private String wkOrdCdNm;

    /** 처리 상태 */
    private String procStat;

    /** 처리 일시 */
    private String procDtm;
}

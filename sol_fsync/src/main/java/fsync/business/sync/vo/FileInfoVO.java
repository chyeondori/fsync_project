package fsync.business.sync.vo;

import lombok.*;

/**
 * @packageName      : fsync.model.vo.sync
 * @fileName         : FileInfoVO.java
 * @author           : kimhyeonjung
 * @date             : 2022.07.20
 * @description      : 파일 정보 VO
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
public class FileInfoVO {

    /** 파일 식별자 */
    private String fileId;

    /** 디렉토리 식별자 */
    private String dirId;

    /** 디렉토리명 */
    private String dirNm;

    /** 파일명 */
    private String fileNm;

    /** 전체 경로명 */
    private String fPathNm;

    /** 파일 일시 */
    private String fileDtm;

    /** 파일 용량 */
    private String fileSize;

    /** 처리 단계 */
    private String procStep;

    /** 처리 상태 */
    private String procStat;

    /** 처리 시각 */
    private String procDtm;

    /** 요약 시각 */
    private String smryDtm;
}

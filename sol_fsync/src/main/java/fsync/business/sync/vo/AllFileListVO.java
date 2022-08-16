package fsync.business.sync.vo;

import lombok.*;

/**
 * @packageName      : fsync.model.vo.sync
 * @fileName         : DirFileListVO.java
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
public class AllFileListVO {

    /* ysy */
    /** 폴더나 파일 이름 */
    private String exName;

    /** 현재 작업중인 경로 */
    private String exDirNm;

    /** 원천 전체 디렉토리명 */
    private String exSrcFName;

    /** 대상 전체 디렉토리명 */
    private String exDstFName;

    /** 디렉토리, 파일 일시 */
    private String exDtm;

    /** 유형 - 한글 */
    private String exType;
    
    /** 유형 - 코드 */
    private String typeCd;

    /** 크기 */
    private String exSize;

    /** 개수 */
    private String exCnt;

    /** 동기화 상태 - 한글 */
    private String exProcStat;

    /** 동기화 상태 - 코드 */
    private String procStat;

    /** 식별자 */
    private String exId;
}

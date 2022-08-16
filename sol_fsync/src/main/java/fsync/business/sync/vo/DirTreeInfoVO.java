package fsync.business.sync.vo;

import lombok.*;

/**
 * @packageName      : fsync.model.vo.sync
 * @fileName         : DirInfoVO.java
 * @author           : kimhyeonjung
 * @date             : 2022.07.19
 * @description      : 트리 생성용 디렉토리 조회 VO
 * =======================================================
 * DATE          AUTHOR          NOTE
 * =======================================================
 * 2022.07.19    kimhyeonjung       최초생성
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DirTreeInfoVO {

    
    /** 디렉토리 식별자 */
    private String dirId;

    /** 부모 디렉토리 식별자 */
    private String pDirId;

    /** 현재 디렉토리명 */
    private String cDirNm;

    /** 전체 디렉토리명 */
    private String fDirNm;

    /** 동기화 식별자 */
    private String syncId;

}

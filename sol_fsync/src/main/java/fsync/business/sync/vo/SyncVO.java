package fsync.business.sync.vo;

import lombok.*;

/**
 * @packageName      : fsync.model.vo.sync
 * @fileName         : syncVO.java
 * @author           : kimhyeonjung
 * @date             : 2022.07.21
 * @description      : 동기화, 크기 검증 id VO
 * =======================================================
 * DATE          AUTHOR          NOTE
 * =======================================================
 * 2022.07.21    kimhyeonjung       최초생성
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SyncVO {

    /** 파일, 디렉토리 식별자 */
    private String type;

    /** id */
    private String id;

    /** 선택한 서버 */
    private String serverId;

}

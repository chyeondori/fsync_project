package fsync.business.sync.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @packageName      : fsync.model.vo.sync
 * @fileName         : DirTreeVO.java
 * @author           : kimhyeonjung
 * @date             : 2022.07.19
 * @description      : 트리 구조 JSON을 생성하기 위한 VO
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
public class DirTreeJsonVO {

    /** 트리 ID */
    @JsonProperty("id")
    private String id;

    /** 트리 부모 */
    @JsonProperty("parent")
    private String parent;

    /** 트리 텍스트 */
    @JsonProperty("text")
    private String text;

}

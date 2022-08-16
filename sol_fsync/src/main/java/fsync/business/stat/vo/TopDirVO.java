package fsync.business.stat.vo;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TopDirVO {

    /** 동기화 식별자 */
    private String syncId;

    /** 원천 최상위 디렉터리 */
    private String srcTopDir;

    /** 대상 최상위 디렉터리 */
    private String dstTopDir;

    private String dirId;

}

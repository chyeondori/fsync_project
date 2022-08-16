package fsync.business.path.vo;

import lombok.*;

/**
 * 등록, 수정 시 Mapping하는 VO
 * 2022.07.19 leedohyun
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegRequestVo {

    /**동기화 식별자**/
    private String id;

    /**소스 디렉터리 경로**/
    private String srcpath;

    /**대상 디렉터리 경로ㅓ**/
    private String despath;

    /**로그 디렉터리 경로**/
    private String logpath;

    /**비고**/
    private String remark;

    /**에이전트 ID**/
    private String serverId;

}

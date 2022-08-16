package fsync.business.sample.vo;

import lombok.*;

/**
 * @packageName      : idmcs.model.vo.sample
 * @fileName         : SampleVO.java
 * @author           : kimindeok
 * @date             : 2021.07.21
 * @description      : API 프로젝트 sample 페이지를 위한 VO
 * =======================================================
 * DATE          AUTHOR          NOTE
 * =======================================================
 * 2021.07.21    kimindeok       최초생성
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SampleVO {

    /** 그룹 코드 */
    private String groupCd;

    /** 그룹 이름 */
    private String groupNm;

    /** 사용 여부 */
    private String useYn;

    /** 등록일자 */
    private String regDt;

}

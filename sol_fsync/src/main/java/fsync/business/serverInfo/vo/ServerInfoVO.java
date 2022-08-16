package fsync.business.serverInfo.vo;

import lombok.*;

/**
 * @packageName      : fsync.model.vo.sync
 * @fileName         : ServerInfoVO.java
 * @author           : kimhyeonjung
 * @date             : 2022.07.22
 * @description      : 서버정보(에이전트) VO
 * =======================================================
 * DATE          AUTHOR          NOTE
 * =======================================================
 * 2022.07.22    kimhyeonjung       최초생성
 *
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServerInfoVO {

    /** 작업서버 식별자 */
    private int serverId;

    /** 서버 타입 */
    private String serverTy;

    /** 서버 이름 */
    private String serverNm;

    /** 서버 IP */
    private String serverIp;

    /** 호스트 */
    private String hostNm;

    /** 대기중인 작업 개수 */
    private String serverOrderCnt;

    /** 서버의 최대 작업량 */
    private String maxJobCnt;

}

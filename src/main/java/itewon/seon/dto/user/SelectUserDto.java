package itewon.seon.dto.user;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectUserDto {
    private long userSeq;
    private String userEmail;
    private String userPw;
    private String userName;
    private Date regTimestamp;
    private long cnt;
}

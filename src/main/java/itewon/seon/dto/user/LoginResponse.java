package itewon.seon.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private long userSeq;
    private String userEmail;
    private String userName;
    private String accessToken;
    private long cnt;
}

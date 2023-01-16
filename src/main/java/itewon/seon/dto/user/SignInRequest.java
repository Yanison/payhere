package itewon.seon.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    private String userEmail;
    private String userPw;
    private String authorityName;
}

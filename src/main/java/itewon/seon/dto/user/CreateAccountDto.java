package itewon.seon.dto.user;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountDto {
    private String userEmail;
    private String userPw;
    private String userName;
}

package itewon.seon.dto.user;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private long userSeq;
    private String userEmail;
    private String userPw;
    private String userName;
    @Builder.Default
    private List<String> roles = new ArrayList<>();
}

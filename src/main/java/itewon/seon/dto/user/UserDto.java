package itewon.seon.dto.user;

import itewon.seon.dto.security.AuthorityDto;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto{
    private long userSeq;
    private String userEmail;
    private String userPw;
    private String userName;
    private Date regTimestamp;
    private String authorities;
    private String authorityName;
    private long cnt;
    private Set<AuthorityDto> authorityDtoSet;

    public UserDto from(User user){
        if(user == null) return null;

        return UserDto.builder()
                .userEmail(user.getUsername())
                .userPw(user.getPassword())
                .build();
    }
}

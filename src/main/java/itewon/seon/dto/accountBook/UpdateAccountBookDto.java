package itewon.seon.dto.accountBook;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountBookDto{
    private String shKey;
    private String shValue;
    private long abSeq;
    private long userSeq;
}

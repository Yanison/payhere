package itewon.seon.dto.accountBook;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccountBookDto{
    private long abSeq;
    private String shKey;
    private String shValue;
}

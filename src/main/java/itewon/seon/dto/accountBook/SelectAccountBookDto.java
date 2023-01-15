package itewon.seon.dto.accountBook;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectAccountBookDto {
    private long abSeq;
    private String price;
    private String contents;
    private int delNy;
    private Date regTimestamp;
    private Date updtTimestamp;
    private String type;
    private long userSeq;
}

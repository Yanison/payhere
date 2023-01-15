package itewon.seon.dto.abType;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbTypeListDto {
    private long typeSeq;
    private String typeName;
    private long userSeq;
    private Date updtTimestamp;
}

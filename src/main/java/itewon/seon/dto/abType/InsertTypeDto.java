package itewon.seon.dto.abType;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsertTypeDto {
    private long userSeq;
    private long abSeq;
    private String typeName;
}

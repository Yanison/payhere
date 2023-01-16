package itewon.seon.dto.httpResponse;


import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponseMessage<T> {
    private String responseMessage;
    private T responseData;
    private String errorStatus;
    private T data;
}

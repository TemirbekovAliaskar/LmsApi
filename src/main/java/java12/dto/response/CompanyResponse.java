package java12.dto.response;

import java12.entity.Company;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponse {
    private HttpStatus httpStatus;
    private String message;

}

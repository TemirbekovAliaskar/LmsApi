package java12.dto.response;

import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
public record TaskResponse (Long id, String taskName, String taskText, ZonedDateTime deadLine){
}

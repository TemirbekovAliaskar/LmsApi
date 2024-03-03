package java12.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java12.entity.enums.Specialization;

public record InstructorRequest( String firstName,String lastName,String phoneNumber,Specialization specialization) {
}

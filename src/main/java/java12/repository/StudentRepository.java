package java12.repository;

import java12.dto.response.StudentFormatResponse;
import java12.dto.response.StudentResponse;
import java12.entity.Student;
import java12.entity.enums.StudyFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student  s")
    List<StudentResponse> getAll();
    @Query("select new java12.dto.response.StudentFormatResponse(c.id,c.fullName,c.lastName,c.phoneNumber,c.email, c.studyFormat) from Student c where c.studyFormat =:studyFormat")
    List<StudentFormatResponse> sortByFormat(StudyFormat studyFormat);
}
package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.StudentRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.StudentFormatResponse;
import java12.dto.response.StudentResponse;
import java12.entity.Group;
import java12.entity.Student;
import java12.entity.enums.StudyFormat;
import java12.entity.exception.MyException;
import java12.repository.GroupRepository;
import java12.repository.StudentRepository;
import java12.service.GroupService;
import java12.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Override @Transactional
    public HTTPResponse save(StudentRequest studentRequest) {
        Student student = new Student();
        student.setFullName(studentRequest.fullName());
        student.setLastName(studentRequest.lastName());
        student.setPhoneNumber(studentRequest.phoneNumber());
        student.setEmail(studentRequest.email());
        student.setStudyFormat(studentRequest.studyFormat());
        studentRepository.save(student);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully saved students !")
                .build();
    }

    @Override @Transactional
    public HTTPResponse asSign(Long groupId, Long studentId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("Not found this id !"+groupId));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Not found id this !" + studentId));
        student.setGroup(group);
        group.addStudents(student);
        return  HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully as signed students !")
                .build();
    }

    @Override
    public HTTPResponse deleteById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Not found id this !" + studentId));
        studentRepository.delete(student);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted students !")
                .build();
    }

    @Override @Transactional
    public HTTPResponse update(Long studentId, StudentRequest studentRequest) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Not found id this !" + studentId));
        student.setFullName(studentRequest.fullName());
        student.setLastName(studentRequest.lastName());
        student.setPhoneNumber(studentRequest.phoneNumber());
        student.setEmail(studentRequest.email());
        student.setStudyFormat(studentRequest.studyFormat());
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated students !")
                .build();
    }

    @Override
    public List<StudentResponse> findAll() {
        return studentRepository.getAll() ;
    }

    @Override
    public List<StudentFormatResponse> sortByFormat(StudyFormat studyFormat) {
        return studentRepository.sortByFormat(studyFormat);
    }

    @Override @Transactional
    public HTTPResponse blockStudent(Long studentId) {
        try {
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Not found id this !" + studentId));
            boolean block = student.isBlock();
            if (block) {
                student.setBlock(false);
                return HTTPResponse.builder()
                        .httpStatus(HttpStatus.ACCEPTED)
                        .message("Successfully blocked students !")
                        .build();
            } else {
                student.setBlock(true);
                return HTTPResponse.builder()
                        .httpStatus(HttpStatus.ACCEPTED)
                        .message("Successfully unblocked students !")
                        .build();
            }
        }catch (Exception e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

}

package java12.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java12.dto.request.CompanyRequest;
import java12.dto.request.InstructorRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.InfoInstructorsResponse;
import java12.dto.response.InstructorResponse;
import java12.entity.Company;
import java12.entity.Course;
import java12.entity.Group;
import java12.entity.Instructor;
import java12.repository.CompanyRepository;
import java12.repository.CourseRepository;
import java12.repository.InstructorRepository;
import java12.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;
    private final CourseRepository courseRepository;

    @Override
    public HTTPResponse save(InstructorRequest instructorRequest) {
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.firstName());
        instructor.setLastName(instructorRequest.lastName());
        instructor.setPhoneNumber(instructorRequest.phoneNumber());
        instructor.setSpecialization(instructorRequest.specialization());
        instructorRepository.save(instructor);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully saved instructor !")
                .build();
    }

    @Override @Transactional
    public HTTPResponse asSign(Long companyId, Long instructorId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Company with " + companyId + "not found"));
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Not found"));
        company.addInstructor(instructor);
        instructor.addCompany(company);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully asSigned !")
                .build();
    }

    @Override
    @Transactional
    public HTTPResponse update(Long instructorId, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Not found"));
        instructor.setFirstName(instructorRequest.firstName());
        instructor.setLastName(instructorRequest.lastName());
        instructor.setPhoneNumber(instructorRequest.phoneNumber());
        instructor.setSpecialization(instructorRequest.specialization());

        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated !")
                .build();
    }

    @Override @Transactional
    public HTTPResponse delete(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Not found"));
        instructorRepository.delete(instructor);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted !")
                .build();
    }

    @Override
    public List<InstructorResponse> findAll(Long companyId) {
        return instructorRepository.findAllId(companyId);
    }

    @Override
    public Integer countStudent(Long inId) {
        return instructorRepository.countOfById(inId);
    }

    @Override @org.springframework.transaction.annotation.Transactional
    public HTTPResponse asSignCourse(Long courseId, Long instructorId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Not found" + courseId));
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Not found"));
        course.setInstructor(instructor);
        instructor.addCourse(course);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully asSigned !")
                .build();
    }

    @Override
    public InfoInstructorsResponse infoInstructor(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Not found"));
        InfoInstructorsResponse infoInstructorsResponse = instructorRepository.fullInstructorInfos(instructorId);
        Map<String,Integer> groupsInt = infoInstructorsResponse.getGroupsNameWithStudent();
        List<Course>courses = instructor.getCourses();
        for (Course course : courses) {
            for (Group group : course.getGroups()) {
                int count = 0;
                count += group.getStudents().size();
                groupsInt.put(group.getGroupName(),count);
            }
        }
        return infoInstructorsResponse;
    }

//    @Override
//    public HTTPResponse asSign(CompanyRequest companyRequest, Long instructorId) {
//        instructorRepository.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Not found"));
//
//        return null;
//    }
}

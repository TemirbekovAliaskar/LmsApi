package java12.api;

import java12.dto.request.InstructorRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.InfoInstructorsResponse;
import java12.dto.response.InstructorResponse;
import java12.service.CompanyService;
import java12.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
public class InstructorAPI {
    private final InstructorService instructorService;


    @GetMapping("/all/{companyId}")
    public List<InstructorResponse> findAll(@PathVariable Long companyId){
        return instructorService.findAll(companyId);
    }
    @PostMapping()
    public HTTPResponse save(@RequestBody InstructorRequest instructorRequest){
        return instructorService.save(instructorRequest);
    }
    @PostMapping("/{companyId}/{instructorId}")
    public HTTPResponse asSignInstructorToCompany(@PathVariable Long companyId,@PathVariable Long instructorId){
        return instructorService.asSign(companyId,instructorId);
    }

    @PutMapping("/{courseId}/{instructorId}")
    public HTTPResponse asSignInstructorToCourse(@PathVariable Long courseId,@PathVariable Long instructorId){
        return instructorService.asSignCourse(courseId,instructorId);
    }

    @PostMapping("/update/{instructorId}")
    public HTTPResponse update(@PathVariable Long instructorId,@RequestBody InstructorRequest instructorRequest){
        return instructorService.update(instructorId,instructorRequest);
    }

    @PostMapping("/delete/{instructorId}")
    public HTTPResponse delete(@PathVariable Long instructorId){
        return instructorService.delete(instructorId);
    }

    @GetMapping("/instructorCount/{inId}")
    public Integer count(@PathVariable Long inId){
        return instructorService.countStudent(inId);
    }

    @GetMapping("/info/{instructorId}")
    public InfoInstructorsResponse infos(@PathVariable Long instructorId){
        return instructorService.infoInstructor(instructorId);
    }

}

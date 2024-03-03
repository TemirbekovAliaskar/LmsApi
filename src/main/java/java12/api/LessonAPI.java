package java12.api;

import java12.dto.request.LessonRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.LessonResponse;
import java12.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
public class LessonAPI {
    private final LessonService lessonService;


    @PostMapping("/save/{courseId}")
    public HTTPResponse save(@PathVariable Long courseId, @RequestBody LessonRequest lessonRequest){
       return lessonService.save(courseId,lessonRequest);
    }

    @PostMapping("/update/{lessonId}")
    public HTTPResponse update(@PathVariable Long lessonId,@RequestBody LessonRequest lessonRequest){
        return lessonService.update(lessonId,lessonRequest);
    }

    @PostMapping ("delete/{lessonId}")
    public HTTPResponse delete(@PathVariable Long lessonId){
        return lessonService.deleteById(lessonId);
    }

    @GetMapping("/all/{courseId}")
    public List <LessonResponse> findAll(@PathVariable Long courseId){
        return lessonService.findAll(courseId);
    }

}

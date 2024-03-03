package java12.api;

import java12.dto.request.TaskRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.TaskResponse;
import java12.entity.Task;
import java12.repository.TaskRepository;
import java12.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskAPI {
    private final TaskService taskService;

    @PostMapping("/saved/{lessonId}")
    public HTTPResponse save(@PathVariable Long lessonId, @RequestBody TaskRequest taskRequest){
        return taskService.saveToLesson(lessonId,taskRequest);
    }

    @PostMapping("/update/{taskId}")
    public HTTPResponse update(@PathVariable Long taskId,@RequestBody TaskRequest taskRequest){
        return taskService.update(taskId,taskRequest);
    }
    @PostMapping("delete/{taskId}")
    public HTTPResponse delete(@PathVariable Long taskId){
        return taskService.delete(taskId);
    }

    @GetMapping("all/{lessonId}")
    public List<TaskResponse> findAll(@PathVariable Long lessonId){
        return taskService.findAll(lessonId);
    }
    @GetMapping("find/{taskId}")
    public TaskResponse findById(@PathVariable Long taskId){
        return taskService.findById(taskId);
    }
}

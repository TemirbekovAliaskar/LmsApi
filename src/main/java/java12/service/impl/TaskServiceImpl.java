package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.TaskRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.TaskResponse;
import java12.entity.Lesson;
import java12.entity.Task;
import java12.repository.LessonRepository;
import java12.repository.TaskRepository;
import java12.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.xml.crypto.NoSuchMechanismException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;
    @Override
    public HTTPResponse saveToLesson(Long lessonId, TaskRequest taskRequest) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NoSuchElementException("Not found !"));
        Task task = new Task();
        task.setTaskName(taskRequest.taskName());
        task.setTaskText(taskRequest.taskText());
        task.setDeadLine(taskRequest.deadLine());

        task.setLesson(lesson);
        taskRepository.save(task);

        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully save task !")
                .build();
    }

    @Override @Transactional
    public HTTPResponse update(Long taskId, TaskRequest taskRequest) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Not found this id " + taskId));
        task.setTaskName(taskRequest.taskName());
        task.setTaskText(taskRequest.taskText());
        task.setDeadLine(taskRequest.deadLine());
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated task !")
                .build();
    }

    @Override
    public HTTPResponse delete(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Not found this id " + taskId));
        taskRepository.delete(task);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted task !")
                .build();
    }

    @Override
    public List<TaskResponse> findAll(Long lessonId) {
        return taskRepository.findAllLessonId(lessonId);
    }

    @Override
    public TaskResponse findById(Long taskId) {
//        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Not found this id " + taskId));
        return taskRepository.findByTaskId(taskId);
    }
}

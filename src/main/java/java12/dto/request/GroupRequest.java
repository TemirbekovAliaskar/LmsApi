package java12.dto.request;

import jakarta.persistence.ManyToMany;
import java12.entity.Course;

import java.util.ArrayList;
import java.util.List;

public record GroupRequest(String groupName,String imageLink,String description,List<Long>coursesIds) {

//    public void addCourse(Course course){
//        if (this.courses == null) this.courses = new ArrayList<>();
//        this.courses.add(course)
//    }
}

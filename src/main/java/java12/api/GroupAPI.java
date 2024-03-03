package java12.api;

import java12.dto.request.GroupRequest;
import java12.dto.response.GroupResponse;
import java12.dto.response.HTTPResponse;
import java12.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupAPI {

    private final GroupService groupService;

    @PostMapping("/save")
    public HTTPResponse save(@RequestBody GroupRequest groupRequest){
        return groupService.save(groupRequest);
    }

    @GetMapping("/all")
    public List <GroupResponse> findAll(){
        return groupService.findAll();
    }

    @PostMapping("/delete/{groupId}")
    public HTTPResponse delete(@PathVariable Long groupId){
        return groupService.deleteById(groupId);
    }

    @PostMapping("/update/{groupId}")
    public HTTPResponse update(@PathVariable Long groupId,@RequestBody GroupRequest groupRequest){
        return groupService.update(groupId,groupRequest);
    }

    @GetMapping("count/{groupId}")
    public List<String> count(@PathVariable Long groupId){
        return groupService.countById(groupId);
    }

}

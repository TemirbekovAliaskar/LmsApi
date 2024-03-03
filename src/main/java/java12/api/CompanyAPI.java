package java12.api;

import java12.dto.request.CompanyRequest;
import java12.dto.response.CompanyInfoResponse;
import java12.dto.response.CompanyResponse;
import java12.entity.exception.MyException;
import java12.service.CompanyService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CompanyAPI {
    private final CompanyService companyService;

    @GetMapping
    public List<CompanyRequest> findAll(){
        return companyService.findAll();
    }

    @PostMapping("/save")
    public CompanyResponse save(@RequestBody CompanyRequest companyRequest) throws MyException {
        return companyService.save(companyRequest);
    }

    @PostMapping("/update/{companyId}")
    public CompanyResponse update(@PathVariable Long companyId,@RequestBody CompanyRequest companyRequest){
        return companyService.updateById(companyId,companyRequest);
    }

    @PostMapping("/delete/{companyId}")
    public CompanyResponse delete(@PathVariable Long companyId){
        return companyService.deleteById(companyId);
    }

    @GetMapping("/info/{companyId}")
    public CompanyInfoResponse infoResponse (@PathVariable Long companyId){
        return companyService.findInfos(companyId);
    }
}

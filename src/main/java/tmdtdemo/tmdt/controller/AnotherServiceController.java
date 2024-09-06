package tmdtdemo.tmdt.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tmdtdemo.tmdt.dto.response.AnotherServiceResponse;
import tmdtdemo.tmdt.service.AnotherServiceService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/service")
public class AnotherServiceController {
    private final AnotherServiceService serviceService;
    @GetMapping("/getAll")
    public ResponseEntity<List<AnotherServiceResponse>> getAllService(){
        List<AnotherServiceResponse> responses = serviceService.getAll();
        return ResponseEntity.ok(responses);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<AnotherServiceResponse> getService(@PathVariable("id")Long id){
        AnotherServiceResponse response = serviceService.getServiceById(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getAllServiceByRoom/{id}")
    public ResponseEntity<List<AnotherServiceResponse>> getListService(@PathVariable("id")Long id){
        List<AnotherServiceResponse> responses = serviceService.getAllServiceByRoom(id);
        return ResponseEntity.ok(responses);
    }
    @GetMapping("/getAnotherService")
    public ResponseEntity<List<AnotherServiceResponse>> getListAnotherService(){
        List<AnotherServiceResponse> responses = serviceService.getAllServiceAvailableNeednt();
        return ResponseEntity.ok(responses);
    }
}

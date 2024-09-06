package tmdtdemo.tmdt.service;

import tmdtdemo.tmdt.dto.response.AnotherServiceResponse;

import java.util.List;

public interface AnotherServiceService {
    List<AnotherServiceResponse> getAll();
    AnotherServiceResponse getServiceById(Long id);
    List<AnotherServiceResponse> getAllServiceByRoom(Long id);
    List<AnotherServiceResponse> getAllServiceAvailableNeednt();
}

package tmdtdemo.tmdt.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tmdtdemo.tmdt.common.TypeService;
import tmdtdemo.tmdt.dto.response.AnotherServiceResponse;
import tmdtdemo.tmdt.entity.RoomDetails;
import tmdtdemo.tmdt.repository.AnotherServiceRepository;
import tmdtdemo.tmdt.repository.ImgServiceRepository;
import tmdtdemo.tmdt.repository.RoomDetailRepository;
import tmdtdemo.tmdt.service.AnotherServiceService;
import tmdtdemo.tmdt.utils.Mapper;
import tmdtdemo.tmdt.entity.AnotherService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceServiceImpl implements AnotherServiceService {
    private final AnotherServiceRepository serviceRepository;
    private final RoomDetailRepository roomDetailRepository;
    private final ImgServiceRepository imgServiceRepository;

    @Override
    public List<AnotherServiceResponse> getAll() {
        return Mapper.serviceToListReponse(serviceRepository.findAll()).stream().collect(Collectors.toList());
    }

    @Override
    public AnotherServiceResponse getServiceById(Long id) {
        return Mapper.serviceToResponse(serviceRepository.findServiceById(id),null);
    }

    @Override
    public List<AnotherServiceResponse> getAllServiceByRoom(Long id) {
       List<RoomDetails> roomDetails = roomDetailRepository.findRoomDetailsByRoomId(id);
       List<AnotherServiceResponse> responses = new ArrayList<>();
       for(RoomDetails r : roomDetails){
           responses.add(Mapper.serviceToResponse(r.getService(),null));
       }
       return responses;
    }

    @Override
    public List<AnotherServiceResponse> getAllServiceAvailableNeednt() {
        List<AnotherService> services = serviceRepository.findServiceAvailableAndType(TypeService.neednt.toString());
        List<AnotherServiceResponse> responses = new ArrayList<>();
        for(AnotherService s : services){
            AnotherServiceResponse response = Mapper.serviceToResponse(s,imgServiceRepository.findSrcByIdService(s.getId()));
            responses.add(response);

        }
        return responses;
    }
}

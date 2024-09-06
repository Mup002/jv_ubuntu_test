package tmdtdemo.tmdt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tmdtdemo.tmdt.dto.response.RoomResponse;
import tmdtdemo.tmdt.service.RoomService;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {
    private final RoomService roomService;
    @GetMapping("/getAll")
    public ResponseEntity<List<RoomResponse>> getAllRoom(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkin,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkout
    ){
        List<RoomResponse> responses = roomService.getAllRoom(checkin,checkout);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
    @GetMapping("/getRoom/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable("id")Long id){
        RoomResponse response = roomService.getRoomById(id);
        return ResponseEntity.ok(response);
    }

}

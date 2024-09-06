package tmdtdemo.tmdt.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tmdtdemo.tmdt.dto.request.CommentRequest;
import tmdtdemo.tmdt.dto.response.CommentResponse;
import tmdtdemo.tmdt.service.CommentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping("/cmt")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest request, HttpServletRequest httpServletRequest){
        CommentResponse response = commentService.createdCmt(request,httpServletRequest.getHeader("x-client-username"));
        return ResponseEntity.ok(response);
    }


    @GetMapping("/allCmt/{roomId}")
    public ResponseEntity<List<CommentResponse>> getAllComment(@PathVariable("roomId")Long roomId){
        List<CommentResponse> responses = commentService.getAllCmtByRoom(roomId);
        return ResponseEntity.ok(responses);
    }
}

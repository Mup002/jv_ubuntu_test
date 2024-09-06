package tmdtdemo.tmdt.service;


import tmdtdemo.tmdt.dto.request.CommentRequest;
import tmdtdemo.tmdt.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse createdCmt(CommentRequest request, String username);

    List<CommentResponse> getAllCmtByRoom(Long roomId);
}

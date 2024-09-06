package tmdtdemo.tmdt.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tmdtdemo.tmdt.dto.request.CommentRequest;
import tmdtdemo.tmdt.dto.response.CommentResponse;
import tmdtdemo.tmdt.entity.Comment;
import tmdtdemo.tmdt.common.CommentLevel;
import tmdtdemo.tmdt.exception.ResourceNotFoundException;
import tmdtdemo.tmdt.repository.CommentRepository;
import tmdtdemo.tmdt.repository.RoomRepository;
import tmdtdemo.tmdt.service.BaseRedisService;
import tmdtdemo.tmdt.service.CommentService;
import tmdtdemo.tmdt.service.UserService;
import tmdtdemo.tmdt.utils.Mapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final RoomRepository roomRepository;
    private final BaseRedisService baseRedisService;

    @Override
    public CommentResponse createdCmt(CommentRequest request,String username) {
        Comment comment = new Comment();
        CommentLevel commentLevel ;
        try{
            commentLevel = CommentLevel.valueOf(request.getLevel());
            comment.setCommentLevel(commentLevel);
        }catch (IllegalArgumentException e){
            throw new ResourceNotFoundException("Lv comment khong hop le");
        }
        Date now = new Date();
        comment.setCreatedDate(now);
        comment.setUser(userService.findUserByUsername(username));
        comment.setText(request.getText());
        comment.setRoom(roomRepository.findRoomById(request.getRoomId()));
        comment.setParentComment(commentRepository.getCommentById(request.getCmtParentId()));
        return Mapper.commentToResponse(commentRepository.save(comment));
    }

    @Override
    public List<CommentResponse> getAllCmtByRoom(Long roomId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getRoom().getId().equals(roomId))
                .map(comment -> Mapper.commentToResponse(comment))
                .collect(Collectors.toList());
    }
}

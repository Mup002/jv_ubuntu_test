package tmdtdemo.tmdt.dto.request;

import lombok.Data;

@Data
public class CommentRequest {
//    private String username;
    private Long roomId;
    private String text;
    private String level;
    private Long cmtParentId;
}

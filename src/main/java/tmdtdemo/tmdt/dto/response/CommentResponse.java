package tmdtdemo.tmdt.dto.response;

import lombok.Data;

@Data
public class CommentResponse
{
    private Long cmtId;
    private String username;
    private String text;
    private String createdDate;
    private String level;
    private Long cmtParentId;
    private Long roomId;
    private String cmtParentUsername;
}

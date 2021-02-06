package pl.example.DownloadApp.utils.converters;

import pl.example.DownloadApp.model.Comment;
import pl.example.DownloadApp.webclient.dto.CommentDto;

import java.util.ArrayList;
import java.util.List;

public class CommentDtoToCommentConverter {

   public static List<Comment> convertCommentDtoToComment(List<CommentDto> commentDtoList) {
        List<Comment> commentList = new ArrayList<>();

        commentDtoList.forEach(commentDto -> {
            Comment comment = Comment.builder()
                    .id(commentDto.getId())
                    .postId(commentDto.getPostId())
                    .name(commentDto.getName())
                    .email(commentDto.getEmail())
                    .body(commentDto.getEmail())
                    .build();
            commentList.add(comment);
        });
        return commentList;

    }




}

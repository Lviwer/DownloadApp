package pl.example.DownloadApp.webclient.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CommentDto {

    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;


}

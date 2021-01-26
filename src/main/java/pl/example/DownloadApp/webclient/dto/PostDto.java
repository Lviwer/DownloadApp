package pl.example.DownloadApp.webclient.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class PostDto {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

}

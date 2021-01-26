package pl.example.DownloadApp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder

public class Post {

    public Post() {
    }

    public Post(Integer userId, Integer id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    private Integer userId;
    private Integer id;
    private String title;
    private String body;


}
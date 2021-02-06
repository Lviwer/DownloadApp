package pl.example.DownloadApp.webclient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.example.DownloadApp.model.Comment;
import pl.example.DownloadApp.model.Post;
import pl.example.DownloadApp.utils.Utils;
import pl.example.DownloadApp.utils.converters.CommentDtoToCommentConverter;
import pl.example.DownloadApp.utils.converters.PostDtoToPostConverter;
import pl.example.DownloadApp.webclient.dto.CommentDto;
import pl.example.DownloadApp.webclient.dto.PostDto;

import java.util.List;
import java.util.Objects;

    //with Post work too but... with PostDto if smth change in JSON we have more flexibility to choose what
    //data we want in our program

@Component
public class ClientHttp {

    private final String baseUrl = Utils.BASE_URL;
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String POSTS_PATH = "/posts";
    private static final String COMMENTS_BY_POST_PATH = "/comments?postId=";



    public List<Post> getPosts() {
        ResponseEntity<List<PostDto>> responseEntity =
                restTemplate.exchange(
                        baseUrl + POSTS_PATH,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<PostDto>>() {
                        });

        return PostDtoToPostConverter.convertPostDtoToPost(Objects.requireNonNull(responseEntity.getBody()));
    }


    public List<Comment> getCommentsByPost(int postId) {
        ResponseEntity<List<CommentDto>> responseEntity =
                restTemplate.exchange(
                        baseUrl + COMMENTS_BY_POST_PATH + postId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<CommentDto>>() {
                        });

        return CommentDtoToCommentConverter.convertCommentDtoToComment(Objects.requireNonNull(responseEntity.getBody()));
    }
}

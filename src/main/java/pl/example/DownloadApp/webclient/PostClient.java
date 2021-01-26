package pl.example.DownloadApp.webclient;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.example.DownloadApp.model.Post;
import pl.example.DownloadApp.webclient.dto.PostDto;


import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

@Component
public class PostClient {


    private static final String GET_POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private final RestTemplate restTemplate = new RestTemplate();

    //with Post work too but... with PostDto if smth change in JSON we have more flexibility
    public List<Post> getPosts() {
        ResponseEntity<List<PostDto>> responseEntity =
                restTemplate.exchange(
                        GET_POSTS_URL,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<PostDto>>() {
                        });

        return convertPostDtoToPost(Objects.requireNonNull(responseEntity.getBody()));
    }


    //taking one post is more safer than download all posts and take one from list
    public Post getPost(Integer id) throws HttpClientErrorException {

        PostDto postDto = callGetMethod(id.toString(), PostDto.class);
        return Post.builder()
                .userId(postDto.getUserId())
                .id(postDto.getId())
                .title(postDto.getTitle())
                .body(postDto.getBody())
                .build();

    }


    private <T> T callGetMethod(String postId, Class<T> responseType) throws HttpClientErrorException {
        return restTemplate.getForObject(GET_POSTS_URL + "/" + postId, responseType);
    }


    private List<Post> convertPostDtoToPost(List<PostDto> postDtoList) {
        List<Post> postList = new ArrayList<>();

        for (PostDto postDto : postDtoList) {
            Post post = Post.builder()
                    .userId(postDto.getUserId())
                    .id(postDto.getId())
                    .title(postDto.getTitle())
                    .body(postDto.getBody())
                    .build();

            postList.add(post);
        }

        return postList;
    }


}

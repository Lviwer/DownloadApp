package pl.example.DownloadApp.utils.converters;

import pl.example.DownloadApp.model.Post;
import pl.example.DownloadApp.webclient.dto.PostDto;

import java.util.ArrayList;
import java.util.List;

public class PostDtoToPostConverter {

    public static List<Post> convertPostDtoToPost(List<PostDto> postDtoList) {
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

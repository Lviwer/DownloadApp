package pl.example.DownloadApp.webclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.DownloadApp.model.Post;
import pl.example.DownloadApp.service.PostService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


// Tests works only if posts on website won't be changed
@SpringBootTest
class PostClientTest {

    @Autowired
    PostService postService;

    @Test
    void shouldGetOnePost() {

        Post testPost = Post.builder()
                .userId(10)
                .id(100)
                .title("at nam consequatur ea labore ea harum")
                .body("cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut")
                .build();

        Post postFromPostService = postService.getSinglePost(100);

        assertThat(testPost.getUserId()).isEqualTo(postFromPostService.getUserId());
        assertThat(testPost.getId()).isEqualTo(postFromPostService.getId());
        assertThat(testPost.getTitle()).isEqualTo(postFromPostService.getTitle());
        assertThat(testPost.getBody()).isEqualTo(postFromPostService.getBody());
    }

    @Test
    void shouldGetAllPosts() {

        List<Post> postList = postService.getPostList();
        assertThat(postList.size()).isEqualTo(100);
    }

    @Test
    void shouldGetCorrectPostList() {


        Post testPost_50 = Post.builder()
                .userId(5)
                .id(50)
                .title("repellendus qui recusandae incidunt voluptates tenetur qui omnis exercitationem")
                .body("error suscipit maxime adipisci consequuntur recusandae\nvoluptas eligendi et est et voluptates\nquia distinctio ab amet quaerat molestiae et vitae\nadipisci impedit sequi nesciunt quis consectetur")
                .build();

        List<Post> postList = postService.getPostList();
        Post postFromList = postList.get(49);

        assertThat(testPost_50.getUserId()).isEqualTo(postFromList.getUserId());
        assertThat(testPost_50.getId()).isEqualTo(postFromList.getId());
        assertThat(testPost_50.getTitle()).isEqualTo(postFromList.getTitle());
        assertThat(testPost_50.getBody()).isEqualTo(postFromList.getBody());

    }

}
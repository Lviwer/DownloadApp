package pl.example.DownloadApp.webclient;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.example.DownloadApp.model.Post;

import java.util.List;

// Tests pass only if posts on website won't be changed

@SpringBootTest
class ClientHttpTest {

    //  @Autowired
    ClientHttp clientHttp;

    @BeforeEach
    void setUp() {
        clientHttp = new ClientHttp();
    }

    @Test
    void shouldGetAll100Posts() {
        List<Post> posts = clientHttp.getPosts();
        Assertions.assertThat(posts.size()).isEqualTo(100);
    }


    @Test
    void shouldGetCorrectLastPost() {

        Post testPost = Post.builder()
                .userId(10)
                .id(100)
                .title("at nam consequatur ea labore ea harum")
                .body("cupiditate quo est a modi nesciunt soluta\nipsa voluptas error itaque dicta in\nautem qui minus magnam et distinctio eum\naccusamus ratione error aut")
                .build();

        List<Post> posts = clientHttp.getPosts();
        Assertions.assertThat(posts.get(99)).isEqualToComparingFieldByField(testPost);
    }

}

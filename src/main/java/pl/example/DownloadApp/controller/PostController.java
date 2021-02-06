package pl.example.DownloadApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.example.DownloadApp.service.PostService;


@RestController
@RequiredArgsConstructor
public class PostController {


    @Autowired
    private PostService postService;

    //http://localhost:8080/comments?howMany=10&folder=beata
    @GetMapping("/comments")
    public String getCommentsByPostId(@RequestParam int howMany, String folder) {
        postService.saveCommentsGroupedByDomain(howMany, folder);
        return "done";
    }


    @GetMapping("/posts")
    public String getAndSavePosts(@RequestParam(defaultValue = "AllPosts") String folder) {
        postService.saveAllPosts(folder);
        return "done";
    }

}

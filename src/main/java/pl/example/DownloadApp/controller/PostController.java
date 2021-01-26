package pl.example.DownloadApp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import pl.example.DownloadApp.model.Post;
import pl.example.DownloadApp.service.PostService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable int id) {
        try {
            return postService.getSinglePost(id);

        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/posts")
    public List<Post> getPostList() {
        return postService.getPostList();
    }


    @GetMapping("/download/posts")
    public String getAndSavePosts(@RequestParam(defaultValue = "AllPosts") String folder) {
        postService.getAndSaveAllPostsToFolder(folder);
        return "done";
    }

    @GetMapping("/download/posts/{id}")
    public String getAndSavePost(@PathVariable int id, @RequestParam(defaultValue = "OnePost") String folder) {
        try {
            postService.getAndSavePostToFolder(id, folder);
            return "done";

        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}

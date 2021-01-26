package pl.example.DownloadApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.example.DownloadApp.model.Post;
import pl.example.DownloadApp.utils.SavePostsToFiles;
import pl.example.DownloadApp.webclient.PostClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostClient postClient;
    private final SavePostsToFiles savePostsToFiles;

    public List<Post> getPostList() {
        return postClient.getPosts();
    }

    public Post getSinglePost(Integer id) throws HttpClientErrorException {
        return postClient.getPost(id);
    }

    public void getAndSavePostToFolder(int postID, String folderName) throws HttpClientErrorException {
        savePostsToFiles.savePostToFile(postClient.getPost(postID), folderName);
    }


    public void getAndSaveAllPostsToFolder(String folderName) {
        savePostsToFiles.savePostsToFiles(postClient.getPosts(), folderName);
    }


}

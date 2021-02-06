package pl.example.DownloadApp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.example.DownloadApp.model.Comment;
import pl.example.DownloadApp.model.Post;
import pl.example.DownloadApp.SaveToFiles;
import pl.example.DownloadApp.webclient.ClientHttp;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PostService {



    private final ClientHttp clientHttp;
    private final SaveToFiles saveToFiles;

    public List<Post> getPostList() {
        return clientHttp.getPosts();
    }


    public void saveAllPosts(String folderName) {
        saveToFiles.savePostsToFiles(clientHttp.getPosts(), folderName);
    }


    public void saveCommentsGroupedByDomain(int postCount, String folderToSaveName) {

        List<Post> postList = clientHttp.getPosts();
        Stream<Post> postStream = postList.stream().limit(postCount);
        Stream<Comment> commentStream = postStream.flatMap(p -> clientHttp.getCommentsByPost(p.getId()).stream());
        Map<String, List<Comment>> commentsGroupedByDomain = commentStream.collect(Collectors.groupingBy(comment ->
                comment.getEmail().substring(comment.getEmail().indexOf("@") + 1)));

        saveToFiles.saveCommentsToFiles(commentsGroupedByDomain, folderToSaveName);
    }

}

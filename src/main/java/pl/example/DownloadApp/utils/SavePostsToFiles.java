package pl.example.DownloadApp.utils;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.example.DownloadApp.model.Post;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SavePostsToFiles {

    public void savePostsToFiles(List<Post> postList, String folderName) {

        createNewFolder(folderName);

        String fileName;
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        for (Post p : postList) {
            try {
                fileName = p.getId().toString();
                writer.writeValue(new FileWriter(folderName + "/" + fileName + ".json"), p);

            } catch (IOException e) {
                System.out.println("There is some problem with saving files");
                e.printStackTrace();
            }
        }
    }


    public void savePostToFile(Post post, String folderName) {

        createNewFolder(folderName);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        String path = folderName + "/" + post.getId().toString() + ".json";
        try {
            writer.writeValue(new FileWriter(path), post);
        } catch (IOException e) {
            System.out.println("There is some problem with saving file");
            e.printStackTrace();
        }

    }

    private void createNewFolder(String folderName) {
        new File(folderName).mkdirs();
    }


}
package pl.example.DownloadApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.example.DownloadApp.model.Comment;
import pl.example.DownloadApp.model.Post;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class SaveToFiles {

    public void saveCommentsToFiles(Map<String, List<Comment>> commentsGroupedByDomain, String folderName) {

        folderName = validateFolderName(folderName);
        createNewFolder(folderName);
        String finalFolderName = folderName;

        commentsGroupedByDomain.forEach((domain, files) -> {
            files.forEach(comment -> {
                try {                                                                //TODO IF YOU WANT WITHOUT DOT ADD -> .replace(".", "") ***************
                    Files.writeString(Path.of(finalFolderName + "\\" + domain.toString() + ".json"), mapperToPrettyPrinterJson(comment));
                } catch (IOException e) {
                    System.out.println("There is some problem with saving files");
                    e.printStackTrace();
                }
            });
        });

    }


    public void savePostsToFiles(List<Post> postList, String folderName) {

        folderName = validateFolderName(folderName);
        createNewFolder(folderName);
        String fileName;

        for (Post p : postList) {
            try {
                fileName = p.getId().toString();
                Files.writeString(Path.of(folderName + "/" + fileName + ".json"), mapperToPrettyPrinterJson(p));

            } catch (IOException e) {
                System.out.println("There is some problem with saving files");
                e.printStackTrace();
            }
        }
    }


    private void createNewFolder(String folderName) {
        new File(folderName).mkdirs();
    }


    //If the first letter is correct folder name will be cut to first incorrect sign
    private String validateFolderName(String folderName) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_.-]*$"); // to exclude: ("\\/?%*:|\"<>")
        Matcher match = pattern.matcher(folderName);

        if (!match.find()) {
            return "DefaultNameForIncorrectParameterInput";
        } else return folderName;
    }


    private String mapperToPrettyPrinterJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }


}

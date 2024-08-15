package com.ssl.pem.listners;

import com.ssl.pem.MAIN.PemToDerConverter;
import com.ssl.pem.file.FileUploadResponse;
import com.ssl.pem.models.TaskStatus;
import com.ssl.pem.models.Tasks;
import com.ssl.pem.services.TaskServices;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAttachment;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class TaskListener implements MessageCreateListener {

    @Autowired
    private TaskServices taskService;

    @Autowired
    PemToDerConverter pemToDerConverter;

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        Message message = messageCreateEvent.getMessage();
        String messageContent = message.getContent();
        if (messageCreateEvent.getMessageAuthor().isBotUser()) {
            return;
        } else if (messageContent.startsWith("!task")) {
            String[] content = messageContent.split("!task ");
            Tasks task = new Tasks();
            task.setStatus(TaskStatus.ON_HOLD);
            task.setDescription(content[1]);
            taskService.save(task);

            new MessageBuilder()
                    .append("Created a new Task: [" + task.getId() + "]")
                    .appendCode("java", "Description: " + task.getDescription() + "\n" + "Status: " + task.getStatus())
                    .send(messageCreateEvent.getChannel());
        } else if( messageContent.startsWith("!pemToDer")){
            if (message.getAttachments().isEmpty()) {
                new MessageBuilder()
                        .append("Please attach a file to convert.")
                        .send(messageCreateEvent.getChannel());
                return;
            }
            try {
                MessageAttachment attachment = message.getAttachments().get(0);
                Path filePath = Paths.get("/Users/ashutoshsingh/pgdb_gcp/projects/pem/src/main/resources/" + attachment.getFileName());
                Files.write(filePath, attachment.downloadAsByteArray().join());
                File file = filePath.toFile();
                FileInputStream input = new FileInputStream(file);
                MultipartFile multipartFile = new MockMultipartFile(
                        attachment.getFileName(),
                        attachment.getFileName(),
                        "application/x-x509-ca-cert",
                        input
                );
                String result = pemToDerConverter.convertPemToDer(multipartFile);
                String arr[]= result.split("\\|");
                if(arr.length<=1){
                    new MessageBuilder()
                            .append("Cant convert this file to the expected format.")
                            .send(messageCreateEvent.getChannel());
                    return ;
                }

                filePath = Paths.get("/Users/ashutoshsingh/pgdb_gcp/projects/pem/src/main/resources/" + arr[0]);
                file = filePath.toFile();
                new MessageBuilder()
                        .append("file Name: "+arr[0])
                        .append("\nSize: "+arr[1])
                        .addAttachment(file)
                        .send(messageCreateEvent.getChannel());

            } catch (IOException e) {
                new MessageBuilder()
                        .append("Cant convert this file to the expected format.")
                        .send(messageCreateEvent.getChannel());
            }
        }


    }
}

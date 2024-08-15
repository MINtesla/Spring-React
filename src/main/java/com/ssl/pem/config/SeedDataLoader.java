package com.ssl.pem.config;

import com.ssl.pem.models.TaskStatus;
import com.ssl.pem.models.Tasks;
import com.ssl.pem.services.TaskServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SeedDataLoader implements CommandLineRunner {

    @Autowired
    TaskServices taskServices;

    @Override
    public void run(String... args) throws Exception {
         loadSeedData();
    }

    private void loadSeedData() {
        taskServices.deleteAll();

        Tasks task1 = new Tasks();
        task1.setDescription("TASK 1 to show at starting of screen");
        task1.setStatus(TaskStatus.ON_HOLD);

        Tasks task2 = new Tasks();
        task2.setDescription("TASK 2 to show at starting of screen");
        task2.setStatus(TaskStatus.IN_PROGRESS);

        taskServices.save(task1);
        taskServices.save(task2);
    }

}
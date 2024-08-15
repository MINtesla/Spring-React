package com.ssl.pem.controllers;

import com.ssl.pem.models.Tasks;
import com.ssl.pem.services.TaskServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskController {
    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskServices taskServices;

    @RequestMapping("/hello")
    @ResponseBody
    public String helloGFG()
    {
        return "Hello world Application is up and running";
    }

    @GetMapping("/tasks")
    public Iterable<Tasks> getTasks(){
        logger.info("GET Controller : getTasks");
        return taskServices.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Tasks> getTask(@PathVariable Long id) {
        logger.debug("GET TaskController::getTaskById: {}", id);
        Optional<Tasks> optionalTask = taskServices.getById(id);
        return optionalTask;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        logger.debug("DELETE TaskController::deleteTaskById: {}", id);
        taskServices.delete(id);
    }

    @PostMapping("/newTask")
    public ResponseEntity<?> createNewTask(@RequestBody Tasks newTask) {
        logger.debug("REST TaskController::createTask: {}", newTask);
        Tasks task = new Tasks();
        task.setDescription(newTask.getDescription());
        task.setStatus(newTask.getStatus());
        task = taskServices.save(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Tasks newTask) {
        logger.debug("REST TaskController::updateTask[{}]: {}", id, newTask);
        Optional<Tasks> optionalTask = taskServices.getById(id);
        if (optionalTask.isPresent()) {
            Tasks task = optionalTask.get();
            task.setDescription(newTask.getDescription());
            task.setStatus(newTask.getStatus());
            task = taskServices.save(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

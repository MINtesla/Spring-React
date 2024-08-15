package com.ssl.pem.services;

import com.ssl.pem.models.Tasks;
import com.ssl.pem.respositories.TaskRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TaskServices {

    @Autowired
    private TaskRepositories taskRepositories;

    public Iterable<Tasks> getAll(){
        return taskRepositories.findAll();
    }

    public Optional<Tasks> getById(Long id){
        return taskRepositories.findById(id);
    }

    public Tasks save(Tasks tasks){
        if(tasks.getId()==null){
            tasks.setCreateAt(LocalDateTime.now());
        }
        tasks.setUpdateAt(LocalDateTime.now());
        return taskRepositories.save(tasks);
    }

    public void delete(Long id){
        taskRepositories.deleteById(id);
    }

    public void deleteAll(){
        taskRepositories.deleteAll();
    }
}

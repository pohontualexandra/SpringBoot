package com.values.ToDoApp.service;

import com.values.ToDoApp.model.UserTask;
import com.values.ToDoApp.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserTaskService {
    @Autowired
    private UserTaskRepository userTaskRepository;

    public List<UserTask> findByUserId(Long userId) {
        return userTaskRepository.findByUserUserId(userId);
    }

    public void saveUserTask(UserTask userTask) {
        if (userTask.getStatus() == null) {
            userTask.setStatus("pending");
        }
        if (userTask.getDate() == null) {
            userTask.setDate(LocalDateTime.now());
        }
        userTaskRepository.save(userTask);
    }

    public void deleteUserTask(Long id) {
        userTaskRepository.deleteById(id);
    }
}



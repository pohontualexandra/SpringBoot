package com.values.ToDoApp.controller;

import com.values.ToDoApp.model.User;
import com.values.ToDoApp.model.UserTask;
import com.values.ToDoApp.repository.UserRepository;
import com.values.ToDoApp.repository.UserTaskRepository;
import com.values.ToDoApp.service.TaskService;
import com.values.ToDoApp.service.UserService;
import com.values.ToDoApp.service.UserTaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/userTasks")
public class UserTaskController {

    @Autowired
    private UserTaskService userTaskService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getAllUserTasks(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User principal = (User) authentication.getPrincipal();
        Long currentUserId = principal.getUserId();
        List<UserTask> userTasks = userTaskService.findByUserId(currentUserId);
        model.addAttribute("userTasks", userTasks);
        return "userTasks";
    }

    @PostMapping("/save")
    public String saveUserTask(@RequestParam("userId") String userId,
                               @RequestParam("taskId") String taskId,
                               @RequestParam("date") String dateStr) {
        UserTask userTask = new UserTask();
        userTask.setUser(userService.findByUserId(Long.parseLong(userId)));
        userTask.setTask(taskService.findById(Long.parseLong(taskId)));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
        userTask.setDate(date);

        userTaskService.saveUserTask(userTask);
        return "redirect:/userTasks";
    }


    @PostMapping("/delete/{id}")
    public String deleteUserTask(@PathVariable("id") Long id) {
        userTaskService.deleteUserTask(id);
        return "redirect:/userTasks";
    }
}
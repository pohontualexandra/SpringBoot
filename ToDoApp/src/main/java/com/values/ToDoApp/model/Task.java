package com.values.ToDoApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    private long taskId;
    @Column(name="task_name", nullable = false)
    private String taskName;

    @OneToMany(mappedBy = "task")
    private Set<UserTask> userTasks;
}

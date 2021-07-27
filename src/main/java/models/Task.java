package models;

import com.example.userTasks.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    private int id;
    private String name;
    private String description;
    private User user;
    private TaskStatus status;
    private Date deadline;
}

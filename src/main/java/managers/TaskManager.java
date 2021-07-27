package managers;

import com.example.userTasks.enums.TaskStatus;
import com.example.userTasks.enums.UserTypes;
import models.Task;
import models.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Connection connection = db.DBConnectionProvider.getProvider().getConnection();

    public void addTask(Task task) {
        try {
            String query = "INSERT INTO `task` (`name`,`description`,`user`,`status`,`deadline`) " +
                    "VALUES(?,?,?,?,?);";


            PreparedStatement pStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, task.getName());
            pStatement.setString(2, task.getDescription());
            pStatement.setInt(3, task.getUser().getId());
            pStatement.setString(4, task.getStatus().name());
            pStatement.setDate(5, Date.valueOf(sdf.format(task.getDeadline())));
            pStatement.executeUpdate();
            ResultSet generatedKeys = pStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                task.setId(id);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Task> getTasks() {
        String sql = "SELECT * from task";
        List<Task> result = new ArrayList<>();
        UserManager userManager = new UserManager();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Task task = Task.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .user(userManager.getUserById(resultSet.getInt(4)))
                        .status(TaskStatus.valueOf(resultSet.getString(5)))
                        .deadline(resultSet.getDate(6))
                        .build();
                result.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public Task getTaskById(int id) {
        String sql = "SELECT * from `task` WHERE  `id` = " + id;
        UserManager userManager = new UserManager();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Task.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .user(userManager.getUserById(resultSet.getInt(4)))
                        .status(TaskStatus.valueOf(resultSet.getString(5)))
                        .deadline(resultSet.getDate(6))
                        .build();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public List<Task> getTasksByUser(int id) {
        UserManager userManager = new UserManager();
        String sql = "SELECT * from task WHERE user = " + id;
        List<Task> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Task task = Task.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .user(userManager.getUserById(resultSet.getInt(4)))
                        .status(TaskStatus.valueOf(resultSet.getString(5)))
                        .deadline(resultSet.getDate(6))
                        .build();
                result.add(task);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public void deleteTask(int id) {
        String sql = "DELETE from task where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteTaskByUser(int userId) {
        String sql = "DELETE * from task where user = " + userId;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateTaskStatus(Task task) {
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE task SET STATUS ='%s' WHERE id =" + task.getId(),
                    task.getStatus());
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

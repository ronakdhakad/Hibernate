package com.servlet.dao;

import com.servlet.connection.DBConnection;
import com.servlet.dto.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    public void saveTask(Task t) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
            con.prepareStatement("INSERT INTO tasks(task_name,category,priority,status) VALUES (?,?,?,?)");

        ps.setString(1, t.getTaskName());
        ps.setString(2, t.getCategory());
        ps.setString(3, t.getPriority());
        ps.setString(4, "Pending");

        ps.executeUpdate();
        con.close();
    }

    public List<Task> getAllTasks() throws Exception {
        List<Task> list = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM tasks");

        while (rs.next()) {
            Task t = new Task();
            t.setId(rs.getInt("id"));
            t.setTaskName(rs.getString("task_name"));
            t.setCategory(rs.getString("category"));
            t.setPriority(rs.getString("priority"));
            t.setStatus(rs.getString("status"));
            list.add(t);
        }
        con.close();
        return list;
    }

    public void updateStatus(int id, String status) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
            con.prepareStatement("UPDATE tasks SET status=? WHERE id=?");
        ps.setString(1, status);
        ps.setInt(2, id);
        ps.executeUpdate();
        con.close();
    }

    public void deleteTask(int id) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
            con.prepareStatement("DELETE FROM tasks WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }
}

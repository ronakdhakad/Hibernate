package com.servlet.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.servlet.dao.TaskDao;
import com.servlet.dto.Task;
@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Task t = new Task();
        t.setTaskName(req.getParameter("task"));
        t.setCategory(req.getParameter("category"));
        t.setPriority(req.getParameter("priority"));

        try {
            new TaskDao().saveTask(t);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("viewTask");
    }
}

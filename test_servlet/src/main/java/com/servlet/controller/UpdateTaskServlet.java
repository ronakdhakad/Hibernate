package com.servlet.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.servlet.dao.TaskDao;
import com.servlet.dto.Task;
@WebServlet("/updateTask")
public class UpdateTaskServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String status = req.getParameter("status");

        try {
            new TaskDao().updateStatus(id, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("viewTask");
    }
}

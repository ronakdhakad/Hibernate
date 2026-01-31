package com.servlet.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.servlet.dao.TaskDao;
@WebServlet("/deleteTask")
public class DeleteTaskServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        try {
            new TaskDao().deleteTask(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("viewTask");
    }
}

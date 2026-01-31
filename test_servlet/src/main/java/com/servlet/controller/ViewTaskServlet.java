package com.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.servlet.dao.TaskDao;
import com.servlet.dto.Task;
@WebServlet("/viewTask")
public class ViewTaskServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html><body><table border='1'>");
        out.println("<tr><th>Task</th><th>Category</th><th>Priority</th><th>Status</th><th>Action</th></tr>");
      

        try {
            for (Task t : new TaskDao().getAllTasks()) {
                out.println("<tr>");
                out.println("<td>"+t.getTaskName()+"</td>");
                out.println("<td>"+t.getCategory()+"</td>");
                out.println("<td>"+t.getPriority()+"</td>");
                out.println("<td>"+t.getStatus()+"</td>");
                out.println("<td>"
                    + "<a href='updateTask?id="+t.getId()+"&status=Completed'>Complete</a> | "
                    + "<a href='deleteTask?id="+t.getId()+"'>Delete</a>"
                    + "</td>");
                out.println("<td class='status-pending'>" + t.getStatus() + "</td>");
                out.println("<td class='status-completed'>" + t.getStatus() + "</td>");
                out.println("</tr>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("</table></body></html>");
    }
}

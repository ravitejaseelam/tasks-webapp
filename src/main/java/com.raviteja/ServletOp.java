package com.raviteja;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ServletOp extends HttpServlet {
    public ServletOp()
    {
    }
    TaskManager taskManager = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,NullPointerException {
        PrintWriter out=resp.getWriter();
        List<Task> List=new ArrayList<>();
        String name =req.getParameter("Name");
        String id =req.getParameter("Id");
        int flag=0;
        try {
            taskManager = new TaskManager();
            List= taskManager.search(name);
        } catch (SQLException e) {
            out.println("hxfcvmbjfx");
        } catch (Exception e) {
            out.println("hcvbjhgchhj");
        }
        for (Task obj : List) {
            if (obj.id.equals(id)) {
                out.println("Id:" + obj.id);
                out.println("Name:" + obj.name);
                out.println("Description:" + obj.description);
                out.println("Due date:" + obj.duedate);
                out.println("Status:" + obj.status);
                out.println();
                flag++;
            }
        }
        if(List.isEmpty()||flag==0)
        {
            resp.setStatus(404);
            out.println("Not Found");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        List<Task> List=new ArrayList<>();
        String name =req.getParameter("Name");
        String id =req.getParameter("Id");
        int flag=0;
        try {
            taskManager = new TaskManager();
            List= taskManager.search(name);
        }
        catch (SQLException e) {}
        catch (Exception e) {}
        for (Task obj : List) {
            if (obj.id.equals(id)) {
                try {
                    taskManager.delete(name,id);
                    out.println("......Deleted.......");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                flag++;
            }
        }
        if(List.isEmpty()||flag==0)
        {
            resp.setStatus(404);
            out.println("Not Found");
        }
        resp.setStatus(202);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            taskManager = new TaskManager();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter out=resp.getWriter();
        int flag=0;
        String name= req.getParameter("Name");
        String id=req.getParameter("Id");
        String status1=req.getParameter("Status");
        List<Task>updateList = null;
        try {
            updateList = taskManager.search(name);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

            for (Task obj : updateList) {
                if (obj.id.equals(id)) {
                    Status status=null;
                    try{
                        status=Status.valueOf(status1);
                    }
                    catch(IllegalArgumentException e)
                    {
                        out.println(e);
                        flag++;
                        out.println("Plz type only from the above given options only, try again");
                    }
                    try {
                        taskManager.updateStatus(status, name, id);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    flag++;
                }
            }
            if(flag==0)
                out.println("No such ID found");
            else
                out.println(".............Updated...............");
    }
}

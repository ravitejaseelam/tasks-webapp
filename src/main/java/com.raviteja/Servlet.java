package com.raviteja;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataOutput;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Servlet extends HttpServlet {

    public Servlet(){
    }
    TaskManager tm = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,NullPointerException {
        PrintWriter out=resp.getWriter();
        List<Task> List=new ArrayList<>();
        try {
            tm = new TaskManager();
            List=tm.display();
        } catch (SQLException e) {
            out.println("hxfcvmbjfx");
        } catch (Exception e) {
            out.println("hcvbjhgchhj");
        }
        if(List.isEmpty())
        {
            resp.setStatus(404);
            out.println("Not Found");
        }
        JSONArray js=new JSONArray(List);

        out.println(js);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        try {
            String name = req.getParameter("Name");
            String dis = req.getParameter("Description");
            String sdueDate = req.getParameter("Due Date in in (DD/MM/YYYY) format");
            SimpleDateFormat sdfo = new SimpleDateFormat("dd/MM/yyyy");
            Date dueDate = null;
            dueDate = sdfo.parse(sdueDate);
            Status st = Status.valueOf("Assigned");
            Date dat = new Date();
            tm.add(name, dis, dat, st, dueDate);
            out.println("..........Added...........");
        }
        catch (NullPointerException e)
        {
            out.println(e);
            out.println("You are not allowed to leave any of the fields empty");
        }
        catch(ParseException p)
        {
            out.println(p);
            out.println("Plz Provide Date in the mentioned format");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

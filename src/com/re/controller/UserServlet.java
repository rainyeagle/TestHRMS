package com.re.controller;

import com.re.StatusCode;
import com.re.model.User;
import com.re.service.UserService;
import com.re.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (action.equals("loginCheck")) {
            User user = new User(username, password);
            String result = userService.checkLoginInfo(user);
            System.out.println(result);
        } else if (action.equals("registerCheck")) {
            System.out.println(username);
            User user = new User(username, null);
            String result = userService.checkUsername(user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result",result);
            System.out.println(jsonObject.toString());
            PrintWriter out = response.getWriter();
            out.print(jsonObject.toString());
            out.flush();
            out.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

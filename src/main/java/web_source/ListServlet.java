package web_source;

import db.UserDao;
import db.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ListServlet extends HttpServlet {

    public ListServlet(){}
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String n = request.getParameter("username");
        String p = request.getParameter("userpass");
        RequestDispatcher rd;
        if (UserDao.validate(n, p)) {
            rd = request.getRequestDispatcher("servlet2");
            rd.forward(request, response);
        } else {
            out.print("Sorry username or password error");
            rd = request.getRequestDispatcher("index.html");
            rd.include(request, response);
        }

        out.close();
    }
}


package web_source;

import db.UserDao;
import db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> accounts= new UserDao().findAllUsers();
        request.setAttribute("account", accounts);

        getServletContext().getRequestDispatcher("/jsp/add.jsp").forward(request, response);
    }
}

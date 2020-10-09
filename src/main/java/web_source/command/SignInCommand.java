package web_source.command;

import db.UserDao;
import db.entity.User;
import org.apache.log4j.Logger;
import web_source.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInCommand extends Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;
        User user =new User();

        String login = request.getParameter("userlogin");
        log.trace("Request parameter: loging --> " + login);

        String email = request.getParameter("username");
        log.trace("Request parameter: loging --> " + email);

        String password = request.getParameter("userpass");
        log.trace("Request parameter: loging --> " + password);
        if (login == null || password == null || email == null || login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        user.setId(1);
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        user.setBill(0);
        user.setRoleId(2);
        UserDao.insertUser(user);
        forward=Path.PAGE__WELCOME;
        request.setAttribute("login", login);
        return forward;
    }
}


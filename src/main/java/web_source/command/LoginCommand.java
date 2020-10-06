package web_source.command;
import db.Role;
import db.UserDao;
import db.entity.User;
import org.apache.log4j.Logger;
import web_source.Path;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // obtain login and password from the request
        String login = request.getParameter("username");
        log.trace("Request parameter: loging --> " + login);

        String password = request.getParameter("userpass");

        // error handler
        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        }

        User user = new UserDao().findUserByLogin(login);
        log.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            RequestDispatcher rd;
            Role userRole = Role.getRole(user);
            log.trace("userRole --> " + userRole);

           /* if (userRole == Role.ADMIN)
                forward = Path.COMMAND__LIST_ORDERS;

            if (userRole == Role.CLIENT)
                forward = Path.COMMAND__LIST_MENU;*/

            session.setAttribute("user", user);
            log.trace("Set the session attribute: user --> " + user);

            session.setAttribute("userRole", userRole);
            log.trace("Set the session attribute: userRole --> " + userRole);

            log.info("User " + user + " logged as " + userRole.toString().toLowerCase());
            forward=Path.PAGE__WELCOME;
        }

        log.debug("Command finished");
        return forward;
    }
}

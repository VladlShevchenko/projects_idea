package web_source.command;

import db.UserDao;
import db.entity.User;
import org.apache.log4j.Logger;
import web_source.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UnblockCommand extends Command{
    private static final Logger log = Logger.getLogger(UnblockCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String forward;
        try {
            String userId = request.getParameter("userId");
            log.trace("userId --> " + userId);
            System.out.println(userId);
            long accountId = Long.parseLong((userId));


            User user = UserDao.findUserById(accountId);
            user.setRoleId(2);
            UserDao.updateUser(user);
            System.out.println(user);
            forward= Path.PAGE__LIST_ACCOUNTS;

        }
        catch (NullPointerException ex){
            forward=Path.PAGE__ERROR_PAGE;
            String error="Oops!Something went wrong";
            request.setAttribute("error_message",error);

        }

        List<User> accounts = new UserDao().findOnlyUsers();
        log.trace("Found in DB: publicationList --> " + accounts);


        // put accounts list to the request
        request.setAttribute("account", accounts);
        log.trace("Set the request attribute: publications --> " + accounts);

        log.debug("Command finished");
        return forward;
    }
}

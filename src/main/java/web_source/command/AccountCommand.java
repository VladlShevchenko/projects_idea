package web_source.command;

import db.PublicationDao;
import db.entity.Publication;
import db.entity.User;
import org.apache.log4j.Logger;
import web_source.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountCommand extends Command {
    private static final Logger log = Logger.getLogger(PublicationViewCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");
        String forward=Path.PAGE__ERROR_PAGE;
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("user"));
        if(session.getAttribute("user")!=null){
        User account = (User) session.getAttribute("user");

        log.trace("user --> " + account);

        ArrayList<User> users=new ArrayList<User>();
        users.add(account);
        List<Publication> publicationsForUser=new PublicationDao().findPublicationForAccount(account.getId());

        // put user the request
        request.setAttribute("account", users);
        // put publication the request
        request.setAttribute("publication", publicationsForUser);
        log.trace("Set the request attribute: publications --> " + publicationsForUser);

        log.debug("Command finished");
            //checking roles
            if(account.getRoleId()==1)
             forward=Path.PAGE__ACCOUNT_ADMIN;
            else forward=Path.PAGE__ACCOUNT_USER;
        }
        else
            forward=Path.PAGE__LOGIN;
        return forward;
    }
}

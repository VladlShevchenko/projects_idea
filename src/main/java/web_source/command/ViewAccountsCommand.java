package web_source.command;

import db.UserDao;
import db.entity.User;
import org.apache.log4j.Logger;
import web_source.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewAccountsCommand extends Command {
    private static final Logger log = Logger.getLogger(ViewAccountsCommand.class);
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");

        // get accounts list
        List<User> accounts = new UserDao().findOnlyUsers();
        log.trace("Found in DB: publicationList --> " + accounts);


        // put accounts list to the request
        request.setAttribute("account", accounts);
        log.trace("Set the request attribute: publications --> " + accounts);

        log.debug("Command finished");
        return Path.PAGE__LIST_ACCOUNTS;
    }
}

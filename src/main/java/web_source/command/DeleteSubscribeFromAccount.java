package web_source.command;

import db.PublicationDao;
import db.ReceiptDao;
import db.entity.Publication;
import db.entity.Receipt;
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

public class DeleteSubscribeFromAccount extends Command {
    private static final Logger log = Logger.getLogger(DeleteSubscribeFromAccount.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String publicationId = request.getParameter("publicationId");
        log.trace("publicationList --> " + publicationId);

        int pubId=Integer.parseInt(publicationId);

        User user = (User) session.getAttribute("user");

        Receipt receipt = new ReceiptDao().findReceiptForCart(user.getId(),pubId);
        receipt.setStatusId(4);
        ReceiptDao.updateReceipt(receipt);


        // error handler
        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;
        ArrayList<User> users=new ArrayList<User>();
        users.add(user);
        //get user subscription
        List<Publication> publicationsForUser=new PublicationDao().findPublicationForAccount(user.getId());
        // put user the request
        request.setAttribute("account", users);
        // put publication the request
        request.setAttribute("publication", publicationsForUser);
        log.trace("Set the request attribute: publications --> " + publicationsForUser);

        //checking roles
        if(user.getRoleId()==1)
            forward=Path.PAGE__ACCOUNT_ADMIN;
        else forward=Path.PAGE__ACCOUNT_USER;


        List<Publication> publication = new PublicationDao().findPublicationForCart(user.getId());
        request.setAttribute("publication", publication);
        log.trace("Set the request attribute: publications --> " + publication);

        log.debug("Command finished");
        return Path.PAGE__CART;
    }
}

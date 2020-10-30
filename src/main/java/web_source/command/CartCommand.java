package web_source.command;

import db.*;
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
import java.util.Collections;
import java.util.List;

public class CartCommand extends Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String forward = Path.PAGE__ERROR_PAGE;

        // obtain login and password from the request
        if(request.getParameter("publication_id")!=null) {
            int publicationId = Integer.parseInt(request.getParameter("publication_id"));
            log.trace("Request parameter: loging --> " + publicationId);

            //create new order
            Receipt newReceipt = new Receipt();
            newReceipt.setUserId(user.getId());
            newReceipt.setStatusId(2);
            ReceiptDao.insertReceipt(newReceipt);

            //add this order to the publication
            ReceiptDao.insertReceiptHasPublication(newReceipt.getId(), publicationId);

            if (session.getAttribute("check") != null) {
                System.out.println(session.getAttribute("check").equals(newReceipt));
                if (session.getAttribute("check").equals(newReceipt))
                    new ReceiptDao().deleteReceipt(newReceipt.getId());
            } else
                session.setAttribute("check", newReceipt);


            // error handler
            String errorMessage = null;


            List<Publication> publications = Collections.singletonList(PublicationDao.findPublicationById(publicationId));
            log.trace("Found in DB: user --> " + publications);

        }
       List<Publication> publication = new PublicationDao().findPublicationForCart(user.getId());
        System.out.println(publication);
        request.setAttribute("publication", publication);
        log.trace("Set the request attribute: publications --> " + publication);
        //checking roles
        if(user.getRoleId()==1||user.getRoleId()==2)
            forward=Path.PAGE__CART;
        else{
            forward=Path.PAGE__ERROR_PAGE;
            String error="You must sign in to buy something";
            request.setAttribute("error_message",error);
        }

        log.debug("Command finished");
        return forward;
    }
}
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
import java.util.Collections;
import java.util.List;

public class PublicationViewCommand extends Command {
    private static final Logger log = Logger.getLogger(PublicationViewCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");
        String forward=Path.PAGE__ERROR_PAGE;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String publicationId = request.getParameter("view");


        log.trace("publicationList --> " + publicationId);

        int pubId=Integer.parseInt(publicationId);

        List<Publication> publication= Collections.singletonList(PublicationDao.findPublicationById(pubId));

        log.trace("Found in DB: receipt List --> " + publication);
        try {
            Receipt receipt= ReceiptDao.findReceiptForCart(user.getId(), pubId);
            if(receipt.getStatusId()!=0){
                request.setAttribute("statusId", 0);
            }
            else
                request.setAttribute("statusId", 1);
            System.out.println(ReceiptDao.findReceiptForCart(user.getId(), pubId));
        }catch (NullPointerException ex){
            String error="Oops!Something went wrong";
            request.setAttribute("error_message",error);
        }

        // put publication items list to the request
        request.setAttribute("publication", publication);
        log.trace("Set the request attribute: publications --> " + publication);
        if(user!=null) {
            if (user.getRoleId() == 1 || user.getRoleId() == 2) {
                forward = Path.PAGE__PUBLICATION_VIEW;
            }
            else
                forward=Path.PAGE__PUBLICATION_VIEW_GUEST;
        }
        else
            forward=Path.PAGE__PUBLICATION_VIEW_GUEST;
        log.debug("Command finished");
        return forward;
    }
}

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

public class DeleteItemFromCartCommand extends Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String publicationId = request.getParameter("publicationId");
        log.trace("publicationList --> " + publicationId);
        int pubId=Integer.parseInt(publicationId);

        User user = (User) session.getAttribute("user");

        Receipt receipt = new ReceiptDao().findReceiptForCart(user.getId(),pubId);
        new ReceiptDao().deleteReceipt(receipt.getId());


        // error handler
        String errorMessage = null;
        String forward = Path.PAGE__ERROR_PAGE;

        List<Publication> publications = Collections.singletonList(PublicationDao.findPublicationById(pubId));
        log.trace("Found in DB: user --> " + publications);


        List<Publication> publication = new PublicationDao().findPublicationForCart(user.getId());
        request.setAttribute("publication", publication);
        log.trace("Set the request attribute: publications --> " + publication);

        log.debug("Command finished");
        return Path.PAGE__CART;
    }
}

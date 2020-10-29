package web_source.command;

import db.PublicationDao;
import db.ReceiptDao;
import db.UserDao;
import db.entity.Publication;
import db.entity.Receipt;
import db.entity.Topic;
import db.entity.User;
import org.apache.log4j.Logger;
import web_source.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuyCommand extends Command {
    private static final Logger log = Logger.getLogger(BuyCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");
        String forward;
        float totalBill=0;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        log.trace("user --> " + user);
        //delete last symbol "53.0/"
        String sum = request.getParameter("amount");
        StringBuilder sb=new StringBuilder();
        sb.append(sum);
        sb.deleteCharAt(sb.length()-1);
        sum= sb.toString();

        float amount= Float.parseFloat(sum);

        if(user.getBill()<amount){
            forward= Path.PAGE__ERROR_PAGE;
            String error="You should have more money to make this purchaise!";
            request.setAttribute("error_message",error);
        }
        else{
        totalBill= user.getBill()-amount;
        user.setBill(totalBill);
            UserDao.updateUser(user);
            List<Receipt> receipts = new ReceiptDao().findReceiptForBuy(user.getId());
            for (Receipt item : receipts) {
                item.setStatusId(1);
                ReceiptDao.updateReceipt(item);
            }

            List<Publication> publicationsForView = new PublicationDao().findPublications();
            request.setAttribute("publication", publicationsForView);

            List<Topic> topic = PublicationDao.findTopic();
            request.setAttribute("topic",topic);

            log.trace("Set the request attribute: publications --> " + publicationsForView);

            log.debug("Command finished");
            forward=  Path.PAGE__LIST_MENU;
        }


            log.debug("Command finished");

        return forward;
    }
}

package web_source.command;

import db.PublicationDao;
import db.entity.Publication;
import db.entity.Topic;
import org.apache.log4j.Logger;
import web_source.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SelectByParamCommand extends Command {
    private static final Logger log = Logger.getLogger(PublicationCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command starts");

        String parameter = request.getParameter("paramId");
        log.trace("Request parameter: pubName --> " + parameter);
        int parameterId= Integer.parseInt(parameter);
        System.out.println(parameterId);

        //let's find publications with this topic
        List<Publication> publicationsByParam=null;
        if(parameterId==1) {
            publicationsByParam = PublicationDao.sortPublicationsByName();
        }
        else if(parameterId==2)
            publicationsByParam = PublicationDao.sortPublicationsByNameDesk();
        else if(parameterId==3)
            publicationsByParam = PublicationDao.sortPublicationsByPrice();
        else if(parameterId==4)
            publicationsByParam = PublicationDao.sortPublicationsByPriceDesc();
        else publicationsByParam=PublicationDao.findPublications();
        request.setAttribute("publication", publicationsByParam);

        List<Topic> topic = PublicationDao.findTopic();
        request.setAttribute("topic",topic);

        return Path.PAGE__LIST_MENU;
    }
}

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

public class SortByTopicCommand extends Command {
    private static final Logger log = Logger.getLogger(PublicationCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("Command starts");

        String parameter = request.getParameter("topicId");
        log.trace("Request parameter: pubName --> " + parameter);
        int topicId= Integer.parseInt(parameter);
        System.out.println(topicId);

        //let's find publications with this topic

        List<Publication> publicationsByTopic=PublicationDao.findPublicationByTopic(topicId);
        request.setAttribute("publication", publicationsByTopic);

        List<Topic> topic = PublicationDao.findTopic();
        request.setAttribute("topic",topic);

        return Path.PAGE__LIST_MENU;
    }
}

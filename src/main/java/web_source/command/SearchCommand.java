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
import java.util.Collections;
import java.util.List;

public class SearchCommand extends Command {
    private static final Logger log = Logger.getLogger(SearchCommand.class);
    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");
        String pubName = request.getParameter("pubName");
        log.trace("Request parameter: pubName --> " + pubName);
        List<Publication> publication;
        // get publications list

             publication= Collections.singletonList(new PublicationDao().findPublicationByName(pubName));
            log.trace("Found in DB: publicationList --> " + publication);
        if (publication==null){
            publication = PublicationDao.findPublications();
        }
        //find topics for drop-down list
        List<Topic> topic = PublicationDao.findTopic();
        request.setAttribute("topic",topic);
        // put publications to the request
        request.setAttribute("publication", publication);
        log.trace("Set the request attribute: publications --> " + publication);

        log.debug("Command finished");
        return Path.PAGE__LIST_MENU;
    }
}


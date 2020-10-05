package web_source.command;
import db.PublicationDao;
import db.entity.Publication;
import org.apache.log4j.Logger;
import web_source.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PublicationCommand extends Command {
    private static final Logger log = Logger.getLogger(PublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");

        // get publications list
        List<Publication> publication = new PublicationDao().findPublications();
        log.trace("Found in DB: publicationList --> " + publication);

        // sort publications by topic
       /* Collections.sort(publication, new Comparator<Publication>() {
            public int compare(Publication o1, Publication o2) {
                return (int) (o1.getTopicId() - o2.getTopicId());
            }
        });*/

        // put menu items list to the request
        request.setAttribute("publication", publication);
        log.trace("Set the request attribute: publications --> " + publication);

        log.debug("Command finished");
        return Path.PAGE__LIST_MENU;
    }
    }

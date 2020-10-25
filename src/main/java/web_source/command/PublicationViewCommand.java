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
import java.util.List;

public class PublicationViewCommand extends Command {
    private static final Logger log = Logger.getLogger(PublicationViewCommand.class);

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        log.debug("Command starts");

        String publicationId = request.getParameter("view");
        /*StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(publicationId);*/
        log.trace("publicationList --> " + publicationId);
        System.out.println(publicationId);
        int pubId=Integer.parseInt(publicationId);
        List<Publication> publication= Collections.singletonList(PublicationDao.findPublicationById(pubId));
        log.trace("Found in DB: publicationList --> " + publication);


        // put publication items list to the request
        request.setAttribute("publication", publication);
        log.trace("Set the request attribute: publications --> " + publication);

        log.debug("Command finished");
        return Path.PAGE__PUBLICATION_VIEW;
    }
}
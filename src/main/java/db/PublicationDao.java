package db;

import com.mysql.cj.jdbc.Blob;
import db.entity.Publication;
import db.entity.Receipt;
import db.entity.Topic;
import db.entity.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationDao {
    private static final String SQL__FIND_ALL_PUBLICATIONS =
            "SELECT * FROM publication";

    private static final String SQL__FIND_PULICATION_BY_NAME =
            "SELECT * FROM publication WHERE name LIKE ?";
    private static final String SQL__FIND_PULICATION_BY_Id =
            "SELECT * FROM publication WHERE id=?";

    private static final String SORT_PUBLICATIONS_BY_NAME_ASC =
            "SELECT * FROM publication ORDER BY name";

    private static final String SORT_PUBLICATIONS_BY_NAME_DESK =
            "SELECT * FROM publication ORDER BY name DESK";

    private static final String SORT_PUBLICATIONS_BY_PRICE =
            "SELECT * FROM publication ORDER BY price_for_mounth";
    private static final String SORT_PUBLICATIONS_BY_PRICE_DESK =
            "SELECT * FROM publication ORDER BY price_for_mounth DESK";
    private static final String SQL__FIND_PUBLICATIONS_BY_RECEIPT =
            "select * from publication where id in (select publication_id from receipt_has_publication where receipt_id=?)";

    private static final String SQL__FIND_ALL_TOPICS =
            "SELECT * FROM topic";

    /**
     * Returns all categories.
     *
     * @return List of topic entities.
     */
    public List<Topic> findTopic() {
        List<Topic> topicList = new ArrayList<Topic>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            TopicMapper mapper = new TopicMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_ALL_TOPICS);
            while (rs.next())
                topicList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return topicList;
    }


    /**
     * Returns all menu items.
     *
     * @return List of publications entities.
     */
    public static List<Publication> findPublications() {
        List<Publication> publicationsList = new ArrayList<Publication>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            PublicationMapper mapper = new PublicationMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_ALL_PUBLICATIONS);
            while (rs.next())
                publicationsList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publicationsList;
    }

    ////Sorting methods!!!
    public List<Publication> sortPublicationsByName() {
        List<Publication> publicationsList = new ArrayList<Publication>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PublicationMapper mapper = new PublicationMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SORT_PUBLICATIONS_BY_NAME_ASC);
            while (rs.next())
                publicationsList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publicationsList;
    }

    public List<Publication> sortPublicationsByNameDesk() {
        List<Publication> publicationsList = new ArrayList<Publication>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PublicationMapper mapper = new PublicationMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SORT_PUBLICATIONS_BY_NAME_DESK);
            while (rs.next())
                publicationsList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publicationsList;
    }
    public List<Publication> sortPublicationsByPrice() {
        List<Publication> publicationsList = new ArrayList<Publication>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PublicationMapper mapper = new PublicationMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SORT_PUBLICATIONS_BY_PRICE);
            while (rs.next())
                publicationsList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publicationsList;
    }
  ////Sorting methods!!!
    /**
     * Returns publications of the given receipt.
     *
     * @param receipt Receipt entity.
     * @return List of publications entities.
     */
    public List<Publication> findPublication(Receipt receipt) {
        List<Publication> publicationsList = new ArrayList<Publication>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PublicationMapper mapper = new PublicationMapper();
            pstmt = con.prepareStatement(SQL__FIND_PUBLICATIONS_BY_RECEIPT);
            pstmt.setLong(1, receipt.getId());
            rs = pstmt.executeQuery();
            while (rs.next())
                publicationsList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publicationsList;
    }
    public static Publication findPublicationByName(String name) {
        Publication publication = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            PublicationDao.PublicationMapper mapper = new PublicationDao.PublicationMapper();
            pstmt = con.prepareStatement(SQL__FIND_PULICATION_BY_NAME);
            pstmt.setString(1, "%"+name+"%");
            rs = pstmt.executeQuery();
            if (rs.next())
                publication=mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publication;
    }

    public static Publication findPublicationById(int id) {
        Publication publication = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            PublicationDao.PublicationMapper mapper = new PublicationDao.PublicationMapper();
            pstmt = con.prepareStatement(SQL__FIND_PULICATION_BY_Id);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                publication=mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publication;
    }
    /**
     * Returns publications with given identifiers.
     *
     * @param ids Identifiers of publications.
     * @return List of publications entities.
     */
    public List<Publication> findMenuItems(String[] ids) {
        List<Publication> publicationsList = new ArrayList<Publication>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PublicationMapper mapper = new PublicationMapper();

            // create SQL query like "... id IN (1, 2, 7)"
            StringBuilder query = new StringBuilder(
                    "SELECT * FROM menu WHERE id IN (");
            for (String idAsString : ids)
                query.append(idAsString).append(',');
            query.deleteCharAt(query.length() - 1);
            query.append(')');

            stmt = con.createStatement();
            rs = stmt.executeQuery(query.toString());
            while (rs.next())
                publicationsList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publicationsList;
    }

    /**
     * Extracts a topic from the result set row.
     */
    private static class TopicMapper implements EntityMapper<Topic> {

        @Override
        public Topic mapRow(ResultSet rs) {
            try {
                Topic topic = new Topic();
                topic.setId(rs.getInt(Constant.ENTITY__ID));
                topic.setName(rs.getString(Constant.TOPIC__NAME));
                return topic;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Extracts a publication from the result set row.
     */
    private static class PublicationMapper implements EntityMapper<Publication> {

        @Override
        public Publication mapRow(ResultSet rs) {
            try {
                Publication publication = new Publication();
                publication.setId(rs.getInt(Constant.ENTITY__ID));
                publication.setName(rs.getString(Constant.PUBLICATION__NAME));
                publication.setPriceForMonth(rs.getInt(Constant.PUBLICATION__PRICE_FOR_MONTH));
                publication.setImage((Blob) rs.getBlob(Constant.PUBLICATION__IMAGE));
                publication.setDescription(rs.getString(Constant.PUBLICATION__DESCRIPTION));
                publication.setTopicId(rs.getLong(Constant.PUBLICATION__TOPIC_ID));
                return publication;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}

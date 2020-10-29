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
    private static final String SQL__FIND_ALL_PUBLICATIONS_WITH_LIMIT =
            "SELECT * FROM publication LIMIT ?,?";

    private static final String SQL__FIND_PUBLICATIONS_BY_ACCOUNT =
            "select mydb.publication.id ,mydb.receipt.id,mydb.publication.name, mydb.publication.price_for_mounth\n" +
                    "from mydb.publication \t\n" +
                    "\t\tjoin mydb.receipt_has_publication \n" +
                    "\t\ton receipt_has_publication.publication_id=publication.id\n" +
                    "\t\tjoin mydb.receipt \n" +
                    "\t\ton  receipt.id=receipt_has_publication.receipt_id\n" +
                    "            where mydb.receipt.account_id=? and mydb.receipt.status_id=2";

    private static final String SQL__FIND_PUBLICATIONS_FOR_USER =
            "select mydb.publication.id ,mydb.receipt.id , mydb.publication.name, mydb.publication.price_for_mounth from mydb.publication \tjoin mydb.receipt_has_publication  on receipt_has_publication.publication_id=publication.id join mydb.receipt  on  receipt.id=receipt_has_publication.receipt_id where mydb.receipt.account_id=? and mydb.receipt.status_id=1;";

    private static final String SQL__FIND_PULICATION_BY_NAME =
            "SELECT * FROM publication WHERE name LIKE ?";

    private static final String SQL__FIND_PULICATION_BY_TOPIC =
            "SELECT * FROM publication WHERE topic_id=?";
    private static final String SQL__FIND_PULICATION_BY_Id =
            "SELECT * FROM publication WHERE id=?";

    private static final String SORT_PUBLICATIONS_BY_NAME_ASC =
            "SELECT * FROM publication ORDER BY name";

    private static final String SORT_PUBLICATIONS_BY_NAME_DESC =
            "SELECT * FROM publication ORDER BY name DESC";

    private static final String SORT_PUBLICATIONS_BY_PRICE =
            "SELECT * FROM publication ORDER BY price_for_mounth";
    private static final String SORT_PUBLICATIONS_BY_PRICE_DESK =
            "SELECT * FROM publication ORDER BY price_for_mounth DESC";
    private static final String SQL__FIND_PUBLICATIONS_BY_RECEIPT =
            "select * from publication where id in (select publication_id from receipt_has_publication where receipt_id=?)";

    private static final String SQL__FIND_ALL_TOPICS =
            "SELECT * FROM mydb.topic";

    /**
     * Returns all categories.
     *
     * @return List of topic entities.
     */
    public static List<Topic> findTopic() {
        List<Topic> topicList = new ArrayList<Topic>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
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


    /**
     * method for pagination
     * Returns selected amount of items.
     *
     * @return List of publications entities.
     */
    public static List<Publication> findPublicationsForPagination(int currentPage, int recordsPerPage) {
        List<Publication> publicationsList = new ArrayList<Publication>();
        int start = currentPage * recordsPerPage - recordsPerPage;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            PublicationMapper mapper = new PublicationMapper();
            pstmt = con.prepareStatement(SQL__FIND_ALL_PUBLICATIONS_WITH_LIMIT);
            pstmt.setInt(1, start);
            pstmt.setInt(2, recordsPerPage);
            rs = pstmt.executeQuery();
            while (rs.next())
                publicationsList.add(mapper.mapRow(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publicationsList;
    }
    /**
     * Returns selected amount of items.
     *
     * @return List of publications by account id.
     */
    public static List<Publication> findPublicationForCart(int account_id) {
        List<Publication> publications = new ArrayList<Publication>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            PublicationDao.PublicationMapperForCart mapper = new PublicationDao.PublicationMapperForCart();
            pstmt = con.prepareStatement(SQL__FIND_PUBLICATIONS_BY_ACCOUNT);
            pstmt.setInt(1, account_id);
            rs = pstmt.executeQuery();
            while (rs.next())
                publications.add(mapper.mapRow(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publications;
    }
    /**
     * Returns selected amount of items.
     *
     * @return List of publications with status id = 1.
     */
    public static List<Publication> findPublicationForAccount(int account_id) {
        List<Publication> publications = new ArrayList<Publication>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            PublicationDao.PublicationMapperForCart mapper = new PublicationDao.PublicationMapperForCart();
            pstmt = con.prepareStatement(SQL__FIND_PUBLICATIONS_FOR_USER);
            pstmt.setInt(1, account_id);
            rs = pstmt.executeQuery();
            while (rs.next())
                publications.add(mapper.mapRow(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publications;
    }



    ////Sorting methods!!!
    /**
     * sorting methods
     *
     * @return List of publications entities sorted by name.
     */
    public static List<Publication> sortPublicationsByName() {
        List<Publication> publicationsList = new ArrayList<Publication>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
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
    /**
     * sorting methods
     *
     * @return List of publications entities sorted by name desc.
     */
    public static List<Publication> sortPublicationsByNameDesk() {
        List<Publication> publicationsList = new ArrayList<Publication>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            PublicationMapper mapper = new PublicationMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SORT_PUBLICATIONS_BY_NAME_DESC);
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
     * sorting methods
     *
     * @return List of publications entities sorted by price.
     */
    public static List<Publication> sortPublicationsByPrice() {
        List<Publication> publicationsList = new ArrayList<Publication>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
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
    /**
     * sorting methods
     *
     * @return List of publications entities sorted by price desc.
     */
    public static List<Publication> sortPublicationsByPriceDesc() {
        List<Publication> publicationsList = new ArrayList<Publication>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            PublicationMapper mapper = new PublicationMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SORT_PUBLICATIONS_BY_PRICE_DESK);
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
    /**
     * Returns all publication items with some name
     *
     * @return List of publications.
     */
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
    /**
     * Returns all publication items by topic id
     *
     * @return List of publications.
     */
    public static List<Publication> findPublicationByTopic(int topicId) {
        List<Publication> publicationsList = new ArrayList<Publication>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            PublicationDao.PublicationMapper mapper = new PublicationDao.PublicationMapper();
            pstmt = con.prepareStatement(SQL__FIND_PULICATION_BY_TOPIC);
            pstmt.setInt(1, topicId);
            rs = pstmt.executeQuery();
            while (rs.next())
                publicationsList.add(mapper.mapRow(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return publicationsList;
    }

    /**
     * Returns all publication items by publication id
     *
     * @return List of publications.
     */
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
                publication.setImage( rs.getString(Constant.PUBLICATION__IMAGE));
                publication.setDescription(rs.getString(Constant.PUBLICATION__DESCRIPTION));
                publication.setTopicId(rs.getLong(Constant.PUBLICATION__TOPIC_ID));
                return publication;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
    /**
     * Extracts a publication from the result set row for cart.
     */
    private static class PublicationMapperForCart implements EntityMapper<Publication> {

        @Override
        public Publication mapRow(ResultSet rs) {
            try {
                Publication publication = new Publication();
                publication.setId(rs.getInt(Constant.ENTITY__ID));
                publication.setName(rs.getString(Constant.PUBLICATION__NAME));
                publication.setPriceForMonth(rs.getInt(Constant.PUBLICATION__PRICE_FOR_MONTH));
                return publication;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}

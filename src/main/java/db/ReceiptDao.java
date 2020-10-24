package db;

import com.mysql.cj.jdbc.Blob;
import db.entity.Publication;
import db.entity.Receipt;
import db.entity.Topic;
import db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceiptDao {
    private static final String SQL__FIND_ALL_RECEIPTS =
            "SELECT * FROM receipt";

    public static void insertReceipt(Receipt receipt) {
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String query1 = "INSERT INTO receipt(account_id,status_id)  VALUES (?,?);";
        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();
            stmt = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, receipt.getUserId());
            stmt.setInt(2, receipt.getStatusId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {receipt.setId(rs.getInt(1));}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DBManager.getInstance().rollbackAndClose(conn);
        }
        finally {
            DBManager.getInstance().commitAndClose(conn);
        }
    }

    public static void insertReceiptHasPublication(int receipt_id,int publication_id) {
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String query1 = "INSERT INTO receipt_has_publication(receipt_id,publication_id)  VALUES (?,?);";
        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();
            stmt = conn.prepareStatement(query1);
            stmt.setInt(1, receipt_id);
            stmt.setInt(2, publication_id);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DBManager.getInstance().rollbackAndClose(conn);
        }
        finally {
            DBManager.getInstance().commitAndClose(conn);
        }
    }


    public static List<Receipt> findReceipts() {
        List<Receipt> receiptList = new ArrayList<Receipt>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            ReceiptDao.ReceiptMapper mapper = new ReceiptDao.ReceiptMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_ALL_RECEIPTS);
            while (rs.next())
                receiptList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return receiptList;
    }


    private static class ReceiptMapper implements EntityMapper<Receipt> {

        @Override
        public Receipt mapRow(ResultSet rs) {
            try {
                Receipt receipt = new Receipt();
                receipt.setId(rs.getInt(Constant.ENTITY__ID));
                receipt.setUserId(rs.getLong(Constant.RECEIPT__USER_ID));
                receipt.setStatusId(rs.getInt(Constant.RECEIPT__STATUS_ID));
                return receipt;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }

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
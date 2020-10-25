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

    private static final String SQL_UPDATE_RECEIPT =
            "UPDATE receipt SET account_id=?, status_id=?"+
                    "	WHERE id=?";

    private static final String SQL__FIND_RECEIPTS_FOR_CART =
            "select \n" +
                    "  mydb.receipt.id ,\n" +
                    "  mydb.receipt.account_id,\n" +
                    "  mydb.receipt.status_id\n" +
                    "  from mydb.receipt \t\n" +
                    "  join mydb.receipt_has_publication \n" +
                    "  on  receipt.id=receipt_has_publication.receipt_id\n" +
                    "  join mydb.publication  \n" +
                    "  on receipt_has_publication.publication_id=publication.id \n" +
                    "  where mydb.receipt.account_id=? And mydb.publication.id=?  ";

    private static final String SQL__FIND_RECEIPTS_FOR_BUY =
            "select \n" +
                    "  mydb.receipt.id ,\n" +
                    "  mydb.receipt.account_id,\n" +
                    "  mydb.receipt.status_id\n" +
                    "  from mydb.receipt \t\n" +
                    "  join mydb.receipt_has_publication \n" +
                    "  on  receipt.id=receipt_has_publication.receipt_id\n" +
                    "  join mydb.publication  \n" +
                    "  on receipt_has_publication.publication_id=publication.id \n" +
                    "  where mydb.receipt.account_id=? and mydb.receipt.status_id=2";

    private static final String SQL__DELETE_RECEIPT =
            "delete from mydb.receipt where receipt.id=?";

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
    //for future delete
    public static Receipt findReceiptForCart(int account_id,int publication_id) {
        Receipt receipt = new Receipt();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            ReceiptDao.ReceiptMapper mapper = new ReceiptDao.ReceiptMapper();
            pstmt = con.prepareStatement(SQL__FIND_RECEIPTS_FOR_CART);
            pstmt.setInt(1, account_id);
            pstmt.setInt(2, publication_id);
            rs = pstmt.executeQuery();
            if (rs.next())
                receipt=mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return receipt;
    }

    //find receipts for buying
    public static List<Receipt> findReceiptForBuy(int account_id) {
        ArrayList<Receipt> receipts = new ArrayList<Receipt>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            ReceiptDao.ReceiptMapper mapper = new ReceiptDao.ReceiptMapper();
            pstmt = con.prepareStatement(SQL__FIND_RECEIPTS_FOR_BUY);
            pstmt.setInt(1, account_id);
            rs = pstmt.executeQuery();
            while (rs.next())
                receipts.add(mapper.mapRow(rs));
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return receipts;
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
    public static void updateReceipt(Receipt receipt) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            updateReceipt(con, receipt);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }
    public static void updateReceipt(Connection con, Receipt receipt) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_RECEIPT);
        int k = 1;
        pstmt.setLong(k++, receipt.getUserId());
        pstmt.setInt(k++, receipt.getStatusId());
        pstmt.setLong(k, receipt.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public void deleteReceipt(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();
            pstmt = conn.prepareStatement(SQL__DELETE_RECEIPT);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
        catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(conn);
            ex.printStackTrace();
        }
        finally{
            DBManager.getInstance().commitAndClose(conn);
        }
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


}
package db;


import db.entity.Publication;
import db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for User entity.
 */
public class UserDao {

    private static final String SQL__FIND_USER_BY_LOGIN =
            "SELECT * FROM account WHERE login=?";
    private static final String SQL__FIND_USER_BY_LOGIN_AND_PASS =
            "select * from account where login=? and password=?";
    private static final String SQL__FIND_USERS =
            "SELECT * FROM account";

    private static final String SQL__FIND_USER_BY_ID =
            "SELECT * FROM account WHERE id=?";

    private static final String SQL_UPDATE_USER =
            "UPDATE account SET login=?, email=?, password=?, bill=?"+
                    "	WHERE id=?";

    /**
     * Returns a user with the given identifier.
     *
     * @param id
     *            User identifier.
     * @return User entity.
     */
    public static User findUser(Long id) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
          DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    public static boolean validate(String name, String pass) {
        boolean status=false;
        try{

            Connection con = DBManager.getInstance().getConnectionWithDriverManager();

            PreparedStatement ps=con.prepareStatement(SQL__FIND_USER_BY_LOGIN_AND_PASS);
            ps.setString(1,name);
            ps.setString(2,pass);

            ResultSet rs=ps.executeQuery();
            status=rs.next();

        }catch(Exception e){System.out.println(e);}
        return status;
    }


    public static List<User> findAllUsers() {
        List<User> userList = new ArrayList<User>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            UserDao.UserMapper mapper = new UserDao.UserMapper();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL__FIND_USERS);
            while (rs.next())
                userList.add(mapper.mapRow(rs));
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return userList;
    }
    /**
     * Returns a user with the given login.
     *
     * @param login
     *            User login.
     * @return User entity.
     */
    public static User findUserByLogin(String login) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL__FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    /**
     * Update user.
     *
     * @param user
     *            user to update.
     */
    public static void updateUser(User user) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnectionWithDriverManager();
            updateUser(con, user);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    // //////////////////////////////////////////////////////////
    // Entity access methods (for transactions)
    // //////////////////////////////////////////////////////////

    /**
     * Update user.
     *
     * @param user
     *            user to update.
     * @throws SQLException
     */
    public static void updateUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER);
        int k = 1;
        pstmt.setString(k++, user.getLogin());
        pstmt.setString(k++, user.getEmail());
        pstmt.setString(k++, user.getPassword());
        pstmt.setFloat(k++, user.getBill());
        pstmt.setLong(k, user.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    public static void insertUser(User petrov) {
        PreparedStatement stmt = null;
        Connection conn = null;
        ResultSet rs = null;
        String query1 = "INSERT INTO account(login,email,password,bill,role_id)  VALUES (?,?,?,?,?);";
        try {
            conn = DBManager.getInstance().getConnectionWithDriverManager();
            stmt = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, petrov.getLogin());
            stmt.setString(2, petrov.getEmail());
            stmt.setString(3, petrov.getPassword());
            stmt.setFloat(4, petrov.getBill());
            stmt.setInt(5, petrov.getRoleId());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {petrov.setId(rs.getInt(1));}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DBManager.getInstance().rollbackAndClose(conn);
        }
        finally {
            DBManager.getInstance().commitAndClose(conn);
        }
    }


        /**
         * Extracts a user from the result set row.
         */
    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getInt(Constant.ENTITY__ID));
                user.setLogin(rs.getString(Constant.USER__LOGIN));
                user.setEmail(rs.getString(Constant.USER__EMAIL));
                user.setPassword(rs.getString(Constant.USER__PASSWORD));
                user.setBill(rs.getFloat(Constant.USER__BILL));
                user.setRoleId(rs.getInt(Constant.USER__ROLE_ID));
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

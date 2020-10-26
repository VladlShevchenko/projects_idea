package web_source;

public final class Path {

    // pages
    public static final String PAGE__WELCOME = "/index.jsp";
    public static final String PAGE__ERROR_PAGE = "jsp/errorPage.jsp";
    public static final String PAGE__LIST_MENU = "jsp/publications.jsp";
    public static final String PAGE__LIST_ACCOUNTS = "jsp/admin/userTable.jsp";
    public static final String PAGE__WELCOME_ADMIN = "jsp/admin/welcomeAdmin.jsp";
    public static final String PAGE__WELCOME_USER = "jsp/user/welcomeUser.jsp";
    public static final String PAGE__PUBLICATION_VIEW = "jsp/publicationView.jsp";
    public static final String PAGE__PUBLICATION_VIEW_GUEST = "jsp/guestPublicationView.jsp";

    // commands
    public static final String COMMAND__LIST_ORDERS = "/controller?command=listOrders";
    public static final String COMMAND__LIST_MENU = "/controller?command=listMenu";

    public static final String PAGE__CART ="jsp/cart.jsp";
    public static final String PAGE__ACCOUNT_ADMIN ="jsp/admin/account.jsp";
    public static final String PAGE__ACCOUNT_USER ="jsp/user/account.jsp";
    public static final String PAGE__LOGIN ="/login.html";
}

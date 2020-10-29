package db.entity;

public class User extends Entity {

    private String login;

    private String password;

    private float bill;

    private String email;

    private int roleId;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBill(float bill) {
        this.bill = bill;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public float getBill() {
        return bill;
    }

    public String getEmail() {
        return email;
    }

    public int getRoleId() {
        return roleId;
    }

    @Override
    public String toString() {
        return "User [login=" + login + ", bill=" + bill + ", email=" + email
                + ", roleId=" + roleId + "]";
    }

}
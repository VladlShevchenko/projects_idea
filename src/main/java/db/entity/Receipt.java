package db.entity;

public class Receipt extends Entity {
    private static final long serialVersionUID = 5692708766041889396L;

    private int userId;

    private int statusId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Order [userId=" + userId + ", statusId="
                + statusId + ", getId()=" + getId() + "]";
    }

}

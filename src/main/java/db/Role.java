package db;

import db.entity.User;

public enum Role {
    G,ADMIN, USER;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}

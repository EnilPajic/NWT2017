package com.enil.haberusers.Entity.Request;

import com.enil.haberusers.Entity.User;

public class AddUserRequest {
    public long ID;
    public String token, oauth, userid, username, email;
    public String img24, img32, img48, img72, img192;
    public boolean owner = false, admin = false;
    public int active = 1;
    public User ToUser()
    {
        return new User(
                token, oauth, userid, username, email, img24, img32, img48, img72, img192,
                owner, admin, active
        );
    }

    @Override
    public String toString() {
        return "AddUserRequest{" +
                "ID=" + ID +
                ", token='" + token + '\'' +
                ", oauth='" + oauth + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", img24='" + img24 + '\'' +
                ", img32='" + img32 + '\'' +
                ", img48='" + img48 + '\'' +
                ", img72='" + img72 + '\'' +
                ", img192='" + img192 + '\'' +
                ", owner=" + owner +
                ", admin=" + admin +
                ", active=" + active +
                '}';
    }
}


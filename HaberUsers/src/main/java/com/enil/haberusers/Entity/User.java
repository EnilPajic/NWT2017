package com.enil.haberusers.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table (name="TOKENS")
public class User {
    public User() {

    }

    public long GetID() {
        return ID;
    }

    public void SetID(long ID) {
        this.ID = ID;
    }

    public String GetToken() {
        return token;
    }

    public void SetToken(String token) {
        this.token = token;
    }

    public String GetOauth() {
        return oauth;
    }

    public void SetOauth(String oauth) {
        this.oauth = oauth;
    }

    public String GetUserid() {
        return userid;
    }

    public void SetUserid(String userid) {
        this.userid = userid;
    }

    public String GetUsername() {
        return username;
    }

    public void SetUsername(String username) {
        this.username = username;
    }

    public String GetEmail() {
        return email;
    }

    public void SetEmail(String email) {
        this.email = email;
    }

    public String GetIMG24() {
        return img24;
    }

    public void SetIMG24(String IMG24) {
        this.img24 = IMG24;
    }

    public String GetIMG32() {
        return img32;
    }

    public void SetIMG32(String IMG32) {
        this.img32 = IMG32;
    }

    public String GetIMG48() {
        return img48;
    }

    public void SetIMG48(String IMG48) {
        this.img48 = IMG48;
    }

    public String GetIMG72() {
        return img72;
    }

    public User(String TOKEN, String OAUTH, String USERID, String USERNAME,
                String EMAIL, String IMG24, String IMG32, String IMG48, String IMG72, String IMG192,
                boolean owner, boolean admin, int active) {
        this.token = TOKEN;
        this.oauth = OAUTH;
        this.userid = USERID;
        this.username = USERNAME;
        this.email = EMAIL;
        this.img24 = IMG24 == null ? "" : IMG24;
        this.img32 = IMG32 == null ? "" : IMG32;
        this.img48 = IMG48 == null ? "" : IMG48;
        this.img72 = IMG72 == null ? "" : IMG72;
        this.img192 = IMG192 == null ? "" : IMG192;
        this.owner = owner;
        this.admin = admin;
        this.active = active;
    }

    public void SetIMG72(String IMG72) {
        this.img72 = IMG72;
    }

    public String GetIMG192() {
        return img192;
    }

    public void SetIMG192(String IMG192) {
        this.img192 = IMG192;
    }

    public boolean IsOwner() {
        return owner;
    }

    public void SetOwner(boolean owner) {
        this.owner = owner;
    }

    public boolean GetAdmin() {
        return admin;
    }

    public void SetAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", token='" + token + '\'' +
                ", oauth='" + oauth + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", img192='" + img192 + '\'' +
                ", owner=" + owner +
                ", admin=" + admin +
                '}';
    }

    @Id
    @GeneratedValue
    public long ID;
    public String token, oauth, userid, username, email;
    public String img24, img32, img48, img72, img192;
    public boolean owner, admin;
    public int active;

    public void SetRegistered(Timestamp registered) {
        this.registered = registered;
    }

    @DateTimeFormat (pattern = "d.m.Y H:M:s")
    public Timestamp registered;

    public void SetDate(Timestamp date) {
        this.date = date;
    }

    public Timestamp date;
    public int GetActive() {
        return active;
    }

    public void SetActive(int active) {
        this.active = active;
    }



}

package com.enil.haberservices.Entity;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Votes
{
    @Id
    @GeneratedValue
    public long id;

    public String votekey = "", ip = "", browser = "", user;

    public Boolean valid = true;
    public Timestamp date = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn (name = "votefor")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonProperty (value = "for")
    @JsonIdentityReference (alwaysAsId = true)
    public VoteItems votefor;
    private class VF {
        public Long id; public String name;
        public VF(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
    @JsonProperty ("votedfor")
    public VF VotedFor ()
    {
        return new VF (votefor.GetId(), votefor.GetName());
    }
    @JsonProperty ("surveyinfo")
    public VoteItems.SI SurveyInfo()
    {
        return votefor.SurveyInfo();
    }
    public Votes() {}

    public long GetId() {
        return id;
    }

    public void SetId(long id) {
        this.id = id;
    }

    public String GetVotekey() {
        return votekey;
    }

    public void SetVotekey(String votekey) {
        this.votekey = votekey;
    }

    public String GetIp() {
        return ip;
    }

    public void SetIp(String ip) {
        this.ip = ip;
    }

    public String GetBrowser() {
        return browser;
    }

    public void SetBrowser(String browser) {
        this.browser = browser;
    }

    public String GetUser() {
        return user;
    }

    public void SetUser(String user) {
        this.user = user;
    }

    public Boolean GetValid() {
        return valid;
    }

    public void SetValid(Boolean valid) {
        this.valid = valid;
    }

    public Timestamp GetDate() {
        return date;
    }

    public void SetDate(Timestamp date) {
        this.date = date;
    }

    public VoteItems GetVotefor() {
        return votefor;
    }

    public void SetVotefor(VoteItems votefor) {
        this.votefor = votefor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Votes)) return false;

        Votes votes = (Votes) o;

        if (id != votes.id) return false;
        if (!votekey.equals(votes.votekey)) return false;
        if (!ip.equals(votes.ip)) return false;
        if (!browser.equals(votes.browser)) return false;
        if (!user.equals(votes.user)) return false;
        if (!valid.equals(votes.valid)) return false;
        if (!date.equals(votes.date)) return false;
        return votefor != null ? votefor.equals(votes.votefor) : votes.votefor == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + votekey.hashCode();
        result = 31 * result + ip.hashCode();
        result = 31 * result + browser.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + valid.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + (votefor != null ? votefor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Votes{" +
                "id=" + id +
                ", votekey='" + votekey + '\'' +
                ", ip='" + ip + '\'' +
                ", browser='" + browser + '\'' +
                ", user='" + user + '\'' +
                ", valid=" + valid +
                ", date=" + date +
                ", votefor=" + votefor +
                '}';
    }
}

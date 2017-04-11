package com.enil.haberservices.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "voteitems")
public class VoteItems
{
    @Id
    @GeneratedValue
    public long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn (name = "survey")
    public Survey survey;

    public class SI
    {
        public Long id;
        public String name, description;
        public SI(Long id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }
    }
    @JsonProperty ("survey")
    public SI SurveyInfo ()
    {
        return new SI (survey.id, survey.name, survey.description);
    }

    public String name;
    @Column (name = "extra_varchar")
    public String extravarchar = "";

    @Column (name = "extra_json")
    public String extrajson = "";

    @Column (name = "extra_int")
    public Integer extraint = 0;

    public boolean active = false;

    @OneToMany (mappedBy = "votefor", cascade = CascadeType.ALL)
    public List<Votes> votes;

    public VoteItems() {}

    public long GetId() {
        return id;
    }

    public void SetId(long id) {
        this.id = id;
    }

    public Survey GetSurvey() {
        return survey;
    }

    public void SetSurvey(Survey survey) {
        this.survey = survey;
    }

    public String GetName() {
        return name;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public String GetExtravarchar() {
        return extravarchar;
    }

    public void SetExtravarchar(String extravarchar) {
        this.extravarchar = extravarchar;
    }

    public String GetExtrajson() {
        return extrajson;
    }

    public void SetExtrajson(String extrajson) {
        this.extrajson = extrajson;
    }

    public int GetExtraint() {
        return extraint;
    }

    public void SetExtraint(int extraint) {
        this.extraint = extraint;
    }

    public boolean IsActive() {
        return active;
    }

    public void SetActive(boolean active) {
        this.active = active;
    }

    public List<Votes> GetVotes() {
        return votes;
    }

    public void SetVotes(List<Votes> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "VoteItems{" +
                "id=" + id +
                ", survey=" + survey +
                ", name='" + name + '\'' +
                ", extravarchar='" + extravarchar + '\'' +
                ", extrajson='" + extrajson + '\'' +
                ", extraint=" + extraint +
                ", active=" + active +
                ", votes=" + votes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteItems)) return false;

        VoteItems voteItems = (VoteItems) o;

        if (id != voteItems.id) return false;
        if (extraint != voteItems.extraint) return false;
        if (active != voteItems.active) return false;
        if (survey != null ? !survey.equals(voteItems.survey) : voteItems.survey != null) return false;
        if (!name.equals(voteItems.name)) return false;
        if (extravarchar != null ? !extravarchar.equals(voteItems.extravarchar) : voteItems.extravarchar != null)
            return false;
        if (extrajson != null ? !extrajson.equals(voteItems.extrajson) : voteItems.extrajson != null) return false;
        return votes != null ? votes.equals(voteItems.votes) : voteItems.votes == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (survey != null ? survey.hashCode() : 0);
        result = 31 * result + name.hashCode();
        result = 31 * result + (extravarchar != null ? extravarchar.hashCode() : 0);
        result = 31 * result + (extrajson != null ? extrajson.hashCode() : 0);
        result = 31 * result + extraint;
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (votes != null ? votes.hashCode() : 0);
        return result;
    }
}

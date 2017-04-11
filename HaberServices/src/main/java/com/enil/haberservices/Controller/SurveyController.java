package com.enil.haberservices.Controller;

import com.enil.haberservices.Entity.Survey;
import com.enil.haberservices.Entity.VoteItems;
import com.enil.haberservices.Entity.Votes;
import com.enil.haberservices.Repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping ("/survey")
public class SurveyController
{
    private SurveyRepository srepo;
    @Autowired
    public SurveyController(SurveyRepository srepo) { this.srepo = srepo; }

    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity GetAllSurveys ()
    {
        List<Survey> all = srepo.findAll();
        if (all == null || all.isEmpty())
            return new ResponseEntity<>(Collections.singletonMap("Error", "Trenutno nema anketa!"),
                    HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
    @RequestMapping (method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity GetSurvey (@PathVariable ("id") Long id)
    {
        Survey s = srepo.findOne(id);
        if (s == null)
            return new ResponseEntity<>(Collections.singletonMap("Error", "Anketa ne postoji! ID: " + id),
                    HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
    @RequestMapping (method = RequestMethod.GET, path = "/votes/{uid}")
    public ResponseEntity GetVotesForUser (@PathVariable ("uid") String uid)
    {
        ResponseEntity<?> r = GetAllSurveys();
        if (r.getStatusCode() != HttpStatus.OK)
            return r;
        List<Votes> votes = new ArrayList<>();
        List<Survey> ss = (List<Survey>)r.getBody();
        for (Survey s : ss)
            for (VoteItems vi : s.voteitems)
                for (Votes vote : vi.votes)
                    if (vote.GetUser().equals(uid))
                        votes.add(vote);
        if (votes.isEmpty())
            return new ResponseEntity<>(Collections.singletonMap("Error", "Korisnik nema glasova ili korisnik ne postoji! UID: " + uid),
                    HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(votes, HttpStatus.OK);
    }
}

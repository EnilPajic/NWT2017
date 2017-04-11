package com.enil.haberusers.Controller;


import com.enil.haberusers.Entity.Request.AddUserRequest;
import com.enil.haberusers.Entity.User;
import com.enil.haberusers.Repository.UserRepository;
import com.sun.deploy.ui.ProgressDialog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sun.rmi.runtime.Log;

import javax.jws.soap.SOAPBinding;
import java.net.URI;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping ("/users")
public class UserController
{

    private RestTemplate rest = new RestTemplate();
    @Value("${enil.conf.rest.services.uri:nema}")
    private String services;
    private UserRepository urepo;
    @Autowired
    public UserController(UserRepository urepo) {
        this.urepo = urepo;
    }
    @RequestMapping (method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity ByID (@PathVariable ("id") long id)
    {
        User x = urepo.findOne(id);
        if (x == null)
            return new ResponseEntity<>(Collections.singletonMap("Error", "Korisnik sa IDom (" + id + ") ne postoji"),
                    HttpStatus.NOT_FOUND);
        return new ResponseEntity<> (x, HttpStatus.OK);
    }
    @RequestMapping (method = RequestMethod.GET)
    public ResponseEntity AllUsers ()
    {
        List<User> x = urepo.findAll();
        if (x == null || x.isEmpty())
            return new ResponseEntity<>(Collections.singletonMap("Error", "Nema korisnika"),
                    HttpStatus.NOT_FOUND);
        return new ResponseEntity<> (x, HttpStatus.OK);
    }
    @RequestMapping (method = RequestMethod.POST)
    public ResponseEntity AddUser (@RequestBody AddUserRequest auq, UriComponentsBuilder ucb)
    {
        User x = urepo.findByUserid(auq.userid);
        if (x != null)
            return new ResponseEntity<>(Collections.singletonMap("Error", "Korisnik sa tim UIDom (" + auq.userid + ") već postoji"),
                    HttpStatus.CONFLICT);
        User u = auq.ToUser();
        u.SetDate(new Timestamp(System.currentTimeMillis()));
        u.SetRegistered(new Timestamp(System.currentTimeMillis()));
        urepo.saveAndFlush(u);
        HttpHeaders h = new HttpHeaders();
        h.setLocation(URI.create(ucb.toUriString() + "/users/uid/" + u.GetUserid()));
        return new ResponseEntity<> (u, h, HttpStatus.CREATED);
    }

    @RequestMapping (method = RequestMethod.DELETE)
    public ResponseEntity DeleteUser (@RequestBody AddUserRequest auq)
    {
        User x = urepo.findByUserid(auq.userid);
        if (x == null)
            return new ResponseEntity<>(Collections.singletonMap("Error", "Korisnik ne postoji, UID: " + auq.userid),
                    HttpStatus.NOT_FOUND);
        urepo.delete(x.GetID());
        return new ResponseEntity<> (Collections.singletonMap("Success", "Korisnik uspješno obrisan! UID: " + auq.userid),
                HttpStatus.OK);
    }
    @RequestMapping (method = RequestMethod.PUT)
    public ResponseEntity UpadeteUser (@RequestBody AddUserRequest auq)
    {
        User x = urepo.findByUserid(auq.userid);
        if (x == null)
            return new ResponseEntity<>(Collections.singletonMap("Error", "Korisnik ne postoji, UID: " + auq.userid),
                    HttpStatus.NOT_FOUND);

        x.SetActive(auq.active);
        x.SetAdmin(auq.admin);
        x.SetOwner(auq.owner);
        x.SetEmail(auq.email);
        x.SetIMG24(auq.img24);
        x.SetIMG32(auq.img32);
        x.SetIMG48(auq.img48);
        x.SetIMG72(auq.img72);
        x.SetIMG192(auq.img192);
        x.SetOauth(auq.oauth);
        x.SetToken(auq.token);
        x.SetUsername(auq.token);
        x.SetDate(new Timestamp(System.currentTimeMillis()));
        urepo.flush();
        return new ResponseEntity<> (x, HttpStatus.OK);
    }

    @RequestMapping (value = "/uid/{i}", method = RequestMethod.GET)
    public ResponseEntity UID (@PathVariable ("i") String uid) throws Exception {
        User u = urepo.findByUserid(uid);
        if (u == null)
            return new ResponseEntity<>(Collections.singletonMap("Error", "Korisnik nije pronađen! UID: " + uid) ,HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping (value = "/uid/{i}/votes", method = RequestMethod.GET)
    public ResponseEntity VotesByUID (@PathVariable ("i") String uid) throws Exception {
        ResponseEntity<?> r = UID (uid);
        if (r.getStatusCode() != HttpStatus.OK)
            return r;
        User u = (User)r.getBody();
        Object s = rest.getForObject(services + "/survey/votes/" + u.GetUserid(), Object.class);
        //Object s = rest.getForObject("http://HABERSERVICES/survey/votes/" + u.GetUserid(), Object.class);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @RequestMapping (value = "/uname/{i}", method = RequestMethod.GET)
    public ResponseEntity UName (@PathVariable ("i") String uid,
                             @RequestParam (value = "match", defaultValue = "false", required = false) boolean match)
    {
        List<User> l = null;
        if (match)
        {
            l =  urepo.findByUsernameContaining(uid);
            if (l == null || l.isEmpty())
                return new ResponseEntity<>(Collections.singletonMap("Error", "Nema korisnika sa tim substringom! (Match = True)"),
                        HttpStatus.NOT_FOUND);
            return new ResponseEntity<List<User>> (l, HttpStatus.OK);
        }

        List<User> k = null;
        k = urepo.findByUsername(uid);
        if (k == null || k.isEmpty())
            return new ResponseEntity<>(Collections.singletonMap("Error", "Ne postoje korisnici sa usernameom '" + uid + "'!"),
                    HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<User>> (k, HttpStatus.OK);
    }

}

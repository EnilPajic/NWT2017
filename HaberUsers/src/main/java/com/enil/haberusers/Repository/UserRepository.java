package com.enil.haberusers.Repository;

import com.enil.haberusers.Entity.User;
import com.oracle.webservices.internal.api.message.PropertySet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;
import org.springframework.data.rest.*;

import javax.persistence.FieldResult;
import java.util.List;

//@RepositoryRestResource (collectionResourceRel = "TOKENS", path = "tokens")
@Repository
public interface UserRepository extends JpaRepository<User, Long>
{

    User findByUserid(String USERID);
    List<User> findByUsername(String USERNAME);
    List<User> findByUsernameContaining (String USERNAME);

}


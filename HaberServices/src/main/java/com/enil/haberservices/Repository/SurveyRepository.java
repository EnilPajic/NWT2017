package com.enil.haberservices.Repository;


import com.enil.haberservices.Entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long>
{

}

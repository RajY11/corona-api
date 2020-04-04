package com.r3tech.springbootcovidapi.service;

import com.r3tech.springbootcovidapi.models.CoronaDetails;
import com.r3tech.springbootcovidapi.models.SubUser;
import com.r3tech.springbootcovidapi.repository.CoronaDetailsRepository;
import com.r3tech.springbootcovidapi.repository.SubUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class MailService {

    private SubUserRepository subUserRepository;
    private CoronaDetailsRepository coronaDetailsRepository;
    private List<SubUser> listOfSubscribers;
    private List<CoronaDetails> coronaDetails;

    public MailService(){}
    @Autowired
    public MailService(SubUserRepository subUserRepository,CoronaDetailsRepository coronaDetailsRepository){
        this.subUserRepository = subUserRepository;
        this.coronaDetailsRepository = coronaDetailsRepository;
    }

    @PostConstruct
    public void loadSubscribersData(){
        //listOfSubscribers = subUserRepository.findAll();

        listOfSubscribers = Arrays.asList(new SubUser(1,"rajuy@gmail.com",Arrays.asList("India","Canada")),new SubUser(2,"rajuyasa@gmail.com",Arrays.asList("India","US")));

        listOfSubscribers.stream().forEach(subUser -> {
            coronaDetails = coronaDetailsRepository.findAllByCountry1In(subUser.getCountries());
            prepareData(subUser.getEmailId(),coronaDetails);
        });

    }

    public void prepareData(String emailId, List<CoronaDetails> coronaDetails){
        System.out.println("for " + emailId);
        coronaDetails.stream().forEach(detail->{
            System.out.println(detail.toString());
        });

    }

    public void sendData(){

    }
}

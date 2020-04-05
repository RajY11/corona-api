package com.r3tech.springbootcovidapi.service;

import com.r3tech.springbootcovidapi.models.CoronaDetails;
import com.r3tech.springbootcovidapi.models.MailContent;
import com.r3tech.springbootcovidapi.models.SubUser;
import com.r3tech.springbootcovidapi.repository.CoronaDetailsRepository;
import com.r3tech.springbootcovidapi.repository.SubUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        listOfSubscribers = subUserRepository.findAll();

        //listOfSubscribers = Arrays.asList(new SubUser(1,"rajuy369@gmail.com",Arrays.asList("India","Canada")),new SubUser(2,"rajuyasa11@gmail.com",Arrays.asList("India","US")));

        listOfSubscribers.stream().forEach(subUser -> {
            coronaDetails = coronaDetailsRepository.findAllByCountry1In(subUser.getCountries());
            prepareData(subUser.getEmailId(),coronaDetails);
        });

    }

    public void prepareData(String emailId, List<CoronaDetails> coronaDetails){
        StringBuilder summary = new StringBuilder();
        coronaDetails.stream().forEach(detail->{

            summary.append(detail.getSummary() + "\n");

        });

        MailContent content = new MailContent();
        content.setEmailId(emailId);
        content.setContent(summary.toString());
        content.setSubject("CORONA UPDATE");

        sendData(content);

    }

    public void sendData(MailContent content){
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("calling api..........................................");
        String response = restTemplate.postForObject("http://localhost:8082/mail", content, String.class);
    }
}

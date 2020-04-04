package com.r3tech.springbootcovidapi.service;

import com.r3tech.springbootcovidapi.models.*;
import com.r3tech.springbootcovidapi.repository.*;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import org.apache.commons.csv.CSVFormat;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaService {


    // can be improved with other type of configuration of url like properties file or spring cloud config etc
    private static final String CONFIRM_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static final String DEATH_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    private static final String RECOVER_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    private List<CoronaData> data;
    private List<DeathData> deathData;
    private List<RecoveryData> recoveryData;

    private DeathRepository deathRepository;
    private RecoveryRepository recoveryRepository;
    private CoronaRepository repository;
    private CoronaDetailsRepository detailsRepository;
    private SubUserRepository subUserRepository;

    public CoronaService(){}

    @Autowired
    public CoronaService(CoronaRepository repository,DeathRepository deathRepository,RecoveryRepository recoveryRepository,CoronaDetailsRepository detailsRepository,SubUserRepository subUserRepository){
        this.repository = repository;
        this.deathRepository = deathRepository;
        this.recoveryRepository = recoveryRepository;
        this.detailsRepository = detailsRepository;
        this.subUserRepository = subUserRepository;
    }

    // can be improved by keeping data loading generic logic into one method., I am focusing on functionality more

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void loadConfirmData() throws IOException, InterruptedException {
        List<CoronaData> tempData = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestConfirm = HttpRequest.newBuilder()
                .uri(URI.create(CONFIRM_URL))
                .build();
        HttpResponse<String> responseConfirm = client.send(requestConfirm,HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(responseConfirm.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for(CSVRecord record: records){
            CoronaData cdata = new CoronaData();
            cdata.setCountry(record.get("Country/Region"));
            cdata.setState(record.get("Province/State"));
            cdata.setReportedCases(Integer.parseInt(record.get(record.size()-1)));

            tempData.add(cdata);
        }
        this.data = tempData;
        repository.saveAll(tempData);
    }
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void loadDeathData() throws IOException, InterruptedException {
        List<DeathData> tempData = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest requestDeath = HttpRequest.newBuilder()
                .uri(URI.create(DEATH_URL))
                .build();
        HttpResponse<String> responseConfirm = client.send(requestDeath,HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(responseConfirm.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for(CSVRecord record: records){
            DeathData cdata = new DeathData();
            cdata.setCountry(record.get("Country/Region"));
            cdata.setState(record.get("Province/State"));
            cdata.setNumberOfDeaths(Integer.parseInt(record.get(record.size()-1)));
            tempData.add(cdata);
        }
        this.deathData = tempData;
        deathRepository.saveAll(tempData);

    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void loadRecoverData() throws IOException, InterruptedException {
        List<RecoveryData> tempData = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RECOVER_URL))
                .build();
        HttpResponse<String> responseConfirm = client.send(request,HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyReader = new StringReader(responseConfirm.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for(CSVRecord record: records){
            RecoveryData cdata = new RecoveryData();
            cdata.setCountry(record.get("Country/Region"));
            cdata.setState(record.get("Province/State"));
            cdata.setNumberOfRecovery(Integer.parseInt(record.get(record.size()-1)));
            tempData.add(cdata);
        }
        this.recoveryData = tempData;
        recoveryRepository.saveAll(tempData);

    }

    public List<CoronaDetails> getAllData() {
        return detailsRepository.findAll();
    }

    public SubUser saveSubuser(SubUser subUser) {
        subUserRepository.save(subUser);
        return subUser;
    }
}

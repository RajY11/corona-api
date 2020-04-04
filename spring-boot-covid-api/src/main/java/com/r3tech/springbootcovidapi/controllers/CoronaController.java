package com.r3tech.springbootcovidapi.controllers;


import com.r3tech.springbootcovidapi.models.CoronaData;
import com.r3tech.springbootcovidapi.models.CoronaDetails;
import com.r3tech.springbootcovidapi.models.SubUser;
import com.r3tech.springbootcovidapi.repository.CoronaDetailsRepository;
import com.r3tech.springbootcovidapi.repository.CoronaRepository;
import com.r3tech.springbootcovidapi.service.CoronaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class CoronaController {

    private CoronaService service;
    public CoronaController(){}

   @Autowired
    public CoronaController(CoronaService service){
      this.service = service;
    }

    @GetMapping("/data")
    public List<CoronaDetails> getData(){
        return service.getAllData();
    }

    @PostMapping("/subscribe")
    public SubUser saveDetails(@RequestBody SubUser subUser){
            return service.saveSubuser(subUser);
    }
}

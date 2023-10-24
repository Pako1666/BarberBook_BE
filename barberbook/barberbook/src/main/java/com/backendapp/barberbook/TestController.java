package com.backendapp.barberbook;

import barberbook.mysqlconnection.mysqlconnector.configuration.DBConfiguration;
import com.backendapp.barberbook.utente.model.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("barberbook-app")
public class TestController {

    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping("/utenti")
    public ResponseEntity<List<Map<String,Object>>> getUtenti(){
        return ResponseEntity.ok().body(utenteRepository.getAllUtenti());
    }

    @GetMapping("/prova")
    public String prova(){
        return "prova";
    }
}

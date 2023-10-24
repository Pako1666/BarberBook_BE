package com.backendapp.barberbook.utente.model.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Component
public interface UtenteRepository {

    List<Map<String, Object>> getAllUtenti();

}

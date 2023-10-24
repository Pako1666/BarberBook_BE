package com.backendapp.barberbook.utente.model.repository;

import barberbook.mysqlconnection.mysqlconnector.annotations.TableName;
import barberbook.mysqlconnection.mysqlconnector.component.MySQLAccess;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Component
@TableName(name = "Utente")
public class UtenteRepositoryImpl extends MySQLAccess implements UtenteRepository {
    @Override
    public List<Map<String, Object>> getAllUtenti() {
        List<Map<String, Object>> resultHashMap = executeQuery("idFindAllUser");
        return resultHashMap;
    }
}

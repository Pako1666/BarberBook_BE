package com.backendapp.barberbook.profilo.model;

import com.backendapp.barberbook.utente.model.UtenteModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProfiloModel {

    private Integer id;

    private String username;

    private String ruolo;

    private String piva;

    private Boolean admin;

    private UtenteModel utente;
}

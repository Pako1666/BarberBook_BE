package com.backendapp.barberbook.utente.model;

import com.backendapp.barberbook.profilo.model.ProfiloModel;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class UtenteModel {

    private Integer id;

    private String nome;

    private String cognome;

    private String email;

    private String password;

    private List<ProfiloModel> profili;
}

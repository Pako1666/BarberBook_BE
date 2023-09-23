package com.app.barberbook;

import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController

public class ProvaController {


    @GetMapping("/prova")
    public ResponseEntity<String> prova(){
        return ResponseEntity.ok("Ciao mondo");
    }

    @GetMapping("/hashmap")
    public ResponseEntity<HashMap<String,Object>> getHashmap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("x",1);
        map.put("y",2);
        map.put("nome","Raffaele");
        map.put("cognome","Carrieri");
        return ResponseEntity.ok(map);
    }
}

package barberbook.app.bbbe.rest.utente;

import barberbook.app.bbbe.rest.utente.dto.UtenteResponseDto;
import barberbook.app.bbbe.rest.utente.service.UtenteService;
import barberbook.app.bbbe.rest.utente.dto.UtenteRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("utente-api/")
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @GetMapping("utente/{id}")
    ResponseEntity<UtenteResponseDto> getUtenteById(@PathVariable String id) throws Exception {
        System.out.println(id);
        return ResponseEntity.ok().body(utenteService.getUtenteById(id));
    }

    @PostMapping("insert")
    ResponseEntity<UtenteResponseDto> insertUtente(@RequestBody UtenteRequestDto req){
        return ResponseEntity.ok().body(utenteService.insertUtente(req));
    }

}

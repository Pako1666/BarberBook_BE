package barberbook.app.bbbe.rest.utente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends CrudRepository<UtenteBean,String> {

    Optional<UtenteBean> findUtenteByEmail(String email);

}

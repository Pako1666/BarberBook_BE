package barberbook.app.bbbe.rest.conto.repository;

import barberbook.app.bbbe.rest.utente.repository.UtenteBean;
import barberbook.app.bbbe.rest.utente.repository.UtenteRepository;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContoRepository extends CrudRepository<ContoBean,String> {

    @Query("select cc from ContoBean cc where cc.intestatario.id = :ints")
    ContoBean findIntestatarioConto(@Param("ints") String ints);
}

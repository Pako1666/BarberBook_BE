package barberbook.app.bbbe.rest.account.repository;

import barberbook.app.bbbe.rest.utente.repository.UtenteBean;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountBean,String> {


    Optional<AccountBean> findAccountByUsernameAndPassword(String username,String password);

    List<AccountBean> findAccountByUtente(UtenteBean utente);

    @Query("select a from AccountBean a where a.utente.id = :utente ")
    List<AccountBean> getAcoountsFromUtente(@Param("utente") String utente);


}

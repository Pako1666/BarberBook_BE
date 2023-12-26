package barberbook.app.bbbe.rest.attivita.repository;

import barberbook.app.bbbe.rest.account.repository.AccountBean;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttivitaRepository extends CrudRepository<AttivitaBean,String> {

    List<AttivitaBean> findByAccountsContains(AccountBean acc);



}

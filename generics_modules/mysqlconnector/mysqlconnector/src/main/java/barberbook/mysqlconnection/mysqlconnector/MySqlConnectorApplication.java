package barberbook.mysqlconnection.mysqlconnector;

import barberbook.mysqlconnection.mysqlconnector.configuration.QueriesValidatorConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class MySqlConnectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySqlConnectorApplication.class,args);
		// System.out.println(System.getProperty("user.dir"));
	}

}

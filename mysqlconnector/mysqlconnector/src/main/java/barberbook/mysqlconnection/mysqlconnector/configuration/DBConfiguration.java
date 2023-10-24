package barberbook.mysqlconnection.mysqlconnector.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Configuration
//@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "spring.datasource", ignoreInvalidFields = true)

public class DBConfiguration {

    @Getter
    @Value("${spring.datasource.url}")
    private String url;
    @Getter
    @Value("${spring.datasource.username}")
    private String username;

    @Getter
    @Value("${spring.datasource.password}")
    private String password;
    private QueriesValidatorConfig queriesValidatorConfig;
    private Connection mysqlConn;

    @Bean
    public Connection getConnection(){

        try {
            if(mysqlConn==null||mysqlConn.isClosed()){
                mysqlConn = DriverManager.getConnection(url,username,password);

                System.out.println("Connessione riuscita");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mysqlConn;

//        try {
//            if(url==null||password==null||username==null)
//                throw new RuntimeException("valori dell'url a null.\n Url="+url+"\nusername="+username+"" +
//                        "\npassword="+password);
//            else
//                System.out.println("Url="+url+",username="+username+",password="+password);
//            conn = DriverManager.getConnection(url,username,password);
//            System.out.println("Connessione riuscita");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


    }

    @Bean
    public QueriesValidatorConfig getQueriesValidatorConfig(){

        if(queriesValidatorConfig==null)
            queriesValidatorConfig = new QueriesValidatorConfig();

        queriesValidatorConfig.getAllQueries();
        return queriesValidatorConfig;
    }
}

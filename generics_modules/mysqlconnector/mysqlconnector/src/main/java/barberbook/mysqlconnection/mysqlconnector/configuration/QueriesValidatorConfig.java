/*
* Implementare il passaggio di valori per reference per le stringhe,
* per i metodi della validazione
*
* */


package barberbook.mysqlconnection.mysqlconnector.configuration;

import barberbook.mysqlconnection.mysqlconnector.BBMySQLConnectorException;
import barberbook.mysqlconnection.mysqlconnector.model.QueryModel;
import barberbook.mysqlconnection.mysqlconnector.model.TableModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.codec.json.Jackson2JsonDecoder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class QueriesValidatorConfig {

    public QueriesValidatorConfig(){
        List<TableModel> json = getAllQueries();
        byte []byteMsg = new byte[127];
        if(!validFile(json,null)){
            try {
                throw new BBMySQLConnectorException(
                        "Errore nel file $fileName.\n$msg"
                                .replace(
                                        "$fileName",
                                        new ClassPathResource("queries.json").getFilename()
                                )
                                .replace("$msg",new String(byteMsg,"UTF-8"))
                );
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validFile(List<TableModel> json,byte[] msg){
        boolean esito = false;
        List<QueryModel> allQueries = new ArrayList<>();
        for(TableModel tab:json){
            allQueries.addAll(tab.getQueries());
        }
        esito = checkTables(json,msg)&&checkQueries(allQueries,msg);

        return esito;
    }

    private boolean checkTables(List<TableModel>tables,byte[] msg){
        boolean esito = false;
        if(tables.size()>1) {
            for (int i = 0; i < tables.size() - 1; i++) {
                if (tables.get(i).equals(tables.get(i + 1))) {
                    esito = false;
                    try {
                        msg = "La tabella \"$tab\" è stata già dichiarata!"
                                .replace("$tab", tables.get(i + 1).getTableName())
                                .getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                esito = true;
            }
        }
        else
            esito = true;
        return esito;
    }

    private boolean checkQueries(List<QueryModel>queries,byte[] msg){
        boolean esito = false;
        if(queries.size()>1) {
            for (int i = 0; i < queries.size() - 1; i++) {
                if (queries.get(i).equals(queries.get(i + 1))) {
                    try {
                        msg = "Query con id \"${idQuery}\" è già esistente!"
                                .replace("${idQuery}", queries.get(i + 1).getIdQuery())
                                .getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                    esito = false;
                    break;
                }
                esito = true;
            }
        }
        else
            esito = true;
        return esito;
    }
    public List<TableModel> getAllQueries(){
        List<TableModel> queries = new ArrayList<>();

        ClassPathResource classPathResource = new ClassPathResource("queries.json");
        try {
            String messageError = new String();
            File jsonQueriesFile = classPathResource.getFile();
            ObjectMapper objectMapper = new ObjectMapper();
            queries = objectMapper.readValue(jsonQueriesFile, new TypeReference<List<TableModel>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return queries;
    }
}

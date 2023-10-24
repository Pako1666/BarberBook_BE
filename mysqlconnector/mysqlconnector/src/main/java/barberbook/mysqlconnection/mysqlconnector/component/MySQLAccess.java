package barberbook.mysqlconnection.mysqlconnector.component;

import barberbook.mysqlconnection.mysqlconnector.annotations.TableName;
import barberbook.mysqlconnection.mysqlconnector.configuration.DBConfiguration;
import barberbook.mysqlconnection.mysqlconnector.model.QueryModel;
import barberbook.mysqlconnection.mysqlconnector.model.TableModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;

//@Component

public abstract class MySQLAccess {

    @Autowired
    private DBConfiguration dbConfiguration;

    //query method
    public List<Map<String,Object>> executeQuery(String idQuery){
        List<Map<String,Object>> result = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        //preleviamo la query dalla lista
        List<TableModel> allQueries = dbConfiguration.getQueriesValidatorConfig().getAllQueries();

        TableModel table = allQueries.stream()
                .filter(tab -> tab.getTableName().equalsIgnoreCase(getTableName()))
                .findFirst()
                .orElseThrow(()->new NoSuchElementException("Tabella \""+getTableName()+"\" inesistente!"));

        QueryModel queryModel = table.getQueries().stream()
                .filter(q->q.getIdQuery().equals(idQuery))
                .findFirst()
                .orElseThrow(()->new NoSuchElementException("Query \""+idQuery+"\" inesistente!"));

        String querySql = fixQuery(queryModel.getSql());

        try {
            Statement statement = dbConfiguration.getConnection().createStatement();

            ResultSet res = statement.executeQuery(querySql);
            while(res.next()){
                HashMap<String,Object> resMap = new HashMap<>();
                ResultSetMetaData metadata = res.getMetaData();
                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    resMap.put(metadata.getColumnName(i), res.getObject(i));
                }

                result.add(resMap);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<HashMap<String,Object>> executeQuery(String idQuery,HashMap<String,String>params){
        List<HashMap<String,Object>> result = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        //preleviamo la query dalla lista
        List<TableModel> allQueries = dbConfiguration.getQueriesValidatorConfig().getAllQueries();

        TableModel table = allQueries.stream()
                .filter(tab -> tab.getTableName().equals(getTableName()))
                .findFirst()
                .orElseThrow(()->new NoSuchElementException("Tabella \""+getTableName()+"\" inesistente!"));

        QueryModel queryModel = table.getQueries().stream()
                .filter(q->q.getIdQuery().equals(idQuery))
                .findFirst()
                .orElseThrow(()->new NoSuchElementException("Query \""+idQuery+"\" inesistente!"));

        String sqlQuery = fixQuery(queryModel.getSql());

        List<String> paramsInQuery = Arrays.stream(sqlQuery.split(" "))
                .filter(x -> x.startsWith("$")).toList();

        for(String p : paramsInQuery){
            sqlQuery = sqlQuery.replace(p,params.get(p));
        }

        try {
            Statement statement = dbConfiguration.getConnection().createStatement();

            ResultSet res = statement.executeQuery(sqlQuery);
            while(res.next()){
                HashMap<String,Object> resMap = new HashMap<>();
                ResultSetMetaData metadata = res.getMetaData();

                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    resMap.put(metadata.getColumnName(i), res.getObject(i));
                }

                result.add(resMap);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        return result;
    }



    private String fixQuery(String q){
        q=q.replace("$table",getTableName());
        if(!q.endsWith(";"))
            q = q.concat(";");
        return q;
    }


    protected String getTableName(){
        String tab="";
        Annotation[] annotations = getClass().getAnnotations();
        Annotation tableAnn = Arrays.stream(annotations)
                .filter(a->a.toString().contains("barberbook.mysqlconnection.mysqlconnector.annotations.TableName("))
                .findFirst()
                .orElseThrow(()->new NoSuchElementException("nessuna annotation \""+TableName.class.toString()+"\"" +
                        " è stata trovata"));

        try {
            tab = (String) tableAnn.getClass().getMethod("name").invoke(tableAnn);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return tab;
    }

    protected String getDbName(){
        String urlSplit[] = this.dbConfiguration.getUrl().split("/");
        return urlSplit[urlSplit.length-1];
    }

    public void prova(){
        System.out.println(Arrays.toString(getClass().getAnnotations()));
        System.out.println(getTableName());
    }


}

package barberbook.mysqlconnection.mysqlconnector.component;

import barberbook.mysqlconnection.mysqlconnector.annotations.TableName;
import barberbook.mysqlconnection.mysqlconnector.configuration.DBConfiguration;
import barberbook.mysqlconnection.mysqlconnector.model.QueryModel;
import barberbook.mysqlconnection.mysqlconnector.model.TableModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

//@Component

public abstract class MySQLAccess {

    @Autowired
    private DBConfiguration dbConfiguration;

    //query method

    public <T> List<T> queryList(String idQuery,HashMap<String,String>params,Class cls){
        List<T> result = new ArrayList<>();

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

        String sqlQuery = queryModel.getSql();

        List<String> paramsInQuery = getQueryParams(sqlQuery);

        for(String p : paramsInQuery){
            sqlQuery = sqlQuery.replace(p,params.get(p));
        }

        sqlQuery = fixQuery(sqlQuery);

        try {
            Statement statement = dbConfiguration.getConnection().createStatement();

            ResultSet res = statement.executeQuery(sqlQuery);
            while(res.next()){
                HashMap<String,Object> resMap = new HashMap<>();
                ResultSetMetaData metadata = res.getMetaData();

                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    resMap.put(metadata.getColumnName(i), res.getObject(i));
                }

                result.add((T)objectMapper.convertValue(resMap,cls));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        return result;
    }


    public <T> List<T> queryList(String idQuery,Class cls){
        List<T> result = new ArrayList<>();

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

        try {
            Statement statement = dbConfiguration.getConnection().createStatement();

            ResultSet res = statement.executeQuery(sqlQuery);
            while(res.next()){
                HashMap<String,Object> resMap = new HashMap<>();
                ResultSetMetaData metadata = res.getMetaData();

                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    resMap.put(metadata.getColumnName(i), res.getObject(i));
                }



                result.add((T) objectMapper.convertValue(resMap,cls));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        return result;
    }

    public <T> T queryObject(String idQuery,Class cls){
        T res = null;
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



        return res;
    }

    public <T> T queryObject(String idQuery,HashMap<String,String> params,Class cls){

        T result = null;
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


        String sqlQuery = queryModel.getSql();

        List<String> paramsInQuery = getQueryParams(sqlQuery);

        for(String p : paramsInQuery){
            sqlQuery = sqlQuery.replace(p,params.get(p));
        }

        sqlQuery = fixQuery(sqlQuery);


        try {
            Statement statement = dbConfiguration.getConnection().createStatement();

            ResultSet res = statement.executeQuery(sqlQuery);

            if(res.next()){
                HashMap<String,Object> resMap = new HashMap<>();
                ResultSetMetaData metadata = res.getMetaData();

                for (int i = 1; i <= metadata.getColumnCount(); i++) {
                    resMap.put(metadata.getColumnName(i), res.getObject(i));
                }
                result = (T) objectMapper.convertValue(resMap,cls);


            }
            else
                throw new NoSuchElementException("Nessun risultato dalla query!");

        } catch (SQLException|NoSuchElementException e) {
            e.printStackTrace();
        }

        return result;
    }

    public <T> T mapObject(HashMap<String,Object>rawResult,Class cls){
        ObjectMapper objectMapper = new ObjectMapper();
        return (T) objectMapper.convertValue(rawResult,cls);
    }


    private String fixQuery(String q){
        q=q.replace("$table",getTableName());
        if(!q.endsWith(";"))
            q = q.concat(";");
        return q;
    }


    private List<String> getQueryParams(String sqlQuery){
        return Arrays.stream(sqlQuery.split(" "))
                .filter(x -> (x.startsWith("$")||x.startsWith("\"$")||x.startsWith("'$"))
                        && !x.equals("$table"))
                .map(x->x.replace("\"","")
                        .replace("\n","")
                        .replace(";","")
                        .replace("'","")
                )
                .toList();
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

//    public void prova(){
//        System.out.println(Arrays.toString(getClass().getAnnotations()));
//        System.out.println(getTableName());
//    }


}

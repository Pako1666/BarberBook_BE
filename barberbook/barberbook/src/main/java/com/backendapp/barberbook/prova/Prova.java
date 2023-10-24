package com.backendapp.barberbook.prova;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Prova {
    public static void main(String args[]) throws ClassNotFoundException{
        InnerGenericClass innerGenericClass = new InnerGenericClass();
        List<Map<String, Object>> res = innerGenericClass.executeQuery("idFindAllUser");
        System.out.println(res.toString());
    }

}

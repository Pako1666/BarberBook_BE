package app.bb.utilspackage.base64token;

import app.bb.utilspackage.jsonparse.JSONBusiness;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Base64Business {

    public static String encodeString(byte[] bytesStr){
        return Base64.encodeBase64URLSafeString(bytesStr);
    }

    public static byte[] decodeString(String base64str){
        return Base64.decodeBase64(base64str);
    }

    public static <T> String encodeObject(T obj){
        if(obj instanceof String || obj instanceof CharSequence)
            throw new IllegalArgumentException("Oggetti della classe String o simili non sono ammessi");


        String base64Str = "";

        try {
            base64Str = Base64.encodeBase64String(JSONBusiness.stringify(obj).getBytes());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return base64Str;

    }

    public static <T> T decodeObject(String base64Str,Class cls){
        T obj = null;
        if(obj instanceof String || obj instanceof CharSequence)
            throw new IllegalArgumentException("Oggetti della classe String o simili non sono ammessi");

        try {
            String json = new String(decodeString(base64Str), StandardCharsets.UTF_8);
            obj = JSONBusiness.parse(json,cls);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }






}

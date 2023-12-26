package app.bb.utilspackage;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.Cookie;

import java.util.HashMap;

//@SpringBootApplication
public class UtilsPackageApplication {

	public static void main(String[] args) {

		ObjectMapper objectMapper = new ObjectMapper();
		HashMap<String,Integer> map = new HashMap<>();
		map.put("x",1);
		map.put("y",4);
		MyClass inst = objectMapper.convertValue(map,MyClass.class);

		System.out.println(inst);
	}


}

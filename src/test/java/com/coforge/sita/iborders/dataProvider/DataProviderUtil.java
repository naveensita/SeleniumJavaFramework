package com.coforge.sita.iborders.dataProvider;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.DataProvider;

import com.coforge.sita.iborders.constants.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProviderUtil {
	
	@DataProvider(name="LoginData")
	public Object[][] getData(){
		return new Object[][] {{"naveenpathak715@gmail.com", "Sonu123@#", "ZARA COAT 3"},
		                       {"naveenpathak715@gmail.com", "Sonu123@#", "ADIDAS ORIGINAL"}};
	}
	
	@DataProvider(name="HashMapData")
    public Object[][] hashMapData() {

        HashMap<String, String> user1 = new HashMap<>();
        user1.put("email", "naveenpathak715@gmail.com");
        user1.put("password", "Sonu123@#");
        user1.put("product", "ZARA COAT 3");

        HashMap<String, String> user2 = new HashMap<>();
        user2.put("email", "naveenpathak715@gmail.com");
        user2.put("password", "Sonu123@#");
        user2.put("product", "ADIDAS ORIGINAL");

        return new Object[][] {{user1}, {user2}};
    }
	
	@DataProvider(name="jsonData")
    public Object[][] jsonLoginData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(Constants.GLOBAL_RESOURCES_FOLDER_CONFIG + "purchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}}; 
    }

    public static List<HashMap<String,String>> getJsonDataToMap(String filePath) throws IOException {
    	//Json to String
    	String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
    	//String to hashmap
    	ObjectMapper mapper = new ObjectMapper();
    	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
    	return data;
    }

}

package arjun.academy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import arjun.academy.testcomponents.BaseTest;

public class DataReader extends BaseTest{
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		// read json to String
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
	
		//read String to HashMap
		ObjectMapper mapper=new ObjectMapper();
		
		List<HashMap<String, String>> data =
			    mapper.readValue(jsonContent,
			        new com.fasterxml.jackson.core.type.TypeReference<List<HashMap<String, String>>>() {});
		
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		
		return data;
	}
	

}

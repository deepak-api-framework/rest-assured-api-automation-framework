package testdata;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import utils.JsonDataReader;

public class AddPlaceData {

	@DataProvider(name = "addPlaceData")

	public Object[][] getData() throws IOException {
		
		String path = System.getProperty("user.dir")+("/src/test/resources/testdata/testdata.json");
		List<Map<String, String>> data = JsonDataReader.getJsonData(path);

		Object[][] obj = new Object[data.size()][1];
		for(int i =0; i <data.size();i++) {
			obj[i][0] = data.get(i);
		}
		return obj;
	}
}
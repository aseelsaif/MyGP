package DeailDetail.DealDetailsFX;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class main {
	  private static final Logger LOGGER = Logger.getLogger(main.class);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		LOGGER.info("Initializing FX Deal Details application");
		Map<String,String> mapDetails=new HashMap<String,String>();
		
		new DealDetailForm();
		CSVReader reader=new CSVReader();
		mapDetails = reader.readCSV();
		for (String key : mapDetails.keySet()) {
          System.out.println(key + "=" + mapDetails.get(key));}
		
		MongoDBConnection passDetailToDB = new MongoDBConnection();
		passDetailToDB.insertToDB(mapDetails);
	
		}
}

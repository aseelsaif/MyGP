package DeailDetail.DealDetailsFX;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class CSVReader {
	  private static final Logger LOGGER = Logger.getLogger(main.class);
	public Map<String, String> readCSV() {
		String line;
		String curDirectory = System.getProperty("user.dir");
		System.out.println(curDirectory);
		Map<String,String> mapDetails=new HashMap<String,String>();
		LOGGER.info("trying to get CSV file...");
	  try (BufferedReader br = new BufferedReader(
	          new FileReader(System.getProperty("user.dir")+"\\dealDetails.csv"))) {
//		  System.out.println("\nLength : " + br.readLine().length());
		  br.readLine();//to skip the first line 
	      while ((line = br.readLine()) != null) {
	  		LOGGER.info("getting FX Deal Details application from database...");

	          // split by a comma separator
	          String[] split = line.split(",");
	          mapDetails.put("DEAL_ID",split[0]);
	          mapDetails.put("FROM_CURRENCY", split[1]);
	          mapDetails.put("TO_CURRENCY" , split[2]);
	          mapDetails.put("TIMESTAMP" , split[3]);
	          mapDetails.put("AMOUNT" , split[4]);
	      }

	  } catch (IOException e) {
		  LOGGER.error(e.getMessage());
		  assert true;
	  }
	  return mapDetails;
	  }
	}
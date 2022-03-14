
package DeailDetail.DealDetailsFX;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBConnection {
	  private static final Logger LOGGER = Logger.getLogger(MongoDBConnection.class);

	@SuppressWarnings("unchecked")
	private static DBCollection connectToDB() {
		
		MongoClient mongo;
		try {
			LOGGER.info("Trying to connect to DB: localhost, 27017 ");
			mongo = new MongoClient("localhost", 27017);
			LOGGER.info("Trying to get schema: FXdeals");
			DB db = mongo.getDB("FXdeals");
			LOGGER.info("Trying to get DB collection: dealDetails");
			DBCollection col = db.getCollection("dealDetails");
			return col;
							
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
		
	}
	
protected void insertToDB(Map<String,String> mapDetails)
{
	DBCollection col = connectToDB();
	LOGGER.info("inserting data to DB...");
	BasicDBObject bO = new BasicDBObject();
	for (String key : mapDetails.keySet()) {
//          System.out.println(key + "=" + mapDetails.get(key));
		  bO.put(key,mapDetails.get(key));
	}
	col.insert(bO);
	LOGGER.info("inserting successully ...");	
}
 @SuppressWarnings("unchecked")
protected static boolean isIDUnique(int id )
 {
	 DBCollection col = connectToDB();
		List<String> dealIDList = new ArrayList<String>();
		dealIDList = col.distinct("DEAL_ID");
		for(String v : dealIDList)
		{
			try{
				if(id == Integer.parseInt(v.trim().replaceAll("^\"|\"$", "")))

				{
					return false;
				}
					
			}
			catch (NumberFormatException error) {
				LOGGER.error(error.getMessage(), error);
				assert true;
			}
		}
		return true;
 }
}

package DeailDetail.DealDetailsFX;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.swing.text.Document;

import org.apache.log4j.Logger;
import org.bson.BasicBSONObject;
import org.bson.BsonDocument;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.util.JSON;

public class MongoDBConnection {
	  private static final Logger LOGGER = Logger.getLogger(MongoDBConnection.class);

	public static void connectToDB(Map<String,String> mapDetails) {
		
		MongoClient mongo;
		try {
			LOGGER.info("Trying to connect to DB: localhost, 27017 ");
			mongo = new MongoClient("localhost", 27017);
			LOGGER.info("Trying to get schema: FXdeals");
			DB db = mongo.getDB("FXdeals");
			LOGGER.info("Trying to get DB collection: dealDetails");
			DBCollection col = db.getCollection("dealDetails");
			BasicDBObject bO = new BasicDBObject();
			insertToDB(mapDetails,bO,col);
			
			DBCursor customDataCollection = db.getCollection("dealDetails").find();
			
//			compareQuery.put("nationality", "Filipino");
//
//			FindIterable<document> cursor = playerColl.find(compareQuery);
//			MongoCursor<document> iterator = cursor.iterator();
//			while(iterator.hasNext()) {
//			System.out.println(iterator.next());
//			}
			
//			FindIterable<Document> iterDoc = col.find().iterator();
//			MongoCursor<Document> dbc = iterDoc.iterator();

//			while(customDataCollection.hasNext()){
//			try {
//			         JsonParser jsonParser = new JsonFactory().createParser(customDataCollection.next().toJson());
//			         ObjectMapper mapper = new ObjectMapper();
//
////			         Person person = mapper.readValue(jsonParser, Person.class);
////			         String name = person.get("Name");
////			         String age = person.get("Age");
//
//			} catch (Exception e){
//			    e.printStackTrace();
//			}
			
			
			
			DBCursor iterDoc = col.find();
		      Iterator it = iterDoc.iterator();
		      while (it.hasNext()) {
		    	  
		         System.out.println("aseeeeel"+it.next());
		      }
			
			
//			 FindIterable<Document> iterDoc =
//				      col.find().projection(Projections.include("name", "age"));
//				      Iterator it = iterDoc.iterator();
//				      while (it.hasNext()) {
//				         System.out.println(it.next());
				         
				Bson projection = Projections.fields(Projections.include("DealID"));

//			FindIterable<Document> iterDoc11 =col.find().projection(Projections.include("name", "age"));
			
			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
	}
	
//	 private Document findOne(Class query, Document projection) {
//		 MongoClient mongoClient = new MongoClient("",1221);
//		  MongoDatabase database = (MongoDatabase) mongoClient.getDB("test");
//		  MongoCollection<org.bson.Document> collection = database.getCollection("testCollection");
//		 MongoClient mongo;
//				mongo = new MongoClient("localhost", 27017);
//		    MongoCollection col = ((Object) mongo).getDatabase("").getCollection("");
//		    MongoCursor<Document> it = (projection != null ? collection.find((Bson) query).projection((Bson) projection) : col.find(query)).iterator();
//		    Document retVal = it.hasNext() ? it.next() : null;
//		    it.close();
//
//		    return retVal;
//		  }
	
//	public void getJSONArrayFromDB() throws UnknownHostException {
////		List [] l=
//		MongoClient mongoClient = new MongoClient("localhost", 27017);
//		  MongoDatabase database = mongoClient.getDB("");
//		  MongoCollection<org.bson.Document> collection = database.getCollection("dealDetails");
////		MongoCollection<BsonDocument> mcol= (MongoCollection<BsonDocument>) db1.getCollection(col);
//
//		Bson filter = Filters.exists("DealID");
//		Bson projection = Projections.fields(Projections.include("DealID"), Projections.excludeId());
////		List<Document> all = collection.find(filter).projection(projection).forEach(doc -> System.out.println(doc));
//		MongoIterable<org.bson.Document> iterDoc =collection.find(filter).projection(Projections.include("DealID"));
//		FindIterable<org.bson.Document> iterDoc1 =
//				collection.find(filter).projection(Projections.include("c"));
//			      Iterator it = iterDoc.iterator();
//			      while (it.hasNext()) {
//			         System.out.println(it.next());
//			      }
//		}

//public void getdata()
//{
//	FindIterable<Document> iterDoc = collection.find();
//    Iterator it = iterDoc.iterator();
//    while (it.hasNext()) {
//       System.out.println(it.next());
//    }	
//}
private static void insertToDB(Map<String,String> mapDetails,BasicDBObject bO,DBCollection col)
{
	LOGGER.info("inserting data to DB...");

	for (String key : mapDetails.keySet()) {
//          System.out.println(key + "=" + mapDetails.get(key));
		  bO.put(key,mapDetails.get(key));
	}
	col.insert(bO);
	LOGGER.info("inserting successully ...");	
}
	
}
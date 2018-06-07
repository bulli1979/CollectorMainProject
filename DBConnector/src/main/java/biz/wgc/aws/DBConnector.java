package biz.wgc.aws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public abstract class DBConnector {
	protected static DynamoDB db;
	protected static AmazonDynamoDB client;
	private static final SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
	
	protected static void initDynamoDbClient() {
		client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();
		db = new DynamoDB(client);
	}
	
	protected static Date toDate(String source) {
		try {
			if (source != null) {
				
				return formater.parse(source);
			} else {
				return null;
			}
		} catch (ParseException e) {
			return null;
		}
	}
	
	protected static String dateToString(Date d) {
		return formater.format(d);
	}
}

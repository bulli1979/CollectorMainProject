package biz.wgc.aws;

import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec;

import biz.wgc.aws.data.CustomerItem;

public class DBDeleteService extends DBConnector{
	
	public static int deleteItem(CustomerItem item) {
		initDynamoDbClient();
		Table table = db.getTable("item");
		DeleteItemSpec deleteItemSpec = new DeleteItemSpec()
	            .withPrimaryKey(new PrimaryKey("title", item.getTitle(), "type", item.getType()));
	        try {
	            System.out.println("Attempting a conditional delete...");
	            table.deleteItem(deleteItemSpec);
	        }
	        catch (Exception e) {
	            System.err.println(e.getMessage());
	            return 0;
	        }
	        return 1;
	}
	
	
}

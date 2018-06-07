package biz.wgc.aws;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

public class DBSaveService extends DBConnector {

	public static int save(biz.wgc.aws.data.CustomerItem item) {
		initDynamoDbClient();
		Table table = db.getTable("item");
		table.putItem(new Item().withPrimaryKey("title", item.getTitle(), "type", item.getType()));
		return 1;
	}

	public static int lendItem(biz.wgc.aws.data.CustomerItem item) {
		initDynamoDbClient();
		Table table = db.getTable("item");

		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey("title", item.getTitle(), "type", item.getType())
				.withUpdateExpression("set lendOutTo = :l, lendOutOn=:lo").withValueMap(new ValueMap()
				.withString(":l", item.getLendOutTo()).withString(":lo", dateToString(item.getLendOutOn())))
				.withReturnValues(ReturnValue.UPDATED_NEW);

		try {
			UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return 0;
		}
		return 1;
	}
	public static int backItem(biz.wgc.aws.data.CustomerItem item) {
		initDynamoDbClient();
		Table table = db.getTable("item");
		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey("title", item.getTitle(), "type", item.getType())
				.withUpdateExpression("set lendOutTo = :l, lendOutOn=:lo").withValueMap(new ValueMap()
				.withString(":l", null).withString(":lo", null))
				.withReturnValues(ReturnValue.UPDATED_NEW);

		try {
			UpdateItemOutcome outcome = table.updateItem(updateItemSpec);
			System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return 0;
		}
		return 1;
	}
}

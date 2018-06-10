package biz.wgc.aws;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;

import biz.wgc.aws.data.CustomerItem;

public class DBSaveService extends DBConnector {

	public static int save(CustomerItem item) {
		initDynamoDbClient();
		Table table = db.getTable("item");
		table.putItem(new Item().withPrimaryKey("title", item.getTitle(), "type", item.getType()));
		return 1;
	}

	public static int lendItem(CustomerItem item) throws Exception {
		initDynamoDbClient();
		Table table = db.getTable("item");

		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey("title", item.getTitle(), "type", item.getType())
				.withUpdateExpression("set lendOutTo = :l, lendOutOn=:lo").withValueMap(new ValueMap()
						.withString(":l", item.getLendOutTo()).withString(":lo", dateToString(item.getLendOutOn())))
				.withReturnValues(ReturnValue.UPDATED_NEW);

		table.updateItem(updateItemSpec);
		return 1;
	}

	public static int backItem(CustomerItem item) {
		initDynamoDbClient();
		Table table = db.getTable("item");
		UpdateItemSpec updateItemSpec = new UpdateItemSpec()
				.withPrimaryKey("title", item.getTitle(), "type", item.getType())
				.withUpdateExpression("set lendOutTo = :l, lendOutOn=:lo")
				.withValueMap(new ValueMap().withString(":l", null).withString(":lo", null))
				.withReturnValues(ReturnValue.UPDATED_NEW);

		table.updateItem(updateItemSpec);

		return 1;
	}
}

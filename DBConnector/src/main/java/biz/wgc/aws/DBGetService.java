package biz.wgc.aws;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import biz.wgc.aws.data.CustomerItem;

public class DBGetService extends DBConnector {

	public static List<CustomerItem> getAllItems(String type) {
		List<CustomerItem> itemList = new ArrayList<CustomerItem>();
		initDynamoDbClient();
		DynamoDBMapper mapper = new DynamoDBMapper(client);
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
				.withConsistentRead(false);
		List<ItemMap> replies = mapper.scan(ItemMap.class, scanExpression);
		if(replies != null) {
			replies.forEach(reply -> {
				itemList.add(createItem(reply));
			});
		}
		return itemList;
	}

	private static CustomerItem createItem(ItemMap reply) {
		CustomerItem item = new CustomerItem();
		item.setType(reply.getType());
		item.setTitle(reply.getTitle());
		item.setLendOutOn(toDate(reply.getLendOutOn()));
		item.setLendOutTo(reply.getLendOutTo());
		return item;
	}
}

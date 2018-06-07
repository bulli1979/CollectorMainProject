package biz.wgc.aws;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
@DynamoDBTable(tableName = "item")
public class ItemMap {

	@DynamoDBHashKey
	private String title;
	@DynamoDBAttribute
	private String type;
	@DynamoDBAttribute
	private String lendOutTo;
	@DynamoDBAttribute
	private String lendOutOn;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLendOutTo() {
		return lendOutTo;
	}
	public void setLendOutTo(String lendOutTo) {
		this.lendOutTo = lendOutTo;
	}
	public String getLendOutOn() {
		return lendOutOn;
	}
	public void setLendOutOn(String lendOutOn) {
		this.lendOutOn = lendOutOn;
	}	
	
}

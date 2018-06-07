package biz.wgc.aws.data;

import java.util.Date;

public class CustomerItem {
	private String title;
	private String type;
	private String lendOutTo;
	private Date lendOutOn;
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
	public Date getLendOutOn() {
		return lendOutOn;
	}
	public void setLendOutOn(Date lendOutOn) {
		this.lendOutOn = lendOutOn;
	}
}

package biz.wgc.aws;

public enum IntentType {
	ADDITEM,DELETEITEM,UPDATEITEM,LISTITEMS;
	
	public IntentType findIntent(String intent) {
		for(IntentType t : IntentType.values()) {
			if(t.toString().equals(intent)) {
				return this;
			}
		}
		return null;
	}
	
}

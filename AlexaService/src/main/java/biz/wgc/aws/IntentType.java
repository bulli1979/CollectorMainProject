package biz.wgc.aws;

public enum IntentType {
	ADDITEM,DELETEITEM,LENDOUT,LISTITEMS,BACKITEM,WHATISLEND;
	
	public IntentType findIntent(String intent) {
		for(IntentType t : IntentType.values()) {
			if(t.toString().equals(intent)) {
				return this;
			}
		}
		return null;
	}
	
}

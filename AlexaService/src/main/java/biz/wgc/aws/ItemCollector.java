package biz.wgc.aws;

import java.util.ArrayList;
import java.util.List;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.Directive;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.dialog.directives.DelegateDirective;
import com.amazon.speech.speechlet.dialog.directives.DialogIntent;
import com.amazon.speech.ui.PlainTextOutputSpeech;

import biz.wgc.aws.data.CustomerItem;

public class ItemCollector implements SpeechletV2 {

	@Override
	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {

	}

	@Override
	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText("Willkommen in deiner Sammlung");
		return SpeechletResponse.newTellResponse(speech);
	}

	@Override
	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		Intent intent = requestEnvelope.getRequest().getIntent();
		IntentType intentType = IntentType.valueOf(intent.getName());
		Slot type = intent.getSlot("Type");
		Slot title = intent.getSlot("Title");
    	PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
    	
	    switch(intentType) {
		    case ADDITEM :
		    	if (type == null || title == null || type.getValue() == null || title.getValue() == null) {
			        return generateDialogResponse(requestEnvelope);
			    }
		    	DBSaveService.save(createItemFromRequest(title.getValue(),type.getValue()));
		    	speech.setText("Dein " + type.getValue() + " " + title.getValue() + " wurde hinzugefügt");
		    	break;
		    case DELETEITEM:
		    	DBDeleteService.deleteItem(null);
		    	break;
		    case UPDATEITEM:
		    	DBSaveService.backItem(null);
		    	break;
		    case LISTITEMS:
		    	List<CustomerItem> itemList = DBGetService.getItemsForType(type.getValue());
		    	StringBuilder b = new StringBuilder();
		    	itemList.forEach(item ->{b.append(item.getTitle()+", ");});
			    speech.setText("Deine Filme " + b.toString());
		    default:
	    }
	    return SpeechletResponse.newTellResponse(speech);
	}

	private SpeechletResponse generateDialogResponse(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		DialogIntent dialogIntent = new DialogIntent(requestEnvelope.getRequest().getIntent());
		DelegateDirective dd = new DelegateDirective();
		dd.setUpdatedIntent(dialogIntent);
		
		List<Directive> directiveList = new ArrayList<>();
		directiveList.add(dd);

		SpeechletResponse speechletResp = new SpeechletResponse();
		speechletResp.setDirectives(directiveList);
		// 3. return the response.
		speechletResp.setNullableShouldEndSession(false);
		return speechletResp;
	}

	private CustomerItem createItemFromRequest(String title, String type) {
		CustomerItem item = new CustomerItem();
		item.setTitle(title);
		item.setType(type);
		return item;
	}

	@Override
	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
		// TODO Auto-generated method stub

	}

}

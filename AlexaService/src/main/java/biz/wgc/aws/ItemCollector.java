package biz.wgc.aws;

import java.util.ArrayList;
import java.util.Date;
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
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		System.out.println("starte Intent Aufruf");
		try {
		Intent intent = requestEnvelope.getRequest().getIntent();
		IntentType intentType = IntentType.valueOf(intent.getName());
		Slot type = intent.getSlot("Type");
		Slot title = intent.getSlot("Title");
		Slot name = intent.getSlot("Name");
    	
	    switch(intentType) {
		    case ADDITEM :
		    	System.out.println("Füge ein Item hinzu");
		    	if (type == null || title == null || type.getValue() == null || title.getValue() == null) {
			        return generateDialogResponse(requestEnvelope);
			    }
		    	System.out.println("Typ: " + type.getValue());
		    	System.out.println("Title: " + title.getValue());
		    	DBSaveService.save(createItemFromRequest(title.getValue(),type.getValue()));
		    	speech.setText("Dein " + type.getValue() + " " + title.getValue() + " wurde hinzugefügt");
		    	break;
		    case DELETEITEM:
		    	DBDeleteService.deleteItem(null);
		    	break;
		    case LENDOUT:
		    	System.out.println("verleihe ein Item");
		    	if (type == null || title == null || name == null || type.getValue() == null || title.getValue() == null || name.getValue() == null) {
			        return generateDialogResponse(requestEnvelope);
			    }		    	
		    	System.out.println("Typ: " + type.getValue());
		    	System.out.println("Title: " + title.getValue());
		    	System.out.println("Name: " + name.getValue());
		    	DBSaveService.lendItem(generateItem(type.getValue(), title.getValue(), name.getValue()));
		    	speech.setText("Dein " + type.getValue() + " " + title.getValue() + " wurde an " + name.getValue() + " verliehen");
		    	break;
		    case LISTITEMS:
		    	List<CustomerItem> itemList = DBGetService.getItemsForType(type.getValue());
		    	StringBuilder b = new StringBuilder();
		    	itemList.forEach(item ->{b.append(item.getTitle()+", ");});
			    speech.setText("Deine Filme " + b.toString());
		    default:
	    }
		}catch(Exception e) {
			System.out.println("Es ist ein Fehler aufgetreten");
			System.out.println(e.getMessage());
			speech.setText("Entschuldigung es ist ein Fehler aufgetreten");
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
	
	
	private CustomerItem generateItem(String type, String title, String name) {
		CustomerItem item = new CustomerItem();
		item.setTitle(title);
		item.setType(type);
		item.setLendOutOn(new Date());
		item.setLendOutTo(name);
		
		return item;
	}

}

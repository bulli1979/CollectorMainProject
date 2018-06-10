package biz.wgc.aws;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.PlainTextOutputSpeech;

public class WhatWeAreDoing implements SpeechletV2{
	private static final String GLOBAL_ANSWER = "Hallo, bei der Session Hochdeutsch für Fortgeschrittene, oh ich meine Alexa Skill Entwicklung mit Richard Schmidt und Mirko Eberlein von Webgate";
	private static final String WHATWEDO = "Wir werden in die Skillerstellung eintauchen";
	public void onSessionEnded(SessionEndedRequest arg0, Session arg1) throws SpeechletException {
		
	}

	public void onSessionStarted(SessionStartedRequest arg0, Session arg1) throws SpeechletException {
		
	}

	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
		
	}

	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
	    speech.setText(GLOBAL_ANSWER);
	    return SpeechletResponse.newTellResponse(speech);
	}

	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		//We have only one intend so we need not ask for the intend name
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
	    speech.setText(WHATWEDO);
	    return SpeechletResponse.newTellResponse(speech);
	}

	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
		
	}
}

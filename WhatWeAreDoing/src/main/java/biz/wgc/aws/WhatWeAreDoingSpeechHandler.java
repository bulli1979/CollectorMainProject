package biz.wgc.aws;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class WhatWeAreDoingSpeechHandler extends SpeechletRequestStreamHandler{
	private static final Set<String> supportedApplicationIds;
	static {
		supportedApplicationIds = new HashSet<String>();
		supportedApplicationIds.add("amzn1.ask.skill.40895c18-8fc2-4352-af19-531fd5c598dc");
	}

	public WhatWeAreDoingSpeechHandler() {
		super(new WhatWeAreDoing(), supportedApplicationIds);
	}
}

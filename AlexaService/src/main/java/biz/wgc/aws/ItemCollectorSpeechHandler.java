package biz.wgc.aws;
import java.util.HashSet;
import java.util.Set;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
public class ItemCollectorSpeechHandler extends SpeechletRequestStreamHandler{
	private static final Set<String> supportedApplicationIds;
	static {
		supportedApplicationIds = new HashSet<String>();
		supportedApplicationIds.add("amzn1.ask.skill.e0f37d3e-4c5d-4f3a-9f35-a478dcad8cb1");
	}
	
	public ItemCollectorSpeechHandler() {
		super(new ItemCollector(), supportedApplicationIds);
	}
}

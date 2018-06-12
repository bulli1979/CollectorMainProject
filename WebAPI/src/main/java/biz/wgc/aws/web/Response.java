package biz.wgc.aws.web;

import java.util.Map;

public class Response {
	int statusCode;
	Map<String, String> headers;
	String body;
	boolean isBase64Encoded;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
		this.headers.put("Content-Type", "application/json");
		this.headers.put("Access-Control-Allow-Origin", "*");
		this.headers.put("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		this.headers.put("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Origin, Authorization, Accept, Client-Security-Token, Accept-Encoding");
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isBase64Encoded() {
		return isBase64Encoded;
	}

	public void setBase64Encoded(boolean isBase64Encoded) {
		this.isBase64Encoded = isBase64Encoded;
	}

}

package biz.wgc.aws.web;

import java.util.HashMap;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import biz.wgc.aws.DBGetService;

public class WebAPI {
	
	public Response getItems(Context context){
		System.out.println("weStart");
		Response resp = new Response();
		resp.setBase64Encoded(false);
		String json = new Gson().toJson(DBGetService.getAllItems(null) );
		resp.setBody(json);
		resp.setHeaders(new HashMap<String,String>());
		resp.setStatusCode(200);
		return resp;
	}
	
}

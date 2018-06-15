package com.qin.crxl.comic.tool;

import javax.ws.rs.core.MediaType;

import com.qin.crxl.comic.system.SystemConfig;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SendCode {

	public static boolean sendCode(String phone,String template) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api",SystemConfig.SendCodeAppkey));
        WebResource webResource = client.resource(SystemConfig.SendCodeHttpUrl);
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("mobile", phone);
        formData.add("message", template);
        ClientResponse response =  webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).
        post(ClientResponse.class, formData);
        String textEntity = response.getEntity(String.class);
        System.out.println(textEntity);
        int status = response.getStatus();
        if(status==200){
        	return true;
        }else{
        	return false;
        }
	}
	
	public static void main(String[] args) {
		sendCode("17302417769",SystemConfig.SendCodeMessage("0241"));
	}
	 private static String testStatus(){
	        Client client = Client.create();
	        client.addFilter(new HTTPBasicAuthFilter(
	            "api","key-1bcb1ba84d702602e8c187a3cad10d14"));
	        WebResource webResource = client.resource( "http://sms-api.luosimao.com/v1/status.json" );
	        MultivaluedMapImpl formData = new MultivaluedMapImpl();
	        ClientResponse response =  webResource.get( ClientResponse.class );
	        String textEntity = response.getEntity(String.class);
	        int status = response.getStatus();
	        //System.out.print(status);
	        //System.out.print(textEntity);
	        return textEntity;
	    }
}

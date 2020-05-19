package mx.com.sps.bitacora;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseException;
import org.elasticsearch.client.RestClient;

import mx.com.sps.bitacora.configuration.ELKClientConfiguration;

public class CargaInicial extends ELKClientConfiguration {	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CargaInicial cargaInicial= new CargaInicial();
		cargaInicial.cargaInicial();
	}
	
	
	
    private void cargaInicial(){    	
    
    	StringBuffer sb = new StringBuffer("");	
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		RestClient restClient = getConnection();   
	    Request request = new Request("POST", "/bitacora/_doc");
        try {    
    	for (int i = 1; i <= 33; i++) {
    		sb = new StringBuffer("");
    		sb.append("{")
    		.append("\"usuario\":\"").append("ana")	
    		.append("\",\"idRegla\":\"regla ").append(i)		
    		.append("\",\"actividad\":\"Actividad carga inicial ").append(i)
    		.append("\",\"fechaIns\":\"").append(format.format(new Date()))
    		.append("\"}");
    		
    		request = new Request("POST", "/bitacora/_doc");
    		request.setEntity(new NStringEntity(
    				sb.toString(),
        	        ContentType.APPLICATION_JSON));
    		        restClient.performRequest(request);  
    	  }  
    	
    	for (int i = 34; i <= 66; i++) {
    		sb = new StringBuffer("");
    		sb.append("{")
    		.append("\"usuario\":\"").append("gaby")	
    		.append("\",\"idRegla\":\"regla ").append(i)		
    		.append("\",\"actividad\":\"Actividad carga inicial ").append(i)
    		.append("\",\"fechaIns\":\"").append(format.format(new Date()))
    		.append("\"}");
    		
    		request = new Request("POST", "/bitacora/_doc");
    		request.setEntity(new NStringEntity(
    				sb.toString(),
        	        ContentType.APPLICATION_JSON));
    		        restClient.performRequest(request);  
    	  }
    	
    	for (int i = 67; i <= 100; i++) {
    		sb = new StringBuffer("");
    		sb.append("{")
    		.append("\"usuario\":\"").append("juan")	
    		.append("\",\"idRegla\":\"regla ").append(i)		
    		.append("\",\"actividad\":\"Actividad carga inicial ").append(i)
    		.append("\",\"fechaIns\":\"").append(format.format(new Date()))
    		.append("\"}");
    		
    		request = new Request("POST", "/bitacora/_doc");
    		request.setEntity(new NStringEntity(
    				sb.toString(),
        	        ContentType.APPLICATION_JSON));
    		        restClient.performRequest(request);  
    	  }
        } catch (ResponseException e) {        	
          	 e.printStackTrace();        	 
   	    }catch (IOException e) {
              e.printStackTrace();         
        }catch (Exception e) {
            e.printStackTrace();         
       }finally{            
            closeConnection(restClient);
       }
		 
    }

}

package mx.com.sps.bitacora.restservice;

import mx.com.sps.bitacora.configuration.ELKClientConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseException;
import org.elasticsearch.client.RestClient;
import java.io.IOException;
import javax.validation.Valid;
import org.elasticsearch.client.Request;

@RestController
public class BitacoraService extends ELKClientConfiguration {	

 private final String genericMessageError="{'error': {'root_cause': '','type': 'exception','reason': 'failed into BitacoraService'},'status': 400}";	
	@GetMapping(path = "/bitacora",produces = "application/json")
public String getAllBitacora() {
		String strResponse="";
        RestClient restClient = getConnection();
        Request request = new Request("GET", "/bitacora/_search?q=*");   
        Response response=null;
        try {
            response = restClient.performRequest(request);
            strResponse =EntityUtils.toString(response.getEntity());           
        } catch (ResponseException e) {        	
       	 e.printStackTrace();  
       	 strResponse= "{"+e.getMessage().substring(e.getMessage().indexOf("{"),e.getMessage().length()); 
       	 return strResponse;
	  }catch (IOException e) {
           e.printStackTrace();
       return    genericMessageError;
     }catch (Exception e) {
         e.printStackTrace();
       return    genericMessageError;  
    }finally{            
         closeConnection(restClient);
     }
		return strResponse;
	}
	
	@GetMapping(path = "/bitacora/{param}",produces = "application/json")
	public String getBitacoraByParams(@PathVariable(value = "param") String param){
		String strResponse="";
        RestClient restClient = getConnection();     
        Request request = new Request("GET", "/bitacora/_search?q="+param);
        try {
            Response response = restClient.performRequest(request);  
            strResponse =EntityUtils.toString(response.getEntity());         
        } catch (ResponseException e) {        	
          	 e.printStackTrace();  
          	 strResponse= "{"+e.getMessage().substring(e.getMessage().indexOf("{"),e.getMessage().length()); 
          	 return strResponse;
   	  }catch (IOException e) {
              e.printStackTrace();
          return    genericMessageError;
        }catch (Exception e) {
            e.printStackTrace();
          return    genericMessageError;  
       }finally{            
            closeConnection(restClient);
        }
		
        return strResponse;		 
	} 
	
	@PostMapping(path = "/bitacora", consumes = "application/json", produces = "application/json")
	public String addRegisterBitacora(@RequestBody String requestBitacora) {
		String strResponse="";
		RestClient restClient = getConnection();   
        Request request = new Request("POST", "/bitacora/_doc");        
        try {        	
        	request.setEntity(new NStringEntity(
        			requestBitacora,
        	        ContentType.APPLICATION_JSON));
            Response response = restClient.performRequest(request);         
            strResponse =EntityUtils.toString(response.getEntity());          
        } catch (ResponseException e) {        	
          	 e.printStackTrace();  
          	 strResponse= "{"+e.getMessage().substring(e.getMessage().indexOf("{"),e.getMessage().length()); 
          	 return strResponse;
   	  }catch (IOException e) {
              e.printStackTrace();
          return    genericMessageError;
        }catch (Exception e) {
            e.printStackTrace();
          return    genericMessageError;  
       }finally{            
            closeConnection(restClient);
        }
		 return strResponse;
	}
	
	
	@PutMapping(path ="/bitacora/{id}", consumes = "application/json", produces = "application/json")
	  public String updateRegisterBitacora(@PathVariable(value = "id") String id,@Valid @RequestBody String requestBitacora) {
		String strResponse="";
		RestClient restClient = getConnection();
        Request request = new Request("PUT", "/bitacora/_doc/"+id);        
        try {
        	request.setEntity(new NStringEntity(
        			requestBitacora,
        	        ContentType.APPLICATION_JSON));
            Response response = restClient.performRequest(request);         
            strResponse =EntityUtils.toString(response.getEntity());          
            } catch (ResponseException e) {        	
              	 e.printStackTrace();  
              	 strResponse= "{"+e.getMessage().substring(e.getMessage().indexOf("{"),e.getMessage().length()); 
              	 return strResponse;
       	  }catch (IOException e) {
                  e.printStackTrace();
              return    genericMessageError;
            }catch (Exception e) {
                e.printStackTrace();
              return    genericMessageError;  
           }finally{            
                closeConnection(restClient);
            }
        return strResponse;
	  }	 
	
	@DeleteMapping(path ="/bitacora/{id}",produces = "application/json")
	public String deleteRegisterBitacora(@PathVariable(value = "id") String id){
		String strResponse="";
		RestClient restClient = getConnection();
        Request request = new Request("DELETE", "/bitacora/_doc/"+id);        
        try {
            Response response = restClient.performRequest(request);         
            strResponse =EntityUtils.toString(response.getEntity());          
            } catch (ResponseException e) {        	
              	 e.printStackTrace();  
              	 strResponse= "{"+e.getMessage().substring(e.getMessage().indexOf("{"),e.getMessage().length()); 
              	 return strResponse;
       	  }catch (IOException e) {
                  e.printStackTrace();
              return    genericMessageError;
            }catch (Exception e) {
                e.printStackTrace();
              return    genericMessageError;  
           }finally{            
                closeConnection(restClient);
            }
        return strResponse;
	}

}

package mx.com.sps.bitacora.configuration;

import java.io.IOException;

import org.apache.http.HttpHost;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.client.BasicCredentialsProvider;

import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.impl.client.HttpClients;

import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

public class ELKClientConfiguration {
    public ELKClientConfiguration() {
        super();
    }
    

      public RestClient  getConnection(){
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "tPgOFgDf6qmtdRQWbsejHDi5"));
        RestClient restClient = RestClient.builder(new HttpHost("bcb4211f6f8d482d96e38f8846d0954b.us-east-1.aws.found.io", 9243,"https"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                }).setRequestConfigCallback(
                	    new RestClientBuilder.RequestConfigCallback() {							
							@Override
							public Builder customizeRequestConfig(
					                RequestConfig.Builder requestConfigBuilder) {
					            return requestConfigBuilder.setSocketTimeout(10000); // para cerrar el socket por timeout
							}
						}).build();        
        return restClient;
      }
  
 
    public void  closeConnection(RestClient restClient){
        try {
            restClient.close();
        } catch (IOException e) {
            e.printStackTrace();   
        }
    } 

}

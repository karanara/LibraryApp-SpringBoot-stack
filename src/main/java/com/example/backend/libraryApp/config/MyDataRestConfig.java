package com.example.backend.libraryApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.example.backend.libraryApp.entity.Book;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

	private String allowedOrigins = "http://localhost:3000";
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,CorsRegistry cors) {
		HttpMethod[] theUnsupportedActions = {
				HttpMethod.POST,
				HttpMethod.PATCH,
				HttpMethod.DELETE,
				HttpMethod.PUT
		};
		config.exposeIdsFor(Book.class);
		disableHttpMethods(Book.class,config,theUnsupportedActions);
        cors.addMapping(config.getBasePath() + "/**")
        .allowedOrigins(allowedOrigins);

	}
	private void disableHttpMethods(Class class1, RepositoryRestConfiguration config,
			HttpMethod[] theUnsupportedActions) {
		// TODO Auto-generated method stub
		config.getExposureConfiguration()
        .forDomainType(class1)
        .withItemExposure((metdata, httpMethods) ->
                httpMethods.disable(theUnsupportedActions))
        .withCollectionExposure((metdata, httpMethods) ->
                httpMethods.disable(theUnsupportedActions));
		    
		
	}
}

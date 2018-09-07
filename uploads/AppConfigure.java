package io.examples.blockchain.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//mport com.hazelcast.core.PostProcessingMapStore;

@Configuration
public class AppConfigure {

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		//Context c = new Context()
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory(){ 
			
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
				//context.addConstraaint(securityConstraint);
			}
		};
		
		tomcat.addAdditionalTomcatConnectors(initHttpConnector());
		return tomcat;
		
	}
	
	private Connector initHttpConnector() {
		
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setRedirectPort(8443);//normaal8443
		connector.setSecure(false);
		return connector;
		
	}
}

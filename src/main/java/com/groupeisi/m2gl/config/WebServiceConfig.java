package com.groupeisi.m2gl.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    // URI de base pour les services SOAP
    public static final String WS_URI = "/ws";

    // 1️⃣ Bean pour dispatcher les requêtes SOAP
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, WS_URI + "/*");
    }

    // 2️⃣ Bean pour charger le fichier XSD
    @Bean(name = "schema")
    public XsdSchema banqueSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema.xsd"));
    }

    // 3️⃣ Bean pour exposer le WSDL basé sur le XSD
    @Bean(name = "banque")
    public DefaultWsdl11Definition banqueWsdl(@Qualifier("schema") XsdSchema banqueSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("BanquePort");
        wsdl11Definition.setLocationUri(WS_URI);
        wsdl11Definition.setTargetNamespace("http://www.esmt.com/banque");
        wsdl11Definition.setSchema(banqueSchema);
        return wsdl11Definition;
    }
}

package com.tarni.dropwizard.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.codahale.metrics.health.HealthCheckRegistry;
import com.tarni.dropwizard.example.HelloWorldApplication;
import com.tarni.dropwizard.example.HelloWorldConfiguration;
import com.tarni.dropwizard.example.HelloWorldResource;

import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Environment;

public class IntegrationTest {

	private final Environment env = Mockito.mock(Environment.class);
	private final JerseyEnvironment jenv = Mockito.mock(JerseyEnvironment.class);
	private final HealthCheckRegistry hc = Mockito.mock(HealthCheckRegistry.class);
	private final HelloWorldApplication app = new HelloWorldApplication();
	private final HelloWorldConfiguration config = new HelloWorldConfiguration();
	
	@Before
	public void setup(){
		config.setDefaultName("Tarni Sharma");
		Mockito.when(env.jersey()).thenReturn(jenv);
		Mockito.when(env.healthChecks()).thenReturn(hc);
	}
	
	@Test
	public void test(){
		app.run(config, env);
		Mockito.verify(jenv).register(Mockito.any(HelloWorldResource.class));
	}
}

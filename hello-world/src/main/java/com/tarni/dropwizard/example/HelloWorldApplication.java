package com.tarni.dropwizard.example;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
	
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    	bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), 
                		new EnvironmentVariableSubstitutor()));
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
    	final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(), configuration.getDefaultName());
    	environment.jersey().register(resource);
    	
    	final HelloWorldHealthCheck healthCheck = new HelloWorldHealthCheck(configuration.getTemplate());
    	environment.healthChecks().register("hello-world",healthCheck);
    }

}
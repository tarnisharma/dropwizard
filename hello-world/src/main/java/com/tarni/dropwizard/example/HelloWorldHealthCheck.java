package com.tarni.dropwizard.example;

import com.codahale.metrics.health.HealthCheck;

public class HelloWorldHealthCheck extends HealthCheck {

	private String template;
	
    public HelloWorldHealthCheck(String template) {
		this.template = template;
	}

	@Override
	protected Result check() throws Exception {
		final String test =  String.format(template, "HEALTH");
		if(!test.contains("HEALTH")){
			return Result.unhealthy("All men must die");
		}
		return Result.healthy();
	}
	
}

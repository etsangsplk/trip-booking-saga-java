package io.flowing.trip;


import org.camunda.bpm.BpmPlatform;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.camunda.consulting.util.LicenseHelper;
import com.camunda.consulting.util.UserGenerator;

@SpringBootApplication
@EnableAutoConfiguration
@EnableProcessApplication
@ComponentScan
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);

    // do default setup of platform (everything is only applied if not yet there)
    ProcessEngine engine = BpmPlatform.getDefaultProcessEngine();
    
    // start a Saga right away
    engine.getRuntimeService().startProcessInstanceByKey(
        "trip", 
        Variables.putValue("someVariableToPass", "someValue"));
    
    // and add default users to Camunda to be ready-to-go
    UserGenerator.createDefaultUsers(engine);
    LicenseHelper.setLicense(engine);    
  }

}

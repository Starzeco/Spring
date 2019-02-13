package guru.springframeworok.controllers;

import guru.springframeworok.services.GreetingService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class SetterInjectedController {


    @Qualifier("setterGreetingService")
    @Autowired          // To mówi springowi, że ma injectować zależność
    private GreetingService greetingService;

    public String sayHello(){

        return greetingService.sayGreeting();
    }

    public void setGreetingService(GreetingService greetingService){

        this.greetingService=greetingService;
    }
}

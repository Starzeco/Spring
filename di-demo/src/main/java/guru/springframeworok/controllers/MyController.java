package guru.springframeworok.controllers;

import guru.springframeworok.services.GreetingService;
import org.springframework.stereotype.Controller;

@Controller
public class MyController {

    private GreetingService greetingService;



    public MyController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public String hello(){
        System.out.println("Hello !!!!!!!!<3");
        return greetingService.sayGreeting();
    }
}

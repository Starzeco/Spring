package guru.springframeworok.controllers;

import guru.springframeworok.services.GreetingService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class ConstructorInjectedController {
            // Tu nie trzeba autowired
    private GreetingService greetingService;

    // Teoretycznie tu mogłoby być autwired ale to autowired właśnie wywołuje construktor (tak jakby), więc bez sensu
    public ConstructorInjectedController(@Qualifier("constructorGreetingService") GreetingService greetingService){
        this.greetingService=greetingService;
    }

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}

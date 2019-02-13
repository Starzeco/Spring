package guru.springframeworok.controllers;

import guru.springframeworok.services.GreetingService;
import guru.springframeworok.services.GreetingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class PropertyInjectedController {


    @Autowired              // To mówi springowi, że ma injectować zależność
    public GreetingServiceImpl greetingService;             // Tu można nie używać Kwalifikatora tylko zmienić INstancje Klasy na IMPL wtedy będzie wiedziało, że ma korzystać z tamtej klasy

    public String sayHello(){
        return greetingService.sayGreeting();
    }
}

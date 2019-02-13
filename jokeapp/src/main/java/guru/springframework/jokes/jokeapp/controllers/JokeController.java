package guru.springframework.jokes.jokeapp.controllers;

import guru.springframework.jokes.jokeapp.services.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JokeController {
    private JokeService jokeService;

    @Autowired // Nie potrzebne
    public JokeController(JokeService jokeService){
        this.jokeService=jokeService;
    }


    @RequestMapping({"/", ""})   // To jest do wyszukiwania w przeglądarce
    public String showJoke(Model model){
       model.addAttribute("joke", jokeService.getJoke());   // Ten "joke" odnosi się do th:text w clasie html'u

       return "chucknorris";  //To odwołuje się do klasy w html'u ( Musi mieć tę samą nazwę)
    }


}

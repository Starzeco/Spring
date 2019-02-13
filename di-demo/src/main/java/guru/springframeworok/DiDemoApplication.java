package guru.springframeworok;

import guru.springframeworok.controllers.ConstructorInjectedController;
import guru.springframeworok.controllers.MyController;
import guru.springframeworok.controllers.PropertyInjectedController;
import guru.springframeworok.controllers.SetterInjectedController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiDemoApplication {

    public static void main(String[] args) {

        ApplicationContext ctx= SpringApplication.run(DiDemoApplication.class, args);


        MyController controller=(MyController) ctx.getBean("myController");

       // controller.hello();


        System.out.println(controller.hello());
        System.out.println(ctx.getBean(PropertyInjectedController.class).sayHello());
        System.out.println(ctx.getBean(SetterInjectedController.class).sayHello());
        System.out.println(ctx.getBean(ConstructorInjectedController.class).sayHello());
    }
}

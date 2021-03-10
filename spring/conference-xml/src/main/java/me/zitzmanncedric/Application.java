package me.zitzmanncedric;

import me.zitzmanncedric.service.SpeakerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        /*
          Before: SpeakerService service = new SpeakerServiceImpl();
          Now: Using configured bean in AppConfig
         */ 
        SpeakerService service = applicationContext.getBean("speakerService", SpeakerService.class);
        System.out.println(service);

        SpeakerService service2 = applicationContext.getBean("speakerService", SpeakerService.class);
        System.out.println(service2);

        System.out.println(service.findAll().get(0).getFirstName());
    }

}

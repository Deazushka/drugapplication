package by.training.drugspayapplication;

import by.training.drugspayapplication.config.db.DBConfig;
import by.training.drugspayapplication.model.menu.ConsoleUi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


@SpringBootApplication
@Component
public class DrugsToSellApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(DrugsToSellApplication.class, args);
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DBConfig.class);


    new ConsoleUi().titleRun();
    ctx.registerShutdownHook();


  }
}

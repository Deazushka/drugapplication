package by.training.drugspayapplication.model.menu;


import by.training.drugspayapplication.config.db.DBConfig;
import by.training.drugspayapplication.repository.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import java.util.Scanner;



@Component("ConsoleUi")
public class ConsoleUi {
  private AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DBConfig.class);

  private StateRepository stateRepository = ctx.getBean(StateRepository.class);
  private ProductRepository productRepository = ctx.getBean(ProductRepository.class);
  private PatientRepository patientRepository = ctx.getBean(PatientRepository.class);
  private TransactionRepository transactionRepository = ctx.getBean(TransactionRepository.class);
  private AuditOperationRepository auditOperationRepository = ctx.getBean(AuditOperationRepository.class);

  private Scanner scanner = new Scanner(System.in);

  public void titleRun() {
    System.out.println(

            "§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§ \n" +
                    "§§______________________________________§§ \n" +
                    "§§______________________________________§§ \n" +
                    "§§______________________________________§§ \n" +
                    "§§_________d-r-u-g-a-p-p__v_0.1_________§§ \n" +
                    "§§______________________________________§§ \n" +
                    "§§______________________________________§§ \n" +
                    "§§______________________________________§§ \n" +
                    "§§______________________________________§§ \n" +
                    "§§______________________________________§§ \n" +
                    "§§______________________________________§§ \n" +
                    "§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§ \n" +
                    "______§________§§§§§§§§§§ \n" +
                    "_____§____________§§§§ \n" +
                    "____§________§§§§§§§§§§§§§§ \n" +
                    "___§ \n" +
                    "__§______§§§§§§§§§§§§§§§§§§§§§§§§§§§ \n" +
                    "_§______§_§_§_§_§_§_§_§_§_§_§_§_§_§ \n" +
                    "§§§§___§_§_§_§_§_§_§_§_§_§_§_§_§_§ \n" +
                    "§§§§__§_§_§_§_§_§_§_§_§_§_§_§_§_§ \n" +
                    "§§§__§§§§§§§§§§§§§§§§§§§§§§§§§§§ \n" +
                    "       Hello! use 'help' for help ");
    run();
  }

  private void run() {
    while (true) {
      String line = scanner.nextLine();
      if (line.equals("help")) {
        runHelp();
      }
      String[] commands = line.split(" ");
      OperationType operationType = OperationType.valueOf(commands[ 0 ].toUpperCase());
      Crud crud = Crud.valueOf(commands[ 1 ].toUpperCase());
      crud.invoke(operationType.getRepository(ctx), operationType.getEntity(), commands);
    }

  }

  public void runHelp() {
    System.out.println(
            "Type in: state, patient, product, transaction or audit\n" +
                    "and one command: read, readall, create, delete or update.\n" +
                    "Application is still be not stable.\n" +
                    "Example:\n" +
                    "state create (id) (code) (full name state)\n" +
                    "state update 1 CA California\n" +
                    "patient create 4 332233 1\n" +
                    "product update 2 boyareshnik 1\n" +
                    "transaction create (id) (id patient) (id product)\n" +
                    "transaction create 2 2 1\n" +
                    "audit readall\n" +
                    "Set time is Localtime().now");
    run();
  }


}



package by.training.drugspayapplication.model.menu;

import by.training.drugspayapplication.config.db.DBConfig;
import by.training.drugspayapplication.entity.AbstractEntity;
import by.training.drugspayapplication.entity.Patient;
import by.training.drugspayapplication.entity.Product;
import by.training.drugspayapplication.repository.CRUDOperation;
import by.training.drugspayapplication.repository.PatientRepository;
import by.training.drugspayapplication.repository.ProductRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public enum Crud {
  READ {
    @Override
    void invoke(CRUDOperation repository, AbstractEntity entity, String... args) {
      System.out.println(repository.read(Long.parseLong(args[ 2 ])));
    }
  },
  READALL {
    @Override
    void invoke(CRUDOperation repository, AbstractEntity entity, String... args) {
      repository.readAll().stream().forEach(System.out::println);
    }
  },
  CREATE {
    @Override
    void invoke(CRUDOperation repository, AbstractEntity entity, String... args) {
      if (entity.getClass().getName().contains("Transaction")) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DBConfig.class);
        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
        Product product = productRepository.read(Long.valueOf(args[ 3 ]));
        PatientRepository patientRepository = ctx.getBean(PatientRepository.class);
        Patient patient = patientRepository.read(Long.valueOf(args[ 4 ]));
        entity.setValues(patient, product);
        System.out.println(repository.create(entity));
      } else {
        entity.setValues(args);
        System.out.println(repository.create(entity));
      }
    }
  },
  UPDATE {
    @Override
    void invoke(CRUDOperation repository, AbstractEntity entity, String... args) {
      if (entity.getClass().getName().contains("Transaction")) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DBConfig.class);
        ProductRepository productRepository = ctx.getBean(ProductRepository.class);
        Product product = productRepository.read(Long.valueOf(args[ 4 ]));
        PatientRepository patientRepository = ctx.getBean(PatientRepository.class);
        Patient patient = patientRepository.read(Long.valueOf(args[ 3 ]));
        entity.setValues(patient, product);
        System.out.println(repository.update(entity));
      } else {
        entity.setValues(args);
        System.out.println(repository.update(entity));
      }
    }
  },
  DELETE {
    @Override
    void invoke(CRUDOperation repository, AbstractEntity entity, String... args) {
      System.out.println(repository.delete(Long.parseLong(args[ 2 ])));
    }
  };

  abstract void invoke(CRUDOperation repository, AbstractEntity entity, String... args);
}


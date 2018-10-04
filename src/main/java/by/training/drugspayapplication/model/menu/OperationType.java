package by.training.drugspayapplication.model.menu;

import by.training.drugspayapplication.entity.*;
import by.training.drugspayapplication.repository.*;
import org.springframework.context.ApplicationContext;

public enum OperationType {

  PATIENT {
    @Override
    CRUDOperation getRepository(ApplicationContext ctx) {
      return ctx.getBean(PatientRepository.class);
    }

    @Override
    AbstractEntity getEntity() {
      return new Patient();
    }
  },
  PRODUCT {
    @Override
    CRUDOperation getRepository(ApplicationContext ctx) {
      return ctx.getBean(ProductRepository.class);
    }

    @Override
    AbstractEntity getEntity() {
      return new Product();
    }
  },
  STATE {
    @Override
    CRUDOperation getRepository(ApplicationContext ctx) {
      return ctx.getBean(StateRepository.class);
    }

    @Override
    AbstractEntity getEntity() {
      return new State();
    }
  },
  TRANSACTION {
    @Override
    CRUDOperation getRepository(ApplicationContext ctx) {
      return ctx.getBean(TransactionRepository.class);
    }

    @Override
    AbstractEntity getEntity() {
      return new Transaction();
    }
  },
  AUDIT {
    @Override
    CRUDOperation getRepository(ApplicationContext ctx) {
      return ctx.getBean(AuditOperationRepository.class);
    }

    @Override
    AbstractEntity getEntity() {
      return new AuditOperation();
    }
  };

  abstract AbstractEntity getEntity();

  abstract CRUDOperation getRepository(ApplicationContext ctx);
}

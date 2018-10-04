package by.training.drugspayapplication.repository;

import by.training.drugspayapplication.entity.AuditOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("auditOperationRepository")
public class AuditOperationRepository implements CRUDOperation <AuditOperation, Long> {

  private static String READ = "SELECT * FROM DRUGS_APP.db_audit_operation WHERE Dao_Id = :id";
  private static String READ_ALL = "SELECT * FROM DRUGS_APP.db_audit_operation";

  private static String CREATE = "INSERT INTO DRUGS_APP.db_audit_operation (Dao_Date, Dao_Status, Dao_Action) VALUES (:date, :status, :action)";

  private static String UPDATE = "UPDATE DRUGS_APP.db_audit_operation SET Dao_Date = :date, Dao_Action = :action WHERE Dao_Id = :id";
  private static String DELETE = "DELETE FROM DRUGS_APP.db_audit_operation WHERE Dao_Id = id";


  @Autowired
  private NamedParameterJdbcTemplate template;

  public AuditOperationRepository(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  @Override
  public boolean create(AuditOperation entity) {
    Map<String, Object> params = new HashMap<>();
    params.put("status", entity.isStatus());
    params.put("date", entity.getDate());
    params.put("action", entity.getAction());
    int count = template.update(CREATE, params);
    return count > 0;
  }

  @Override
  public AuditOperation read(Long id) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    final List<AuditOperation> auditOperations = new ArrayList<>();
    template.query(READ, params, new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet rs) throws SQLException {
        auditOperations.add(new AuditOperation(rs));
      }
    });
    return auditOperations.get(0);
  }

  @Override
  public boolean update(AuditOperation entity) {
    Map<String, Object> params = new HashMap<>();
    params.put("date", entity.getDate());
    params.put("action", entity.getAction());
    params.put("status", entity.isStatus());
    params.put("id", entity.getId());
    int count = template.update(UPDATE, params);
    return count > 0;
  }

  @Override
  public boolean delete(Long id) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    int count = template.update(DELETE, params);
    return count > 0;
  }

  @Override
  public List<AuditOperation> readAll() {
    List<AuditOperation> auditOperations = new ArrayList<>();
    template.query(READ_ALL, new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet rs) throws SQLException {
        auditOperations.add(new AuditOperation(rs));
      }
    });
    return auditOperations;
  }
}

package by.training.drugspayapplication.repository;



import by.training.drugspayapplication.entity.AuditOperation;
import by.training.drugspayapplication.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("transactionRepository")
public class TransactionRepository implements CRUDOperation<Transaction, Long> {

    private static String CREATE =
            "INSERT INTO DRUGS_APP.DB_TRANSACTION (Dtr_Dpi_Patient, Dtr_Dpr_Product, Dtr_Date) VALUES (:patient, :product, :date)";
    private static String READ_ALL =
            "SELECT  * " +
            "FROM drugs_app.DB_TRANSACTION " +
            "JOIN drugs_app.db_patient_info ON Dtr_Dpi_Patient = Dpi_id " +
            "JOIN drugs_app.db_product_info ON Dtr_Dpr_Product = Dpr_id";
    private static String READ = READ_ALL + " WHERE Dtr_Id = :id";
    private static String DELETE = "DELETE FROM DRUGS_APP.DB_TRANSACTION WHERE Dtr_Id = :id";
    private static String UPDATE =
            "UPDATE DRUGS_APP.DB_TRANSACTION SET Dtr_Dpi_Patient = :patient, Dtr_Dpr_Product = :product WHERE Dtr_Id = :id";
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Autowired
    private AuditOperationRepository auditOperationRepository;

    public TransactionRepository() {
    }

    public TransactionRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = namedParameterJdbcTemplate;
    }

    @Override
    public boolean create(Transaction entity) {
        if (!entity.getPatient().getState().equals(entity.getProduct().getState())) {

            auditOperationRepository.create(new AuditOperation(LocalDate.now(), false,entity.getPatient().getId().toString()+"-patientID  "+entity.getProduct().getName()+"-productName"));
            return false;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("patient", entity.getPatient().getId());
        params.put("product", entity.getProduct().getId());
        params.put("date", LocalDate.now());
        int count = template.update(CREATE, params);
        auditOperationRepository.create(new AuditOperation(LocalDate.now(), true,entity.getPatient().getId().toString()+"-patientID  "+entity.getProduct().getName()+"-productName"));
        return count > 0;
    }

    @Override
    public Transaction read(Long id) {
        List<Transaction> transactions = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        template.query(READ, params, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                transactions.add(new Transaction(rs));
            }
        });
        return transactions.get(0);
    }

    @Override
    public boolean update(Transaction entity) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", entity.getId());
        params.put("patient", entity.getPatient().getId());
        params.put("product", entity.getProduct().getId());
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
    public List<Transaction> readAll() {
        List<Transaction> transactions = new ArrayList<>();
        template.query(READ_ALL, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                transactions.add(new Transaction(rs));
            }
        });
        return transactions;
    }
}

package by.training.drugspayapplication.repository;
import by.training.drugspayapplication.entity.Patient;
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


@Component("patientRepository")
public class PatientRepository implements CRUDOperation<Patient, Long> {

  private static String READ = "SELECT * FROM DRUGS_APP.DB_PATIENT_INFO WHERE DPI_ID = :id";
  private static String READ_ALL = "SELECT * FROM DRUGS_APP.DB_PATIENT_INFO";

  private static String CREATE = "INSERT INTO DRUGS_APP.DB_PATIENT_INFO (DPI_PHONE, Dpi_Dsi_State) VALUES (:phone, :state)";

  private static String UPDATE = "UPDATE DRUGS_APP.DB_PATIENT_INFO SET DPI_PHONE = :phone, Dpi_Dsi_State = :state WHERE DPI_ID = :id";
  private static String DELETE = "DELETE FROM DRUGS_APP.DB_PATIENT_INFO WHERE DPI_ID = id";


  @Autowired
  private NamedParameterJdbcTemplate template;

  public PatientRepository(NamedParameterJdbcTemplate template) {
    this.template = template;
  }

  public PatientRepository() {
  }

  public boolean create(Patient entity) {
    Map<String, Object> params = new HashMap<>();
    params.put("phone", entity.getPhone());
    params.put("state", entity.getState());
    int count = template.update(CREATE, params);
    return count > 0;
  }

  @Override
  public Patient read(Long id) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    final List<Patient> patient = new ArrayList<>();
    template.query(READ, params, new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet rs) throws SQLException {
        patient.add(new Patient(rs));
      }
    });
    return patient.get(0);
  }

  @Override
  public boolean update(Patient entity) {
    Map<String, Object> params = new HashMap<>();
    params.put("phone", entity.getPhone());
    params.put("state", entity.getState());
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
  public List readAll() {
    List<Patient> patient = new ArrayList<>();
    template.query(READ_ALL, new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet rs) throws SQLException {
        patient.add(new Patient(rs));
      }
    });
    return patient;
  }
}
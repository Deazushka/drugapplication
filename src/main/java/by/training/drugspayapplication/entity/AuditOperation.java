package by.training.drugspayapplication.entity;





import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;


public class AuditOperation extends AbstractEntity<AuditOperation> {
  private Long id;
  private LocalDate date;
  private boolean status;
  private String action;

  @Override
  public void setValues(Object... args) {

  }

  public AuditOperation() {
  }

  public AuditOperation(LocalDate date, boolean status, String action) {
    this.date = date;
    this.status = status;
    this.action = action;
  }

  public AuditOperation(ResultSet rs) throws SQLException {
    this.id = rs.getLong("Dao_Id");
    this.date = rs.getDate("Dao_Date").toLocalDate();
    this.status = rs.getBoolean("Dao_Status");
    this.action = rs.getString("Dao_Action");
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AuditOperation that = (AuditOperation) o;
    return status == that.status &&
            Objects.equals(id, that.id) &&
            Objects.equals(date, that.date) &&
            Objects.equals(action, that.action);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date, status, action);
  }

  @Override
  public String toString() {
    return "AuditOperation{" +
            "id=" + id +
            ", date=" + date +
            ", status=" + status +
            ", action='" + action + '\'' +
            '}';
  }
}

package by.training.drugspayapplication.entity;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;


public class Transaction extends AbstractEntity<Transaction> {
    private Long id;
    private Patient patient;
    private Product product;
    private LocalDate date;

    @Override
    public void setValues(Object... args) {
        setPatient((Patient) args[0]);
        setProduct((Product) args[1]);
//        setDate(LocalDate.parse((String) args[2]));
    }

    public Transaction(Long id, Patient patient, Product product) {
        this.id = id;
        this.patient = patient;
        this.product = product;
    }

    public Transaction(Patient patient, Product product) {
        this.patient = patient;
        this.product = product;
    }

    public Transaction() {
    }

    public Transaction(ResultSet rs) throws SQLException {
        this.id = rs.getLong("Dtr_Id");
        this.date = rs.getDate("Dtr_Date").toLocalDate();
        this.patient = new Patient(
                rs.getLong("Dpi_id"),
                rs.getLong("Dpi_Phone"),
                rs.getLong("Dpi_Dsi_State")
        );
        this.product = new Product(
                rs.getLong("Dpr_id"),
                rs.getString("Dpr_Name"),
                rs.getLong("Dpr_Dsi_State")
        );
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(product, that.product) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, product, date);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", patient=" + patient +
                ", product=" + product +
                ", date=" + date +
                '}';
    }
}

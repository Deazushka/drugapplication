package by.training.drugspayapplication.entity;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class Patient extends AbstractEntity<Patient> {
    private Long id;
    private Long phone;
    private Long state;

    @Override
    public void setValues(Object... args) {
        setId(Long.valueOf((String) args[2]));
        setPhone(Long.valueOf((String) args[3]));
        setState(Long.valueOf((String) args[4]));
    }

    public Patient(Long id) {
        this.id = id;
    }

    public Patient(ResultSet rs) throws SQLException {
        this.id = rs.getLong("Dpi_Id");
        this.phone = rs.getLong("Dpi_Phone");
        this.state = rs.getLong("Dpi_Dsi_State");
    }

    public Patient(Long phone, Long state) {
        this.phone = phone;
        this.state = state;
    }

    public Patient(Long id, Long phone, Long state) {
        this.id = id;
        this.phone = phone;
        this.state = state;
    }

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) &&
                Objects.equals(phone, patient.phone) &&
                Objects.equals(state, patient.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, state);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", phone=" + phone +
                ", state=" + state +
                '}';
    }


}
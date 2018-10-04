package by.training.drugspayapplication.entity;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class State extends AbstractEntity<State> {
    private Long id;
    private String code;
    private String name;

    @Override
    public void setValues(Object... args) {
        setId(Long.valueOf((String) args[2]));
        setCode((String) args[3]);
        setName((String) args[4]);
    }

    public State(ResultSet rs) throws SQLException {
        this.id = rs.getLong("Dsi_Id");
        this.code = rs.getString("Dsi_Code");
        this.name = rs.getString("Dsi_Name");
    }

    public State() {
    }

  public State(String code, String name) {
    this.code = code;
    this.name = name;
  }

    public State(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    State state = (State) o;
    return Objects.equals(id, state.id) &&
            Objects.equals(code, state.code) &&
            Objects.equals(name, state.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, code, name);
  }

  @Override
  public String toString() {
    return "State{" +
            "id=" + id +
            ", code='" + code + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}

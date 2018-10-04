package by.training.drugspayapplication.entity;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;


public class Product extends AbstractEntity<Product> {
    private Long id;
    private String name;
    private Long state;

    @Override
    public void setValues(Object... args) {
        setId(Long.valueOf((String) args[2]));
        setName((String) args[3]);
        setState(Long.valueOf((String) args[4]));
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(ResultSet rs) throws SQLException {
        this.id = rs.getLong("Dpr_Id");
        this.name = rs.getString("Dpr_Name");
        this.state = rs.getLong("Dpr_Dsi_State");
    }

    public Product(Long id, String name, Long state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public Product() {
    }

    public Product(String name, Long state) {
        this.name = name;
        this.state = state;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(state, product.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, state);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}

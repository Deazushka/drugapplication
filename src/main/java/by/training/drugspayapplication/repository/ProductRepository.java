package by.training.drugspayapplication.repository;

import by.training.drugspayapplication.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component("productRepository")
public class ProductRepository implements CRUDOperation<Product, Long> {

    private static String READ = "SELECT * FROM DRUGS_APP.DB_PRODUCT_INFO WHERE DPR_ID = :id";
    private static String READ_ALL = "SELECT * FROM DRUGS_APP.DB_PRODUCT_INFO";

    private static String CREATE =
            "INSERT INTO DRUGS_APP.DB_PRODUCT_INFO (Dpr_Name, Dpr_Dsi_State) VALUES (:name, :state)";

    private static String UPDATE =
            "UPDATE DRUGS_APP.DB_PRODUCT_INFO SET Dpr_Name = :name, Dpr_Dsi_State = :state WHERE DPR_ID  = :id";
    private static String DELETE = "DELETE FROM DRUGS_APP.DB_PRODUCT_INFO WHERE DPR_ID = :id";

    public ProductRepository() {
    }

    @Autowired
    private NamedParameterJdbcTemplate template;

    public ProductRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = namedParameterJdbcTemplate;
    }

    @Override
    public boolean create(Product entity) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", entity.getName());
        params.put("state", entity.getState());
        int count = template.update(CREATE, params);
        return count > 0;
    }

    @Override
    public Product read(Long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        final List<Product> product = new ArrayList<>();
        template.query(READ, params, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                product.add(new Product(rs));
            }
        });
        return product.get(0);

    }

    @Override
    public boolean update(Product entity) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", entity.getId());
        params.put("name", entity.getName());
        params.put("state", entity.getState());
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
    public List<Product> readAll() {
        List<Product> product = new ArrayList<>();
        template.query(READ_ALL, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                product.add(new Product(rs));
            }
        });
        return product;
    }
}

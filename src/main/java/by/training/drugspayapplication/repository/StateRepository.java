package by.training.drugspayapplication.repository;


import by.training.drugspayapplication.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

@Component("stateRepository")
public class StateRepository implements CRUDOperation<State, Long> {
  private static String READ = "SELECT * FROM drugs_app.db_state_info WHERE Dsi_Id = :id";
  private static String READ_ALL = "SELECT * FROM drugs_app.db_state_info";
  private static String CREATE =
          "INSERT INTO drugs_app.db_state_info (DSI_CODE, DSI_NAME) VALUES (:code, :name)";
  private static String UPDATE =
          "UPDATE drugs_app.db_state_info SET DSI_CODE = :code, DSI_NAME = :name WHERE Dsi_Id = :id";
  private static String DELETE = "DELETE FROM drugs_app.db_state_info WHERE Dsi_Id = :id";


  @Autowired
  private NamedParameterJdbcTemplate template;


  public StateRepository() {
  }

  public StateRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.template = namedParameterJdbcTemplate;
  }

  @Override
  public boolean create(State entity) {
    Map<String, Object> params = new HashMap<>();
    params.put("name", entity.getName());
    params.put("code", entity.getCode());
    int count = template.update(CREATE, params);
    return count > 0;
  }

  @Override
  public State read(Long id) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    final List<State> states = new ArrayList<>();
    template.query(READ, params, new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet rs) throws SQLException {
        states.add(new State(rs));
      }
    });
    return states.get(0);
  }

  @Override
  public boolean update(State entity) {
    Map<String, Object> params = new HashMap<>();
    params.put("id", entity.getId());
    params.put("name", entity.getName());
    params.put("code", entity.getCode());
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
  public List<State> readAll() {
    List<State> states = new ArrayList<>();
    template.query(READ_ALL, new RowCallbackHandler() {
      @Override
      public void processRow(ResultSet rs) throws SQLException {
        states.add(new State(rs));
      }
    });
    return states;
  }
}

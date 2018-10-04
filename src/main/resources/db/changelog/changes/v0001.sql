CREATE SCHEMA drugs_app;

CREATE TABLE drugs_app.db_state_info (
  Dsi_Id   BIGINT(20)  NOT NULL   AUTO_INCREMENT,
  Dsi_Code VARCHAR(4)  NOT NULL,
  Dsi_Name VARCHAR(15) NOT NULL,
  PRIMARY KEY (Dsi_Id)
);

INSERT INTO drugs_app.db_state_info (Dsi_Code, Dsi_Name) VALUES
  ('PL', 'Philadelphia'),
  ('WS', 'Washington');
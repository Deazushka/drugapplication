CREATE TABLE drugs_app.db_patient_info (
  Dpi_Id        BIGINT(20) NOT NULL   AUTO_INCREMENT,
  Dpi_Phone     BIGINT(20) NOT NULL,
  Dpi_Dsi_State BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (Dpi_Id),
  FOREIGN KEY (Dpi_Dsi_State) REFERENCES drugs_app.db_state_info (Dsi_Id)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);

CREATE TABLE drugs_app.db_product_info (
  Dpr_Id        BIGINT(20)  NOT NULL   AUTO_INCREMENT,
  Dpr_Name      VARCHAR(15) NOT NULL,
  Dpr_Dsi_State BIGINT(20)  DEFAULT NULL,
  PRIMARY KEY (Dpr_Id),
  FOREIGN KEY (Dpr_Dsi_State) REFERENCES drugs_app.db_state_info (Dsi_Id)
    ON UPDATE CASCADE
    ON DELETE SET NULL
);


INSERT INTO drugs_app.db_patient_info (Dpi_Phone, Dpi_Dsi_State) VALUES
  (333123, 1),
  (366666, 2),
  (123455, 2);

INSERT INTO drugs_app.db_product_info (Dpr_Name, Dpr_Dsi_State) VALUES
  ('pervin', 1),
  ('revit', 2),
  ('dxm', 2);
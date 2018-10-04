CREATE TABLE drugs_app.db_transaction (
  Dtr_Id           BIGINT(20)     NOT NULL    AUTO_INCREMENT,
  Dtr_Dpi_Patient  BIGINT(20)     NOT NULL,
  Dtr_Dpr_Product  BIGINT(20)     NOT NULL,
  Dtr_Date         DATETIME       NOT NULL,
  PRIMARY KEY (Dtr_Id),
  FOREIGN KEY (Dtr_Dpi_Patient) REFERENCES drugs_app.db_patient_info (Dpi_Id),
  FOREIGN KEY (Dtr_Dpr_Product) REFERENCES drugs_app.db_product_info (Dpr_Id)
);
INSERT INTO drugs_app.db_transaction (Dtr_Dpi_Patient, Dtr_Dpr_Product, Dtr_Date) VALUES
  (1, 1, '2000-01-01'),
  (1, 1, '2000-01-01'),
  (1, 1, '2000-01-01'),
  (1, 1, '2000-01-01'),
  (1, 2, '2000-01-01'),
  (1, 2, '2000-01-01'),
  (1, 2, '2000-01-01'),
  (2, 1, '1998-01-01'),
  (2, 2, '2001-01-01'),
  (2, 3, '2000-01-01'),
  (2, 1, '1999-01-01'),
  (2, 2, '2003-01-01');
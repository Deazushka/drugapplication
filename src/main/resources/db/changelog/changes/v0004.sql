CREATE TABLE drugs_app.db_audit_operation (
  Dao_Id      BIGINT(20)     NOT NULL   AUTO_INCREMENT,
  Dao_Date    DATETIME       NOT NULL,
  Dao_Status  BOOLEAN        NOT NULL,
  Dao_Action  LONGTEXT       NOT NULL,
  PRIMARY KEY (Dao_Id)
);
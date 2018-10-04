package by.training.dao.mapper;

import by.training.model.Transaction;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface TransactionMapper {
	@Results({
			@Result(property = "vid", column = "id"),
			@Result(property = "villageName", column = "name"),
			@Result(property = "district", column = "district")
	})
	@Select ("SELECT " +
			"    dpi_id AS patient_id," +
			"    dpi_phone AS phone, " +
			"    dsi_name AS state, " +
			"    dpr_name AS product, " +
			"    count(dpr_id) AS count, " +
			"    max(dtr_date) AS last_transaction " +
			" FROM drugs_app.db_transaction tr " +
			"JOIN drugs_app.db_patient_info pt " +
			"   ON tr.dtr_dpi_patient = pt.dpi_id " +
			"JOIN drugs_app.db_product_info pd " +
			"   ON tr.dtr_dpr_product = pd.dpr_id " +
			"JOIN drugs_app.db_state_info st " +
			"   ON st.dsi_id = pt.dpi_dsi_state " +
			"WHERE dtr_date BETWEEN #{dateFrom} AND #{dateTo} " +
			"GROUP BY dpi_id, dpi_phone, dsi_name, dpr_name")
	public List<Transaction> getTransactions(
			@Param("dateFrom") Timestamp dateFrom,
			@Param("dateTo") Timestamp dateTo
	);
}

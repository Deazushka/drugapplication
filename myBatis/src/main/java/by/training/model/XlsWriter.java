package by.training.model;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class XlsWriter implements AutoCloseable {
    private static String SHEET_NAME = "Result";

    private List<String> headers;
    private SXSSFWorkbook workbook;
    private Sheet sheet;
    private int rowCounter = 0;

    public XlsWriter(List<String> headers) {
        this.workbook = new SXSSFWorkbook();
        sheet = workbook.createSheet(SHEET_NAME);
        rowCounter = 0;
        if (!headers.isEmpty()) {
            writeRow(headers);
        }
    }

    public void writeRow(List<String> values) {
        Row row = sheet.createRow(rowCounter++);
        for (int i = 0; i < values.size(); i++) {
            String value = values.get(i);
            row.createCell(i).setCellValue(value);
        }
    }

    public byte[] toByteArray() {
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            return bos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void close() throws Exception {
        workbook.dispose();
        workbook.close();
    }
}

package by.training.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private Long patientId;
    private Long phone;
    private String state;
    private String product;
    private Long count;
    private Timestamp lastTransaction;

    public Transaction(Long patientId) {
        this.patientId = patientId;
    }

    public Transaction(Long patientId, Long phone, String state, String product, Long count, Timestamp lastTransaction) {
        this.patientId = patientId;
        this.phone = phone;
        this.state = state;
        this.product = product;
        this.count = count;
        this.lastTransaction = lastTransaction;
    }

    public List<String> toList() {
        List<String> list = new ArrayList<>();
        list.add(patientId.toString());
        list.add(phone.toString());
        list.add(state);
        list.add(product);
        list.add(count.toString());
        list.add(lastTransaction
                .toString()
                .substring(0, lastTransaction.toString().lastIndexOf(":")));
        return list;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "patientId=" + patientId +
                ", phone=" + phone +
                ", state='" + state + '\'' +
                ", product='" + product + '\'' +
                ", count=" + count +
                ", lastTransaction=" + lastTransaction +
                '}';
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Timestamp getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(Timestamp lastTransaction) {
        this.lastTransaction = lastTransaction;
    }
}

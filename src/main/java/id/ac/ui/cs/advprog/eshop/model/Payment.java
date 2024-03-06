package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;

        if (method.equals(PaymentMethod.VOUCHER.getValue())) {
            if (!paymentData.containsKey("voucherCode")) {
                throw new IllegalArgumentException();
            }
            if (paymentData.get("voucherCode").length() != 16){
                setStatus(PaymentStatus.REJECTED.getValue());
            } else if (!paymentData.get("voucherCode").startsWith("ESHOP")){
                setStatus(PaymentStatus.REJECTED.getValue());
            } else if (countNumericalCharacters(paymentData.get("voucherCode")) != 8){
                setStatus(PaymentStatus.REJECTED.getValue());
            } else {
                setStatus(PaymentStatus.SUCCESS.getValue());
            }

        } else if (method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
            if (!paymentData.containsKey("bankName")) {
                throw new IllegalArgumentException();
            } else if (!paymentData.containsKey("referenceCode")) {
                throw new IllegalArgumentException();
            }
            if (paymentData.get("bankName") == null){
                setStatus(PaymentStatus.REJECTED.getValue());
            } else if (paymentData.get("referenceCode") == null){
                setStatus(PaymentStatus.REJECTED.getValue());
            } else {
                setStatus(PaymentStatus.SUCCESS.getValue());
            }

        } else {
            throw new IllegalArgumentException();
        }
    }

    private int countNumericalCharacters(String string) {
        int count = 0;
        for (char c : string.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
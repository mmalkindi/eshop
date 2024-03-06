package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.Arrays;
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

        if (method.equals("voucher")) {
            if (!paymentData.containsKey("voucherCode")) {
                throw new IllegalArgumentException();
            }
            if (paymentData.get("voucherCode").length() != 16){
                this.status = "REJECTED";
            } else if (!paymentData.get("voucherCode").startsWith("ESHOP")){
                this.status = "REJECTED";
            } else if (countNumericalCharacters(paymentData.get("voucherCode")) != 8){
                this.status = "REJECTED";
            } else {
                this.status = "SUCCESS";
            }

        } else if (method.equals("bankTransfer")) {
            if (!paymentData.containsKey("bankName")) {
                throw new IllegalArgumentException();
            } else if (!paymentData.containsKey("referenceCode")) {
                throw new IllegalArgumentException();
            }
            if (paymentData.get("bankName") == null){
                this.status = "REJECTED";
            } else if (paymentData.get("referenceCode") == null){
                this.status = "REJECTED";
            } else {
                this.status = "SUCCESS";
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
        String[] statusList ={"SUCCESS", "REJECTED"};
        if(Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))){
            throw new IllegalArgumentException();
        } else{
            this.status = status;
        }
    }
}
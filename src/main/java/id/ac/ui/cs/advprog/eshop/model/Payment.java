package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    Map<String, String> paymentData;
    @Setter
    String status;

    public Payment(String id, String method, Map<String, String> paymentData) {
    }
}

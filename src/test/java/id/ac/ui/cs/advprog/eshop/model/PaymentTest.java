package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class PaymentTest {
    private Map<String, String> voucherPaymentData;
    private Map<String, String> bankPaymentData;

    @BeforeEach
    void setUp() {
        voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "ESHOP1234ABC5678");

        bankPaymentData = new HashMap<>();
        bankPaymentData.put("bankName", "Union Depository");
        bankPaymentData.put("referenceCode", "UD");
    }

    @Test
    void testCreateInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("5fb0fffe-0080-439c-bd5c-2e6824c54c7b",
                    "something", voucherPaymentData);
        });
    }

    @Test
    void testCreateValidVoucher() {
        Payment payment = new Payment("5fb0fffe-0080-439c-bd5c-2e6824c54c7b",
                "voucher", voucherPaymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreateInvalidVoucherNot16Characters() {
        voucherPaymentData.put("voucherCode", "ESHOP1234ABC56789");

        Payment payment = new Payment("5fb0fffe-0080-439c-bd5c-2e6824c54c7b",
                "voucher", voucherPaymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateInvalidVoucherNotStartsWithESHOP() {
        voucherPaymentData.put("voucher", "ESH0P1234ABC56789");

        Payment payment = new Payment("5fb0fffe-0080-439c-bd5c-2e6824c54c7b",
                "voucher", voucherPaymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateInvalidVoucherNot8Numerical() {
        voucherPaymentData.put("voucher", "ESHOP1234ABC567D");

        Payment payment = new Payment("5fb0fffe-0080-439c-bd5c-2e6824c54c7b",
                "voucher", voucherPaymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateVoucherInvalidPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("5fb0fffe-0080-439c-bd5c-2e6824c54c7b",
                    "voucher", bankPaymentData);
        });
    }

    @Test
    void testCreateValidBankTransfer() {
        Payment payment = new Payment("5fb0fffe-0080-439c-bd5c-2e6824c54c7b",
                "bankTransfer", bankPaymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreateInvalidBankEmptyName() {
        bankPaymentData.put("bankName", null);

        Payment payment = new Payment("5fb0fffe-0080-439c-bd5c-2e6824c54c7b",
                "bankTransfer", bankPaymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateInvalidBankEmptyReferenceCode() {
        bankPaymentData.put("referenceCode", null);

        Payment payment = new Payment("5fb0fffe-0080-439c-bd5c-2e6824c54c7b",
                "bankTransfer", bankPaymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateBankTransferInvalidPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("5fb0fffe-0080-439c-bd5c-2e6824c54c7b",
                    "bankTransfer", voucherPaymentData);
        });
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("5fb0fffe-0080-439c-bd5c-2e6824c54c7b",
                "voucher", voucherPaymentData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("5fb0fffe-0080-439c-bd5c-2e6824c54c7b",
                "bankTransfer", bankPaymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("ZCZC"));
    }
}

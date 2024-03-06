package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();

        Map<String, String> voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "ESHOP1234ABC5678");

        Map<String, String> bankPaymentData = new HashMap<>();
        bankPaymentData.put("bankName", "Union Depository");
        bankPaymentData.put("referenceCode", "UD");

        payments = new ArrayList<>();
        Payment payment1 = new Payment("eb558e9f-1c39-460e-8860-71af6af63bd6",
                PaymentMethod.VOUCHER.getValue(), voucherPaymentData);
        payments.add(payment1);
        Payment payment2 = new Payment("0ad22a3a-3e61-47dd-95f0-d528d6d997d1",
                PaymentMethod.VOUCHER.getValue(), voucherPaymentData);
        payments.add(payment2);
        Payment payment3 = new Payment("f50010a7-8747-4bae-ba07-f38f5e18d8f8",
                PaymentMethod.BANK_TRANSFER.getValue(), bankPaymentData);
        payments.add(payment3);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(1);
        Payment result = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(1);
        paymentRepository.save(payment);

        Map<String, String> newPaymentData = new HashMap<>();
        newPaymentData.put("bankName", "Union Depository");
        newPaymentData.put("referenceCode", "UD");

        Payment newPayment = new Payment(payment.getId(),
                PaymentMethod.BANK_TRANSFER.getValue(), newPaymentData);
        Payment result = paymentRepository.save(newPayment);

        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), findResult.getMethod());
        assertEquals(newPaymentData, findResult.getPaymentData());
        assertEquals(payment.getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment toFind = payments.get(1);
        Payment findResult = paymentRepository.findById(toFind.getId());
        assertEquals(toFind.getId(), findResult.getId());
        assertEquals(toFind.getMethod(), findResult.getMethod());
        assertEquals(toFind.getPaymentData(), findResult.getPaymentData());
        assertEquals(toFind.getStatus(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }

        Iterator<Payment> paymentIterator = paymentRepository.findAll();
        assertTrue(paymentIterator.hasNext());
        int index = 0;
        while (paymentIterator.hasNext() && index < payments.size()) {
            Payment findResult = paymentIterator.next();
            assertEquals(payments.get(index).getId(), findResult.getId());
            assertEquals(payments.get(index).getId(), findResult.getId());
            assertEquals(payments.get(index).getId(), findResult.getId());
            index++;
        }
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Payment> paymentIterator = paymentRepository.findAll();
        assertFalse(paymentIterator.hasNext());
    }
}
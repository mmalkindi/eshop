package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    @Mock
    OrderRepository orderRepository;
    private Map<String, String> paymentData;
    private Order order;
    private Payment payment;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("6a48c142-207c-44bb-9278-f0cb2816ed97",
                products, 1708560000L, "Ruri Sinaga");
        orderRepository.save(order);

        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        payment = new Payment("6a48c142-207c-44bb-9278-f0cb2816ed97",
                PaymentMethod.VOUCHER.getValue(), paymentData);
    }

    @Test
    void testAddPayment() {
        Payment result = paymentService.addPayment(order, PaymentMethod.VOUCHER.getValue(), paymentData);
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
        assertEquals(payment.getStatus(), result.getStatus());
    }

    @Test
    void testSetStatusSuccess() {
        Payment newPayment = paymentService.addPayment(order, PaymentMethod.VOUCHER.getValue(), paymentData);
        verify(paymentRepository, times(1)).save(payment);

        Payment updatedPayment = paymentService.setStatus(newPayment, PaymentStatus.SUCCESS.getValue());
        verify(orderRepository, times(1)).save(any(Order.class));

        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusRejected() {
        paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());

        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }

    @Test
    void testGetPaymentIfExists() {
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testGetPaymentIfNotExists() {
        doReturn(null).when(paymentRepository).findById("zczc");
        assertNull(paymentService.getPayment("zczc"));
    }

    @Test
    void testGetAllPayments() {
        List<Payment> results = paymentService.getAllPayments();
        assertEquals(1, results.size());
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        List<Payment> results = paymentService.getAllPayments();
        assertTrue(results.isEmpty());
    }
}

package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Cart cart = purchase.getCart();
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);
        Set<CartItem> cartItems = purchase.getCartItems();
        cart.setStatus(StatusType.ordered);
        cartItems.forEach(item -> {
            cart.add(item);
            item.setCart(cart);

        });
        Customer customer = purchase.getCustomer();
        customer.add(cart);
        customerRepository.save(customer);
        cartRepository.save(cart);

        return new PurchaseResponse(orderTrackingNumber);
    }
    String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}

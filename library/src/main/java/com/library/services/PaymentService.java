package com.library.services;

import com.library.models.Payment;
import com.library.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment save(Payment payment){

        return paymentRepository.save(payment);
    }

    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }

    public void deleteById(Long idPayment){
        Payment payment = paymentRepository.findById(idPayment).orElseThrow();
        paymentRepository.delete(payment);
    }

    public Payment findById(Long idPayment){
        return paymentRepository.findById(idPayment).orElseThrow();
    }



}

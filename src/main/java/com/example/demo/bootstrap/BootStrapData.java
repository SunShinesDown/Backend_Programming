package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Division division1 = new Division(3L);
        division1.setId(430110L);

        Customer customer1 = new Customer("Marcus", "Columbus","17618 cali dr, Mezcal",
                "32552", "123-312-8899", division1);
        customer1.setDivision(division1);
        division1.getCustomers().add(customer1);
        divisionRepository.save(division1);
        customerRepository.save(customer1);


        Division division2 = new Division(2L);
        division2.setId(430111L);

        Customer customer2 = new Customer("Jake", "Polio","18234 Bumbay st, Golabad",
                "62532", "173-315-5898", division2);
        customer2.setDivision(division2);
        division2.getCustomers().add(customer2);
        divisionRepository.save(division2);
        customerRepository.save(customer2);


        Division division3 = new Division(1L);
        division3.setId(430112L);

        Customer customer3 = new Customer("Zeynab", "Kobra","28534 Dastan ave, Rostamabad",
                "22553", "911-345-5878", division3);
        customer3.setDivision(division3);
        division3.getCustomers().add(customer3);
        divisionRepository.save(division3);
        customerRepository.save(customer3);


        Division division4 = new Division(1L);
        division4.setId(430113L);

        Customer customer4 = new Customer("Cole", "Solomon","62162 Murphy Plain, Schadenbury",
                "06972", "324-347-5479", division4);
        customer4.setDivision(division4);
        division4.getCustomers().add(customer4);
        divisionRepository.save(division4);
        customerRepository.save(customer4);


        Division division5 = new Division(3L);
        division4.setId(430114L);

        Customer customer5 = new Customer("Felicity", "Matthews","773 Abernathy Trace, Simoneville",
                "46818", "424-747-3499", division5);
        customer5.setDivision(division5);
        division5.getCustomers().add(customer5);
        divisionRepository.save(division5);
        customerRepository.save(customer5);


        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }

    }
}

package orgsid.orderservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import orgsid.orderservice.entities.Order;
import orgsid.orderservice.entities.ProductItem;
import orgsid.orderservice.enums.OrderStatus;
import orgsid.orderservice.model.Customer;
import orgsid.orderservice.model.Product;
import orgsid.orderservice.repository.OrderRepository;
import orgsid.orderservice.repository.ProductItemRepository;
import orgsid.orderservice.services.CustomerRestClientService;
import orgsid.orderservice.services.InventoryRestClientService;

import java.util.*;


@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }



    @Bean
    CommandLineRunner start(OrderRepository orderRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClientService customerRestClientService,
                            InventoryRestClientService inventoryRestClientService){
        return args -> {
            List<Customer> customers = customerRestClientService.allCustomer().getContent().stream().toList();
            List<Product> products= inventoryRestClientService.allProducts().getContent().stream().toList();
//            Long customerId=1L;
            Random random = new Random();
//            Customer customer = customerRestClientService.customerById(customerId);
            for (int i = 0; i < 20; i++) {
                Order order = Order.builder()
                        .customerId(customers.get(random.nextInt(customers.size())).getId())
                        .status(Math.random()>0.5?OrderStatus.PENDING:OrderStatus.CREATED)
                        .createdAt(new Date())
                        .build();
                Order savedOrder = orderRepository.save(order);
                for (int j = 0; j <products.size(); j++) {
                    if (Math.random()>0.70){
                    ProductItem productItem = ProductItem.builder()
                            .order(savedOrder)
                            .productid(products.get(j).getId())
                            .price(products.get(j).getPrice())
                            .quantity(1+random.nextInt(10))
                            .discount(Math.random())
                            .build();
                    productItemRepository.save(productItem);
                    }
                }
            }
        };
    }
}

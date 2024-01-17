package orgsid.orderservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import orgsid.orderservice.entities.Order;
import orgsid.orderservice.model.Customer;
import orgsid.orderservice.model.Product;
import orgsid.orderservice.repository.OrderRepository;
import orgsid.orderservice.repository.ProductItemRepository;
import orgsid.orderservice.services.CustomerRestClientService;
import orgsid.orderservice.services.InventoryRestClientService;

@RestController
public class OrderRestController {
    private OrderRepository orderRepository;
    private ProductItemRepository productItemRepository;
    private CustomerRestClientService customerRestClientService;
    private InventoryRestClientService inventoryRestClientService;

    public OrderRestController(OrderRepository orderRepository, ProductItemRepository productItemRepository, CustomerRestClientService customerRestClientService, InventoryRestClientService inventoryRestClientService) {
        this.orderRepository = orderRepository;
        this.productItemRepository = productItemRepository;
        this.customerRestClientService = customerRestClientService;
        this.inventoryRestClientService = inventoryRestClientService;
    }

    @GetMapping(path = "/fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order = orderRepository.findById(id).get();
        Customer customer = customerRestClientService.customerById(order.getId());
        order.setCustomer(customer);
        order.getProductItems().forEach(pi -> {
            Product product = inventoryRestClientService.productById(pi.getProductid());
            pi.setProduct(product);
        });
        return order;
    }
}

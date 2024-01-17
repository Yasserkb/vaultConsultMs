package orgsid.orderservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import orgsid.orderservice.model.Customer;

@FeignClient(name = "customer-service")
public interface CustomerRestClientService {
    @GetMapping("/customers/{id}?projection=fullCustomer")
    Customer customerById(@PathVariable Long id);
    @GetMapping("/customers?projection=fullCustomer")
    PagedModel<Customer> allCustomer();

}

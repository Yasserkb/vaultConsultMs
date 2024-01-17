package orgsid.orderservice.entities;

import org.springframework.data.rest.core.config.Projection;
import orgsid.orderservice.enums.OrderStatus;

import java.util.Date;

@Projection(name = "fullOrder",types = Order.class)
public interface OrderProjection {
    Long getId();
    Date getCreatedAt();
    Long getCustomerId();
    OrderStatus getStatus();
}

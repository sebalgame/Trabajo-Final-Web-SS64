package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketTypeDTO {

    private Long id;
    private String name;
    private Double price;
    private Integer availableQuantity;
    private EventDTO event;
    private List<PurchasedTicketsDTO> purchasedTickets;
}

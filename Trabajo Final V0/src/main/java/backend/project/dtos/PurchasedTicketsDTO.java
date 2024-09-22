package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchasedTicketsDTO {

    private Long id;
    private Double purchasePrice;
    private LocalDateTime purchaseDate;
    private TicketTypeDTO ticketType;
    private TransactionDTO transaction;
}

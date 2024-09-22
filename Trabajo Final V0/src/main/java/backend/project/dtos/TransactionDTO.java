package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private Integer quantity;
    private ClientDTO client;
    private List<PurchasedTicketsDTO> purchasedTickets;
}

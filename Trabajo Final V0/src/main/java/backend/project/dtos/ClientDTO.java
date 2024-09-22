package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String phone;
    private String dni;
    private UserDTO user;
    private List<CommentDTO> comments;
    private List<FavoriteDTO> favorites;
    private List<TransactionDTO> transactions;
}

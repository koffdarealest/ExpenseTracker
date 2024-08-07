package koff.expense.model.dto.expense;

import koff.expense.model.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExpenseDTO {
    private String id;
    private String tag;
    private String description;
    private Integer type;
    private Double amount;
    private String date;
    private UserDTO user;
}

package koff.expense.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "expense")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Expense {
    @Id
    private String id;
    private String tag;
    private String description;
    private Integer type;
    private Double amount;
    private String date;
    private User user;
}

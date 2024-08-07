package koff.expense.model.form.expense;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddExpenseForm {
    private String tag;
    private String description;
    private Integer type;
    private Double amount;
    private String date;
}

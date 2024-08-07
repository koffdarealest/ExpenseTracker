package koff.expense.service;

import koff.expense.model.dto.expense.ExpenseDTO;
import koff.expense.model.form.expense.AddExpenseForm;
import koff.expense.model.form.expense.UpdateExpenseForm;

import java.util.List;

public interface ExpenseService {

    void addExpense(AddExpenseForm addExpenseForm);

    void updateExpense(UpdateExpenseForm updateExpenseForm);

    ExpenseDTO getExpenseById(String id);

    void deleteExpense(String id);

    List<ExpenseDTO> getExpenses();
}

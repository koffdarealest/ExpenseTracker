package koff.expense.controller;

import jakarta.validation.Valid;
import koff.expense.model.dto.expense.ExpenseDTO;
import koff.expense.model.form.expense.AddExpenseForm;
import koff.expense.model.form.expense.UpdateExpenseForm;
import koff.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@Validated
@Log4j2
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping()
    public void addExpense(@Valid @RequestBody AddExpenseForm addExpenseForm) {
        expenseService.addExpense(addExpenseForm);
        log.info("Add expense");
    }

    @PutMapping()
    public void updateExpense(@Valid @RequestBody UpdateExpenseForm updateExpenseForm) {
        expenseService.updateExpense(updateExpenseForm);
        log.info("Update expense");
    }

    @GetMapping("/{id}")
    public ExpenseDTO getExpenseById(
            @PathVariable String id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(
            @PathVariable String id) {
        expenseService.deleteExpense(id);
        log.info("Delete expense");
    }

    @GetMapping("/list")
    public List<ExpenseDTO> getExpenses() {
        return expenseService.getExpenses();
    }
}

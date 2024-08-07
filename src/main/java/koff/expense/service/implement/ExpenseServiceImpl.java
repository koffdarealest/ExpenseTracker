package koff.expense.service.implement;

import koff.expense.config.CustomMapper;
import koff.expense.exception.NoAuthorityException;
import koff.expense.exception.ResourceNotFoundException;
import koff.expense.model.dto.expense.ExpenseDTO;
import koff.expense.model.entity.Expense;
import koff.expense.model.form.expense.AddExpenseForm;
import koff.expense.model.form.expense.UpdateExpenseForm;
import koff.expense.repository.IExpenseRepository;
import koff.expense.repository.IUserRepository;
import koff.expense.service.ExpenseService;
import koff.expense.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final IExpenseRepository expenseRepository;
    private final JwtService jwtService;
    private final IUserRepository userRepository;
    private final CustomMapper customMapper;

    @Override
    public void addExpense(AddExpenseForm addExpenseForm) {
        Expense expense = customMapper.convert(addExpenseForm, Expense.class);
        String id = jwtService.getIdFromToken();
        userRepository.findById(id).ifPresent(expense::setUser);
        expenseRepository.insert(expense);
    }

    @Override
    public void updateExpense(UpdateExpenseForm updateExpenseForm) {
        Expense expense = expenseRepository.findById(updateExpenseForm.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Expense not found")
        );
        String id = jwtService.getIdFromToken();
        if (!id.equals(expense.getUser().getId())) {
            throw new NoAuthorityException("No authority for this expense");
        }
        expense = customMapper.convert(updateExpenseForm, expense);
        expenseRepository.save(expense);
    }

    @Override
    public ExpenseDTO getExpenseById(String id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Expense not found")
        );
        return customMapper.convert(expense, ExpenseDTO.class);
    }

    @Override
    public void deleteExpense(String id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Expense not found")
        );
        String userId = jwtService.getIdFromToken();
        if (!userId.equals(expense.getUser().getId())) {
            throw new NoAuthorityException("No authority for this expense");
        }
        expenseRepository.deleteById(id);
    }

    @Override
    public List<ExpenseDTO> getExpenses() {
        String id = jwtService.getIdFromToken();
        List<Expense> expenses = expenseRepository.getExpensesByUserId(id);
        return customMapper.convertList(expenses, ExpenseDTO.class);
    }
}

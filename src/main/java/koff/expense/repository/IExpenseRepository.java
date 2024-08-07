package koff.expense.repository;

import koff.expense.model.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IExpenseRepository extends MongoRepository<Expense, String> {
    List<Expense> getExpensesByUserId(String userId);
}

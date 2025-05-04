package package1;

import java.io.*;
import java.util.*;

class Expense implements Serializable {
    private static final long serialVersionUID = 1L;
    private String description;
    private double amount;

    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return description + ": " + String.format("%.2f", amount);
    }
}

public class ExpenseManager {

    private static final String FILE = "expenses.dat";

    public static void saveExpenses(List<Expense> expenses) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            System.err.println("Error saving expenses are " + e.getMessage());
        }
    }

    public static List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            expenses = (List<Expense>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading expenses- " + e.getMessage());
        }
        return expenses;
    }

    public static void main(String[] args) {
        List<Expense> expenses = Arrays.asList(
            new Expense("Groceries", 45),
            new Expense("Electricity Bill", 60),
            new Expense("Lunch", 12)
        );

        saveExpenses(expenses);
        loadExpenses().forEach(System.out::println);
    }
}

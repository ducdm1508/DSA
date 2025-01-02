package org.example.transactionmanager.Controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.transactionmanager.Entity.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DisplayController {

    @FXML private TableView<Transaction> transactionTableView;
    @FXML private TableColumn<Transaction, Integer> idColumn;
    @FXML private TableColumn<Transaction, String> descriptionColumn;
    @FXML private TableColumn<Transaction, Double> amountColumn;
    @FXML private TableColumn<Transaction, String> categoryColumn;
    @FXML private TableColumn<Transaction, LocalDate> dateColumn;
    @FXML private TableColumn<Transaction, String> typeColumn;
    @FXML private TextField search;

    private List<Transaction> allTransactions;
    private List<Transaction> filteredTransactions;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

        descriptionColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDescription()));

        amountColumn.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getAmount()).asObject());

        categoryColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCategory()));

        dateColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDate()));

        typeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getType().toString()));

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                filteredTransactions.clear();
                filteredTransactions.addAll(allTransactions);
                transactionTableView.setItems(FXCollections.observableArrayList(filteredTransactions));
            }
        });

        allTransactions = new ArrayList<>();
        filteredTransactions = new ArrayList<>();
        transactionTableView.setItems(FXCollections.observableArrayList(allTransactions));
    }

    public void setTransactions(List<Transaction> transactions) {
        this.allTransactions = transactions;
        this.filteredTransactions = new ArrayList<>(transactions);
        transactionTableView.setItems(FXCollections.observableArrayList(transactions));
    }

    public void handleSearch() {
        String searchText = search.getText().toLowerCase();


        if (searchText.isEmpty()) {
            filteredTransactions.clear();
            filteredTransactions.addAll(allTransactions);
            transactionTableView.setItems(FXCollections.observableArrayList(filteredTransactions));
        }

        filteredTransactions.clear();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (Transaction transaction : allTransactions) {
            boolean matches = false;

            if (transaction.getDescription().toLowerCase().contains(searchText) ||
                    transaction.getCategory().toLowerCase().contains(searchText) ||
                    String.valueOf(transaction.getAmount()).contains(searchText) ||
                    transaction.getType().toString().toLowerCase().contains(searchText)) {
                filteredTransactions.add(transaction);
                matches = true;
            }

            try {
                LocalDate searchDate = LocalDate.parse(searchText, dateFormatter);
                if (transaction.getDate().isEqual(searchDate)) {
                    matches = true;
                    filteredTransactions.add(transaction);
                }
            } catch (Exception e) {
            }
        }

        try {
            double amount = Double.parseDouble(searchText);
            int index = binarySearchByAmount(filteredTransactions, amount);
            if (index != -1) {
                filteredTransactions.clear();
                filteredTransactions.add(filteredTransactions.get(index));
            }
        } catch (NumberFormatException e) {
        }

        transactionTableView.setItems(FXCollections.observableArrayList(filteredTransactions));
    }

    private int binarySearchByAmount(List<Transaction> transactions, double targetAmount) {
        int left = 0;
        int right = transactions.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            double midAmount = transactions.get(mid).getAmount();

            if (midAmount == targetAmount) {
                return mid;
            } else if (midAmount < targetAmount) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public void sortByAmountAscending() {
        bubbleSortByAmount(filteredTransactions);
        transactionTableView.setItems(FXCollections.observableArrayList(filteredTransactions));
    }

    public void sortByAmountDescending() {
        bubbleSortByAmountDescending(filteredTransactions);
        transactionTableView.setItems(FXCollections.observableArrayList(filteredTransactions));
    }

    public void sortByDateNew() {
        selectionSortByDateNew(filteredTransactions);
        transactionTableView.setItems(FXCollections.observableArrayList(filteredTransactions));
    }

    public void sortByDateOld() {
        selectionSortByDateOld(filteredTransactions);
        transactionTableView.setItems(FXCollections.observableArrayList(filteredTransactions));
    }

    private void bubbleSortByAmount(List<Transaction> transactions) {
        int n = transactions.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).getAmount() > transactions.get(j + 1).getAmount()) {
                    Transaction temp = transactions.get(j);
                    transactions.set(j, transactions.get(j + 1));
                    transactions.set(j + 1, temp);
                }
            }
        }
    }

    private void bubbleSortByAmountDescending(List<Transaction> transactions) {
        int n = transactions.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (transactions.get(j).getAmount() < transactions.get(j + 1).getAmount()) {
                    Transaction temp = transactions.get(j);
                    transactions.set(j, transactions.get(j + 1));
                    transactions.set(j + 1, temp);
                }
            }
        }
    }

    private void selectionSortByDateNew(List<Transaction> transactions) {
        int n = transactions.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (transactions.get(j).getDate().isAfter(transactions.get(maxIndex).getDate())) {
                    maxIndex = j;
                }
            }
            Transaction temp = transactions.get(maxIndex);
            transactions.set(maxIndex, transactions.get(i));
            transactions.set(i, temp);
        }
    }

    private void selectionSortByDateOld(List<Transaction> transactions) {
        int n = transactions.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (transactions.get(j).getDate().isBefore(transactions.get(minIndex).getDate())) {
                    minIndex = j;
                }
            }
            Transaction temp = transactions.get(minIndex);
            transactions.set(minIndex, transactions.get(i));
            transactions.set(i, temp);
        }
    }
}
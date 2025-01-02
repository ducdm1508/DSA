package org.example.transactionmanager.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.example.transactionmanager.Model.BinaryTree;
import org.example.transactionmanager.Entity.Transaction;
import org.example.transactionmanager.Service.TransactionService;

import java.io.IOException;
import java.util.List;

public class MainController {

    @FXML
    private StackPane viewItem;

    private BinaryTree binaryTree = new BinaryTree();
    private TransactionService transactionService = new TransactionService(binaryTree);

    @FXML
    public void initialize() {
        loadItem("/org/example/transactionmanager/fxml/add_transaction.fxml");
    }

    @FXML
    public void handleAddItem() {
        loadItem("/org/example/transactionmanager/fxml/add_transaction.fxml");
    }

    @FXML
    public void handleDisplayItems() {
        loadItem("/org/example/transactionmanager/fxml/display_item.fxml");
    }

    @FXML
    public void handleShowChart() {
        loadItem("/org/example/transactionmanager/fxml/dashboard.fxml");
    }

    @FXML
    public void handleExit() {
        System.exit(0);
    }

    public void addTransaction(Transaction transaction) {
        transactionService.addTransaction(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }

    private void loadItem(String fileFxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileFxml));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof AddController) {
                ((AddController) controller).setMainController(this);
            } else if (controller instanceof DisplayController) {
                ((DisplayController) controller).setTransactions(getTransactions());
            } else if (controller instanceof DashboardController) {
                ((DashboardController) controller).setTransactionService(transactionService);
                ((DashboardController) controller).handleShowChart();
            }

            viewItem.getChildren().clear();
            viewItem.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
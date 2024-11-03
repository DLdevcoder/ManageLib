
package controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.BorrowRecord;
import utils.DatabaseConnection;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Objects;

public class BorrowRecordController extends HeaderController {
    @FXML
    private TableView<BorrowRecord> tableView;

    @FXML
    private TableColumn<BorrowRecord, Integer> recordIdColumn;
    @FXML
    private TableColumn<BorrowRecord, Integer> documentIdColumn;
    @FXML
    private TableColumn<BorrowRecord, Integer> memberIdColumn;
    @FXML
    private TableColumn<BorrowRecord, Date> borrowDateColumn;
    @FXML
    private TableColumn<BorrowRecord, Date> returnDateColumn;
    @FXML
    private TableColumn<BorrowRecord, Date> dueDateColumn;
    @FXML
    private TableColumn<BorrowRecord, String> statusColumn;
    @FXML
    private TableColumn<BorrowRecord, Integer> quantityColumn;

    private ObservableList<BorrowRecord> borrowRecordList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        recordIdColumn.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        documentIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadBorrowRecords();
    }

    private void loadBorrowRecords() {
        String query = "SELECT * FROM Borrow_Records";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BorrowRecord record = new BorrowRecord(
                        rs.getInt("record_id"),
                        rs.getInt("document_id"),
                        rs.getInt("member_id"),
                        rs.getDate("borrow_date"),
                        rs.getDate("return_date"),
                        rs.getDate("due_date"),
                        rs.getString("status"),
                        rs.getInt("quantity")
                );
                borrowRecordList.add(record);
            }

            tableView.setItems(borrowRecordList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sceneDataStatistics(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/views/borrow_records/DataStatistics.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            scene.getStylesheets().add(Paths.get("src/resources/DataStatistics.css").toUri().toString());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML file.");
        }
    }
}
package controllers;

import controllers.Document.Search.SearchController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HeaderController {
    protected Parent root;
    protected Stage stage;
    protected Scene scene;
    private Stage tableStage;

    @FXML
    private TextField SearchDoc_TextField;

    public void sceneBorrow(ActionEvent event) {
        changeScene(event, "/views/borrow_records/Borrow.fxml");
    }

    // Chuyển đến trang quản lý thành viên
    public void sceneMemberList(ActionEvent event) {
        changeScene(event, "/views/members/MemberList.fxml");
    }
      
    public void ScenceBookList(ActionEvent event) {
     changeScene(event, "/views/books/BookList.fxml");
    }

    public void sceneAdmin(ActionEvent event) {
        changeScene(event, "/views/admin/AdminInfo.fxml");
    }

    public void logout(ActionEvent event) {
        changeScene(event, "/views/login/Login.fxml");
    }

    public void changeScene(ActionEvent event, String path) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML file.");
        }
    }

    /**
     * Hiển thị một cửa sổ thông báo với tiêu đề và nội dung được chỉ định.
     * @param title Tiêu đề của cửa sổ thông báo.
     * @param message Nội dung của cửa sổ thông báo.
     */
    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void HomeScence(ActionEvent event) {
        changeScene(event, "/views/Home/Home.fxml");
    }

    /**
     * Initializes the event handler for the SearchDoc_TextField.
     * When the Enter key is pressed, it triggers the search process.
     */
    @FXML
    private void initialize() {
        if(SearchDoc_TextField !=null) {
            SearchDoc_TextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    if (keyEvent.getCode().toString().equals("ENTER")) {
                        String query = SearchDoc_TextField.getText();
                        openTableViewWindow(query);
                        SearchDoc_TextField.clear();


                    }
                }
            });

        }
    }

    /**
     * Mở một cửa sổ mới để hiển thị kết quả tìm kiếm tài liệu.
     * Tạo một cửa sổ mới và gọi phương thức tìm kiếm trong SearchController.
     *
     * @param query Từ khóa tìm kiếm được nhập vào TextField.
     */
    private void openTableViewWindow(String query){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DocSearch.fxml"));
            Parent root = loader.load();
            SearchController searchController = loader.getController();
            searchController.performSearch(query);
            tableStage = new Stage();
            tableStage.setTitle("Doc Search");
            tableStage.setScene(new Scene(root));
            tableStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void homeButton(ActionEvent event) {
        changeScene(event, "/views/Home/Home.fxml");
    }
}

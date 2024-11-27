package controllers.Home;

import controllers.HeaderController;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HomeController  {
    @FXML
    private PieChart Home_PieChart;
    @FXML
    private PieChart Category_PieChart;
    @FXML
    private void initialize() {
        loadPieChartData();
        loadCategoryPieChartData();
    }

    private void loadPieChartData(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        try(Connection connection = databaseConnection.getConnection()){
            String query = "SELECT language, COUNT(*) AS count FROM books GROUP BY language";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String language = resultSet.getString("language");
                int count = resultSet.getInt("count");
                if (language.equals("en")) {
                    language = "English";
                } else if (language.equals("ru")) {
                    language = "Russia";
                } else if (language.equals("spa")) {
                    language = "Spain";
                } else if (language.equals("vi")) {
                    language = "VietNam";
                } else if (language.equals("ja")) {
                    language = "Japan";
                } else {
                    language = "Others";
                }
                pieChartData.add(new PieChart.Data(language, count));

            }
            Home_PieChart.lookupAll(".chart-legend").forEach(node ->
                    node.setStyle("-fx-background-color: #E8F5E9;"));
            //System.out.println(pieChartData);
            Home_PieChart.setData(pieChartData);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    private void loadCategoryPieChartData() {

        // ObservableList để lưu trữ dữ liệu cho Category_PieChart
        ObservableList<PieChart.Data> categoryPieChartData = FXCollections.observableArrayList();

        // Truy vấn SQL để lấy dữ liệu cho Category_PieChart
        String query = """
        SELECT c.name AS category_name, COUNT(b.category_id) AS count
        FROM categories c
        LEFT JOIN books b ON c.category_id = b.category_id
        GROUP BY c.name
    """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String categoryName = resultSet.getString("category_name");
                int count = resultSet.getInt("count");

                // Thêm dữ liệu vào ObservableList
                categoryPieChartData.add(new PieChart.Data(categoryName, count));
            }
            Category_PieChart.lookupAll(".chart-legend").forEach(node ->
                    node.setStyle("-fx-background-color: #E8F5E9;"));
            // Gắn dữ liệu vào Category_PieChart
            Category_PieChart.setData(categoryPieChartData);

        } catch (Exception e) {
            e.printStackTrace(); // Ghi log lỗi (hoặc thay bằng Logger nếu cần)
        }
    }
}

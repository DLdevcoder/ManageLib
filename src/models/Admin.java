package models;

import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends Person {
    private int adminId; // id tự tăng nên k cần setter
    private String createDate; // tu dong lay ngay hien tai nen k can setter

    public Admin() {
    }

    public Admin(int adminId, String name, String address, String phone, String email, String createDate, String password) {
        super(name, address, phone, email, password);
        this.adminId = adminId;
        this.createDate = createDate;

    }

    public int getAdminId() {
        return adminId;
    }

    public String getCreateDate() {
        return this.createDate;
    }
    // admin
    public List<Admin> getAdmins() {
        String query = "SELECT * FROM admin";
        List<Admin> Admins = new ArrayList<>();
        // execute query
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int AdminId = rs.getInt("admin_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone_number");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String createDate = rs.getString("created_at");
                Admin Admin = new Admin(AdminId, name, address, phone, email, createDate, password);
                Admins.add(Admin);
            }
            //this.Admins = Admins;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return Admins;
    }
    public void addAdmin() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên thành viên: ");
        String name = sc.nextLine();

        System.out.print("Nhập địa chỉ: ");
        String address = sc.nextLine();

        System.out.print("Nhập số liên lạc: ");
        String phone = sc.nextLine();

        System.out.print("Nhập email: ");
        String email = sc.nextLine();

        System.out.print("Nhập mật khẩu: ");
        String password = sc.nextLine();

        try(Connection connection = DatabaseConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO admin (name, address, phone_number, email, password)  VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phone);
            statement.setString(4, email);
            statement.setString(5, password);
            int rowsAffected = statement.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Thành viên mới đã được thêm thành công!.");
            }
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                System.out.println("ID của thành viên mới: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeAdmin(int AdminId) throws SQLException {
        String query = "DELETE FROM admin WHERE Admin_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, AdminId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Admin removed successfully!");
            } else {
                System.out.println("Admin not found!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAdminInfo() {
        System.out.println("Admin ID: " + this.adminId);
        System.out.println("Name: " + this.name);
        System.out.println("Address: " + this.address);
        System.out.println("Phone: " + this.phone);
        System.out.println("Email: " + this.email);
        System.out.println("Create Date: " + this.createDate);
        System.out.println("Password: " + this.password);
    }

    public void updateAdmin(int adminId) {
        String query = "UPDATE admin SET name = ?, address = ?, phone_number = ?, email = ?, password = ? WHERE admin_id = ?";
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên thành viên: ");
        String name = sc.nextLine();

        System.out.print("Nhập địa chỉ: ");
        String address = sc.nextLine();

        System.out.print("Nhập số liên lạc: ");
        String phone = sc.nextLine();

        System.out.print("Nhập email: ");
        String email = sc.nextLine();

        System.out.print("Nhập mật khẩu: ");
        String password = sc.nextLine();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, phone);
            stmt.setString(4, email);
            stmt.setString(5, password);
            stmt.setInt(6, adminId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Admin updated successfully!");
            } else {
                System.out.println("Admin not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayAllAdmin() {
        List<Admin> Admins = this.getAdmins();
        for (Admin Admin : Admins) {
            System.out.println("Admin ID: " + Admin.getAdminId());
            System.out.println("Name: " + Admin.getName());
            System.out.println("Address: " + Admin.getAddress());
            System.out.println("Phone: " + Admin.getPhone());
            System.out.println("Email: " + Admin.getEmail());
            System.out.println("Create Date: " + Admin.getCreateDate());
            System.out.println("Password: " + Admin.getPassword());
            System.out.println();
        }

    }

    // member
    public List<Member> getMembers() {
        String query = "SELECT * FROM members";
        List<Member> members = new ArrayList<>();
        // execute query
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);) {
            while (rs.next()) {
                int memberId = rs.getInt("member_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone_number");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String membershipDate = rs.getString("membership_date");
                Member member = new Member(memberId, name, address, phone, email, membershipDate, password);
                members.add(member);
            }
            //this.members = members;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return members;
    }

    public void displayAllMember() {
        List<Member> members = this.getMembers();
        for (Member member : members) {
            System.out.println("Member ID: " + member.getMemberId());
            System.out.println("Name: " + member.getName());
            System.out.println("Address: " + member.getAddress());
            System.out.println("Phone: " + member.getPhone());
            System.out.println("Email: " + member.getEmail());
            System.out.println("Membership Date: " + member.getMembershipDate());
            System.out.println("Password: " + member.getPassword());
            System.out.println();
        }
    }


    public void addMember() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên thành viên: ");
        String name = sc.nextLine();

        System.out.print("Nhập địa chỉ: ");
        String address = sc.nextLine();

        System.out.print("Nhập số liên lạc: ");
        String phone = sc.nextLine();

        System.out.print("Nhập email: ");
        String email = sc.nextLine();

        System.out.print("Nhập mật khẩu: ");
        String password = sc.nextLine();

        try(Connection connection = DatabaseConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO members (name, address, phone_number, email, password)  VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, address);
            statement.setString(3, phone);
            statement.setString(4, email);
            statement.setString(5, password);
            int rowsAffected = statement.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Thành viên mới đã được thêm thành công!.");
            }
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                int id = resultSet.getInt(1);
                System.out.println("ID của thành viên mới: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeMember(int memberId) throws SQLException {
        String query = "DELETE FROM members WHERE member_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Member removed successfully!");
            } else {
                System.out.println("Member not found!");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMember(int memberId) {
        String query = "UPDATE members SET name = ?, address = ?, phone_number = ?, email = ?, password = ? WHERE member_id = ?";
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên thành viên: ");
        String name = sc.nextLine();

        System.out.print("Nhập địa chỉ: ");
        String address = sc.nextLine();

        System.out.print("Nhập số liên lạc: ");
        String phone = sc.nextLine();

        System.out.print("Nhập email: ");
        String email = sc.nextLine();

        System.out.print("Nhập mật khẩu: ");
        String password = sc.nextLine();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, address);
            stmt.setString(3, phone);
            stmt.setString(4, email);
            stmt.setString(5, password);
            stmt.setInt(6, memberId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Member updated successfully!");
            } else {
                System.out.println("Member not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // book


}
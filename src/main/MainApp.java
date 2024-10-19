package main;
import controllers.BorrowRecordController;
import controllers.MemberController;

import controllers.BookController;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
    public MainApp() {
    }

    public static void main(String[] args) throws SQLException {
        MemberController memberController = new MemberController();
        BookController bookController = new BookController();
        Scanner scanner = new Scanner(System.in);

        Connection connection = DatabaseConnection.getConnection();



        int choice = 0;
        int memberId;
        int bookId;

        do {
            System.out.println("Welcome to My Application!");
            System.out.println("[0] Exit");
            System.out.println("[1] Add Document");
            System.out.println("[2] Remove Document");
            System.out.println("[3] Update Document");
            System.out.println("[4] Find Document");
            System.out.println("[5] Display Document");
            System.out.println("[6] Add Member");
            System.out.println("[7] Borrow Document");
            System.out.println("[8] Return Document");
            System.out.println("[9] Display Member Info");
            System.out.println("[10] Remove Member");
            System.out.println("[11] Update Member");
            System.out.print("Please select an option: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                        System.out.println("Exiting application...");
                        break;
                    case 1:

                        BookController.addBook();
                        waitToRead(scanner);
                        break;
                    case 2:
                        System.out.println("Nhập id của tài liệu bạn muốn xóa: ");
                        bookId = scanner.nextInt();
                        BookController.removeBook(connection, bookId);







                        waitToRead(scanner);
                        break;
                    case 3:
                        updateDocument();
                        waitToRead(scanner);
                        break;
                    case 4:
                        findDocument();
                        waitToRead(scanner);
                        break;
                    case 5:
                        bookController.getBook();
                        System.out.print("Enter book ID: ");
                        bookId = scanner.nextInt();
                        bookController.displayDocument(bookId);
                        waitToRead(scanner);
                        break;
                    case 6:
                        memberController.addMember();
                        waitToRead(scanner);
                        break;
                    case 7:
                        BorrowRecordController.borrowDocument();
                        waitToRead(scanner);
                        break;
                    case 8:
                        BorrowRecordController.returnDocument();
                        waitToRead(scanner);
                        break;
                    case 9:
                        memberController.getMembers();
                        System.out.print("Enter member ID: ");
                        memberId = scanner.nextInt();
                        memberController.displayMemberInfo(memberId);
                        waitToRead(scanner);
                        break;
                    case 10:
                        System.out.print("Enter member ID: ");
                        memberId = scanner.nextInt();
                        memberController.removeMember(memberId);
                        waitToRead(scanner);
                        break;
                    case 11:
                        System.out.print("Enter member ID: ");
                        memberId = scanner.nextInt();
                        memberController.updateMember(memberId);
                        waitToRead(scanner);
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } else {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.next();
            }
        } while(choice != 0);

        scanner.close();
    }

<<<<<<< HEAD
=======
    public static void waitToRead(Scanner scanner) {
        while (true) {
            String checkEnter = scanner.nextLine();
            System.out.print("Press enter to back to menu");
            if (checkEnter.equals(scanner.nextLine())) break;
        }
    }

    public static void addBook() throws SQLException {
    }

    public static void removeBook() {

    }

>>>>>>> Long
    public static void updateDocument() {
    }

    public static void findDocument() {
    }

    public static void displayDocument() {
    }


}


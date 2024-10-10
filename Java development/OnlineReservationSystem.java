import java.util.*;

class User {
    String username;
    String password;
    String email;

    User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}

class Reservation {
    int reservationID;
    String userID;
    String date;
    String time;

    Reservation(int reservationID, String userID, String date, String time) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.date = date;
        this.time = time;
    }
}

class Admin {
    List<Reservation> reservations = new ArrayList<>();

    void viewReservations() {
        for (Reservation res : reservations) {
            System.out.println("Reservation ID: " + res.reservationID + ", User ID: " + res.userID + ", Date: " + res.date + ", Time: " + res.time);
        }
    }

    void manageReservation(int reservationID, String newDate, String newTime) {
        for (Reservation res : reservations) {
            if (res.reservationID == reservationID) {
                res.date = newDate;
                res.time = newTime;
                System.out.println("Reservation updated.");
                return;
            }
        }
        System.out.println("Reservation not found.");
    }
}

public class OnlineReservationSystem {
    static List<User> users = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();
    static Admin admin = new Admin();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Online Reservation System");

        // Example usage
        User user1 = new User("john_doe", "password123", "john@example.com");
        users.add(user1);

        Reservation res1 = new Reservation(1, "john_doe", "2024-09-28", "10:00 AM");
        reservations.add(res1);
        admin.reservations.add(res1);

        admin.viewReservations();
        admin.manageReservation(1, "2024-09-29", "11:00 AM");
        admin.viewReservations();
    }
}

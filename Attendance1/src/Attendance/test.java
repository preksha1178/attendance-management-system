package Attendance;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class test {

    public static void main(String[] args) {
        // Initialize the frame
        JFrame frame = new JFrame("Attendance System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Create UI components
        JLabel barcodeLabel = new JLabel("Scan ID:");
        barcodeLabel.setBounds(50, 50, 100, 30);
        frame.add(barcodeLabel);

        JTextField barcodeField = new JTextField();
        barcodeField.setBounds(150, 50, 200, 30);
        frame.add(barcodeField);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(50, 100, 300, 100);
        resultArea.setEditable(false);
        frame.add(resultArea);

        // Add an ActionListener to the barcodeField to trigger when the user presses "Enter"
        barcodeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String scannedId = barcodeField.getText();
                if (scannedId.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please scan an ID first.");
                    return;
                }

                try {
                    // Connect to the database
                    String url = "jdbc:mysql://localhost:3306/attendance"; // Update with your database name
                    String username = "root"; // Update with your MySQL username
                    String password = ""; // Update with your MySQL password
                    Connection conn = DriverManager.getConnection(url, username, password);

                    // Fetch student details from the 'students' table
                    String studentQuery = "SELECT * FROM students WHERE id = ?";
                    PreparedStatement studentStmt = conn.prepareStatement(studentQuery);
                    studentStmt.setString(1, scannedId);

                    ResultSet studentResult = studentStmt.executeQuery();
                    if (studentResult.next()) {
                        String studentName = studentResult.getString("name");
                        String course = studentResult.getString("class");

                        // Mark attendance with the current date and time
                        String date = LocalDate.now().toString();
                        String time = LocalTime.now().toString();

                        String attendanceQuery = "INSERT INTO attend (stid, dt, time) VALUES (?, ?, ?)";
                        PreparedStatement attendanceStmt = conn.prepareStatement(attendanceQuery);
                        attendanceStmt.setString(1, scannedId);
                        attendanceStmt.setString(2, date);
                        attendanceStmt.setString(3, time);

                        // Execute the query and update the attendance
                        int rowsInserted = attendanceStmt.executeUpdate();
                        if (rowsInserted > 0) {
                            resultArea.setText("Attendance marked for:\n");
                            resultArea.append("Name: " + studentName + "\n");
                            resultArea.append("Course: " + course + "\n");
                            resultArea.append("Date: " + date + "\n");
                            resultArea.append("Time: " + time + "\n");
                        }

                        attendanceStmt.close();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Student not found!");
                    }

                    studentStmt.close();
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });

        // Make frame visible
        frame.setVisible(true);
    }
  
}

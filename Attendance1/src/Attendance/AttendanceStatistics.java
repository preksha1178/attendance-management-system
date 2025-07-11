package Attendance;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class AttendanceStatistics {

    Connection con;

    // Main method to run the program
    public static void main(String[] args) {
        AttendanceStatistics statistics1 = new AttendanceStatistics();
        statistics1.statistics();
    }

    // Initialize the JFrame and UI components
    public void statistics() {
        connect();
        JFrame frame = new JFrame();
        Font text = new Font("Times New Roman", Font.PLAIN, 20);
        Font btn = new Font("Arial Narrow", Font.BOLD, 20);

        // Close Button
        JLabel x = new JLabel("");
        x.setHorizontalAlignment(SwingConstants.CENTER);
        x.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-cross-22.png"));
        x.setBounds(955, 0, 45, 35);
        frame.getContentPane().add(x);

        x.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                x.setOpaque(true);
                x.setBackground(new Color(255, 0, 0)); // Change to red on hover
                x.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                x.setOpaque(false);
                x.repaint();
            }
        });

        // Back Button
        JLabel back = new JLabel("");
        back.setHorizontalAlignment(SwingConstants.CENTER);
        back.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-back-23.png"));
        back.setForeground(new Color(255, 255, 255));
        back.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        back.setBounds(0, 0, 40, 35);
        frame.getContentPane().add(back);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                back.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-back-23.png"));
                back.setOpaque(true);
                back.setBackground(new Color(211, 211, 211));
                back.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-back-23.png"));
                back.setForeground(new Color(255, 255, 255));
                back.setOpaque(false);
                back.repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });

        // Panel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1000, 35);
        panel.setBackground(Color.decode("#457b9d"));
        frame.getContentPane().add(panel);

        // Select Class Label
        JLabel classLabel = new JLabel("Class:");
        classLabel.setFont(text);
        classLabel.setBounds(30, 80, 100, 30);
        frame.getContentPane().add(classLabel);

        // Class Dropdown
        JComboBox<String> classDropdown = new JComboBox<>(getClasses());
        classDropdown.setForeground(new Color(0, 0, 0));
        classDropdown.setBackground(Color.decode("#DEE4E7"));
        classDropdown.setEditable(true);
        classDropdown.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        classDropdown.setBounds(100, 80, 200, 30);
        frame.getContentPane().add(classDropdown);

        // Select Date Range
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        dateLabel.setBounds(30, 130, 100, 30);
        frame.getContentPane().add(dateLabel);

        JComboBox<String> dateDropdown = new JComboBox<>(getDates());
        dateDropdown.setEditable(true);
        dateDropdown.setForeground(new Color(0, 0, 0));
        dateDropdown.setBackground(Color.decode("#DEE4E7"));
        dateDropdown.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        dateDropdown.setBounds(100, 130, 200, 30);
        frame.getContentPane().add(dateDropdown);

        // Statistics Table
        JTable statsTable = new JTable();
        DefaultTableModel model = (DefaultTableModel) statsTable.getModel();
        model.addColumn("Student ID");
        model.addColumn("Name");
        model.addColumn("Present");
        model.addColumn("Absent");

        statsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        statsTable.setRowHeight(25);
        JScrollPane tableScrollPane = new JScrollPane(statsTable);
        tableScrollPane.setBounds(30, 200, 740, 300);
        frame.getContentPane().add(tableScrollPane);

        // Total Present Label
        JLabel presentLabel = new JLabel("Total Present: 0");
        presentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        presentLabel.setBounds(30, 520, 200, 30);
        frame.getContentPane().add(presentLabel);

        // Total Absent Label
        JLabel absentLabel = new JLabel("Total Absent: 0");
        absentLabel.setFont(new Font("Arial", Font.BOLD, 16));
        absentLabel.setBounds(250, 520, 200, 30);
        frame.getContentPane().add(absentLabel);

        // View Button
        JButton viewButton = new JButton("View Statistics");
        viewButton.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
        viewButton.setBackground(Color.decode("#457b9d"));
        viewButton.setForeground(Color.WHITE);
        viewButton.setBounds(338, 129, 200, 30);
        frame.getContentPane().add(viewButton);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClass = (String) classDropdown.getSelectedItem();
                String selectedDate = (String) dateDropdown.getSelectedItem();
                if (selectedClass != null && selectedDate != null) {
                    updateTable(model, selectedClass, selectedDate, presentLabel, absentLabel);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a class and date.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.getContentPane().setBackground(Color.decode("#f1faee"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Database Connection
    private void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/attendance";
            String user = "root";
            String password = "";
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Get Classes from Database
    private String[] getClasses() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT name FROM class", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();

            String[] classArray = new String[10]; // Assuming maximum 10 classes for now
            int index = 0;
            while (rs.next()) {
                classArray[index++] = rs.getString("name");
            }
            return classArray;

        } catch (SQLException e) {
            e.printStackTrace();
            return new String[]{}; // Return empty array if any error occurs
        }
    }

    // Get Dates for Dropdown
    private String[] getDates() {
        try {
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT dt FROM attend ORDER BY dt DESC");
            ResultSet rs = ps.executeQuery();

            String[] dateArray = new String[10]; // Assuming maximum 10 dates for now
            int index = 0;
            while (rs.next()) {
                dateArray[index++] = rs.getString("dt");
            }
            return dateArray;

        } catch (SQLException e) {
            e.printStackTrace();
            return new String[]{}; // Return empty array if any error occurs
        }
    }

    // Update Table based on selected class and date
    private void updateTable(DefaultTableModel model, String className, String date, JLabel presentLabel, JLabel absentLabel) {
        try {
            model.setRowCount(0); // Clear the table
            String query = """
                SELECT s.id, s.name,
                       SUM(CASE WHEN a.status = 'Present' THEN 1 ELSE 0 END) AS total_present,
                       SUM(CASE WHEN a.status = 'Absent' THEN 1 ELSE 0 END) AS total_absent
                FROM students s
                LEFT JOIN attend a ON s.id = a.stid
                WHERE a.class = ? AND a.dt = ?
                GROUP BY s.id, s.name
                ORDER BY total_present DESC
            """;

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, className);
            pstmt.setString(2, date);

            ResultSet rs = pstmt.executeQuery();

            int totalPresent = 0;
            int totalAbsent = 0;

            while (rs.next()) {
                int studentId = rs.getInt("id");
                String studentName = rs.getString("name");
                int totalPresentCount = rs.getInt("total_present");
                int totalAbsentCount = rs.getInt("total_absent");

                model.addRow(new Object[]{studentId, studentName, totalPresentCount, totalAbsentCount});

                // Counting total present and absent students
                totalPresent += totalPresentCount;
                totalAbsent += totalAbsentCount;
            }

            // Update the attendance counters
            presentLabel.setText("Total Present: " + totalPresent);
            absentLabel.setText("Total Absent: " + totalAbsent);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to fetch data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

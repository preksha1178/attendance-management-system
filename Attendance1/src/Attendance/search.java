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
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;;

public class search {
    Connection con;
    DefaultTableModel model = new DefaultTableModel();

    /**
     * @wbp.parser.entryPoint
     */
    public void editView1() throws SQLException {
        connect();
        JFrame frame = new JFrame();
        Font text = new Font("Times New Roman", Font.PLAIN, 20);
        Font btn = new Font("Arial Narrow", Font.BOLD, 20);

      //------------------------CLOSE---------------------------
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
		//----------------------------------------------------------


		//-----------------------BACK---------------------------------
		JLabel back = new JLabel("");
		back.setHorizontalAlignment(SwingConstants.CENTER);
		back.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-back-23.png"));
		back.setForeground(new Color(255, 255, 255));
		back.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		back.setBounds(0, 0, 40, 35);
		 // Make the label opaque so that the background color is visible
		frame.getContentPane().add(back);

		// Mouse listener to detect hover effect
		back.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseEntered(MouseEvent e) {
		        // Change icon, text color, and background color when mouse enters
		        back.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-back-23.png"));
		        back.setOpaque(true);  // Change text color on hover
		        back.setBackground(new Color(211, 211, 211));
		        back.repaint();
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        // Reset icon, text color, and background color when mouse exits
		        back.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-back-23.png"));
		        back.setForeground(new Color(255, 255, 255)); // Reset text color
		       back.setOpaque(false);
		       back.repaint();
		    }

		    @Override
		    public void mouseClicked(MouseEvent e) {
		        frame.dispose();
		    }
		});
		//--------------------------------------------------------------
		//------------------Panel----------------------------------
		JPanel panel = new  JPanel();
		panel.setBounds(0, 0, 1000, 35);
		panel.setBackground(Color.decode("#457b9d"));
		frame.getContentPane().add(panel);
		//---------------------------------------------------------

        //---------------------SEARCH PANEL----------------------
        JLabel searchLabel = new JLabel("SELECT : ");
        searchLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        searchLabel.setBounds(127, 55, 95, 25);
        frame.getContentPane().add(searchLabel);

        JComboBox<String> searchType = new JComboBox<>(new String[]{"Student", "Teacher", "Class", "User"});
        searchType.setBounds(216, 50, 197, 30);
        searchType.setFont(text);
        searchType.setForeground(new Color(0,0,0));
        searchType.setBackground(Color.decode("#DEE4E7"));
        searchType.setEditable(true);
        frame.getContentPane().add(searchType);

        JLabel criteriaLabel = new JLabel("SEARCH BY :");
        criteriaLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        criteriaLabel.setBounds(92, 108, 120, 25);
        frame.getContentPane().add(criteriaLabel);

        JTextField criteriaInput = new JTextField();
        criteriaInput.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        criteriaInput.setToolTipText("Enter ID or  Name ");
        criteriaInput.setBackground(Color.decode("#DEE4E7")); 
        criteriaInput.setBounds(216, 103, 197, 30);
        frame.getContentPane().add(criteriaInput);
     // Add click effect to text field
        criteriaInput.addMouseListener(new MouseAdapter() {
     						    @Override
     						    public void mouseClicked(MouseEvent e) {
     						    	criteriaInput.setBackground(Color.decode("#FFFFFF")); // Highlight color when clicked
     						    }

     						    @Override
     						    public void mouseExited(MouseEvent e) {
     						    	criteriaInput.setBackground(Color.decode("#DEE4E7")); // Reset color when mouse exits
     						    }
     						});
     						//------------------------------------------------------

        JButton searchButton = new JButton("");
        searchButton.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-search-25.png"));
        searchButton.setBounds(423, 102, 40, 31);
        searchButton.setFont(btn);
        searchButton.setBackground(new Color(255, 255, 255));
        searchButton.setForeground(Color.WHITE);
        frame.getContentPane().add(searchButton);

        //------------------------TABLE-------------------------
        JTable table = new JTable();
        model = (DefaultTableModel) table.getModel();
        table.setFont(new Font("Arial Narrow", Font.PLAIN, 17));
        table.setRowHeight(30);
        JScrollPane scPane = new JScrollPane(table);
        scPane.setBounds(30, 150, 900, 400);
        frame.getContentPane().add(scPane);

        //-------------------SEARCH BUTTON ACTION---------------
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = (String) searchType.getSelectedItem();
                String criteria = criteriaInput.getText().trim();
                if (criteria.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid search criteria!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    model.setRowCount(0); // Clear table before updating
                    switch (type) {
                        case "Student":
                            searchStudent(criteria);
                            break;
                        case "Teacher":
                            searchTeacher(criteria);
                            break;
                        case "Class":
                            searchClass(criteria);
                            break;
                        case "User":
                            searchUser(criteria);
                            break;
                        default:
                            JOptionPane.showMessageDialog(frame, "Invalid selection!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error fetching data. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            private void searchStudent(String criteria) throws SQLException {
                String query = "SELECT * FROM students WHERE id = ? OR name = ?";
                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                    pstmt.setString(1, criteria);
                    pstmt.setString(2, criteria);
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(frame, "No student found with given ID or USERNAME !", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    model.setColumnIdentifiers(new String[]{"ID", "Name", "Class"});
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("class")});
                    }
                }
            }

            private void searchTeacher(String criteria) throws SQLException {
                String query = "SELECT * FROM teachers WHERE id = ? OR name = ?";
                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                    pstmt.setString(1, criteria);
                    pstmt.setString(2, criteria);
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(frame, "No teacher found with given ID or USERNAME !", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    model.setColumnIdentifiers(new String[]{"ID", "Name"});
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getInt("id"), rs.getString("name")});
                    }
                }
            }

            private void searchClass(String criteria) throws SQLException {
                String query = "SELECT * FROM students WHERE class = ?";
                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                    pstmt.setString(1, criteria);
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(frame, "No students found for the given class !", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    model.setColumnIdentifiers(new String[]{"ID", "Name", "Class"});
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("class")});
                    }
                }
            }

            private void searchUser(String criteria) throws SQLException {
                String query = "SELECT id, name, username FROM user WHERE id = ? OR name = ?";
                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                    pstmt.setString(1, criteria);
                    pstmt.setString(2, criteria);
                    ResultSet rs = pstmt.executeQuery();
                    if (!rs.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(frame, "No user found with given ID or USERNAME !", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    model.setColumnIdentifiers(new String[]{"ID", "Name", "Username"});
                    while (rs.next()) {
                        model.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("username")});
                    }
                }
            }
        });

        //-------------------------FRAME-------------------------
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.decode("#f1faee"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/attendance";
        String user = "root";
        String pass = "";
        con = DriverManager.getConnection(url, user, pass);
    }
}

package Attendance;

import java.awt.Color;
import java.awt.Cursor;
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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
	


public class Login {
    int usr = 0;

    /**
     * @wbp.parser.entryPoint
     */
    public void loginView() {
        JFrame frame = new JFrame();
        Font text = new Font("Arial", Font.PLAIN, 20);
        Home hm = new Home();
        TeacherView tview = new TeacherView();
        StudentView sview = new StudentView();
        frame.getContentPane().setLayout(null);

        // -------------------------LOGO--------------------------
        JLabel attendance = new JLabel("ATTENDANCE");
        attendance.setBounds(100, 275, 400, 50);
        attendance.setForeground(Color.decode("#FFFFFF"));
        attendance.setFont(new Font("Verdana", Font.BOLD, 50));
		frame.getContentPane().add(attendance);
        //-------------------------------------------------------
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setLocation(707, 14);
        lblNewLabel.setSize(105, 100);
        frame.getContentPane().add(lblNewLabel);
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-user-100 (1).png"));

        JLabel management = new JLabel("MANAGEMENT SYSTEM");
        management.setBounds(214, 310, 325, 50);
        management.setForeground(Color.decode("#FFFFFF"));
        management.setFont(new Font("Verdana", Font.BOLD, 20));
        frame.getContentPane().add(management);

        // ------------------Panel----------------------------------
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 500, 600);
        panel.setBackground(Color.decode("#457b9d"));
        frame.getContentPane().add(panel);
        
        //---------------------------------------------------------

		//------------------------CLOSE---------------------------
		JLabel x = new JLabel("");
		x.setHorizontalAlignment(SwingConstants.CENTER);
		x.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-cross-26.png"));
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
        // --------------------------LOGINTEXT---------------------------
        JLabel lgn = new JLabel(" LOGIN");
        lgn.setBounds(692, 117, 150, 68);
        lgn.setBackground(Color.decode("#457b9d"));
        lgn.setForeground(Color.decode("#457b9d"));
        lgn.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 35));
        frame.getContentPane().add(lgn);
        //---------------------------------------------------------------

        // -------------------------USER--------------------------
        JLabel user = new JLabel("Username");
        user.setBounds(570, 250, 150, 20);
        user.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-username-20.png"));
        user.setForeground(Color.decode("#000000"));
        user.setFont(text);
        frame.getContentPane().add(user);
        //-------------------------------------------------------

        // -----------------------USERFIELD-----------------------
        JTextField username = new JTextField();
        username.setBounds(570, 285, 360, 35);
        username.setBackground(Color.decode("#ECEFF1"));
        username.setForeground(Color.decode("#37474F"));
        username.setFont(new Font("Arial", Font.PLAIN, 15));
        frame.getContentPane().add(username);
     // Add click effect to ID text field
     		username.addMouseListener(new MouseAdapter() {
     		    @Override
     		    public void mouseClicked(MouseEvent e) {
     		        username.setBackground(Color.decode("#FFFFFF")); // Highlight color when clicked
     		    }

     		    @Override
     		    public void mouseExited(MouseEvent e) {
     		        username.setBackground(Color.decode("#ECEFF1")); // Reset color when mouse exits
     		    }
     		});
        //---------------------------------------------------------

        // -------------------------PASSWORD--------------------------
        JLabel pass = new JLabel("Password");
        pass.setBounds(570, 352, 150, 20);
        pass.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-password-20.png"));
        pass.setForeground(Color.decode("#000000"));
        pass.setFont(text);
        frame.getContentPane().add(pass);
     // -----------------------PASSWORD FIELD WITH EYE ICON-----------------------
        JPanel passwordPanel = new JPanel(null); // Use null layout for precise positioning
        passwordPanel.setBounds(570, 384, 396, 35); // Position the panel
        passwordPanel.setBackground(Color.decode("#ECEFF1")); // Background matches the password field
        frame.getContentPane().add(passwordPanel);

        // Add the eye icon
        JLabel eyeIcon = new JLabel();
        eyeIcon.setBounds(366, 6, 20, 20); // Position the eye icon
        eyeIcon.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-eye-20 (1).png")); // Your normal eye icon path
        eyeIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        passwordPanel.add(eyeIcon);
        
                // Create the password field
                JPasswordField passwordField = new JPasswordField();
                passwordField.setBounds(0, 0, 359, 35);
                passwordPanel.add(passwordField);
                passwordField.setBackground(Color.decode("#ECEFF1"));
                passwordField.setForeground(Color.decode("#37474F"));
                passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
                passwordField.setEchoChar('\u2022');
             // Add click effect to ID text field
                passwordField.addMouseListener(new MouseAdapter() {
         		    @Override
         		    public void mouseClicked(MouseEvent e) {
         		    	passwordField.setBackground(Color.decode("#FFFFFF")); // Highlight color when clicked
         		    }

         		    @Override
         		    public void mouseExited(MouseEvent e) {
         		    	passwordField.setBackground(Color.decode("#ECEFF1")); // Reset color when mouse exits
         		    }
         		});

        // Toggle password visibility when the eye icon is clicked
        eyeIcon.addMouseListener(new MouseAdapter() {
            boolean isVisible = false;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isVisible) {
                    passwordField.setEchoChar('\u2022'); // Default echo character (dot)
                    eyeIcon.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-eye-20 (1).png")); // Normal eye icon
                } else {
                    passwordField.setEchoChar((char) 0); // No echo character (visible text)
                    eyeIcon.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-eye-20.png")); // Eye-open icon
                }
                isVisible = !isVisible;
            }
        });

        // -------------------------FORGOT PASSWORD----------------------
        JLabel forgotPassword = new JLabel("Forgot Password?");
        forgotPassword.setBounds(816, 423, 150, 20);
        forgotPassword.setForeground(Color.decode("#007BFF"));
        forgotPassword.setFont(new Font("Arial", Font.ITALIC, 14));
        forgotPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        frame.getContentPane().add(forgotPassword);
        forgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                forgotPassword.setForeground(Color.decode("#1d3557")); // Darker blue
            }

            @Override
            public void mouseExited(MouseEvent e) {
                forgotPassword.setForeground(Color.decode("#007BFF")); // Original blue
            }
        });


        forgotPassword.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Redirecting to reset password...", "Forgot Password", JOptionPane.INFORMATION_MESSAGE);
                // Implement password reset functionality here
            }
        });
        //---------------------------------------------------------

        // -----------------------REMEMBER ME-----------------------
        JCheckBox rememberMe = new JCheckBox("Remember Me");
        rememberMe.setBounds(565, 423, 150, 20);
        rememberMe.setBackground(Color.decode("#f1faee"));
        rememberMe.setFont(new Font("Arial", Font.PLAIN, 14));
        rememberMe.setForeground(Color.decode("#000000"));
        frame.getContentPane().add(rememberMe);
        //---------------------------------------------------------
     // Pre-fill fields with 'Remember Me' data if available
        try {
            String[] credentials = loadRememberMe();
            if (credentials != null) {
                username.setText(credentials[0]);
                passwordField.setText(credentials[1]);
                rememberMe.setSelected(true);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // ----------------------LOGIN BUTTON----------------------------
     // ----------------------LOGIN BUTTON WITH HOVER EFFECT----------------------------
        JButton login = new JButton("LOGIN");
        login.setBounds(570, 500, 360, 50);
        login.setFont(new Font("Arial", Font.BOLD, 20));
        login.setBackground(Color.decode("#457b9d"));
        login.setForeground(Color.decode("#FFFFFF"));
        login.setBorderPainted(false);
        login.setFocusPainted(false);
        login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        frame.getContentPane().add(login);

        // Add hover effect to the login button
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setBackground(Color.decode("#6abef2")); // Darker shade for hover
                login.setForeground(Color.decode("#f1faee")); // Lighten text color
                login.setBorder(BorderFactory.createLineBorder(Color.decode("#f1faee"), 2));
                login.setFont(new Font("Arial", Font.BOLD, 23));// Add border
            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setBackground(Color.decode("#457b9d")); // Revert to original background
                login.setForeground(Color.decode("#FFFFFF")); // Revert to original text color
                login.setBorder(null); // Remove border
                login.setFont(new Font("Arial", Font.BOLD, 20));// Add border

            }
        });

        login.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rememberMe.isSelected()) {
                    try {
                        saveRememberMe(username.getText(), new String(passwordField.getPassword()));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

                try {
                    int res = dbCheck(username.getText(), passwordField.getText());
                    if (res == 0) {
                        JOptionPane.showMessageDialog(frame, "No User Found!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (res == -1) {
                        JOptionPane.showMessageDialog(frame, "Invalid Password!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (res == 1)
                            hm.homeView(usr);
                        else if (res == 2)
                            tview.tcView(usr);
                        else if (res == 3)
                            sview.stView(usr);
                        frame.dispose();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });


        //----------------------------------------------------------

        // Set up frame properties
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.getContentPane().setBackground(Color.decode("#f1faee"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Database check method
    public int dbCheck(String name, String password) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/attendance";
        String user = "root";
        String pass = "";
        String str = "SELECT * FROM user WHERE username = '" + name + "'";
        Connection con = DriverManager.getConnection(url, user, pass);
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery(str);
        if (rst.next()) {
            if (rst.getString("password").equals(password)) {
                usr = rst.getInt("id");
                return rst.getInt("prio");
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }
    public void saveRememberMe(String username, String password) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/attendance";
        String user = "root";
        String pass = "";

        Connection con = DriverManager.getConnection(url, user, pass);

        // Clear existing active credentials
        String clearQuery = "UPDATE remember_me SET is_active = FALSE";
        PreparedStatement clearStmt = con.prepareStatement(clearQuery);
        clearStmt.executeUpdate();

        // Save new credentials
        String saveQuery = "INSERT INTO remember_me (username, password, is_active) VALUES (?, ?, TRUE)";
        PreparedStatement saveStmt = con.prepareStatement(saveQuery);
        saveStmt.setString(1, username);
        saveStmt.setString(2, password);
        saveStmt.executeUpdate();

        con.close();
    }

    public String[] loadRememberMe() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/attendance";
        String user = "root";
        String pass = "";

        Connection con = DriverManager.getConnection(url, user, pass);

        String query = "SELECT username, password FROM remember_me WHERE is_active = TRUE";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        String[] credentials = null;
        if (rs.next()) {
            credentials = new String[]{rs.getString("username"), rs.getString("password")};
        }

        con.close();
        return credentials;
    }
}


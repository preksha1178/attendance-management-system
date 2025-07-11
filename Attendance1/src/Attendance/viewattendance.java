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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;;

public class viewattendance {
	Connection con;
	DefaultTableModel model = new DefaultTableModel();
	/**
	 * @wbp.parser.entryPoint
	 */
	public void viewView() throws SQLException {
		connect();
		JFrame frame = new JFrame();
		Font text = new Font("Arial Narrow", Font.BOLD, 20);
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
		//-------------------------DATE-------------------------
				JLabel dt = new JLabel("DATE : ");
				dt.setBackground(new Color(0, 0, 0));
				dt.setFont(new Font("Arial Narrow", Font.BOLD, 20));
				dt.setBounds(63, 64, 75, 20);
				dt.setForeground(new Color(0, 0, 0));
				frame.getContentPane().add(dt);
				JTextField dtbox= new JTextField();
				dtbox.setEditable(false);
				dtbox.setBounds(148, 61, 201, 25);
				dtbox.setBackground(Color.decode("#DEE4E7"));
				dtbox.setFont(new Font("Arial Narrow", Font.BOLD, 20));
				dtbox.setForeground(Color.decode("#37474F"));
				String dateInString =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				dtbox.setText(dateInString);
				frame.getContentPane().add(dtbox);
				//-------------------------------------------------------
		
				//----------------TABLE---------------------------------
				@SuppressWarnings("serial")
				JTable table = new JTable() {
				    @Override
				    public boolean isCellEditable(int row, int column) {
				        return false; // Make the table non-editable
				    }
				};

				// Update the table model to include an additional "Time" column
				model = (DefaultTableModel) table.getModel();
				model.addColumn("ID");
				model.addColumn("NAME");
				model.addColumn("CLASS");
				model.addColumn("STATUS");
				model.addColumn("TIME"); // New column for attendance time

				// Adjust column widths for better visibility
				table.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
				table.getColumnModel().getColumn(1).setPreferredWidth(200); // Name
				table.getColumnModel().getColumn(2).setPreferredWidth(100); // Class
				table.getColumnModel().getColumn(3).setPreferredWidth(100); // Status
				table.getColumnModel().getColumn(4).setPreferredWidth(150); // Time

				// Set table font and row height
				table.setFont(new Font("Arial Narrow", Font.PLAIN, 17));
				table.setRowHeight(50);


				// Add the table to a scroll pane
				JScrollPane scPane = new JScrollPane(table);
				scPane.setBounds(500, 50, 480, 525);
				frame.getContentPane().add(scPane);
				scheduleMarkingAbsentees();
				//------------------------------------------------------

		// Create UI components
        JLabel barcodeLabel = new JLabel("Scan ID:");
        barcodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        barcodeLabel.setBounds(63, 131, 76, 30);
        frame.getContentPane().add(barcodeLabel);
        

        JTextField barcodeField = new JTextField();
        barcodeField.setBounds(149, 135, 200, 26);
        barcodeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        barcodeField.setBackground(Color.decode("#DEE4E7")); 
        frame.getContentPane().add(barcodeField);
     // Add click effect to ID text field
        barcodeField.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	barcodeField.setBackground(Color.decode("#FFFFFF")); // Highlight color when clicked
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	barcodeField.setBackground(Color.decode("#DEE4E7")); // Reset color when mouse exits
		    }
		});

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(63, 259, 370, 246);
        resultArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
        resultArea.setEditable(false);
        frame.getContentPane().add(resultArea);
        barcodeField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                processBarcode();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                processBarcode();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                processBarcode();
            }
            private void processBarcode() {
                String scannedId = barcodeField.getText().trim();
                if (scannedId.isEmpty()) {
                    resultArea.setText("\n\n\n\n                    Scan ID First !"); // Clear the text area when ID is removed
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
                        String studentClass = studentResult.getString("class");

                        // Mark attendance with the current date and time
                        String date = LocalDate.now().toString();
                        String time =LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
                        String status = "Present"; // Default status

                        // Check if attendance is already marked for the scanned ID on the same date
                        String checkQuery = "SELECT * FROM attend WHERE stid = ? AND dt = ?";
                        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                        checkStmt.setString(1, scannedId);
                        checkStmt.setString(2, date);
                        ResultSet checkResult = checkStmt.executeQuery();

                        if (checkResult.next()) {
                            String currentStatus = checkResult.getString("status");

                            if ("Absent".equalsIgnoreCase(currentStatus)) {
                                // Update the attendance status to "Present"
                                String updateQuery = "UPDATE attend SET status = ?, time = ? WHERE stid = ? AND dt = ?";
                                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                                updateStmt.setString(1, "Present"); // New status
                                updateStmt.setString(2, time);     // Update time
                                updateStmt.setString(3, scannedId);
                                updateStmt.setString(4, date);

                                int rowsUpdated = updateStmt.executeUpdate();
                                if (rowsUpdated > 0) {
                                    JOptionPane.showMessageDialog(frame, "Status updated to 'Present' for ID: " + scannedId);
                                }
                                updateStmt.close();
                            } else {
                                JOptionPane.showMessageDialog(frame, "Attendance already marked as 'Present' for ID: " + scannedId);
                            }
                        } else {
                            // Insert attendance record into the database
                            String insertQuery = "INSERT INTO attend (stid, dt, time, class, status) VALUES (?, ?, ?, ?, ?)";
                            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                            insertStmt.setString(1, scannedId);
                            insertStmt.setString(2, date);
                            insertStmt.setString(3, time);
                            insertStmt.setString(4, studentClass);
                            insertStmt.setString(5, "Present");

                            insertStmt.executeUpdate();
                            insertStmt.close();

                            model.addRow(new Object[]{scannedId, studentName, studentClass, "Present", time});
                            JOptionPane.showMessageDialog(frame, "Attendance marked successfully for ID: " + scannedId);
                        }


                        // Update the text area with student details
                        resultArea.setText("\n   ID: " + scannedId + "\n" +
                                "\n   Name: " + studentName + "\n" +
                                "   Class: " + studentClass + "\n" +
                                "   Status: " + status + "\n" +
                                "\n   Date: " + date + "\n" );

                        checkStmt.close();
                    } else {
                       // resultArea.setText("Student not found!"); // Show error message in the text area
                    }

                    studentStmt.close();
                    conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }

        });


	    //-------------------------------------------------------
		
		//-------------------------------------------------------
		frame.setSize(1000,600);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.getContentPane().setBackground(Color.decode("#f1faee"));
		
		JLabel lblNewLabel = new JLabel("Student's Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 25));
		lblNewLabel.setBounds(63, 223, 286, 25);
		lblNewLabel.setForeground(Color.decode("#1d3557"));
		frame.getContentPane().add(lblNewLabel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lblNewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	lblNewLabel.setBackground(Color.decode("#45789D")); 
            	lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 27));
            	// Darker shade for hover
            	lblNewLabel.setForeground(Color.decode("#45789D")); // Lighten text color
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	lblNewLabel.setBackground(Color.decode("#1d3557")); // Revert to original background
            	lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 25));
            	lblNewLabel.setForeground(Color.decode("#1d3557")); // Revert to original text color
            }
        });
		//--------------------------------------------------------------
	}
	
	public void connect() throws SQLException {
		//ENTER PORT, USER, PASSWORD.
		String url = "jdbc:mysql://localhost:3306/attendance";
		String user = "root";
		String pass = "";
		con = DriverManager.getConnection(url, user, pass);
	}
	 public void markAbsentees() {
	        String date = LocalDate.now().toString();

	        try {
	            String studentQuery = "SELECT id, class FROM students";
	            Statement stmt = con.createStatement();
	            ResultSet students = stmt.executeQuery(studentQuery);

	            while (students.next()) {
	                String studentId = students.getString("id");
	                String studentClass = students.getString("class");

	                String checkQuery = "SELECT * FROM attend WHERE stid = ? AND dt = ?";
	                PreparedStatement checkStmt = con.prepareStatement(checkQuery);
	                checkStmt.setString(1, studentId);
	                checkStmt.setString(2, date);
	                ResultSet checkResult = checkStmt.executeQuery();

	                if (!checkResult.next()) {
	                    String markAbsentQuery = "INSERT INTO attend (stid, dt, time, class, status) VALUES (?, ?, ?, ?, ?)";
	                    PreparedStatement absentStmt = con.prepareStatement(markAbsentQuery);
	                    absentStmt.setString(1, studentId);
	                    absentStmt.setString(2, date);
	                    absentStmt.setString(3, "00:00");
	                    absentStmt.setString(4, studentClass);
	                    absentStmt.setString(5, "Absent");

	                    absentStmt.executeUpdate();
	                    absentStmt.close();
	                }
	                checkStmt.close();
	            }

	            students.close();
	            stmt.close();
	            System.out.println("Absentees marked successfully!");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Schedule absentee marking at the end of the day.
	     */
	 public void scheduleMarkingAbsentees() {
		    Thread schedulerThread = new Thread(() -> {
		        while (true) {
		            try {
		                LocalTime now = LocalTime.now();
		                LocalTime markingTime = LocalTime.of(14, 0); // 2:00 PM

		                // Get current date and time
		                LocalDateTime nowDateTime = LocalDateTime.of(LocalDate.now(), now);
		                LocalDateTime nextMarkingDateTime = LocalDateTime.of(LocalDate.now(), markingTime);

		                // If it's already past 2:00 PM today, set the next marking for tomorrow
		                if (now.isAfter(markingTime)) {
		                    nextMarkingDateTime = nextMarkingDateTime.plusDays(1);
		                }

		                // Calculate the time difference (in milliseconds) until the next 2:00 PM
		                long sleepTime = Duration.between(nowDateTime, nextMarkingDateTime).toMillis();
		                System.out.println("Next marking scheduled in: " + sleepTime / 1000 + " seconds.");

		                // Sleep until the next marking time
		                Thread.sleep(sleepTime);

		                // Mark absentees at the scheduled time
		                System.out.println("2 PM has passed. Marking absentees now...");
		                markAbsentees();

		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		    });
		    schedulerThread.setDaemon(true);
		    schedulerThread.start();
		}




	
	public ResultSet dbSearch(String classes) throws SQLException {
		String str1 = "SELECT * from students where class = '"+classes+"'";
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str1);
		return rst;
	}
	
	public String[] classEt() throws SQLException {
		String str1 = "SELECT name from class";
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str1);
		String[] rt = new String[25];
		int i=0;
		while(rst.next()) {
			rt[i] = rst.getString("name");
			i++;
		}
		return rt;
	}
	
	public void tblupdt(String classes) {
		for (int i=0; i < model.getRowCount(); i++) {
		    model.removeRow(i);
		}
		try {
			ResultSet res = dbSearch(classes);
			for(int i=0; res.next(); i++) {
				model.addRow(new Object[0]);
				model.setValueAt(res.getInt("id"), i, 0);
		        model.setValueAt(res.getString("name"), i, 1);
		        model.setValueAt("Present", i, 2);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void addItem(int id, String status, String date, String classes) throws SQLException {
		String adding = "INSERT INTO attend values("+id+", '"+date+"', '"+status+"', '"+classes+"')";
		Statement stm = con.createStatement();
        stm.executeUpdate(adding);
	}
	
	public boolean check(String classes, String dt) throws SQLException {
		String str1 = "select * from attend where class = '"+classes+"' AND dt = '"+dt+"'";
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str1);
		if(rst.next())
			return true;
		else 
			return false;
	}
	public static void main(String[] args) {
	    try {
	        viewattendance attendance = new viewattendance();
	        attendance.connect(); 
	        attendance.viewView();// Ensure database connection is established
	        attendance.markAbsentees(); // Manually trigger absentee marking
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
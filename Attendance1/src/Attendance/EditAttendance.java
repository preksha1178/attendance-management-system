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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class EditAttendance {
	
	Connection con;
	DefaultTableModel model = new DefaultTableModel();
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void editView() throws SQLException {
		
		connect();
		JFrame frame = new JFrame();
		Font text =new Font("Times New Roman", Font.PLAIN, 20);
		Font btn =new Font("Arial Narrow", Font.BOLD, 20);
		
		
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
		
		//----------------TABLE---------------------------------
		@SuppressWarnings("serial")
		JTable table=new JTable(){
			public boolean isCellEditable(int row,int column){
				if(column == 3)
					return true;
				else
					return false;
			}
		};
		model = (DefaultTableModel)table.getModel();
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("STATUS");
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.setFont(new Font("Arial Narrow", Font.PLAIN, 17));
		table.setRowHeight(30);
		JScrollPane scPane=new JScrollPane(table);
		scPane.setBounds(500, 50, 480, 525);
		frame.getContentPane().add(scPane);
		//------------------------------------------------------
		
		//-------------------------DATE-------------------------
		JLabel dt = new JLabel("DATE : ");
		dt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dt.setBounds(25, 62, 75, 20);
		dt.setForeground(Color.BLACK);
		frame.getContentPane().add(dt);
		JTextField dtbox= new JTextField();
		dtbox.setBounds(100, 60, 150, 25);
		dtbox.setBackground(Color.decode("#DEE4E7"));
		dtbox.setFont(text);
		dtbox.setForeground(Color.decode("#37474F"));
		String dateInString =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		dtbox.setText(dateInString);
		frame.getContentPane().add(dtbox);
		//-------------------------------------------------------
		
		//--------------------CLASS---------------------------------
		JLabel classes = new JLabel("CLASS : ");
		classes.setFont(text);
		classes.setBounds(23, 150, 100, 20);
		classes.setForeground(Color.BLACK);
		frame.getContentPane().add(classes);
		@SuppressWarnings("unchecked")
		JComboBox clss= new JComboBox(classEt());
		clss.setFont(new Font("Arial Narrow", Font.PLAIN, 18));
		clss.setEditable(true);
		clss.setBounds(102, 149, 148, 25);
		frame.getContentPane().add(clss);
		//------------------------------------------------------------
		
		//----------------------VIEWBUTTON-----------------------
		JButton view = new JButton("VIEW");
		view.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-view-25.png"));
		view.setBounds(37, 373, 394, 60);
		view.setFont(btn);
		view.setBackground(Color.decode("#457b9d"));
		view.setForeground(Color.WHITE);
		frame.getContentPane().add(view);
		view.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tblupdt(String.valueOf(clss.getSelectedItem()), dtbox.getText());
			}
		});
		//-------------------------------------------------------
		
		//----------------------ABSENTBUTTON-----------------------
		JButton ab = new JButton("ABSENT");
		ab.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-close-window-25.png"));
		ab.setBounds(239, 444, 192, 60);
		ab.setFont(btn);
		ab.setBackground(Color.decode("#457b9d"));
		ab.setForeground(Color.WHITE);
		frame.getContentPane().add(ab);
		ab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				table.setValueAt("Absent", table.getSelectedRow(), 2);
			}
		});
		//-------------------------------------------------------
		
		//----------------------PRESENTBUTTON-----------------------
		JButton pre = new JButton("PRESENT");
		pre.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-check-25.png"));
		pre.setBounds(37, 444, 192, 60);
		pre.setFont(btn);
		pre.setBackground(Color.decode("#457b9d"));
		pre.setForeground(Color.WHITE);
		frame.getContentPane().add(pre);
		pre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(table.getSelectedRow());
				table.setValueAt("Present", table.getSelectedRow(), 2);
			}
		});
		//-------------------------------------------------------
		
		 //----------------------SUBMIT BUTTON-----------------------
	    JButton sbmt = new JButton("SUBMIT");
	    sbmt.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-save-25.png"));
	    sbmt.setBounds(37, 515, 397, 60);
	    sbmt.setFont(btn);
	    sbmt.setBackground(Color.decode("#457b9d"));
	    sbmt.setForeground(Color.WHITE);
	    frame.getContentPane().add(sbmt);
	    sbmt.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            int confirm = JOptionPane.showConfirmDialog(frame, 
	                "Are you sure you want to submit the attendance?", 
	                "Confirmation", 
	                
	                JOptionPane.YES_NO_OPTION);
	            
	            if (confirm == JOptionPane.YES_OPTION) {
	                try {
	                    for (int i = 0; i < table.getRowCount(); i++) {
	                        int id = Integer.parseInt(String.valueOf(table.getValueAt(i, 0)));
	                        String status = String.valueOf(table.getValueAt(i, 2));
	                        String date = dtbox.getText();
	                        editItem(id, status, date);
	                    }
	                    JOptionPane.showMessageDialog(frame, "Attendance Submitted Successfully!");
	                    model.setRowCount(0); // Clears the table
	                } catch (NumberFormatException | SQLException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(frame, 
	                        "An error occurred while submitting attendance. Please try again.", 
	                        "Error", 
	                        JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        }
	    });
	  //----------------------DELETEBUTTON-----------------------
	    
	    JButton del1 = new JButton("DELETE ALL");
	    del1.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-delete-25 (1).png"));
	    del1.setBounds(37, 312, 213, 50);
	    del1.setFont(new Font("Arial Narrow", Font.BOLD, 20));
	    del1.setBackground(Color.decode("#457b9d"));
	    del1.setForeground(Color.WHITE);
	    frame.getContentPane().add(del1);

	    del1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Show confirmation dialog
	            int confirm = JOptionPane.showConfirmDialog(frame, 
	                "Are you sure you want to delete this attendance record?", 
	                "Delete Confirmation", 
	                JOptionPane.YES_NO_OPTION);
	            
	            // If user confirms, proceed with deletion
	            if (confirm == JOptionPane.YES_OPTION) {
	                String query = "DELETE FROM attend WHERE class = ? AND dt = ?";
	                try (PreparedStatement pstmt = con.prepareStatement(query)) {
	                    // Set parameters for the prepared statement
	                    pstmt.setString(1, String.valueOf(clss.getSelectedItem())); // Set the class parameter
	                    pstmt.setString(2, dtbox.getText()); // Set the date parameter

	                    // Execute delete query
	                    int rowsAffected = pstmt.executeUpdate();

	                    // If rows were deleted, clear the table
	                    if (rowsAffected > 0) {
	                        // Clear the table after deletion
	                        model.setRowCount(0); // Clears the table content

	                        JOptionPane.showMessageDialog(frame, "Attendance records deleted successfully.");
	                    } else {
	                        JOptionPane.showMessageDialog(frame, 
	                            "No matching attendance records found to delete.", 
	                            "No Records", 
	                            JOptionPane.INFORMATION_MESSAGE);
	                    }
	                } catch (SQLException ex) {
	                    // Handle errors during the delete operation
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(frame, 
	                        "An error occurred while deleting records.", 
	                        "Error", 
	                        JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        }
	    });

		
		//----------------------DELETEBUTTON-----------------------
	    JButton del = new JButton("DELETE");
	    del.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-delete-25 (1).png"));
	    del.setBounds(260, 312, 171, 50);
	    del.setFont(new Font("Arial Narrow", Font.BOLD, 20));
	    del.setBackground(Color.decode("#457b9d"));
	    del.setForeground(Color.WHITE);
	    frame.getContentPane().add(del);

	    del.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Get selected row
	            int selectedRow = table.getSelectedRow();
	            
	            // Check if a row is selected
	            if (selectedRow != -1) {
	                // Get the ID of the selected student (assuming ID is in the first column of the table)
	                int studentId = (int) table.getValueAt(selectedRow, 0);  // Get the ID from the first column
	                String date = dtbox.getText();
	                String className = String.valueOf(clss.getSelectedItem());

	                // Show confirmation dialog
	                int confirm = JOptionPane.showConfirmDialog(frame, 
	                    "Are you sure you want to delete this attendance record for student ID " + studentId + "?", 
	                    "Delete Confirmation", 
	                    JOptionPane.YES_NO_OPTION);
	                
	                // If user confirms, proceed with deletion
	                if (confirm == JOptionPane.YES_OPTION) {
	                    String query = "DELETE FROM attend WHERE stid = ? AND class = ? AND dt = ?";
	                    try (PreparedStatement pstmt = con.prepareStatement(query)) {
	                        // Set parameters for the prepared statement
	                        pstmt.setInt(1, studentId);  // Set the student ID
	                        pstmt.setString(2, className); // Set the class
	                        pstmt.setString(3, date); // Set the date

	                        // Execute delete query
	                        int rowsAffected = pstmt.executeUpdate();

	                        // If a row was deleted, update the table
	                        if (rowsAffected > 0) {
	                            // Remove the row from the table view
	                            model.removeRow(selectedRow); // Remove the selected row from the table
	                            JOptionPane.showMessageDialog(frame, "Attendance record deleted successfully.");
	                        } else {
	                            JOptionPane.showMessageDialog(frame, 
	                                "No matching attendance record found for deletion.", 
	                                "Error", 
	                                JOptionPane.INFORMATION_MESSAGE);
	                        }
	                    } catch (SQLException ex) {
	                        // Handle errors during the delete operation
	                        ex.printStackTrace();
	                        JOptionPane.showMessageDialog(frame, 
	                            "An error occurred while deleting the record.", 
	                            "Error", 
	                            JOptionPane.ERROR_MESSAGE);
	                    }
	                }
	            } else {
	                JOptionPane.showMessageDialog(frame, 
	                    "Please select a record to delete.", 
	                    "No Selection", 
	                    JOptionPane.WARNING_MESSAGE);
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 // Buttons
	    JButton[] buttons = {view, ab, pre, sbmt, del, del1};
	    for (JButton button : buttons) {
	        addHoverEffect(button);
	    }

	    // Other components
	    
		//--------------------------------------------------------------
	}
	// Hover effect colors (centralized for easy changes)
	Color hoverColor = Color.decode("#2e5168"); // Cornflower Blue for hover
	Color defaultButtonColor = Color.decode("#457b9d"); // Default button color
	Color defaultButtonTextColor = Color.decode("#FFFFFF"); // Default button text color
	Color hoverButtonTextColor = Color.decode("#FFFFFF"); // Hover button text color

	// Add hover effect for a button
	private void addHoverEffect(JButton button) {
	    button.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            button.setBackground(hoverColor);
	            button.setForeground(hoverButtonTextColor);
	            button.setFont(new Font("Times New Roman", Font.PLAIN, 22));
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	            button.setBackground(defaultButtonColor);
	            button.setForeground(defaultButtonTextColor);
	            button.setFont(new Font("Arial Narrow", Font.BOLD, 20));
	        }
	    });
	}
	public void connect() throws SQLException {
		//ENTER PORT, USER, PASSWORD.
		String url = "jdbc:mysql://localhost:3306/attendance";
		String user = "root";
		String pass = "";
		con = DriverManager.getConnection(url, user, pass);
	}
	
	public ResultSet dbSearch(String classes, String dt) throws SQLException {
		//ENTER PORT, USER, PASSWORD.
		String str1 = "SELECT * from attend, students where attend.stid=students.id AND attend.class = '"+classes+"' AND attend.dt = '"+dt+"'";
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
	
	public void tblupdt(String classes, String dt) {
		try {
			for (int i=0; i < model.getRowCount(); i++) {
			    model.removeRow(i);
			    model.setRowCount(0);
			}
			ResultSet res = dbSearch(classes, dt);
			for(int i=0; res.next(); i++) {
				model.addRow(new Object[0]);
				model.setValueAt(res.getInt("stid"), i, 0);
		        model.setValueAt(res.getString("name"), i, 1);
		        model.setValueAt(res.getString("status"), i, 2);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void editItem(int id, String status, String date) throws SQLException {
		String adding = "UPDATE attend SET status = '"+status+"' WHERE stid = "+id+" AND dt = '"+date+"'";
		Statement stm = con.createStatement();
        stm.executeUpdate(adding);
	}
	public static void main(String[] args) {
        Teachers teachersApp = new Teachers();
        try {
            teachersApp.teachersView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
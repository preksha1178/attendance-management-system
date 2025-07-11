package Attendance;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
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
import java.sql.PreparedStatement;
import java.time.LocalTime; // For getting the current time
 // If using java.util.Date objects


public class AddAttendance {
	Connection con;
	DefaultTableModel model = new DefaultTableModel();
	/**
	 * @wbp.parser.entryPoint
	 */
	public void addView() throws SQLException {
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
		
		//----------------TABLE---------------------------------
		@SuppressWarnings("serial")
		JTable table=new JTable(){
			public boolean isCellEditable(int row,int column){
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
		table.setRowHeight(50);
		JScrollPane scPane=new JScrollPane(table);
		scPane.setBounds(500, 50, 480, 525);
		frame.getContentPane().add(scPane);
		//------------------------------------------------------
		
		//-------------------------DATE-------------------------
		JLabel dt = new JLabel("DATE : ");
		dt.setBackground(new Color(0, 0, 0));
		dt.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		dt.setBounds(25, 60, 75, 20);
		dt.setForeground(new Color(0, 0, 0));
		frame.getContentPane().add(dt);
		JTextField dtbox= new JTextField();
		dtbox.setBounds(100, 60, 150, 25);
		dtbox.setBackground(Color.decode("#DEE4E7"));
		dtbox.setFont(new Font("Arial Narrow", Font.BOLD, 20));
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
		clss.setBounds(102, 149, 111, 25);
		frame.getContentPane().add(clss);
		//------------------------------------------------------------
		
		//----------------------VIEWBUTTON-----------------------
		JButton view = new JButton("");
		view.setToolTipText("VIEW");
		view.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-view-25.png"));
		view.setBounds(359, 426, 75, 50);
		view.setFont(btn);
		view.setBackground(new Color(255, 255, 255));
		view.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(view);
		view.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
//		        try {
//		            if (check(String.valueOf(clss.getSelectedItem()), dtbox.getText())) {
//		                // Show a dialog box with the message in large font
//		                JLabel message = new JLabel("Attendance Already Marked !");
//		                message.setFont(new Font("Arial Narrow", Font.BOLD, 15)); // Set a large font
//		                message.setForeground(Color.red);
//		                JOptionPane.showMessageDialog(frame, message, "Info", JOptionPane.WARNING_MESSAGE);
//		            } else {
		                tblupdt(String.valueOf(clss.getSelectedItem()));
//		            }
//		        } catch (SQLException e1) {
//		            e1.printStackTrace();
//		        }
		    }
		});

		//-------------------------------------------------------
		
		//----------------------ABSENTBUTTON-----------------------
		JButton ab = new JButton("ABSENT");
		ab.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-close-window-25.png"));
		ab.setBounds(37, 498, 289, 50);
		ab.setFont(btn);
		ab.setBackground(new Color(255, 255, 255));
		ab.setForeground(new Color(0, 0, 0));
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
		pre.setBounds(37, 426, 288, 50);
		pre.setFont(btn);
		pre.setBackground(new Color(255, 255, 255));
		pre.setForeground(new Color(0, 0, 0));
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
	    JButton sbmt = new JButton("");
	    sbmt.setToolTipText("SUBMIT");
	    sbmt.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-save-25.png"));
	    sbmt.setBounds(359, 498, 75, 50);
	    sbmt.setFont(btn);
	    sbmt.setBackground(new Color(255, 255, 255));
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
	                        String classes = String.valueOf(clss.getSelectedItem());
	                        addItem(id, status, date, classes);
	                    }
	                    JOptionPane.showMessageDialog(frame, "Attendance Submitted Successfully!");
	                   
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
		//--------------------------------------------------------------
		 // Buttons
	    JButton[] buttons = {view, ab, pre, sbmt};
	    for (JButton button : buttons) {
	        addHoverEffect(button);
	    }

	    // Other components
		//--------------------------------------------------------------
	}
	// Hover effect colors (centralized for easy changes)
	Color hoverColor = Color.decode("#DEE4E7"); // Cornflower Blue for hover
	Color defaultButtonColor = Color.decode("#FFFFFF"); // Default button color
	Color defaultButtonTextColor = Color.BLACK; // Default button text color
	Color hoverButtonTextColor = Color.BLACK; // Hover button text color


	// Add hover effect for a button
	private void addHoverEffect(JButton button) {
	    button.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseEntered(MouseEvent e) {
	            button.setBackground(hoverColor);
	            button.setForeground(hoverButtonTextColor);
	            button.setFont(new Font("Arial Narrow", Font.PLAIN, 22));
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
	    // SQL query with placeholders for parameters
	    String adding = "INSERT INTO attend (stid, dt, status, class, time) VALUES (?, ?, ?, ?, ?)";
	    
	    // Create a PreparedStatement object
	    PreparedStatement pstmt = con.prepareStatement(adding);
	    
	    // Set the parameters in the correct order
	    pstmt.setInt(1, id); // First placeholder is for 'stid'
	    pstmt.setString(2, date); // Second placeholder is for 'dt'
	    pstmt.setString(3, status); // Third placeholder is for 'status'
	    pstmt.setString(4, classes); // Fourth placeholder is for 'class'
	    pstmt.setTime(5, java.sql.Time.valueOf(java.time.LocalTime.now())); // Fifth placeholder is for 'time'

	    // Execute the update
	    pstmt.executeUpdate();
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
}
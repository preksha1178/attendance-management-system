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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class StudentView {
	
	Connection con;
	JFrame frame = new JFrame();
	DefaultTableModel model = new DefaultTableModel();
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void stView(int id) throws SQLException {
		
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

						
						
				//-----------------------MINIMIZE-----------------------------
				JLabel min = new JLabel("");
				min.setHorizontalAlignment(SwingConstants.CENTER);
				min.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-minimize-18 (1).png"));
				min.setForeground(new Color(255, 255, 255));
				min.setBounds(910, 0, 45, 35);
				frame.getContentPane().add(min);

				min.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				        frame.setState(JFrame.ICONIFIED); // Minimize the frame
				    }

				    @Override
				    public void mouseEntered(MouseEvent e) {
				        min.setOpaque(true); // Enable background color change
				        min.setBackground(new Color(211, 211, 211)); // Set light gray on hover
				        min.repaint(); // Refresh to apply background change
				    }

				    @Override
				    public void mouseExited(MouseEvent e) {
				        min.setOpaque(false); // Revert to transparent background
				        min.repaint(); // Refresh to remove background color
				    }
				});
				//-------------------------------------------------------------

				
				//------------------Panel----------------------------------
				JPanel panel = new  JPanel();
				panel.setBounds(0, 0, 1000, 35);
				panel.setBackground(Color.decode("#457b9d"));
				frame.getContentPane().add(panel);
						//---------------------------------------------------------
						
				//-------------------Welcome---------------------------------
				JLabel welcome = new JLabel("Welcome "+getUser(id)+",");
				welcome.setBackground(Color.LIGHT_GRAY);
				welcome.setForeground(Color.decode("#1d3557"));
				welcome.setBounds(10, 40, 400, 35);
				welcome.setFont(new Font("Times New Roman", Font.PLAIN, 25));
				frame.getContentPane().add(welcome);
				// Add hover effect to the button
				welcome.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseEntered(MouseEvent e) {
			            	welcome.setBackground(Color.decode("#45789D")); 
			            	welcome.setFont(new Font("Times New Roman", Font.ITALIC, 27));
			            	// Darker shade for hover
			            	welcome.setForeground(Color.decode("#45789D")); // Lighten text color
			            }

			            @Override
			            public void mouseExited(MouseEvent e) {
			            	welcome.setBackground(Color.decode("#1d3557")); // Revert to original background
			        		welcome.setFont(new Font("Times New Roman", Font.PLAIN, 25));
			            	welcome.setForeground(Color.decode("#1d3557")); // Revert to original text color
			            }
			        });

				
		
		//----------------TABLE---------------------------------
		JTable table=new JTable();
		model = (DefaultTableModel)table.getModel();
		model.addColumn("DATE");
		model.addColumn("STATUS");
		JScrollPane scPane=new JScrollPane(table);
		scPane.setBounds(441, 50, 480, 525);
		table.setFont(new Font("Times New Roman", Font.BOLD, 20));
		table.setRowHeight(50);
		frame.getContentPane().add(scPane);
		//------------------------------------------------------
		
		//--------------------------INFO------------------------
		JLabel totalclass = new JLabel("TOTAL CLASSES : ");
		totalclass.setBounds(25, 148, 182, 35);
		totalclass.setForeground(new Color(0, 0, 0));
		totalclass.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.getContentPane().add(totalclass);
		// Add hover effect to the button
		totalclass.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	            	totalclass.setBackground(Color.decode("#45789D")); 
	            	totalclass.setFont(new Font("Times New Roman", Font.ITALIC, 21));
	            	// Darker shade for hover
	            	totalclass.setForeground(Color.decode("#45789D")); // Lighten text color
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	totalclass.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	            	totalclass.setForeground(new Color(0, 0, 0)); // Revert to original text color
	            }
	        });
		JLabel ttbox = new JLabel("");
		ttbox.setHorizontalAlignment(SwingConstants.CENTER);
		ttbox.setBounds(267, 148, 45, 27);
		ttbox.setForeground(new Color(0, 0, 0));
		ttbox.setFont(new Font("Times New Roman", Font.BOLD, 21));
		frame.getContentPane().add(ttbox);
		// Add hover effect to the button
		ttbox.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseEntered(MouseEvent e) {
			            	ttbox.setBackground(Color.decode("#45789D")); 
			            	ttbox.setFont(new Font("Times New Roman", Font.ITALIC, 21));
			            	// Darker shade for hover
			            	ttbox.setForeground(Color.decode("#45789D")); // Lighten text color
			            }

			            @Override
			            public void mouseExited(MouseEvent e) {
			            	ttbox.setFont(new Font("Times New Roman", Font.BOLD, 21));
			            	ttbox.setForeground(new Color(0, 0, 0)); // Revert to original text color
			            }
			        });
		JLabel classAtt = new JLabel("CLASSES ATTENDED : ");
		classAtt.setBounds(25, 236, 217, 35);
		classAtt.setForeground(new Color(0, 0, 0));
		classAtt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.getContentPane().add(classAtt);
		// Add hover effect to the button
		classAtt.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseEntered(MouseEvent e) {
			            	classAtt.setBackground(Color.decode("#45789D")); 
			            	classAtt.setFont(new Font("Times New Roman", Font.ITALIC, 21));
			            	// Darker shade for hover
			            	classAtt.setForeground(Color.decode("#45789D")); // Lighten text color
			            }

			            @Override
			            public void mouseExited(MouseEvent e) {
			            	classAtt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			            	classAtt.setForeground(new Color(0, 0, 0)); // Revert to original text color
			            }
			        });
		JLabel atbox = new JLabel("");
		atbox.setHorizontalAlignment(SwingConstants.CENTER);
		atbox.setBounds(267, 236, 45, 27);
		atbox.setForeground(new Color(0, 0, 0));
		atbox.setFont(new Font("Times New Roman", Font.BOLD, 21));
		frame.getContentPane().add(atbox);
		atbox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	atbox.setBackground(Color.decode("#45789D")); 
            	atbox.setFont(new Font("Times New Roman", Font.ITALIC, 21));
            	// Darker shade for hover
            	atbox.setForeground(Color.decode("#45789D")); // Lighten text color
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	atbox.setFont(new Font("Times New Roman", Font.BOLD, 21));
            	atbox.setForeground(new Color(0, 0, 0)); // Revert to original text color
            }
        });
		JLabel classAbs = new JLabel("CLASSES MISSED : ");
		classAbs.setBounds(25, 333, 182, 35);
		classAbs.setForeground(new Color(0, 0, 0));
		classAbs.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.getContentPane().add(classAbs);
		// Add hover effect to the button
		classAbs.addMouseListener(new MouseAdapter() {
					            @Override
					            public void mouseEntered(MouseEvent e) {
					            	classAbs.setBackground(Color.decode("#45789D")); 
					            	classAbs.setFont(new Font("Times New Roman", Font.ITALIC, 21));
					            	// Darker shade for hover
					            	classAbs.setForeground(Color.decode("#45789D")); // Lighten text color
					            }

					            @Override
					            public void mouseExited(MouseEvent e) {
					            	classAbs.setFont(new Font("Times New Roman", Font.PLAIN, 20));
					            	classAbs.setForeground(new Color(0, 0, 0)); // Revert to original text color
					            }
					        });
		JLabel mtbox = new JLabel("");
		mtbox.setHorizontalAlignment(SwingConstants.CENTER);
		mtbox.setBounds(267, 333, 45, 27);
		mtbox.setForeground(new Color(0, 0, 0));
		mtbox.setFont(new Font("Times New Roman", Font.BOLD, 21));
		frame.getContentPane().add(mtbox);
		mtbox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	mtbox.setBackground(Color.decode("#45789D")); 
            	mtbox.setFont(new Font("Times New Roman", Font.ITALIC, 21));
            	// Darker shade for hover
            	mtbox.setForeground(Color.decode("#45789D")); // Lighten text color
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	mtbox.setFont(new Font("Times New Roman", Font.BOLD, 21));
            	mtbox.setForeground(new Color(0, 0, 0)); // Revert to original text color
            }
        });
		JLabel AttPer = new JLabel("ATTENDANCE PERCENTAGE : ");
		AttPer.setBackground(new Color(0, 0, 0));
		AttPer.setBounds(25, 480, 299, 35);
		AttPer.setForeground(new Color(0, 0, 0));
		AttPer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.getContentPane().add(AttPer);
		// Add hover effect to the button
		AttPer.addMouseListener(new MouseAdapter() {
							            @Override
							            public void mouseEntered(MouseEvent e) {
							            	AttPer.setBackground(Color.decode("#45789D")); 
							            	AttPer.setFont(new Font("Times New Roman", Font.ITALIC, 21));
							            	// Darker shade for hover
							            	AttPer.setForeground(Color.decode("#45789D")); // Lighten text color
							            }

							            @Override
							            public void mouseExited(MouseEvent e) {
							            	AttPer.setFont(new Font("Times New Roman", Font.BOLD, 20));
							            	AttPer.setForeground(new Color(0, 0, 0)); // Revert to original text color
							            }
							        });
		JLabel prbox = new JLabel("");
		prbox.setHorizontalAlignment(SwingConstants.CENTER);
		prbox.setBounds(321, 481, 62, 31);
		prbox.setForeground(new Color(0, 0, 0));
		prbox.setFont(new Font("Times New Roman", Font.BOLD, 22));
		frame.getContentPane().add(prbox);
		//------------------------------------------------------
		
//		//----------------------SETVALUES---------------------------
		int[] arr = stat(id);
		ttbox.setText(String.valueOf(arr[0]));
		atbox.setText(String.valueOf(arr[1]));
		mtbox.setText(String.valueOf(arr[2]));
		prbox.setText(String.valueOf(arr[3])+"%");
//		//----------------------------------------------------------
//		//-------------------------------------------------------
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
				// Add Logout Button
				JButton logout = new JButton("");
				logout.setBorderPainted(false);
				logout.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-logout-30.png"));
				logout.setBounds(933, 35, 67, 40);
				frame.getContentPane().add(logout);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(Color.WHITE);
				panel_1.setBounds(0, 34, 1038, 40);
				frame.getContentPane().add(panel_1);
		        // Add hover effect to the button
				logout.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseEntered(MouseEvent e) {
			            	logout.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-logout-35.png"));
			            }

			            @Override
			            public void mouseExited(MouseEvent e) {
			            	logout.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-logout-30.png"));
			        		

			            }
			        });
				
						// Add ActionListener for Logout
						logout.addActionListener(new ActionListener() {
						    @Override
						    public void actionPerformed(ActionEvent e) {
						        frame.dispose(); // Close the current window
						        // Optionally, navigate back to the login screen
						        Login login = new Login(); // Assuming you have a Login class for the login screen
						        login.loginView();  // Call method to show the login screen
						    }
						});
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//--------------------------------------------------------------
	}
	
	public String getUser(int id) throws SQLException {
		//ENTER PORT, USER, PASSWORD.
		String url = "jdbc:mysql://localhost:3306/attendance";
		String user = "root";
		String pass = "";
		con = DriverManager.getConnection(url, user, pass);
		String str = "SELECT name FROM user WHERE id = "+id;
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str);
		rst.next();
		return rst.getString("name");
	}
	
	public void tblupdt(int id) {
		try {
			ResultSet res = dbSearch(id);
			for(int i=0; res.next(); i++) {
				model.addRow(new Object[0]);
				model.setValueAt(res.getString("dt"), i, 0);
		        model.setValueAt(res.getString("status"), i, 1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public int[] stat(int id) throws SQLException {
	    String str = "SELECT COUNT(*) AS pre FROM attend WHERE stid = "+id+" AND status = 'Present'";
	    String str2 = "SELECT COUNT(*) AS abs FROM attend WHERE stid = "+id+" AND status = 'Absent'";
	    int[] x = new int[4];
	    Statement stm = con.createStatement();
	    
	    // Get Present Count
	    ResultSet rst = stm.executeQuery(str);
	    
	    rst.next();
	    JOptionPane.showMessageDialog(null, rst.getString("pre"));
	    x[1] = rst.getInt("pre");
	    
	    // Get Absent Count
	    rst = stm.executeQuery(str2);
	    rst.next();
	    x[2] = rst.getInt("abs");
	    
	    // Calculate Total Classes
	    x[0] = x[1] + x[2];
	    
	    // Calculate Attendance Percentage
	    if (x[0] == 0) {
	        x[3] = 0;  // Default to 0% if no classes
	    } else {
	        x[3] = (x[1] * 100) / x[0];
	    }

	    // Update the table
	    tblupdt(id);
	    
	    return x;
	}

	
	public ResultSet dbSearch(int id) throws SQLException {
		String str1 = "SELECT * from attend where stid = "+id+" ORDER BY dt desc";
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str1);
		return rst;
	}
}
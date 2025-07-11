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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TeacherView {
	/**
	 * @wbp.parser.entryPoint
	 */
	public void tcView(int id) throws SQLException {
		
		JFrame frame = new JFrame();
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
				welcome.setBounds(10, 40,400, 35);
				welcome.setFont(new Font("Times New Roman", Font.PLAIN, 25));
				frame.getContentPane().add(welcome);
				// Add hover effect to the button
				welcome.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseEntered(MouseEvent e) {
			            	welcome.setBackground(Color.decode("#45789D")); 
			            	welcome.setFont(new Font("Times New Roman", Font.PLAIN, 27));
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

				
		
				//----------------------ADDATTENDANCE----------------------------
				JButton addattendance = new JButton(" ADD ATTENDANCE");
				addattendance.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-add-48 (1).png"));
				addattendance.setBounds(376, 264, 407, 67);
				addattendance.setFont(new Font("Times New Roman", Font.BOLD, 25));
				addattendance.setBackground(Color.decode("#457b9d"));
				addattendance.setForeground(Color.WHITE);
				frame.getContentPane().add(addattendance);
				 addattendance.setBorderPainted(false);
			     addattendance.setFocusPainted(false);
			     // Add hover effect to the button
			     addattendance.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseEntered(MouseEvent e) {
			            	addattendance.setBackground(Color.decode("#2e5168")); // Darker shade for hover
			            	addattendance.setForeground(Color.decode("#FFFFFF")); // Lighten text color
			            	addattendance.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			            	addattendance.setBorder(BorderFactory.createLineBorder(Color.decode("#FFFFFF"), 2)); // Add border
			            }

			            @Override
			            public void mouseExited(MouseEvent e) {
			            	addattendance.setBackground(Color.decode("#457b9d")); // Revert to original background
			            	addattendance.setForeground(Color.decode("#FFFFFF"));
			            	addattendance.setFont(new Font("Times New Roman", Font.BOLD, 25));// Revert to original text color
			            	addattendance.setBorder(null); // Remove border
			            }
			        });
				addattendance.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						AddAttendance addatt = new AddAttendance();
						try {
							addatt.addView();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});

				//----------------------VIEWATTENDANCE----------------------------
						JButton viewattendance = new JButton(" VIEW ATTENDANCE");
						viewattendance.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-view-40.png"));
						viewattendance.setBounds(376, 181, 407, 72);
						viewattendance.setFont(new Font("Times New Roman", Font.BOLD, 25));
						viewattendance.setBackground(Color.decode("#457b9d"));
						viewattendance.setForeground(Color.WHITE);
						frame.getContentPane().add(viewattendance);
						viewattendance.setBorderPainted(false);
						viewattendance.setFocusPainted(false);
					     // Add hover effect to the button
					     viewattendance.addMouseListener(new MouseAdapter() {
					            @Override
					            public void mouseEntered(MouseEvent e) {
					            	viewattendance.setBackground(Color.decode("#2e5168")); // Darker shade for hover
					            	viewattendance.setForeground(Color.decode("#FFFFFF")); // Lighten text color
					            	viewattendance.setFont(new Font("Times New Roman", Font.PLAIN, 30));
					            	viewattendance.setBorder(BorderFactory.createLineBorder(Color.decode("#FFFFFF"), 2)); // Add border
					            }

					            @Override
					            public void mouseExited(MouseEvent e) {
					            	viewattendance.setBackground(Color.decode("#457b9d")); // Revert to original background
					            	viewattendance.setForeground(Color.decode("#FFFFFF"));
					            	viewattendance.setFont(new Font("Times New Roman", Font.BOLD, 25));// Revert to original text color
					            	viewattendance.setBorder(null); // Remove border
					            }
					        });
					     viewattendance.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								AttendanceStatistics addat = new AttendanceStatistics();
									addat.statistics();
								}
					     }
							);
								//----------------------------------------------------------

				//----------------------EDITATTENDANCE----------------------------
				JButton editattendance = new JButton(" EDIT ATTENDANCE");
				editattendance.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-edit-40 (2).png"));
				editattendance.setBounds(376, 342, 407, 67);
				editattendance.setFont(new Font("Times New Roman", Font.BOLD, 25));
				editattendance.setBackground(Color.decode("#457b9d"));
				editattendance.setForeground(Color.WHITE);
				frame.getContentPane().add(editattendance);
				 editattendance.setBorderPainted(false);
			     editattendance.setFocusPainted(false);
			     // Add hover effect to the  button
			     editattendance.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseEntered(MouseEvent e) {
			            	editattendance.setBackground(Color.decode("#2e5168")); // Darker shade for hover
			            	editattendance.setForeground(Color.decode("#FFFFFF")); // Lighten text color
			            	editattendance.setBorder(BorderFactory.createLineBorder(Color.decode("#FFFFFF"), 2));
			        		editattendance.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		// Add border
			            }

			            @Override
			            public void mouseExited(MouseEvent e) {
			            	editattendance.setBackground(Color.decode("#457b9d")); // Revert to original background
			            	editattendance.setForeground(Color.decode("#FFFFFF")); // Revert to original text color
			            	editattendance.setBorder(null); // Remove border
			        		editattendance.setFont(new Font("Times New Roman", Font.BOLD, 25));

			            }
			        });
				editattendance.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						EditAttendance editatt = new EditAttendance();
						try {
							editatt.editView();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				//----------------------------------------------------------
				
		
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
		Connection con = DriverManager.getConnection(url, user, pass);
		String str = "SELECT name FROM user WHERE id = "+id;
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str);
		rst.next();
		return rst.getString("name");
	}
}
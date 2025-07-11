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
public class Students {
	DefaultTableModel model = new DefaultTableModel();
	Connection con;
	int check;
	JButton edit;
	JButton delete;
	JButton add;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void studentView() throws SQLException {
		
		Font text = new Font("Arial Narrow", Font.BOLD, 20);
		Font btn = new Font("Arial Narrow", Font.BOLD, 20);

		JFrame frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		
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
		model.addColumn("USERNAME");
		model.addColumn("NAME");
		tblupdt();
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.setFont(new Font("Arial Narrow", Font.PLAIN, 17));
		table.setRowHeight(30);
		JScrollPane scPane=new JScrollPane(table);
		scPane.setBounds(500, 50, 480, 525);
		frame.getContentPane().add(scPane);
		//------------------------------------------------------
		
		//--------------------ID-----------------------------------
		JLabel id = new JLabel("ID : ");
		id.setFont(text);
		id.setBounds(25, 62, 40, 20);
		id.setForeground(Color.BLACK);
		frame.getContentPane().add(id);
		JTextField idbox= new JTextField();
		idbox.setEditable(false);
		idbox.setBounds(97, 59, 134, 25);
		idbox.setBackground(Color.decode("#DEE4E7"));
		idbox.setFont(text);
		idbox.setForeground(Color.decode("#37474F"));
		idbox.setText(String.valueOf(getid()));
		frame.getContentPane().add(idbox);
		// Add click effect to text field
		idbox.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	idbox.setBackground(Color.decode("#FFFFFF")); // Highlight color when clicked
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		    	idbox.setBackground(Color.decode("#DEE4E7")); // Reset color when mouse exits
		    }
		});
		//------------------------------------------------------
		//--------------------------------------------------------
		
		//--------------------CLASS---------------------------------
		JLabel classes = new JLabel("CLASS : ");
		classes.setFont(text);
		classes.setBounds(25, 131, 100, 20);
		classes.setForeground(Color.BLACK);
		frame.getContentPane().add(classes);
		@SuppressWarnings("unchecked")
		JComboBox clss= new JComboBox(classEt());
		clss.setEditable(true);
		clss.setBackground(new Color(222, 228, 231));
		clss.setForeground(Color.decode("#37474F"));
		clss.setFont(new Font("Arial Narrow", Font.PLAIN, 18));
		clss.setBounds(97, 129, 134, 25);
		frame.getContentPane().add(clss);
		// Add click effect to text field
		clss.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	clss.setBackground(Color.decode("#FFFFFF")); // Highlight color when clicked
				    }

				    @Override
				    public void mouseExited(MouseEvent e) {
				    	clss.setBackground(Color.decode("#DEE4E7")); // Reset color when mouse exits
				    }
				});
				//------------------------------------------------------
		//------------------------------------------------------------
		
		//---------------------USERNAME-------------------------
		JLabel user = new JLabel("USERNAME : ");
		user.setBackground(Color.BLACK);
		user.setFont(text);
		user.setBounds(25, 205, 150, 20);
		user.setForeground(Color.BLACK);
		frame.getContentPane().add(user);
		JTextField username= new JTextField();
		username.setBounds(25, 236, 400, 35);
		username.setBackground(new Color(222, 228, 231));
		username.setFont(text);
		username.setForeground(Color.decode("#37474F"));
		username.setEditable(false);
		frame.getContentPane().add(username);
		// Add click effect to text field
		username.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	username.setBackground(Color.decode("#FFFFFF")); // Highlight color when clicked
				    }

				    @Override
				    public void mouseExited(MouseEvent e) {
				    	username.setBackground(Color.decode("#DEE4E7")); // Reset color when mouse exits
				    }
				});
				//------------------------------------------------------
		//------------------------------------------------------
		
		//-------------------NAME----------------------------------
		JLabel nm = new JLabel("NAME : ");
		nm.setFont(text);
		nm.setBounds(25, 307, 150, 20);
		nm.setForeground(Color.BLACK);
		frame.getContentPane().add(nm);
		JTextField name= new JTextField();
		name.setBounds(25, 338, 400, 35);
		name.setBackground(Color.decode("#DEE4E7"));
		name.setFont(text);
		name.setForeground(Color.decode("#37474F"));
		name.setEditable(false);
		frame.getContentPane().add(name);
		// Add click effect to text field
		name.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	name.setBackground(Color.decode("#FFFFFF")); // Highlight color when clicked
				    }

				    @Override
				    public void mouseExited(MouseEvent e) {
				    	name.setBackground(Color.decode("#DEE4E7")); // Reset color when mouse exits
				    }
				});
				//------------------------------------------------------
		//--------------------------------------------------------
		
		//---------------------PASS--------------------------------
		JLabel pass = new JLabel("PASSWORD : ");
		pass.setFont(text);
		pass.setBounds(25, 420, 150, 20);
		pass.setForeground(Color.BLACK);
		frame.getContentPane().add(pass);
		JTextField password= new JTextField();
		password.setBounds(25, 451, 400, 35);
		password.setBackground(Color.decode("#DEE4E7"));
		password.setFont(text);
		password.setForeground(Color.decode("#37474F"));
		password.setEditable(false);
		frame.getContentPane().add(password);
		// Add click effect to text field
		password.addMouseListener(new MouseAdapter() {
						    @Override
						    public void mouseClicked(MouseEvent e) {
						    	password.setBackground(Color.decode("#FFFFFF")); // Highlight color when clicked
						    }

						    @Override
						    public void mouseExited(MouseEvent e) {
						    	password.setBackground(Color.decode("#DEE4E7")); // Reset color when mouse exits
						    }
						});
						//------------------------------------------------------
		//-----------------------------------------------------------
		
		//--------------------SAVEBUTTON---------------------------
		JButton save = new JButton("");
		save.setToolTipText("SAVE");
		save.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-save-25.png"));
		save.setBounds(197, 525, 60, 50);
		save.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		save.setBackground(Color.decode("#FFFFFF"));
		save.setForeground(Color.WHITE);
		frame.getContentPane().add(save);

		save.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Show confirmation dialog
		        int confirm = JOptionPane.showConfirmDialog(
		            frame,
		            "Are you sure you want to save this record?",
		            "Confirm Save",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE
		        );

		        if (confirm == JOptionPane.YES_OPTION) {
		            // Proceed with saving the record
		            if (check == 1) {
		                try {
		                    adder(
		                        Integer.parseInt(idbox.getText()), 
		                        username.getText(), 
		                        name.getText(), 
		                        password.getText(), 
		                        String.valueOf(clss.getSelectedItem())
		                    );
		                } catch (SQLException e1) {
		                    e1.printStackTrace();
		                }
		            } else if (check == 2) {
		                save.setEnabled(false);
		                try {
		                    if (password.getText().equals("")) {
		                        editor(
		                            Integer.parseInt(idbox.getText()), 
		                            username.getText(), 
		                            name.getText(), 
		                            String.valueOf(clss.getSelectedItem())
		                        );
		                    } else {
		                        editor(
		                            Integer.parseInt(idbox.getText()), 
		                            username.getText(), 
		                            name.getText(), 
		                            password.getText(), 
		                            String.valueOf(clss.getSelectedItem())
		                        );
		                    }
		                } catch (SQLException e1) {
		                    e1.printStackTrace();
		                }
		            }

		            // Reset and update the UI
		            try {
		                idbox.setText(String.valueOf(getid()));
		                edit.setEnabled(false);
		                delete.setEnabled(false);
		                name.setText("");
		                username.setText("");
		                password.setText("");
		                while (model.getRowCount() > 0)
		                    model.removeRow(0);
		                tblupdt();
		            } catch (SQLException e1) {
		                e1.printStackTrace();
		            }
		        }
		    }
		});
	//----------------------EDITBUTTON-----------------------
		edit = new JButton("");
		edit.setToolTipText("EDIT");
		edit.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-edit-25.png"));
		edit.setBounds(284, 525, 60, 50);
		edit.setFont(btn);
		edit.setBackground(Color.decode("#FFFFFF"));
		edit.setForeground(Color.WHITE);
		frame.getContentPane().add(edit);
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				edit.setEnabled(false);
				save.setEnabled(true);
				check = 2;
				username.setEditable(true);
				name.setEditable(true);
				password.setEditable(true);
				clss.setEnabled(true);
			}
		});
		//-------------------------------------------------------
		
		//--------------------ADDBUTTON-------------------------
		add = new JButton("");
		add.setToolTipText("ADD");
		add.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-add-25.png"));
		add.setBounds(25, 525, 60, 50);
		add.setFont(btn);
		add.setBackground(Color.decode("#FFFFFF"));
		add.setForeground(Color.WHITE);
		frame.getContentPane().add(add);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add.setEnabled(false);
				save.setEnabled(true);
				delete.setEnabled(false);
				username.setEditable(true);
				name.setEditable(true);
				password.setEditable(true);
				clss.setEnabled(true);
				check = 1;
				try {
					idbox.setText(String.valueOf(getid()));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//------------------------------------------------------
		
		//------------------------DELETEBUTTON-----------------------
		delete = new JButton("");
		delete.setToolTipText("DELETE");
		delete.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-delete-25 (1).png"));
		delete.setBounds(108, 525, 60, 50);
		delete.setFont(btn);
		delete.setBackground(Color.decode("#FFFFFF"));
		delete.setForeground(Color.WHITE);
		frame.getContentPane().add(delete);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username.setEditable(false);
				name.setEditable(false);
				password.setEditable(false);
				clss.setEnabled(false);
				edit.setEnabled(false);
				add.setEnabled(true);
				try {
					deleter(Integer.parseInt(idbox.getText()));
					idbox.setText(String.valueOf(getid()));
					name.setText("");
					username.setText("");
					password.setText("");
					while(model.getRowCount() > 0)
						model.removeRow(0);
					tblupdt();
				} 
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		//------------------------------------------------------------
		
		//-----------------TABLE ACTION----------------------------
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				password.setText("");
				idbox.setText(String.valueOf(table.getModel().getValueAt(row, 0)));
				username.setText(String.valueOf(table.getModel().getValueAt(row, 1)));
				name.setText(String.valueOf(table.getModel().getValueAt(row, 2)));
				edit.setEnabled(true);
				username.setEditable(false);
				password.setEditable(false);
				clss.setEnabled(false);
				name.setEditable(false);
				save.setEnabled(false);
				delete.setEnabled(true);
				add.setEnabled(true);
			}
		});
		//-------------------------------------------------------------
		
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
	    JButton[] buttons = {add,delete,save,edit};
	    for (JButton button : buttons) {
	        addHoverEffect(button);
	    }

	    // Other components
		//--------------------------------------------------------------
	}
	// Hover effect colors (centralized for easy changes)
	Color hoverColor = Color.decode("#DEE4E7"); // Cornflower Blue for hover
	Color defaultButtonColor = Color.decode("#FFFFFF"); // Default button color
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
	
	public void tblupdt() {
		try {
			ResultSet res = dbSearch();
			for(int i=0; res.next(); i++) {
				model.addRow(new Object[0]);
				model.setValueAt(res.getInt("id"), i, 0);
		        model.setValueAt(res.getString("username"), i, 1);
		        model.setValueAt(res.getString("name"), i, 2);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public int getid() throws SQLException {
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery("SELECT MAX(id) from user");
        if(rst.next()) {
            return rst.getInt("MAX(id)")+1;
        }
        else {
            return 1;
        }
    }
	
	public ResultSet dbSearch() throws SQLException {
		//ENTER PORT, USER, PASSWORD.
		String str1 = "SELECT user.id, user.username, students.name FROM user, students where user.id = students.id";
		String url = "jdbc:mysql://localhost:3306/attendance";
		String user = "root";
		String pass = "";
		con = DriverManager.getConnection(url, user, pass);
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str1);
		return rst;
	}
	
	public void adder(int id, String user, String name, String password, String classes) throws SQLException {
		String adding = "insert into user values ("+id+", '"+user+"', '"+name+"', '"+password+"', 3)";
		String adding2 = "insert into students values ("+id+", '"+name+"', '"+classes+"')";
        Statement stm = con.createStatement();
        stm.executeUpdate(adding);
        stm.executeUpdate(adding2);
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void deleter(int id) throws SQLException {
		String del = "DELETE FROM students WHERE id = "+id;
		String del2 = "DELETE FROM user WHERE id = "+id;
        Statement stm = con.createStatement();
        stm.executeUpdate(del);
        stm.executeUpdate(del2);
	}
	public void editor(int id, String username, String name, String password, String classes) throws SQLException {
		String update = "UPDATE user SET username = '"+username+"', name = '"+name+"', password = '"+password+"'WHERE id = "+id;
		String update2 = "UPDATE students SET name = '"+name+"', class = '"+classes+"' WHERE id = "+id;
        Statement stm = con.createStatement();
        stm.executeUpdate(update);
        stm.executeUpdate(update2);
	}
	public void editor(int id, String username, String name, String classes) throws SQLException {
		String update = "UPDATE user SET username = '"+username+"', name = '"+name+"' WHERE id = "+id;
		String update2 = "UPDATE students SET name = '"+name+"', class = '"+classes+"' WHERE id = "+id;
		Statement stm = con.createStatement();
        stm.executeUpdate(update);
        stm.executeUpdate(update2);
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
}

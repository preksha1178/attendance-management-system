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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Teachers {
	
	DefaultTableModel model = new DefaultTableModel();
	Connection con;
	int check;
	JButton edit;
	JButton delete;
	JButton add;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void teachersView() throws SQLException {
		JFrame frame = new JFrame();
		
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
				id.setFont(new Font("Arial Narrow", Font.BOLD, 20));
				id.setBounds(25, 63, 40, 20);
				id.setForeground(new Color(0, 0, 0));
				frame.getContentPane().add(id);
				JTextField idbox= new JTextField();
				idbox.setBounds(63, 61, 50, 25);
				idbox.setBackground(Color.decode("#DEE4E7"));
				idbox.setFont(new Font("Arial Narrow", Font.BOLD, 20));
				idbox.setForeground(new Color(0, 0, 0));
				idbox.setEditable(false);
				frame.getContentPane().add(idbox);
				// Add click effect to ID text field
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
				//--------------------------------------------------------
				
				//---------------------USERNAME-------------------------
				JLabel user = new JLabel("USERNAME : ");
				user.setFont(new Font("Arial Narrow", Font.BOLD, 20));
				user.setBounds(25, 129, 164, 20);
				user.setForeground(new Color(0, 0, 0));
				frame.getContentPane().add(user);
				JTextField username= new JTextField();
				username.setBounds(25, 160, 400, 35);
				username.setBackground(Color.decode("#DEE4E7"));
				username.setFont(new Font("Arial Narrow", Font.BOLD, 20));
				username.setForeground(Color.decode("#37474F"));
				username.setEditable(false);
				frame.getContentPane().add(username);
				// Add click effect to ID text field
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
				
				//-------------------NAME----------------------------------
				JLabel nm = new JLabel("NAME : ");
				nm.setFont(new Font("Arial Narrow", Font.BOLD, 20));
				nm.setBounds(25, 240, 150, 20);
				nm.setForeground(new Color(0, 0, 0));
				frame.getContentPane().add(nm);
				JTextField name= new JTextField();
				name.setBounds(25, 270, 400, 35);
				name.setBackground(Color.decode("#DEE4E7"));
				name.setFont(new Font("Arial Narrow", Font.BOLD, 20));
				name.setForeground(Color.decode("#37474F"));
				name.setEditable(false);
				frame.getContentPane().add(name);
				// Add click effect to ID text field
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
				pass.setFont(new Font("Arial Narrow", Font.BOLD, 20));
				pass.setBounds(25, 350, 150, 20);
				pass.setForeground(new Color(0, 0, 0));
				frame.getContentPane().add(pass);
				JTextField password= new JTextField();
				password.setBounds(25, 380, 400, 35);
				password.setBackground(Color.decode("#DEE4E7"));
				password.setFont(new Font("Arial Narrow", Font.BOLD, 20));
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
				save.setBounds(193, 499, 64, 50);
				save.setFont(new Font("Arial Narrow", Font.BOLD, 20));
				save.setBackground(new Color(255, 255, 255));
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
				                        password.getText()
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
				                            name.getText()
				                        );
				                    } else {
				                        editor(
				                            Integer.parseInt(idbox.getText()), 
				                            username.getText(), 
				                            name.getText(), 
				                            password.getText()
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
				//-------------------------------------------------------

		//----------------------EDITBUTTON-----------------------
		edit = new JButton("");
		edit.setToolTipText("EDIT");
		edit.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-edit-25.png"));
		edit.setBounds(279, 499, 64, 50);
		edit.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		edit.setBackground(new Color(255, 255, 255));
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
			}
		});
		//-------------------------------------------------------
		
		//--------------------ADDBUTTON-------------------------
		add = new JButton("");
		add.setToolTipText("ADD");
		add.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-add-25.png"));
		add.setBounds(25, 499, 64, 50);
		add.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		add.setBackground(new Color(255, 255, 255));
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
		delete.setBounds(111, 499, 64, 50);
		delete.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		delete.setBackground(new Color(255, 255, 255));
		delete.setForeground(Color.WHITE);
		frame.getContentPane().add(delete);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username.setEditable(false);
				name.setEditable(false);
				password.setEditable(false);
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
				name.setEditable(false);
				save.setEnabled(false);
				delete.setEnabled(true);
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
		String str1 = "SELECT user.id, user.username, teachers.name FROM user, teachers where user.id = teachers.id";
		String url = "jdbc:mysql://localhost:3306/attendance";
		String user = "root";
		String pass = "";
		con = DriverManager.getConnection(url, user, pass);
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str1);
		return rst;
	}
	
	public void adder(int id, String user, String name, String password) throws SQLException {
		String adding = "insert into user values ("+id+", '"+user+"', '"+name+"', '"+password+"', 2)";
		String adding2 = "insert into teachers values ("+id+", '"+name+"')";
        Statement stm = con.createStatement();
        stm.executeUpdate(adding);
        stm.executeUpdate(adding2);
	}
	
	public void deleter(int id) throws SQLException {
		String del = "DELETE FROM teachers WHERE id = "+id;
		String del2 = "DELETE FROM user WHERE id = "+id;
        Statement stm = con.createStatement();
        stm.executeUpdate(del);
        stm.executeUpdate(del2);
	}
	public void editor(int id, String username, String name, String password) throws SQLException {
		String update = "UPDATE user SET username = '"+username+"', name = '"+name+"', password = '"+password+"'WHERE id = "+id;
		String update2 = "UPDATE teachers SET name = '"+name+"' WHERE id = "+id;
        Statement stm = con.createStatement();
        stm.executeUpdate(update);
        stm.executeUpdate(update2);
	}
	public void editor(int id, String username, String name) throws SQLException {
		String update = "UPDATE user SET username = '"+username+"', name = '"+name+"' WHERE id = "+id;
		String update2 = "UPDATE teachers SET name = '"+name+"' WHERE id = "+id;
		Statement stm = con.createStatement();
        stm.executeUpdate(update);
        stm.executeUpdate(update2);
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
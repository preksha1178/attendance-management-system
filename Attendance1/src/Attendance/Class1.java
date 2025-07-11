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
public class Class1{
	DefaultTableModel model = new DefaultTableModel();
	Connection con;
	int check;
	JButton edit;
	JButton delete;
	JButton add;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public void classView() {
		JFrame frame = new JFrame();
		Font text = new Font("Arial Narrow", Font.BOLD, 20);
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

		//--------------------------------------------------------------
		
		//------------------Panel----------------------------------
		JPanel panel = new  JPanel();
		panel.setBounds(0, 0, 1000, 35);
		panel.setBackground(Color.decode("#457b9d"));
		frame.getContentPane().add(panel);
		//---------------------------------------------------------
		
		//--------------------ID-----------------------------------
		JLabel id = new JLabel("ID : ");
		id.setBackground(new Color(240, 240, 240));
		id.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		id.setBounds(26, 88, 56, 20);
		id.setForeground(new Color(0, 0, 0));
		frame.getContentPane().add(id);

		JTextField idbox = new JTextField();
		idbox.setBounds(65, 82, 72, 28);
		idbox.setBackground(Color.decode("#DEE4E7"));
		idbox.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		idbox.setForeground(Color.decode("#37474F"));
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

		//-------------------NAME----------------------------------
		JLabel nm = new JLabel("NAME : ");
		nm.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		nm.setBounds(26, 151, 150, 20);
		nm.setForeground(new Color(0, 0, 0));
		frame.getContentPane().add(nm);

		JTextField name = new JTextField();
		name.setBounds(25, 182, 314, 35);
		name.setBackground(Color.decode("#DEE4E7"));
		name.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		name.setForeground(Color.decode("#37474F"));
		name.setEditable(false);
		frame.getContentPane().add(name);

		// Add click effect to Name text field
		name.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        name.setBackground(Color.decode("#FFFFFF")); // Highlight color when clicked
		    }

		    @Override
		    public void mouseExited(MouseEvent e) {
		        name.setBackground(Color.decode("#DEE4E7")); // Reset color when mouse exits
		    }
		});		//--------------------------------------------------------

		
		//--------------------SAVEBUTTON---------------------------
		JButton save = new JButton("SAVE");
		save.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-save-25.png"));
		save.setBounds(25, 515, 451, 60);
		save.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		save.setBackground(Color.decode("#457b9d"));
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
		                    adder(Integer.parseInt(idbox.getText()), name.getText());
		                } catch (SQLException e1) {
		                    e1.printStackTrace();
		                }
		            } else if (check == 2) {
		                save.setEnabled(false);
		                try {
		                    editor(Integer.parseInt(idbox.getText()), name.getText());
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
		edit = new JButton("EDIT");
		edit.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-edit-25.png"));
		edit.setBounds(336, 444, 140, 60);
		edit.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		edit.setBackground(Color.decode("#457b9d"));
		edit.setForeground(Color.WHITE);
		frame.getContentPane().add(edit);
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				edit.setEnabled(false);
				save.setEnabled(true);
				check = 2;
				name.setEditable(true);
			}
		});
		//-------------------------------------------------------
		
		//--------------------ADDBUTTON-------------------------
		add = new JButton("ADD");
		add.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-add-25.png"));
		add.setBounds(26, 444, 140, 60);
		add.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		add.setBackground(Color.decode("#457b9d"));
		add.setForeground(Color.WHITE);
		frame.getContentPane().add(add);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				add.setEnabled(false);
				delete.setEnabled(false);
				save.setEnabled(true);
				name.setEditable(true);
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
		delete = new JButton("DELETE");
		delete.setIcon(new ImageIcon("C:\\Users\\preks\\Downloads\\icons8-delete-25 (1).png"));
		delete.setBounds(176, 444, 150, 60);
		delete.setFont(new Font("Arial Narrow", Font.BOLD, 20));
		delete.setBackground(Color.decode("#457b9d"));
		delete.setForeground(Color.WHITE);
		frame.getContentPane().add(delete);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				name.setEditable(false);
				edit.setEnabled(false);
				add.setEnabled(true);
				try {
					deleter(Integer.parseInt(idbox.getText()));
					idbox.setText(String.valueOf(getid()));
					name.setText("");
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
		tblupdt();
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.setFont(new Font("Arial Narrow", Font.PLAIN, 17));
		table.setRowHeight(30);
		JScrollPane scPane=new JScrollPane(table);
		scPane.setFocusTraversalPolicyProvider(true);
		scPane.setFont(new Font("Tahoma", Font.PLAIN, 25));
		scPane.setBounds(500, 50, 480, 525);
		frame.getContentPane().add(scPane);
		//------------------------------------------------------
		
		//-----------------TABLE ACTION----------------------------
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				idbox.setText(String.valueOf(table.getModel().getValueAt(row, 0)));
				name.setText(String.valueOf(table.getModel().getValueAt(row, 1)));
				edit.setEnabled(true);
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
	
	public void tblupdt() {
		try {
			ResultSet res = dbSearch();
			for(int i=0; res.next(); i++) {
				model.addRow(new Object[0]);
				model.setValueAt(res.getInt("id"), i, 0);
		        model.setValueAt(res.getString("name"), i, 1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public int getid() throws SQLException {
        Statement stm = con.createStatement();
        ResultSet rst = stm.executeQuery("SELECT MAX(id) from class");
        if(rst.next()) {
            return rst.getInt("MAX(id)")+1;
        }
        else {
            return 1;
        }
    }
	
	public ResultSet dbSearch() throws SQLException {
		//ENTER PORT, USER, PASSWORD.
		String str1 = "SELECT * FROM class";
		String url = "jdbc:mysql://localhost:3306/attendance";
		String user = "root";
		String pass = "";
		con = DriverManager.getConnection(url, user, pass);
		Statement stm = con.createStatement();
		ResultSet rst = stm.executeQuery(str1);
		return rst;
	}
	
	public void adder(int id, String name) throws SQLException {
		String adding = "insert into class values ("+id+", '"+name+"')";
        Statement stm = con.createStatement();
        stm.executeUpdate(adding);
	}
	
	public void deleter(int id) throws SQLException {
		String del = "DELETE FROM class WHERE id = "+id;
        Statement stm = con.createStatement();
        stm.executeUpdate(del);
	}
	public void editor(int id, String name) throws SQLException {
		String update = "UPDATE class SET name = '"+name+"'WHERE id = "+id;
        Statement stm = con.createStatement();
        stm.executeUpdate(update);
	}
}
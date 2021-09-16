import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class JavaCrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField textauthorname;
	private JTextField textedition;
	private JTextField textprice;
	private JTable table;
	private JTextField textbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public void connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/javacrud", "root", "Levi009))(");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		
	}
	
	
	 public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from book");
		    rs = pst.executeQuery();
		   table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 845, 594);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BOOK SHOP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(327, 11, 164, 51);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 87, 384, 268);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(22, 44, 111, 32);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Author Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(22, 92, 125, 32);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Edition");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(22, 135, 111, 32);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Price ");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(22, 180, 111, 32);
		panel.add(lblNewLabel_1_3);
		
		txtbname = new JTextField();
		txtbname.setBounds(190, 53, 144, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		textauthorname = new JTextField();
		textauthorname.setColumns(10);
		textauthorname.setBounds(190, 101, 144, 20);
		panel.add(textauthorname);
		
		textedition = new JTextField();
		textedition.setColumns(10);
		textedition.setBounds(190, 144, 144, 20);
		panel.add(textedition);
		
		textprice = new JTextField();
		textprice.setColumns(10);
		textprice.setBounds(190, 189, 144, 20);
		panel.add(textprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,aname,edition,price;
				
				bname=txtbname.getText();
				aname=textauthorname.getText();
				edition=textedition.getText();
			    price=textprice.getText();
			    
			    try {
					pst = con.prepareStatement("insert into book(bookname,authorname,edition,price)values(?,?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, aname);
					pst.setString(3, edition);
					pst.setString(4, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
						           
					txtbname.setText("");
					textauthorname.setText("");
					textedition.setText("");
					textprice.setText("");
					txtbname.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(10, 350, 109, 41);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(139, 350, 120, 41);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				txtbname.setText("");
				textauthorname.setText("");
				textedition.setText("");
				textprice.setText("");
				txtbname.requestFocus();
			
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_2.setBounds(282, 350, 109, 41);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(395, 87, 424, 304);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 431, 384, 95);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Book ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(33, 33, 73, 39);
		panel_1.add(lblNewLabel_2);
		
		textbid = new JTextField();
		textbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				 try {
			          
			            String id = textbid.getText();

			                pst = con.prepareStatement("select bookname,authorname,edition,price from book where id = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			              
			                String bookname = rs.getString(1);
			                
			                String authorname = rs.getString(2);
			                String edition = rs.getString(3);
			                String price = rs.getString(4);
			                
			                txtbname.setText(bookname);
							textauthorname.setText(authorname);
							textedition.setText(edition);
							textprice.setText(price);
			                
			                
			            }   
			            else
			            {
			            	txtbname.setText("");
							textauthorname.setText("");
							textedition.setText("");
							textprice.setText("");
			            }
			            


			        } 
				
				 catch (SQLException ex) {
			           
			        }
				
				
				
			}
		});
		textbid.setBounds(138, 44, 138, 20);
		panel_1.add(textbid);
		textbid.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Update");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
String bname,aname,edition,price,bid;
				
				
				bname = txtbname.getText();
				aname=textauthorname.getText();
				edition = textedition.getText();
				price = textprice.getText();
				bid  = textbid.getText();
				
				 try {
						pst = con.prepareStatement("update book set bookname= ?,authorname=?,edition=?,price=? where id =?");
						pst.setString(1, bname);
						pst.setString(2, aname);
			            pst.setString(3, edition);
			            pst.setString(4, price);
			            pst.setString(5, bid);
			            pst.executeUpdate();
			           JOptionPane.showMessageDialog(null, "Record Update!!!!!");
			            table_load();
			           
			            txtbname.setText("");
						textauthorname.setText("");
						textedition.setText("");
						textprice.setText("");
						txtbname.requestFocus();
					}
 
		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
	
				
				
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_3.setBounds(458, 400, 137, 41);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			
	             String bid;
	 			bid  = textbid.getText();
	 			
	 			 try {
	 					pst = con.prepareStatement("delete from book where id =?");
	 			
	 		            pst.setString(1, bid);
	 		            pst.executeUpdate();
	 		    //        JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
	 		            table_load();
	 		           
	 		           txtbname.setText("");
						textauthorname.setText("");
						textedition.setText("");
						textprice.setText("");
						txtbname.requestFocus();
	 				}
	  
	 	            catch (SQLException e1) {
	 					
	 					e1.printStackTrace();
	 				}
				
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_4.setBounds(611, 400, 137, 41);
		frame.getContentPane().add(btnNewButton_4);
	}
}

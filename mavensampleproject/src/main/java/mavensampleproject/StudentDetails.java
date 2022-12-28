package mavensampleproject;

import java.awt.EventQueue;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.*;
public class StudentDetails {

	private JFrame frame;
	private JTextField txtrollno;
	private JTextField txtName;
	private JTextField txtclassno;
	private JTextField txtmarks;
	private JTable table;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDetails window = new StudentDetails();
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
	public StudentDetails() {
		initialize();
		Connect();
		table_load();
		
	}
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/mavenproject","root","");
	}
		catch(ClassNotFoundException ex)
		{
			
		}
		catch(SQLException ex)
		{
			
		}
	}
	public void table_load()
	{
	try {
		pst=con.prepareStatement("select * from student");
		rs=pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
	}
	catch(SQLException e2)
	{
		e2.printStackTrace();
	}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 868, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Details");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(335, 42, 204, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Add Student Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(34, 116, 368, 234);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Admission No");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(10, 33, 135, 32);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Name");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(10, 88, 75, 28);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Class");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_2.setBounds(10, 134, 75, 28);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Total Marks");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_3.setBounds(10, 190, 110, 28);
		panel.add(lblNewLabel_2_3);
		
		txtrollno = new JTextField();
		txtrollno.setBounds(143, 37, 178, 28);
		panel.add(txtrollno);
		txtrollno.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(143, 92, 178, 28);
		panel.add(txtName);
		
		txtclassno = new JTextField();
		txtclassno.setColumns(10);
		txtclassno.setBounds(143, 138, 178, 28);
		panel.add(txtclassno);
		
		txtmarks = new JTextField();
		txtmarks.setColumns(10);
		txtmarks.setBounds(143, 194, 178, 28);
		panel.add(txtmarks);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rollno,marks,classno;
				String name;
				name=txtName.getText();
				rollno=Integer.parseInt(txtrollno.getText());
				marks=Integer.parseInt(txtmarks.getText());
				classno=Integer.parseInt(txtclassno.getText());
				
				try {
					pst=con.prepareStatement("insert into student(adm_no,name,class,marks)values(?,?,?,?)");
					pst.setInt(1,rollno);
					pst.setString(2,name);
					pst.setInt(3,classno);
					pst.setInt(4,marks);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record added !!!");
					table_load();
					txtrollno.setText("");
					txtName.setText("");
					txtmarks.setText("");
					txtclassno.setText("");
					txtrollno.requestFocus();
					
					
				}
				catch(SQLException e1)
				{
				}
								
			}
			
		
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(44, 360, 110, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(177, 360, 110, 38);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrollno.setText("");
				txtName.setText("");
				txtmarks.setText("");
				txtclassno.setText("");
				txtrollno.requestFocus();
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(292, 360, 110, 38);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(431, 116, 413, 284);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(34, 408, 368, 64);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setBounds(157, 14, 105, 32);
		textField_3.setColumns(10);
		panel_1.add(textField_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Admission No");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2_4.setBounds(10, 10, 135, 32);
		panel_1.add(lblNewLabel_2_4);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setBounds(513, 425, 110, 38);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(664, 425, 110, 38);
		frame.getContentPane().add(btnDelete);
				
		}
}

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private JTable table;
	private JTextField textID;
	private JTextField textName;
	private JTextField textClass;
	private JTextField textMajor;
	private JTextField textPhone;
	private JTextField textAddress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con = null;
	private JTextField textSearch;
	/**
	 * Create the application.
	 */
	public Main() {
		con = sqlConnection.dbConnection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 896, 574);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(459, 21, 425, 501);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setFont(new Font("Dialog", Font.BOLD, 16));
		lblStudentId.setBounds(25, 12, 124, 33);
		frame.getContentPane().add(lblStudentId);
		
		JLabel lblStudentName = new JLabel("Student Name");
		lblStudentName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblStudentName.setBounds(25, 49, 142, 33);
		frame.getContentPane().add(lblStudentName);
		
		JLabel lblStudentClass = new JLabel("Student Class");
		lblStudentClass.setFont(new Font("Dialog", Font.BOLD, 16));
		lblStudentClass.setBounds(25, 83, 142, 33);
		frame.getContentPane().add(lblStudentClass);
		
		JLabel lblStudentMajor = new JLabel("Student Major");
		lblStudentMajor.setFont(new Font("Dialog", Font.BOLD, 16));
		lblStudentMajor.setBounds(25, 117, 142, 33);
		frame.getContentPane().add(lblStudentMajor);
		
		JLabel lblStudentPhone = new JLabel("Student Phone");
		lblStudentPhone.setFont(new Font("Dialog", Font.BOLD, 16));
		lblStudentPhone.setBounds(25, 153, 142, 33);
		frame.getContentPane().add(lblStudentPhone);
		
		JLabel lblStudentAddress = new JLabel("Student Address");
		lblStudentAddress.setFont(new Font("Dialog", Font.BOLD, 16));
		lblStudentAddress.setBounds(25, 191, 160, 33);
		frame.getContentPane().add(lblStudentAddress);
		
		textID = new JTextField();
		textID.setBounds(191, 20, 247, 19);
		frame.getContentPane().add(textID);
		textID.setColumns(10);
		
		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(191, 49, 247, 19);
		frame.getContentPane().add(textName);
		
		textClass = new JTextField();
		textClass.setColumns(10);
		textClass.setBounds(191, 91, 247, 19);
		frame.getContentPane().add(textClass);
		
		textMajor = new JTextField();
		textMajor.setColumns(10);
		textMajor.setBounds(191, 117, 247, 19);
		frame.getContentPane().add(textMajor);
		
		textPhone = new JTextField();
		textPhone.setColumns(10);
		textPhone.setBounds(191, 153, 247, 19);
		frame.getContentPane().add(textPhone);
		
		textAddress = new JTextField();
		textAddress.setColumns(10);
		textAddress.setBounds(191, 184, 247, 97);
		frame.getContentPane().add(textAddress);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO Student (ID, Name, Class, Major, Phone, Address) VALUES (?, ?, ?, ?, ?, ?)";
					PreparedStatement pst = con.prepareStatement(query);
					pst.setString(1, textID.getText());
					pst.setString(2, textName.getText());
					pst.setString(3, textClass.getText());
					pst.setString(4, textMajor.getText());
					pst.setString(5, textPhone.getText());
					pst.setString(6, textAddress.getText());
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Success created data");
					
					pst.close();
					textID.setText("");
					textName.setText("");
					textClass.setText("");
					textMajor.setText("");
					textAddress.setText("");
					textPhone.setText("");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "error :" + e2);
				}
			}
		});
		btnAdd.setBounds(25, 293, 117, 25);
		frame.getContentPane().add(btnAdd);
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					String query = "SELECT * FROM Student";
					PreparedStatement pst = con.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
//					while(rs.next()) {
//						int id = rs.getInt("id");
//						String name = rs.getString("name");
//						String major = rs.getString("major");
//						System.out.println(id + " " + name + " " + major);
//					}
					table.setModel(DbUtils.resultSetToTableModel(rs));
					textID.setText("");
					textName.setText("");
					textClass.setText("");
					textMajor.setText("");
					textAddress.setText("");
					textPhone.setText("");
					pst.close();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnShow.setBounds(25, 330, 117, 25);
		frame.getContentPane().add(btnShow);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "";
					PreparedStatement pst = con.prepareStatement(query);
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnUpdate.setBounds(25, 369, 117, 25);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "DELETE FROM Student WHERE ID='"+textID.getText()+"' ";
					PreparedStatement pst = con.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Are you sure?");
					pst.close();
					textID.setText("");
					textName.setText("");
					textClass.setText("");
					textMajor.setText("");
					textAddress.setText("");
					textPhone.setText("");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnDelete.setBounds(25, 406, 117, 25);
		frame.getContentPane().add(btnDelete);
		
		JLabel label = new JLabel("Search");
		label.setBounds(193, 316, 70, 15);
		frame.getContentPane().add(label);
		
		textSearch = new JTextField();
		textSearch.setBounds(192, 333, 255, 19);
		frame.getContentPane().add(textSearch);
		textSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM Student WHERE Name LIKE '%"+textSearch.getText()+"%' ";
					PreparedStatement pst = con.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
					textID.setText("");
					textName.setText("");
					textClass.setText("");
					textMajor.setText("");
					textAddress.setText("");
					textPhone.setText("");
				} catch (Exception e3) {
					// TODO: handle exception
				}
			}
		});
		btnSearch.setBounds(191, 364, 117, 25);
		frame.getContentPane().add(btnSearch);
	}
}

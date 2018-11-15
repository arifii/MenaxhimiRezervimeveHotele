package MenaxhimiRezervimeveHotele;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.proteanit.sql.DbUtils;
//import eclipse.wb.swing.FocusTraversalOnArray;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings({"unused", "serial", "deprecation"})
public class frmNdryshoFjalekalimin extends JFrame {

	Connection conn;
	java.sql.PreparedStatement pst;
	
	private JPanel contentPane;
	private JPasswordField txtFjalekalimiAktual;
	private JPasswordField txtFjalekalimiRi;
	private JPasswordField txtRishkruaj;
	private JMenuBar menuBar;
	private JMenu mnInfo;
	private JPanel panel;
	private JLabel lblUsername;
	private JLabel lblmes;
	private JButton btnNdrysho;
	private JButton btnAl_Flag;
	private JButton btnUS_flag;
	private boolean pasNjejt;
	Boolean userNjejt = false;
	private static String username;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmNdryshoFjalekalimin frame = new frmNdryshoFjalekalimin(username, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmNdryshoFjalekalimin(String newUsername, int gjuha) 
	{
		setTitle("Ndryshoni Fjal\u00EBkalimin");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmNdryshoFjalekalimin.class.getResource("/images/changePassword.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		setLocationRelativeTo(null);
		username = newUsername;
		conn=dbConnect.connectDb("MenaxhimiRezervimeveHotele","root","1234");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(135,206,250));
		
		
	    
		
		
		btnNdrysho = new JButton("Ndrysho");
		btnNdrysho.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		btnNdrysho.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						ResultSet res;
						try
						{
							String query="SELECT id FROM administratoret where username='"+username+"'";
							pst=conn.prepareStatement(query);
							res=pst.executeQuery();
							userNjejt=res.next();
							pst.close();
						}
						catch(SQLException c)
						{
							c.printStackTrace();
						}
						if(userNjejt == true)
						{
							JTable tabelaAdministratoret = new JTable();
							String query="SELECT password,salt FROM administratoret WHERE username="+"'"+username+"'";
							try
							{
								pst = conn.prepareStatement(query);
								res = pst.executeQuery();
								tabelaAdministratoret.setModel(DbUtils.resultSetToTableModel(res));
								tabelaAdministratoret.setBackground(new Color(255, 153, 0));
								pst.close();
							}
							catch(Exception e1)
							{
								e1.printStackTrace();
							}
							String fjalekalimi=String.valueOf(tabelaAdministratoret.getValueAt(0,0));
							String salt=String.valueOf(tabelaAdministratoret.getValueAt(0,1));
							String fjalekalimiAktual=HashSHA512.hash(txtFjalekalimiAktual.getText(), salt);
							if(!fjalekalimi.equals(fjalekalimiAktual))
							{
								lblmes.setForeground(Color.RED);
								if(gjuha == 0)
								{
									lblmes.setText("Fjalëkalimi i dhënë nuk korrespodon me username!!");
								}
								else if(gjuha == 1)
								{
									lblmes.setText("Given password don't match with username!!");
								}	
								pasNjejt=false;
							}
							else
							{
								pasNjejt=true;
								if(txtFjalekalimiRi.getText().equals(txtRishkruaj.getText()))
								{
									if(!txtFjalekalimiRi.getText().equals(""))
									{
										String salt1=HashSHA512.gjeneroSalt();
										String fjalekalimiRi=HashSHA512.hash(txtFjalekalimiRi.getText(),salt1);
										String query1="UPDATE administratoret SET password="+"'"+fjalekalimiRi+"'"+","+"salt="+"'"+salt1+"'"+" WHERE username="+"'"+username+"'";
										try{
										pst=conn.prepareStatement(query1);
										pst.execute();
										lblmes.setForeground(Color.GREEN);
										lblmes.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
										if(gjuha == 0)
										{
											lblmes.setText("Fjalëkalimi është ndryshuar me sukses!");
										}
										else if(gjuha == 1)
										{
											lblmes.setText("Password changed successfully!");
										}
										}
										catch(Exception e2)
										{
											e2.printStackTrace();
										}
									}
									else
									{
										if(gjuha == 0)
										{
											lblmes.setText("Fjalëkalimi i ri është i zbrazët!");
										}
										else if(gjuha == 1)
										{
											lblmes.setText("New password is empty!");
										}
									}
								}
								else
								{
									lblmes.setForeground(Color.RED);
									if(gjuha == 0)
									{
										lblmes.setText("Rishkruaj edhe njëherë fjalëkalimin!");
									}
									else if(gjuha == 1)
									{
										lblmes.setText("Retype your password again!");
									}
								}
							}
						}
						else
						{
							lblmes.setForeground(Color.RED);
							if(gjuha == 0 || btnAl_Flag.isSelected())
							{
								lblmes.setText("Username nuk është në rregull!");
							}
							else if(gjuha == 1)
							{
								lblmes.setText("Username is not correct!");
							}
							txtFjalekalimiAktual.setText("");
							txtRishkruaj.setEnabled(false);
							txtRishkruaj.setText("");
							txtFjalekalimiRi.setEnabled(false);
							txtFjalekalimiRi.setText("");
						}
					}
					
				});
		
		btnNdrysho.setBounds(152, 248, 148, 42);
		contentPane.add(btnNdrysho);
		
		panel = new JPanel();
		panel.setBounds(30, 59, 383, 171);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(135,206,250));
		
		lblUsername = new JLabel("Perdoruesi:");
		lblUsername.setBounds(14, 23, 139, 14);
		panel.add(lblUsername);
		
		lblmes = new JLabel("");
		lblmes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblmes.setBounds(7, 148, 369, 14);
		panel.add(lblmes);
		lblmes.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtFjalekalimiAktual = new JPasswordField();
		txtFjalekalimiAktual.setBounds(163, 53, 163, 20);
		panel.add(txtFjalekalimiAktual);
		txtFjalekalimiAktual.setColumns(10);
		
		JLabel lblFjalekalimiAktual = new JLabel("Fjalekalimi i aktual:");
		lblFjalekalimiAktual.setBounds(14, 59, 139, 14);
		panel.add(lblFjalekalimiAktual);
		
		txtFjalekalimiRi = new JPasswordField();
		txtFjalekalimiRi.setBounds(163, 88, 163, 20);
		panel.add(txtFjalekalimiRi);
		txtFjalekalimiRi.setColumns(10);
		txtFjalekalimiRi.setEnabled(true);
		
		JLabel lblFjalekalimiIRi = new JLabel("Fjalekalimi i ri:");
		lblFjalekalimiIRi.setBounds(14, 94, 139, 14);
		panel.add(lblFjalekalimiIRi);
		
		txtRishkruaj = new JPasswordField();
		txtRishkruaj.setBounds(163, 126, 163, 20);
		panel.add(txtRishkruaj);
		txtRishkruaj.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				lblmes.setText("");
			}
		});
		txtRishkruaj.setColumns(10);
		txtRishkruaj.setEnabled(false);
		
		JLabel lblRishkruajFjalekalimin = new JLabel("Rishkruaj fjalekalimin:");
		lblRishkruajFjalekalimin.setBounds(14, 132, 155, 14);
		panel.add(lblRishkruajFjalekalimin);
		
		JLabel lblusername = new JLabel("");
		lblusername.setBounds(163, 23, 128, 14);
		panel.add(lblusername);
		lblusername.setText(username);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 444, 21);
		contentPane.add(menuBar);
		
		
		mnInfo = new JMenu("info");
		mnInfo.addMenuListener(new MenuListener() 
		{
			public void menuCanceled(MenuEvent arg0) 
			{
			}
			public void menuDeselected(MenuEvent arg0) 
			{
			}
			public void menuSelected(MenuEvent arg0)
			{
				frmNdihmaDyGjuhesia frame=new frmNdihmaDyGjuhesia(gjuha);
				frame.setVisible(true);
			}
		});
		mnInfo.setMnemonic('I');
		menuBar.add(mnInfo);
		
		btnAl_Flag = new JButton("");
		btnAl_Flag.setIcon(new ImageIcon(frmNdryshoFjalekalimin.class.getResource("/images/AL_FLAG.png")));
		btnAl_Flag.setBounds(354, 26, 41, 32);
		contentPane.add(btnAl_Flag);
		
		btnUS_flag = new JButton("");
		btnUS_flag.setIcon(new ImageIcon(frmNdryshoFjalekalimin.class.getResource("/images/US_FLAG.png")));
		btnUS_flag.setBounds(393, 26, 41, 32);
		contentPane.add(btnUS_flag);
	
		txtFjalekalimiRi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) 
			{
				ResultSet res;
				try {
				String query="SELECT id FROM administratoret where username='"+username+"'";
					pst=conn.prepareStatement(query);
					res=pst.executeQuery();
					userNjejt=res.next();
					pst.close();
				} catch (SQLException c) {
					// TODO Auto-generated catch block
					c.printStackTrace();
				}
				lblmes.setText("");
				if(userNjejt==true)
				{
					txtFjalekalimiRi.setEditable(true);
					lblmes.setText("");	
					 JTable tabelaProf = new JTable();
						String query="SELECT password,salt FROM administratoret WHERE username="+"'"+username+"'";
						try{
						pst=conn.prepareStatement(query);
						res=pst.executeQuery();
						tabelaProf.setModel(DbUtils.resultSetToTableModel(res));
						tabelaProf.setBackground(new Color(255, 153, 0));
						pst.close(); 
						}
						catch(Exception arg0)
						{
							arg0.printStackTrace();
						}
						String fjalekalimi=String.valueOf(tabelaProf.getValueAt(0,0));
						
						String salt=String.valueOf(tabelaProf.getValueAt(0,1));
						
						String fjalekalimiAkt=HashSHA512.hash(txtFjalekalimiAktual.getText(), salt);
						
						if(!fjalekalimi.equals(fjalekalimiAkt))
						{
							lblmes.setForeground(Color.RED);
							if(gjuha == 0)
							{
								lblmes.setText("Fjalëkalimi i dhënë nuk korrespodon me username");	
							}
							else if(gjuha == 1)
							{
								lblmes.setText("Given password don't match with username!!");
							}
							pasNjejt=false;
						}
						else
						{
							pasNjejt=true;
						}
						
						
				}
				else
				{
					lblmes.setForeground(Color.RED);
					if(gjuha == 0)
					{
						lblmes.setText("Username nuk është në rregull!");
					}
					else if(gjuha == 1)
					{
						lblmes.setText("Username is not correct!");
					}
					txtFjalekalimiRi.setEditable(false);
				}
			}
		});
		txtFjalekalimiRi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if((!txtFjalekalimiRi.getText().equals(""))&&pasNjejt)
				{
					txtRishkruaj.setEnabled(true);
				}
				else if(pasNjejt==false)
				{
					e.consume();
					txtRishkruaj.setEnabled(false);
					txtRishkruaj.setText("");
				}
			}
		});
		txtFjalekalimiAktual.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ResultSet res;
				try {
				String query="SELECT id FROM administratoret where username='"+username+"'";
					pst=conn.prepareStatement(query);
					res=pst.executeQuery();
					userNjejt=res.next();
					pst.close();
					lblmes.setText("");
				} catch (SQLException c) {
					// TODO Auto-generated catch block
					c.printStackTrace();
				}
				txtFjalekalimiRi.setEnabled(true);
				if(userNjejt==false)
				{
					lblmes.setForeground(Color.RED);
					if(gjuha == 0)
					{
						lblmes.setText("Username nuk është në rregull!");
					}
					else if(gjuha == 1)
					{
						lblmes.setText("Username is not correct!");
					}
					txtFjalekalimiRi.setEnabled(false);
				}
			}
		});
		btnAl_Flag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblUsername.setText("Përdoruesi:");
				lblFjalekalimiAktual.setText("Fjalëkalimi Aktual:");
				lblFjalekalimiIRi.setText("Fjalëkalimi i ri:");
				lblRishkruajFjalekalimin.setText("Rishkruaj Fjalëkalimin:");
				btnNdrysho.setText("Ndrysho");
				setTitle("Ndrysho Passwordin");
				lblmes.setText("");
				
			}
		});
		btnUS_flag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblUsername.setText("Username:");
				lblFjalekalimiAktual.setText("Current password:");
				lblFjalekalimiIRi.setText("New password:");
				lblRishkruajFjalekalimin.setText("Rewrite password:");
				btnNdrysho.setText("Change");
				setTitle("Change Password");
				lblmes.setText("");
			}
		});
		btnNdrysho.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					ResultSet res;
					try {
					String query="SELECT id FROM adminisratoret where username='"+username+"'";
						pst=conn.prepareStatement(query);
						res=pst.executeQuery();
						userNjejt=res.next();
						pst.close();
					} catch (SQLException c) {
						// TODO Auto-generated catch block
						c.printStackTrace();
					}
					if(userNjejt==true)
					{
						 JTable tabelaProf = new JTable();
							String query="SELECT password,salt FROM administratoret WHERE username="+"'"+username+"'";
							try{
							pst=conn.prepareStatement(query);
							res=pst.executeQuery();
							tabelaProf.setModel(DbUtils.resultSetToTableModel(res));
							tabelaProf.setBackground(new Color(255, 153, 0));
							pst.close(); 
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							String fjalekalimi=String.valueOf(tabelaProf.getValueAt(0,0));
							
							String salt=String.valueOf(tabelaProf.getValueAt(0,1));
							
							String fjalekalimiAkt=HashSHA512.hash(txtFjalekalimiAktual.getText(), salt);
							
							if(!fjalekalimi.equals(fjalekalimiAkt))
							{
								lblmes.setForeground(Color.RED);
								if(gjuha == 0)
								{
									lblmes.setText("Fjalëkalimi i dhënë nuk korrespodon me username");	
								}
								else if(gjuha == 1)
								{
									lblmes.setText("Given password don't match with username!!");
								}	
								pasNjejt=false;
							}
							else
							{
								pasNjejt=true;
								if(txtFjalekalimiRi.getText().equals(txtRishkruaj.getText()))
								{
									if(!txtFjalekalimiRi.getText().equals(""))
									{
										String salt1=HashSHA512.gjeneroSalt();
										String fjalekalimiRi=HashSHA512.hash(txtFjalekalimiRi.getText(),salt1);
										String query1="UPDATE administratoret SET password="+"'"+fjalekalimiRi+"'"+","+"salt="+"'"+salt1+"'"+" WHERE username="+"'"+username+"'";
										try{
										pst=conn.prepareStatement(query1);
										pst.execute();
										lblmes.setForeground(Color.GREEN);
										lblmes.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
										if(gjuha == 0)
										{
											lblmes.setText("Fjalëkalimi është ndryshuar me sukses!");
										}
										else if(gjuha == 1)
										{
											lblmes.setText("Password changed successfully!");
										}
										}
										catch(Exception e)
										{
											e.printStackTrace();
										}
									}
									else
									{
										if(gjuha == 0)
										{
											lblmes.setText("Fjalëkalimi i ri është i zbrazët!");
										}
										else if(gjuha == 1)
										{
											lblmes.setText("New password is empty!");
										}
									}
								}
								else
								{
									lblmes.setForeground(Color.RED);
									if(gjuha == 0)
									{
										lblmes.setText("Rishkruaj edhe njëherë fjalëkalimin!");
									}
									else if(gjuha == 1)
									{
										lblmes.setText("Retype your password again!");
									}
								}
							}
						
					}
					else
					{
						lblmes.setForeground(Color.RED);
						if(gjuha == 0)
						{
							lblmes.setText("Username nuk është në rregull!");
						}
						else if(gjuha == 1)
						{
							lblmes.setText("Username is not correct!");
						}
						txtFjalekalimiAktual.setText("");
						txtRishkruaj.setEnabled(false);
						txtRishkruaj.setText("");
						txtFjalekalimiRi.setEnabled(false);
						txtFjalekalimiRi.setText("");
					}	
				}
			}
		});
		if(gjuha==0)
		{
			btnAl_Flag.doClick();
		}
		else if(gjuha==1)
		{
			btnUS_flag.doClick();
		}
	}
}

package MenaxhimiRezervimeveHotele;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.security.MessageDigest;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

@SuppressWarnings({"unused", "serial"})
public class frmLogin extends JFrame
{
	Connection conn=null;
	ResultSet res=null;
	PreparedStatement pst=null;
	private JPanel contentPane;
	private JLabel lblImage;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField txtShfrytezuesi;
	private JPasswordField txtFjalekalimi;
	private JButton btnKycu;
	private JLabel lblGjuha;
	private JRadioButton rdbtnShqip;
	private JRadioButton rdbtnAnglisht;
	
	
	private boolean bllokuar=false;
	private boolean userValid=false;
	private boolean passValid=false;
	private static String idUser;
	private static frmMain objMain;
	private static int gjuha=0;
	private static int count = 0;
	
	private final ButtonGroup buttonGroupGjuha = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	
	public String hash()
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			
			String sql="SELECT salt FROM administratoret WHERE username='" + txtShfrytezuesi.getText() + "'";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			res.next();
			String salt = res.getString(1);
	
			String saltedPass = new String(txtFjalekalimi.getPassword())+salt;
			md.update(saltedPass.getBytes("UTF-8"));
			byte[] digest = md.digest();
			
			return String.format("%064x", new java.math.BigInteger(1, digest));
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate hashimit: "+e.toString());
			return "";
		}
	}
	
	public void loginUser()
	{
		if(txtShfrytezuesi.getText().trim().length()!=0)
		{
			try 
			{
				String sql="SELECT * FROM administratoret WHERE username='"+txtShfrytezuesi.getText()+"'";
				pst=conn.prepareStatement(sql);
				res=pst.executeQuery();
				res.last();
				int nrRez = res.getRow();
				if(nrRez==1)
				{
					userValid = true;
					idUser = res.getString(1);
				}
				else
				{
					userValid = false;
				}
				
			} 
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "Gabim gjate ekzekutimi: "+e.toString());
			}
		}
	}
	
	public void loginPass()
	{
		try 
		{
			String sql="SELECT * FROM administratoret WHERE username='" + txtShfrytezuesi.getText()
																   + "' AND password = '"+  hash() +"'";
			pst=conn.prepareStatement(sql);
			res=pst.executeQuery();
			res.last();
			int nrRez = res.getRow();
			if(nrRez==1)
			{
				passValid = true;
			}
			else
			{
				passValid = false;
			}
			
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate ekzekutimi: "+e.toString());
		}
	}
	
	public void blloko()
	{
		try 
		{
			String sql="UPDATE administratoret SET bllokimi=1 WHERE username = '" + txtShfrytezuesi.getText()+ "'";
			pst=conn.prepareStatement(sql);
			pst.execute();
			pst.close();
			bllokuar();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate ekzekutimi: "+e.toString());
		}
	}
	
	public boolean bllokuar()
	{
		try 
		{
			String sql="SELECT bllokimi FROM administratoret WHERE username='" + txtShfrytezuesi.getText() + "'";
			pst=conn.prepareStatement(sql);
			res = pst.executeQuery();
			res.next();
			String b = res.getString("bllokimi");
			if(b.equals("1"))
			{
				bllokuar = true;
			}
			else
			{
				bllokuar = false;
			}
			return true;
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate ekzekutimi: "+e.toString());
			return false;
		}
	}
	
	public void validoFushat()
	{
		loginUser();
		if(userValid)
		{
			txtShfrytezuesi.setBackground(Color.GREEN);
			txtFjalekalimi.setEditable(true);
		}
		else
		{
			txtShfrytezuesi.setBackground(Color.RED);
			txtFjalekalimi.setEditable(false);
			txtFjalekalimi.setText("");
		}
	}
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try {
					frmLogin frame = new frmLogin();
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
	public frmLogin() 
	{
		setTitle("Lidhu");
		conn = dbConnect.connectDb("MenaxhimiRezervimeveHotele", "root", "1234");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmLogin.class.getResource("/images/access_key-16.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 356);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(135,206,250));
		
		lblImage = new JLabel("");
		lblImage.setIcon(new ImageIcon(frmLogin.class.getResource("/images/loginLogo.png")));
		lblImage.setBounds(10, 11, 254, 295);
		contentPane.add(lblImage);
		
		JLabel lblTitulli = new JLabel("Mir\u00EBsevini!");
		lblTitulli.setFont(new Font("AppleGothic", Font.PLAIN, 29));
		lblTitulli.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulli.setBounds(298, 11, 236, 38);
		contentPane.add(lblTitulli);
		
		
		
		lblUsername = new JLabel("Shfryt\u00EBzuesi: ");
		lblUsername.setBounds(286, 84, 85, 18);
		lblUsername.setFont(new Font("AppleGothic", Font.PLAIN, 14));
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Fjal\u00EBkalimi: ");
		lblPassword.setBounds(286, 128, 85, 18);
		lblPassword.setFont(new Font("AppleGothic", Font.PLAIN, 14));
		contentPane.add(lblPassword);
		
		txtShfrytezuesi = new JTextField();
		txtShfrytezuesi.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseExited(MouseEvent e)
					{
						validoFushat();
					}
				});
		txtShfrytezuesi.setBounds(374, 81, 130, 26);
		txtShfrytezuesi.setFont(new Font("AppleGothic", Font.PLAIN, 12));
		contentPane.add(txtShfrytezuesi);
		txtShfrytezuesi.setColumns(10);
		
		txtFjalekalimi = new JPasswordField();
		txtFjalekalimi.addFocusListener(new FocusAdapter()
				{
					@Override
					public void focusGained(FocusEvent e)
					{
						validoFushat();
					}
				});
		txtFjalekalimi.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseEntered(MouseEvent e)
					{
						validoFushat();
					}
				});
		txtFjalekalimi.setBounds(374, 125, 130, 26);
		txtFjalekalimi.setFont(new Font("AppleGothic", Font.PLAIN, 12));
		contentPane.add(txtFjalekalimi);
		
		
		
		
		
		
		JLabel lblKujdes = new JLabel("");
		lblKujdes.setHorizontalAlignment(SwingConstants.CENTER);
		lblKujdes.setBounds(283, 163, 250, 16);
		lblKujdes.setFont(new Font("AppleGothic", Font.BOLD, 13));
		lblKujdes.setForeground(Color.RED);
		contentPane.add(lblKujdes);
		

		lblGjuha = new JLabel("Gjuha:");
		lblGjuha.setFont(new Font("AppleGothic", Font.PLAIN, 13));
		lblGjuha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblGjuha.setBounds(266, 275, 64, 16);
		contentPane.add(lblGjuha);
		
		rdbtnShqip = new JRadioButton("Shqip");
		rdbtnShqip.setFont(new Font("AppleGothic", Font.PLAIN, 13));
		rdbtnShqip.setBounds(340, 261, 85, 23);
		rdbtnShqip.setSelected(true);
		buttonGroupGjuha.add(rdbtnShqip);
		contentPane.add(rdbtnShqip);
		
		rdbtnAnglisht = new JRadioButton("Anglisht");
		rdbtnAnglisht.setBounds(340, 287, 85, 23);
		rdbtnAnglisht.setFont(new Font("AppleGothic", Font.PLAIN, 13));
		buttonGroupGjuha.add(rdbtnAnglisht);
		contentPane.add(rdbtnAnglisht);
		
		rdbtnShqip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setTitle("Lidhu");
				lblTitulli.setText("Mir\u00EBsevini!");
				lblUsername.setText("Shfryt\u00EBzuesi:");
				lblPassword.setText("Fjal\u00EBkalimi:");
				rdbtnShqip.setText("Shqip");
				lblGjuha.setText("Gjuha:");
				rdbtnAnglisht.setText("Anglisht");
				btnKycu.setText("Ky\u00E7u");
				gjuha=0;
			}
		});
		
		rdbtnAnglisht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setTitle("Login");
				lblTitulli.setText("Welcome!");
				lblUsername.setText("Username:");
				lblPassword.setText("Password:");
				rdbtnShqip.setText("Albanian");
				rdbtnAnglisht.setText("English");
				lblGjuha.setText("Language:");
				btnKycu.setText("Login");
				gjuha=1;
			}
		});
		
		
		
		class listener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				String shfrytezuesi = txtShfrytezuesi.getText();
				String fjalekalimi = new String(txtFjalekalimi.getPassword());
				
				if(fjalekalimi.trim().length()!=0 && shfrytezuesi.trim().length()!=0)
				{
					loginPass();
					bllokuar();
				}
				if(count!=3 && !bllokuar)
				{
					if(userValid && passValid)
					{
						count = 0;
						count = 0;
						if(objMain == null)
						{
							objMain = new frmMain(shfrytezuesi, gjuha);
							objMain.setVisible(true);
						}
						else if(!objMain.isVisible())
						{
							objMain.setVisible(true);
						}
						dispose();
					}
					else if(shfrytezuesi.trim().length()!=0 && !fjalekalimi.equals(""))
					{
						if(count>=0 && count<2)
						{
							if(gjuha==0)
							{
								lblKujdes.setText(userValid ? "Keni shtypur fjalekalimin e gabuar" : "Keni shtypur gabim fjalekalimin ose emrin");
							}
							else if(gjuha==1)
							{
								lblKujdes.setText(userValid ? "Wrong Password" : "Wrong Username and Password");
							}
							count++;
						}
						else
						{
							//Pas bllokimit ne llogari shfrytezuesi nuk ka qasje ne session
							if(gjuha==0)
							{
								lblKujdes.setText("Llogaria juaj eshte bllokuar!");
							}
							else if(gjuha==1)
							{
								lblKujdes.setText("You account has been blocked!");
							}
							count++;
							blloko();
						}
					}
					else
					{
						if(gjuha==0)
							lblKujdes.setText("Ju lutem plotesoni fushat e shenjuara.");
						else if(gjuha==1)
							lblKujdes.setText("Please fill the colored textfields.");
						if(shfrytezuesi.equals("") && fjalekalimi.equals(""))
						{
							txtShfrytezuesi.setBackground(Color.MAGENTA);
							txtFjalekalimi.setBackground(Color.MAGENTA);
						}
						else if(fjalekalimi.equals(""))
						{
							txtShfrytezuesi.setBackground(Color.WHITE);
							txtFjalekalimi.setBackground(Color.MAGENTA);
						}
						
						else
						{
							txtShfrytezuesi.setBackground(Color.MAGENTA);
							txtFjalekalimi.setBackground(Color.WHITE);
						}
					}
				}
				else
				{
					bllokuar();
					if(bllokuar && count==3)
					{
						System.exit(0);
					}
					else
					{
						if(gjuha==0)
							lblKujdes.setText("Qasja e ndaluar. Llogaria e bllokuar!");
						else if(gjuha==1)
							lblKujdes.setText("Access Denied. Account is blocked!");
						count++; //opsionale
					}
				}
			}
		}
		
		
		btnKycu = new JButton("Ky\u00E7u");
		btnKycu.setForeground(new Color(255, 140, 0));
		btnKycu.setBounds(358, 198, 106, 38);
		btnKycu.setFont(new Font("AppleMyungjo", Font.PLAIN, 19));
		listener btnListen = new listener();
		btnKycu.addActionListener(btnListen);
		contentPane.add(btnKycu);
		
		txtFjalekalimi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				btnListen.actionPerformed(e);
			}
		});
		
	}
	public frmLogin(int newGjuha)
	{
		this();
		gjuha = newGjuha;
	}
}

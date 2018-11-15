package MenaxhimiRezervimeveHotele;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;

@SuppressWarnings("all")
public class frmRegjistro extends JFrame {

	private JPanel contentPane;
	private JLabel lblEmri;
	private JTextField txtEmri;
	private JLabel lblMbiemri;
	private JTextField txtMbiemri;
	private JLabel lblDitlindja;
	private JTextField txtUsername;
	private JPasswordField txtFjalekalimi;
	private JMenuItem mntmShqip;
	private JMenuItem mntmAnglisht;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	java.sql.PreparedStatement pst;
	private int count=0;
	private static int gjuha=0;
	private final ButtonGroup btnGrGjinia = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmRegjistro frame = new frmRegjistro();
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
	public frmRegjistro() 
	{
		Connection conn=dbConnect.connectDb("MenaxhimiRezervimeveHotele","root","1234");
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmRegjistro.class.getResource("/images/reg.png")));
		setTitle("Shto administrator te ri");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 369, 449);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Color p =new Color(0,0,30);
		contentPane.setBackground(Color.BLACK);
		
		
		lblEmri = new JLabel("Emri:");
		lblEmri.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmri.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblEmri.setForeground(new Color(204, 255, 51));
		lblEmri.setBounds(30, 55, 46, 14);
		contentPane.add(lblEmri);
		
		txtEmri = new JTextField();
		txtEmri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakteri=e.getKeyChar();
				if(Character.isLetter(karakteri))
				{
					
				}
				else
				{
					e.consume();
				}
			}
		});
		txtEmri.setBounds(138, 52, 212, 20);
		contentPane.add(txtEmri);
		txtEmri.setColumns(10);
		
		lblMbiemri = new JLabel("Mbiemri:");
		lblMbiemri.setHorizontalAlignment(SwingConstants.LEFT);
		lblMbiemri.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblMbiemri.setForeground(new Color(204, 255, 51));
		lblMbiemri.setBounds(30, 86, 55, 14);
		contentPane.add(lblMbiemri);
		
		txtMbiemri = new JTextField();
		txtMbiemri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char karakteri=e.getKeyChar();
				if(Character.isLetter(karakteri))
				{
					
				}
				else
				{
					e.consume();
				}
			}
		});
		txtMbiemri.setColumns(10);
		txtMbiemri.setBounds(138, 83, 212, 20);
		contentPane.add(txtMbiemri);
		
		
		lblDitlindja = new JLabel("Ditlindja:");
		lblDitlindja.setHorizontalAlignment(SwingConstants.LEFT);
		lblDitlindja.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDitlindja.setForeground(new Color(204, 255, 51));
		lblDitlindja.setBounds(30, 133, 56, 14);
		contentPane.add(lblDitlindja);
		
		JComboBox cmbMuaji = new JComboBox();
		cmbMuaji.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		cmbMuaji.setBounds(138, 130, 71, 20);
		contentPane.add(cmbMuaji);
		
		JComboBox cmbData = new JComboBox();
		cmbData.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cmbData.setBounds(213, 130, 55, 20);
		contentPane.add(cmbData);
		
		JComboBox cmbViti = new JComboBox();
		cmbViti.setModel(new DefaultComboBoxModel(new String[] {"", "1953", "1954", "1955", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995"}));
		cmbViti.setBounds(269, 130, 88, 20);
		contentPane.add(cmbViti);
		
		
		JRadioButton rdbShqip = new JRadioButton("Shqip");
		rdbShqip.setBounds(138, 376, 109, 23);
		contentPane.add(rdbShqip);
		
		JRadioButton rdbAnglisht = new JRadioButton("Anglisht");
		rdbAnglisht.setBounds(248, 376, 109, 23);
		contentPane.add(rdbAnglisht);
		
		JComboBox cmbMuajip = new JComboBox();
		cmbMuajip.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		cmbMuajip.setBounds(138, 161, 71, 20);
		contentPane.add(cmbMuajip);
		
		JLabel lblDataEFillimit = new JLabel("Data e fillimit te pun\u00EBs:");
		lblDataEFillimit.setHorizontalAlignment(SwingConstants.LEFT);
		lblDataEFillimit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDataEFillimit.setForeground(new Color(204, 255, 51));
		lblDataEFillimit.setBounds(30, 164, 142, 14);
		contentPane.add(lblDataEFillimit);
		
		JComboBox cmbDatap = new JComboBox();
		cmbDatap.setModel(new DefaultComboBoxModel(new String[] {"", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cmbDatap.setBounds(211, 161, 57, 20);
		contentPane.add(cmbDatap);
		
		JComboBox cmbVitip = new JComboBox();
		cmbVitip.setModel(new DefaultComboBoxModel(new String[] {"", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017"}));
		cmbVitip.setBounds(269, 161, 88, 20);
		contentPane.add(cmbVitip);
		cmbMuaji.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(cmbMuaji.getSelectedItem()=="04"||cmbMuaji.getSelectedItem()=="06"||cmbMuaji.getSelectedItem()=="09"||cmbMuaji.getSelectedItem()=="11")
				{
					cmbData.setModel(new DefaultComboBoxModel(new String[] {"","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
				}
				else if(cmbMuaji.getSelectedItem()=="02")
				{
					cmbData.setModel(new DefaultComboBoxModel(new String[] {"","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"}));
				}
				else
				{
					cmbData.setModel(new DefaultComboBoxModel(new String[] {"","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30","31"}));
				}
			}
		});
		
		cmbMuajip.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cmbMuajip.getSelectedItem()=="04"||cmbMuaji.getSelectedItem()=="06"||cmbMuaji.getSelectedItem()=="09"||cmbMuaji.getSelectedItem()=="11")
				{
					cmbDatap.setModel(new DefaultComboBoxModel(new String[] {"","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"}));
				}
				else if(cmbMuajip.getSelectedItem()=="02")
				{
					cmbDatap.setModel(new DefaultComboBoxModel(new String[] {"","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"}));
				}
				else
				{
					cmbDatap.setModel(new DefaultComboBoxModel(new String[] {"","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30","31"}));
				}
			}
		});
		
		JLabel lblGjinia = new JLabel("Gjinia:");
		lblGjinia.setHorizontalAlignment(SwingConstants.LEFT);
		lblGjinia.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblGjinia.setForeground(new Color(204, 255, 51));
		lblGjinia.setBounds(30, 195, 46, 14);
		contentPane.add(lblGjinia);
		
		
		JRadioButton rdBtnM= new JRadioButton("Mashkull");
		btnGrGjinia.add(rdBtnM);
		rdBtnM.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdBtnM.setForeground(new Color(204, 255, 51));
		rdBtnM.setBounds(138, 192, 71, 20);
		rdBtnM.setBackground(p);
		contentPane.add(rdBtnM);
		
		JRadioButton rdBtnF= new JRadioButton("Femër");
		btnGrGjinia.add(rdBtnF);
		rdBtnF.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdBtnF.setForeground(new Color(204, 255, 51));
		rdBtnF.setBounds(221, 192, 61, 20);
		rdBtnF.setBackground(p);
		contentPane.add(rdBtnF);
		
		
		JLabel lblTitulli = new JLabel("Titulli:");
		lblTitulli.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulli.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblTitulli.setForeground(new Color(204, 255, 51));
		lblTitulli.setBounds(30, 223, 46, 14);
		contentPane.add(lblTitulli);
		
		JRadioButton rdBachelor = new JRadioButton("Bachelor");
		rdBachelor.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		buttonGroup.add(rdBachelor);
		rdBachelor.setForeground(new Color(204, 255, 51));
		rdBachelor.setBounds(138, 219, 78, 23);
		rdBachelor.setBackground(p);
		contentPane.add(rdBachelor);
		
		JRadioButton rdMaster = new JRadioButton("Master");
		rdMaster.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		buttonGroup.add(rdMaster);
		rdMaster.setForeground(new Color(204, 255, 51));
		rdMaster.setBounds(217, 219, 65, 23);
		rdMaster.setBackground(p);
		contentPane.add(rdMaster);
		Color ngjBtnRegj = new Color(0,204,102);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 153, 0));
		panel_1.setBounds(0, 0, 369, 34);
		contentPane.add(panel_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 365, 21);
		panel_1.add(menuBar);
		
		
		JMenu mnGjuha = new JMenu("Gjuha");
		mnGjuha.setMnemonic('G');
		menuBar.add(mnGjuha);
		
		mntmShqip = new JMenuItem("Shqip");
		mntmShqip.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnGjuha.add(mntmShqip);
		
		mntmAnglisht = new JMenuItem("Anglisht");
		mntmAnglisht.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnGjuha.add(mntmAnglisht);
		
		JMenu mnInfo = new JMenu("Info");
		mnInfo.setMnemonic('I');
		mnInfo.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}
			public void menuDeselected(MenuEvent arg0) {
			}
			public void menuSelected(MenuEvent arg0) {
				frmInfoRegjistro frame=new frmInfoRegjistro(gjuha);
				frame.setVisible(true);
			}
		});
		menuBar.add(mnInfo);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(138, 249, 212, 20);
		contentPane.add(txtUsername);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblUsername.setForeground(new Color(204, 255, 51));
		lblUsername.setBounds(30, 252, 65, 14);
		contentPane.add(lblUsername);
		
		txtFjalekalimi = new JPasswordField();
		txtFjalekalimi.setColumns(10);
		txtFjalekalimi.setBounds(138, 280, 212, 20);
		contentPane.add(txtFjalekalimi);
		
		JLabel lblok = new JLabel("");
		lblok.setBounds(0, 308, 102, 92);
		contentPane.add(lblok);
		
		JLabel lblFjalekalimi = new JLabel("Fjal\u00EBkalimi:");
		lblFjalekalimi.setHorizontalAlignment(SwingConstants.LEFT);
		lblFjalekalimi.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblFjalekalimi.setForeground(new Color(204, 255, 51));
		lblFjalekalimi.setBounds(30, 283, 68, 14);
		contentPane.add(lblFjalekalimi);
		
		
		JLabel txtmes = new JLabel("");
		txtmes.setHorizontalAlignment(SwingConstants.CENTER);
		txtmes.setForeground(Color.RED);
		txtmes.setBounds(14, 309, 340, 14);
		contentPane.add(txtmes);
		
		JButton btnRegjistro = new JButton("Regjistro");
		btnRegjistro.setMnemonic(KeyEvent.VK_ENTER);
		btnRegjistro.setBounds(112, 335, 144, 34);
		contentPane.add(btnRegjistro);
		btnRegjistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet res;
				Boolean userNjejt=false;
				try {
				String query="SELECT id FROM administratoret where emri='"+txtUsername.getText()+"'";
					pst=conn.prepareStatement(query);
					res=pst.executeQuery();
					userNjejt=res.next();
					pst.close();
				} catch (SQLException c) {
					// TODO Auto-generated catch block
					c.printStackTrace();
				}

				if(txtEmri.getText().equals("")||txtMbiemri.getText().equals("")||
						txtUsername.getText().equals("")||txtFjalekalimi.getText().equals("")||
						(rdBtnM.isSelected()==false&&rdBtnF.isSelected()==false)||
						(rdBachelor.isSelected()==false&&rdMaster.isSelected()==false)||
						cmbMuaji.getSelectedItem().equals("")||cmbData.getSelectedItem().equals("")||cmbViti.getSelectedItem().equals("")||
						cmbMuajip.getSelectedItem().equals("")||cmbDatap.getSelectedItem().equals("")||cmbVitip.getSelectedItem().equals(""))
				{
					txtmes.setForeground(Color.RED);
					if(rdbAnglisht.isSelected())
					{
						txtmes.setText("Please fill the required data!!");
					}
					else
					{
						txtmes.setText("Ju lutem plotesoni te dhenat!!");
					}
				}
				else if(userNjejt==true)
				{
					if(rdbAnglisht.isSelected())
					{
						txtmes.setText("Try another username!!");
					}
					else
					{
					txtmes.setText("Username eshte i perdorur,ju lutem zevendesojeni ate!!");
					}
				}
				else
				{
					txtmes.setText("");
				String emri,mbiemri,ditlindja,dFpunes,gjinia,username,fjalekalimi,titulli,muaji,viti,data,muajip,vitip,datap;
				emri=txtEmri.getText();
				mbiemri=txtMbiemri.getText();
				muaji=String.valueOf(cmbMuaji.getSelectedItem());
				data=String.valueOf(cmbData.getSelectedItem());
				viti=String.valueOf(cmbViti.getSelectedItem());
				ditlindja=viti+"-"+muaji+"-"+data;
				muajip=String.valueOf(cmbMuajip.getSelectedItem());
				datap=String.valueOf(cmbDatap.getSelectedItem());
				vitip=String.valueOf(cmbVitip.getSelectedItem());
				dFpunes=vitip+"-"+muajip+"-"+datap;
				if(rdBtnM.isSelected())
				{
					gjinia = "M";
				}
				else
				{
					gjinia = "F";
				}
				if(rdBachelor.isSelected())
				{
					titulli="Bachelor";
				}
				else
				{
					titulli="Master";
				}
				String salt = HashSHA512.gjeneroSalt(); //salt qe ruhet ne db
				String pass = txtFjalekalimi.getText(); //passi perkates
				String hashedValue = HashSHA512.hash(pass, salt);
				username=txtUsername.getText();
				fjalekalimi=hashedValue;
				
				String vitiLindjesString = String.valueOf(cmbViti.getSelectedItem());
				int vitiLindjes = Integer.parseInt(vitiLindjesString);
				
				String muajiLindjesString = String.valueOf(cmbMuaji.getSelectedItem());
				int muajiLindjes = Integer.parseInt(muajiLindjesString);
				
				String dataLindjesString = String.valueOf(cmbData.getSelectedItem());
				int dataLindjes = Integer.parseInt(dataLindjesString);
				
				LocalDate birthdate = LocalDate.of(vitiLindjes, muajiLindjes, dataLindjes);
				LocalDate now = LocalDate.now();
				Period period = Period.between(birthdate, now);
				int mosha = period.getYears();
		
				String query="INSERT INTO administratoret VALUES(NULL,'"+emri+"','"+mbiemri+"','"+gjinia+"','"+mosha+"','"+username+"', '"+fjalekalimi+"', '"+salt+"', '0', '0');";
				java.sql.PreparedStatement prs;
				try{
					
					prs=conn.prepareStatement(query);
					prs.execute();
					lblok.setIcon(new ImageIcon(frmRegjistro.class.getResource("/images/ok2.jpg")));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				
				
				}
				
			}
		});
		
		
		btnRegjistro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
			
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					ResultSet res;
					Boolean userNjejt=false;
					try {
					String query="SELECT id FROM administratoret where emri='"+txtUsername.getText()+"'";
						pst=conn.prepareStatement(query);
						res=pst.executeQuery();
						userNjejt=res.next();
						pst.close();
					} catch (SQLException c) {
						// TODO Auto-generated catch block
						c.printStackTrace();
					}

					if(txtEmri.getText().equals("")||txtMbiemri.getText().equals("")||
							txtUsername.getText().equals("")||txtFjalekalimi.getText().equals("")||
							(rdBtnM.isSelected()==false&&rdBtnF.isSelected()==false)||
							(rdBachelor.isSelected()==false&&rdMaster.isSelected()==false)||
							cmbMuaji.getSelectedItem().equals("")||cmbData.getSelectedItem().equals("")||cmbViti.getSelectedItem().equals("")||
							cmbMuajip.getSelectedItem().equals("")||cmbDatap.getSelectedItem().equals("")||cmbVitip.getSelectedItem().equals(""))
					{
						txtmes.setForeground(Color.RED);
						if(rdbAnglisht.isSelected())
								{
							txtmes.setText("Please fill the required data!!");
								}
						else
						{
							txtmes.setText("Ju lutem plotesoni te dhenat!!");
						}
					}
					else if(userNjejt==true)
					{
						if(rdbAnglisht.isSelected())
						{
							txtmes.setText("Try another username!!");
						}
						else
						{
							txtmes.setText("Username eshte i perdorur,ju lutem zevendesojeni ate!!");
						}
					}
					else
					{
						txtmes.setText("");
					String emri,mbiemri,ditlindja,dFpunes,gjinia,username,fjalekalimi,titulli,muaji,viti,data,muajip,vitip,datap;
					emri=txtEmri.getText();
					mbiemri=txtMbiemri.getText();
					muaji=String.valueOf(cmbMuaji.getSelectedItem());
					data=String.valueOf(cmbData.getSelectedItem());
					viti=String.valueOf(cmbViti.getSelectedItem());
					ditlindja=viti+"-"+muaji+"-"+data;
					muajip=String.valueOf(cmbMuajip.getSelectedItem());
					datap=String.valueOf(cmbDatap.getSelectedItem());
					vitip=String.valueOf(cmbVitip.getSelectedItem());
					dFpunes=vitip+"-"+muajip+"-"+datap;
					
					if(rdBtnM.isSelected())
					{
						gjinia = "M";
					}
					else
					{
						gjinia = "F";
					}
					if(rdBachelor.isSelected())
					{
						titulli="Bachelor";
					}
					else
					{
						titulli="Master";
					}
					String salt = HashSHA512.gjeneroSalt(); //salt qe ruhet ne db
					String pass = txtFjalekalimi.getText(); //passi perkates
					String hashedValue = HashSHA512.hash(pass, salt);
					username=txtUsername.getText();
					fjalekalimi=hashedValue;
					
					String vitiLindjesString = String.valueOf(cmbViti.getSelectedItem());
					int vitiLindjes = Integer.parseInt(vitiLindjesString);
					
					String muajiLindjesString = String.valueOf(cmbMuaji.getSelectedItem());
					int muajiLindjes = Integer.parseInt(muajiLindjesString);
					
					String dataLindjesString = String.valueOf(cmbData.getSelectedItem());
					int dataLindjes = Integer.parseInt(dataLindjesString);
					
					LocalDate birthdate = LocalDate.of(vitiLindjes, muajiLindjes, dataLindjes);
					LocalDate now = LocalDate.now();
					Period period = Period.between(birthdate, now);
					int mosha = period.getYears();
			
					String query="INSERT INTO administratoret VALUES(NULL,'"+emri+"','"+mbiemri+"','"+gjinia+"','"+mosha+"','"+username+"', '"+fjalekalimi+"', '"+salt+"', '0', '0');";
					java.sql.PreparedStatement prs;
					try{
						
						prs=conn.prepareStatement(query);
						prs.execute();
						lblok.setIcon(new ImageIcon(frmRegjistro.class.getResource("/images/ok2.jpg")));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
						
					
					
					}
				}
			}
		});
		btnRegjistro.setForeground(Color.BLACK);
		btnRegjistro.setBackground(ngjBtnRegj);
		
		
		JLabel lblMuajiDitaViti = new JLabel("Muaji               Dita                Viti          ");
		lblMuajiDitaViti.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblMuajiDitaViti.setBounds(157, 114, 212, 14);
		lblMuajiDitaViti.setForeground(new Color(204, 255, 51));
		
		contentPane.add(lblMuajiDitaViti);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255, 153, 0));
		panel.setBounds(0, 400, 369, 23);
		contentPane.add(panel);
	
		
		rdbShqip.setSelected(true);
		
		mntmShqip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbShqip.setSelected(true);
				txtmes.setText("");
				lblEmri.setText("Emri");
				lblMbiemri.setText("Mbiemri");
				lblDitlindja.setText("Ditlindja:");
				lblDataEFillimit.setText("Data e fillimit t\u00EB pun\u00EBs:");
				lblTitulli.setText("Titulli");
				lblFjalekalimi.setText("Fjal\u00EBkalimi:");
				lblGjinia.setText("Gjinia:");
				lblMuajiDitaViti.setText("Muaji               Dita                Viti          ");
				btnRegjistro.setText("Regjistro");
				setTitle("Shto administrator te ri");
				rdbAnglisht.setText("English");
				rdbShqip.setText("Albanian");
				rdbAnglisht.setText("Anglisht");
				rdbShqip.setText("Shqip");
				mnGjuha.setText("Gjuha");
				mntmAnglisht.setText("Aglisht");
				mntmShqip.setText("Shqip");
				rdBtnM.setText("Mashkull");
				rdBtnF.setText("Femër");
				gjuha=0;
			}
		});
		
		mntmAnglisht.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				count=1;
				rdbAnglisht.setSelected(true);
				txtmes.setText("");
				lblEmri.setText("Name:");
				lblMbiemri.setText("Surname");
				lblDitlindja.setText("Birthday:");
				lblDataEFillimit.setText("Employed on:");
				lblTitulli.setText("Degree:");
				lblGjinia.setText("Gender:");
				lblFjalekalimi.setText("Password:");
				lblMuajiDitaViti.setText("Month               Day                Year         ");
				btnRegjistro.setText("Enroll");
				setTitle("Add new admin");
				rdbAnglisht.setText("English");
				rdbShqip.setText("Albanian");
				mnGjuha.setText("Language");
				mntmAnglisht.setText("English");
				mntmShqip.setText("Albanian");
				rdBtnM.setText("Male");
				rdBtnF.setText("Female");
				gjuha=1;
			}
		});
		if(gjuha == 0)
		{
			rdbShqip.setSelected(true);
			txtmes.setText("");
			lblEmri.setText("Emri");
			lblMbiemri.setText("Mbiemri");
			lblDitlindja.setText("Ditlindja:");
			lblDataEFillimit.setText("Data e fillimit t\u00EB pun\u00EBs:");
			lblTitulli.setText("Titulli");
			lblFjalekalimi.setText("Fjal\u00EBkalimi:");
			lblGjinia.setText("Gjinia:");
			lblMuajiDitaViti.setText("Muaji               Dita                Viti          ");
			btnRegjistro.setText("Regjistro");
			setTitle("Shto administrator te ri");
			rdbAnglisht.setText("English");
			rdbShqip.setText("Albanian");
			rdbAnglisht.setText("Anglisht");
			rdbShqip.setText("Shqip");
			mnGjuha.setText("Gjuha");
			mntmAnglisht.setText("Aglisht");
			mntmShqip.setText("Shqip");
			rdBtnM.setText("Mashkull");
			rdBtnF.setText("Femër");
			gjuha=0;
		}
		else if(gjuha == 1)
		{
			rdbAnglisht.setSelected(true);
			txtmes.setText("");
			lblEmri.setText("Name:");
			lblMbiemri.setText("Surname");
			lblDitlindja.setText("Birthday:");
			lblDataEFillimit.setText("Employed on:");
			lblTitulli.setText("Degree:");
			lblGjinia.setText("Gender:");
			lblFjalekalimi.setText("Password:");
			lblMuajiDitaViti.setText("Month               Day                Year         ");
			btnRegjistro.setText("Enroll");
			setTitle("Add new admin");
			rdbAnglisht.setText("English");
			rdbShqip.setText("Albanian");
			mnGjuha.setText("Language");
			mntmAnglisht.setText("English");
			mntmShqip.setText("Albanian");
			rdBtnM.setText("Male");
			rdBtnF.setText("Female");
			gjuha=1;
		}
		
		ButtonGroup grupi=new ButtonGroup();
		grupi.add(rdbAnglisht);
		grupi.add(rdbShqip);
		rdbAnglisht.setEnabled(false);
		rdbShqip.setEnabled(false);
	}
	public frmRegjistro(int gjuha)
	{
		this();
		if(gjuha==0)
			mntmShqip.doClick();
		else if(gjuha==1)
			mntmAnglisht.doClick();
	}
}

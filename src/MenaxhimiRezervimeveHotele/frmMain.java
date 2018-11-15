package MenaxhimiRezervimeveHotele;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.*;

import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import com.toedter.components.JLocaleChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
@SuppressWarnings("all")
public class frmMain extends JFrame 
{

	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtAddress;
	private JTextField txtPostal;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	public JTable tblCustomers;
	public JTable tblRooms;
	public JTable tblReservations;
	private JButton btnLogOut;
	
	public JButton btnRefresh = new JButton("");
	JButton btnReception = new JButton("");
	JButton btnRooms = new JButton("");
	JButton btnReservations = new JButton("");
	JButton btnAddGuests = new JButton("");
	JButton btnViewGuests = new JButton("");
	
	JComboBox cmbCountry = new JComboBox();
	JComboBox cmbCity = new JComboBox();
	JPanel RegisteredGuests = new JPanel();
	JPanel RegisterGuests = new JPanel();
	JComboBox cmbSearchRooms = new JComboBox();
	JLabel lblCountGuests = new JLabel("");
	JLabel lblCountRooms = new JLabel("");
	JLabel lblCountReservations = new JLabel("");
	
	private double gjeresia = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private double gjatesia = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	private int GuestID;
	private int RoomNo;
	public int BookID;
	private int countGuests;
	private int countRooms;
	private int countReservations;
	private int nights;
	String Paid;
	String Available;
	
	private static String username;
	private static String emri;
	private static int gjuha=0;
	
	Connection conn = null;
	ResultSet res = null;
	PreparedStatement pst = null;
	
	private static frmNdryshoFjalekalimin objNdryshoFjalekalimin;
	private static frmInfo objInfo;
	private static frmRegjistro objRegjistro;
	
	
	private JTextField txtSearch = new JTextField();
	private JTextField txtSearchRooms = new JTextField();
	private JLabel lblMiresevini;
	private JTextField txtSearchReservations;
	private JMenuItem mntmAlbanian;
	private JMenuItem mntmEnglish;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMain frame = new frmMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void updateTableRooms()
	{
		try 
		{
			String sql = "select * from tbl_rooms";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			tblRooms.setModel(DbUtils.resultSetToTableModel(res));
			
			pst.close();
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void updateTableReservations()
	{
		try 
		{
			String sql = "select * from tbl_reservations";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			tblReservations.setModel(DbUtils.resultSetToTableModel(res));
			
			pst.close();
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void updateTableCustomers()
	{
		try 
		{	
			String sql = "SELECT g.Guest_ID,g.FirstName,g.Surname,g.Gender,c.Country,g.City,g.Address,g.PostalCode,g.Phone,g.RegistrationTime "
					+ "FROM tbl_guests g,tbl_countries c WHERE c.cId=g.CountryID";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			tblCustomers.setModel(DbUtils.resultSetToTableModel(res));
			pst.close();
			
		} catch (Exception e) 
		{
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void fillCountries()
	{
		try 
		{
			String sql = "select * from tbl_countries";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			
			while(res.next())
			{
				cmbCountry.addItem(res.getString(2));
			}
			pst.close();
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void countGuest()
	{
		try 
		{
			String sql = "select count(*) from tbl_guests";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			
			while(res.next())
			{
				countGuests = res.getInt(1);
				lblCountGuests.setText(String.valueOf(countGuests));
			}
		} catch (Exception e) 
		{
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void countRooms()
	{
		try 
		{
			String sql = "select count(*) from tbl_rooms where Available='NO'";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			
			while(res.next())
			{
				countRooms = res.getInt(1);
				lblCountRooms.setText(String.valueOf(countRooms));
			}
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	public void countReservations()
	{
		try 
		{
			String sql = "select count(*) from tbl_reservations";
			pst = conn.prepareStatement(sql);
			res = pst.executeQuery();
			
			while(res.next())
			{
				countReservations = res.getInt(1);
				lblCountReservations.setText(String.valueOf(countReservations));
			}
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	/**
	 * Create the frame.
	 */
	public frmMain() 
	{
		setUndecorated(true);
		setResizable(false);
		setBackground(new Color(255, 255, 255));
		conn = dbConnect.connectDb();
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, (int)gjeresia, (int)gjatesia);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		JPanel pnlGuests = new JPanel();
		pnlGuests.setVisible(false);
		
		JPanel pnlRooms = new JPanel();
		pnlRooms.setVisible(false);
		
		JPanel Reservations = new JPanel();
		Reservations.setBackground(new Color(255, 255, 255));
		Reservations.setVisible(false);
		Reservations.setBounds(340, 102, 1580, 638);
		contentPane.add(Reservations);
		Reservations.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		Reservations.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(184, 87, 1233, 338);
		Reservations.add(scrollPane_2);
		
		tblReservations = new JTable();
		tblReservations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				DefaultTableModel model = (DefaultTableModel)tblReservations.getModel();
				BookID = (int)model.getValueAt(tblReservations.getSelectedRow(), 0);
			}
		});
		scrollPane_2.setViewportView(tblReservations);
		
		JComboBox cmbSearchReservations = new JComboBox();
		cmbSearchReservations.setModel(new DefaultComboBoxModel(new String[] {"Search By", "Guest ID", "Room Number","Payment"}));
		cmbSearchReservations.setFont(new Font("Verdana", Font.PLAIN, 15));
		cmbSearchReservations.setBounds(184, 475, 200, 35);
		Reservations.add(cmbSearchReservations);
		
		txtSearchReservations = new JTextField();
		txtSearchReservations.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(cmbSearchReservations.getSelectedIndex()==1)
				{
					try 
					{
						String sql = "SELECT * FROM tbl_reservations WHERE Guest_ID LIKE '"+txtSearchReservations.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblReservations.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				else if(cmbSearchReservations.getSelectedIndex()==2)
				{
					try 
					{
						String sql = "SELECT * FROM tbl_reservations WHERE RoomNumber LIKE '"+txtSearchReservations.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblReservations.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				else if(cmbSearchReservations.getSelectedIndex()==3)
				{
					try 
					{
						String sql = "SELECT * FROM tbl_reservations WHERE Paid LIKE '"+txtSearchReservations.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblReservations.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
			}
		});
		txtSearchReservations.setFont(new Font("Verdana", Font.PLAIN, 15));
		txtSearchReservations.setColumns(10);
		txtSearchReservations.setBounds(410, 475, 350, 35);
		Reservations.add(txtSearchReservations);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(BookID==0)
				{
					if(gjuha == 0)
					{
						JOptionPane.showMessageDialog(null, "Zgjedhni njërin nga rezervimet");
					}
					else if(gjuha == 1)
					{
						JOptionPane.showMessageDialog(null, "Select one of reservations");
					}
				}
				else
				{
					try 
					{
						String sql = "SELECT Paid FROM tbl_reservations WHERE rId='"+BookID+"'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						while(res.next())
						{
							Paid = res.getString(1);
						}
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
					if(Paid.equals("YES"))
					{
						if(gjuha == 0)
						{
							JOptionPane.showMessageDialog(null, "Zgjedhni një rezervim që nuk është i paguar");
						}
						else if(gjuha == 1)
						{
							JOptionPane.showMessageDialog(null, "Select a reservation that is not PAID");
						}
					}
					else
					{	
						frmPayment objPay = new frmPayment(0);
						objPay.setVisible(true);
						//objPay.setAlwaysOnTop(true);
						objPay.setLocationRelativeTo(null);
						try 
						{
							String sql = "SELECT g.FirstName, g.Surname, b.CheckInDate, b.CheckOutDate, b.Nights, b.RoomNumber,r.PricePerNight, b.rId"
									+ " FROM tbl_guests g, tbl_rooms r,tbl_reservations b WHERE b.rId='"+BookID+"' AND b.guest_ID=g.Guest_ID AND b.RoomNumber=r.RoomNumber";
							pst = conn.prepareStatement(sql);
							res = pst.executeQuery();
							while(res.next())
							{
								String name = res.getString("FirstName")+" "+res.getString(2);
								objPay.txtGuest.setText(name);
								objPay.txtCheckIn.setText(res.getString(3));
								objPay.txtCheckOut.setText(res.getString(4));
								objPay.txtNights.setText(res.getString(5));
								objPay.txtRoomNumber.setText(res.getString(6));
								objPay.txtPrice.setText(res.getString(7));
								int total = res.getInt(5)*res.getInt(7);
								objPay.txtTotal.setText(String.valueOf(total));
								objPay.txtReservationID.setText(res.getString(8));
							}
							pst.close();
						} 
						catch (Exception e2) 
						{
							JOptionPane.showMessageDialog(null, e2.getMessage());
						}
					}
				}
			}
		});
		btnPay.setForeground(Color.WHITE);
		btnPay.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnPay.setBackground(new Color(255, 127, 80));
		btnPay.setBounds(1170, 475, 250, 35);
		Reservations.add(btnPay);
		
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				updateTableReservations();
				updateTableRooms();
				countRooms();
			}
		});
		btnRefresh.setIcon(new ImageIcon(frmMain.class.getResource("/images/refresh.png")));
		btnRefresh.setFocusPainted(false);
		btnRefresh.setContentAreaFilled(false);
		btnRefresh.setBorderPainted(false);
		btnRefresh.setBorder(BorderFactory.createEmptyBorder());
		btnRefresh.setBounds(1449, 87, 36, 40);
		Reservations.add(btnRefresh);
		
		pnlRooms.setBounds(340, 102, 1580, 638);
		contentPane.add(pnlRooms);
		pnlRooms.setLayout(null);
		
		JPanel Rooms = new JPanel();
		Rooms.setBounds(0, 0, 1590, 700);
		pnlRooms.add(Rooms);
		Rooms.setBackground(new Color(255, 255, 255));
		Rooms.setVisible(false);
		Rooms.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(1207, 87, 344, 338);
		Rooms.add(panel);
		
		JDateChooser txtCheckOut = new JDateChooser();
		JDateChooser txtCheckIn = new JDateChooser();
		Date noww = new Date();
		txtCheckIn.setMinSelectableDate(noww);
		txtCheckIn.setDate(noww);
		txtCheckIn.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) 
			{
				Date checkIni = txtCheckIn.getDate();
				Calendar checkOutt = Calendar.getInstance();
				checkOutt.setTime(checkIni);
				checkOutt.add(Calendar.DATE, 1);
				Date checkOuti = checkOutt.getTime();
				txtCheckOut.setMinSelectableDate(checkOuti);
			}
		});
		txtCheckIn.setDateFormatString("MMMM dd, yyyy");
		txtCheckIn.setBounds(118, 92, 200, 22);
		panel.add(txtCheckIn);
		
		JLabel label_5 = new JLabel("Check In:");
		label_5.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		label_5.setBounds(12, 92, 94, 26);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Check Out:");
		label_6.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		label_6.setBounds(12, 145, 94, 26);
		panel.add(label_6);
		
		
		txtCheckOut.setDateFormatString("MMMM dd, yyyy");
		txtCheckOut.setBounds(118, 145, 200, 22);
		panel.add(txtCheckOut);
		
		JButton btnReserve = new JButton("Reserve");
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				long night = (txtCheckOut.getDate().getTime() - txtCheckIn.getDate().getTime())/86400000;
				nights = (int)night;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String checkinni = sdf.format(txtCheckIn.getDate());
				String checkoutti = sdf.format(txtCheckOut.getDate());
				SimpleDateFormat regi = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String registr = regi.format(noww);
				
				
				if(RoomNo==0)
				{
					if(gjuha == 0)
					{
						JOptionPane.showMessageDialog(null, "Zgjedhni njërën nga dhomat", "Paralajmërim", JOptionPane.WARNING_MESSAGE);
					}
					else if(gjuha == 1)
					{
						JOptionPane.showMessageDialog(null, "Select one of Rooms", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				else
				{
					try 
					{
						String sql = "Select Available from tbl_rooms where RoomNumber='"+RoomNo+"'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						while(res.next())
						{
							Available = res.getString(1);
						}
						pst.close();
					}
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
					if(Available.equals("NO"))
					{
						if(gjuha == 0)
						{
							JOptionPane.showMessageDialog(null, "Zgjedhni një dhomë të lirë");
						}
						else if(gjuha == 1)
						{
							JOptionPane.showMessageDialog(null, "Select an AVAILABLE Room");
						}
					}
					else
					{
						if(GuestID==0)
						{
							if(gjuha == 0)
							{
								JOptionPane.showMessageDialog(null, "Zgjedhni njërin nga mysafirët", "Paralajmërim", JOptionPane.WARNING_MESSAGE);
							}
							else if(gjuha == 1)
							{
								JOptionPane.showMessageDialog(null, "Select one of Guests", "Warning", JOptionPane.WARNING_MESSAGE);
							}
							btnReception.doClick();
							btnViewGuests.doClick();
						}
						else
						{
							try 
							{
								String sql = "INSERT INTO tbl_reservations (rId,CheckInDate,CheckOutDate,Nights,RoomNumber,Guest_ID,Paid,ReservationTime)"
										+ "VALUES (default,'"+checkinni+"','"+checkoutti+"','"+nights+"','"+RoomNo+"','"+GuestID+"','NO','"+registr+"')";
								pst = conn.prepareStatement(sql);
								pst.executeUpdate();
								pst.close();
								updateTableReservations();
								countReservations();
								countRooms();
								btnReservations.doClick();
							} 
							catch (Exception e2) 
							{
								JOptionPane.showMessageDialog(null, e2.getMessage());
							}
							try 
							{
								String sql = "UPDATE tbl_rooms SET Available='NO' WHERE RoomNumber='"+RoomNo+"'";
								pst = conn.prepareStatement(sql);
								pst.executeUpdate();
								pst.close();
								updateTableRooms();
								cmbSearchRooms.setSelectedIndex(0);
								txtSearchRooms.setText("");
								txtCheckIn.setDate(noww);
								txtCheckOut.setDate(null);
							} 
							catch (Exception e2) 
							{
								JOptionPane.showMessageDialog(null, e2.getMessage());
							}
						}
					}
				}
			}
		});
		btnReserve.setForeground(new Color(255, 255, 255));
		btnReserve.setBackground(new Color(255, 127, 80));
		btnReserve.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnReserve.setBounds(34, 271, 250, 35);
		panel.add(btnReserve);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(184, 87, 981, 338);
		Rooms.add(scrollPane_1);
		scrollPane_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		
		tblRooms = new JTable();
		tblRooms.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				DefaultTableModel model = (DefaultTableModel)tblRooms.getModel();
				RoomNo = (int)model.getValueAt(tblRooms.getSelectedRow(), 0);
				
				try 
				{
					String sql = "select * from tbl_rooms where RoomNumber = '"+RoomNo+"'";
					pst = conn.prepareStatement(sql);
					res = pst.executeQuery();
					
					pst.close();
				} catch (Exception e2) 
				{
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		tblRooms.setFont(new Font("Verdana", Font.PLAIN, 15));
		scrollPane_1.setViewportView(tblRooms);
		
		
		cmbSearchRooms.setModel(new DefaultComboBoxModel(new String[] {"Search By", "Room Type", "Floor", "max Persons", "Price per Night", "Availibility", "RoomNumber"}));
		cmbSearchRooms.setFont(new Font("Verdana", Font.PLAIN, 15));
		cmbSearchRooms.setBounds(184, 475, 200, 35);
		Rooms.add(cmbSearchRooms);
		
		txtSearchRooms.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(cmbSearchRooms.getSelectedIndex()==1)
				{
					try 
					{
						String sql = "SELECT * FROM tbl_Rooms WHERE RoomType LIKE '"+txtSearchRooms.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblRooms.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				else if(cmbSearchRooms.getSelectedIndex()==2)
				{
					try 
					{
						String sql = "SELECT * FROM tbl_Rooms WHERE Floor LIKE '"+txtSearchRooms.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblRooms.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				else if(cmbSearchRooms.getSelectedIndex()==3)
				{
					try 
					{
						String sql = "SELECT * FROM tbl_Rooms WHERE maxPersons LIKE '"+txtSearchRooms.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblRooms.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				else if(cmbSearchRooms.getSelectedIndex()==4)
				{
					try 
					{
						String sql = "SELECT * FROM tbl_Rooms WHERE PricePerNight LIKE '"+txtSearchRooms.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblRooms.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				else if(cmbSearchRooms.getSelectedIndex()==5)
				{
					try 
					{
						String sql = "SELECT * FROM tbl_Rooms WHERE Available LIKE '"+txtSearchRooms.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblRooms.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				else if(cmbSearchRooms.getSelectedIndex()==6)
				{
					try 
					{
						String sql = "SELECT * FROM tbl_rooms WHERE RoomNumber LIKE '"+txtSearchRooms.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblRooms.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
			}
		});
		txtSearchRooms.setFont(new Font("Verdana", Font.PLAIN, 15));
		txtSearchRooms.setColumns(10);
		txtSearchRooms.setBounds(410, 475, 350, 35);
		Rooms.add(txtSearchRooms);
		pnlGuests.setBackground(Color.WHITE);
		pnlGuests.setBounds(340, 102, 1580, 638);
		contentPane.add(pnlGuests);
		pnlGuests.setLayout(null);
		RegisterGuests.setVisible(false);
		RegisteredGuests.setVisible(false);
		RegisteredGuests.setBounds(0, 0, 1601, 700);
		pnlGuests.add(RegisteredGuests);
		
		
		RegisteredGuests.setBackground(new Color(255, 255, 255));
		RegisteredGuests.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(184, 87, 1233, 338);
		RegisteredGuests.add(scrollPane);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		tblCustomers = new JTable();
		tblCustomers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				DefaultTableModel model = (DefaultTableModel)tblCustomers.getModel();
				GuestID = (int)model.getValueAt(tblCustomers.getSelectedRow(), 0);
			}
		});
		scrollPane.setViewportView(tblCustomers);
		tblCustomers.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 13));
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tblCustomers, popupMenu);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the customer with id '"+GuestID+"'","Warning",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION)
				{
					try 
					{
						String sql = "delete from tbl_guests where Guest_ID = '"+GuestID+"'";
						pst = conn.prepareStatement(sql);
						pst.executeUpdate();
						pst.close();
						updateTableCustomers();
						countGuest();
						GuestID=0;
					} 
					catch (Exception e2) 
					{
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
			}
		});
		popupMenu.add(mntmDelete);
		
		JComboBox cmbSearch = new JComboBox();
		cmbSearch.setFont(new Font("Verdana", Font.PLAIN, 15));
		cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"Search By", "First Name", "Surname", "Gender", "Country", "City", "ID"}));
		cmbSearch.setBounds(184, 475, 200, 35);
		RegisteredGuests.add(cmbSearch);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Verdana", Font.PLAIN, 15));
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(cmbSearch.getSelectedIndex()==1)
				{
					try 
					{
						String sql = "SELECT g.Guest_ID,g.FirstName,g.Surname,g.Gender,c.Country,g.City,g.Address,g.PostalCode,g.Phone,g.RegistrationTime "
								+ "FROM tbl_guests g,tbl_countries c WHERE c.cId=g.CountryID AND g.FirstName LIKE '"+txtSearch.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblCustomers.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				else if(cmbSearch.getSelectedIndex()==2)
				{
					try 
					{
						String sql = "SELECT g.Guest_ID,g.FirstName,g.Surname,g.Gender,c.Country,g.City,g.Address,g.PostalCode,g.Phone,g.RegistrationTime "
								+ "FROM tbl_guests g,tbl_countries c WHERE c.cId=g.CountryID AND g.Surname LIKE '"+txtSearch.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblCustomers.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				else if(cmbSearch.getSelectedIndex()==3)
				{
					try 
					{
						String sql = "SELECT g.Guest_ID,g.FirstName,g.Surname,g.Gender,c.Country,g.City,g.Address,g.PostalCode,g.Phone,g.RegistrationTime "
								+ "FROM tbl_guests g,tbl_countries c WHERE c.cId=g.CountryID AND g.Gender LIKE '"+txtSearch.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblCustomers.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				else if(cmbSearch.getSelectedIndex()==4)
				{
					try 
					{
						String sql = "SELECT g.Guest_ID,g.FirstName,g.Surname,g.Gender,c.Country,g.City,g.Address,g.PostalCode,g.Phone,g.RegistrationTime "
								+ "FROM tbl_guests g,tbl_countries c WHERE c.cId=g.CountryID AND c.Country LIKE '"+txtSearch.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblCustomers.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				else if(cmbSearch.getSelectedIndex()==5)
				{
					try 
					{
						String sql = "SELECT g.Guest_ID,g.FirstName,g.Surname,g.Gender,c.Country,g.City,g.Address,g.PostalCode,g.Phone,g.RegistrationTime "
								+ "FROM tbl_guests g,tbl_countries c WHERE c.cId=g.CountryID AND g.City LIKE '"+txtSearch.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblCustomers.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
				else if(cmbSearch.getSelectedIndex()==6)
				{
					try 
					{
						String sql = "SELECT g.Guest_ID,g.FirstName,g.Surname,g.Gender,c.Country,g.City,g.Address,g.PostalCode,g.Phone,g.RegistrationTime "
								+ "FROM tbl_guests g,tbl_countries c WHERE c.cId=g.CountryID AND g.Guest_ID LIKE '"+txtSearch.getText()+"%'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblCustomers.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
			}
		});
		txtSearch.setBounds(410, 475, 350, 35);
		RegisteredGuests.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnToRooms = new JButton("RESERVE");
		btnToRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(GuestID==0)
				{
					if(gjuha == 0)
					{
						JOptionPane.showMessageDialog(null, "Zgjedhni njërin nga mysafirët", "Paralajmërim", JOptionPane.WARNING_MESSAGE);
					}
					else if(gjuha == 0)
					{
						JOptionPane.showMessageDialog(null, "Click on one of Guests", "Warning", JOptionPane.WARNING_MESSAGE);	
					}
				}
				else
				{
					btnRooms.doClick();
					cmbSearchRooms.setSelectedIndex(5);
					txtSearchRooms.setText("YES");
					try 
					{
						String sql = "select * from tbl_rooms where available='YES'";
						pst = conn.prepareStatement(sql);
						res = pst.executeQuery();
						tblRooms.setModel(DbUtils.resultSetToTableModel(res));
						pst.close();
						
					} 
					catch (Exception e2) 
					{
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
			}
		});
		btnToRooms.setFocusPainted(false);
		btnToRooms.setForeground(new Color(255, 255, 255));
		btnToRooms.setBackground(new Color(255, 127, 80));
		btnToRooms.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnToRooms.setBounds(1167, 476, 250, 35);
		RegisteredGuests.add(btnToRooms);
		RegisterGuests.setBounds(0, 0, 1590, 700);
		pnlGuests.add(RegisterGuests);
		
		
		RegisterGuests.setBackground(new Color(255, 255, 255));
		RegisterGuests.setLayout(null);
		
				
				JLabel lblFirstName = new JLabel("First Name:");
				lblFirstName.setBounds(632, 135, 94, 26);
				RegisterGuests.add(lblFirstName);
				lblFirstName.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
				
				JLabel lblSurname = new JLabel("Surname:");
				lblSurname.setBounds(632, 174, 94, 26);
				RegisterGuests.add(lblSurname);
				lblSurname.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
				
				JLabel lblAddress = new JLabel("Address:");
				lblAddress.setBounds(632, 330, 94, 26);
				RegisterGuests.add(lblAddress);
				lblAddress.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
				
				JLabel lblPostalCode = new JLabel("Postal Code:");
				lblPostalCode.setBounds(632, 369, 94, 26);
				RegisterGuests.add(lblPostalCode);
				lblPostalCode.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
				
				JLabel lblCity = new JLabel("City:");
				lblCity.setBounds(632, 283, 94, 26);
				RegisterGuests.add(lblCity);
				lblCity.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
				
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setBounds(632, 408, 94, 26);
				RegisterGuests.add(lblEmail);
				lblEmail.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
				
				JLabel lblPhone = new JLabel("Phone:");
				lblPhone.setBounds(632, 447, 94, 26);
				RegisterGuests.add(lblPhone);
				lblPhone.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
				
				JLabel lblGender = new JLabel("Gender:");
				lblGender.setBounds(632, 213, 94, 26);
				RegisterGuests.add(lblGender);
				lblGender.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
				
				JLabel lblCountry = new JLabel("Country:");
				lblCountry.setBounds(632, 244, 94, 26);
				RegisterGuests.add(lblCountry);
				lblCountry.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
				
				txtFirstName = new JTextField();
				txtFirstName.setBounds(738, 137, 200, 22);
				RegisterGuests.add(txtFirstName);
				txtFirstName.setColumns(10);
				
				txtLastName = new JTextField();
				txtLastName.setBounds(738, 176, 200, 22);
				RegisterGuests.add(txtLastName);
				txtLastName.setColumns(10);
				
				txtAddress = new JTextField();
				txtAddress.setBounds(738, 332, 200, 22);
				RegisterGuests.add(txtAddress);
				txtAddress.setColumns(10);
				
				txtPostal = new JTextField();
				txtPostal.setBounds(738, 371, 200, 22);
				RegisterGuests.add(txtPostal);
				txtPostal.setColumns(10);
				
				txtEmail = new JTextField();
				txtEmail.setBounds(738, 410, 200, 22);
				RegisterGuests.add(txtEmail);
				txtEmail.setColumns(10);
				
				txtPhone = new JTextField();
				txtPhone.setBounds(738, 449, 200, 22);
				RegisterGuests.add(txtPhone);
				txtPhone.setColumns(10);
				
				JRadioButton rdbtnMale = new JRadioButton("Male");
				rdbtnMale.setBounds(748, 214, 90, 25);
				RegisterGuests.add(rdbtnMale);
				rdbtnMale.setSelected(true);
				rdbtnMale.setBackground(new Color(255,250,250));
				buttonGroup.add(rdbtnMale);
				rdbtnMale.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
				
				JRadioButton rdbtnFemale = new JRadioButton("Female");
				rdbtnFemale.setBounds(838, 214, 90, 25);
				RegisterGuests.add(rdbtnFemale);
				rdbtnFemale.setBackground(new Color(255,250,250));
				buttonGroup.add(rdbtnFemale);
				rdbtnFemale.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
				
				JButton btnRegister = new JButton("Register");
				btnRegister.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) 
					{
						if(e.getKeyCode()==KeyEvent.VK_ENTER)
						{
							btnRegister.doClick();
						}
					}
				});
				btnRegister.setBounds(719, 498, 97, 25);
				RegisterGuests.add(btnRegister);
				btnRegister.setForeground(new Color(255, 255, 255));
				btnRegister.setBackground(new Color(0, 0, 128));
				btnRegister.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						String zbrazet = "";
						String gender;
						if(rdbtnMale.isSelected())
						{
							gender="Male";
						}
						else
						{
							gender="Female";
						}
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
						LocalDateTime now = LocalDateTime.now();
						String date = dtf.format(now);
						if(txtFirstName.getText().equals(zbrazet) || txtLastName.getText().equals(zbrazet) || txtAddress.getText().equals(zbrazet) || txtPostal.getText().equals(zbrazet)
								|| cmbCity.getSelectedItem().equals(zbrazet) || cmbCountry.getSelectedItem().equals(zbrazet) || txtEmail.getText().equals(zbrazet) || txtPhone.getText().equals(zbrazet))
						{
							if(gjuha == 0)
							{
								JOptionPane.showMessageDialog(null, "Plotsoni të gjitha të dhënat");
							}
							else if(gjuha == 1)
							{
								JOptionPane.showMessageDialog(null, "All datas REQUIRED");
							}
						}
						else
						{
							try 
							{
								String sql = "insert into tbl_guests (Guest_ID,FirstName,Surname,Gender,CountryID,City,Address,PostalCode,Email,"
										+ "Phone,RegistrationTime) values (default,'"+txtFirstName.getText()+"','"+txtLastName.getText()+"','"+gender+"','"+cmbCountry.getSelectedIndex()+"','"+cmbCity.getSelectedItem()+"','"+txtAddress.getText()+"',"
												+ "'"+txtPostal.getText()+"','"+txtEmail.getText()+"',"
														+ "'"+txtPhone.getText()+"','"+date+"')";
								pst = conn.prepareStatement(sql);
								pst.executeUpdate();
								pst.close();
								updateTableCustomers();
								countGuest();
								int reply = JOptionPane.showConfirmDialog(null, "Do you want to add another guest?", "ADD ANOTHER", JOptionPane.YES_NO_OPTION);
								if(reply==JOptionPane.YES_OPTION)
								{
									txtFirstName.setText("");
									txtLastName.setText("");
									rdbtnMale.setSelected(true);
									cmbCountry.setSelectedIndex(0);
									cmbCity.setEnabled(false);
									txtAddress.setText("");
									txtPostal.setText("");
									txtEmail.setText("");
									txtPhone.setText("");
								}
								else
								{
									btnReception.doClick();
									btnViewGuests.doClick();
									
									txtFirstName.setText("");
									txtLastName.setText("");
									rdbtnMale.setSelected(true);
									cmbCountry.setSelectedIndex(0);
									cmbCity.setEnabled(false);
									txtAddress.setText("");
									txtPostal.setText("");
									txtEmail.setText("");
									txtPhone.setText("");
								}
								
							}
							catch (Exception e2) 
							{
								JOptionPane.showMessageDialog(null, e2.getMessage());
							}
						}
					}
				});
				btnRegister.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
				cmbCountry.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) 
					{
						cmbCity.removeAllItems();
						cmbCity.addItem("");
						try 
						{
							String sql = "select * from tbl_cities where cId = '"+cmbCountry.getSelectedIndex()+"'";
							
							pst = conn.prepareStatement(sql);
							res = pst.executeQuery();
							while(res.next())
							{
								cmbCity.addItem(res.getString(2));
							}
							pst.close();
							cmbCity.setEnabled(true);
						} 
						catch (Exception e2) 
						{
							JOptionPane.showMessageDialog(null, e2.getMessage());
						}
					}
				});
				
				
				cmbCountry.setModel(new DefaultComboBoxModel(new String[] {""}));
				cmbCountry.setBounds(738, 246, 200, 24);
				RegisterGuests.add(cmbCountry);
				
				
				cmbCity.setEnabled(false);
				cmbCity.setModel(new DefaultComboBoxModel(new String[] {""}));
				cmbCity.setBounds(738, 285, 200, 24);
				RegisterGuests.add(cmbCity);
				
				JPanel GuestsMain = new JPanel();
				GuestsMain.setBackground(new Color(255, 255, 255));
				GuestsMain.setBounds(341, 193, 864, 198);
				pnlGuests.add(GuestsMain);
				GuestsMain.setLayout(null);
				
				
				btnAddGuests.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						RegisterGuests.setVisible(true);
						GuestsMain.setVisible(false);
						RegisteredGuests.setVisible(false);
					}
				});
				btnAddGuests.setBounds(109, 0, 134, 137);
				GuestsMain.add(btnAddGuests);
				btnAddGuests.setIcon(new ImageIcon(frmMain.class.getResource("/images/plus.png")));
				btnAddGuests.setFocusPainted(false);
				btnAddGuests.setContentAreaFilled(false);
				btnAddGuests.setBorder(new EmptyBorder(0, 0, 0, 0));
				
				
				btnViewGuests.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) 
					{
						RegisteredGuests.setVisible(true);
						GuestsMain.setVisible(false);
						RegisterGuests.setVisible(false);
					}
				});
				btnViewGuests.setBounds(609, 0, 134, 137);
				GuestsMain.add(btnViewGuests);
				btnViewGuests.setIcon(new ImageIcon(frmMain.class.getResource("/images/view.png")));
				btnViewGuests.setFocusPainted(false);
				btnViewGuests.setContentAreaFilled(false);
				btnViewGuests.setBorder(new EmptyBorder(0, 0, 0, 0));
				
				JLabel lblAdd = new JLabel("ADD");
				lblAdd.setBounds(0, 141, 355, 57);
				GuestsMain.add(lblAdd);
				lblAdd.setOpaque(true);
				lblAdd.setBackground(new Color(102, 204, 51));
				lblAdd.setForeground(new Color(255, 255, 255));
				lblAdd.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 30));
				lblAdd.setHorizontalAlignment(SwingConstants.CENTER);
				
				JLabel lblView = new JLabel("VIEW");
				lblView.setBounds(509, 141, 355, 57);
				GuestsMain.add(lblView);
				lblView.setOpaque(true);
				lblView.setBackground(new Color(102, 204, 51));
				lblView.setHorizontalAlignment(SwingConstants.CENTER);
				lblView.setForeground(new Color(255, 255, 255));
				lblView.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 30));
		
		updateTableCustomers();
		
		JPanel Title = new JPanel();
		Title.setBackground(new Color(51, 0, 102));
		Title.setBounds(0, 0, 1920, 70);
		contentPane.add(Title);
		Title.setLayout(null);
		
		JButton btnClose = new JButton("");
		btnClose.setFocusPainted(false); 
		btnClose.setBorder(BorderFactory.createEmptyBorder());
		btnClose.setBorderPainted(false);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnClose.setContentAreaFilled(false);
		btnClose.setIcon(new ImageIcon(frmMain.class.getResource("/images/closee.png")));
		btnClose.setBounds(1872, 0, 36, 40);
		Title.add(btnClose);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 44, 1920, 26);
		Title.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		mnFile.add(mntmExit);
		
		JMenu mnLanguage = new JMenu("Language");
		menuBar.add(mnLanguage);
		
		mntmEnglish = new JMenuItem("English");
		mntmEnglish.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnLanguage.add(mntmEnglish);
		
		mntmAlbanian = new JMenuItem("Albanian");
		mntmAlbanian.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnLanguage.add(mntmAlbanian);
		
		JMenu mnPanels = new JMenu("Panels");
		menuBar.add(mnPanels);
		
		JMenu mnGuests = new JMenu("Guests");
		mnPanels.add(mnGuests);
		
		JMenuItem mntmGuestsMain = new JMenuItem("Guests Main");
		mntmGuestsMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				btnReception.doClick();
			}
		});
		mntmGuestsMain.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnGuests.add(mntmGuestsMain);
		
		JMenuItem mntmAddGuests = new JMenuItem("Add Guests");
		mntmAddGuests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				btnReception.doClick();
				btnAddGuests.doClick();
			}
		});
		mntmAddGuests.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnGuests.add(mntmAddGuests);
		
		JMenuItem mntmViewGuests = new JMenuItem("View Guests");
		mntmViewGuests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				btnReception.doClick();
				btnViewGuests.doClick();
			}
		});
		mntmViewGuests.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnGuests.add(mntmViewGuests);
		
		JMenuItem mntmRooms = new JMenuItem("Rooms");
		mntmRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				btnRooms.doClick();
			}
		});
		mntmRooms.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnPanels.add(mntmRooms);
		
		JMenuItem mntmReservations = new JMenuItem("Reservations");
		mntmReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				btnReservations.doClick();
			}
		});
		mntmReservations.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnPanels.add(mntmReservations);
		
		JMenu mnProfile = new JMenu("Profile");
		menuBar.add(mnProfile);
		
		JMenuItem mntmNewAdministrator = new JMenuItem("Add new administrator");
		mntmNewAdministrator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmNewAdministrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				shfaqRegjistro();
			}
		});
		mnProfile.add(mntmNewAdministrator);

		JMenuItem mntmNdryshoFjalekalimin = new JMenuItem("Change Password");
		mntmNdryshoFjalekalimin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmNdryshoFjalekalimin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				shfaqNdryshoFjalekalimin();
			}
		});
		mnProfile.add(mntmNdryshoFjalekalimin);
		
		JMenu mnHelp = new JMenu("About");
		menuBar.add(mnHelp);
		
		JMenuItem mntmInfo = new JMenuItem("Info");
		mntmInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				shfaqNdihmen();
			}
		});
		mnHelp.add(mntmInfo);
		
		JButton btnMinimize = new JButton("");
		btnMinimize.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				setState(frmMain.ICONIFIED);
			}
		});
		btnMinimize.setBounds(1838, 10, 36, 23);
		btnMinimize.setFocusPainted(false); 
		btnMinimize.setBorderPainted(false);
		btnMinimize.setBackground(new Color(51, 0, 102));
		btnMinimize.setBorder(BorderFactory.createEmptyBorder());
		btnMinimize.setIcon(new ImageIcon(frmMain.class.getResource("/images/minimize2.png")));
		Title.add(btnMinimize);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int answer=0;
				JFrame dialogFrame = new JFrame();
				dialogFrame.setAlwaysOnTop(true);
				if(gjuha==0)
					answer = JOptionPane.showOptionDialog(dialogFrame, "Po largoheni?", "D\u00EBshironi t\u00EB largoheni?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Po", "Jo"}, new Object[]{JOptionPane.YES_OPTION, JOptionPane.NO_OPTION});
				else if(gjuha==1)
					answer = JOptionPane.showOptionDialog(dialogFrame, "Are you leaving?", "Are you sure that you are leaving?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

				if(answer==JOptionPane.YES_OPTION)
				{
					Frame[] objFrms = Frame.getFrames();
					for(int i=0;i<objFrms.length;i++)
						objFrms[i].dispose();
					
					frmLogin.main(null);
				}
			}
		});
		btnLogOut.setForeground(Color.WHITE);
		btnLogOut.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnLogOut.setBackground(new Color(0, 102, 153));
		btnLogOut.setBounds(1800, 76, 112, 23);
		contentPane.add(btnLogOut);
		
		
		
		
		JPanel Menu = new JPanel();
		Menu.setBackground(new Color(0, 102, 153));
		Menu.setBounds(10, 76, 330, 1020);
		contentPane.add(Menu);
		Menu.setLayout(null);
		
		JLabel lblGuests = new JLabel("GUESTS");
		lblGuests.setOpaque(true);
		lblGuests.setBackground(new Color(0, 102, 153));
		lblGuests.setForeground(new Color(255, 255, 255));
		lblGuests.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblGuests.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuests.setBounds(12, 220, 308, 47);
		Menu.add(lblGuests);
		
		JLabel lblRooms = new JLabel("ROOMS");
		lblRooms.setOpaque(true);
		lblRooms.setBackground(new Color(0, 102, 153));
		lblRooms.setForeground(new Color(255, 255, 255));
		lblRooms.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblRooms.setHorizontalAlignment(SwingConstants.CENTER);
		lblRooms.setBounds(12, 540, 308, 40);
		Menu.add(lblRooms);
		
		JLabel lblReservations = new JLabel("RESERVATIONS");
		lblReservations.setOpaque(true);
		lblReservations.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservations.setForeground(Color.WHITE);
		lblReservations.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblReservations.setBackground(new Color(0, 102, 153));
		lblReservations.setBounds(12, 860, 308, 40);
		Menu.add(lblReservations);
		
		
		btnReception.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				pnlGuests.setVisible(true);
				GuestsMain.setVisible(true);
				RegisterGuests.setVisible(false);
				RegisteredGuests.setVisible(false);
				Reservations.setVisible(false);
				pnlRooms.setVisible(false);
				
				lblGuests.setBackground(new Color(255,127,80));
				lblRooms.setBackground(new Color(0,102,153));
				lblReservations.setBackground(new Color(0,102,153));
				
				
			}
		});
		btnReception.setFocusPainted(false);
		btnReception.setContentAreaFilled(false);
		btnReception.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnReception.setIcon(new ImageIcon(frmMain.class.getResource("/images/reception.png")));
		btnReception.setBounds(100, 80, 134, 137);
		Menu.add(btnReception);
		
		
		contentPane.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					btnReception.doClick();
				}
			}
 });
		
		
		btnRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				pnlGuests.setVisible(false);
				Reservations.setVisible(false);
				pnlRooms.setVisible(true);
				Rooms.setVisible(true);
				
				lblGuests.setBackground(new Color(0,102,153));
				lblRooms.setBackground(new Color(255,127,80));
				lblReservations.setBackground(new Color(0,102,153));
			}
		});
		btnRooms.setIcon(new ImageIcon(frmMain.class.getResource("/images/room-key.png")));
		btnRooms.setFocusPainted(false);
		btnRooms.setContentAreaFilled(false);
		btnRooms.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnRooms.setBounds(100, 400, 134, 137);
		Menu.add(btnRooms);
		
		
		btnReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				pnlGuests.setVisible(false);
				pnlRooms.setVisible(false);
				Reservations.setVisible(true);
				
				lblGuests.setBackground(new Color(0,102,153));
				lblRooms.setBackground(new Color(0,102,153));
				lblReservations.setBackground(new Color(255,127,80));
			}
		});
		btnReservations.setIcon(new ImageIcon(frmMain.class.getResource("/images/booking.png")));
		btnReservations.setFocusPainted(false);
		btnReservations.setContentAreaFilled(false);
		btnReservations.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnReservations.setBounds(100, 720, 134, 137);
		Menu.add(btnReservations);
		
		JPanel Footer = new JPanel();
		Footer.setBorder(new MatteBorder(5, 1, 1, 1, (Color) new Color(51, 204, 204)));
		Footer.setBackground(new Color(255, 255, 255));
		Footer.setBounds(340, 739, 1580, 368);
		contentPane.add(Footer);
		Footer.setLayout(null);
		
		
		JPanel FooterGuests = new JPanel();
		FooterGuests.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				btnReception.doClick();
				btnViewGuests.doClick();
			}
		});
		FooterGuests.setBorder(new LineBorder(new Color(0, 204, 204), 3, true));
		FooterGuests.setBackground(new Color(51, 204, 204));
		FooterGuests.setBounds(110, 30, 300, 200);
		Footer.add(FooterGuests);
		FooterGuests.setLayout(null);
		
		JButton btnShowGuests = new JButton("");
		btnShowGuests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				btnReception.doClick();
				btnViewGuests.doClick();
			}
		});
		btnShowGuests.setContentAreaFilled(false);
		btnShowGuests.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnShowGuests.setFocusPainted(false);
		btnShowGuests.setIcon(new ImageIcon(frmMain.class.getResource("/images/right-arrow.png")));
		btnShowGuests.setBounds(248, 147, 40, 53);
		FooterGuests.add(btnShowGuests);
		
		JLabel lblShowGuests = new JLabel("        Show");
		lblShowGuests.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				btnReception.doClick();
				btnViewGuests.doClick();
			}
		});
		lblShowGuests.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblShowGuests.setForeground(new Color(0, 204, 204));
		lblShowGuests.setBorder(new LineBorder(new Color(0, 204, 204), 2));
		lblShowGuests.setOpaque(true);
		lblShowGuests.setBounds(0, 147, 300, 53);
		FooterGuests.add(lblShowGuests);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(frmMain.class.getResource("/images/people.png")));
		label_1.setBounds(160, 38, 48, 53);
		FooterGuests.add(label_1);
		
		lblCountGuests.setForeground(Color.WHITE);
		lblCountGuests.setFont(new Font("Verdana", Font.BOLD, 40));
		lblCountGuests.setBounds(220, 38, 68, 53);
		FooterGuests.add(lblCountGuests);
		
		JLabel lblGuests_1 = new JLabel("GUESTS");
		lblGuests_1.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblGuests_1.setForeground(Color.WHITE);
		lblGuests_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuests_1.setBounds(12, 38, 156, 53);
		FooterGuests.add(lblGuests_1);
		
		JPanel FooterRooms = new JPanel();
		FooterRooms.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				btnRooms.doClick();
				try 
				{
					String sql = "SELECT * FROM tbl_rooms WHERE available='NO'";
					pst = conn.prepareStatement(sql);
					res = pst.executeQuery();
					tblRooms.setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
					cmbSearchRooms.setSelectedIndex(5);
					txtSearchRooms.setText("NO");
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		FooterRooms.setBackground(new Color(51, 204, 204));
		FooterRooms.setBounds(635, 30, 300, 200);
		Footer.add(FooterRooms);
		FooterRooms.setLayout(null);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(frmMain.class.getResource("/images/bed.png")));
		label_2.setBounds(160, 33, 48, 53);
		FooterRooms.add(label_2);
		
		JButton btnShowRooms = new JButton("");
		btnShowRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				btnRooms.doClick();
				try 
				{
					String sql = "SELECT * FROM tbl_rooms WHERE available='NO'";
					pst = conn.prepareStatement(sql);
					res = pst.executeQuery();
					tblRooms.setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
					cmbSearchRooms.setSelectedIndex(5);
					txtSearchRooms.setText("NO");
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		btnShowRooms.setIcon(new ImageIcon(frmMain.class.getResource("/images/right-arrow.png")));
		btnShowRooms.setFocusPainted(false);
		btnShowRooms.setContentAreaFilled(false);
		btnShowRooms.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnShowRooms.setBounds(248, 147, 40, 53);
		FooterRooms.add(btnShowRooms);
		
		JLabel lblShowRooms = new JLabel("        Show");
		lblShowRooms.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				btnRooms.doClick();
				try 
				{
					String sql = "SELECT * FROM tbl_rooms WHERE available='NO'";
					pst = conn.prepareStatement(sql);
					res = pst.executeQuery();
					tblRooms.setModel(DbUtils.resultSetToTableModel(res));
					pst.close();
					cmbSearchRooms.setSelectedIndex(5);
					txtSearchRooms.setText("NO");
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		lblShowRooms.setOpaque(true);
		lblShowRooms.setForeground(new Color(0, 204, 204));
		lblShowRooms.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		lblShowRooms.setBorder(new LineBorder(new Color(0, 204, 204), 2));
		lblShowRooms.setBounds(0, 147, 300, 53);
		FooterRooms.add(lblShowRooms);
		
		
		lblCountRooms.setForeground(Color.WHITE);
		lblCountRooms.setFont(new Font("Verdana", Font.BOLD, 40));
		lblCountRooms.setBounds(220, 33, 68, 53);
		FooterRooms.add(lblCountRooms);
		
		JLabel lblRooms_1 = new JLabel("ROOMS");
		lblRooms_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRooms_1.setForeground(Color.WHITE);
		lblRooms_1.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblRooms_1.setBounds(12, 33, 156, 53);
		FooterRooms.add(lblRooms_1);
		
		JPanel FooterReservations = new JPanel();
		FooterReservations.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				btnReservations.doClick();
			}
		});
		FooterReservations.setBackground(new Color(51, 204, 204));
		FooterReservations.setBounds(1160, 30, 300, 200);
		Footer.add(FooterReservations);
		FooterReservations.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(160, 26, 48, 53);
		FooterReservations.add(label);
		label.setIcon(new ImageIcon(frmMain.class.getResource("/images/calend.png")));
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				btnReservations.doClick();
			}
		});
		button_2.setIcon(new ImageIcon(frmMain.class.getResource("/images/right-arrow.png")));
		button_2.setFocusPainted(false);
		button_2.setContentAreaFilled(false);
		button_2.setBorder(new EmptyBorder(0, 0, 0, 0));
		button_2.setBounds(248, 147, 40, 53);
		FooterReservations.add(button_2);
		
		JLabel label_4 = new JLabel("        Show");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				btnReservations.doClick();
			}
		});
		label_4.setOpaque(true);
		label_4.setForeground(new Color(0, 204, 204));
		label_4.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		label_4.setBorder(new LineBorder(new Color(0, 204, 204), 2));
		label_4.setBounds(0, 147, 300, 53);
		FooterReservations.add(label_4);
		
		lblCountReservations.setForeground(Color.WHITE);
		lblCountReservations.setFont(new Font("Verdana", Font.BOLD, 40));
		lblCountReservations.setBounds(220, 26, 68, 53);
		FooterReservations.add(lblCountReservations);
		
		JLabel lblBookings = new JLabel("BOOKINGS");
		lblBookings.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookings.setForeground(Color.WHITE);
		lblBookings.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblBookings.setBounds(12, 26, 156, 53);
		FooterReservations.add(lblBookings);
		
		updateTableReservations();
		updateTableRooms();
		fillCountries();
		countGuest();
		countRooms();
		countReservations();

		if(gjuha==0)
		{
			cmbSearchReservations.setModel(new DefaultComboBoxModel(new String[] {"Kërko sipas", "ID e mysafirëve", "Numri i dhomës","Pagesa"}));
			btnPay.setText("Paguaj");
			label_5.setText("Data e fillimit:");
			label_6.setText("Data e mbarimit:");
			btnReserve.setText("Rezervo");
			btnToRooms.setText("Rezervo");
			cmbSearchRooms.setModel(new DefaultComboBoxModel(new String[] {"Kërko sipas", "Tipi i dhomës", "Kati", "Nr. max. i personave", "Cmimi për natë", "Disponueshmëria", "Numri i dhomës"}));
			mntmDelete.setText("Fshij");
			cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"Kërko sipas", "Emri", "Mbiemri", "Gjinia", "Shteti", "Qyteti", "ID"}));
			lblFirstName.setText("Emri:");
			lblSurname.setText("Mbiemri:");
			lblAddress.setText("Adresa:");
			lblPostalCode.setText("Kodi Postal:");
			lblCity.setText("Qyteti:");
			lblEmail.setText("Email:");
			lblPhone.setText("Kontakti:");
			lblGender.setText("Gjinia:");
			lblCountry.setText("Shteti:");
			rdbtnMale.setText("Mashkull");
			rdbtnFemale.setText("Femër");
			btnRegister.setText("Regjistro");
			lblAdd.setText("Shto");
			lblView.setText("Pamja");
			mnFile.setText("File");
			mntmExit.setText("Dil");
			mnLanguage.setText("Gjuha");
			mntmEnglish.setText("Anglisht");
			mntmAlbanian.setText("Shqip");
			mnPanels.setText("Panelet");
			mnGuests.setText("Mysafirët");
			mntmGuestsMain.setText("Mysafirët kryesorë");
			mntmAddGuests.setText("Shto mysafirë");
			mntmViewGuests.setText("Shfaq mysafirët");
			mntmRooms.setText("Dhomat");
			mntmReservations.setText("Rezervimet");
			lblGuests.setText("Mysafirët");
			lblRooms.setText("Dhomat");
			lblReservations.setText("Rezervimet");
			lblShowGuests.setText("        Shfaq");
			lblGuests_1.setText("Mysafirët");
			lblShowRooms.setText("        Shfaq");
			lblRooms_1.setText("Dhomat");
			label_4.setText("        Shfaq");
			lblBookings.setText("Rezervimet");
			gjuha=0;
		}
		else if(gjuha==1)
		{
			cmbSearchReservations.setModel(new DefaultComboBoxModel(new String[] {"Search By", "Guest ID", "Room Number","Payment"}));
			btnPay.setText("Pay");
			label_5.setText("Check In:");
			label_6.setText("Check Out:");
			btnReserve.setText("Reserve");
			btnToRooms.setText("Reserve");
			cmbSearchRooms.setModel(new DefaultComboBoxModel(new String[] {"Search By", "Room Type", "Floor", "max Persons", "Price per Night", "Availibility", "RoomNumber"}));
			mntmDelete.setText("Delete");
			cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"Search By", "First Name", "Surname", "Gender", "Country", "City", "ID"}));
			lblFirstName.setText("First Name:");
			lblSurname.setText("Surname:");
			lblAddress.setText("Address:");
			lblPostalCode.setText("Postal Code:");
			lblCity.setText("City:");
			lblEmail.setText("Email:");
			lblPhone.setText("Phone");
			lblGender.setText("Gender:");
			lblCountry.setText("Country:");
			rdbtnMale.setText("Male");
			rdbtnFemale.setText("Female");
			btnRegister.setText("Register");
			lblAdd.setText("ADD");
			lblView.setText("VIEW");
			mnFile.setText("File");
			mntmExit.setText("Exit");
			mnLanguage.setText("Language");
			mntmEnglish.setText("English");
			mntmAlbanian.setText("Albanian");
			mnPanels.setText("Panels");
			mnGuests.setText("Guests");
			mntmGuestsMain.setText("Guests Main");
			mntmAddGuests.setText("Add guests");
			mntmViewGuests.setText("View Guests");
			mntmRooms.setText("Rooms");
			mntmReservations.setText("Reservations");
			lblGuests.setText("GUESTS");
			lblRooms.setText("ROOMS");
			lblReservations.setText("RESERVATIONS");
			lblShowGuests.setText("        Show");
			lblGuests_1.setText("GUESTS");
			lblShowRooms.setText("        Show");
			lblRooms_1.setText("ROOMS");
			label_4.setText("        Show");
			lblBookings.setText("BOOKINGS");
			gjuha=1;
		}
	
		mntmAlbanian.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						cmbSearchReservations.setModel(new DefaultComboBoxModel(new String[] {"Kërko sipas", "ID e mysafirëve", "Numri i dhomës","Pagesa"}));
						btnPay.setText("Paguaj");
						label_5.setText("Data e fillimit:");
						label_6.setText("Data e mbarimit:");
						btnReserve.setText("Rezervo");
						btnToRooms.setText("Rezervo");
						cmbSearchRooms.setModel(new DefaultComboBoxModel(new String[] {"Kërko sipas", "Tipi i dhomës", "Kati", "Nr. max. i personave", "Cmimi për natë", "Disponueshmëria", "Numri i dhomës"}));
						mntmDelete.setText("Fshij");
						cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"Kërko sipas", "Emri", "Mbiemri", "Gjinia", "Shteti", "Qyteti", "ID"}));
						lblFirstName.setText("Emri:");
						lblSurname.setText("Mbiemri:");
						lblAddress.setText("Adresa:");
						lblPostalCode.setText("Kodi Postal:");
						lblCity.setText("Qyteti:");
						lblEmail.setText("Email:");
						lblPhone.setText("Kontakti:");
						lblGender.setText("Gjinia:");
						lblCountry.setText("Shteti:");
						rdbtnMale.setText("Mashkull");
						rdbtnFemale.setText("Femër");
						btnRegister.setText("Regjistro");
						lblAdd.setText("Shto");
						lblView.setText("Pamja");
						mnFile.setText("File");
						mntmExit.setText("Dil");
						mnLanguage.setText("Gjuha");
						mntmEnglish.setText("Anglisht");
						mntmAlbanian.setText("Shqip");
						mnPanels.setText("Panelet");
						mnGuests.setText("Mysafirët");
						mntmGuestsMain.setText("Mysafirët kryesorë");
						mntmAddGuests.setText("Shto mysafirë");
						mntmViewGuests.setText("Shfaq mysafirët");
						mntmRooms.setText("Dhomat");
						mntmReservations.setText("Rezervimet");
						lblGuests.setText("Mysafirët");
						lblRooms.setText("Dhomat");
						lblReservations.setText("Rezervimet");
						lblShowGuests.setText("        Shfaq");
						lblGuests_1.setText("Mysafirët");
						lblShowRooms.setText("        Shfaq");
						lblRooms_1.setText("Dhomat");
						label_4.setText("        Shfaq");
						lblBookings.setText("Rezervimet");
						mnProfile.setText("Profili");
						mntmNewAdministrator.setText("Shto administrator të ri");
						mntmNdryshoFjalekalimin.setText("Ndrysho Fjalëkalimin");
						mnHelp.setText("Ndihmë");
						mntmInfo.setText("Informatë");
						lblMiresevini.setText("Mirësevini: ");
						btnLogOut.setText("Dil");
						gjuha=0;
					}
				});

		mntmEnglish.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						cmbSearchReservations.setModel(new DefaultComboBoxModel(new String[] {"Search By", "Guest ID", "Room Number","Payment"}));
						btnPay.setText("Pay");
						label_5.setText("Check In:");
						label_6.setText("Check Out:");
						btnReserve.setText("Reserve");
						btnToRooms.setText("Reserve");
						cmbSearchRooms.setModel(new DefaultComboBoxModel(new String[] {"Search By", "Room Type", "Floor", "max Persons", "Price per Night", "Availibility", "RoomNumber"}));
						mntmDelete.setText("Delete");
						cmbSearch.setModel(new DefaultComboBoxModel(new String[] {"Search By", "First Name", "Surname", "Gender", "Country", "City", "ID"}));
						lblFirstName.setText("First Name:");
						lblSurname.setText("Surname:");
						lblAddress.setText("Address:");
						lblPostalCode.setText("Postal Code:");
						lblCity.setText("City:");
						lblEmail.setText("Email:");
						lblPhone.setText("Phone");
						lblGender.setText("Gender:");
						lblCountry.setText("Country:");
						rdbtnMale.setText("Male");
						rdbtnFemale.setText("Female");
						btnRegister.setText("Register");
						lblAdd.setText("ADD");
						lblView.setText("VIEW");
						mnFile.setText("File");
						mntmExit.setText("Exit");
						mnLanguage.setText("Language");
						mntmEnglish.setText("English");
						mntmAlbanian.setText("Albanian");
						mnPanels.setText("Panels");
						mnGuests.setText("Guests");
						mntmGuestsMain.setText("Guests Main");
						mntmAddGuests.setText("Add guests");
						mntmViewGuests.setText("View Guests");
						mntmRooms.setText("Rooms");
						mntmReservations.setText("Reservations");
						lblGuests.setText("GUESTS");
						lblRooms.setText("ROOMS");
						lblReservations.setText("RESERVATIONS");
						lblShowGuests.setText("        Show");
						lblGuests_1.setText("GUESTS");
						lblShowRooms.setText("        Show");
						lblRooms_1.setText("ROOMS");
						label_4.setText("        Show");
						lblBookings.setText("BOOKINGS");
						mnProfile.setText("Profile");
						mntmNewAdministrator.setText("Add new administrator");
						mntmNdryshoFjalekalimin.setText("Change Password");
						mnHelp.setText("Help");
						mntmInfo.setText("Info");
						lblMiresevini.setText("Welcome: ");
						btnLogOut.setText("Log Out");
						gjuha=1;
					}
				});
			
		
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	public frmMain(String user, int newGjuha)
	{
		this();
		username = user;
		gjuha = newGjuha;
		
		
		JLabel lblFoto = new JLabel();
		lblFoto.setIcon(new ImageIcon(frmLogin.class.getResource("/images/Login.jpg")));
		lblFoto.setBounds(370, 110, 600, 600);
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblFoto);
		
		lblMiresevini = new JLabel("");
		lblMiresevini.setOpaque(true);
		lblMiresevini.setBackground(new Color(0, 102, 153));
		lblMiresevini.setForeground(new Color(255, 255, 255));
		lblMiresevini.setFont(new Font("Verdana", Font.PLAIN, 130));
		lblMiresevini.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiresevini.setBounds(980, 110, 900, 300);
		lblMiresevini.setText("Mirësevini: ");
		contentPane.add(lblMiresevini);
		
		JLabel lblShfrytezuesi = new JLabel("");
		lblShfrytezuesi.setOpaque(true);
		lblShfrytezuesi.setBackground(new Color(0, 102, 153));
		lblShfrytezuesi.setForeground(new Color(255, 255, 255));
		lblShfrytezuesi.setFont(new Font("Verdana", Font.PLAIN, 130));
		lblShfrytezuesi.setHorizontalAlignment(SwingConstants.CENTER);
		lblShfrytezuesi.setBounds(980, 410, 900, 300);
		lblShfrytezuesi.setText(username);
		contentPane.add(lblShfrytezuesi);
		
		
		if(gjuha == 0)
		{
			mntmAlbanian.doClick();
		}
		else if(gjuha==1)
		{
			mntmEnglish.doClick();
		}
	}
	public void shfaqNdryshoFjalekalimin()
	{
		if(objNdryshoFjalekalimin==null)
		{
			objNdryshoFjalekalimin = new frmNdryshoFjalekalimin(username, gjuha);
			objNdryshoFjalekalimin.setVisible(true);
			objNdryshoFjalekalimin.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		else if(!objNdryshoFjalekalimin.isVisible())
		{
			objNdryshoFjalekalimin.setVisible(true);
			objNdryshoFjalekalimin.toFront();
		}
		else if(objNdryshoFjalekalimin.isVisible())
		{
			objNdryshoFjalekalimin.toFront();
		}
	}
	
	public void shfaqRegjistro()
	{
		if(objRegjistro==null)
		{
			objRegjistro = new frmRegjistro(gjuha);
			objRegjistro.setVisible(true);
			objRegjistro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
		else if(!objRegjistro.isVisible())
		{
			objRegjistro.setVisible(true);
			objRegjistro.toFront();
		}
		else if(objRegjistro.isVisible())
		{
			objRegjistro.toFront();
		}
	}
	
	public void shfaqNdihmen()
	{
		if(objInfo==null)
		{
			objInfo = new frmInfo(gjuha);
			objInfo.setVisible(true);
			objInfo.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			objInfo.setAlwaysOnTop(true);
		}
		else if(!objInfo.isVisible())
		{
			objInfo.setVisible(true);
			objInfo.toFront();
			objInfo.perkthe(gjuha);
		}
		else if(objInfo.isVisible())
		{
			objInfo.toFront();
			objInfo.perkthe(gjuha);
		}
	}
}

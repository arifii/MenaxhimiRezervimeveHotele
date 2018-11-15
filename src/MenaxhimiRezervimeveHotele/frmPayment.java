package MenaxhimiRezervimeveHotele;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
@SuppressWarnings("all")
public class frmPayment extends JFrame {

	
	private JPanel contentPane;
	public JTextField txtGuest;
	public JTextField txtCheckIn;
	public JTextField txtCheckOut;
	public JTextField txtNights;
	public JTextField txtPrice;
	public JTextField txtRoomNumber;
	public JTextField txtTotal;
	public JTextField txtReservationID;
	
	Connection conn = null;
	ResultSet res = null;
	PreparedStatement pst = null;
	
	private static String username;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPayment frame = new frmPayment(0);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frmPayment(int gjuha) {
		conn = dbConnect.connectDb();
		setResizable(false);
		setUndecorated(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		button.setIcon(new ImageIcon(frmPayment.class.getResource("/images/closee.png")));
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setBounds(564, 0, 36, 40);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("Guest:");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(12, 143, 180, 34);
		contentPane.add(lblNewLabel);
		
		txtGuest = new JTextField();
		txtGuest.setEditable(false);
		txtGuest.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtGuest.setBounds(200, 143, 360, 34);
		contentPane.add(txtGuest);
		txtGuest.setColumns(10);
		
		JLabel lblCheckIn = new JLabel("Check IN:");
		lblCheckIn.setForeground(Color.WHITE);
		lblCheckIn.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblCheckIn.setBounds(12, 200, 180, 34);
		contentPane.add(lblCheckIn);
		
		txtCheckIn = new JTextField();
		txtCheckIn.setEditable(false);
		txtCheckIn.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtCheckIn.setColumns(10);
		txtCheckIn.setBounds(200, 200, 360, 34);
		contentPane.add(txtCheckIn);
		
		JLabel lblCheckOut = new JLabel("Check OUT");
		lblCheckOut.setForeground(Color.WHITE);
		lblCheckOut.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblCheckOut.setBounds(12, 262, 180, 34);
		contentPane.add(lblCheckOut);
		
		txtCheckOut = new JTextField();
		txtCheckOut.setEditable(false);
		txtCheckOut.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtCheckOut.setColumns(10);
		txtCheckOut.setBounds(200, 262, 360, 34);
		contentPane.add(txtCheckOut);
		
		JLabel lblNights = new JLabel("Nights:");
		lblNights.setForeground(Color.WHITE);
		lblNights.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNights.setBounds(12, 442, 180, 34);
		contentPane.add(lblNights);
		
		txtNights = new JTextField();
		txtNights.setEditable(false);
		txtNights.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtNights.setColumns(10);
		txtNights.setBounds(200, 442, 360, 34);
		contentPane.add(txtNights);
		
		JLabel lblPricePerNight = new JLabel("Price Per Night:");
		lblPricePerNight.setForeground(Color.WHITE);
		lblPricePerNight.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblPricePerNight.setBounds(12, 385, 180, 34);
		contentPane.add(lblPricePerNight);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtPrice.setColumns(10);
		txtPrice.setBounds(200, 385, 360, 34);
		contentPane.add(txtPrice);
		
		JLabel lblRoomNumber = new JLabel("Room Number:");
		lblRoomNumber.setForeground(Color.WHITE);
		lblRoomNumber.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblRoomNumber.setBounds(12, 324, 180, 34);
		contentPane.add(lblRoomNumber);
		
		txtRoomNumber = new JTextField();
		txtRoomNumber.setEditable(false);
		txtRoomNumber.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtRoomNumber.setColumns(10);
		txtRoomNumber.setBounds(200, 324, 360, 34);
		contentPane.add(txtRoomNumber);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblTotal.setBounds(12, 502, 180, 34);
		contentPane.add(lblTotal);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtTotal.setColumns(10);
		txtTotal.setBounds(200, 502, 360, 34);
		contentPane.add(txtTotal);
		
		frmMain objMain = new frmMain(username, gjuha);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String roomN = txtRoomNumber.getText();
				String ReserveID = txtReservationID.getText();
				try 
				{
					String sql = "UPDATE tbl_reservations SET Paid='YES' WHERE rId='"+ReserveID+"'";
					pst = conn.prepareStatement(sql);
					pst.executeUpdate();
					pst.close();
					
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
				try 
				{
					String sql = "UPDATE tbl_rooms SET Available='YES' WHERE RoomNumber='"+roomN+"'";
					pst = conn.prepareStatement(sql);
					pst.executeUpdate();
					pst.close();
					
				} 
				catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				dispose();
				if(gjuha == 0)
				{
					JOptionPane.showMessageDialog(null, "Freskoni tabelën", "Informatë", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(gjuha == 1)
				{
					JOptionPane.showMessageDialog(null, "Refresh table", "Info", JOptionPane.INFORMATION_MESSAGE);	
				}
			}
		});
		btnPay.setForeground(Color.WHITE);
		btnPay.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnPay.setBackground(new Color(255, 127, 80));
		btnPay.setBounds(175, 565, 250, 35);
		contentPane.add(btnPay);
		
		JLabel lblReservationId = new JLabel("Reservation ID:");
		lblReservationId.setForeground(Color.WHITE);
		lblReservationId.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblReservationId.setBounds(12, 79, 180, 34);
		contentPane.add(lblReservationId);
		
		txtReservationID = new JTextField();
		txtReservationID.setFont(new Font("Verdana", Font.PLAIN, 20));
		txtReservationID.setEditable(false);
		txtReservationID.setColumns(10);
		txtReservationID.setBounds(200, 79, 360, 34);
		contentPane.add(txtReservationID);
		
		if(gjuha==0)
		{
			lblNewLabel.setText("Mysafiri:");
			lblCheckIn.setText("Data e fillimit:");
			lblCheckOut.setText("Data e mbarimit:");
			lblNights.setText("Netët:");
			lblPricePerNight.setText("Cmimi për natë:");
			lblRoomNumber.setText("Numri i dhomës:");
			lblTotal.setText("Totali:");
			btnPay.setText("Paguaj");
			lblReservationId.setText("ID e rezervimit:");
		}
		else if(gjuha==1)
		{
			lblNewLabel.setText("Guest:");
			lblCheckIn.setText("Check IN:");
			lblCheckOut.setText("Check OUT:");
			lblNights.setText("Nights:");
			lblPricePerNight.setText("Price Per Night:");
			lblRoomNumber.setText("Room Number:");
			lblTotal.setText("Total:");
			btnPay.setText("Pay");
			lblReservationId.setText("Reservation ID:");
		}
	}
}

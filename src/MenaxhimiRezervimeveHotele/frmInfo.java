package MenaxhimiRezervimeveHotele;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class frmInfo extends JFrame {

	private JPanel contentPane;
	private JLabel lblFaqjaEInformacioneve;
	private JLabel lblPunoi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmInfo frame = new frmInfo(0);
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
	public frmInfo() 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmInfo.class.getResource("/images/information-image.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 533, 483);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(135,206,250));
		
		JLabel lblFaqjaEInformacionit = new JLabel("");
		lblFaqjaEInformacionit.setHorizontalAlignment(SwingConstants.CENTER);
		lblFaqjaEInformacionit.setBounds(106, 67, 327, 246);
		lblFaqjaEInformacionit.setIcon(new ImageIcon(frmInfo.class.getResource("/images/Uniprishtina_logo.gif")));
		contentPane.add(lblFaqjaEInformacionit);
		
		lblFaqjaEInformacioneve = new JLabel("FAQJA E INFORMACIONEVE");
		lblFaqjaEInformacioneve.setHorizontalAlignment(SwingConstants.CENTER);
		lblFaqjaEInformacioneve.setFont(new Font("AppleGothic", Font.PLAIN, 19));
		lblFaqjaEInformacioneve.setBounds(0, 24, 517, 24);
		contentPane.add(lblFaqjaEInformacioneve);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(218, 324, 106, 117);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBackground(new Color(135, 206, 250));
		
		lblPunoi = new JLabel("Punoi: Grupi 11");
		panel.add(lblPunoi, BorderLayout.CENTER);
		lblPunoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblPunoi.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPunoi.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel lblAnetaret = new JLabel("<html>Rinor Rafuna<br/>Arif Arifi<br/>Kenan Pallusha<br/>Drinor Ahmeti<br/>Ardit Lajqi</html>");
		lblAnetaret.setBackground(Color.WHITE);
		panel.add(lblAnetaret, BorderLayout.SOUTH);
		lblAnetaret.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnetaret.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAnetaret.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	frmInfo(int newGjuha)
	{
		this();
		perkthe(newGjuha);
	}
	
	public void perkthe(int gjuha)
	{
		if(gjuha==0)
		{
			lblFaqjaEInformacioneve.setText("FAQJA E INFORMACIONEVE");
			lblPunoi.setText("Punoi: Grupi 11");
		}
		else if(gjuha==1)
		{
			lblFaqjaEInformacioneve.setText("Information Page");
			lblPunoi.setText("Worked: Group 11");
		}
	}

}

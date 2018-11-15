package MenaxhimiRezervimeveHotele;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({"unused", "serial"})
public class frmInfoRegjistro extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmInfoRegjistro frame = new frmInfoRegjistro(0);
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
	public frmInfoRegjistro(int gjuha) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmInfoRegjistro.class.getResource("/images/information-image.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Info");
		setAlwaysOnTop(true);
		setBounds(100, 100, 648, 407);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(135,206,250));
		
		JTextArea txtrKdsadk = new JTextArea();
		txtrKdsadk.setEditable(false);
		txtrKdsadk.setBackground(SystemColor.menu);
		txtrKdsadk.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 14));
		txtrKdsadk.setText("-Kjo dritare p\u00EBrdoret p\u00EBr t\u00EB regjistruar administrator\u00EBt e rinj\u00EB.\r\n-Shfryt\u00EBzuesi i cili ka t\u00EB drejtat p\u00EBr regjistrim,duhet t\u00EB plot\u00EBsoj\r\n fushat me t\u00EB dh\u00EBnat  p\u00EBrkat\u00EBse t\u00EB administratorit t\u00EB ri.\r\n-Fushat t\u00EB cilat duhet t\u00EB plot\u00EBsoj shfryt\u00EBzuesi jan\u00EB:\r\n                            Emri\r\n                            Mbiemri\r\n                            Ditlindja\r\n                            Data e filimit t\u00EB pun\u00EBs\r\n                            Gjinia \r\n                            Titulli \r\n                            Username \r\n                            Fjal\u00EBkalimi\r\n-Fushat p\u00EBr t\u00EB dh\u00EBna deri tek Username mbushen sakt me t\u00EB dh\u00EBnat e administratorit \r\nnd\u00EBrsa Username dhe Fjal\u00EBkalimi mund t\u00EB zgjidhen var\u00EBsisht nga d\u00EBshira.\r\n-Asnj\u00EBra nga fushat nuk duhet t\u00EB jet\u00EB e zbraz\u00EBt!!\r\n-Butoni Regjistro regjistron administratorin me t\u00EB dh\u00EBnat e sh\u00EBnuara n\u00EB qoft\u00EB se ato jan\u00EB valide.\r\n-N\u00EB pjes\u00EBn e poshtme tregohet gjuha momentale e p\u00EBrdorur n\u00EB dritare.");
		txtrKdsadk.setBounds(0, 31, 617, 338);
		contentPane.add(txtrKdsadk);
		txtrKdsadk.setBackground(new Color(135,206,250));
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(frmInfoRegjistro.class.getResource("/images/AL_FLAG.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrKdsadk.setText("-Kjo dritare p\u00EBrdoret p\u00EBr t\u00EB regjistruar administrator\u00EBt e rinj\u00EB.\r\n-Shfryt\u00EBzuesi i cili ka t\u00EB drejtat p\u00EBr regjistrim,duhet t\u00EB plot\u00EBsoj\r\n fushat me t\u00EB dh\u00EBnat  p\u00EBrkat\u00EBse t\u00EB administratorit t\u00EB ri.\r\n-Fushat t\u00EB cilat duhet t\u00EB plot\u00EBsoj shfryt\u00EBzuesi jan\u00EB:\r\n                            Emri\r\n                            Mbiemri\r\n                            Ditlindja\r\n                            Data e filimit t\u00EB pun\u00EBs\r\n                            Gjinia \r\n                            Titulli \r\n                            Username \r\n                            Fjal\u00EBkalimi\r\n-Fushat p\u00EBr t\u00EB dh\u00EBna deri tek Username mbushen sakt me t\u00EB dh\u00EBnat e administratorit \r\nnd\u00EBrsa Username dhe Fjal\u00EBkalimi mund t\u00EB zgjidhen var\u00EBsisht nga d\u00EBshira.\r\n-Asnj\u00EBra nga fushat nuk duhet t\u00EB jet\u00EB e zbraz\u00EBt!!\r\n-Butoni Regjistro regjistron administratorin me t\u00EB dh\u00EBnat e sh\u00EBnuara n\u00EB qoft\u00EB se ato jan\u00EB valide.\r\n-N\u00EB pjes\u00EBn e poshtme tregohet gjuha momentale e p\u00EBrdorur n\u00EB dritare.");
			}
		});
		button.setBounds(535, 0, 42, 32);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrKdsadk.setText("-This windows is used to register new administrators.\r\n-The user should fill the fields with the administrator's data if he is allowed to do this.\r\n-Fields that should be filled are these:\r\n                              Name\r\n                              Surname\r\n                             "
						+ " Birthday\r\n                              Employed on\r\n                              Gender\r\n                              Degree\r\n                              Username\r\n                              Password\r\n-The data fields,not including Username and Password,should be "
						+ "filled with new administrator's data \r\nwhereas Username and Password can be chosen by the administrator.\r\n-None of the fields should be empty!!\r\n-Register Button registers the teacher with the data written above if they're valid.\r\n-In the bottom of the window you can see the current language that is being used.\r\n");
			}
		});
		button_1.setIcon(new ImageIcon(frmInfoRegjistro.class.getResource("/images/US_FLAG.png")));
		button_1.setBounds(580, 0, 42, 32);
		contentPane.add(button_1);
		
		if(gjuha==0)
		{
			txtrKdsadk.setText("-Kjo dritare p\u00EBrdoret p\u00EBr t\u00EB regjistruar administrator\u00EBt e rinj\u00EB.\r\n-Shfryt\u00EBzuesi i cili ka t\u00EB drejtat p\u00EBr regjistrim,duhet t\u00EB plot\u00EBsoj\r\n fushat me t\u00EB dh\u00EBnat  p\u00EBrkat\u00EBse t\u00EB administratorit t\u00EB ri.\r\n-Fushat t\u00EB cilat duhet t\u00EB plot\u00EBsoj shfryt\u00EBzuesi jan\u00EB:\r\n                            Emri\r\n                            Mbiemri\r\n                            Ditlindja\r\n                            Data e filimit t\u00EB pun\u00EBs\r\n                            Gjinia \r\n                            Titulli \r\n                            Username \r\n                            Fjal\u00EBkalimi\r\n-Fushat p\u00EBr t\u00EB dh\u00EBna deri tek Username mbushen sakt me t\u00EB dh\u00EBnat e administratorit \r\nnd\u00EBrsa Username dhe Fjal\u00EBkalimi mund t\u00EB zgjidhen var\u00EBsisht nga d\u00EBshira.\r\n-Asnj\u00EBra nga fushat nuk duhet t\u00EB jet\u00EB e zbraz\u00EBt!!\r\n-Butoni Regjistro regjistron administratorin me t\u00EB dh\u00EBnat e sh\u00EBnuara n\u00EB qoft\u00EB se ato jan\u00EB valide.\r\n-N\u00EB pjes\u00EBn e poshtme tregohet gjuha momentale e p\u00EBrdorur n\u00EB dritare.");

		}
		else if(gjuha==1)
		{
			txtrKdsadk.setText("-This windows is used to register new administrators.\r\n-The user should fill the fields with the administrator's data if he is allowed to do this.\r\n-Fields that should be filled are these:\r\n                              Name\r\n                              Surname\r\n                             "
					+ " Birthday\r\n                              Employed on\r\n                              Gender\r\n                              Degree\r\n                              Username\r\n                              Password\r\n-The data fields,not including Username and Password,should be "
					+ "filled with new administrator's data \r\nwhereas Username and Password can be chosen by the administrator.\r\n-None of the fields should be empty!!\r\n-Register Button registers the teacher with the data written above if they're valid.\r\n-In the bottom of the window you can see the current language that is being used.\r\n");
		}
		
	}

}

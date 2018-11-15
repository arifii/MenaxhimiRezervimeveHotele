package MenaxhimiRezervimeveHotele;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

@SuppressWarnings("all")
public class frmNdihmaDyGjuhesia extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmNdihmaDyGjuhesia frame = new frmNdihmaDyGjuhesia(0);
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
	public frmNdihmaDyGjuhesia(int gjuha) 
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(frmNdihmaDyGjuhesia.class.getResource("/images/information-image.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Info");
		setBounds(100, 100, 508, 286);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(135,206,250));
		
		JTextArea txtrInfo = new JTextArea();
		txtrInfo.setEditable(false);
		txtrInfo.setBackground(SystemColor.menu);
		txtrInfo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 15));
		txtrInfo.setText("Kjo dritare mund\u00EBson nd\u00EBrrimin e fjal\u00EBkalimit \r\nt\u00EB shfryt\u00EBzuesit "
				+ "q\u00EB \u00EBsht\u00EB i kyqur.\r\nUsername-tregon shfryt\u00EBzuesin q\u00EB \u00EBsht\u00EB i kyqur \r\n        "
				+ "            momentalisht.\r\nFjal\u00EBkalimi aktual-K\u00EBtu duhet t\u00EB shkruhet fjal\u00EBkalimi \r\n        "
				+ "                       q\u00EB shfryt\u00EBzuesi  e ka p\u00EBrdorur deri m\u00EB tani.\r\nFjal\u00EBkalimi i "
				+ "ri-K\u00EBtu duhet t\u00EB shkruhet fjal\u00EBkalimi t\u00EB\r\n                         "
				+ "  cilin shfrytwzuesi deshiron ta perdor\u00EB n\u00EB t\u00EB ardhmen\r\nRishkruaj fjal\u00EBkalimin-K\u00EBtu "
				+ "duhet t\u00EB rishkruhet fjal\u00EBkalimi i \r\n                                        "
				+ "shkruar ne fush\u00EBn Fjal\u00EBkalimi i ri. ");
		txtrInfo.setBounds(0, 40, 481, 218);
		contentPane.add(txtrInfo);
		txtrInfo.setBackground(new Color(135,206,250));
		
		
		
		if(gjuha == 0)
		{
			setTitle("Informacion");
			txtrInfo.setText("Kjo dritare mund\u00EBson nd\u00EBrrimin e fjal\u00EBkalimit \r\nt\u00EB shfryt\u00EBzuesit "
					+ "q\u00EB \u00EBsht\u00EB i kyqur.\r\nUsername-tregon shfryt\u00EBzuesin q\u00EB \u00EBsht\u00EB i kyqur \r\n        "
					+ "            momentalisht.\r\nFjal\u00EBkalimi aktual-K\u00EBtu duhet t\u00EB shkruhet fjal\u00EBkalimi \r\n        "
					+ "                       q\u00EB shfryt\u00EBzuesi  e ka p\u00EBrdorur deri m\u00EB tani.\r\nFjal\u00EBkalimi i "
					+ "ri-K\u00EBtu duhet t\u00EB shkruhet fjal\u00EBkalimi t\u00EB\r\n                         "
					+ "  cilin shfrytwzuesi deshiron ta perdor\u00EB n\u00EB t\u00EB ardhmen\r\nRishkruaj fjal\u00EBkalimin-K\u00EBtu "
					+ "duhet t\u00EB rishkruhet fjal\u00EBkalimi i \r\n                                        "
					+ "shkruar ne fush\u00EBn Fjal\u00EBkalimi i ri. ");
		}
		else if(gjuha == 1)
		{
			setTitle("Info");
			txtrInfo.setText("This window is about changing the password of the current user.\r\nUsername-Tells the current user.\r\nCurrent password-Here the user writes the password \r\n                                 that has been using until now.\r\nNew password-Here the user writes the password \r\n                            that wants to use in the future.\r\nRewrite password- Here the user rewrites the password \r\n                                  written in the New password field.");
		}
		
	}
}

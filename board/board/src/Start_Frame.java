import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class Start_Frame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Name_Frame name;
//	private Admin_Frame admin;
	private Image background;
	private BackgroundPanel back;
	private JPanel quitPanel, helpPanel;
	private JButton quit, cont;
	private JLabel title;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private Clip clip;
	private AudioInputStream audio;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start_Frame frame = new Start_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Start_Frame(){  
		try {
			background = ImageIO.read(new File("start.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		back = new BackgroundPanel(background);
		back.setTransparentAdd(true);
		setContentPane(back);
		back.setLayout(new BorderLayout(5, 5));
		
		try{
			audio = AudioSystem.getAudioInputStream(new File("battle_theme.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audio);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch(Exception e){
			e.printStackTrace();
		}

		//vazo to label sto north tou arxikou border panel
		title = new JLabel("\u0394\u03B9\u03AC\u03BB\u03B5\u03BE\u03B5 \u03C4\u03BF \u03C1\u03CC\u03BB\u03BF \u03C3\u03BF\u03C5");
		title.setFont(new Font("Sylfaen", Font.PLAIN, 40));
		title.setForeground(Color.BLACK);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		back.add(title, BorderLayout.NORTH);
		
		//
		helpPanel = new JPanel();
		helpPanel.setBorder(new EmptyBorder(50, 100, 50, 100));
		helpPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 200));
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u0394\u03B9\u03B1\u03C7\u03B5\u03B9\u03C1\u03B9\u03C3\u03C4\u03AE\u03C2", "\u03A7\u03C1\u03AE\u03C3\u03C4\u03B7\u03C2"}));
		helpPanel.add(comboBox);
		cont = new JButton("\u03A3\u03C5\u03BD\u03AD\u03C7\u03B9\u03C3\u03B5");
		cont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clip.stop();
				if (comboBox.getSelectedItem().toString().equals("\u0394\u03B9\u03B1\u03C7\u03B5\u03B9\u03C1\u03B9\u03C3\u03C4\u03AE\u03C2")){
					System.exit(0);
					//Start_Frame.this.setVisible(false);
					//admin = new Admin_Frame();
					//admin.setVisible(true);
				}
				else
				{
					Start_Frame.this.setVisible(false);
					name = new Name_Frame();
					name.setVisible(true);
				}
			}
		});
		cont.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		helpPanel.add(cont);
		back.add(helpPanel, BorderLayout.CENTER);
		
		//vazo to quitpanel sto south tou arxikou panel
		quitPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) quitPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		quit = new JButton("\u0388\u03BE\u03BF\u03B4\u03BF\u03C2");
		quit.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		quitPanel.add(quit);
		back.add(quitPanel, BorderLayout.SOUTH);
	}
}

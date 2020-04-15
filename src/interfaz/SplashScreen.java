package interfaz;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;

public class SplashScreen extends JFrame {

	static boolean isRegistered;
	private static JProgressBar progressBar = new JProgressBar();
	private static SplashScreen execute;
	private static int count = 0;
	private static Timer timer1;
	private JLabel lblNewLabel;
	private String[] txt = new String[5];
	private String[] txt2 = new String[50];
	private JLabel lblNewLabel_1;

	public SplashScreen() {
		setUndecorated(true);
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 373, 342);

		Container container = getContentPane();
		container.setLayout(null);

		progressBar.setBackground(Color.GRAY);
		progressBar.setForeground(new Color(153, 0, 0));
		progressBar.setMaximum(45);
		progressBar.setBounds(52, 293, 260, 14);
		container.add(progressBar);
		getContentPane().add(getLblNewLabel());
		getContentPane().add(getLblNewLabel_1());
		loadProgressBar();

		setLocationRelativeTo(null);
		setVisible(true);
	

		txt[0] = "Alain Fernández";
		txt[1] = "Alvaro Riaño";
		txt[2] = "Alvaro Luzuriaga";
		txt[3] = "Jon Ander Miguel";
		txt[4] = "Iker Larrarte";

		int c = 0;
		for (int i = 0; i < 50; i++) {

			if (i < 10)
				txt2[i] = "Generando tablero....";

			else if (i >= 10 && i < 20)
				txt2[i] = "Generando casillas...";
			else if (i >= 20 && i < 30)
				txt2[i] = "Generando minas...";

			else if (i >= 30 && i < 40)
				txt2[i] = "Conectando con la base de datos...";
			else {
				if (c == 5)
					c = 0;
				txt2[i] = txt[c];
				c++;
			}
		}
		txt2[47] = "Iniciando Juego";

	}

	private void loadProgressBar() {
		ActionListener al = new ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent evt) {

				lblNewLabel.setText(txt2[count]);
				progressBar.setValue(count);
				count++;

				if (count == 49) {
					lblNewLabel.setText(txt2[47]);
					try {
						timer1.stop();
						Thread.sleep(500);
						dispose();
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Iu_Juego juego = new Iu_Juego();
					juego.crearPartidaPersonalizada(15, 10, 30);
				}

			}
		};
		timer1 = new Timer(50, al);
		timer1.start();
	}

	public static void main(String[] args) {
		execute = new SplashScreen();
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Texto para poner mientras carga");
			lblNewLabel.setBackground(Color.DARK_GRAY);
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(52, 267, 260, 14);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setBounds(52, 11, 265, 269);
			ImageIcon imageIcon = new ImageIcon("img/gif.gif");
			java.awt.Image conversion = imageIcon.getImage();
			java.awt.Image tamano = conversion.getScaledInstance(220, 180, 0);
			ImageIcon fin = new ImageIcon(tamano);
			lblNewLabel_1.setIcon(fin);
			imageIcon.setImageObserver(lblNewLabel);
		}
		return lblNewLabel_1;
	}
};
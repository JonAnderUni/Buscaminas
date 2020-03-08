package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import com.sun.javafx.tk.Toolkit;
import com.sun.prism.Image;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Iu_Partida extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JLabel lblBombas;
	private JLabel lblCarita;
	private JLabel lblTiempo;
	private JMenuItem trespor3;
	private JMenuItem diezpor10;
	private JMenuItem quincepor15;
	private JMenuItem personal;
	private JMenuItem volverAEmpezar;

	private JButton[][] tablero;
	private int fila;
	private int columna;
	private int bombas;

	private static Iu_Partida miPartida = new Iu_Partida();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Iu_Partida frame = new Iu_Partida();
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
	private Iu_Partida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 436);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel_10(), BorderLayout.NORTH);
		contentPane.add(getPanel_1_1(), BorderLayout.EAST);
		contentPane.add(getPanel_2_1(), BorderLayout.WEST);
		contentPane.add(getPanel_3_1(), BorderLayout.SOUTH);
		contentPane.add(getPanel_4_1(), BorderLayout.CENTER);
		this.setTitle("Buscaminas");
	}

	public static Iu_Partida getMiPartida() {
		return miPartida;
	}

	private JMenuBar getMenuBar_1() {

		if (menuBar == null) {

			menuBar = new JMenuBar();
			JMenu juego = new JMenu();
			juego.setText("Juego");
			JMenu ayuda = new JMenu();
			ayuda.setText("Ayuda");

			this.setJMenuBar(menuBar);
			juego.add(getTres());
			juego.add(getDiez());
			juego.add(getQuince());
			juego.add(getPersonalizada());
			juego.add(getVolver());
			menuBar.add(juego);
			menuBar.add(ayuda);
		}
		return menuBar;
	}

	private JMenuItem getTres() {
		if (trespor3 == null) {
			trespor3 = new JMenuItem();
			trespor3.setText("3x3");
			trespor3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					fila = 3;
					columna = 3;
					crearTablero(3, 3);
				}
			});
		}
		return trespor3;
	}

	private JMenuItem getDiez() {
		if (diezpor10 == null) {
			diezpor10 = new JMenuItem();
			diezpor10.setText("10x10");
			diezpor10.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fila = 10;
					columna = 10;
					crearTablero(10, 10);
				}
			});
		}
		return diezpor10;
	}

	private JMenuItem getQuince() {
		if (quincepor15 == null) {
			quincepor15 = new JMenuItem();
			quincepor15.setText("15x15");
			quincepor15.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fila = 15;
					columna = 15;
					crearTablero(15, 15);

				}
			});
		}
		return quincepor15;
	}

	private JMenuItem getPersonalizada() {
		if (personal == null) {
			personal = new JMenuItem();
			personal.setText("Personalizada");
			personal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// LLamar a un nuevo jFrame para crear la partida personalizada
					dispose();
					Iu_Personalizar.getMiPartidaPersonalizada().setVisible(true);

				}
			});

		}
		return personal;
	}

	private JMenuItem getVolver() {
		if (volverAEmpezar == null) {
			volverAEmpezar = new JMenuItem();
			volverAEmpezar.setText("Nueva Partida");
			volverAEmpezar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					crearTablero(fila, columna);
				}
			});
		}
		return volverAEmpezar;
	}

	private void actualizarTablero(JPanel panel) {
		SwingUtilities.updateComponentTreeUI(panel);
	}

	private JPanel getPanel_10() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 3, 0, 0));
			panel.add(getPanel_5());
			panel.add(getPanel_6());
			panel.add(getPanel_7());
		}
		return panel;
	}

	private JPanel getPanel_1_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
		}
		return panel_1;
	}

	private JPanel getPanel_2_1() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
		}
		return panel_2;
	}

	private JPanel getPanel_3_1() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
		}
		return panel_3;
	}

	private JPanel getPanel_4_1() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			crearTablero(12, 12);
		}
		return panel_4;
	}

	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			panel_5.add(getLblBombas());
		}
		return panel_5;
	}

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.add(getLblCarita());
		}
		return panel_6;
	}

	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			panel_7.add(getLblTiempo());

		}
		return panel_7;
	}

	private JLabel getLblBombas() {
		if (lblBombas == null) {
			lblBombas = new JLabel("bombas");
		}
		return lblBombas;
	}

	private JLabel getLblCarita() {
		if (lblCarita == null) {
			lblCarita = new JLabel("");
			ImageIcon icon = new ImageIcon("img/smiley.png");
			lblCarita.setIcon(icon);
		}
		return lblCarita;
	}

	private JLabel getLblTiempo() {
		if (lblTiempo == null) {
			lblTiempo = new JLabel("tiempo");
		}
		return lblTiempo;
	}

	public void crearTablero(int fila, int col) {

		getPanel_4_1().removeAll();

		int i = 12;
		int j = 12;
		if (fila <= 0 || col <= 0) {
			// poner mensaje de tamaño incorrecto creando por defecto;
			JOptionPane.showMessageDialog(null, "Valores incorrectos, tablero creado por valores predeterminados",
					"Advertencia", JOptionPane.WARNING_MESSAGE);
		} else {
			i = fila;
			j = col;
		}

		tablero = new JButton[i][j];
		getPanel_4_1().setLayout(new GridLayout(0, j, 0, 0));

		for (int a = 0; a < i; a++) {
			for (int e = 0; e < j; e++) {
				JButton jb = new JButton();
				jb.setBackground(Color.LIGHT_GRAY);
				jb.setBorderPainted(true);
				ImageIcon icon = new ImageIcon("img/covered.png");
				jb.setIcon(icon);

				getPanel_4_1().add(jb);
				tablero[a][e] = jb;
			}
		}

		pintarTablero();
		actualizarTablero(getPanel_4_1());
	}

	private void pintarTablero() {
		
		
		// java.awt.Image.SCALE_SMOOTH
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				ImageIcon imagen = new ImageIcon("img/covered.png");
				java.awt.Image conversion = imagen.getImage();
				java.awt.Image tamano = conversion.getScaledInstance(getWidth()/tablero[0].length,
						getHeight()/tablero.length, 0);
				ImageIcon fin = new ImageIcon(tamano);
				tablero[i][j].setIcon(fin);
			}
		}
	}

	public void crearPartidaPersonalizada(String i, String j, String b) {

		try {
			fila = Integer.parseInt(i);
			columna = Integer.parseInt(j);
			bombas = Integer.parseInt(b);
			crearTablero(fila, columna);
			actualizarTablero(getPanel_4_1());
			Iu_Personalizar.getMiPartidaPersonalizada().setVisible(false);
			// si no guarda informacion hace falta hacer un dispose
			setVisible(true);
		} catch (NumberFormatException excepcion) {
			// Para probar que lo hace bien, hay que ponerlo mejor
			// System.out.println("Por favor introduce numeros");
			JOptionPane.showMessageDialog(null, "Por favor Introduce solo números", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public int[] getDatosTablero() {
		int[] datos = new int[2];
		datos[0] = fila;
		datos[1] = columna;
		datos[2] = bombas;
		return datos;
	}
}
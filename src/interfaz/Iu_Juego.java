package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import codigo.Casilla;
import codigo.Tablero;


public class Iu_Juego extends JFrame implements Observer, ComponentListener {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JButton lblCarita;
	private JLabel lblTiempo;
	private JMenuItem facil;
	private JMenuItem medio;
	private JMenuItem dificil;
	private JMenuItem personal;
	private JMenuItem volverAEmpezar;
	private JLabel lblNewLabel;
	private JLabel btnNewButton;
	private JLabel btnNewButton_1;
	private JLabel btnNewButton_2;

	private JButton[][] tablero;
	private int fila;
	private int columna;
	private int bombas;
	private int tamanoX;
	private int tamanoY;

	private static Iu_Juego miPartida = new Iu_Juego();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Iu_Juego frame = new Iu_Juego();
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
	private Iu_Juego() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 12 * 29, (12 * 29));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel_4_1(), BorderLayout.CENTER);
		contentPane.add(getPanel_9(), BorderLayout.NORTH);
		contentPane.add(getPanel_10(), BorderLayout.SOUTH);
		contentPane.add(getPanel_11(), BorderLayout.WEST);
		contentPane.add(getPanel_12(), BorderLayout.EAST);

		// Para centrar frame en la mitad de la pantalla
		setLocation(750, 200);
		;
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Partida
		this.setTitle("Buscaminas");
		ImageIcon imagen = new ImageIcon("img/mine.png");
		this.setIconImage(imagen.getImage());
		tamanoX = 30;
		tamanoY = 30;
		bombas = (15*10)/5;
		crearTablero(15, 10);
		setJMenuBar(getMenuBar_1()); // Menu

		// Resized
		addComponentListener(this);

	}

	public static Iu_Juego getJuego() {
		return miPartida;
	}

	// Metodo para ajustar botones a la dimension de la ventana
	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		ordenar();
		redimensionarContadorBombas();

	}

	// Metodo para crear la matriz de botones y guardarla
	private void crearTablero(int filas, int columnas) {

		// Damos tamano al panel_4 donde van a estar los botones
		panel_4.setSize(((columnas) * tamanoX), ((filas) * tamanoY));

		// Damos tamano al panel principal
		setSize(panel_11.getWidth() + panel_12.getWidth() + panel_4.getWidth() + 26,
				panel_9.getHeight() + panel_10.getHeight() + panel_4.getHeight() + 80);

		contadorBombas();
		fila = filas;
		columna = columnas;
		if (bombas <= 0) bombas = (fila * columna) / 5;

		getPanel_4_1().removeAll();
		tablero = new JButton[fila][columna];

		for (int f = 0; f < fila; f++) {
			for (int c = 0; c < columna; c++) {
				JButton jb = new JButton();
				jb.setBackground(Color.LIGHT_GRAY);
				jb.setBorderPainted(true);
				tablero[f][c] = jb;
				tablero[f][c].addMouseListener(new MouseAdapter() {

					// Para aplicar el patron estate state
					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
						int j = (int) (jb.getX() / (tablero[0][1]).getX());
						int j2 = (int) (jb.getY() / (tablero[1][1].getY() - 6));
						if (arg0.getButton() == 1) {
							Tablero.getTablero().getCasilla(j2, j).clickIzq();
						} else if (arg0.getButton() == 3) {
							Tablero.getTablero().getCasilla(j2, j).clickDer();
						}
					}
				});
				getPanel_4_1().add(jb);

			} // for
		} // for

		// creamos el tablero con la matriz de casillas
		Tablero.getTablero().generarTablero(tablero.length, tablero[0].length, bombas);

		// Anadimos los observables
		anadirObserver(Tablero.getTablero().getMatriz());

		// metodo Resized, al principio todos los botones misma dimension
		ordenar();
	}

	// Metodo para ajustar los botones a la ventana
	private void ordenar() {

		int anchoTotal = panel_4.getWidth();
		int altoTotal = panel_4.getHeight();
		int tx = anchoTotal / columna;
		int ty = altoTotal / fila;

		int x = 0;
		int y = 6;
		for (int f = 0; f < tablero.length; f++) {
			for (int c = 0; c < tablero[0].length; c++) {
				tablero[f][c].setBounds(x, y, tx, ty);
				x = x + tx;
			} // for
			x = 0;
			y = y + ty;
		} // for

		pintarTablero(tx, ty);

	}

	// Metodo para pintar todos los botones
	private void pintarTablero(int tX, int tY) {
		// java.awt.Image.SCALE_SMOOTH
		ImageIcon imagen;
		

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {

				int estado = Tablero.getTablero().getCasilla(i, j).getEstado();
				int num = Tablero.getTablero().getNumPos(i, j);

				if (estado == 0) {
					if (num == -1) {
						imagen = new ImageIcon("img/mine.png");
						// Mensaje de que ha pulsado una mina, pierde la partida
					} else {
						imagen = new ImageIcon("img/" + num + ".png");
					}
				} else if (estado == 1) {
					imagen = new ImageIcon("img/flagged.png");
				} else {
					imagen = new ImageIcon("img/covered.png");
				}
				
				java.awt.Image conversion = imagen.getImage();
				java.awt.Image tamano = conversion.getScaledInstance(tablero[0][0].getWidth(), tablero[0][0].getWidth(), 0);
				ImageIcon fin = new ImageIcon(tamano);
				tablero[i][j].setIcon(fin);
			}
		}
	}

	// metodo para pintar un boton
	private void pintarPosicion(int f, int c) {

		/*
		 * Este metodo lo vamos a utilizar para el patron Observer Cada vez que una
		 * casilla cambia de estado la pintaremos con el nuevo estado
		 */

		int estado = Tablero.getTablero().getCasilla(f, c).getEstado();
		ImageIcon imagen;
		int num = Tablero.getTablero().getNumPos(f, c);

		if (estado == 0) {
			if (num == -1) {
				imagen = new ImageIcon("img/mine.png");
				// Mensaje de que ha pulsado una mina, pierde la partida
			} else {
				imagen = new ImageIcon("img/" + num + ".png");
				tablero[f][c].setEnabled(true);
			}
		} else if (estado == 1) {
			imagen = new ImageIcon("img/flagged.png");
			bombas--;
			contadorBombas();
		} else {
			imagen = new ImageIcon("img/covered.png");
			bombas++;
			contadorBombas();
		}

		java.awt.Image conversion = imagen.getImage();
		java.awt.Image tamano = conversion.getScaledInstance(tamanoX, tamanoY, 0);
		ImageIcon fin = new ImageIcon(tamano);
		tablero[f][c].setIcon(fin);
		actualizarTablero(getPanel_4_1());
	}
	

	private void actualizarTablero(JPanel panel) {
		SwingUtilities.updateComponentTreeUI(panel);
	}

//	// Para poner el numero de bombas que hay en la interfaz
	private void contadorBombas() {

		if (bombas < 0) bombas = 0;

		int centenas = bombas / 100;
		int decenas = (bombas - (centenas * 100)) / 10;
		int unidades = bombas - (centenas * 100 + decenas * 10);

		ImageIcon img1 = new ImageIcon("img/r" + decenas + ".png");
		ImageIcon img2 = new ImageIcon("img/r" + unidades + ".png");
		ImageIcon img0 = new ImageIcon("img/r" + centenas + ".png");

		java.awt.Image conversion0 = img0.getImage();
		java.awt.Image tamano0 = conversion0.getScaledInstance(20, 25, 0);
		ImageIcon fin = new ImageIcon(tamano0);

		java.awt.Image conversion1 = img1.getImage();
		java.awt.Image tamano1 = conversion1.getScaledInstance(20, 25, 0);
		ImageIcon fin1 = new ImageIcon(tamano1);

		java.awt.Image conversion2 = img2.getImage();
		java.awt.Image tamano2 = conversion2.getScaledInstance(20, 25, 0);
		ImageIcon fin2 = new ImageIcon(tamano2);

		getBtnNewButton().setIcon(fin);
		getBtnNewButton_1().setIcon(fin1);
		getBtnNewButton_2().setIcon(fin2);

		redimensionarContadorBombas();

	}

	private void redimensionarContadorBombas() {
		int width = (getPanel_9().getWidth()) / 3;
		int inicio = (50 * width) / 100;

		getBtnNewButton().setBounds(inicio - 30, 1, 20, panel_9.getHeight());
		getBtnNewButton_1().setBounds(inicio - 10, 1, 20, panel_9.getHeight());
		getBtnNewButton_2().setBounds(inicio + 10, 1, 20, panel_9.getHeight());

	}

	// Metodo para añadir los observables del patron observer
	private void anadirObserver(Observable[][] c) {
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[0].length; j++) {
				c[i][j].addObserver(this);
			}
		}
	}

	// Implementacion del patron Observer, una vez la interfaz cambia de estado,
	// pintamos la posicion correspondiente
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

		if (o instanceof Casilla) {
			Casilla casilla = (Casilla) o;
			int x = casilla.getFila();
			int y = casilla.getcolumna();
			pintarPosicion(x, y);

			if (((Casilla) o).getEstado() == 0) {
				Tablero.getTablero().destaparCasillas(x, y);
			}
		}
		
	}

	public void crearPartidaPersonalizada(int f, int c, int b) {

		bombas = b;
		crearTablero(f, c);

		Iu_Personalizar.getMiPartidaPersonalizada().setVisible(false);
		// Hemos decidido que guarde la informacion, por lo que no hay que hacer dispose
		setVisible(true);

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	private JMenuBar getMenuBar_1() {

		if (menuBar == null) {

			menuBar = new JMenuBar();
			JMenu juego = new JMenu();
			juego.setText("Juego");
			JMenu ayuda = new JMenu();
			ayuda.setText("Ayuda");

			this.setJMenuBar(menuBar);
			juego.add(getFacil());
			juego.add(getMedio());
			juego.add(getDificil());
			juego.add(getPersonalizada());
			juego.add(getVolver());
			menuBar.add(juego);
			menuBar.add(ayuda);
		}
		return menuBar;
	}

	private JMenuItem getFacil() {
		if (facil == null) {
			facil = new JMenuItem();
			facil.setText("Facil");
			facil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					bombas = 10;
					crearTablero(7, 10);

				}
			});
		}
		return facil;
	}

	private JMenuItem getMedio() {
		if (medio == null) {
			medio = new JMenuItem();
			medio.setText("Medio");
			medio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bombas = 30;
					crearTablero(10, 15);

				}
			});
		}
		return medio;
	}

	private JMenuItem getDificil() {
		if (dificil == null) {
			dificil = new JMenuItem();
			dificil.setText("Dificil");
			dificil.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					bombas = 75;
					crearTablero(12, 25);

				}
			});
		}
		return dificil;
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
					bombas = (fila * columna) / 5;
				}
			});
		}
		return volverAEmpezar;
	}

	private JPanel getPanel_4_1() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(Color.WHITE);
			panel_4.setLayout(null);
		}
		return panel_4;
	}

	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.setSize(getWidth(), 10);
			panel_9.setBorder(null);
			panel_9.setLayout(new GridLayout(0, 3, 0, 0));
			panel_9.add(getPanel_5());
			panel_9.add(getPanel_6());
			panel_9.add(getPanel_7());
		}
		return panel_9;
	}

	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setBackground(Color.LIGHT_GRAY);
			panel_5.setBorder(null);
			panel_5.setLayout(null);
			panel_5.add(getBtnNewButton());
			panel_5.add(getBtnNewButton_1());
			panel_5.add(getBtnNewButton_2());
		}
		return panel_5;
	}

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(Color.LIGHT_GRAY);
			
			panel_6.add(getLblCarita());
			
		}
		return panel_6;
	}

	private JButton getLblCarita() {
		if (lblCarita == null) {
			lblCarita = new JButton("");
			lblCarita.setBackground(Color.LIGHT_GRAY);
			ImageIcon icon = new ImageIcon("img/smiley.png");
			lblCarita.setIcon(icon);
			lblCarita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//creamos una nueva Partida
					crearTablero(fila, columna);
				}
			});
			
		}
		return lblCarita;
	}

	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setBackground(Color.LIGHT_GRAY);
			panel_7.setBorder(null);
			panel_7.setLayout(new GridLayout(0, 2, 0, 0));
			panel_7.add(getLblTiempo());
			panel_7.add(getLblNewLabel());

		}
		return panel_7;
	}

	private JLabel getLblTiempo() {
		if (lblTiempo == null) {
			lblTiempo = new JLabel("m");
			lblTiempo.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblTiempo;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("s");
		}
		return lblNewLabel;
	}

	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			panel_10.setSize(getWidth(), 10);
		}
		return panel_10;
	}

	private JPanel getPanel_11() {
		if (panel_11 == null) {
			panel_11 = new JPanel();
			panel_11.setSize(10, getHeight());
		}
		return panel_11;
	}

	private JPanel getPanel_12() {
		if (panel_12 == null) {
			panel_12 = new JPanel();
			panel_12.setSize(10, getHeight());
		}
		return panel_12;
	}

	private JLabel getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JLabel("");
			btnNewButton.setBackground(Color.LIGHT_GRAY);
			btnNewButton.setBounds(3, 1, 14, 23);
			btnNewButton.setEnabled(true);

		}
		return btnNewButton;
	}

	private JLabel getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JLabel("");
			btnNewButton_1.setBackground(Color.LIGHT_GRAY);
			btnNewButton_1.setBounds(17, 1, 14, 23);
			btnNewButton_1.setEnabled(true);

		}
		return btnNewButton_1;
	}

	private JLabel getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JLabel("");
			btnNewButton_2.setBackground(Color.LIGHT_GRAY);
			btnNewButton_2.setBounds(31, 1, 14, 23);
			btnNewButton_2.setEnabled(true);
		}
		return btnNewButton_2;
	}
}
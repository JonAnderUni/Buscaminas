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
import javax.swing.border.LineBorder;

import codigo.Casilla;
import codigo.Tablero;

public class Iu_Juego extends JFrame implements Observer, ComponentListener {

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
	private JLabel lblCarita;
	private JLabel lblTiempo;
	private JMenuItem facil;
	private JMenuItem medio;
	private JMenuItem dificil;
	private JMenuItem personal;
	private JMenuItem volverAEmpezar;
	private JButton[][] tablero;
	private int fila;
	private int columna;
	private int bombas;
	private JLabel lblDec;
	private JLabel lblUd;
	private JLabel lblNewLabel;

	private static Iu_Juego miPartida = new Iu_Juego();
	private JPanel panel_8;
	private int tamanoX;
	private int tamanoY;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;

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

		// Partida
		this.setTitle("Buscaminas");
		tamanoX = 30;
		tamanoY = 30;
		crearTablero(30, 30);
		setJMenuBar(getMenuBar_1()); //Menu

		
		//Resized
		addComponentListener(this);

	}

	// Metodo para ajustar botones a la dimension de la ventana
	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		ordenar();

	}

	// Metodo para crear la matriz de botones y guardarla
	private void crearTablero(int filas, int columnas) {

		fila = filas;
		columna = columnas;
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
						int j2 = (int) (jb.getY()/(tablero[1][1].getY()-6));
						if (arg0.getButton() == 1) {
							Tablero.getTablero().getCasilla(j2, j).clickIzq();
							//pintarPosicion(j2, j);
						} else if (arg0.getButton() == 3) {
							Tablero.getTablero().getCasilla(j2, j).clickDer();
							//pintarPosicion(j2, j);
						}
					}
				});
				getPanel_4_1().add(jb);

			} // for
		} // for

		// creamos el tablero con la matriz de casillas
		Tablero.getTablero().generarTablero(tablero.length, tablero[0].length, tablero[0].length * 3);
		
		contadorBombas();
		//Anadimos los observables
		anadirObservables(Tablero.getTablero().getMatriz());

		/*
		 * Establecemos un tamano a la ventana para que las casillas sean de 29x29 si
		 * modificamos el tamano de la ventana cambiamos el tamano predeterminado esto
		 * ultimo lo hacemos con el metodo ordenar
		 */

		panel_4.setSize(((columnas) * tamanoX), ((filas) * tamanoY));
		setSize(panel_11.getWidth() + panel_12.getWidth() + panel_4.getWidth(), panel_9.getHeight() + panel_10.getHeight() + panel_4.getHeight() );
		

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
				// pintarPosicion(f, c);
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
		ImageIcon imagen = new ImageIcon("img/covered.png");
		java.awt.Image conversion = imagen.getImage();
		java.awt.Image tamano = conversion.getScaledInstance(tablero[0][0].getWidth(), tablero[0][0].getWidth(), 0);
		ImageIcon fin = new ImageIcon(tamano);
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
			
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
			}
		} else if (estado == 1) {
			imagen = new ImageIcon("img/flagged.png");
		} else {
			imagen = new ImageIcon("img/covered.png");
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
		getPanel_5().removeAll();
		int numM = bombas;

		// Si se ha introducido mal el numero de bombas
		// bombas <= 0
		if (bombas <= 0) {
			numM = bombas * columna;
			bombas = numM;
		}
		int aa = numM / 10;
		int bb = numM % 10;
		ImageIcon img1 = new ImageIcon("img/r" + aa + ".png");
		ImageIcon img2 = new ImageIcon("img/r" + bb + ".png");
		getLblDec().setIcon(img1);
		getLblUd().setIcon(img2);
		getPanel_5().add(getLblDec());
		getPanel_5().add(getLblUd());
	}

	// Metodo para añadir los observables del patron observer
	private void anadirObservables(Observable[][] c) {
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
		}
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
					crearTablero(15,10);
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
					crearTablero(20,15);
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
					crearTablero(25,25);

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
					crearTablero(fila,columna);
				}
			});
		}
		return volverAEmpezar;
	}


	private JPanel getPanel_4_1() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(Color.GREEN);
			panel_4.setLayout(null);
		}
		return panel_4;
	}

	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.setSize(getWidth(), 10);
			panel_9.setBorder(new LineBorder(new Color(0, 0, 0), 2));
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
			panel_5.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			panel_5.setLayout(new GridLayout(0, 2, 0, 0));
			panel_5.add(getLblDec());
			panel_5.add(getLblUd());
		}
		return panel_5;
	}
	
	private JLabel getLblDec() {
		if (lblDec == null) {
			lblDec = new JLabel("");
			lblDec.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblDec;
	}
	private JLabel getLblUd() {
		if (lblUd == null) {
			lblUd = new JLabel("");
			lblUd.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblUd;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(Color.LIGHT_GRAY);
			panel_6.add(getLblCarita());
		}
		return panel_6;
	}
	private JLabel getLblCarita() {
		if (lblCarita == null) {
			lblCarita = new JLabel("");
			ImageIcon icon = new ImageIcon("img/smiley.png");
			lblCarita.setIcon(icon);
		}
		return lblCarita;
	}
	
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
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
}
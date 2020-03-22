package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import codigo.Casilla;
import codigo.Tablero;

import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

public class Iu_Partida extends JFrame implements Observer,ComponentListener {

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

	private static Iu_Partida miPartida = new Iu_Partida();
	private int tamanoX = 25;
	private int tamanoY = 25;
	private JLabel lblAlain;


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
		
		setSize(((columna) * (tamanoX)), ((fila) * tamanoY));
		
		
		//Para hacerlo redimensionable
		addComponentListener(this);

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
					fila = 7;
					columna = 10;
					bombas = 30;
					crearTablero();
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
					fila = 10;
					columna = 15;
					bombas = 15 * 3;
					crearTablero();
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
					fila = 12;
					columna = 25;
					bombas = 25 * 3;
					crearTablero();

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
					crearTablero();
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
			panel.setBackground(Color.LIGHT_GRAY);
			panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
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
			panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return panel_2;
	}

	private JPanel getPanel_3_1() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			panel_3.add(getLblAlain());
		}
		return panel_3;
	}

	private JPanel getPanel_4_1() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(null);
			fila = 10;
			columna = 12;
			crearTablero();

		}
		return panel_4;
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

	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(Color.LIGHT_GRAY);
			panel_6.add(getLblCarita());
		}
		return panel_6;
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
			lblTiempo = new JLabel("m");
			lblTiempo.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblTiempo;
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

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("s");
		}
		return lblNewLabel;
	}

	/*
	 * Metodos que utilizamos en la clase Iu_Partida
	 * -----------------------------------------------------------------------------
	 * --------------------------------------------------------
	 */

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

	// Utilizamos este metodo para comunicar la interfaz con Tablero.
	// Generamsos la matriz de botones
	// Una vez generada la matriz creamos el tablero de casillas.
	private void crearTablero() {

		

		getPanel_4_1().removeAll();
		tablero = new JButton[fila][columna];
		
		
		for (int f = 0; f < fila; f++) {
			for (int c = 0; c < columna; c++) {
				JButton jb = new JButton();
				jb.setBackground(Color.LIGHT_GRAY);
				jb.setBorderPainted(true);
				tablero[f][c] = jb;
				tablero[f][c].addMouseListener(new MouseAdapter() {
					
					//Para aplicar el patron estate state
					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub

						int j = (int) (jb.getX() / (getWidth() / columna));
						int j2 = (int) (jb.getY() / (getHeight() / fila));
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
				
			}
			
		}
		
		
		/*Establecemos un tamano a la ventana para que las casillas sean de 29x29
		 * si modificamos el tamano de la ventana cambiamos el tamano predeterminado
		 * esto ultimo lo hacemos con el metodo ordenar
		 * */
		setSize(((columna + 2)*tamanoX ) + 300,(fila + 3)*tamanoY);
		getPanel_4_1().setSize(((columna+2) * (tamanoX)) + 300, ((fila+3) * tamanoY));
		
		ordenar();
		Tablero.getTablero().generarTablero(tablero.length, tablero[0].length, tablero[0].length * 3);
		contadorBombas();
		//Anadimos los observables
		anadirObservables(Tablero.getTablero().getMatriz());
		pintarTablero(tamanoX, tamanoY);
		actualizarTablero(getPanel_4_1());
	}
	
	
	// metodo para hacer la interfaz responsive
		private void ordenar() {
			//falta impelementar este metodo
				
			int anchoTotal = getWidth();
			int altoTotal = getHeight();	
			int tx = anchoTotal / columna;
			int ty = altoTotal / fila;
			
			int x = 0;
			int y = 15;
			for(int f = 0; f<tablero.length;f++) {
				for(int c = 0; c<tablero[0].length;c++) {
					tablero[f][c].setBounds(x, y,tx, ty);
					//pintarPosicion(f, c);
					x = x + tx;
				}
				x = 0;
				y = y + ty;
			}
			pintarTablero(tx, ty);
			
		}
		
		

	// hace los mismo que pintarposicion, pero inicializamos el tablero a casillas
	// cerradas y las pintamos como tal
	private void pintarTablero(int tX, int tY) {
		// java.awt.Image.SCALE_SMOOTH
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[0].length; j++) {
				ImageIcon imagen = new ImageIcon("img/covered.png");
				java.awt.Image conversion = imagen.getImage();
				java.awt.Image tamano = conversion.getScaledInstance(tX, tY,0);
				ImageIcon fin = new ImageIcon(tamano);
				tablero[i][j].setIcon(fin);
					

			}
		}
		
	}

	// lo utilizamos en el patron observer
	// si una casilla ha sido cambiada de estado lo cambiamos de acorde a este
	private void pintarPosicion(int f, int c) {

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

	// Metodo para comunicar la interfaz Iu_personalizada con la interfaz Iu_partida
	public void crearPartidaPersonalizada(int f, int c, int b) {

		fila = f;
		columna = c;
		bombas = b;
		crearTablero();

		Iu_Personalizar.getMiPartidaPersonalizada().setVisible(false);
		// Hemos decidido que guarde la informacion, por lo que no hay que hacer dispose
		setVisible(true);

	}

	// Para poner el numero de bombas que hay en la interfaz
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
	
	
	//Metodo para añadir los observables del patron observer
	private void anadirObservables(Observable[][] c) {
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[0].length; j++) {
				c[i][j].addObserver(this);
			}
		}
	}

	
	@Override
	public void componentResized(ComponentEvent e) {
		ordenar();
	}
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub	
	}


	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	private JLabel getLblAlain() {
		if (lblAlain == null) {
			lblAlain = new JLabel("Alain");
		}
		return lblAlain;
	}
}
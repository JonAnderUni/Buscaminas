package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

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
		setBounds(100, 100, 12 * 29, (12* 29));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel_4_1(), BorderLayout.CENTER);
		contentPane.add(getPanel_9(), BorderLayout.NORTH);
		contentPane.add(getPanel_10(), BorderLayout.SOUTH);
		contentPane.add(getPanel_11(), BorderLayout.WEST);
		contentPane.add(getPanel_12(), BorderLayout.EAST);
		
		
		
		//Partida
		this.setTitle("Buscaminas");
		tamanoX = 30;
		tamanoY = 30;
		crearTablero(30,30);
		addComponentListener(this);

		
	}

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

		/*
		 * Establecemos un tamano a la ventana para que las casillas sean de 29x29 si
		 * modificamos el tamano de la ventana cambiamos el tamano predeterminado esto
		 * ultimo lo hacemos con el metodo ordenar
		 */
		
		setSize(((columnas )*tamanoX), ((filas )*tamanoY));
		ordenar();
		//pintarTablero(29,29);
	}
	
	private void ordenar() {
		//falta impelementar este metodo
			
		int anchoTotal = panel_4.getWidth();
		int altoTotal = panel_4.getHeight();	
		int tx = anchoTotal / columna;
		int ty = altoTotal / fila;
		
		int x = 0;
		int y = 0;
		for(int f = 0; f<tablero.length;f++) {
			for(int c = 0; c<tablero[0].length;c++) {
				tablero[f][c].setBounds(x, y,tx, ty);
				//pintarPosicion(f, c);
				x = x + tx;
			}
			x = 0;
			y = y + ty;
		}
		//pintarTablero(tx, ty);
		
	}
	
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
	
	private void actualizarTablero(JPanel panel) {
		SwingUtilities.updateComponentTreeUI(panel);
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
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		ordenar();

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	private JPanel getPanel_4_1() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(null);
		}
		return panel_4;
	}
	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
			panel_9.setSize(getWidth(),10);
		}
		return panel_9;
	}
	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
			panel_10.setSize(getWidth(),10);
		}
		return panel_10;
	}
	private JPanel getPanel_11() {
		if (panel_11 == null) {
			panel_11 = new JPanel();
			panel_11.setSize(10,getHeight());
		}
		return panel_11;
	}
	private JPanel getPanel_12() {
		if (panel_12 == null) {
			panel_12 = new JPanel();
			panel_12.setSize(10,getHeight());
		}
		return panel_12;
	}
}

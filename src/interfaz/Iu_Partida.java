package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.border.LineBorder;

public class Iu_Partida extends JFrame {

	private JPanel contentPane;
	
	private JButton[][] tablero;
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
	private JPanel panel_8;
	private JButton btnNewButton;


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
	public Iu_Partida() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 542);
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
	}
	
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
		}
		return menuBar;
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
			panel_3.add(getBtnNewButton());
			panel_3.add(getPanel_8());
		}
		return panel_3;
	}
	private JPanel getPanel_4_1() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(new GridLayout(1, 0, 0, 0));
			crearTablero(-1, -1);
		}
		return panel_4;
	}
	
	public void crearTablero(int fila, int col) {
		
		
		int i = 6;
		int j= 9;
		if(fila<=0 || col<= 0) {			
			//poner mensaje de tamaño incorrecto creando por defecto;
		}else {
			i = fila;
			j = col;
		}
		
		tablero = new JButton[i][j];
		
		getPanel_4_1().setLayout(new GridLayout(0, j, 0, 0));
		
		for(int a= 0; a<i;a++) {
			for(int e= 0; e<j;e++) {
				JButton jb = new JButton();
				jb.setBackground(Color.LIGHT_GRAY);
				jb.setBorderPainted(true);
			
				getPanel_4_1().add(jb);
				tablero[a][e] = jb;
			}
		}
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
			lblCarita = new JLabel("carita");
		}
		return lblCarita;
	}
	private JLabel getLblTiempo() {
		if (lblTiempo == null) {
			lblTiempo = new JLabel("tiempo");
		}
		return lblTiempo;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
		}
		return panel_8;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("New button");
		}
		return btnNewButton;
	}
}

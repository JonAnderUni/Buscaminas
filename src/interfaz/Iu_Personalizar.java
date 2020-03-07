package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Iu_Personalizar extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JLabel lblFilas;
	private JTextField textField;
	private JLabel lblColumnas;
	private JTextField textField_1;
	private JLabel lblBombas;
	private JTextField textField_2;
	private JButton btnOk;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	
	private static Iu_Personalizar miPartidaPersonalizada = new Iu_Personalizar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Iu_Personalizar frame = new Iu_Personalizar();
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
	private Iu_Personalizar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getPanel_1(), BorderLayout.EAST);
		contentPane.add(getPanel_2(), BorderLayout.SOUTH);
		contentPane.add(getPanel_3(), BorderLayout.WEST);
		contentPane.add(getPanel_4(), BorderLayout.CENTER);
	}
	
	public static Iu_Personalizar getMiPartidaPersonalizada() {
		return miPartidaPersonalizada;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
		}
		return panel_3;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setLayout(new GridLayout(0, 1, 0, 0));
			panel_4.add(getPanel_9());
			panel_4.add(getPanel_5());
			panel_4.add(getPanel_6());
			panel_4.add(getPanel_7());
			panel_4.add(getPanel_10());
		}
		return panel_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
		}
		return panel_5;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setLayout(new GridLayout(0, 4, 0, 0));
			panel_6.add(getLblFilas());
			panel_6.add(getTextField());
			panel_6.add(getLblColumnas());
			panel_6.add(getTextField_1());
			panel_6.add(getLblBombas());
			panel_6.add(getTextField_2());
			panel_6.add(getPanel_8());
			panel_6.add(getBtnOk());
		}
		return panel_6;
	}
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
		}
		return panel_7;
	}
	private JLabel getLblFilas() {
		if (lblFilas == null) {
			lblFilas = new JLabel("Filas");
			lblFilas.setHorizontalAlignment(SwingConstants.CENTER);
			lblFilas.setVerticalAlignment(SwingConstants.CENTER);
		}
		return lblFilas;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	private JLabel getLblColumnas() {
		if (lblColumnas == null) {
			lblColumnas = new JLabel("Columnas");
			lblColumnas.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblColumnas;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setColumns(10);
		}
		return textField_1;
	}
	private JLabel getLblBombas() {
		if (lblBombas == null) {
			lblBombas = new JLabel("Bombas");
			lblBombas.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblBombas;
	}
	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setColumns(10);
		}
		return textField_2;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("Ok");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String filas = getTextField().getText();
					String columnas = getTextField_1().getText();
					String bombas = getTextField_2().getText();
					Iu_Partida.getMiPartida().crearPartidaPersonalizada(filas, columnas, bombas);
					setVisible(false);
					dispose();
				}
			});
		}
		return btnOk;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
		}
		return panel_8;
	}
	private JPanel getPanel_9() {
		if (panel_9 == null) {
			panel_9 = new JPanel();
		}
		return panel_9;
	}
	private JPanel getPanel_10() {
		if (panel_10 == null) {
			panel_10 = new JPanel();
		}
		return panel_10;
	}
}

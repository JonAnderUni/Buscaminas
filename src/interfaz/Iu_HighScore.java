package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import codigo.CustomTableModel;
import codigo.Puntuacion;

import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.SystemColor;

public class Iu_HighScore extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Iu_HighScore dialog = new Iu_HighScore();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Iu_HighScore() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				
				table = new JTable(this.obtenerTabla(Puntuacion.miPuntuacion().leerdatosFichero()));
				table.setPreferredScrollableViewportSize(new Dimension(450, 300));
				table.setGridColor(Color.BLACK);
				table.setBackground(getContentPane().getBackground());
//				JScrollPane JS = new JScrollPane(table);
//				JS.setPreferredSize(new Dimension(400, 150));
				panel.add(table);

			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new GridLayout(0, 3, 0, 0));
			{
				JLabel lblNewLabel = new JLabel("Dificultad");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre");
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Puntuacion");
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(lblNewLabel_2);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salir");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
	}

	private CustomTableModel obtenerTabla(String[] tabla) {
		CustomTableModel nm = new CustomTableModel();
		nm.addColumn("Nivel");
		nm.addColumn("Nombre");
		nm.addColumn("Puntuacion");
		int cont = 0; // Para mostrar solo los 10 primeros

		for (String datos : tabla) {

			if (cont < 10) {
				String[] a = datos.split("\\s+--->\\s+");
				String[] row = { a[0], a[1], a[2] };
				nm.addRow(row);
			} else {
				break;
			}
			cont++;
		}

		return nm;
	}
}

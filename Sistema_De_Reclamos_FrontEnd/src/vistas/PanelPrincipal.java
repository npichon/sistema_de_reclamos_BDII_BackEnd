package vistas;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class PanelPrincipal extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelPrincipal() {
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 629, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 424, Short.MAX_VALUE)
		);
		setLayout(groupLayout);
		//this.setSize(629,424);
	}

	public void paintComponent(Graphics g) {
		Dimension tamanio = getSize();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource(
				"/Archivos/fondo3.jpg"));
		g.drawImage(imagenFondo.getImage(), 0, 0, tamanio.width,
				tamanio.height, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}

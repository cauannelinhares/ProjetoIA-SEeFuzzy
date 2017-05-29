import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class telaResultado extends JFrame {

	private JPanel contentPane;
	private final JTextArea txtrA = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String s) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaResultado frame = new telaResultado(s);
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
	public telaResultado(String res) {
		setTitle("RESULTADO");
		getContentPane().setLayout(null);
		txtrA.setBounds(0, 0, 376, 363);
		getContentPane().add(txtrA);
		setTitle("RESULTADO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 362, 309);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		panel.add(textArea);
		textArea.setBounds(0, 0, 335, 306);
		textArea.setText(res);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollBar_1.setBounds(335, 0, 27, 306);
		panel.add(scrollBar_1);
		
		JButton btnNewButton = new JButton("Exportar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				File file = new File("output.txt");
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(new FileWriter(file));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Arquivo Não Exportado");
				}
				try {
					writer.write(res);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Arquivo Não Exportado");
				}
				
				try {
					writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Arquivo Não Exportado");
				}
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Arquivo Não Exportado");
				}
				JOptionPane.showMessageDialog(null, "Arquivo Exportado");
			}
		});
		btnNewButton.setBounds(119, 331, 124, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//scrollPane.add(textArea);
		//scrollPane.add(scrollBar_1);
		//panel.add(scrollPane);
		

		
		
		

		
	
	}
}

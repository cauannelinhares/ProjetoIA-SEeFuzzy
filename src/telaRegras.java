import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class telaRegras extends JFrame {

	private JPanel contentPane;
    public static String[] ret = new String[5];
	
	private static JTextField r1;
	private static JTextField r2;
	private static JTextField r3;
	private static JTextField r4;
	private static JTextField r5;

	/**
	 * Launch the application.
	 * @return 
	 */
	public static String[] main(String[] Args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaRegras frame = new telaRegras();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
		
		return retornar();
	}

	/**
	 * Create the frame.
	 */
	public telaRegras( ) {
		setTitle("CONFIGURAR REGRAS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 408);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setToolTipText("");
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "MotorFornSE", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 11, 356, 347);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Diferen\u00E7a valor itens");
		lblNewLabel.setBounds(10, 49, 154, 14);
		panel_2.add(lblNewLabel);
		
		r1 = new JTextField();
		r1.setText("9");
		r1.setBounds(228, 46, 86, 20);
		panel_2.add(r1);
		r1.setColumns(10);
		
		JLabel lblPesos = new JLabel("Pesos");
		lblPesos.setBounds(257, 21, 67, 14);
		panel_2.add(lblPesos);
		
		JLabel lblModalidades = new JLabel("Modalidades especiais");
		lblModalidades.setBounds(10, 92, 130, 14);
		panel_2.add(lblModalidades);
		
		r2 = new JTextField();
		r2.setText("5");
		r2.setBounds(228, 89, 86, 20);
		panel_2.add(r2);
		r2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Outras coloca\u00E7\u00F5es");
		lblNewLabel_1.setBounds(10, 135, 154, 14);
		panel_2.add(lblNewLabel_1);
		
		r3 = new JTextField();
		r3.setText("1");
		r3.setBounds(228, 132, 86, 20);
		panel_2.add(r3);
		r3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Diferen\u00E7a da m\u00E9dia de participantes");
		lblNewLabel_2.setBounds(10, 180, 207, 14);
		panel_2.add(lblNewLabel_2);
		
		r4 = new JTextField();
		r4.setText("2");
		r4.setBounds(228, 177, 86, 20);
		panel_2.add(r4);
		r4.setColumns(10);
		
		r5 = new JTextField();
		r5.setText("4");
		r5.setBounds(228, 223, 86, 20);
		panel_2.add(r5);
		r5.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ganhador frequente");
		lblNewLabel_3.setBounds(10, 226, 171, 14);
		panel_2.add(lblNewLabel_3);
		
		JButton bVoltar = new JButton("Voltar");
		bVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
			}
		});
		bVoltar.setBounds(225, 285, 89, 23);
		panel_2.add(bVoltar);
	}
	
	public static String[] retornar(){
		ret[0] = r1.getText();
		ret[1] = r2.getText();
		ret[2] = r3.getText();
		ret[3] = r4.getText();
		ret[4] = r5.getText();
		
		return ret;
	}
}

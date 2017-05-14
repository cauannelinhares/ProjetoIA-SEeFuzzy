

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;




public class Tela extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
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
	
	
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 346, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAbrirArquivo = new JButton("Abrir Arquivo");
		btnAbrirArquivo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				File a =new Arquivo().escolherArquivos();
				try {
					manipularExcel(a.getAbsolutePath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});
		btnAbrirArquivo.setBounds(110, 203, 111, 23);
		contentPane.add(btnAbrirArquivo);
		
		
		JLabel lblPOLI = new JLabel("");
		lblPOLI.setBounds(26, -14, 294, 234);
		contentPane.add(lblPOLI);
		
		lblPOLI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/poli-LOGO.png"))); // NOI18N
		
		JLabel lblcauanneLinharesJoyce = new JLabel("@Carlos Alberto, @Carlos Costa, @Joyce S\u00E1 ");
		lblcauanneLinharesJoyce.setBounds(63, 258, 219, 23);
		contentPane.add(lblcauanneLinharesJoyce);
		
		JLabel lblCauanneLinhares = new JLabel("@Cauanne Linhares ");
		lblCauanneLinhares.setBounds(120, 231, 133, 27);
		contentPane.add(lblCauanneLinhares);
		
	}
	
	

	public void manipularExcel(String path) throws IOException{
		
		ArrayList<String[]> linhas = new ArrayList<String[]>();
		
		 BufferedReader buffRead = new BufferedReader(new FileReader(path));
	        String linhaLida = "";
	        linhaLida = buffRead.readLine(); //desprezar o cabeçalho
	        while (true) {
	        	linhaLida = buffRead.readLine();
	            if (linhaLida != null) {
	            	String quebrado[] = linhaLida.replace('|', ';').split(";");
	            	linhas.add(quebrado);
	            } else
	                break;
	        }
	        buffRead.close();
		
	        
	        for (String[] i : linhas) {
	        	for (String colunas : i) {
	        		System.out.print(colunas +" ");	
	        	}
	        	System.out.println();//pular linha	        		
	        }
	}
}

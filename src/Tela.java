

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
					String[][] b = manipularExcel(a.getAbsolutePath());
					ArrayList<String> ugs = identificadorUG(b);
					ArrayList<String> fornecedores = identificadorFornecedor(b);
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
	
	

	public String[][] manipularExcel(String path) throws IOException{
		
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
		
	        //Reduz o .csv as linhas que são importantes para as regras
	        String[][] matrizDados = new String [linhas.size() + 1][13];
	        int contadorLinhas = 0;
	        int contadorColunas = 0;
	        
	        for (String[] i : linhas) {
	        	contadorColunas = 0;
	        	for (String colunas : i) {
	        		switch(contadorColunas){
	        		//modalidade
	        		case 2:
	        			matrizDados[contadorLinhas][0] = colunas;
	        		//valor
	        		case 4:
	        			matrizDados[contadorLinhas][1] = colunas;
	        		//qtde participantes
	        		case 10:
	        			matrizDados[contadorLinhas][2] = colunas;
	        		//UG
	        		case 13:
	        			matrizDados[contadorLinhas][3] = colunas;
	        		//fornecedor
	        		case 19: 
	        			matrizDados[contadorLinhas][4] = colunas;
	        		//colocação do vencedor
	        		case 20:
	        			matrizDados[contadorLinhas][5] = colunas;
	        		//valor do item
	        		case 21:
	        			matrizDados[contadorLinhas][6] = colunas;
	        		//sigla UG
	        		case 23: 
	        			matrizDados[contadorLinhas][7] = colunas;
	        		//nome modalidade
	        		case 24:
	        			matrizDados[contadorLinhas][8] = colunas;
	        		//cod item
	        		case 28:
	        			matrizDados[contadorLinhas][9] = colunas;
	        		//nome item
	        		case 30:
	        			matrizDados[contadorLinhas][10] = colunas;
	        		//razão social
	        		case 38:
	        			matrizDados[contadorLinhas][11] = colunas;
	        		//cnpj
	        		case 40:
	        			matrizDados[contadorLinhas][12] = colunas;
	        		}
	        		contadorColunas++;
	        	}
	        	contadorLinhas++;
	        }
	        int qtdeColunas = matrizDados[0].length;
			int qtdeLinhas = matrizDados.length;
			
			System.out.println(qtdeLinhas + " " + qtdeColunas);
	return matrizDados;        
	}
	
	public void testarDados(String[][] dados){
		int qtdeColunas = dados[0].length;
		int qtdeLinhas = dados.length;
		
		System.out.println(qtdeLinhas + qtdeColunas);
		
	}
	
	public ArrayList<String> identificadorUG (String[][] dados) {
		int qtdeColunas = dados[0].length;
		int qtdeLinhas = dados.length;
		ArrayList<String> ugs = new ArrayList<String>();
		String codUg = "";
		
		for (int i = 0; i < qtdeLinhas; i++) {
			boolean jaExiste = false;
			codUg = dados[i][3];
			for (String j : ugs) {
				if (codUg == j) {
					jaExiste = true;
					break;
				} else {
					jaExiste = false;
				}
			}
			if (jaExiste == false){
				ugs.add(codUg);
			}
			
		}
		
	return ugs;	
	}
	
	public ArrayList<String> identificadorFornecedor (String[][] dados) {
		int qtdeLinhas = dados.length;
		ArrayList<String> fornecedores = new ArrayList<String>();
		String codFornecedor = "";
		
		for (int i = 0; i < qtdeLinhas; i++) {
			boolean jaExiste = false;
			codFornecedor = dados[i][3];
			for (String j : fornecedores) {
				if (codFornecedor == j) {
					jaExiste = true;
					break;
				} else {
					jaExiste = false;
				}
			}
			if (jaExiste == false){
				fornecedores.add(codFornecedor);
			}	
		}
		
	return fornecedores;	
	}
	
	public ArrayList<Float> contadorModalidade(String ug, String[][] dados){
		int qtdeLinhas = dados.length;
		int contadorDispensa = 0;
		int contadorDispensaTotal = 0;
		int contadorPregaoP = 0;
		int contadorPregoPTotal = 0;
		int contadorInegibilidade = 0;
		int contadorInegibilidadeTotal = 0;
		
		for (int i = 0; i <= qtdeLinhas; i++) {
			String ugAtual= dados[i][4];
			if (dados[i][0] == "8"){
				contadorDispensaTotal++;
			} else if (dados[i][0] == "6") {
				contadorPregoPTotal++;
			} else if (dados[i][0] == "10") {
				contadorInegibilidadeTotal++;
			}
			if (ug.equals(ugAtual)) {
				if (dados[i][0] == "8") {
					contadorDispensa++;
				} else if (dados[i][0] == "6") {
					contadorPregaoP++;
				} else if (dados[i][0] == "10") {
					contadorInegibilidade++;
				}
			}
		}
		
		ArrayList<Float> contadores = new ArrayList<Float>();
		float temp = contadorDispensa/contadorDispensaTotal;
		
		contadores.add(temp);
		temp = contadorPregaoP/contadorPregoPTotal;
		contadores.add(temp);
		temp = contadorInegibilidade/contadorInegibilidadeTotal;
		contadores.add(temp);
		
		return contadores;
	} 
	
	public ArrayList<String[]> motorInferenciaUG(String[][] dados){
		ArrayList<String[]> indiciosUG = new ArrayList<String[]>();
		ArrayList<String> ugs = identificadorUG(dados);
		ArrayList<Float> contadorModalidade = new ArrayList<Float>();
		
		for (String i : ugs) {
			String[] detalhesUG = new String[ugs.size()];
			int posicaoVetorDetalhes = 0;
			contadorModalidade = contadorModalidade(i, dados);
			//Regra de modalidades de aquisição de itens
			if (contadorModalidade.get(0) > 0.4) {
				if (detalhesUG.length == 0){
					detalhesUG[posicaoVetorDetalhes] = i;
					posicaoVetorDetalhes++;
				}
				detalhesUG[posicaoVetorDetalhes] = contadorModalidade.get(0).toString();
				posicaoVetorDetalhes++;
			}
			if (contadorModalidade.get(1) > 0.1) {
				if (detalhesUG.length == 0){
					detalhesUG[posicaoVetorDetalhes] = i;
					posicaoVetorDetalhes++;
				}
				detalhesUG[posicaoVetorDetalhes] = contadorModalidade.get(1).toString();
				posicaoVetorDetalhes++;
			}
			if (contadorModalidade.get(2) > 0.01) {
				if (detalhesUG.length == 0){
					detalhesUG[posicaoVetorDetalhes] = i;
					posicaoVetorDetalhes++;
				}
				detalhesUG[posicaoVetorDetalhes] = contadorModalidade.get(2).toString();
				posicaoVetorDetalhes++;
			}
			
			indiciosUG.add(detalhesUG);
		}
		
		
		
		return indiciosUG;
	}
		
	
	
}

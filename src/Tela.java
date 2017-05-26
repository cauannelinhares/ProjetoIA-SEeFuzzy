

import java.awt.BorderLayout;
import java.math.*;
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
					ArrayList<String[]> mi = motorInferenciaUG(b);

					System.out.println(ugs);

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
		linhaLida = buffRead.readLine(); //desprezar o cabe�alho
		while (true) {
			linhaLida = buffRead.readLine();
			if (linhaLida != null) {
				String quebrado[] = linhaLida.replace('|', ';').split(";");
				linhas.add(quebrado);
			} else
				break;
		}
		buffRead.close();

		//Reduz o .csv as linhas que s�o importantes para as regras
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
					//coloca��o do vencedor
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
					//raz�o social
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

	//Pegar array com as UGs existentes no .csv sem repeti��o
	public ArrayList<String> identificadorUG (String[][] dados) {
		int qtdeLinhas = dados.length;
		ArrayList<String> ugs = new ArrayList<String>();
		String codUg = "";

		for (int i = 0; i < qtdeLinhas; i++) {
			codUg = dados[i][3];
			if (!ugs.contains(codUg)){
				ugs.add(codUg);
			}
		}

		return ugs;	
	}

	//Pegar array com os Fronecedores existentes no .csv sem repeti��o
	public ArrayList<String> identificadorFornecedor (String[][] dados) {
		int qtdeLinhas = dados.length;
		ArrayList<String> fornecedores = new ArrayList<String>();
		String codFornecedor = "";

		for (int i = 0; i < qtdeLinhas; i++) {
			codFornecedor = dados[i][4];
			if (!fornecedores.contains(codFornecedor)){
				fornecedores.add(codFornecedor);
			}
		}
		return fornecedores;	
	}

	//Pegar array com os Fronecedores existentes na ug sem repetição
	public ArrayList<String> identificadorFornecedorUG (String ug, String[][] dados) {
		int qtdeLinhas = dados.length;
		ArrayList<String> fornecedores = new ArrayList<String>();
		String codFornecedor = "";

		for (int i = 0; i < qtdeLinhas; i++) {
			codFornecedor = dados[i][4];
			if (!fornecedores.contains(codFornecedor) && dados[i][3].equals(ug)){
				fornecedores.add(codFornecedor);
			}
		}
		return fornecedores;   
	}

	public String[][] frequenciaVencedor(String ug, String[][] dados){

		ArrayList<String> fornecedor = identificadorFornecedorUG(ug, dados);
		String[][] frequencia = new String[fornecedor.size()][2];

		int qtdeLinhas = dados.length;
		int qtdeFornecedor = fornecedor.size();
		int contAux=0;

		for(int k = 0; k < qtdeFornecedor; k++){
			contAux=0;
			for (int i = 0; i < qtdeLinhas; i++) {
				if(dados[i][3].equals(ug) && dados[i][4].equals(fornecedor.get(k))){
					contAux++;
				}
			}
			frequencia[k][0] = fornecedor.get(k);
			frequencia[k][1] = String.valueOf(contAux);
		}

		return frequencia;
	}


	public float frequenciaMax(String[][] freq){

		int FreqMax=0;
		int total=0;
		int qtdeFornecedor = freq.length;

		for(int i=0; i<qtdeFornecedor;i++){
			if(Integer.parseInt(freq[i][1])>FreqMax){
				FreqMax = Integer.parseInt(freq[i][1]);
			}
			total+=Integer.parseInt(freq[i][1]);
		}

		return FreqMax/total;
	

	}
	
//	public float mediaParticipantes(String[][] dados) {
//		int qtdeLinhas = dados.length;
//		int acumulador = 0;
//		
//		for (int i = 0; i < qtdeLinhas; i++) {
//			float valor = Float.parseFloat(dados[i][2]);
//			acumulador += valor;
//		}
//		return acumulador/qtdeLinhas;
//	}
	
	public float mediaParticipantesPorUG(String codUG, String[][] dados) {
		int qtdeLinhas = dados.length;
		int acumulador = 0;
		int qtdeLicitacoesUG = 0;
		
		for (int i = 0; i < qtdeLinhas; i++) {
			if (codUG.equals(dados[i][3])) {
				int valor = Integer.parseInt(dados[i][2]);
				acumulador += valor;	
				qtdeLicitacoesUG++;
			}
			
		}
		return acumulador/qtdeLicitacoesUG;
	}

	public ArrayList<Float> contadorModalidade(String ug, String[][] dados){
		int qtdeLinhas = dados.length;
		int contadorDispensa = 0;
		int contadorDispensaTotal = 0;
		int contadorPregaoP = 0;
		int contadorPregoPTotal = 0;
		int contadorInegibilidade = 0;
		int contadorInegibilidadeTotal = 0;
		int contadorLicitacoesNaUG = 0;

		for (int i = 0; i < qtdeLinhas; i++) {
			String ugAtual= dados[i][3];
			String modalidadeAtual = dados[i][0];
			if (modalidadeAtual.equals("8")){
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
				contadorLicitacoesNaUG++;
			}
		}

		ArrayList<Float> contadores = new ArrayList<Float>();

		//Percentual modalidade na UG
		float tempUG = contadorDispensa/contadorLicitacoesNaUG;
		contadores.add(0, tempUG);
		tempUG = contadorInegibilidade/contadorLicitacoesNaUG;
		contadores.add(1, tempUG);
		tempUG = contadorPregaoP/contadorLicitacoesNaUG;
		contadores.add(2,  tempUG);

		//Percentual modalidade da UG com rela��o ao total
		float tempTotal = contadorDispensa/contadorDispensaTotal;
		contadores.add(tempTotal);
		tempTotal = contadorPregaoP/contadorPregoPTotal;
		contadores.add(tempTotal);
		tempTotal = contadorInegibilidade/contadorInegibilidadeTotal;
		contadores.add(tempTotal);

		return contadores;
	} 

	public ArrayList<Float> itemMaisConsumido (String codUG, String[][] dados) {
		ArrayList<Float> itemMaisConsumido = new ArrayList<Float>();
		ArrayList<String> itensMais = new ArrayList<String>();

		//Controi vetor de itens da UG
		for (int i = 0; i < dados.length; i++) {
			if(codUG.equals(dados[i][3])) {
				itensMais.add(dados[i][9]);
			}
		}
		//Verifica qual item mais se repetiu
		int maisRepetido = 0;
		String maisRepetida = "";
		for (int i = 0; i < itensMais.size(); i++) {
			String atual = itensMais.get(i);
			int contadorAtual = 0;
			for (int j = 0; j < itensMais.size(); j++) {
				if (atual.equals(itensMais.get(j))) {
					contadorAtual++;
				}
			}
			if (contadorAtual > maisRepetido) {
				maisRepetido = contadorAtual;
				maisRepetida = atual;
			}
		}

		//Calculando Medias..
		float somaValoresItemNaUG = 0;
		float somaValoresItemTotal = 0;
		int qtdeNoTotal = 0;
		for (int i = 0; i < dados.length; i++) {
			String itemAtual = dados[i][9];
			float valor = Float.parseFloat(dados[i][6]);
			if (itemAtual.equals(maisRepetida)) {
				somaValoresItemTotal += valor;
				if (codUG.equals(dados[i][3])) {
					somaValoresItemNaUG += valor; 
				}
				qtdeNoTotal++;
			}
		}

		float precoMedioNaUG = somaValoresItemNaUG / maisRepetido;
		float precoMedioTotal = somaValoresItemTotal / qtdeNoTotal;

		//Desvio Padrao..
		float desvioPadraoNaUG = 0;
		float desvioPadraoTotal = 0;
		for (int i = 0; i < dados.length; i++) {
			float valor = Float.parseFloat(dados[i][6]);
			if (codUG.equals(dados[i][3])){
				desvioPadraoNaUG += Math.sqrt((Math.pow((valor - precoMedioNaUG), 2))/i - 1);
			} else {
				desvioPadraoTotal += Math.sqrt((Math.pow((valor - precoMedioTotal), 2))/i - 1);
			}
		}

		itemMaisConsumido.add(precoMedioNaUG);
		itemMaisConsumido.add(desvioPadraoNaUG);
		itemMaisConsumido.add(precoMedioTotal);
		itemMaisConsumido.add(desvioPadraoTotal);

		return itemMaisConsumido;
	}  

	public ArrayList<String[]> motorInferenciaUG(String[][] dados){
		ArrayList<String[]> indiciosUG = new ArrayList<String[]>();
		ArrayList<String> ugs = identificadorUG(dados);
		ArrayList<Float> contadorModalidade = new ArrayList<Float>();
		ArrayList<Float> itemMaisConsumido = new ArrayList<Float>();
		
		//float mediaParticipantesGeral = mediaParticipantes(dados);

		for (String i : ugs) {
			String[] detalhesUG = new String[ugs.size()];
			contadorModalidade = contadorModalidade(i, dados);
			itemMaisConsumido(i, dados);
			
			boolean flag = false;
			//Regra de modalidades de aquisi��o de itens
			if (contadorModalidade.get(0) > 0.5) {
				flag = true;
			}
			if (contadorModalidade.get(1) > 0.1) {
				flag = true;
			}
			if (contadorModalidade.get(2) > 0.01) {
				flag = true;
			}
			if (itemMaisConsumido.get(0) > 1.5 * itemMaisConsumido.get(2)) {
				flag = true;
			}
//			if (mediaParticipantesPorUG(i, dados) < 1.3 * mediaParticipantesGeral) {
//				flag = true;
//			}
			if (frequenciaMax(frequenciaVencedor(i, dados)) > 0.2){
				flag = true;
			}
			
			if (flag = true){
				detalhesUG[0] = i;
				detalhesUG[1] = contadorModalidade.get(0).toString();
				detalhesUG[2] = contadorModalidade.get(1).toString();
				detalhesUG[3] = contadorModalidade.get(2).toString();
				detalhesUG[4] = contadorModalidade.get(3).toString();
				detalhesUG[5] = contadorModalidade.get(4).toString();
				detalhesUG[6] = contadorModalidade.get(5).toString();
				detalhesUG[7] = itemMaisConsumido.get(0).toString();
				detalhesUG[8] = itemMaisConsumido.get(1).toString();
				detalhesUG[9] = itemMaisConsumido.get(2).toString();
				detalhesUG[10] = itemMaisConsumido.get(3).toString();
				//detalhesUG[11] = String.valueOf(mediaParticipantesGeral);
				detalhesUG[11] = String.valueOf(frequenciaMax(frequenciaVencedor(i, dados)));
			}
			
			indiciosUG.add(detalhesUG);
		}
		
		System.out.println(indiciosUG);

		

		return indiciosUG;
	}



}

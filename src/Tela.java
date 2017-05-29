import java.awt.EventQueue; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class Tela extends JFrame {
 
    private JPanel contentPane;

    public String[] pesos = { "9","5","1","2","4"};
    
    public String[][] dPrin;
    
    public StringBuffer resultado = new StringBuffer();
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
    	setTitle("IA 2017.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 392, 408);
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
                    dPrin = manipularExcel(a.getAbsolutePath());
                    /*
                    ArrayList<String> ugs = identificadorUG(b);
                    ArrayList<String> fornecedores = identificadorFornecedor(b);
                    ArrayList<ArrayList<String>> ugForn = new ArrayList<ArrayList<String>>();
                    for (String i : ugs) {
                    	ugForn.add(identificadorFornecedorUG(i, b));
                    }
                    ArrayList<String[]> mi = motorInferenciaUG(b);
                    
                    System.out.println("ugs:");
                    for (String i : ugs) {
                    	System.out.println(i);
                    }
                    System.out.println("fornecedores:");
                    for (String i : fornecedores) {
                    	System.out.println(i);
                    }
                    System.out.println("ug - fornecedor:");
                    for (int i = 0; i < ugForn.size(); i++) {
                    	ArrayList<String> temp = ugForn.get(i);
                    	for (int j = 0; j < temp.size(); j++) {
                    		System.out.println(temp);
                    	}
                    }
                    System.out.println("media participantes:");
                    System.out.println(mediaParticipantes(b));
                  
                    
                    System.out.println("media participantes na ug:");
                    for (String i : ugs){
                    	System.out.println(mediaParticipantesPorUG(i, b));
                    }
                    
                    System.out.println("item mais vendido");
                    b = calcularValorTotalItem(b);
                    //imprimird(b);
                    String[][] asaasa = mediaItemUG(ugs.get(1), b);
                    imprimird(asaasa);
                    System.out.println(frequenciaMax(asaasa));
                    
                    System.out.println("contadores modalidades:");
                    ArrayList<Float> lala = contadorModalidade(ugs.get(1), b);
                    imprimirFloat(lala);
                    
                    System.out.println("contadores:");
                    System.out.println(somaContadorModalidadeUG(ugs.get(1), b));
                    
                    System.out.println("CodUG|VLmedItem|Soma Modalidades");
                    imprimire(motorInferenciaUG(b));
                    
                    System.out.println("===========================================================");
                    
                    System.out.println("CodFornecedor|VlMedItem item|SomaModalidades");
                    imprimire(motorInferenciaFornecedor(b));
                    
                    System.out.println("===========================================================");
                    
                    System.out.println("CodFornecedor|Classificacao|Nota");
                    imprimire(motorInferenciaFornecedorSE(b));
                */   
 
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                
                //dispose();
            }
        });
        btnAbrirArquivo.setBounds(138, 211, 111, 23);
        contentPane.add(btnAbrirArquivo);
 
 
        JLabel lblPOLI = new JLabel("");
        lblPOLI.setBounds(45, 0, 294, 234);
        contentPane.add(lblPOLI);
 
        lblPOLI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/poli-LOGO.png"))); // NOI18N
 
        JLabel lblcauanneLinharesJoyce = new JLabel("@Carlos Alberto, @Carlos Costa, @Joyce S\u00E1 ");
        lblcauanneLinharesJoyce.setBounds(81, 271, 275, 23);
        contentPane.add(lblcauanneLinharesJoyce);
 
        JLabel lblCauanneLinhares = new JLabel("@Cauanne Linhares ");
        lblCauanneLinhares.setBounds(149, 245, 133, 27);
        contentPane.add(lblCauanneLinhares);
        
        JButton gerar = new JButton("VerificarFornecedor");
        gerar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		System.out.println("Estou processando");
        		resultado.append("CodFornecedor|Classificacao|Nota"+"\n");
        		resultado.append(formatarResultado(motorInferenciaFornecedorSE(dPrin)).toString());
        		System.out.println("Terminei de processar");
        		new telaResultado(resultado.toString()).main(resultado.toString());
        		
        		dispose();
        	}
        });
        gerar.setBounds(200, 305, 156, 23);
        contentPane.add(gerar);
        
        JButton btnNewButton_3 = new JButton("Regras");
        btnNewButton_3.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent arg0) {
        		pesos = new telaRegras().main(pesos);
        	}
        });

        btnNewButton_3.setBounds(34, 305, 156, 23);
        contentPane.add(btnNewButton_3);
 
    }
 
    public String[][] manipularExcel(String path) throws IOException{
 
        ArrayList<String[]> linhas = new ArrayList<String[]>();
 
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linhaLida = "";
        linhaLida = buffRead.readLine(); //desprezar o cabe�alho
        int count=0;
        while (true) {
            linhaLida = buffRead.readLine();
            if (linhaLida != null) {
                String quebrado[] = linhaLida.replace('|', ';').replace('\"', ' ' ).replace(" ","").split(";");
                if(!validarLinha(quebrado)){//arquivo tem 47 colunas
                	//imprimirP(quebrado);
                	//System.out.println("");
                	System.out.println("Desprezei linha: " + count);
                }else{
                	linhas.add(quebrado);
                	count++;
                }
            } else
                break;
        }
        buffRead.close();
        System.out.println("Processei " + count + " linhas");
 
        //Reduz o .csv as linhas que s�o importantes para as regras
        String[][] matrizDados = new String [linhas.size()][15];
        int contadorLinhas = 0;
        int contadorColunas = 0;
 
        for (String[] i : linhas) {
            contadorColunas = 0;
            if(i.length >=40){

            
            for (String colunas : i) {
                switch(contadorColunas){
                //modalidade
                case 2:
                    matrizDados[contadorLinhas][0] = colunas;break;
                    //valor
                case 4:
                	if(!Pattern.matches("[-]?[0-9]*[.]?[0-9]+", colunas)){colunas = "0";}
                    matrizDados[contadorLinhas][1] = colunas;break;
                    //qtde participantes
                case 10:
                	if(!Pattern.matches("[-]?[0-9]*[.]?[0-9]+", colunas)){colunas = "0";}
                    matrizDados[contadorLinhas][2] = colunas;break;
                    //UG
                case 13:
                    matrizDados[contadorLinhas][3] = colunas;break;
                    //fornecedor
                case 19:
                    matrizDados[contadorLinhas][4] = colunas;break;
                    //coloca��o do vencedor
                case 20:
                	if(!colunas.matches("^[0-9]*$")){colunas = "0";}
                    matrizDados[contadorLinhas][5] = colunas;break;
                    //valor do item
                case 21:
                	if(!Pattern.matches("[-]?[0-9]*[.]?[0-9]+", colunas)){colunas = "0";}
                    matrizDados[contadorLinhas][6] = colunas;break;
                    //sigla UG
                case 23:
                    matrizDados[contadorLinhas][7] = colunas;break;
                    //nome modalidade
                case 24:
                    matrizDados[contadorLinhas][8] = colunas;break;
                    //cod item
                case 28:
                    matrizDados[contadorLinhas][9] = colunas;break;
                    //nome item
                case 30:
                    matrizDados[contadorLinhas][10] = colunas;break;
                    //razao social
                case 38:
                    matrizDados[contadorLinhas][11] = colunas;break;
                    //cnpj
                case 40:
                    matrizDados[contadorLinhas][12] = colunas;break;
                    //qtd itens licitados
                case 26:
                	if(!Pattern.matches("[-]?[0-9]*[.]?[0-9]+", colunas)){colunas = "0";}
                	matrizDados[contadorLinhas][13] = colunas;break;
                }
                contadorColunas++;
            }
            
            }
            contadorLinhas++;
        }
          
        return matrizDados;        
    }
 
    private boolean validarLinha(String[] quebrado) {
		boolean retorno = true;
		if(quebrado.length > 40){
			//modalidade
	    	if(!Pattern.matches("[-]?[0-9]*[.]?[0-9]+", quebrado[2])){retorno=false;}
	        //valor
	    	if(!Pattern.matches("[-]?[0-9]*[.]?[0-9]+", quebrado[4])){retorno=false;}
	        //qtde participantes
	    	if(!Pattern.matches("[-]?[0-9]*[.]?[0-9]+", quebrado[10])){retorno=false;}
	        //UG
	    	if(quebrado[13].isEmpty() || quebrado[13]==null){retorno=false;}
	        //fornecedor
	    	if(quebrado[19].isEmpty() || quebrado[19]==null){retorno=false;}
	        //coloca��o do vencedor
	    	if(!Pattern.matches("[-]?[0-9]*[.]?[0-9]+", quebrado[20])){retorno=false;}
	        //valor do item
	    	if(!Pattern.matches("[-]?[0-9]*[.]?[0-9]+", quebrado[21])){retorno=false;}
	        //sigla UG
	    	if(quebrado[23].isEmpty() || quebrado[23]==null){retorno=false;}
	        //nome modalidade
	    	if(quebrado[24].isEmpty() || quebrado[24]==null){retorno=false;}
	    	//qtd itens licitados
	    	if(!Pattern.matches("[-]?[0-9]*[.]?[0-9]+", quebrado[26])){retorno=false;}
	        //cod item
	    	if(quebrado[28].isEmpty() || quebrado[28]==null){retorno=false;}
	        //nome item
	    	if(quebrado[30].isEmpty() || quebrado[30]==null){retorno=false;}
	        //razao social
	    	if(quebrado[38].isEmpty() || quebrado[38]==null){retorno=false;}
	        //cnpj
	    	if(quebrado[40].isEmpty() || quebrado[40]==null){retorno=false;}
		}else{
			return false;
		}
		
		return retorno;
	}

	public void testarDados(String[][] dados){
        int qtdeColunas = dados[0].length;
        int qtdeLinhas = dados.length;
 
        System.out.println(qtdeLinhas + qtdeColunas);
 
    }
 
    //Pegar array com as UGs existentes no .csv sem repeticao - ok
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
    
    //Pegar array com os Fornecedores existentes no .csv sem repeticao - ok
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
 
    //Pegar array com os Fornecedores existentes na ug sem repeticao - ok
    public ArrayList<String> identificadorFornecedorUG (String ug, String[][] dados) {
        int qtdeLinhas = dados.length;
        ArrayList<String> fornecedores = new ArrayList<String>();
        String codFornecedor = "";
 
        for (int i = 0; i < qtdeLinhas; i++) {
            codFornecedor = dados[i][4];
            if ((!fornecedores.contains(codFornecedor)) && dados[i][3].equals(ug)){
                fornecedores.add(codFornecedor);
            }
        }
        return fornecedores;  
    }
    
  //Pegar array com as UGs existentes no fornecedor sem repeticao - ok
    public ArrayList<String> identificadorUGFornecedor (String fornecedor, String[][] dados) {
        int qtdeLinhas = dados.length;
        ArrayList<String> ugs = new ArrayList<String>();
        String codUgs = "";
 
        for (int i = 0; i < qtdeLinhas; i++) {
            codUgs = dados[i][3];
            try{
	            if ((!ugs.contains(codUgs)) && dados[i][4].equals(fornecedor)){
	                ugs.add(codUgs);
	            }
            }catch (Exception e) {
				// TODO: handle exception
			}
        }
        return ugs;  
    }
       
    //Pegar array com os itens existentes na ug sem repeticao - ok
    public ArrayList<String> identificadorItemUG (String ug, String[][] dados) {
        int qtdeLinhas = dados.length;
        ArrayList<String> itens = new ArrayList<String>();
        String codItem = "";
 
        for (int i = 0; i < qtdeLinhas; i++) {
            codItem = dados[i][9];
            if ((!itens.contains(codItem)) && dados[i][3].equals(ug)){
                itens.add(codItem);
            }
        }
        return itens;  
    }
    
    //Pegar array com os itens existentes no fornecedor sem repeticao - ok
    public ArrayList<String> identificadorItemFornecedor (String fornecedor, String[][] dados) {
        int qtdeLinhas = dados.length;
        ArrayList<String> itens = new ArrayList<String>();
        String codItem = "";
 
        for (int i = 0; i < qtdeLinhas; i++) {
            codItem = dados[i][9];
            try{
	            if ((!itens.contains(codItem)) && dados[i][4].equals(fornecedor)){
	                itens.add(codItem);
	            }
            }catch (Exception e) {
				// TODO: handle exception
			}
        }
        return itens;  
    }
    
    //Conta o total de licitacoes da UG
    public int contadorLicitacaoUG (String ug, String[][] dados) {
		int contador = 0;
    	for (int i = 0; i < dados.length; i++){
			if (dados[i][3].equals(ug)){
				contador++;
			}
		}
    	return contador;
    }
    
    //Conta o total de licitacoes que o fornecedor venceu
    public int contadorLicitacaoFornecedor (String fornecedor, String[][] dados) {
		int contador = 0;
    	for (int i = 0; i < dados.length; i++){
    		try{ 
				if (dados[i][4].equals(fornecedor)){
					contador++;
				}
    		}catch (Exception e) {
				
			}
		}
    	return contador;
    }

    public int contadorOutrasColocacoes (String fornecedor, String[][] dados) {
    	int contador = 0;
    	
    	for(int i = 0; i < dados.length; i++) {
    		try{
	    		if(dados[i][4].equals(fornecedor) && (!dados[i][5].equals("1"))) {
	    			contador++;
	    		}
    		}catch (Exception e) {
				// TODO: handle exception
			}
    	}
    	
    	return contador;
    	
    }
    
    //Frequencia dos fornecedores que venceram a licitacao de uma ug
    public String[][] frequenciaFornecedorVencedorUG(String ug, String[][] dados){
 
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

   //Frequencia das ugs que tiveram a licitacao ganha por um fornecedor
    public String[][] frequenciaUgVencedorFornecedor(String fornecedor, String[][] dados){
    	 
        ArrayList<String> ugs = identificadorUGFornecedor(fornecedor, dados);
        String[][] frequencia = new String[ugs.size()][2];
 
        int qtdeLinhas = dados.length;
        int qtdeFornecedor = ugs.size();
        int contAux=0;
 
        for(int k = 0; k < qtdeFornecedor; k++){
            contAux=0;
            for (int i = 0; i < qtdeLinhas; i++) {
            	try{
	                if(dados[i][4].equals(fornecedor) && dados[i][3].equals(ugs.get(k))){
	                    contAux++;
	                }
            	}catch (Exception e) {
					// TODO: handle exception
				}
            }
            frequencia[k][0] = ugs.get(k);
            frequencia[k][1] = String.valueOf(contAux);
        }
 
        return frequencia;
    }
    
    //Retorna o elemento com a maior frequencia 
    public float frequenciaMax(String[][] freq){
 
        float FreqMax=0;
        int total=0;
        int qtdeFornecedor = freq.length;
 
        for(int i=0; i<qtdeFornecedor;i++){
            if(Float.parseFloat(freq[i][1])>FreqMax){
                FreqMax = Float.parseFloat(freq[i][1]);
            }
            total+=Float.parseFloat(freq[i][1]);
        }
 
        return FreqMax;
   
 
    }
    
    //Calcula qtdItensLicitados * valorItemLicitado
    public String[][] calcularValorTotalItem(String dados[][]) {
    	
    	int qtdeLinhas = dados.length;
    	
    	for(int i=0; i<qtdeLinhas; i++){
    		
    			try{
    				dados[i][14] = String.valueOf(Float.parseFloat(dados[i][6]) * Float.parseFloat(dados[i][13]));
 
    			}catch (Exception e) {
    				dados[i][14] = "0";
				}
    	}
    	
    	return dados;
    	
    }
    
    //Calcula o valor medio de cada item adquirido pela UG
    public String[][] mediaItemUG (String ug,String dados[][]){
		
    	 ArrayList<String> itens = identificadorItemUG(ug, dados);
         String[][] frequencia = new String[itens.size()][2];
  
         int qtdeLinhas = dados.length;
         int qtdeItens = itens.size();
         float contAux=0;
         float sumAux=0;
         
  
         for(int k = 0; k < qtdeItens; k++){
             contAux=0;
             sumAux=0;
             for (int i = 0; i < qtdeLinhas; i++) {
                 if(dados[i][3].equals(ug) && dados[i][9].equals(itens.get(k))){
                     sumAux+=Float.parseFloat(dados[i][14]);
                     //contAux++;
                     contAux+= Float.parseFloat(dados[i][13]);
                 }
             }
             frequencia[k][0] = itens.get(k);
             frequencia[k][1] = String.valueOf((float)sumAux/contAux);
         }
  
         return frequencia;
    	
    }
    
    //Calcula o valor medio de cada item vendido pelo fornecedor
    public String[][] mediaItemFornecedor (String fornecedor,String dados[][]){
		
    	 ArrayList<String> itens = identificadorItemFornecedor(fornecedor, dados);
         String[][] frequencia = new String[itens.size()][2];
  
         int qtdeLinhas = dados.length;
         int qtdeItens = itens.size();
         float contAux=0;
         float sumAux=0;
         
  
         for(int k = 0; k < qtdeItens; k++){
             contAux=0;
             sumAux=0;
             for (int i = 0; i < qtdeLinhas; i++) {
                try{
            	 if(dados[i][4].equals(fornecedor) && dados[i][9].equals(itens.get(k))){
                     sumAux+=Float.parseFloat(dados[i][14]);
                     //contAux++;
                     contAux+= Float.parseFloat(dados[i][13]);
                 }
                }catch (Exception e) {
					// TODO: handle exception
				}
             }
                
             frequencia[k][0] = itens.get(k);
             frequencia[k][1] = String.valueOf((float)sumAux/contAux);
         }
  
         return frequencia;
    	
    }

   //Calcula o valor medio de cada licitacao de item adquirido pela UG
    public String[][] mediaLicitacaoItemUG (String ug,String dados[][]){
		
    	 ArrayList<String> itens = identificadorItemUG(ug, dados);
         String[][] frequencia = new String[itens.size()][2];
  
         int qtdeLinhas = dados.length;
         int qtdeItens = itens.size();
         float contAux=0;
         float sumAux=0;
         
  
         for(int k = 0; k < qtdeItens; k++){
             contAux=0;
             sumAux=0;
             for (int i = 0; i < qtdeLinhas; i++) {
                 if(dados[i][3].equals(ug) && dados[i][9].equals(itens.get(k))){
                     sumAux+=Float.parseFloat(dados[i][14]);
                     contAux++;
                 }
             }
             frequencia[k][0] = itens.get(k);
             frequencia[k][1] = String.valueOf((float)sumAux/contAux);
         }
  
         return frequencia;
    	
    }
    
   //Calcula o valor medio de cada licitacao de item vendido pelo fornecedor
    public String[][] mediaLicitacaoItemFornecedor (String fornecedor,String dados[][]){
		
    	 ArrayList<String> itens = identificadorItemFornecedor(fornecedor, dados);
         String[][] frequencia = new String[itens.size()][2];
  
         int qtdeLinhas = dados.length;
         int qtdeItens = itens.size();
         float contAux=0;
         float sumAux=0;
         
  
         for(int k = 0; k < qtdeItens; k++){
             contAux=0;
             sumAux=0;
             for (int i = 0; i < qtdeLinhas; i++) {
                 if(dados[i][4].equals(fornecedor) && dados[i][9].equals(itens.get(k))){
                     sumAux+=Float.parseFloat(dados[i][14]);
                     contAux++;
                 }
             }
             frequencia[k][0] = itens.get(k);
             frequencia[k][1] = String.valueOf((float)sumAux/contAux);
         }
  
         return frequencia;
    	
    }
     
    //ok
    public float mediaParticipantes(String[][] dados) {
      float qtdeLinhas = dados.length;
      float acumulador = 0;
      float valor;
      
      for (int i = 0; i < qtdeLinhas; i++) {
    	  try{
    		  valor = Float.parseFloat(dados[i][2]);
    	  }catch (Exception e) {
    		  valor = (float) 0.0;
    	  }
          acumulador += valor;
      }
      return acumulador/qtdeLinhas;
  }
   
  	// ok
    public float mediaParticipantesPorUG(String codUG, String[][] dados) {
        float qtdeLinhas = dados.length;
        float acumulador = 0;
        float qtdeLicitacoesUG = 0;
       
        for (int i = 0; i < qtdeLinhas; i++) {
            if (codUG.equals(dados[i][3])) {
                float valor = Integer.parseInt(dados[i][2]);
                acumulador += valor;  
                qtdeLicitacoesUG++;
            }
           
        }
        return acumulador/qtdeLicitacoesUG;
    }

    public float mediaParticipantesPorFornecedor(String codFornecedor, String[][] dados) {
        float qtdeLinhas = dados.length;
        float acumulador = 0;
        float qtdeLicitacoesUG = 0;
       
        for (int i = 0; i < qtdeLinhas; i++) {
          try{
            if (codFornecedor.equals(dados[i][4])) {
                float valor = Integer.parseInt(dados[i][2]);
                acumulador += valor;  
                qtdeLicitacoesUG++;
            }
          }catch (Exception e) {
			// TODO: handle exception
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
            } else if (modalidadeAtual.equals("6")) {
                contadorPregoPTotal++;
            } else if (modalidadeAtual.equals("10")) {
                contadorInegibilidadeTotal++;
            }
            if (ug.equals(ugAtual)) {
                if (modalidadeAtual.equals("8")) {
                    contadorDispensa++;
                } else if (modalidadeAtual.equals("6")) {
                    contadorPregaoP++;
                } else if (modalidadeAtual.equals("10")) {
                    contadorInegibilidade++;
                }
                contadorLicitacoesNaUG++;
            }
        }
 
        ArrayList<Float> contadores = new ArrayList<Float>();
 
       
        //Percentual modalidade na UG
        
        float tempUG = 0;
        
        if(contadorLicitacoesNaUG == 0){
        	tempUG = 0;
        } else {
        	tempUG = (float) contadorDispensa/contadorLicitacoesNaUG;
        	contadores.add(0, tempUG);
        
        	tempUG = (float) contadorPregaoP/contadorLicitacoesNaUG;
        	contadores.add(1,  tempUG);
        
        	tempUG = (float) contadorInegibilidade/contadorLicitacoesNaUG;
        	contadores.add(2, tempUG);
        }
        
       
        //Percentual modalidade da UG com rela��o ao total
        float tempTotal = 0;
        if (contadorDispensaTotal == 0){
        	tempTotal = 0;
        } else {
        	tempTotal = (float) contadorDispensa/contadorDispensaTotal;	
        }
        
        contadores.add(3, tempTotal);

        if (contadorPregoPTotal == 0) {
        	tempTotal = 0;
        } else {
        	tempTotal = (float) contadorPregaoP/contadorPregoPTotal;
        }
        
        contadores.add(4, tempTotal);
        
        if (contadorInegibilidadeTotal == 0) {
        	tempTotal = 0;
        } else {
        	tempTotal = (float) contadorInegibilidade/contadorInegibilidadeTotal;
        }
        
        contadores.add(5, tempTotal);
 
        return contadores;
    }

    public int somaContadorModalidadeUG(String ug, String[][] dados){
        int qtdeLinhas = dados.length;
        int qtdeModalidadesProblematicas = 0;
        for (int i = 0; i < qtdeLinhas; i++) {
            String ugAtual= dados[i][3];
            String modalidadeAtual = dados[i][0];
            
            if (ug.equals(ugAtual)) {
                if (modalidadeAtual.equals("8") || modalidadeAtual.equals("6") 
                		|| modalidadeAtual.equals("10")) {
                    qtdeModalidadesProblematicas++;
                }
            }
        }
        return qtdeModalidadesProblematicas;
    }
    
    public int somaContadorModalidadeFornecedor(String fornecedor, String[][] dados){
        int qtdeLinhas = dados.length;
        int qtdeModalidadesProblematicas = 0;
        for (int i = 0; i < qtdeLinhas; i++) {
            String fornecedorAtual= dados[i][4];
            String modalidadeAtual = dados[i][0];
            
            try{
	            if (fornecedor.equals(fornecedorAtual)) {
	                if (modalidadeAtual.equals("8") || modalidadeAtual.equals("6") 
	                		|| modalidadeAtual.equals("10")) {
	                    qtdeModalidadesProblematicas++;
	                }
	            }
            }catch (Exception e) {
				// TODO: handle exception
			}
        }
        return qtdeModalidadesProblematicas;
    }
    
    public ArrayList<Float> itemMaisConsumido (String codUG, String[][] dados) {
        ArrayList<Float> itemMaisConsumido = new ArrayList<Float>();
        ArrayList<String> itensUG = new ArrayList<String>();
 
        //Constroi vetor de itens da UG
        for (int i = 0; i < dados.length; i++) {
            if(codUG.equals(dados[i][3])) {
                itensUG.add(dados[i][9]);
            }
        }
        //Verifica qual item mais se repetiu
        int maisRepetido = 0;
        String maisRepetida = "";
        for (int i = 0; i < itensUG.size(); i++) {
            String atual = itensUG.get(i);
            int contadorAtual = 0;
            for (int j = 0; j < dados.length; j++) {
                if (atual.equals(dados[j][9])) {
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
        int contaux=0;
        for (int i = 0; i < dados.length; i++) {
            float valor = Float.parseFloat(dados[i][6]);
            if (codUG.equals(dados[i][3])){
                desvioPadraoNaUG += (Math.pow((valor - precoMedioNaUG), 2));
                contaux ++;
            }
            desvioPadraoTotal += (Math.pow((valor - precoMedioTotal), 2));
        }
       
        desvioPadraoNaUG = (float) Math.sqrt(desvioPadraoNaUG/(contaux));
        desvioPadraoTotal = (float) Math.sqrt(desvioPadraoTotal/(dados.length));
 
       
        itemMaisConsumido.add(precoMedioNaUG);
        itemMaisConsumido.add(desvioPadraoNaUG);
        itemMaisConsumido.add(precoMedioTotal);
        itemMaisConsumido.add(desvioPadraoTotal);
 
        return itemMaisConsumido;
    }  
 
    public ArrayList<String[]> motorInferenciaUG(String[][] dados){
        ArrayList<String[]> indiciosUG = new ArrayList<String[]>();
        ArrayList<String> ugs = identificadorUG(dados);
      
        dados = calcularValorTotalItem(dados);
       
        //imprimir(ugs);
        float mediaParticipantesGeral = mediaParticipantes(dados);
 
        for (String i : ugs) {
            String[] detalhesUG = new String[3];
           
            boolean flag = false;
            
            //Regra de modalidades de aquisicaoo de iten
            
            float soma = somaContadorModalidadeUG(i, dados);
            float contadorLicitacao = contadorLicitacaoUG(i, dados);
            soma = (float) soma / contadorLicitacao;
            float frequencia = frequenciaMax(frequenciaFornecedorVencedorUG(i, dados));
            frequencia = (float) frequencia / contadorLicitacao;
            
            
            if (soma > 0.9) {
                flag = true;
            }
            
            if (mediaParticipantesPorUG(i, dados) < 1.5 * mediaParticipantesGeral) {
            	flag = true;
            }
            
            if (frequencia > 0.51){
                flag = true;
            }
           
            if (flag == true){
                detalhesUG[0] = i;
                detalhesUG[1] = String.valueOf(frequenciaMax(mediaItemUG(i, dados)));
                detalhesUG[2] = String.valueOf(somaContadorModalidadeUG(i, dados));

                indiciosUG.add(detalhesUG);
            }
           
        }
 
        return indiciosUG;
    }

    public ArrayList<String[]> motorInferenciaFornecedor(String[][] dados){
        ArrayList<String[]> indiciosFornecedor = new ArrayList<String[]>();
        ArrayList<String> fornecedores = identificadorFornecedor(dados);
        dados = calcularValorTotalItem(dados);
       
        float mediaParticipantesGeral = mediaParticipantes(dados);
 
        for (String i : fornecedores) {
            String[] detalhesFornecedor = new String[3];

            boolean flag = false;
            
            //Regra de modalidades de aquisicaoo de iten
            
            float soma = somaContadorModalidadeFornecedor(i, dados);
            float contadorLicitacao = contadorLicitacaoFornecedor(i, dados);
            soma = (float) soma / contadorLicitacao;
            float frequencia = frequenciaMax(frequenciaUgVencedorFornecedor(i, dados));
            frequencia = (float) frequencia / contadorLicitacao;
            float outras = contadorOutrasColocacoes(i, dados);
            outras = (float) outras / contadorLicitacao;
            
            if (soma > 0.9) {
                flag = true;
            }
            
            if (outras > 0.2) {
            	flag = true;
            }
            
            if (mediaParticipantesPorFornecedor(i, dados) < 1.5 * mediaParticipantesGeral) {
            	flag = true;
            }
            
            if (frequencia > 0.50){
                flag = true;
            }
           
            if (flag == true){
                detalhesFornecedor[0] = i;
                detalhesFornecedor[1] = String.valueOf(frequenciaMax(mediaItemFornecedor(i, dados)));
                detalhesFornecedor[2] = String.valueOf(somaContadorModalidadeFornecedor(i, dados));

                indiciosFornecedor.add(detalhesFornecedor);
            }
           
        }
 
        return indiciosFornecedor;
    }
    /*public ArrayList<String[]> motorInferenciaUG(String[][] dados){
        ArrayList<String[]> indiciosUG = new ArrayList<String[]>();
        ArrayList<String> ugs = identificadorUG(dados);
        ArrayList<Float> contadorModalidade = new ArrayList<Float>();
        ArrayList<Float> itemMaisConsumido = new ArrayList<Float>();
       
        //imprimir(ugs);
        float mediaParticipantesGeral = mediaParticipantes(dados);
 
        for (String i : ugs) {
            String[] detalhesUG = new String[13];
            
            contadorModalidade = contadorModalidade(i, dados);
           
            itemMaisConsumido=itemMaisConsumido(i, dados);
           
            boolean flag = false;
            
            //Regra de modalidades de aquisi��o de iten
            
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
          if (mediaParticipantesPorUG(i, dados) < 1.3 * mediaParticipantesGeral) {
              flag = true;
          }
            if (frequenciaMax(frequenciaFornecedorVencedorUG(i, dados)) > 0.2){
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
                detalhesUG[11] = String.valueOf(mediaParticipantesGeral);
                detalhesUG[12] = String.valueOf(frequenciaMax(frequenciaFornecedorVencedorUG(i, dados)));
            }
           
            indiciosUG.add(detalhesUG);
        }
       
        imprimire(indiciosUG);
 
       
 
        return indiciosUG;
    }
    
    */

    public ArrayList<String[]> motorInferenciaFornecedorSE(String[][] dados){
        ArrayList<String[]> indiciosFornecedor = new ArrayList<String[]>();
        ArrayList<String> fornecedores = identificadorFornecedor(dados);
        dados = calcularValorTotalItem(dados);
        
        float mediaParticipantesGeral = mediaParticipantes(dados);
 
        for (String i : fornecedores) {
            String[] detalhesFornecedor = new String[3];

            float acumuladorNotas = 0;
            
            //Regra de modalidades de aquisicaoo de iten
            
            float soma = somaContadorModalidadeFornecedor(i, dados);
            float contadorLicitacao = contadorLicitacaoFornecedor(i, dados);
            soma = (float) soma / contadorLicitacao;
            float frequencia = frequenciaMax(frequenciaUgVencedorFornecedor(i, dados));
            frequencia = (float) frequencia / contadorLicitacao;
            float outras = contadorOutrasColocacoes(i, dados);
            outras = (float) outras / contadorLicitacao;
            
            String[][] med = mediaItemFornecedor(i, dados);
            
            for(String[] m: med){
            	
            	int qtdLinhas = dados.length;
            	
            	for(int l =0; l<qtdLinhas; l++){
            		try{
            			if(dados[l][4].equals(i)&&dados[l][9].equals(m[0])
            					&&(Float.parseFloat(dados[l][6])>Float.parseFloat(m[1])*1.5)){
            						acumuladorNotas += Integer.parseInt(pesos[0]);
            						break;
            			}
            		}catch (Exception e) {
						// TODO: handle exception
					}
            	}
            	
            	
            	
            }
            
            if (soma > 0.9) {
                acumuladorNotas += Integer.parseInt(pesos[1]);
            }
            
            if (outras > 0.2) {
            	acumuladorNotas += Integer.parseInt(pesos[2]);
            }
            
            if (mediaParticipantesPorFornecedor(i, dados) < 1.5 * mediaParticipantesGeral) {
            	acumuladorNotas += Integer.parseInt(pesos[3]);
            }
            
            if (frequencia > 0.50){
                acumuladorNotas += Integer.parseInt(pesos[4]);
            }
            
            int somaP = Integer.parseInt(pesos[0]) +
            		Integer.parseInt(pesos[1]) +
            		Integer.parseInt(pesos[2]) +
            		Integer.parseInt(pesos[3]) +
            		Integer.parseInt(pesos[4]);
            
            //21 e a soma dos pesos das regras, ao inserir outras regras somar pesos
            float mediaPonderada = (float) acumuladorNotas / somaP;
            if(mediaPonderada>1.0){mediaPonderada =(float) 1.00;}
            
            String classificacao = "";
            if (acumuladorNotas <= 4) {
            	classificacao = "OTIMO";
            } else if (acumuladorNotas > 4 && acumuladorNotas <= 8) {
            	classificacao = "BOM";
            } else if (acumuladorNotas > 8 && acumuladorNotas <= 12) {
            	classificacao = "REGULAR";
            } else if (acumuladorNotas > 12 && acumuladorNotas <= 16) {
            	classificacao = "RUIM";
            } else if (acumuladorNotas > 16) {
            	classificacao = "PESSIMO";
            }
            
            detalhesFornecedor[0] = i;
            detalhesFornecedor[1] = classificacao;
            detalhesFornecedor[2] = String.valueOf(mediaPonderada);

            indiciosFornecedor.add(detalhesFornecedor);
            }
           
        return indiciosFornecedor;
    }
    
    public void imprimir (ArrayList<String> d){
        for(String s: d ) {
            System.out.println(s.toString());
        }  
       
    }
    
    public void imprimirP (String[] d){
        for(String s: d ) {
            System.out.print(s.toString());
            System.out.print(">");
        }  
       
    }
    
    public void imprimirFloat (ArrayList<Float> d){
        for(Float s: d ) {
            System.out.println(s.toString());
        }  
       
    }
   
    public void imprimire (ArrayList<String[]> d){
        for(String[] s: d ) {
            for(String ss: s){
                System.out.print(ss.toString());
                System.out.print("|");
            }
            System.out.println("");
        }  
       
    }
    
    public StringBuffer formatarResultado(ArrayList<String[]> d){
    	
    	StringBuffer b = new StringBuffer();
    	int cont=0;
    	for(String[] s: d ) {
            cont=0;
    		for(String ss: s){
	    			
    			if(ss==null){
    				break;
    			}	
    				b.append(ss.toString());
	            if(cont!=2){
	            	b.append("|");
	                }
	                cont++;
    		}
            b.append("\n");
        }
		return b;  
       
    	
    }
   
    public void imprimird (String[][] d){
        int ia= d.length;
        int ja =d[0].length;
        for(int i=0; i<ia; i++) {
            for(int j=0; j<ja;j++){
                System.out.print(d[i][j]);
                System.out.print("  ");
            }
            System.out.println("FIM LINHA");
           
           
        }  
    }  
    
       
    
}
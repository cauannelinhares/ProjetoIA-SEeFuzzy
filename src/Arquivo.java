import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;


public class Arquivo {
	
	public File escolherArquivos(){
        File[] arquivos  = null;
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Escolha o(s) arquivo(s)...");
        fc.setDialogType(JFileChooser.OPEN_DIALOG);
        fc.setApproveButtonText("OK");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(true);
        int resultado = fc.showOpenDialog(fc);
        if (resultado == JFileChooser.CANCEL_OPTION){
            System.exit(1);
        }
        arquivos = fc.getSelectedFiles();
        return arquivos[0];
    }
	
	void salvaCollection(String s, String nomeArquivo) {  
		try {  
			PrintWriter pw = new PrintWriter (nomeArquivo);
			pw.println (s);
			pw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}

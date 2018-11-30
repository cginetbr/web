package conectads;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Parametros {
	private Properties prop;
	private log log;
	Parametros()  {
		
		try {
			
		log = new log();
		
		prop = new Properties();
		FileInputStream fi = new FileInputStream("application.prop");
		prop.load(fi);
	
		}catch (Exception e) {
			// TODO: handle exception
			log.log(e);
			
		}
		
	}
	
	public void SetParametro(String campo, String Valor) {
		
		prop.setProperty(campo, Valor);
		
	}
	
	public void salvar() {
		
		try {
			prop.store(new FileOutputStream("application.prop"),"Properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public String getParametro(String campo) {
		
		return  prop.getProperty(campo);
		
	}
	
	
	
	
	
	
}

package conectads;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class log {

	Logger logger;
	FileHandler fh;
	
	private String path = "./";
	
	public log() throws SecurityException, IOException {
		
		logger = Logger.getLogger("conectads"); 
		 fh = new FileHandler("./conectads.log");  
	 	logger.addHandler(fh);
	 	
	 	SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);
	
	}
	
	public void log (Exception e){
		
		
		StringBuilder builder = new StringBuilder();
		StackTraceElement[] trace = e.getStackTrace();
		    for (StackTraceElement traceElement : trace)
		        builder.append("\tat " + traceElement + "\n");
				
		    logger.log(Level.FINE, builder.toString());
		    System.out.println(builder.toString());
		
	}
	
	public void log (Throwable throwable){
		
		StringBuilder builder = new StringBuilder();
		StackTraceElement[] trace = throwable.getStackTrace();
		    for (StackTraceElement traceElement : trace)  builder.append("\tat " + traceElement + "\n");
				
		    logger.log(Level.FINE, builder.toString());
		    System.out.println(builder.toString());
		
	}
	
	public void log(String texto) {
		
		 logger.log(Level.INFO, texto);
		 System.out.println(texto);
		 
	}
	
	
}

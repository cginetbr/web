package application;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javafx.scene.web.WebEngine;

public class Barramento {
	
	
	private String obuIdativo;
	private Boolean executando = false;
	private Mqtt mq;
	private WebEngine wb;
	public static final long TEMPO = (1000) * 6; // 6 segundos minimo de execução
	public Timer timer = null;
	 
	Barramento() throws Exception {
	
		inicializaMq();
				
	} 
		
	private void inicializaMq() {
		
		try {
			
			mq = new clsMq();
 		 
			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 

	}
	
	public void formataHTML() {
					
		
				
		 
	}
			
	
	
	
	
	public void processaMsg(String topic, MqttMessage message) {
		
		if(executando ) {
			
			System.out.println("Video em execução");
						
		}else {
		
				executando = true;
				
				System.out.println("Executou: " + message.toString());
				
				timer = new Timer();
				TimerTask tarefa = new TimerTask() {
					public void run() {
						try {
							System.out.println("Parou: " + message.toString());
							
							
							
							executando = false;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				timer.schedule(tarefa, TEMPO);
				
		}
		
		
	}
	
	public class clsMq extends Mqtt {

		clsMq() throws MqttException {
			
			super();
			// TODO Auto-generated constructor stub
		
			this.setCallBack(new MqttCallback() {
				        @Override
				        public void connectionLost(Throwable cause) {
				          // msg("Connection lost...");
				        }

				        @Override
				        public void messageArrived(String topic, MqttMessage message) throws Exception {
				        	processaMsg(topic,message);
				        }

				        @Override
				        public void deliveryComplete(IMqttDeliveryToken token) {
				        	
				        }
				    }	);
			
			this.connect();
			
		
		}

		
		
		
	}
	
	
}

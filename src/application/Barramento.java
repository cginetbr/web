package application;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javafx.scene.web.WebEngine;

public class Barramento {

	private Mqtt mq;
	private WebEngine wb;
	
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
		
	
	public class clsMq extends Mqtt {

		clsMq() throws MqttException {
			super();
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void messageArrived(String topic, MqttMessage message) throws Exception {
			
			
			
		}
		
		
	}
	
	
}

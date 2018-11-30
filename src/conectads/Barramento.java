package conectads;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javafx.scene.web.WebEngine;


public class Barramento {

	private Boolean executando = false;
	private Mqtt mq;
	public static final long TEMPO = (1000) * 8; // 6 segundos minimo de execução
	public static final long Alive = (1000) * 10; // 6 segundos minimo de execução
	public Timer timer = null;
	public Timer timerAlive = null;
	public Timer timerAlteraMidiaPadrao = null;
	public Mysql mysql = null;
 
	private String v_id, v_nomePista,v_urlOrquestrador;
	private Modal modal;
	private log log;
	private Parametros parm;
	Barramento() throws Exception {
		
		log = new log();
		 
		parm = new Parametros();
		
		
		inicializaMq();
		inicializaMysql();
 		ocupaMidia(); // ocupa midia sem passagem	
	
		
	}
	
	
 
	
	private void inicializaMq() {

		try {

			mq = new clsMq();
			Thread threadMQ = new Thread(mq);
			threadMQ.start();

		} catch (MqttException e) {
	 
			e.printStackTrace();
			log.log(e);
		}

	}

	private void inicializaMysql() {

		mysql = new Mysql();
		Thread threadMysql = new Thread(mysql);
		threadMysql.start();

	}

	public void formataHTML() {

	}

	public void processaMsg(String topic, MqttMessage message) {

		String texto = message.toString();
 
		if (texto.indexOf("{") != 0) { // mensagem fora do padrao

			log.log("Parou: " + message.toString());

		} else if (executando) { // nova entrada com video executando
 
			log.log("Video em execução");

		} else {

			executando = true;
			
			timerAlteraMidiaPadrao.cancel();
			
			timer = new Timer();
			
			habilitaView(texto, false);
			
			TimerTask tarefa = new TimerTask() {
				public void run() {
					try {
						
						System.out.println("Parou: " + texto);
						log.log("Parou: " + texto);
						executando = false;
						ocupaMidia();
						
					} catch (Exception e) {
						
						 
						log.log(e);
						
						
					}
				}
			};
			timer.schedule(tarefa, TEMPO);
		}

	}

	private void ocupaMidia() {
		
	 
			timerAlteraMidiaPadrao = new Timer();
			
			TimerTask tarefa = new TimerTask() {
				public void run() {
					try {					
						System.out.println("Midia Padrao: sim");
						log.log("Midia Padrao: sim");
						habilitaView("{}", true);
					} catch (Exception e) {
						
						 
						log.log(e);
						
					}
				}
			};
			
			timerAlteraMidiaPadrao.scheduleAtFixedRate(tarefa, 0, TEMPO);
		
	 
		
	}
	
	
	
	private void habilitaView(String texto, Boolean aleatorio) {
		 
		
		
		JsonParser parser = new JsonParser();
		JsonObject o = parser.parse(texto).getAsJsonObject();
		
		String placa = "";
		String obuId = "";
		
		if(texto.indexOf("obuId") > 0)
			obuId = o.get("obuId").getAsString();
		
		if(texto.indexOf("placa") > 0)
			placa = o.get("placa").getAsString();
		
		try {
					
				if(placa.length() > 0) {
					modal.setSite(parm.getParametro("urlOrquestrador") +"?placa="+ placa);
					System.out.println("Executa-placa: " + placa);
					log.log("Executa-placa: " + placa);
				}
				else if(obuId.length() > 0) {
					modal.setSite(parm.getParametro("urlOrquestrador") +"?obuid="+ obuId); 
					System.out.println("Executa-obuId: " + obuId);
					log.log("Executa-obuId: " + obuId);
				}
				else if(aleatorio.equals(true)) { 
					modal.setSite(parm.getParametro("urlOrquestrador") +"?aleatorio=true"); 
					System.out.println("Executa-aleatorio: S");
					log.log("Executa-aleatorio: S");
					
				}
				else {
					modal.setSite(parm.getParametro("urlOrquestrador"));
					log.log("Executa-sem-id: " + texto);
				}
			
			}	
			catch (Exception e) {
				 
				log.log(e.getMessage());
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
					processaMsg(topic, message);
				}

				@Override
				public void deliveryComplete(IMqttDeliveryToken token) {
					// posta("Entrou");
					// System.out.println("Entrega completa");
				}

			});

			this.connect();

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

		}

	}


	public void setModal(Modal m) {
		// TODO Auto-generated method stub
		this.modal = m;
		
	}
	
	public void setModalMain(Modal m) {
 
		
	}
	

}

package conectads;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Mqtt implements Runnable {

	private final int qos = 1;
	private String topic = "stream";
	private Boolean us = false;

	MqttClient client;
	Properties prop;
	MqttConnectOptions conOpt;

	Mqtt() throws MqttException {
		// client = new MqttClient("tcp://m15.cloudmqtt.com:19041", "erpswmon");

		Boolean conectado = false;

		String servidor = "";
		String porta = "";

		String host = "tcp://34.238.150.50:19041";

		String username = "erpswmon";
		String password = "ku5X6wIwkxBC";
		String clientId = "monitor2";

		prop = new Properties();

		FileInputStream fi;
		try {
			fi = new FileInputStream("application.prop");
			prop.load(fi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		servidor = prop.getProperty("servidorMqtt");
		porta = prop.getProperty("portaMqtt");
		username = prop.getProperty("usuarioMqtt");
		password = prop.getProperty("senhaMqtt");
		clientId = prop.getProperty("id");
		
		topic = clientId;
		
		
		host = "tcp://" + servidor + ":" + porta;

		conOpt = new MqttConnectOptions();
		conOpt.setCleanSession(true);

		if (username.isEmpty() == false) {
			us = true;
			conOpt.setUserName(username);
			conOpt.setPassword(password.toCharArray());
		}
		client = new MqttClient(host, clientId, new MemoryPersistence());

	}

	public void connect() throws MqttSecurityException, MqttException {

		if (us.equals(false))
			client.connect();
		else {
			client.connect(conOpt);
			client.subscribe(this.topic, qos);
		}

	}

	public Boolean conectado() {

		return client.isConnected();

	}

	public void setCallBack(MqttCallback cb) {

		client.setCallback(cb);

	}

	public void posta(String txt) {

		MqttMessage message = new MqttMessage();
		message.setPayload(txt.getBytes());
		try {
			client.publish(this.topic, message);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void comandoMqtt(String ip, String porta, String comando) {
		try {
			String comandos = ip + "&" + porta + "&" + comando; // string que recebe e concatena três parâmetros
																// recebidos na função
			client.subscribe(topic);// tópico de subscrição é o mesmo valor do IP
			MqttMessage message = new MqttMessage();
			message.setPayload(comandos.getBytes());
			client.publish(topic, message);// tópico onde publica é o mesmo valor do ip e o outro parâmetro é a mensagem
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
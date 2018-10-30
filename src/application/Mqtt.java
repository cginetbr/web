package application;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Mqtt implements MqttCallback {

	private final int qos = 1;
	private String topic = "passagem";
	
	private static Mqtt INSTANCE;

MqttClient client;
//String ipPatchPanel;
//String mensagem = "D" + "numeroPorta" + "comando";
String topico = "Stream";

Mqtt() throws MqttException {
   // client = new MqttClient("tcp://m15.cloudmqtt.com:19041", "erpswmon");
    
    String host  = "tcp://m15.cloudmqtt.com:19041";
    String username = "erpswmon";
    String password = "ku5X6wIwkxBCp";
    String clientId = "MQTT-Java-Example";
    

    MqttConnectOptions conOpt = new MqttConnectOptions();
    conOpt.setCleanSession(true);
    conOpt.setUserName(username);
    conOpt.setPassword(password.toCharArray());
    
    client = new MqttClient(host, clientId, new MemoryPersistence());
    client.setCallback(this);
    client.connect(conOpt);
   
    client.subscribe(this.topic, qos);
    
}

public void posta(String txt) {
	
	
	MqttMessage message = new MqttMessage();
    message.setPayload("Texto".getBytes());
    try {
		client.publish(this.topic, message);
	} catch (MqttException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}


//Singleton
public static Mqtt getInstance() throws MqttException {
    if (INSTANCE == null) {
        return new Mqtt();
    } else {
        return INSTANCE;
    }
}

public void comandoMqtt(String ip, String porta, String comando) {
    try {
        String comandos = ip + "&" + porta + "&" + comando; // string que recebe e concatena três parâmetros recebidos na função
        client.subscribe(topico);//tópico de subscrição é o mesmo valor do IP
        MqttMessage message = new MqttMessage();
        message.setPayload(comandos.getBytes());
        client.publish(topico, message);//tópico onde publica é o mesmo valor do ip e o outro parâmetro é a mensagem
    } catch (MqttException e) {
        e.printStackTrace();
    }
}

@Override
public void connectionLost(Throwable cause) {
    // TODO Auto-generated method stub

}

@Override
public void messageArrived(String topic, MqttMessage message) throws Exception {
    System.out.println(message);
 
}

 

@Override
public void deliveryComplete(IMqttDeliveryToken token) {
    // TODO Auto-generated method stub

}

}
package it.unina.p2.jmsrmi.client;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ReceiverJMS implements Runnable {

	@Override
	public void run() {
		Hashtable<String, String> properties = new Hashtable<String,String>();
		properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		properties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
		properties.put("queue.cons", "consumo");
	
		try {
			Context jndiContext = new InitialContext(properties);
			QueueConnectionFactory qcf = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
			Queue cons = (Queue)jndiContext.lookup("cons");
			
			QueueConnection qc = qcf.createQueueConnection();
			qc.start();
			
			QueueSession qs = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			QueueReceiver receiver = qs.createReceiver(cons);
			receiver.setMessageListener(new MyListener());
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}
	
	private class MyListener implements MessageListener{

		@Override
		public void onMessage(Message arg0) {
			TextMessage msg = (TextMessage)arg0;
			try {
				System.out.println("Ricevuto messaggo! -> " + msg.getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}

package hit.hitnslab.ProducerSDK;

import java.util.Properties;

import scala.collection.Seq;
import kafka.producer.KeyedMessage;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import kafka.producer.async.EventHandler;
import kafka.serializer.StringEncoder;

public class HITProducer {
	KeyedMessage keyedMessage;
	Producer producer;
	String topic;
	public HITProducer(String topic) {
		this.topic=topic;
		 Properties properties = new Properties();  
         properties.put("zookeeper.connect", "localhost:2181");//声明zk  
         properties.put("serializer.class", StringEncoder.class.getName());  
         properties.put("metadata.broker.list", "localhost:9092");// 声明kafka broker  
         producer= new Producer<Integer, String>(new ProducerConfig(properties));  
		// TODO Auto-generated constructor stub
	}
	public void HITsend( String message){
		producer.send(new KeyedMessage<Integer, String>(topic, message));  
	}


}

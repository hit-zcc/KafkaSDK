package hit.hitnslab.ProducerSDK;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import UserControl.KafkaUser;
import UserControl.KafkaUserFactory;
import UserControl.TopicFactory;
import scala.collection.Seq;
import kafka.producer.KeyedMessage;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import kafka.producer.async.EventHandler;
import kafka.serializer.StringEncoder;

public class HITProducer implements IProducer {
	private static Logger logger = Logger.getLogger(HITProducer.class);  
	private KeyedMessage keyedMessage;
	private Producer producer;
	private String topic;
	private KafkaUser user;
	
	public HITProducer(String topic,KafkaUser user) throws NoSuchAlgorithmException {
		this.topic=topic;
		this.user=user;
		try {
			if(!user.getZk().getChildren("/USER", false, null).contains(topic)){
			  List list=new ArrayList();
			  list.add(user);
				logger.info(topic+" is not created ");
				logger.info(topic+" is  creating ");
				user.createPowerTopic(topic, list);
				logger.info(topic+"has  created ");
			}
			else if(!user.getZk().getChildren("/USER/"+topic, false, null).contains(user.getUserName())){
				logger.error(user.getUserName()+" has no right to use this topic ");
			}
			else{
				logger.info(user.getUserName()+" has  right to use this topic ");
			 Properties properties = new Properties();  
			 properties.put("zookeeper.connect", "localhost:2181");//声明zk  
			 properties.put("serializer.class", StringEncoder.class.getName());  
			 properties.put("metadata.broker.list", "localhost:9092");// 声明kafka broker  
			 producer= new Producer<Integer, String>(new ProducerConfig(properties));  
			// TODO Auto-generated constructor stub
			 
			}
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void HITsend(Object message){
		
		producer.send(new KeyedMessage<Integer, String>(topic, message.toString()));  
	}

}

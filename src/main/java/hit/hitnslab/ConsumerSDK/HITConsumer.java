package hit.hitnslab.ConsumerSDK;

import hit.hitnslab.ProducerSDK.HITProducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;

import UserControl.KafkaUser;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class HITConsumer {
	ConsumerConnector consumer;
	private static Logger logger = Logger.getLogger(HITConsumer.class);  
	 Map<String, Integer> topicCountMap = new HashMap<String, Integer>();  
	 KafkaUser user;
	 String topic;
  public HITConsumer(String topic,KafkaUser user){
	  this.user=user;
	  this.topic=topic;
	  try {
			if(!user.getZk().getChildren("/USER/"+topic, false, null).contains(user.getUserName())){
				logger.info(user.getUserName()+"has no right to use this topic ");
			}
			else{
	  Properties properties = new Properties();  
      properties.put("zookeeper.connect", "localhost:2181");//声明zk  
      properties.put("group.id", "topic");// 必须要使用别的组名称， 如果生产者和消费者都在同一组，则不能访问同一组内的topic数据  
      consumer= Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));  
	  topicCountMap.put(topic, 1); // 一次从主题中获取一个数据  
			}
	  }
	  catch(KeeperException e){
		  e.printStackTrace();
	  } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }  
  public KafkaStream GetMessageStreams(){
	  Map<String, List<KafkaStream<byte[], byte[]>>>  messageStreams = consumer.createMessageStreams(topicCountMap); 
	  KafkaStream<byte[], byte[]> stream = messageStreams.get(topic).get(0);
	  return stream;
  }
  public <T> List<String> GetMessageAndFillWith(T li,long num){
	   KafkaStream stream=this.GetMessageStreams();                                                               //获得消费流
	     ConsumerIterator<byte[], byte[]> iterator =  stream.iterator();     
	  //创建迭代器
List <String>list=new ArrayList();
	     for(long i=0;i<num;i++){  
	         @SuppressWarnings("unchecked")
			String  message =  new String(iterator.next().message());  
             list.add(message);                                     
	}
	 this.shutdown();
	return list; 
	 
  }
  public void shutdown(){
	  consumer.shutdown();
  }
  
}

package hit.hitnslab.ConsumerSDK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class HITConsumer {
	ConsumerConnector consumer;
	 Map<String, Integer> topicCountMap = new HashMap<String, Integer>();  
	 String topic;
  public HITConsumer(String topic){
	  this.topic=topic;
	  Properties properties = new Properties();  
      properties.put("zookeeper.connect", "localhost:2181");//声明zk  
      properties.put("group.id", "topic");// 必须要使用别的组名称， 如果生产者和消费者都在同一组，则不能访问同一组内的topic数据  
      consumer= Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));  
	  topicCountMap.put(topic, 1); // 一次从主题中获取一个数据  
    
   }  
  public KafkaStream GetMessageStreams(){
	  Map<String, List<KafkaStream<byte[], byte[]>>>  messageStreams = consumer.createMessageStreams(topicCountMap); 
	  KafkaStream<byte[], byte[]> stream = messageStreams.get(topic).get(0);
	  return stream;
  }
  public <T> T GetMessageAndFillWith(Class<T> List){
	return null; 
	 
  }
  public void shutdown(){
	  consumer.shutdown();
  }
  
}

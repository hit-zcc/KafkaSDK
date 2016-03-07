package hit.hitnslab.KafkaSDK;

import hit.hitnslab.ConsumerSDK.HITConsumer;
import hit.hitnslab.ProducerSDK.HITProducer;

import java.util.Properties;

import util.ClientMeta;
import kafka.consumer.Consumer;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

public class DefaultHITKafkaClient implements HITKafkaClient {
	    private HITProducer producer;
	    private HITConsumer consumer;
	    private ClientMeta clientmeta;
         public DefaultHITKafkaClient(String topic){
        	 producer=new HITProducer(topic);
        	 consumer=new HITConsumer(topic);
  }
         public DefaultHITKafkaClient(ClientMeta clientmeta){
        	 Properties properties = new Properties();
             properties.put("zookeeper.connect", clientmeta.getZkClient());//声明zk
             if(clientmeta.getTranstype().equals("STRINGTYPE")){
             properties.put("serializer.class", StringEncoder.class.getName());  
             }
             properties.put("metadata.broker.list", "localhost:9092");// 声明kafka broker  
//             producer= new Producer<Integer, String>(new ProducerConfig(properties));  
         }
         public HITProducer getProducer(){
        	 return this.producer;
         }
		public HITConsumer getConsumer() {
			return consumer;
		}
		public void shutdown(){
			consumer.shutdown();
		}

         
}

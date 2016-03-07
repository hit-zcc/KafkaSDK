package example;

import java.util.Iterator;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import scala.collection.immutable.Stream;
import hit.hitnslab.ConsumerSDK.HITConsumer;
import hit.hitnslab.KafkaSDK.DefaultHITKafkaClient;
import hit.hitnslab.ProducerSDK.HITProducer;


public class test {
	public static void main(String[] args) {  
     DefaultHITKafkaClient defalutHITKafkaClient=new DefaultHITKafkaClient("test");                    //创建Client
     HITProducer hitProducer=defalutHITKafkaClient.getProducer();                                                     //获得生产者
     hitProducer.HITsend("tttt");                                                                                                                            //发送消息
     
      
     HITConsumer hitConsumer=defalutHITKafkaClient.getConsumer();                                            //获得消费者
     KafkaStream stream=hitConsumer.GetMessageStreams();                                                               //获得消费流
     ConsumerIterator<byte[], byte[]> iterator =  stream.iterator();                                                   //创建迭代器
     while(iterator.hasNext()){  
         String message = new String(iterator.next().message());  
         System.out.println("接收到:------" + message);                                                                                 //接受数据
}
}
}

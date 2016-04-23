package example;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import UserControl.KafkaUser;
import UserControl.KafkaUserFactory;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import scala.collection.immutable.Stream;
import hit.hitnslab.ConsumerSDK.HITConsumer;
import hit.hitnslab.KafkaSDK.DefaultHITKafkaClient;
import hit.hitnslab.ProducerSDK.HITProducer;


public class test {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeeperException, InterruptedException {  
		Logger logger = Logger.getLogger(test.class);  
		KafkaUser user=new KafkaUser("xiaoming","localhost:2181","123");

//		KafkaUserFactory.createUser(user, "my", "123");
//		KafkaUser user1=new KafkaUser("zcc","localhost:2181","123");
//		  List list=new ArrayList();
//		  list.add(user);
//		user1.createPowerTopic("test2", list);
			// TODO Auto-generated catch block

     DefaultHITKafkaClient defalutHITKafkaClient=new DefaultHITKafkaClient("test2",user);                    //创建Client
//     
//     
//     //****************************************************************//
     HITProducer hitProducer=defalutHITKafkaClient.getProducer();                                                     //获得生产者
     hitProducer.HITsend("tttt");                                                                                                                            //发送消息
     hitProducer.HITsend("???");        
//     
      
//     HITConsumer hitConsumer=defalutHITKafkaClient.getConsumer();                                            //获得消费者
//     KafkaStream stream=hitConsumer.GetMessageStreams();                                                               //获得消费流
//     ConsumerIterator<byte[], byte[]> iterator =  stream.iterator();                                                   //创建迭代器
//     while(iterator.hasNext()){  
//         String message = new String(iterator.next().message());  
//         System.out.println("接收到:------" + message);                                                                                 //接受数据
//}
    List<String> s=new ArrayList();
     s= hitConsumer.GetMessageAndFillWith(new String(),2);
//    System.out.println( s.get(0));
//    System.out.println( s.get(1));
}
}

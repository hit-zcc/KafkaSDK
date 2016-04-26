
#KafkaSDK

##原理：
用户信息存储在zookeeper中，使用的是zookeeper的ACL权限控制来进用户行数据存储的加密。

###具体用法
1.新建一个kafkaUser类，这个kafkaUser构造函数是用户名，zookeeper的地址和用户密码
例如
```java
KafkaUser user=new KafkaUser("xiaoming","localhost:2181","123");
```
2.新建一个HITKafkaClient类并提供默认，构造函数传入要操作的topic名称和要操作这个topic的用户，并用于获取生产者和消费者
当该用户无访问权限时
```java
DefaultHITKafkaClient defalutHITKafkaClient=new DefaultHITKafkaClient("test2",user);
HITProducer hitProducer=defalutHITKafkaClient.getProducer();                                                     //获得生产者
hitProducer.HITsend("tttt");                                                                                                                            //发送消息    
```
打印结果：
```java
[INFO ] 2016-04-23 19:33:53,834 method:hit.hitnslab.ConsumerSDK.HITConsumer.<init>(HITConsumer.java:32)
xiaoming has no right to use this topic 
Exception in thread "main" java.lang.NullPointerException
	at hit.hitnslab.ProducerSDK.HITProducer.HITsend(HITProducer.java:67)
	at example.test.main(test.java:40)
```
如果需要使某一用户可以使用该topic，则必须使用一个已经可以使用该topic的用户对他进行授权操作
例如
```java
KafkaUser user1=new KafkaUser("zcc","localhost:2181","123");
List list=new ArrayList();
list.add(user);
user1.createPowerTopic("test2", list);
```
其中zcc是已有test2操作权限的用户，使用createPowerTopic（“topic名称”，授权的user组成的list）完成授权操作,完成授权后再次操作发送成功
打印结果
```java
[DEBUG] 2016-04-23 19:43:22,229 method:kafka.utils.Logging$class.debug(Logging.scala:52)
Producer sending messages with correlation id 4 for topics [test2,0] to broker 2 on zcc-Inspiron-5521:9094
[DEBUG] 2016-04-23 19:43:22,230 method:kafka.utils.Logging$class.debug(Logging.scala:52)
Producer sent messages with correlation id 4 for topics [test2,0] to broker 2 on zcc-Inspiron-5521:9094
```
总体使用代码：
生产：
```java
KafkaUser user=new KafkaUser("xiaoming","localhost:2181","123");
DefaultHITKafkaClient defalutHITKafkaClient=new DefaultHITKafkaClient("test2",user);    
HITProducer hitProducer=defalutHITKafkaClient.getProducer();        //获得生产者                                             
 hitProducer.HITsend("tttt");                                                                                                                            //发送消息    
 ```
消费两条数据：并将其加载到list中
```java
  List<String> s=new ArrayList();
  s= hitConsumer.GetMessageAndFillWith(new String(),2);
```

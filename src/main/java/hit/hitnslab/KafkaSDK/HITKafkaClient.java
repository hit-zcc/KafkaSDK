package hit.hitnslab.KafkaSDK;

import hit.hitnslab.ConsumerSDK.HITConsumer;
import hit.hitnslab.ProducerSDK.HITProducer;

public interface HITKafkaClient {
	public HITProducer getProducer();
	public HITConsumer getConsumer();
	public void shutdown();
}

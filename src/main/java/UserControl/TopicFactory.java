package UserControl;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class TopicFactory {
	public static void createTopic(String topic,KafkaUser user) throws KeeperException, InterruptedException{
		ZooKeeper zk = user.getZk();
		zk.setData("/USER/"+topic, "own".getBytes(), 1);
	}
}

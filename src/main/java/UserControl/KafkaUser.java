package UserControl;

import java.io.IOException;

import org.apache.zookeeper.data.Stat;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;


public class KafkaUser {
	private static Logger logger = Logger.getLogger(KafkaUser.class);  
	private final String userName;
	private  String password;
	private ZooKeeper zk;
	public KafkaUser( String userName, String host,String password) throws IOException{
         this.password=password;
		this.userName=userName;
		 this.zk = new ZooKeeper(host, 5000,null);
		 zk.addAuthInfo("digest", "zcc:123".getBytes());
		try {
			if(zk.getChildren("/USER", false).contains(userName)){
				logger.error("No user has created named"+userName);
			}
			else{
				logger.info("Find User named"+userName);
			}
		} catch (final KeeperException e) {
e.printStackTrace();

			e.printStackTrace();
		} catch (final InterruptedException e) {
e.printStackTrace();

		}
	}
   public void createPowerTopic(String topicName,List<KafkaUser> userList) throws KeeperException, InterruptedException, NoSuchAlgorithmException{
	   List l=zk.getChildren("/USER", false);
	   if(!l.contains(topicName)){
		   for(KafkaUser u:userList){
		   Stat stat = zk.exists("/USER/"+topicName, false); 
    	   List<ACL> acls = new ArrayList<ACL>() ;
    	   Id id1 = new Id("digest", DigestAuthenticationProvider.generateDigest(u.getUserName()+":"+u.getPassword())); 
    	   ACL acl1 = new ACL(ZooDefs.Perms.ALL, id1);
    	   acls.add(acl1);
    	   
		   zk.create("/USER/"+topicName, null,acls, CreateMode.PERSISTENT);
		   zk.create("/USER/"+topicName+"/"+u.getUserName(), "own".getBytes(),acls, CreateMode.PERSISTENT);


	   }
	   
   }
	   else{
		   for(KafkaUser u:userList){
		   Stat stat = zk.exists("/USER/"+topicName, false); 
    	   List<ACL> acls = zk.getACL("/USER/"+topicName, stat) ;
    	   Id id1 = new Id("digest", DigestAuthenticationProvider.generateDigest(u.getUserName()+":"+u.getPassword())); 
    	   ACL acl1 = new ACL(ZooDefs.Perms.ALL, id1);
    	   acls.add(acl1);
    	   zk.create("/USER/"+topicName+"/"+u.getUserName(), "follower".getBytes(),acls, CreateMode.PERSISTENT);
		   }
	   }
   }
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public ZooKeeper getZk(){
		return zk;
	}


}

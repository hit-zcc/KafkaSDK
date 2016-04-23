package UserControl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

public class KafkaUserFactory {

       public static void createUser(KafkaUser user,String name,String password) throws IOException, NoSuchAlgorithmException, KeeperException, InterruptedException{
    	   ZooKeeper zk = user.getZk();
    	   Stat stat = zk.exists("/USER", false); 
    	   List<ACL> acls = zk.getACL("/USER", stat);     
           Id id1 = new Id("digest", DigestAuthenticationProvider.generateDigest(name+":"+password)); 
           ACL acl1 = new ACL(ZooDefs.Perms.ALL, id1);
           acls.add(acl1);
           zk.setACL("/USER", acls,-1);
       }
}

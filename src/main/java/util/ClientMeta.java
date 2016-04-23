package util;

import util.Global.Transtype;

public class ClientMeta {
         private String zkClient="loclhost:2181";
        private Transtype transtype;
        public ClientMeta(){
        	this("loclhost:2181",Transtype.STRING);
        }
        public ClientMeta(String zkClient,Transtype transtype )
        {
        	this.transtype=transtype;
        	this.zkClient=zkClient;
        }
		public String getZkClient() {
			return zkClient;
		}
		public void setZkClient(String zkClient) {
			this.zkClient = zkClient;
		}
		public Transtype getTranstype() {
			return transtype;
		}
		public void setTranstype(Transtype transtype) {
			this.transtype = transtype;
		}
}

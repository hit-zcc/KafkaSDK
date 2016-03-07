package util;

public class ClientMeta {
         private String zkClient="loclhost:2181";
         private String transtype="STRINGTYPE";
		public String getZkClient() {
			return zkClient;
		}
		public void setZkClient(String zkClient) {
			this.zkClient = zkClient;
		}
		public String getTranstype() {
			return transtype;
		}
		public void setTranstype(String transtype) {
			this.transtype = transtype;
		}
}

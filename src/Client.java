import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;


public class Client {

	public static void main(String[] args) {

		Context ctx = ZMQ.context(1);
		Socket socket = ctx.socket(ZMQ.REQ);
		socket.connect("tcp://localhost:5556");


		JSONObject obj = new JSONObject();
		obj.put("name", "Fufu");
		obj.put("age", new Integer(100));

		JSONArray list = new JSONArray();
		list.add("msg 1");
		list.add("msg 2");
		list.add("msg 3");

		obj.put("messages", list);



		socket.send(obj.toJSONString(), 0);
		System.out.println(socket.recv(0));

	}


}

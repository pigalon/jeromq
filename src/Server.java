import java.nio.ByteBuffer;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;


public class Server {

	public static void main(String[] args) throws ParseException {

		Context ctx = ZMQ.context(1);
		Socket socket = ctx.socket(ZMQ.REP);
		socket.bind("tcp://*:5556");

		JSONParser parser = new JSONParser();


		while (true) {

			byte[] request = socket.recv (0);
			System.out.println("get !" +   new String(request));

			JSONObject jsonObject = (JSONObject) parser.parse(new String(request));

			String name = (String) jsonObject.get("name");
			System.out.println("name from Json : " + name);

			socket.send("World", 0);


		}

	}

}

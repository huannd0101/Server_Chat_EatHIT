package com.example.demo.socket;

import org.json.simple.JSONObject;
import org.springframework.boot.CommandLineRunner;

import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.ClientOperations;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;

public class SocketApplication{

	
	public static void startSocketApplication() {
		Configuration configuration = new Configuration();
		configuration.setHostname("localhost");
		configuration.setPort(8081);
		configuration.setTransports(Transport.POLLING);
		
		final SocketIOServer server = new SocketIOServer(configuration);
		
		//lắng nghe sự kiện từ client gửi lên
//		server.addEventListener("send-message", String.class, (client, data, ackRequest) -> {
//			System.out.println(data); //sout thử xem client gửi gì
//			//trả kết quả cho client bằng 1 event nào đó mà client phải tự nhận
//			client.sendEvent("reply-client", "bạn vừa send: " + data);
//		});
		
		
		server.addEventListener("client-leave-room", JSONObject.class, (client, data, ackRequest) -> {
			JSONObject jsonObject = (JSONObject) data;
			client.leaveRoom((String) jsonObject.get("room"));
		});
		
		//2 or nhiều client
		server.addEventListener("client-send-room", String.class, (client, data, ackRequest) -> {
			client.joinRoom(data);
		});
		
		server.addEventListener("client-send-message", JSONObject.class, (client, data, ackRequest) -> {
			JSONObject jsonObject = (JSONObject) data;
			System.out.println(data);
			String s = (String) jsonObject.get("room");
			server.getRoomOperations(s).sendEvent("server-send-message", data);
//			server.getRoomOperations("asd").sendEvent("server-send-message", data);
			//tất cả các phòng dùng chung
//			server.getBroadcastOperations().sendEvent("server-send-message", data);
		});
		
		
		
		
		
		
		server.start();
	}

}

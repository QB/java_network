import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer{
	static ServerSocket serversocket;
	static List<Socket> connections;
	static final int PORT = 8080;

	public static void sendAll(String message){
		if(connections!=null){
			for(Socket socket : connections){
				try{
					PrintWriter pw = new PrintWriter(socket.getOutputStream());
					//System.out.println(connections.size());
					pw.println(message);
					pw.flush();
					System.out.println(socket.toString()+"is opened.");
				}catch(IOException error){
					System.out.println(socket.toString()+"is closed. : "+socket.isClosed());
					error.printStackTrace();
				}
			}
		}
		System.out.println(message);
	}

	public static void addConnections(Socket socket){
		if(connections == null){
			connections = new LinkedList<Socket>();
		}
		connections.add(socket);
	}

	public static void deleteConnection(Socket socket){
		if(connections != null){
			connections.remove(socket);
		}
	}

	public static void main(String[] args) {
		try{
			serversocket = new ServerSocket(PORT);
		}catch(IOException error){
			error.printStackTrace();
			System.exit(1);
		}

		while(true){
			try{
				Socket socket = serversocket.accept();
				System.out.println("-----------新しい接続を受け付けました---------------");//for debug
				addConnections(socket);
				(new Thread(new Client(socket))).start();
			}catch(IOException error){
				System.err.println(error);
				error.printStackTrace();
			}
		}
	}
}

class Client implements Runnable{
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	String name = null;
	ChatServer server = null;

	public Client(Socket socket) throws IOException{ 
		System.out.println("新しいClientからの接続 : " + this.name);//for debug
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
	}

	public void run(){
		try{
			System.out.println("新しいスレッドが走り始めました : "+this.name);//for debug
			while(name == null){
				out.println("What's your name? : ");
				out.flush();
				name = in.readLine();
			}
			//System.out.println("名前が設定されました : "+this.name);//for debug
			String line = in.readLine();
			while(!"quit".equals(line)){
				System.out.println(socket.toString()+": のClientが動いています");
				ChatServer.sendAll(name + " : " + line);
				line = in.readLine();
			}
			ChatServer.deleteConnection(socket);
			socket.close();
		}catch(IOException error){
			try{
				socket.close();
			}catch(IOException error2){
				error2.printStackTrace();
			}
		}
	}
}




//memo:UserはUsersの内部クラスにするのはどうなんだろうか

//1:ClientがUserを持つ
//2:UserはUsersで管理
//3:Usersを持つのはServer
//4:ClientはServerのUsersを通してUserを作成&取得(認証)
//5:Usersはデータベースとやりとりする

import java.io.*;
import java.util.*;

public class User{
	private String name = null; 
	private String password = null;

	//Clientから呼び出す　ここでバリデイトしてClientでtrueが返ってくるまで繰り返し呼び出せばよい
	//修正1：Clientではなくユーザを管理するUsersでユーザの追加、削除、認証を行う
	boolean setName(String name){
		this.name = name;
		return true;
	}

	boolean setPassword(String password){
		this.password = password;
		return true;
	}

	String getName(){
		return this.name;
	}

	String getPassword(){
		return this.password;
	}

	//for test
	public static void main(String[] args) {
		User user = new User();
		user.setName("Haru");
		user.setPassword("uni");
		System.out.println(user.getName()+":"+user.getPassword());
	}


}

class Users{
	private List<User> users = new ArrayList<User>();
	//ユーザで使う入力
	private BufferedReader in;
	//ユーザで使う出力
	private PrintWriter out;

	//users作成時にユーザで使う入出力を渡す
	public Users(BufferedReader in, PrintWriter out){
		this.in = in;
		this.out = out;
	}

	//超簡易ユーザ認証用(真偽値) 
	public boolean authenticate(String name, String password){
		for(User user : users){
			if(user.getName().equals(name)){
				if(user.getPassword().equals(password)){
					out.println("ユーザ認証に成功しました");
					out.flush();
					return true;
				}else{
					out.println("パスワードが間違っています");
					out.flush();
					return false;
				}
			}
		}
		out.println("ユーザ名が間違っています");
		out.flush();
		return false;
	}

	//超簡易ユーザ認証(User)
	public User getUser(String name, String password){
		for(User user : users){
			if(user.getName().equals(name)){
				if(user.getPassword().equals(password)){
					return user;
				}else{
					return null;
				}
			}
		}
		return null;
	}

	public boolean createUser(){
		User user = new User();
		try{
			//nameを登録
			while(true){
				out.print("Name : ");
				out.flush();
				String name = in.readLine();
				if(user.setName(name)){
					break;
				}
			}
			//passwordを登録
			while(true){
				out.print("password : ");
				out.flush();
				String password = in.readLine();
				if(user.setPassword(password)){
					break;
				}
			}
			//userを追加
			users.add(user);
		}catch(IOException error){
			error.printStackTrace();
		}
		return true;
	}

	public boolean deleteUser(){
		try{
			out.print("Name : ");
			out.flush();
			String name = in.readLine();
			
			out.print("password : ");
			out.flush();
			String password = in.readLine();
			
			User user = this.getUser(name, password);
			if(user!=null){
				this.users.remove(user);
				out.println("ユーザを消去しました");
				out.flush();

			}else{
				out.println("ユーザ認証に失敗しました");
			}
		}catch(IOException error){
			error.printStackTrace();
		}
		return true;
	}

	//for test
	public static void main(String[] args) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		Users users = new Users(in, out);
		users.createUser();
		System.out.println(users.authenticate("Haru","Uni"));
		System.out.println(users.authenticate("Haru","uni"));
		System.out.println(users.authenticate("haru","uni"));
		users.deleteUser();
		System.out.println(users.authenticate("Haru","Uni"));
		
	}
}












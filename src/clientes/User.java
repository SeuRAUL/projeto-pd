/**
 * @author SeuRAUL
 */	

package clientes;

import java.net.*;
import java.io.*;
import java.util.*;

import mensagens.Mensagem;

public class User {

	static Socket conexao;
	static int PORT;
	static boolean mestre;
	static ObjectInputStream input;
	static ObjectOutputStream output;
	
	
	public static void main(String[] args) {

		//Setando a porta para conexão na inicialização
		Scanner p = new Scanner(System.in);
		System.out.print("Porta USER: ");
		PORT = Integer.parseInt(p.nextLine());

		Scanner u = new Scanner(System.in);
		System.out.println("Aluno[0] / Mestre [1]");
		int user = Integer.parseInt(u.nextLine());
		if (user == 0)
			mestre = false;
		else
			mestre = true;

		System.out.print("Iniciando");
		if (mestre) System.out.print(" MESTRE ");
		else System.out.print(" ALUNO ");
		System.out.println("na porta " + PORT + "...");
		
		Mensagem msg = new Mensagem();

		try {
			conexao = new Socket("localhost", PORT);
			output = new ObjectOutputStream(conexao.getOutputStream());

			/*criar mensagem*/
			
			msg.edit("USER", "CONECTAR");
			Mensagem msg2 = msg;
			String mens2 = msg2.to_str();//"USER", "CONECTAR");
			System.out.println("Msg2: " + mens2);
			Mensagem msg3 = new Mensagem();
			msg3.to_obj(mens2);
			System.out.println("Mensageiro2: " + msg3.getMensageiro().toString());
			System.out.println("info2: " + msg3.getInfo());
			
			/*enviando msg*/
			output.writeObject(msg);
			output.flush();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		try {
			input = new ObjectInputStream(conexao.getInputStream());
			while (true) {
								
				msg = (Mensagem) input.readObject();
				System.out.println("Info recebida: " + msg.getInfo());
				if(msg.getInfo().equals("CONECTOU!")){
					System.out.println("Requisitando servidor");
					msg.edit("USER", "getSERVIDOR");
					output.writeObject(msg);
					output.flush();
				}
				if (msg.getInfo().equals("getSERVIDOR")){
					System.out.println("SERVIDOR RECEBIDO");
				}
			}
			
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}

	}

}

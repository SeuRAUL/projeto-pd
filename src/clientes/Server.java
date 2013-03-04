/**
 * @author SeuRAUL
 */

package clientes;

import java.net.*;
import java.util.Scanner;
import java.io.*;

import servicos.TrataReq;
import mensagens.*;

public class Server {

	static int sPORT; //Porta do servidor, escuta msgs
	static int lbPORT; //Porta de saída, envia msg ao LowBase
	static String[] treinos;

	public Server() {
		treinos = new String[50];
	}

	public void tratarConexao() {

		try {
			for (;;) {
				ServerSocket server = new ServerSocket(sPORT);
				TrataReq tratador = new TrataReq(server.accept());
				tratador.start();
				server.close();
			}
		} catch (BindException be) {
			System.err.println("Serviço ja está sendo executado na porta: " + sPORT);
		} catch (IOException ioe) {
			System.err.println("Erro de I/O - " + ioe);
		}
	}
	
	public static void main(String[] args) {

		//Setando a porta para conexão na inicialização
		Scanner p = new Scanner(System.in);
		System.out.print("Porta Server: ");
		sPORT = Integer.parseInt(p.nextLine());
		System.out.print("Porta Comunicação: ");
		lbPORT = Integer.parseInt(p.nextLine());
		System.out.println("Escutando na porta " + sPORT + "\n Enviando na porta " + lbPORT + "...");

		try {
			Mensagem msg = new Mensagem();
			Socket conexao = new Socket("localhost", lbPORT);
			ObjectOutputStream output = new ObjectOutputStream(conexao.getOutputStream());

			/*criar mensagem*/
			msg.edit("SERVER", "ON:"+sPORT);
			System.out.println("Enviando: " + msg.getInfo());
			output.writeObject(msg);
			output.flush();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Server server = new Server();
		server.tratarConexao();

		

	}

}

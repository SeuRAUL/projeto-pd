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

	static int PORT;
	static Treinos[] treinos;

	public Server() {
		treinos = new Treinos[50];
	}

	public void tratarConexao() {

		try {
			for (;;) {
				ServerSocket server = new ServerSocket(PORT);
				TrataReq tratador = new TrataReq(server.accept());
				tratador.start();
				server.close();
			}
		} catch (BindException be) {
			System.err.println("Serviço ja está sendo executado na porta: " + PORT);
		} catch (IOException ioe) {
			System.err.println("Erro de I/O - " + ioe);
		}
	} 
	
	public static void main(String[] args) {

		//Setando a porta para conexão na inicialização
		Scanner p = new Scanner(System.in);
		System.out.print("Porta Server: ");
		PORT = Integer.parseInt(p.nextLine());
		System.out.println("Iniciando na porta " + PORT + "...");

		Server server = new Server();
		server.tratarConexao();

		try {
			Mensagem msg = new Mensagem();
			Socket conexao = new Socket("localhost", PORT);
			ObjectOutputStream output = new ObjectOutputStream(conexao.getOutputStream());

			/*criar mensagem*/
			msg.edit("SERVER", "ON");
			output.writeObject(msg);
			output.flush();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

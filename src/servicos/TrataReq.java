package servicos;

import java.io.*;
import java.net.Socket;
import menssagens.mensagem;
import clientes.Lowbase;

public class TrataReq extends Thread {
	private Socket conexao;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	public TrataReq(Socket con) {
		conexao = con;
		
		try {
			input = new ObjectInputStream(conexao.getInputStream());
			output = new ObjectOutputStream(conexao.getOutputStream());
		}
		catch (IOException e) { }
	}
	
	public void run() {
		mensagem recebida = new mensagem();
		mensagem aEnviar = new mensagem();
		while(true){
			try {
				recebida = (mensagem)input.readObject();
				String endereco = recebida.getAddress().toString();
				String mensageiro = recebida.getMenssageiro().toString();

				if (mensageiro.equals("USER")) {

				}
			}
			catch (ClassNotFoundException e) { }
			catch (IOException e) { }
		}
	}
}

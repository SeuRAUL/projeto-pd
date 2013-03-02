/**
 * @author SeuRAUL
 */

package servicos;

import java.io.*;
import java.net.*;
import mensagens.Mensagem;
//import clientes.*;

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
		Mensagem recebida = new Mensagem();
		Mensagem aEnviar = new Mensagem();
		while(true){
			try {
				recebida = (Mensagem)input.readObject();
				String mensageiro = recebida.getMensageiro().toString();
				String msg = recebida.getInfo().toString();

				// USER !!
				if (mensageiro.equals("USER")) {
					if (msg.equals("CONECTAR")) {
						System.out.println("USER CONNECTED");
						aEnviar.setMensageiro("LB");
						aEnviar.setInfo("CONECTOU!");
						output.writeObject(aEnviar);
						output.flush();
					}
					else if (msg.equals("getSERVIDOR")){
						System.out.println("Enviando servidor ao user...");
						aEnviar.setMensageiro("LB");
						aEnviar.setInfo("setSERVER..."); /* !!!!!!!!!! */
						output.writeObject(aEnviar);
						output.flush();
					}
				}

				// SERVER !!
				else if(mensageiro.equals("SERVER")) {
					if( msg.equals("ON") {
						
					}
				}
			}
			catch (ClassNotFoundException e) {
				//e.printStackTrace();
			}
			catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}
}

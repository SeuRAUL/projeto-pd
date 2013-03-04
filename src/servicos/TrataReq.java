/**
 * @author SeuRAUL
 */

package servicos;

import java.io.*;
import java.net.*;
import mensagens.Mensagem;
import clientes.*;

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
						aEnviar.edit("LB", "CONECTOU!");
						/*aEnviar.setMensageiro("LB");
						aEnviar.setInfo("CONECTOU!");*/
						output.writeObject(aEnviar);
						output.flush();
					}
					else if (msg.equals("getSERVIDOR")) {
						System.out.println("Enviando servidor ao user...");
						aEnviar.edit("LB", "CONECTE:" + Lowbase.conectarMenosOcupado().toString());
						System.out.println(aEnviar.getInfo());
						System.out.println(Lowbase.conectarMenosOcupado());
						/*aEnviar.setMensageiro("LB");
						aEnviar.setInfo("CONECTE:" + Lowbase.conectarMenosOcupado());*/
						output.reset(); //.writeObject(aEnviar);
						output.writeObject(aEnviar);
						output.flush();
					}
				}

				// SERVER !!
				if(mensageiro.equals("SERVER")) {
					if( msg.contains("ON")) {
						System.out.println(msg);
						
						 if ( Lowbase.setServidor(conexao.getInetAddress().getHostName(), msg.substring(3)) )
							 System.out.println("Setou!");
						
						Lowbase.getServidores();
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

/**
 * @author SeuRAUL
 */

package clientes;

import java.net.*;
import java.util.Scanner;
import java.io.*;

import servicos.TrataReq;

public class Lowbase {
	
	static int PORT;
	/*guarda os endereços dos servidores que informarem conexão
	* [ip][porta] */
	String[][] endServidor = new String[10][2];
	//registra a quantidade de conexões em cada servidor online
	int[] conexoes = new int[10];


	/* Métodos do LowBase */

	// Informa o servidor online com menos conexões para enviar ao usuário
	public int menosOcupado(){
		int menor = 11;
		int retorno = -1
		for (int i=0; i<10; i++) {
			if (conexoes[i] != -1 && conexoes[i] < menor) {
				menor = i;
				retorno = i;
			}
		}
		return retorno;
	}

	//Insere um servidor à lista de conectados
	public boolean setServidor(String ip, String port){
		boolean setado = false;
		for (int i=0;i<10;i++) {
			if(conexoes[1] == -1) { // -1 indica posição livre
				endServidor[i][0] = ip;
				endServidor[i][1] = port;
				setado = true;
				break;
			}
		}
		return setado;
	}
		

	
	public static void main(String[] args) {

		//zera o log de conexões
		for (int i=0;i<10;i++)
			conexoes[i] == -1;
		
		//Setando a porta para conexão na inicialização
		Scanner p = new Scanner(System.in);
		System.out.print("Porta: ");
		PORT = Integer.parseInt(p.nextLine());
		System.out.println("Iniciando na porta " + PORT + "...");
		
		//Inicializando Controlador
		try {
			for(;;) {
				ServerSocket lowBase = new ServerSocket(PORT);
				TrataReq tratador = new TrataReq(lowBase.accept());
				tratador.start();
				lowBase.close();
				
			}
		} catch (BindException be) {
			System.err.println("Serviço ja está sendo executado na porta: " + PORT);
		} catch (IOException ioe) {
			System.err.println("Erro de I/O - " + ioe);
		}

	}

}

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
	static String[][] endServidor;
	//registra a quantidade de conexões em cada servidor online
	static int[] conexoes;
	
	public Lowbase() {
		endServidor = new String[10][2];
		//registra a quantidade de conexões em cada servidor online
		conexoes = new int[10];
		
		//zera o log de conexões
		for (int i=0;i<10;i++)
			conexoes[i] = -1;
	}


	/* Métodos do LowBase */

	// Informa o servidor online com menos conexões para enviar ao usuário
	public static String conectarMenosOcupado(){
		int menor = 11;
		int retorno = -1;
		for (int i=0; i<10; i++) {
			if (conexoes[i] != -1 && conexoes[i] < menor) {
				menor = i;
				retorno = i;
			}
		}
		if (retorno != -1){
			return endServidor[retorno][0] + ":" + endServidor[retorno][1];
		}
		return "Nenhum servidor disponível";
	}

	//Insere um servidor à lista de conectados
	public static boolean setServidor(String ip, String port){
		boolean setado = false;
		for (int i=0;i<10;i++) {
			if(conexoes[i] == -1) { // -1 indica posição livre
				endServidor[i][0] = ip;
				endServidor[i][1] = port;
				conexoes[i]++;
				setado = true;
				break;
			}
		}
		return setado;
	}
	
	public static void getServidores() {
		for (int i=0;i<10;i++) {
			if(conexoes[i] >= 0 ) {
				System.out.println(endServidor[i][0] + ":" + endServidor[i][1]);
			}
		}
	}


	public void tratarConexao() {
		
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


	public static void main(String[] args) {

		//Setando a porta para conexão na inicialização
		Scanner p = new Scanner(System.in);
		System.out.print("Porta LB: ");
		PORT = Integer.parseInt(p.nextLine());
		System.out.println("Iniciando na porta " + PORT + "...");
		
		Lowbase server = new Lowbase();
		server.tratarConexao();

	}

}

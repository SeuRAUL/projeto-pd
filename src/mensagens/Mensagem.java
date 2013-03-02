/**
 * @author SeuRAUL
 */

package mensagens;

import java.io.*;
import java.net.*;

public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;
	private String mensageiro;
	private String info;
	
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getMensageiro() {
		return mensageiro;
	}

	public void setMensageiro(String mensageiro) {
		this.mensageiro = mensageiro;
	}
	
	public void edit(String mensageiro, String info) {
		this.mensageiro = mensageiro;
		this.info = info;
	}
	
	public String to_str() { //String mensageiro, String info) {
		String UDP = this.mensageiro + "#" + this.info + "#";		
		return UDP;
	}
	
	public void to_obj(String UDP) throws UnknownHostException {
		this.mensageiro = UDP.substring(0, UDP.indexOf("#", 0));
		
		UDP = UDP.substring(UDP.indexOf("#", 0));
		String info = UDP.substring(1, UDP.indexOf("#", 1));
		this.info = info;
	}

}

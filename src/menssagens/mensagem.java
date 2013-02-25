/*
 * @author SeuRAUL
 */

package menssagens;

import java.io.*;
import java.net.*;

public class mensagem implements Serializable {
	
	private InetAddress address;
	private String menssageiro;
	private String info;
	
	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public String getMenssageiro() {
		return menssageiro;
	}

	public void setMenssageiro(String menssageiro) {
		this.menssageiro = menssageiro;
	}

}

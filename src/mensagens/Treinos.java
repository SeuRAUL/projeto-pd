package mensagens;

public class Treinos {
	
	private int data;
	private int hora;
	private String mestre;
	private String local;

	public int getData() {
		return this.data;
	}

	public int getHora() {
		return this.hora;
	}

	public String getMestre() {
		return this.mestre;
	}

	public String getLocal() {
		return this.local;
	}

	public void setData(int data){
		this.data = data;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public void setMestre(String mestre) {
		this.mestre = mestre;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	public String to_str() {
		String treino = this.data + ";" + this.hora + ";" + this.mestre + ";" + this.local + ";";
		return treino;
	}
	
	public void to_obj(String treino) {
		
	}

}

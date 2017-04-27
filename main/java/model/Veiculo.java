package model;

public class Veiculo {
	
	private int id;
	private String placa;
	private String nome;
	private String marca;
	private double valor;
	
	public Veiculo(int id, String placa, String nome, String marca, double valor){
		
		this.id = id;
		this.placa = placa;
		this.nome = nome;
		this.marca = marca;
		this.valor = valor;
	}
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getPlaca() {return placa;}
	public void setPlaca(String placa) {this.placa = placa;}
	
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	public String getMarca() {return marca;}
	public void setMarca(String marca) {this.marca = marca;
	}
	public double getValor() {return valor;}
	public void setValor(double valor) {this.valor = valor;}
	

}

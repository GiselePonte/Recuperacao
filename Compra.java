package Rec;

public class Compra {
	private String produto;
	private int quantidade;
	
	public Compra(String produto, int quantidade) { 
		this.produto = produto; 
		this.quantidade = quantidade; } 
	
	public String getProduto() { 
		return produto; } 
	
	public int getQuantidade() { 
		return quantidade; }

}

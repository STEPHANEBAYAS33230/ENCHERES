package fr.eni.encheres.modele.bo;

public class Retraits {
	
	private int id;
	private String rueretrait;
	private String postalretrait;
	private String villeretrait;
	
	
	
	
	public Retraits() {
	}
	public Retraits(int id, String rueretrait, String postalretrait, String villeretrait) {
		this.id = id;
		this.rueretrait = rueretrait;
		this.postalretrait = postalretrait;
		this.villeretrait = villeretrait;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRueretrait() {
		return rueretrait;
	}
	public void setRueretrait(String rueretrait) {
		this.rueretrait = rueretrait;
	}
	public String getVilleretrait() {
		return villeretrait;
	}
	public void setVilleretrait(String villeretrait) {
		this.villeretrait = villeretrait;
	}
	public String getPostalretrait() {
		return postalretrait;
	}
	public void setPostalretrait(String postalretrait) {
		this.postalretrait = postalretrait;
	}
	@Override
	public String toString() {
		return "retrait [id=" + id + ", rueretrait=" + rueretrait + ", villeretrait=" + villeretrait
				+ ", postalretrait=" + postalretrait + "]";
	}


}

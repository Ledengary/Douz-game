package Classes;
import Main.*;

public class Players {
	int id;
	String name;
	String profilePictureAddress;
	int n;
	int m;
	int p;
	int c;

	public Players() {
		
	}
	
	public Players(int id ,String name, String profilePictureAddress, int n, int m, int p, int c) {
		this.id = id;
		this.name = name;
		this.profilePictureAddress = profilePictureAddress;
		this.n = n;
		this.m = m;
		this.p = p;
		this.c = c;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfilePictureAddress() {
		return profilePictureAddress;
	}

	public void setProfilePictureAddress(String profilePictureAddress) {
		this.profilePictureAddress = profilePictureAddress;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
}

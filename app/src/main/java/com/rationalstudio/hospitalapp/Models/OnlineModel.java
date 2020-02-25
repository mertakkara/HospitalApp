package com.rationalstudio.hospitalapp.Models;

public class OnlineModel{
	private Object tur;
	private boolean tf;
	private Object docid;
	private Object docisim;
	private Object randevutarih;

	public void setTur(Object tur){
		this.tur = tur;
	}

	public Object getTur(){
		return tur;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setDocid(Object docid){
		this.docid = docid;
	}

	public Object getDocid(){
		return docid;
	}

	public void setDocisim(Object docisim){
		this.docisim = docisim;
	}

	public Object getDocisim(){
		return docisim;
	}

	public void setRandevutarih(Object randevutarih){
		this.randevutarih = randevutarih;
	}

	public Object getRandevutarih(){
		return randevutarih;
	}

	@Override
 	public String toString(){
		return 
			"OnlineModel{" + 
			"tur = '" + tur + '\'' + 
			",tf = '" + tf + '\'' + 
			",docid = '" + docid + '\'' + 
			",docisim = '" + docisim + '\'' + 
			",randevutarih = '" + randevutarih + '\'' + 
			"}";
		}
}

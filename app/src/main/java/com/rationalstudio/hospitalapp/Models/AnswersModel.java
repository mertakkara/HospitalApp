package com.rationalstudio.hospitalapp.Models;

public class AnswersModel{
	private String cevapid;
	private String cevap;
	private boolean tf;
	private String soruid;
	private String soru;

	public void setCevapid(String cevapid){
		this.cevapid = cevapid;
	}

	public String getCevapid(){
		return cevapid;
	}

	public void setCevap(String cevap){
		this.cevap = cevap;
	}

	public String getCevap(){
		return cevap;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setSoruid(String soruid){
		this.soruid = soruid;
	}

	public String getSoruid(){
		return soruid;
	}

	public void setSoru(String soru){
		this.soru = soru;
	}

	public String getSoru(){
		return soru;
	}

	@Override
	public String toString(){
		return
				"Defemodel{" +
						"cevapid = '" + cevapid + '\'' +
						",cevap = '" + cevap + '\'' +
						",tf = '" + tf + '\'' +
						",soruid = '" + soruid + '\'' +
						",soru = '" + soru + '\'' +
						"}";
	}
}

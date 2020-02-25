package com.rationalstudio.hospitalapp.Models;

public class AppoinmentModel{
	private String tur;
	private String randid;
	private String resim;
	private boolean tf;
	private String dokismi;

	public void setTur(String tur){
		this.tur = tur;
	}

	public String getTur(){
		return tur;
	}

	public void setRandid(String randid){
		this.randid = randid;
	}

	public String getRandid(){
		return randid;
	}

	public void setResim(String resim){
		this.resim = resim;
	}

	public String getResim(){
		return resim;
	}

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setDokismi(String dokismi){
		this.dokismi = dokismi;
	}

	public String getDokismi(){
		return dokismi;
	}

	@Override
 	public String toString(){
		return 
			"AppoinmentModel{" + 
			"tur = '" + tur + '\'' + 
			",randid = '" + randid + '\'' + 
			",resim = '" + resim + '\'' + 
			",tf = '" + tf + '\'' + 
			",dokismi = '" + dokismi + '\'' + 
			"}";
		}
}

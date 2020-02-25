package com.rationalstudio.hospitalapp.Models;

public class CampaignModel{
	private boolean tf;
	private String text;
	private String baslik;

	public void setTf(boolean tf){
		this.tf = tf;
	}

	public boolean isTf(){
		return tf;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setBaslik(String baslik){
		this.baslik = baslik;
	}

	public String getBaslik(){
		return baslik;
	}

	@Override
 	public String toString(){
		return 
			"CampaignModel{" + 
			"tf = '" + tf + '\'' + 
			",text = '" + text + '\'' + 
			",baslik = '" + baslik + '\'' + 
			"}";
		}
}

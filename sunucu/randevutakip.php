<?php
include("ayar.php");
$musid = $_POST["id"];


$sor = mysqli_query($baglan,"SELECT a.dok_ismi,a.tur,b.randevu_tarih FROM randevular a INNER JOIN takiprandevu b ON a.id = b.doc_id WHERE a.mus_id='$musid' AND b.mus_id='$musid' AND b.randevu_durum=0");
$count = mysqli_num_rows($sor);

class randevuTakip{
	
	public $docisim;
	public $tur;
	public $randevutarih;
	public $tf;
}
$randevu = new randevuTakip();
$sayac = 0;
if($count>0){
	
	echo("[");
	while($bilgi = mysqli_fetch_assoc($sor)){
		$sayac = $sayac +1;
	$randevu->docisim = $bilgi["dok_ismi"];
	$randevu->tur = $bilgi["tur"];
	$randevu->randevutarih = $bilgi["randevu_tarih"];
	$randevu->tf =true;
	echo(json_encode($randevu));
	if($count>$sayac){
	echo(",");
	}
	}
	echo("]");
	
}else{
	echo("[");
	
		$randevu->docid = null;
	$randevu->tur = null;
	$randevu->randevutarih = null;
	$randevu->tf =false;
	echo(json_encode($randevu));
	echo("]");
	
}
	


?>

	




<?php
include("ayar.php");
$mus_id = $_POST["musid"];
$sorgula = mysqli_query($baglan,"select * from randevular where mus_id = '$mus_id' ");
$count =mysqli_num_rows($sorgula);
class randevularClass{
	public $randid;
	public $resim;
	public $tur;
	public $dokismi;
	public $tf;
	
}
$randevu = new randevularClass();
$sayac = 0;
if($count>0){
	
	echo("[");
	while($bilgi = mysqli_fetch_assoc($sorgula)){
		$sayac = $sayac +1;
	$randevu->randid = $bilgi["id"];
	$randevu->resim = $bilgi["resim"];
	$randevu->tur = $bilgi["tur"];
	$randevu->dokismi = $bilgi["dok_ismi"];
	$randevu->tf = true;
	echo(json_encode($randevu));
	if($count>$sayac){
	echo(",");
	}
	}
	echo("]");
	
}else{
	echo("[");
	
	$randevu->randid = null;
	$randevu->resim = null;
	$randevu->tur = null;
	$randevu->dokismi = null;
	$randevu->tf = false;
	echo(json_encode($randevu));
	echo("]");
	
}



?>




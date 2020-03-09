<?php
include("ayar.php");
$cevapid= $_POST["cevap"];
$soruid= $_POST["soru"];
$sil = mysqli_query($baglan,"DELETE FROM cevaplar WHERE id= '$cevapid' AND soru_id = $soruid ");
$sil2 = mysqli_query($baglan,"DELETE FROM sorular WHERE id= '$soruid'");

class deleteRecord{
	public $text;
	public $tf;
	
}
$del = new deleteRecord();

	$del->text ="Basarili";
	$del->tf=true;
	echo(json_encode($del));
	


?>
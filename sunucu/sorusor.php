<?php
include("ayar.php");
$mus_id = $_POST["id"];
$soru = $_POST["soru"];
$ekle = mysqli_query($baglan,"insert into sorular(mus_id,soru,durum) values('$mus_id','$soru','0')");
class result{
	public $text;
	public $tf;
	
}
$ekle = new result();
if($ekle){
	$ekle->text="Your question was sent to the relevant department.";
	$ekle->tf =true;
	echo(json_encode($ekle));
	
}else{
	$ekle->text="An error occurred while submitting your question.";
	$ekle->tf =false;
	echo(json_encode($ekle));
	
}



?>


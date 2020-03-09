<?php
include("ayar.php");


class Kampanya{
	public $baslik;
	public $text;
	public $tf;
	
}
$kam = new Kampanya();
$sorgu = mysqli_query($baglan,"SELECT * FROM kampanyalar");
$count = mysqli_num_rows($sorgu);

$sayac = 0;
if($count>0){
	
	echo("[");
	while($kayit = mysqli_fetch_assoc($sorgu)){
		$sayac = $sayac +1;
	$kam->baslik = $kayit["baslik"];
	$kam->text = $kayit["text"];
	$kam->tf=true; 
	echo(json_encode($kam));
	if($count>$sayac){
	echo(",");
	}
	}
	echo("]");
	
}else{
	echo("[");
	
	$kam->baslik = null;
	$kam->text = null;
	$kam->tf=false; 
	echo(json_encode($kam));
	echo("]");
	
}
	


?>
<?php
include("ayar.php");
$mailA = $_POST["mailadres"];
$sifre = $_POST["sifre"];

$control=mysqli_query($baglan,"select * from mertak where mailadress= '$mailA' and password= '$sifre'");
$count=mysqli_num_rows($control);

class userLogin{
	public $id;
	public $username;
public $mailadres;
public $parola;
public $tf;
public $text;
}
$user = new userLogin();



if($count > 0){
	$parse = mysqli_fetch_assoc($control);
	$state = $parse["state"];
	$id = $parse["id"];
	$username = $parse["username"];
	$parola = $parse["password"];
	$mailadres = $parse["mailadress"];
	
	if($state ==1){
		
	$user->tf = true;
	$user->text = "Login successfully. ";
	$user->id = $id ;
	$user->parola = $parola;
	$user->username = $username;
	$user->mailadres = $mailadres;
	echo(json_encode($user));
	
	}else{
		$user->tf = false;
	$user->text = "You must confirm your e-mail address. ";
	$user->id = null ;
	$user->parola = null;
	$user->username = null;
	$user->mailadres = null;
		echo(json_encode($user));
	}
	
	
}else{
	
	$user->tf = false;
	$user->text = "This user dont exist.";
	$user->id = null;
	$user->parola = null;
	$user->username = null;
	$user->mailadres = null;
	echo(json_encode($user));
}

?>



<?php
include("ayar.php");
$mailAdress = $_POST["mailAdress"];
$username = $_POST["username"];
$password = $_POST["password"];
$code = rand(0,10000).rand(0,10000);
$state = 0;
$controll = mysqli_query($baglan,"select * from mertak where mailadress = '$mailAdress' or username = '$username' ");
$count = mysqli_num_rows($controll);

class User{
		public $text;
		public $tf;
}	
$user = new User();

if($count > 0){
	$user->text = "There is already such a user. Please change your information.";
	$user->tf = false;
	echo(json_encode($user));
}else{
$addUser = mysqli_query($baglan,"insert into mertak(username,mailadress,password,code,state) values ('$username','$mailAdress','$password','$code','$state')");


$user->text = "Your acoount have saved, But need to registiration";
	$user->tf = true;
	echo(json_encode($user));
	$guncelle = mysqli_query($baglan,"update mertak set state= '1' where mailadress = '$mailAdress' and code = '$code'" );
}





?>


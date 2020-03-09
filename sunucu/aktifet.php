<?php
include("ayar.php");
$mail = $_GET["mail"];
$code = $_GET["code"];

$guncelle = mysqli_query($baglan,"update mertak set state= '1' where mailadress = '$mail' and code = '$code'" );

if($guncelle){
?>
<h1>Confirmed your account.</h1>
<?php

}


?>
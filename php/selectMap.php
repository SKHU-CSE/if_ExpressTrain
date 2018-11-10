<?php
$conn = mysqli_connect(localhost, 'root', '123', 'ExpressTrain');
$key = $_GET['store'];//storename받음

$sql = "SELECT * FROM STORE WHERE STORE_NAME='$key'";  //name으로 num받기위한sql
$res =  mysqli_query($conn, $sql);
$topic = mysqli_fetch_array($res);

$D_row=array();
$row_array['longitude'] = $topic['LONGITUDE'];
$row_array['LATITUDE'] = $topic['LATITUDE'];
array_push($D_row, $row_array);

echo json_encode($D_row, JSON_UNESCAPED_UNICODE);
mysqli_close($conn);

 ?>

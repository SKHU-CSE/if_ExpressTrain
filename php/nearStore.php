<?php
$conn = mysqli_connect(localhost, 'root', '123', 'ExpressTrain');

$longi = $_GET['longi']; //현위치 경도좌표
$lati = $_GET['lati'];  //현위치 위도좌표

$sql = "SELECT STORE_NAME, ADDRESS, (6371*acos(cos(radians($lati))*cos(radians(LATITUDE))*cos(radians(LONGITUDE)-radians(126.9221))+sin(radians(37.604))*sin(radians(LATITUDE)))) AS distance FROM STORE HAVING distance<=10 ORDER BY distance ASC LIMIT 0, 5";;
$res =  mysqli_query($conn, $sql);

var_dump($res);
//반경 10키로설정
$D_row = array();
while($topic = mysqli_fetch_array($res)){
  $row_array['distance']=$topic['distance'];
  $row_array['store_name']=$topic['STORE_NAME'];
  array_push($D_row, $row_array);
}

echo json_encode($D_row, JSON_UNESCAPED_UNICODE);
mysqli_close($conn);

?>

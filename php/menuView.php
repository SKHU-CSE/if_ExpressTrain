<?php
$conn = mysqli_connect(localhost, 'root', '123', 'ExpressTrain');

$key = $_GET['store'];//storename받음

$sql = "SELECT * FROM STORE WHERE STORE_NAME='$key'";  //name으로 num받기위한sql
$res =  mysqli_query($conn, $sql);
$topic = mysqli_fetch_array($res);
$storenum = $topic['STORE_NUM'];

$sql = "SELECT * FROM MENU WHERE STORE_NUM='$storenum'";  //num으로 메뉴이미지받아옴
$res =  mysqli_query($conn, $sql);

$D_row=array();
#행값 가져오기
while($topic = mysqli_fetch_array($res)){
  $row_array['MENU_IMG']=$topic['MENU_IMG'];
  array_push($D_row,$row_array);
}
echo json_encode($D_row,JSON_UNESCAPED_UNICODE);
mysqli_close($conn);

 ?>

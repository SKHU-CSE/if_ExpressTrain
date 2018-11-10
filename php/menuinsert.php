<?php
if($_SERVER['REQUEST_METHOD']=='POST'){  //보안
$image=$_POST['image'];
$conn = mysqli_connect(localhost, 'root', '123', 'ExpressTrain');


#한식 중식 분식 제과점 패스트푸드 부식
#카드종류에 따라 화면 출력 달라짐
$key = $_POST['store'];  //가게의 이름
$sql = "SELECT STORE_NUM FROM STORE WHERE STORE_NAME = '$key'";
$res = mysqli_query($conn, $sql); //이름가지고 storenum접근

$storeval = mysqli_fetch_array($res); //값불러옴

$store_num = $storeval['STORE_NUM'];

$sql = "SELECT STORE_NUM FROM MENU";  //갯수만큼아이디(삭제생각안함->중복해결해야함)
$res=mysqli_query($conn, $sql);
$bolder = $res->num_rows;  //행갯수

$path = "uploads/".$bolder.".jpg";
$serverip = "52.14.45.167/$path";
$sql = "INSERT INTO MENU(STORE_NUM, MENU_IMG) VALUES('$store_num', '$serverip')";
if(mysqli_query($conn, $sql)){
  file_put_contents($path, base64_decode($image));
  echo "image uploaded successfully";
}else{
  echo "Error uploading image";
}
mysqli_close($conn);
}
else {
  echo "Error";
}
?>

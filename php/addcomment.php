<?php
$conn = mysqli_connect(localhost, 'root', '123', 'ExpressTrain');

$key1 = $_GET['STORE'];   //STORE_NAME을가지고 시작
$key2 = $_GET['NAME'];
$key3 = $_GET['TIME'];
$key4 = $_GET['CONTENT'];

$sql = "INSERT INTO COMMENT(NAME, CONTENT, TIME) VALUES('$key2', '$key4', '$key3')";
$res = mysqli_query($conn, $sql);

$sql = "SELECT * FROM COMMENT WHERE NAME='$key2' AND TIME='$key3' AND CONTENT='$key4'";
$res = mysqli_query($conn, $sql);

$D_row=array();

while($topic = mysqli_fetch_array($res)){  //값불러옴
$comment['num'] = $topic['COMMENT_NUM'];     //COMMENT_NUM값넣어주기
array_push($D_row,$comment);
}

$sql = "SELECT STORE_NUM FROM STORE WHERE STORE_NAME='$key1'";  //STORE_NUM 찾아와서
$res = mysqli_query($conn, $sql);

$val_store = mysqli_fetch_array($res); //값불러옴
$store['store'] = $val_store['STORE_NUM'];

$storenum = $store['store'];
$commentnum = $comment['num'];

$sql = "INSERT INTO COMMENT_LIST (STORE_NUM, COMMENT_NUM) VALUES('$storenum', '$commentnum')";
$res = mysqli_query($conn, $sql);
mysqli_close($conn);

 ?>

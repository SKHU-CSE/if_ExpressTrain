<?php
$conn = mysqli_connect(localhost, 'root', '123', 'ExpressTrain');

$key = $_GET['id'];
$key1 = $_GET['pw'];
$key2 = $_GET['type'];
$key3 = $_GET['name'];
$key4 = $_GET['email'];

if($key === null|| $key1 === null|| $key2 === null|| $key3 ===null || $key4 ===null){
  echo "빈칸이 있습니다.";
}else{
    $sql = "INSERT INTO MEMBER(ID, PW, TYPE, NAME, EMAIL) VALUES('$key','$key1', '$key2', '$key3', '$key4')";
    $res = mysqli_query($conn, $sql);
    if($res === false){  //값제대로 전달 x시
      echo mysqli_errno($conn). ": ". mysqli_error($conn);   //에러코드 : 에러내용
    }else {
      $result = array("result" => "true");  //result태그에 true값
      echo json_encode($result, JSON_UNESCAPED_UNICODE);
    }
  }
?>

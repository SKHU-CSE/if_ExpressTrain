<?php
$conn = mysqli_connect(localhost, 'root', '123', 'ExpressTrain');

$key1 = $_GET['id'];
$key2 = $_GET['pw'];

if($key1===null || $key2===null ){
  echo "아이디 또는 패스워드를 입력해 주세요.";
}else{
  $sql = "SELECT * FROM MEMBER WHERE ID='$key1' AND PW='$key2'";
  $res =  mysqli_query($conn, $sql);

  $count = mysqli_num_rows($res);
  if($count === 0){   //없는아이디나 pw일경우 에러코드반환
    $row_array['error']=100;
    echo json_encode($row_array, JSON_UNESCAPED_UNICODE);
  }else {
    echo "로그인성공";
  }
}
 ?>

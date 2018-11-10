<?php
$conn = mysqli_connect(localhost, 'root', '123', 'ExpressTrain');

$key = $_GET['email'];

$sql = "SELECT * FROM MEMBER WHERE EMAIL='$key'";
$res =  mysqli_query($conn, $sql);

$count = mysqli_num_rows($res);
if($count === 0){   //없는메일일경우에러코드 200반환
  $row_array['error']=200;
  echo json_encode($row_array, JSON_UNESCAPED_UNICODE);
}else {
  $D_row=array();  //빈배열생성

  $topic = mysqli_fetch_array($res);
  $row_array['id'] = $topic['ID'];
  $row_array['pw'] = $topic['PW'];

  array_push($D_row,$row_array);   //배열에 값넣어주기
  echo json_encode($D_row,JSON_UNESCAPED_UNICODE);
mysqli_close($conn);
}
 ?>

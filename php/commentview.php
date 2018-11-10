<?php
$conn = mysqli_connect(localhost, 'root', '123', 'ExpressTrain');
header('Content-TYPE:application/json');
#댓글다가져오기
$key = $_GET['store'];  //가게의 이름
$sql = "SELECT STORE_NUM FROM STORE WHERE STORE_NAME = '$key'";
$res = mysqli_query($conn, $sql); //이름가지고 storenum접근

$storeval = mysqli_fetch_array($res); //값불러옴
$storenum['store'] = $storeval['STORE_NUM'];
$store_num= $storenum['store'];


$sql = "SELECT * FROM COMMENT_LIST LEFT JOIN COMMENT ON COMMENT_LIST.COMMENT_NUM = COMMENT.COMMENT_NUM WHERE STORE_NUM = '$store_num'";
$res=mysqli_query($conn, $sql);


$D_row=array();

#행값 가져오기
while($topic = mysqli_fetch_array($res)){
  $row_array['name']=$topic['NAME'];
  $row_array['content']=$topic['CONTENT'];
  $row_array['time']=$topic['TIME'];

  array_push($D_row,$row_array);
}
#var_dump( $D_row);
echo json_encode($D_row,JSON_UNESCAPED_UNICODE);
mysqli_close($conn);
 ?>

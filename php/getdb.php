<?php
$conn = mysqli_connect(localhost, 'root', '123', 'ExpressTrain');
#header('Content-TYPE: applcation/json'); 다운로드됌 넣으면


#카드종류에 따라 화면 출력 달라짐
switch($_GET['cardtype']){
  case '0':
    $sql = "SELECT * FROM STORE WHERE CARD_TYPE=0";
    break;
  case '1':
    $sql = "SELECT * FROM STORE WHERE CARD_TYPE=1";
    break;
  case '2':
    $sql = "SELECT * FROM STORE WHERE CARD_TYPE=2";
    break;
}

$res=mysqli_query($conn, $sql);
$D_row=array();

#행값 가져오기
while($topic = mysqli_fetch_array($res)){
  $row_array['name']=$topic['STORE_NAME'];
  $row_array['address']=$topic['ADDRESS'];
  $row_array['phone']=$topic['PHONE'];
  $row_array['latitude']=$topic['LATITUDE'];
  $row_array['longitude']=$topic['LONGITUDE'];
  $row_array['photo']=$topic['PHOTO'];
  $storenum = $topic['STORE_NUM'];

  $M_row=array();
  $sql="SELECT * FROM MENU WHERE STORE_NUM = $storenum";   //메뉴이미지가져오기
  $res2=mysqli_query($conn, $sql);
  while($topic = mysqli_fetch_array($res2)){
    $row2_array['image']=$topic['MENU_IMG'];
    array_push($M_row,$row2_array);   //이미지 db에서 가져옴
  }
  $row_array["Image"]=$M_row;   //image태그에 배열을 넣음
  array_push($D_row,$row_array); //가게정보 db에서 가져옴
}



echo json_encode($D_row,JSON_UNESCAPED_UNICODE);
mysqli_close($conn);
 ?>

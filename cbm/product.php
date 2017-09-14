<?php
include 'connection.php';

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
 die("Connection failed: " . $conn->connect_error);
}
$sql = "SELECT p_id,p_title,p_price,p_keyword,p_description,p_warantry,p_image FROM tbl_product";
$result=mysqli_query($conn,$sql);
  	while($e=mysqli_fetch_assoc($result)){
        		$output[]=$e; 
  			}
  	
  	print(json_encode($output)); 
  	
?>
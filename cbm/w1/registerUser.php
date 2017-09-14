<?php
require_once '../includes/DbOperations.php';
$response=array();

if($_SERVER['REQUEST_METHOD']=='POST'){

	if(isset($_POST['user_name']) and isset($_POST['user_surname']) and isset($_POST['user_email']) and 
		isset($_POST['user_password']) and isset($_POST['user_phone']) and isset($_POST['user_address']) and 
			isset($_POST['user_city']) and isset($_POST['user_state']))
	{
	//Operate the data further

		$db= new DbOperations();

		$result=$db->createUser($_POST['user_name'],
								$_POST['user_surname'],
								$_POST['user_email'],
								$_POST['user_password'],
								$_POST['user_phone'],
								$_POST['user_address'],
								$_POST['user_city'],
								$_POST['user_state']
								);

		if($result == 1){
			$response['error']=false;
	$response['message']="registered Successfully";
		}
		elseif($result==2)
		{
			$response['error']=true;
	$response['message']="Please Try Again";
		}elseif($result==0){
			$response['error']=true;
	$response['message']="User Already Existed...";
		}
}else{
	$response['error']=true;
	$response['message']="Required Fields Are Missing";
}

}
else{
$response['error']=true;
$response['message']="Invalid Request";

}
echo json_encode($response);
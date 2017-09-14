<?php
require_once '../includes/DbOperations.php';
$response=array();

if($_SERVER['REQUEST_METHOD']=='POST'){
	if(isset($_POST['user_email']) and isset($_POST['user_password'])){
		$db = new DbOperations();

			if($db->userLogin($_POST['user_email'],$_POST['user_password'])){
				$userr=$db->getUserByUserEmail($_POST['user_email']);
				$response['error']=false;
				$response['user_id'] = $userr['user_id'];
				$response['user_name'] = $userr['user_name'];
				$response['user_surname'] = $userr['user_surname'];
				$response['user_email'] = $userr['user_email'];
				$response['user_password'] = $userr['user_password'];
				$response['user_phone'] = $userr['user_phone'];
				$response['user_address'] = $userr['user_address'];
				$response['user_city'] = $userr['user_city'];
				$response['user_state'] = $userr['user_state'];
				
			}else{
				$response['error']=true;
	$response['message']="Invalid Email or Password";
			}

	}else{
		$response['error']=true;
	$response['message']="Required fields are missing...";
	}
}

echo json_encode($response);
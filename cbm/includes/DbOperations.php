<?php

	class DbOperations{

		private $con;
		function __construct(){
			require_once dirname(__FILE__).'/DbConnect.php';
			$db = new DbConnect();
			$this->con=$db->connect();
			
		}

		//CRUD -> C -> CREATE
		public function createUser($user_name,$user_surname,$user_email,$user_pass,$user_phone,$user_address,$user_city,$user_state){
			if($this->isUserExist($user_email,$user_phone)){
						return 0;
			}else{
			$user_password=md5($user_pass);
			$stmt= $this->con->prepare("INSERT INTO `tbl_user` (`user_id`, `user_name`, `user_surname`, `user_email`, `user_password`, `user_phone`, `user_address`, `user_city`, `user_state`) VALUES (NULL,?,?,?,?,?,?,?,?);");
			$stmt->bind_param("ssssssss",$user_name,$user_surname,$user_email,
											$user_password,$user_phone,
											$user_address,$user_city,$user_state);
			if($stmt->execute())
			{
				return 1;
			}else{
				return 2;
			}
		}}

		public function userLogin($user_email,$user_pass){
			$user_password = md5($user_pass);
				$stmt = $this->con->prepare("SELECT user_id FROM tbl_user WHERE user_email=? and user_password=?");
				$stmt->bind_param("ss",$user_email,$user_password);
				$stmt->execute();
				$stmt->store_result();
				return $stmt->num_rows>0;
		}

		public function getUserByUserEmail($user_email){
			$stmt = $this->con->prepare("SELECT * FROM tbl_user where user_email=?");
			$stmt->bind_param("s",$user_email);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();

		}


		private function isUserExist($user_email,$user_phone){
			$stmt = $this->con->prepare("SELECT user_id FROM tbl_user where user_email=? AND user_phone=? ");
			$stmt->bind_param("ss",$user_email,$user_phone);
			$stmt->execute();
			$stmt->store_result();
			return $stmt->num_rows>0;
		}
	}
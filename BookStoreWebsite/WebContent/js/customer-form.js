/**
 * 
 */

$(document).ready(function(){
	 $("#customerForm").validate({
		 
		rules: {
			email:{
				required:true,
				email:true
				
			},
            firstname:"required",
            lastname:"required",

			password:"required",
			
			confirmPassword:{
				required:true,
				equalTo:"#password"
			},
            phone:"required",
            address1:"required",
            address2:"required",
            city:"required",
            state:"required",
            zipcode:"required",
            country:"required",

		},
			
			
			
      	   message:{
      		 email:{
 				required:"please enter e-mail address",
 				email:"please enter a valid e-mail address"
 				
 			},
             firstname:"please enter first name",
             lastname:"please enter last name",
 			 password:"please enter password",
 			 
 			 confirmPassword:{
 				required:"please confirm password",
 				equalTo:"Confirm password does not match password with entered password"	 
 			 },
             phone:"please enter phone number",
             address1:"please enter address line 1",
             address2:"please enter address line 2",
             city:"please enter city",
             state:"please enter state",
             zipcode:"please enter zip code",
             country:"please enter country",
                }
	 });
	 
	 $("#buttonCancel").click(function(){
		 history.go(-1);
	 });
	 
 });
 
 
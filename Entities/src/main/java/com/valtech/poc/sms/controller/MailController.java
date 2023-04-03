package com.valtech.poc.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.valtech.poc.sms.entities.Employee;
import com.valtech.poc.sms.service.EmployeeService;
import com.valtech.poc.sms.service.ResetPassword;

@RestController
public class MailController {

	@Autowired
	EmployeeService empService;

	@Autowired
	ResetPassword resetPassword;
	

	@ResponseBody
	@PostMapping("/reset/{email}")
	public String forgotPass(@PathVariable("email") String email) {
		boolean b = resetPassword.checkMailId(email);
		System.out.println(b);
		if (b == true) {
			Boolean forgot = resetPassword.generateOtp(email);
//			System.out.println(forgot);
			String s1 = "sent";
			return s1;
		}
		String s2 = "fail";
		return s2;
	}

	@PostMapping("/reset/newPass/{id}")
	public String newPass(@PathVariable("id") int id, @RequestParam String otpKey, @RequestParam String newPassword) {

		Employee emp = empService.findById(id);
		String email = emp.getMailId();
		System.out.println("test mail= " + email);
		boolean b = resetPassword.newPasswod(email, otpKey, newPassword);
		System.out.println(b);
		if (b == true) {			
			return "changed";
		}
		return "fail";
	}
	
//	@PostMapping("/reset/{email}")
//    public String forgotPass(@PathVariable("email") String email, @RequestHeader("Authorization") String jwt) throws Exception {
//        boolean b = resetPassword.checkMailId(email);
//        System.out.println(b);
//        if (b==true) {
//            Boolean forgot = resetPassword.generateOtp(email);
//            System.out.println(forgot);
//            String url = "https://localhost/reset/" + email;
//            String requestBody = "{\"key\":\"value\"}";
//            HttpResponse response = httpService.performPostRequest(url, requestBody, jwt);
//           
//            // handle response and return appropriate value
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode == 200) {
////                String responseBody = EntityUtils.toString(response.getEntity());
//                return "sent"; // success
//            } else if (statusCode == 401) {
//                return "Unauthorized"; // JWT token invalid or expired
//            } else {
//                return "Failed"; // other error
//            }
//        } else {
//            return "fail";
//        }
//    }
//	@PostMapping("/reset/newPass/{id}")
//	public String newPass(@PathVariable("id") int id, @RequestParam String otpKey, @RequestParam String newPassword) {
//
//	    Employee emp = empService.findById(id);
//	    if (emp != null) {
//	        String email = emp.getMailId();
//	        System.out.println("test mail= " + email);
//	        boolean b = resetPassword.newPasswod(email, otpKey, newPassword);
//	        System.out.println(b);
//	        if (b) {
//	            return "changed";
//	        }
//	    }
//	    return "fail";
//	}

}










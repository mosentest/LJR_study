package com.ljr.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljr.dao.TbUserDAO;
import com.ljr.dto.UserDTO;
import com.ljr.entity.TbUser;
import com.ljr.util.JsonResponse;

@Controller
@RequestMapping("/")
public class LoginController {
	
	private String msg = "";
	
	private TbUserDAO dao = new TbUserDAO();
	
	@RequestMapping(value = "login.html", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JsonResponse<TbUser> login(@RequestBody final UserDTO userDTO, HttpSession session) {
		JsonResponse<TbUser> jsonResponse = new JsonResponse<TbUser>();
 		try {
			List findByUsername = dao.findByUsername(userDTO.getUsername());
			//判断是否存在
			if(findByUsername.isEmpty()){
				msg = "帐号或密码错误";
				throw new Exception(msg);
			}
			//再次比较密码
			TbUser user =(TbUser) findByUsername.get(0);
			if(!user.getPassword().equals(userDTO.getPassword())){
				msg = "帐号或密码错误";
				throw new Exception(msg);
			}
			session.setAttribute("username", userDTO.getUsername());
			jsonResponse.setEntity(user);
			jsonResponse.setMsg("登录成功");
			jsonResponse.setSuccess(true);
		} catch (Exception e) {
			jsonResponse.setMsg(msg);
			jsonResponse.setSuccess(false);
			e.printStackTrace();
		}
		return jsonResponse;
	}
	
}

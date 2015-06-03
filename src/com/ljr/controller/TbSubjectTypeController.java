package com.ljr.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljr.dao.TbSubjectTypeDAO;
import com.ljr.entity.TbSubjectType;
import com.ljr.util.JsonResponse;
/**
 * 类型action
 * @author 
 *
 */
@Controller
@RequestMapping("/")
public class TbSubjectTypeController {
	public TbSubjectTypeDAO dao = new TbSubjectTypeDAO();

	@RequestMapping(value = "subjecttype/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbSubjectType> list() {
		JsonResponse<TbSubjectType> jsonResponse = new JsonResponse<TbSubjectType>();
		try {
			List<TbSubjectType> findAll = dao.findAll();
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg("获取成功");
			jsonResponse.setList(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg("获取失败");
		}
		return jsonResponse;
	}
	
}

package com.ljr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljr.dao.TbUserQuestionnaireDAO;
import com.ljr.entity.TbUserQuestionnaire;
import com.ljr.util.JsonResponse;
import com.ljr.util.Page;

/**
 * 用户做题情况
 * @author 
 *
 */
@Controller
@RequestMapping("/")
public class TbUserQuestionnaireController {
	
	private TbUserQuestionnaireDAO dao = new TbUserQuestionnaireDAO();
	
	/**
	 * 编号，用户编号，用户名，类型编号, 类型名称
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "userquestionnaire/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbUserQuestionnaire> list(@RequestParam(required = false, defaultValue = "1") int page,
															@RequestParam(required = false, defaultValue = "1000") int size, 
															@RequestParam(required = false) final String id,
															@RequestParam(required = false) final String userId,
															@RequestParam(required = false) final String username,
															@RequestParam(required = false) final String questionaireName,
															@RequestParam(required = false) final String typeId,
															@RequestParam(required = false) final String typeName){
		JsonResponse<TbUserQuestionnaire> jsonResponse = new JsonResponse<TbUserQuestionnaire>();
		// 获取对应的参数
		String[] params = new String[] { id, userId, username,questionaireName,typeId,typeName};
		try {
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					// 编码有问题,get传过来的参数
					if (params[i] != null && !"".equals(params[i])) {
						String newStr = new String(params[i].getBytes("iso8859-1"), "UTF-8");
						params[i] = newStr;
					}
				}
			}
			Page<TbUserQuestionnaire> findAll = dao.findAll(page, size, params);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg("获取成功");
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg("获取失败");
		}
		return jsonResponse;
	}
}

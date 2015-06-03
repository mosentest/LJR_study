package com.ljr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljr.dao.TbQuestionnaireDisciplineDAO;
import com.ljr.entity.TbQuestionnaireDiscipline;
import com.ljr.util.JsonResponse;
import com.ljr.util.Page;

/**
 * 问卷中的题目
 * @author 
 *
 */
@Controller
@RequestMapping("/")
public class TbQuestionnaireDisciplineController {
	private TbQuestionnaireDisciplineDAO dao = new TbQuestionnaireDisciplineDAO();
	
	/**
	 * 编号,问卷编号
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "questionnairediscipline/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbQuestionnaireDiscipline> list(@RequestParam(required = false, defaultValue = "1") int page,
															@RequestParam(required = false, defaultValue = "1000") int size, 
															@RequestParam(required = false) final String id,
															@RequestParam(required = false) final String questionnaireId){
		JsonResponse<TbQuestionnaireDiscipline> jsonResponse = new JsonResponse<TbQuestionnaireDiscipline>();
		// 获取对应的参数
		String[] params = new String[] { id, questionnaireId };
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
			Page<TbQuestionnaireDiscipline> findAll = dao.findAll(page, size, params);
			jsonResponse.setSuccess(true);
			jsonResponse.setMsg("获取成功");
			jsonResponse.setPage(findAll);
		} catch (Exception e) {
			jsonResponse.setSuccess(false);
			jsonResponse.setMsg("获取失败");
		}
		return jsonResponse;
	}
}

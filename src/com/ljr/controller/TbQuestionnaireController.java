package com.ljr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljr.dao.TbQuestionnaireDAO;
import com.ljr.entity.TbQuestionnaire;
import com.ljr.util.JsonResponse;
import com.ljr.util.Page;
/**
 * 问卷action
 * @author 
 *
 */
@Controller
@RequestMapping("/")
public class TbQuestionnaireController {
	
	private TbQuestionnaireDAO dao = new TbQuestionnaireDAO();
	
	/**
	 * 问卷编号，类型编号，类型名称
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "questionnaire/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbQuestionnaire> list(@RequestParam(required = false, defaultValue = "1") int page,
															@RequestParam(required = false, defaultValue = "1000") int size, 
															@RequestParam(required = false) final String id,
															@RequestParam(required = false) final String typeId,
															@RequestParam(required = false) final String typeName){
		JsonResponse<TbQuestionnaire> jsonResponse = new JsonResponse<TbQuestionnaire>();
		// 获取对应的参数
		String[] params = new String[] { id, typeId, typeName };
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
			Page<TbQuestionnaire> findAll = dao.findAll(page, size, params);
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

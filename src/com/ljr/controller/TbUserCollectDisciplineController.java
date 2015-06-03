package com.ljr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljr.dao.TbUserCollectDisciplineDAO;
import com.ljr.entity.TbUserCollectDiscipline;
import com.ljr.util.JsonResponse;
import com.ljr.util.Page;

/**
 * 收藏题目
 * @author 
 *
 */
@Controller
@RequestMapping("/")
public class TbUserCollectDisciplineController {
	private TbUserCollectDisciplineDAO dao = new TbUserCollectDisciplineDAO();
	
	/**
	 * 编号，用户id，用户帐号
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "usercollectdiscipline/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbUserCollectDiscipline> list(@RequestParam(required = false, defaultValue = "1") int page,
															@RequestParam(required = false, defaultValue = "1000") int size, 
															@RequestParam(required = false) final String id,
															@RequestParam(required = false) final String userId,
															@RequestParam(required = false) final String username){
		JsonResponse<TbUserCollectDiscipline> jsonResponse = new JsonResponse<TbUserCollectDiscipline>();
		// 获取对应的参数
		String[] params = new String[] { id, userId, username};
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
			Page<TbUserCollectDiscipline> findAll = dao.findAll(page, size, params);
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

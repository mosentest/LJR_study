package com.ljr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljr.dao.TbUserReadArticleDAO;
import com.ljr.entity.TbUserReadArticle;
import com.ljr.util.JsonResponse;
import com.ljr.util.Page;

/**
 * 用户阅读文章
 * @author 
 *
 */
@Controller
@RequestMapping("/")
public class TbUserReadArticleController {
	
	private TbUserReadArticleDAO dao = new TbUserReadArticleDAO();
	
	
	/**
	 * 编号，用户编号，用户名，文章编号，文章标题
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "userreadarticle/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbUserReadArticle> list(@RequestParam(required = false, defaultValue = "1") int page,
															@RequestParam(required = false, defaultValue = "1000") int size, 
															@RequestParam(required = false) final String id,
															@RequestParam(required = false) final String userId,
															@RequestParam(required = false) final String username,
															@RequestParam(required = false) final String aId,
															@RequestParam(required = false) final String title){
		JsonResponse<TbUserReadArticle> jsonResponse = new JsonResponse<TbUserReadArticle>();
		// 获取对应的参数
		String[] params = new String[] { id, userId, username,aId,title};
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
			Page<TbUserReadArticle> findAll = dao.findAll(page, size, params);
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

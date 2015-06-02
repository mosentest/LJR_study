package com.ljr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljr.dao.TbArticleDAO;
import com.ljr.entity.TbArticle;
import com.ljr.util.JsonResponse;
import com.ljr.util.Page;

@Controller
@RequestMapping("/")
public class TbArticleController {
	private  TbArticleDAO dao = new TbArticleDAO();
	
	/**
	 * 文章编号，标题，类型编号
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "article/list.html", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse<TbArticle> list(@RequestParam(required = true, defaultValue = "1") int page,
															@RequestParam(required = true, defaultValue = "10") int size, 
															@RequestParam(required = false) final String id,
															@RequestParam(required = false) final String title,
															@RequestParam(required = false) final String typeId){
		JsonResponse<TbArticle> jsonResponse = new JsonResponse<TbArticle>();
		// 获取对应的参数
		String[] params = new String[] { id, title, typeId };
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
			Page<TbArticle> findAll = dao.findAll(page, size, params);
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

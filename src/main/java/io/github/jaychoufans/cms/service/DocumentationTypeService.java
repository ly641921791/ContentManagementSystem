package io.github.jaychoufans.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.jaychoufans.cms.model.DocumentationType;

import java.util.List;

public interface DocumentationTypeService extends IService<DocumentationType> {

	/**
	 * 根据根结点id删除树
	 * @param id 根结点id
	 */
	void removeTreeByRootId(Long id);

	/**
	 * 根据根结点id删除树
	 * @param idList 根结点id
	 */
	void removeTreeByRootIds(List<Long> idList);

}

package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.DocumentationTypeMapper;
import io.github.jaychoufans.cms.model.DocumentationType;
import io.github.jaychoufans.cms.service.DocumentationTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentationTypeServiceImpl extends ServiceImpl<DocumentationTypeMapper, DocumentationType>
		implements DocumentationTypeService {

	@Resource
	private DocumentationTypeService that;

	@Override
	public boolean removeById(Serializable id) {
		if (id == null || that.getById(id) == null) {
			return false;
		}

		return super.removeById(id);
	}

	@Override
	@Transactional
	public void removeTreeByRootId(Long id) {
		if (id == null || that.getById(id) == null) {
			return;
		}

		that.removeById(id);

		DocumentationType args = new DocumentationType();
		args.setParentId(id);
		List<Long> ids = that.list(new QueryWrapper<>(args)).stream().map(DocumentationType::getId)
				.collect(Collectors.toList());

		that.removeTreeByRootIds(ids);
	}

	@Override
	@Transactional
	public void removeTreeByRootIds(List<Long> idList) {
		if (CollectionUtils.isEmpty(idList)) {
			return;
		}
		idList.forEach(id -> that.removeTreeByRootId(id));
	}

}

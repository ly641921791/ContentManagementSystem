package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.DocumentationMapper;
import io.github.jaychoufans.cms.model.Documentation;
import io.github.jaychoufans.cms.service.DocumentationService;
import org.springframework.stereotype.Service;

@Service
public class DocumentationServiceImpl extends ServiceImpl<DocumentationMapper, Documentation>
		implements DocumentationService {

	@Override
	public boolean save(Documentation entity) {
		if (entity.getIntroduction() == null) {
			entity.setIntroduction("");
		}
		return super.save(entity);
	}

}

package io.github.jaychoufans.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.jaychoufans.cms.model.Documentation;
import io.github.jaychoufans.cms.model.DocumentationLend;

public interface DocumentationService extends IService<Documentation> {

	void lend(Long documentationId);

	void modLendStatus(DocumentationLend args);

}

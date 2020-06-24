package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.exception.CmsException;
import io.github.jaychoufans.cms.mapper.DocumentationMapper;
import io.github.jaychoufans.cms.model.BookLend;
import io.github.jaychoufans.cms.model.Documentation;
import io.github.jaychoufans.cms.model.DocumentationLend;
import io.github.jaychoufans.cms.service.DocumentationLendService;
import io.github.jaychoufans.cms.service.DocumentationService;
import io.github.jaychoufans.cms.utils.WebUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class DocumentationServiceImpl extends ServiceImpl<DocumentationMapper, Documentation>
		implements DocumentationService {

	@Resource
	private DocumentationService that;

	@Resource
	private DocumentationLendService documentationLendService;

	@Override
	public boolean save(Documentation entity) {
		if (entity.getIntroduction() == null) {
			entity.setIntroduction("");
		}
		return super.save(entity);
	}

	@Override
	@Transactional
	public void lend(Long documentationId) {
		// 预扣资料库存
		Documentation documentation;
		Documentation documentationNew;
		do {
			documentation = that.getById(documentationId);
			if (documentation.getRemaining() < 1) {
				throw new CmsException("B0001", "资料库存不足");
			}
			documentationNew = new Documentation();
			documentationNew.setId(documentation.getId());
			documentationNew.setRemaining(documentation.getRemaining() - 1);
			documentationNew.setVersion(documentation.getVersion());
		}
		while (!that.updateById(documentationNew));

		// 添加借阅记录
		DocumentationLend documentationLend = new DocumentationLend();
		documentationLend.setUserId(WebUtils.getCurrentUser().getId());
		documentationLend.setDocumentationId(documentationId);
		documentationLendService.save(documentationLend);
	}

	@Override
	@Transactional
	public void modLendStatus(DocumentationLend args) {
		DocumentationLend documentationLend = documentationLendService.getById(args.getId());

		if (documentationLend == null) {
			throw new CmsException("A0400", "借还记录不存在");
		}

		DocumentationLend entity = new DocumentationLend();
		entity.setId(documentationLend.getId());
		entity.setVersion(documentationLend.getVersion());

		if (DocumentationLend.waitCollect.equals(documentationLend.getState())) {
			if (DocumentationLend.waitReturn.equals(args.getState())
					|| BookLend.alreadyReturn.equals(args.getState())) {
				entity.setState(args.getState());
			}
			else {
				throw new CmsException("A0400", "参数异常");
			}
		}
		else if (DocumentationLend.waitReturn.equals(documentationLend.getState())) {
			if (DocumentationLend.alreadyReturn.equals(args.getState())) {
				entity.setState(args.getState());
			}
			else {
				throw new CmsException("A0400", "参数异常");
			}
		}
		else {
			throw new CmsException("A0400", "图书已经归还");
		}

		if (documentationLendService.updateById(entity) && BookLend.alreadyReturn.equals(args.getState())) {
			// 图书库存 + 1
			Documentation documentation;
			Documentation documentationNew;
			do {
				documentation = that.getById(documentationLend.getDocumentationId());

				documentationNew = new Documentation();
				documentationNew.setId(documentation.getId());
				documentationNew.setRemaining(documentation.getRemaining() + 1);
				documentationNew.setVersion(documentation.getVersion());
			}
			while (!that.updateById(documentationNew));
		}
	}

}

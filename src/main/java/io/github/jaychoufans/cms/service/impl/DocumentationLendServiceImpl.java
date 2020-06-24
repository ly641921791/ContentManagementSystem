package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.DocumentationLendMapper;
import io.github.jaychoufans.cms.model.DocumentationLend;
import io.github.jaychoufans.cms.service.DocumentationLendService;
import org.springframework.stereotype.Service;

@Service
public class DocumentationLendServiceImpl extends ServiceImpl<DocumentationLendMapper, DocumentationLend>
		implements DocumentationLendService {

}

package io.github.jaychoufans.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.jaychoufans.cms.mapper.DocumentationTypeMapper;
import io.github.jaychoufans.cms.model.DocumentationType;
import io.github.jaychoufans.cms.service.DocumentationTypeService;
import org.springframework.stereotype.Service;

@Service
public class DocumentationTypeServiceImpl extends ServiceImpl<DocumentationTypeMapper, DocumentationType>
		implements DocumentationTypeService {

}

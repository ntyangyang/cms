package test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import cms.mapper.ComponentMapper;
import cms.mapper.PropertyMapper;
import cms.pojo.Component;
import cms.pojo.ComponentProperty;
import cms.spring.conifg.CmsApplication;

public class Main {
	public static void main(String[] args) {
		// 通过Java配置来实例化Spring容器
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CmsApplication.class);

		// 在Spring容器中获取Bean对象
		PropertyMapper PropertyMapper = context.getBean(PropertyMapper.class);
		ComponentProperty temp = PropertyMapper.selectByPrimaryKey(Long.valueOf(1));
		PropertyMapper.deleteByPrimaryKey(Long.valueOf(1));
		int insert = PropertyMapper.insert(temp);
		System.out.println(insert);
		System.out.println(temp.getId());
		// 销毁该容器
		context.destroy();

	}
}

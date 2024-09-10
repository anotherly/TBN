package kr.co.wizbrain.tbn.producer.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.wizbrain.tbn.producer.mapper.ProducerMapper;
import kr.co.wizbrain.tbn.producer.service.ProducerService;

@Service("producerService")
public class ProducerServiceImpl implements ProducerService {
	
	@Resource(name="producerMapper")
	private ProducerMapper producerMapper;
	
	
}

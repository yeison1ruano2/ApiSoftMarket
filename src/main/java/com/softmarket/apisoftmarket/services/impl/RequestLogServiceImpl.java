package com.softmarket.apisoftmarket.services.impl;

import com.softmarket.apisoftmarket.entity.RequestLog;
import com.softmarket.apisoftmarket.repository.RequestLogRepository;
import com.softmarket.apisoftmarket.services.RequestLogService;
import org.springframework.stereotype.Service;

@Service
public class RequestLogServiceImpl implements RequestLogService{

  private final RequestLogRepository requestLogRepository;

  public RequestLogServiceImpl(RequestLogRepository requestLogRepository) {
    this.requestLogRepository = requestLogRepository;
  }

  @Override
  public void guardarLog(RequestLog log) {
    requestLogRepository.save(log);
  }
}

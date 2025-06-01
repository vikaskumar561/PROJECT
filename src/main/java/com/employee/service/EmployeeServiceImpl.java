package com.employee.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.employee.dto.DepartmentDto;
import com.employee.dto.EmployeeResponseDto;
import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.repository.EmployeeRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
    EmployeeRepository EmployeeRepositorys;

    private static final String DEPARTMENT_SERVICE = "departmentService";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${department.service.url}")
    String departmentServiceUrl;

    @Override
    @CircuitBreaker(name = DEPARTMENT_SERVICE, fallbackMethod = "getAllDataFallback")
    @Retry(name = DEPARTMENT_SERVICE,fallbackMethod = "getAllDataFallback")
    public EmployeeResponseDto getAllData(long id) {

        Optional<Employee> emps = EmployeeRepositorys.findById(id);

        if (!emps.isPresent()) {
            log.error("Employee not found for given id {}", id);
            throw new EmployeeNotFoundException("Employee not found for given id " + id);
        }

        Employee emp = emps.get();

        String url = departmentServiceUrl + "/get-department/" + emp.getDepartmentId();

        // IMPORTANT: Remove try-catch here so exceptions propagate and trigger fallback
        DepartmentDto deptresponse = restTemplate.getForObject(url, DepartmentDto.class);

        log.info("Successfully fetched department for departmentId {}", emp.getDepartmentId());

        EmployeeResponseDto response = new EmployeeResponseDto();
        response.setEmployee(emp);
        response.setDept(deptresponse);
        return response;
    }

    // Fallback method signature: same params + Throwable
    public EmployeeResponseDto getAllDataFallback(long id, Throwable t) {
        log.error("Department service is down for employee ID {}: {}", id, t.getMessage());

        Optional<Employee> emps = EmployeeRepositorys.findById(id);
        if (!emps.isPresent()) {
            throw new EmployeeNotFoundException("Employee not found for given id " + id);
        }

        DepartmentDto fallbackDept = new DepartmentDto();
        fallbackDept.setName("Department service not available after multiple retry attempts");
        // other fields can be left null

        EmployeeResponseDto fallbackResponse = new EmployeeResponseDto();
        fallbackResponse.setEmployee(emps.get());
        fallbackResponse.setDept(fallbackDept);

        return fallbackResponse;
    }
}

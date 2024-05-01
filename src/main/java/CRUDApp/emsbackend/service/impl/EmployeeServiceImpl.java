package CRUDApp.emsbackend.service.impl;

import CRUDApp.emsbackend.dto.EmployeeDto;
import CRUDApp.emsbackend.entity.Employee;
import CRUDApp.emsbackend.exception.ResoourceNotFoundException;
import CRUDApp.emsbackend.mapper.EmployeeMapper;
import CRUDApp.emsbackend.repository.EmployeeRepository;
import CRUDApp.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {


        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
       Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResoourceNotFoundException("Employee is not exist with given id :" +employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
        Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResoourceNotFoundException("Employee is not exist with id :" +employeeId)
        );

        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());

        Employee updateEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee= employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResoourceNotFoundException("Employee is not exist with id :" +employeeId)
        );
        employeeRepository.deleteById(employeeId);
    }
}

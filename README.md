## EmployeeStateMachine

Steps to run the Application

- clone the repository.
- run `mvn package`
- run `docker build -t employee-state-machine .`
- run `docker run -p 8080:8080 -t employee-state-machine`


#### API Contract
- To **create** or **update** Employee you can use the Structure of
     **EmployeeDTO** {int: id, string: name, int: age, string: contarctInfo, string: state}

- You can use http://**hostname**:8080/employee to create a new Employee with initial state "**ADDED**"
- You can use http://**hostname**:8080/employee/{employeeId} to update the state for a specific Employee.

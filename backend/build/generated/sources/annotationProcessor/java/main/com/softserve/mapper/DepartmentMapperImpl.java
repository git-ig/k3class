package com.softserve.mapper;

import com.softserve.dto.DepartmentDTO;
import com.softserve.dto.DepartmentDTO.DepartmentDTOBuilder;
import com.softserve.entity.Department;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-26T14:44:38+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.27 (Homebrew)"
)
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Override
    public DepartmentDTO departmentToDepartmentDTO(Department department) {
        if ( department == null ) {
            return null;
        }

        DepartmentDTOBuilder departmentDTO = DepartmentDTO.builder();

        departmentDTO.id( department.getId() );
        departmentDTO.name( department.getName() );
        departmentDTO.disable( department.isDisable() );

        return departmentDTO.build();
    }

    @Override
    public Department departmentDTOToDepartment(DepartmentDTO departmentDTO) {
        if ( departmentDTO == null ) {
            return null;
        }

        Department department = new Department();

        department.setId( departmentDTO.getId() );
        department.setName( departmentDTO.getName() );
        department.setDisable( departmentDTO.isDisable() );

        return department;
    }

    @Override
    public List<DepartmentDTO> departmentsToDepartmentDTOs(List<Department> departments) {
        if ( departments == null ) {
            return null;
        }

        List<DepartmentDTO> list = new ArrayList<DepartmentDTO>( departments.size() );
        for ( Department department : departments ) {
            list.add( departmentToDepartmentDTO( department ) );
        }

        return list;
    }
}

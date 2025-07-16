package com.softserve.mapper;

import com.softserve.dto.RegistrationRequestDTO;
import com.softserve.dto.UserCreateDTO;
import com.softserve.dto.UserDTO;
import com.softserve.entity.User;
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
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setRole( user.getRole() );

        return userDTO;
    }

    @Override
    public User toUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setEmail( userDTO.getEmail() );
        user.setRole( userDTO.getRole() );

        return user;
    }

    @Override
    public User toCreateUser(RegistrationRequestDTO registrationDTO) {
        if ( registrationDTO == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( registrationDTO.getEmail() );
        user.setPassword( registrationDTO.getPassword() );

        return user;
    }

    @Override
    public List<UserDTO> toUserDTOs(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( User user : users ) {
            list.add( toUserDTO( user ) );
        }

        return list;
    }

    @Override
    public User toUser(UserCreateDTO userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userCreateDTO.getId() );
        user.setEmail( userCreateDTO.getEmail() );
        user.setPassword( userCreateDTO.getPassword() );

        return user;
    }

    @Override
    public UserCreateDTO toUserCreateDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserCreateDTO userCreateDTO = new UserCreateDTO();

        userCreateDTO.setId( user.getId() );
        userCreateDTO.setEmail( user.getEmail() );
        userCreateDTO.setPassword( user.getPassword() );

        return userCreateDTO;
    }
}

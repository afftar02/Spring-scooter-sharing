package by.grsu.scootersharing.dto;

import lombok.Data;

@Data
public class UserDto {
    public long userId;
    public String firstName;
    public String secondName;
    public String email;
    public String password;
}

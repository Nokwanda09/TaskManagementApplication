package com.singabenkosimpungose.taskmanagement.DTOs;

import lombok.*;


/**
 * Represent details put by the user when loging in
    @param username - the user’s username
    @param password - the user’s password

 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String username;

    private String password;
}

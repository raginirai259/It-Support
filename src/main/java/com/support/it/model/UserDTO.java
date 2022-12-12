package com.support.it.model;

import com.support.it.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
		private String name;
		private String username;
	    private String password;
	    private String email;

}

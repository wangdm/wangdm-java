package com.wangdm.user.dto;

import com.wangdm.core.dto.BaseDto;
import com.wangdm.core.dto.annotation.DtoMapper;
import com.wangdm.user.entity.User;

public class UserAccountDto extends BaseDto {
	
	@DtoMapper(entity=User.class, field="id")
	private String id;

	@DtoMapper(entity=User.class, field="username")
	private String username;

	@DtoMapper(entity=User.class, field="password")
	private String password;

	@DtoMapper(entity=User.class, field="email")
	private String email;

	@DtoMapper(entity=User.class, field="phone")
	private String phone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}

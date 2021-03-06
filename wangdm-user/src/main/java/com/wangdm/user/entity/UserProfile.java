package com.wangdm.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.wangdm.core.entity.ForeignEntity;
import com.wangdm.user.constant.Gender;

@Entity
@Table(name="user_profile")
public class UserProfile extends ForeignEntity {

	private static final long serialVersionUID = -5198650525243171585L;
	
	@Id
    @OneToOne
    @JoinColumn(name="id", referencedColumnName="id", nullable=false)
    private User user;
	
	@Column(name="nickname", length=20)
	private String nickname;
    
    @Column(name="realname", length=20)
    private String realname;

	@Column(name="avatar", length=250)
	private String avatar;

	@Column(name="gender")
	private Gender gender = Gender.SECRET;

	@Column(name="age")
	private Integer age = 0;

	@Column(name="motto", length=250)
	private String motto;

	@Override
	public Long getId() {
		
		if(this.user!=null)
			return this.user.getId();
		
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

}

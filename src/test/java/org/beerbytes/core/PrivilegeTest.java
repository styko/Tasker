package org.beerbytes.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PrivilegeTest  {
		
	@Test
	public void testIsPrivileged() {
		Privilege privilege = Privilege.ADMINISTRATOR;
		assertThat(privilege.isPrivileged(Privilege.USER)).isTrue();
		assertThat(privilege.isPrivileged(Privilege.MODERATOR)).isTrue();
		assertThat(privilege.isPrivileged(Privilege.ADMINISTRATOR)).isTrue();
		
		privilege = Privilege.USER;
		assertThat(privilege.isPrivileged(Privilege.USER)).isTrue();
		assertThat(privilege.isPrivileged(Privilege.MODERATOR)).isFalse();
		assertThat(privilege.isPrivileged(Privilege.ADMINISTRATOR)).isFalse();
		
		privilege = Privilege.MODERATOR;
		assertThat(privilege.isPrivileged(Privilege.USER)).isTrue();
		assertThat(privilege.isPrivileged(Privilege.MODERATOR)).isTrue();
		assertThat(privilege.isPrivileged(Privilege.ADMINISTRATOR)).isFalse();
	}
	
}

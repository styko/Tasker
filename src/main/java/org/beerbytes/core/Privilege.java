package org.beerbytes.core;

public enum Privilege
{
    USER, MODERATOR, ADMINISTRATOR;
    
    public boolean isPrivileged(Privilege requriedPrivilege){
    	return this.ordinal() >= requriedPrivilege.ordinal();
    }
}
package org.beerbytes;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class TaskerConfiguration extends Configuration {
	private int maxLength;
	
	@NotNull
    private String authHeader;

    @NotNull
    private String authSalt;

	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();

	@JsonProperty
	public int getMaxLength() {
		return maxLength;
	}

	@JsonProperty
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }
    
    public String getAuthHeader()
    {
        return authHeader;
    }

    public void setAuthHeader(String authHeader)
    {
        this.authHeader = authHeader;
    }

    public String getAuthSalt()
    {
        return authSalt;
    }

    public void setAuthSalt(String authSalt)
    {
        this.authSalt = authSalt;
    }
}

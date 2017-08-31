package org.beerbytes.core;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

//TODO replace Date with LocalDateTime and Time with LocalTime
@Entity
@Table(name = "task")
@NamedQueries({
	@NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"), 
	@NamedQuery(name = "Task.findAllbyUserId", query = "SELECT t FROM Task t where user.id = :id"),
	@NamedQuery(name = "Task.findByIdAndUserId", query = "SELECT t FROM Task t where t.id= :id and user.id = :userid")
})
public class Task {
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	@Length(min = 3, max = 255)
	@Column(nullable = false)
	private String info;

	@NotNull
	@Column(nullable = false)
	private Date begin;

	@NotNull
	@Column(nullable = false)
	private Date end;

	@NotNull
	@Column(nullable = false)
	private Time slackTime;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Task() {
		super();
	}

	public Task(String info, Date begin, Date end, Time slackTime, User user) {
		super();
		this.info = info;
		this.begin = begin;
		this.end = end;
		this.slackTime = slackTime;
		this.user = user;
	}
	
	@JsonProperty
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@JsonProperty
	public long getId() {
		return id;
	}

	@JsonProperty
	public String getInfo() {
		return info;
	}

	@JsonProperty
	public String getBegin() {
		return begin.toString();
	}

	@JsonProperty
	public String getEnd() {
		return end.toString();
	}

	@JsonProperty
	public String getSlackTime() {
		return slackTime.toString();
	}
	
	public void setInfo(String info) {
		this.info = info;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setSlackTime(Time slackTime) {
		this.slackTime = slackTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((begin == null) ? 0 : begin.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((slackTime == null) ? 0 : slackTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (begin == null) {
			if (other.begin != null)
				return false;
		} else if (!begin.equals(other.begin))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (id != other.id)
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (slackTime == null) {
			if (other.slackTime != null)
				return false;
		} else if (!slackTime.equals(other.slackTime))
			return false;
		return true;
	}
}

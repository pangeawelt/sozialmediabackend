package com.projekt.sozial.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Entity
@Table(name = "post")
@Data
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	/*
	 * Bu ilişki, bir kullanıcının birden fazla gönderi (post) oluşturabileceği
	 * anlamına gelir. Yani, "Post" tablosunda her bir kayıt, "User" tablosundan bir
	 * kullanıcıya ait olacaktır.
	 */

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	User user;

	String title;
	@Lob
	@Column(columnDefinition = "text")
	String text;

	@Temporal(TemporalType.TIMESTAMP)
	Date createDate;

}

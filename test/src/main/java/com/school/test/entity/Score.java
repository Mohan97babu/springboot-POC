package com.school.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Score {
	
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO,generator="sequenceGenerator")
  private long id;
  
  @Column(name="score")
  private long score;
  
  @ManyToOne
  @JoinColumn(name="student_id",nullable=false)
  private Student student;
  
  @ManyToOne
  @JoinColumn(name="test_id",nullable=false)
  private Test test;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
}

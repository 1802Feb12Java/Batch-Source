package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ANIMAL")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Animal {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="animSeq")
	@SequenceGenerator(allocationSize=1, name="animSeq", sequenceName="ANIM_SEQ")
	@Column(name="ANIMAL_ID")
	protected int id;
	
	@Column(name="ANIMAL_NAME")
	protected String name;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ANIMAL_CAVE")
	Cave cave;

}

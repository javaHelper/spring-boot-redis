package com.concretepage.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int age;
}

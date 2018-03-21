package com.revature.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class IceCream {
	
	@NotNull
	@Size(min=3, max=15)
	private String flavor;
	
	@NotNull
	@Min(1)
	private Integer scoops;

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	public Integer getScoops() {
		return scoops;
	}

	public void setScoops(Integer scoops) {
		this.scoops = scoops;
	}

	public IceCream(String flavor, Integer scoops) {
		super();
		this.flavor = flavor;
		this.scoops = scoops;
	}

	public IceCream() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "IceCream [flavor=" + flavor + ", scoops=" + scoops + "]";
	}

}

package org.meth4j.futil;

import java.util.List;

import javax.faces.model.SelectItem;

import com.google.common.collect.ImmutableList;

public class Util {
	
	public static List<SelectItem> toSelectItems(IKey[] kvs) {
		ImmutableList.Builder<SelectItem> unitBuilder = ImmutableList.builder();
		for (IKey kv: kvs) {
			unitBuilder.add(new SelectItem(kv.getKey(), kv.getLabel()));
		}
		return unitBuilder.build();
	}

}

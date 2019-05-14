package com.xishan.smartapicaller;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GitResponse{

	@SerializedName("total_count")
	private int totalCount;

	@SerializedName("incomplete_results")
	private boolean incompleteResults;

	@SerializedName("items")
	private List<ItemsItem> items;

	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	public int getTotalCount(){
		return totalCount;
	}

	public void setIncompleteResults(boolean incompleteResults){
		this.incompleteResults = incompleteResults;
	}

	public boolean isIncompleteResults(){
		return incompleteResults;
	}

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	@Override
 	public String toString(){
		return 
			"GitResponse{" + 
			"total_count = '" + totalCount + '\'' + 
			",incomplete_results = '" + incompleteResults + '\'' + 
			",items = '" + items + '\'' + 
			"}";
		}
}
package com.example.lol.model.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResVO {
	
	private Object data;
	private String resultCode;
	private String resultMsg;
}

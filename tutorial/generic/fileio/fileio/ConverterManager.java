package com.tutorial.fileio;

import java.util.HashMap;
import java.util.Map;

import com.tutorial.fileio.exception.NoSuchConverterException;

public class ConverterManager {
	/***
	 * ConverterMap에 추가, 삭제, 검색 등의 일을 수행하는 객체가 필요하고,
	 * ConverterManager는 그런 업무에 대해서는 ConverterMap을 관리하는 객체에게 위임해야 한다.
	 * Key Value 형식으로 저장을 할 때 Registry라는 말을 많이 쓴다.
	 * 단순히 데이터를 저장을 한다고 할 때는 Repository를 쓴다.
	 * 
	 * 
	 * 
	 */
	private final Map<TypeOfSourceAndResponse<?, ?>, GenericConverter<?, ?>> converterMap = new HashMap<>();
	
	public void addConverter(Class<?> sourceType, Class<?> responseType, GenericConverter<?, ?> converter) {
		converterMap.put(new TypeOfSourceAndResponse<>(sourceType, responseType), converter);
	}

	public <S, R> R requestConvert(S source, Class<R> responseType) {
		if(source == null || responseType == null) {
			throw new IllegalArgumentException();
		}
		
		@SuppressWarnings("unchecked")
		GenericConverter<S, R> converter = (GenericConverter<S, R>)converterMap.get(new TypeOfSourceAndResponse<>(source.getClass(), responseType));
		if(converter == null) {
			throw new NoSuchConverterException("No Converter for ResultType: " + responseType.getName());
		}
		
		return converter.convert(source);
	}
	
	private static class TypeOfSourceAndResponse<S, R>{
		Class<S> source;
		Class<R> response;
		
		public TypeOfSourceAndResponse(Class<S> source, Class<R> response) {
			this.source = source;
			this.response = response;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((response == null) ? 0 : response.hashCode());
			result = prime * result + ((source == null) ? 0 : source.hashCode());
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
			@SuppressWarnings("unchecked")
			TypeOfSourceAndResponse<S, R> other = (TypeOfSourceAndResponse<S, R>) obj;
			if (response == null) {
				if (other.response != null)
					return false;
			} else if (!response.equals(other.response))
				return false;
			if (source == null) {
				if (other.source != null)
					return false;
			} else if (!source.equals(other.source))
				return false;
			return true;
		}
		
	} // End of TypeOfSourceAndResponse
	
}

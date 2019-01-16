package com.tutorial.fileio;

import java.util.HashMap;
import java.util.Map;

import com.tutorial.fileio.copier.GenericCopier;
import com.tutorial.fileio.exception.NoSuchCopierException;

public class CopierManager {
	
	private final Map<TypeOfSourceAndResponse<?, ?>, GenericCopier<?, ?>> copierMap = new HashMap<>();
	
	public void addCopier(Class<?> targetSourceType, Class<?> copySourceType, GenericCopier<?, ?> copier) {
		copierMap.put(new TypeOfSourceAndResponse<>(targetSourceType, copySourceType), copier);
	}

	public <S, R> void requestCopy(S target, R copy) {
		if(target == null || copy == null) {
			throw new IllegalArgumentException();
		}
		
		@SuppressWarnings("unchecked")
		GenericCopier<S, R> copier = (GenericCopier<S, R>)copierMap.get(new TypeOfSourceAndResponse<>(target.getClass(), copy.getClass()));
		if(copier == null) {
			throw new NoSuchCopierException("No Copier for ResultType");
		}
		
		copier.copy(target, copy);
	}
	
	public <S, R> void requestMove(S target, R copy) {
		if(target == null || copy == null) {
			throw new IllegalArgumentException();
		}
		
		@SuppressWarnings("unchecked")
		GenericCopier<S, R> copier = (GenericCopier<S, R>)copierMap.get(new TypeOfSourceAndResponse<>(target.getClass(), copy.getClass()));
		if(copier == null) {
			throw new NoSuchCopierException("No Copier for ResultType");
		}
		
		copier.move(target, copy);
	}
	
	private static class TypeOfSourceAndResponse<S, R>{
		Class<S> targetSource;
		Class<R> copiedSource;
		
		public TypeOfSourceAndResponse(Class<S> target, Class<R> copy) {
			this.targetSource = target;
			this.copiedSource = copy;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((copiedSource == null) ? 0 : copiedSource.hashCode());
			result = prime * result + ((targetSource == null) ? 0 : targetSource.hashCode());
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
			if (copiedSource == null) {
				if (other.copiedSource != null)
					return false;
			} else if (!copiedSource.equals(other.copiedSource))
				return false;
			if (targetSource == null) {
				if (other.targetSource != null)
					return false;
			} else if (!targetSource.equals(other.targetSource))
				return false;
			return true;
		}
		
	} // End of TypeOfSourceAndResponse
	
}

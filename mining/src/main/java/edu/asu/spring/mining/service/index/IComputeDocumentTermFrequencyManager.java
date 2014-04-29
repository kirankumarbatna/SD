package edu.asu.spring.mining.service.index;

import java.util.Map;

import edu.asu.spring.mining.domain.impl.Document;

public interface IComputeDocumentTermFrequencyManager {
	
	public abstract Map<Integer, Document> computeDocumentTermFreqeuncies();

}

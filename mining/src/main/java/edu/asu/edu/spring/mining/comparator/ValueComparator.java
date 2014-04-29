package edu.asu.edu.spring.mining.comparator;

import java.util.Comparator;

import edu.asu.spring.mining.domain.impl.Document;

public class ValueComparator implements Comparator<Document> {
	@Override
	public int compare(Document md1, Document md2) {
		Double d1 = md1.getSimilarityCoeff();
		Double d2 = md2.getSimilarityCoeff();
		
		return d2.compareTo(d1);
	}
}

package edu.asu.spring.mining.domain.impl;

import edu.asu.spring.mining.domain.IDocument;

public class Document implements IDocument {
	
	private Integer maxTermFreq;
	private Double docNorm;
	private Double similarityCoeff;
	private Double docNormIDF;
	public Integer getMaxTermFreq() {
		return maxTermFreq;
	}
	public void setMaxTermFreq(Integer maxTermFreq) {
		this.maxTermFreq = maxTermFreq;
	}
	public Double getDocNorm() {
		return docNorm;
	}
	public void setDocNorm(Double docNorm) {
		this.docNorm = docNorm;
	}
	public Double getSimilarityCoeff() {
		return similarityCoeff;
	}
	public void setSimilarityCoeff(Double similarityCoeff) {
		this.similarityCoeff = similarityCoeff;
	}
	public Double getDocNormIDF() {
		return docNormIDF;
	}
	public void setDocNormIDF(Double docNormIDF) {
		this.docNormIDF = docNormIDF;
	}
	

}

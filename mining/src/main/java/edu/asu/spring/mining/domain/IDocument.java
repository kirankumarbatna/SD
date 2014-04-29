package edu.asu.spring.mining.domain;

public interface IDocument {
	
	public Integer getMaxTermFreq();
	public void setMaxTermFreq(Integer maxTermFreq);
	
	public Double getDocNorm();
	public void setDocNorm(Double docNorm);;
	
	public Double getSimilarityCoeff();
	public void setSimilarityCoeff(Double similarityCoeff);
	
	public Double getDocNormIDF();
	public void setDocNormIDF(Double docNormIDF);

}

package edu.asu.spring.mining.service.index.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.index.TermEnum;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import edu.asu.spring.mining.domain.impl.Document;
import edu.asu.spring.mining.service.index.IComputeDocumentTermFrequencyManager;

@Service
public class ComputeDocumentTermFrequencyManager implements
		IComputeDocumentTermFrequencyManager {

	@Override
	public Map<Integer, Document> computeDocumentTermFreqeuncies() {

		Map<Integer, Document> docMaps = new HashMap<Integer, Document>();
		try {
			IndexReader r = IndexReader.open(FSDirectory
					.open(new File("index")));
			int totalDocs = r.maxDoc();
			
			Document[] docsArray = new Document[r.maxDoc()];

			for (int i = 0; i < docsArray.length; i++) {

				docsArray[i] = new Document();
			}

			double idf = 0.0;
			TermEnum t = r.terms();
			while (t.next()) {

				Term te = new Term("contents", t.term().text());
				TermDocs td = r.termDocs(te);
				int docFreq = r.docFreq(te);

				// calculate max term frequency and doc norm for each document
				while (td.next()) {
					int docId = td.doc();
					
					idf = Math.log((totalDocs / docFreq));
					docsArray[docId]
							.setDocNorm((docsArray[docId].getDocNorm() + td
									.freq() * td.freq()));
					docsArray[docId].setDocNormIDF(docsArray[docId]
							.getDocNormIDF()
							+ td.freq()
							* td.freq()
							* idf
							* idf);
					if (docsArray[docId].getMaxTermFreq() < td.freq()) {
						docsArray[docId].setMaxTermFreq(td.freq());
					}

				}

			}

			for (int i = 0; i < docsArray.length; i++) {
				docsArray[i]
						.setDocNorm(Math.pow(docsArray[i].getDocNorm(), 0.5));
				docsArray[i].setDocNormIDF(Math.pow(
						docsArray[i].getDocNormIDF(), 0.5));
				docMaps.put(i, docsArray[i]);
				// System.out.println("doc Id:" + i + ", doc norm:"
				// + docsArray[i].getDocNorm() + "max term freq:"
				// + docsArray[i].getMaxTermFreq());
				// System.out
				// .println("--------------------------------------------------------------------------------------------");
				// }
			}

		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return docMaps;
	}

}

package edu.asu.spring.mining.service.searchmanagement.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.store.FSDirectory;
import org.springframework.stereotype.Service;

import edu.asu.edu.spring.mining.comparator.ValueComparator;
import edu.asu.spring.mining.domain.impl.Document;
import edu.asu.spring.mining.service.searchmanagement.IFindSimialrDocuments;

@Service
public class FindSimialrDocuments implements IFindSimialrDocuments {

	@Override
	public LinkedHashMap<Integer, Document> findSimialrRequirementsBasedOnKeywords(String keyword,
			Map<Integer, Document> docMaps) {
		IndexReader r = null;
		
		try {
			r = IndexReader.open(FSDirectory.open(new File("index")));
		} catch (CorruptIndexException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		double queryNorm = 0.0;
		Map<String, Integer> queryMap = new HashMap<String, Integer>();
		LinkedHashMap<Integer, Document> sortedMap = null;
		// Calculate the query norm .
		while (!(keyword.equals("quit"))) {
			String[] terms = keyword.split("\\s+");

			for (String word : terms) {
				if (queryMap.containsKey(word)) {
					int count = queryMap.get(word);
					queryNorm -= count * count;
					queryMap.put(word, count++);
					queryNorm += count * count;
				} else {
					queryNorm++;

					queryMap.put(word, 1);
				}

			}

			queryNorm = Math.pow(queryNorm, 0.5);
			Set<Integer> docSet = docMaps.keySet();
			Iterator<Integer> keyItr = docSet.iterator();
			while (keyItr.hasNext()) {
				Integer id = keyItr.next();
				docMaps.get(id).setSimilarityCoeff(0.0);
			}

			long st = System.nanoTime();
			Set<String> keys = queryMap.keySet();
			Iterator<String> itr = keys.iterator();
			int totalNumberOfDocuments = r.maxDoc();
			// calculates the similarity coefficients for query and each doc.
			while (itr.hasNext()) {

				Term term = new Term("contents", itr.next());
				try {
					int documentFrequency = r.docFreq(term);

					TermDocs tdocs = r.termDocs(term);

					while (tdocs.next()) {
						double idf = Math.log(totalNumberOfDocuments
								/ documentFrequency);
						double sim = docMaps.get(tdocs.doc())
								.getSimilarityCoeff()
								+ queryMap.get(term.text())
								* tdocs.freq()
								* idf;
						docMaps.get(tdocs.doc()).setSimilarityCoeff(sim);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Set<Integer> docIds = docMaps.keySet();
			Iterator<Integer> iterator = docIds.iterator();
			while (iterator.hasNext()) {
				Integer id = iterator.next();
				Document myDocument = docMaps.get(id);
				double simCoef = myDocument.getSimilarityCoeff();
				simCoef = simCoef / (queryNorm * myDocument.getDocNormIDF());
				myDocument.setSimilarityCoeff(simCoef);
				docMaps.put(id, myDocument);
			}
			// Returns the sorted documents based on similarity coefficients
			 sortedMap = sortHashMapByValues(docMaps);
			Set<Integer> integers = sortedMap.keySet();
			int count = 0;
			for (Iterator<Integer> iterator2 = integers.iterator(); iterator2
					.hasNext();) {
				if (++count <= 10) {
					Integer id = (Integer) iterator2.next();
					System.out.println("Doc Id:" + id + "Similarity Coeff:"
							+ sortedMap.get(id).getSimilarityCoeff());
				} else
					break;
			}

		}
		return sortedMap;
	}

	// Sorting based on similarity coefficients.
	public LinkedHashMap<Integer, Document> sortHashMapByValues(
			Map<Integer, Document> docMaps) {
		List<Integer> mapKeys = new ArrayList<Integer>(docMaps.keySet());
		List<Document> mapValues = new ArrayList<Document>(docMaps.values());
		Collections.sort(mapValues, new ValueComparator());

		Collections.sort(mapKeys);

		LinkedHashMap<Integer, Document> sortedMap = new LinkedHashMap<Integer, Document>();

		Iterator<Document> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Document val = (Document) valueIt.next();
			Iterator<Integer> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				Integer key = (Integer) keyIt.next();
				Double comp1 = docMaps.get(key).getSimilarityCoeff();
				Double comp2 = val.getSimilarityCoeff();

				if (comp2.equals(comp1)) {
					mapKeys.remove(key);
					sortedMap.put((Integer) key, (Document) val);
					break;
				}

			}

		}
		return sortedMap;
	}

}

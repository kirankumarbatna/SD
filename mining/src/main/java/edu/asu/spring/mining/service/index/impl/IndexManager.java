package edu.asu.spring.mining.service.index.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;

import edu.asu.spring.mining.service.index.IIndexManager;

@Service
public class IndexManager implements IIndexManager {

	@Override
	public void indexRequirementFiles() throws IOException {

		Properties p = new Properties();
		p.load(IndexManager.class.getClassLoader()
				.getResourceAsStream("storage.properties"));

		String parent = (String) p.get("storagepath");

	      String indexpath = parent + "/index/requirement_index";
	      
	      String docspath = parent + "/new_requirement";
	      
	      addToIndex(indexpath, docspath);
		
	}
	
	
	@Override
	public void indexComponentFiles() throws IOException {

		Properties p = new Properties();
		p.load(IndexManager.class.getClassLoader()
				.getResourceAsStream("storage.properties"));

		String parent = (String) p.get("storagepath");

	      String indexpath = parent + "/index/component_index";
	      
	      String docspath = parent + "/new_component";
	      
	      addToIndex(indexpath, docspath);
		
	}
	
	
	

	/** Index all text files under a directory. */
	public static void addToIndex(String indexPath,String docsPath) {
		
		final File docDir = new File(docsPath);
		if (!docDir.exists() || !docDir.canRead()) {
			System.out
					.println("Document directory '"
							+ docDir.getAbsolutePath()
							+ "' does not exist or is not readable, please check the path");
			System.exit(1);
		}

		Date start = new Date();
		try {
			System.out.println("Indexing to directory '" + indexPath + "'...");

			Directory dir = FSDirectory.open(new File(indexPath));
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
			IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_31,
					analyzer);

			iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);

			IndexWriter writer = new IndexWriter(dir, iwc);
			indexDocs(writer, docDir, docsPath);

			writer.close();

			Date end = new Date();
			System.out.println(end.getTime() - start.getTime()
					+ " total milliseconds");

		} catch (IOException e) {
			System.out.println(" caught a " + e.getClass()
					+ "\n with message: " + e.getMessage());
		}
	}

	static void indexDocs(IndexWriter writer, File file, String docsPath)
			throws IOException {

		if (file.canRead()) {
			if (file.isDirectory()) {
				String[] files = file.list();

				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						indexDocs(writer, new File(file, files[i]), docsPath);
					}
				}
			} else {

				FileInputStream fis;
				try {
					fis = new FileInputStream(file);
				} catch (FileNotFoundException fnfe) {
					return;
				}

				try {

					// make a new, empty document
					Document doc = new Document();

					Field pathField = new Field("path", file.getPath()
							.substring(docsPath.length()), Field.Store.YES,
							Field.Index.NO);
					//pathField.setIndexOptions(IndexOptions.DOCS_ONLY);
					doc.add(pathField);

					NumericField modifiedField = new NumericField("modified");
					modifiedField.setLongValue(file.lastModified());
					doc.add(modifiedField);

					doc.add(new Field("contents", new BufferedReader(
							new InputStreamReader(fis, "UTF-8"))));

					if (writer.getConfig().getOpenMode() == OpenMode.CREATE) {
						System.out.println("adding " + file);
						writer.addDocument(doc);
					} else {
						System.out.println("updating " + file);
						writer.updateDocument(new Term("path", file.getPath()),
								doc);
					}

				} finally {
					fis.close();
				}
			}
		}
	}

}
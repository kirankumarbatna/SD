package edu.asu.spring.mining.service.index.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
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
		p.load(IndexManager.class.getClassLoader().getResourceAsStream(
				"storage.properties"));

		String parent = (String) p.get("storagepath");

		String indexpath = parent + "/index/requirement_index";

		String docspath = parent + "/new_requirement";

		IndexDirectory(indexpath, docspath);

	}

	@Override
	public void indexComponentFiles() throws IOException {

		Properties p = new Properties();
		p.load(IndexManager.class.getClassLoader().getResourceAsStream(
				"storage.properties"));

		String parent = (String) p.get("storagepath");

		String indexpath = parent + "/index/component_index";

		String docspath = parent + "/new_component";

		IndexDirectory(indexpath, docspath);

	}

	/**
	 * This method is used to index all files in the directory
	 * 
	 * @param indexPath
	 * @param directoryPath
	 */
	public static void IndexDirectory(String indexPath, String directoryPath) {

		final File file = new File(directoryPath);

		try {

			Directory dir = FSDirectory.open(new File(indexPath));
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(
					Version.LUCENE_31, analyzer);

			indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);

			IndexWriter writer = new IndexWriter(dir, indexWriterConfig);

			if (file.isDirectory()) {
				String[] files = file.list();

				if (files != null) {
					for (int i = 0; i < files.length; i++) {
						indexfile(writer, new File(file, files[i]), directoryPath);
					}
				}

			} else {

				// index one file
				indexfile(writer, file, directoryPath);
			}
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to index one file
	 * 
	 * @param writer
	 * @param file
	 * @param filePath
	 * @throws IOException
	 */
	static void indexfile(IndexWriter writer, File file, String filePath)
			throws IOException {

		FileInputStream fileInputStream = null;

		try {
			fileInputStream = new FileInputStream(file);
			Document doc = new Document();

			NumericField modifiedField = new NumericField("modified");
			modifiedField.setLongValue(file.lastModified());
			doc.add(modifiedField);

			doc.add(new Field("contents", new BufferedReader(
					new InputStreamReader(fileInputStream, "UTF-8"))));

			Field pathField = new Field("path", file.getPath().substring(
					filePath.length()), Field.Store.YES, Field.Index.NO);
			doc.add(pathField);

			writer.addDocument(doc);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fileInputStream.close();
		}
	}

}
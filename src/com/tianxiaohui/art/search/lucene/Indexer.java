package com.tianxiaohui.art.search.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {

	public static String INDEX_DIR = "C:/work/temp/index";
	public static String FOLDER_ROOT = "C:/work/projects/art";
	public static String INDEX_FIELD_FILE_NAME = "fileName";
	public static String INDEX_FIELD_FILE_FULL_NAME = "fileFullName";
	public static String INDEX_FIELD_FILE_CREATE_TIME = "createTime";
	
	public static void main(String[] args) {
		try {
			Indexer.index();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This is an demo axample to index files in this project
	 * @throws IOException 
	 */
	public static void index() throws Exception {
		Directory directory = FSDirectory.open(new File(Indexer.INDEX_DIR));
		Set<String> stopWords = new HashSet<String>();
		stopWords.add("com");
		stopWords.add("maneco");
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35, stopWords);
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, analyzer);
		IndexWriter iw = new IndexWriter(directory, iwc);
		
		iw.addDocuments(Indexer.createDocs());
		iw.commit();
		iw.close();
	}
	
	private static List<Document> createDocs() throws Exception {
		File rootDir = new File(Indexer.FOLDER_ROOT);
		
		if (!rootDir.isDirectory()) {
			throw new Exception("Ilegal root folder: " + Indexer.FOLDER_ROOT);
		}
		
		return Indexer.loopDir(rootDir);
	}
	
	private static List<Document> loopDir(File dir) {
		List<Document> docs = new ArrayList<Document>();
		Document doc = null;
		for (File file : dir.listFiles()) {
			if (file.isFile()) {
				doc = new Document();
				doc.add(new Field(Indexer.INDEX_FIELD_FILE_NAME, file.getName(), Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field(Indexer.INDEX_FIELD_FILE_FULL_NAME, file.toString(), Field.Store.YES, Field.Index.ANALYZED));
				doc.add(new Field(Indexer.INDEX_FIELD_FILE_CREATE_TIME, String.valueOf(file.lastModified()), Field.Store.YES, Field.Index.NOT_ANALYZED));
				docs.add(doc);
			} else {
				docs.addAll(loopDir(file));
			}
		}
		
		return docs;
	}

}

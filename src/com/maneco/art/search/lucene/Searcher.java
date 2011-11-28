package com.maneco.art.search.lucene;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Searcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Searcher.search("art");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void search(String keywords) throws Exception {
		Directory directory = FSDirectory.open(new File(Indexer.INDEX_DIR));
		IndexReader ir = IndexReader.open(directory);
		IndexSearcher is = new IndexSearcher(ir);
		
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
		QueryParser parser = new QueryParser(Version.LUCENE_35, Indexer.INDEX_FIELD_FILE_FULL_NAME, analyzer);
		Query query = parser.parse(keywords);
		TopDocs topDocs = is.search(query, null, 20);
		System.out.println("total hits: " + topDocs.totalHits);
		
		if (topDocs.totalHits > 0) {
			for (ScoreDoc sd : topDocs.scoreDocs) {
				System.out.println(is.doc(sd.doc));
			}
		}
		//System.out.println(is.doc(710).get(Indexer.INDEX_FIELD_FILE_NAME));
	}

}

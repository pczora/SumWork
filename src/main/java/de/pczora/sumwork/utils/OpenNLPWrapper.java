package de.pczora.sumwork.utils;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.InputStream;

/**
 * Created by pczora on 16/08/14.
 */
public class OpenNLPWrapper {
    static SentenceModel sentenceModel;
    static SentenceDetectorME sentenceDetector;

    static TokenizerModel tokenizerModel;
    static Tokenizer tokenizer;

    public String[] extractSentences(String text) {
        if (sentenceDetector == null) {
            InputStream modelIn = null;
            try {
                //            modelIn = new FileInputStream("en-sent.bin");
                modelIn = getClass().getResourceAsStream("/en-sent.bin");
                sentenceModel = new SentenceModel(modelIn);
                sentenceDetector = new SentenceDetectorME(sentenceModel);
                return sentenceDetector.sentDetect(text);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sentenceDetector.sentDetect(text);
    }

    public String[] tokenize(String text) {
        if (tokenizer == null) {
            InputStream modelIn = null;
            try {
                modelIn = getClass().getResourceAsStream("/en-token.bin");
                tokenizerModel = new TokenizerModel(modelIn);
                tokenizer = new TokenizerME(tokenizerModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return tokenizer.tokenize(text);
    }

    public int tokenCount(String text) {
        return tokenize(text).length;
    }
}

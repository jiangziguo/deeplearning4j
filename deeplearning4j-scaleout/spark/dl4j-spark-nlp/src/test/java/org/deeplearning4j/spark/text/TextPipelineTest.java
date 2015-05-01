/*
 *
 *  * Copyright 2015 Skymind,Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package org.deeplearning4j.spark.text;

import static org.junit.Assert.*;

import org.apache.spark.api.java.JavaRDD;
import org.deeplearning4j.berkeley.Pair;
import org.deeplearning4j.models.word2vec.wordstore.VocabCache;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.Collection;

/**
 * Created by agibsonccc on 1/29/15.
 */
public class TextPipelineTest extends BaseSparkTest {

    @Test
    public void testTextPipeline() throws Exception {
        JavaRDD<String> corpus = sc.textFile(new ClassPathResource("basic/word2vec.txt").getFile().getAbsolutePath());
        TextPipeline pipeline = new TextPipeline(corpus,1);
        Pair<VocabCache,Long> pair = pipeline.process();
        assertEquals(pair.getFirst().numWords(), 2);
        assertTrue(pair.getSecond() > 0);
        VocabCache vocab = pair.getFirst();
        Collection<String> words = vocab.words();
        assertTrue(words.contains("UNK") && words.contains("test"));
    }





}

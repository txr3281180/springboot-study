package com.txr.spbbasic.fenci;

public class WordFenCi {

    /**
       <dependency>
         <groupId>org.apdplat</groupId>
         <artifactId>word</artifactId>
         <version>1.3</version>
       </dependency>
     */

 /*   @Test
    public void test() {
          *//*    List<Word> words1 = WordSegmenter.seg("中国建设银行华东分行有限公司");
        System.out.println("移除停用词:" + words1);

        List<Word> words2 = WordSegmenter.segWithStopWords("中国建设银行华东分行有限公司");

        System.out.println("保留停用词:" + words2);*//*

        System.out.println("--------------------");

        WordFenCi wordFenCi = new WordFenCi();
        Map<String, String> stringMap = wordFenCi.segMore("中国建设银行华东分行有限公司");
        for (String string : stringMap.keySet()) {
            System.out.println(string + "： " + stringMap.get(string));
        }
    }

    public Map<String, String> segMore(String text) {
        Map<String, String> map = new HashMap<>();
        SegmentationAlgorithm[] values = SegmentationAlgorithm.values();
        for (SegmentationAlgorithm segmentationAlgorithm : values) {
            map.put(segmentationAlgorithm.getDes(), seg(text, segmentationAlgorithm));
        }
        return map;
    }

    private static String seg(String text, SegmentationAlgorithm segmentationAlgorithm) {
        StringBuilder result = new StringBuilder();
        List<Word> words = WordSegmenter.segWithStopWords(text, segmentationAlgorithm);
        System.out.println(words);

        for (Word word : WordSegmenter.segWithStopWords(text, segmentationAlgorithm)) {
            result.append(word.getText()).append(" ");
        }
        return result.toString();
    }
*/

    // -----------------------------------------------------------------------------

   /* @Test
    public void testWordFenci () {
        String text = "上海市浦东发展银行浦江分行";
        String text2 ="浦发银行上海分行";

        SegmentationAlgorithm[] values = SegmentationAlgorithm.values();
        for (SegmentationAlgorithm value : values) {
            List<Word> words = WordSegmenter.segWithStopWords(text, value);
            System.out.println(value.getDes() + ":" + words);
        }

        System.out.println("---------------------------------------");
        for (SegmentationAlgorithm value : values) {
            List<Word> words = WordSegmenter.seg(text, value);
            System.out.println(value.getDes() + ":" + words);
        }
    }


    *//*方式一：余弦相似度，通过计算两个向量的夹角余弦值来评估他们的相似度*//*
    @Test
    public void testWordFenci1 () {
        String text = "上海市浦东发展银行浦江分行";
        String text2 ="浦发银行上海分行";
        String test3 = "浦发银行";
        String test4 = "普法银行";
        String test5 = "浦发很行";

        TextSimilarity textSimilarity = new CosineTextSimilarity();
        double v = textSimilarity.similarScore(test3, text);
        double v1 = textSimilarity.similarScore(test3, text2);
        double v2 = textSimilarity.similarScore(test3, test4);
        double v3 = textSimilarity.similarScore(test3, test5);
        double v4 = textSimilarity.similarScore(test3, test3);
        System.out.println(v);
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
    }

    *//*方式二：简单共有词，通过计算两篇文档共有的词的总字符数除以最长文档字符数来评估他们的相似度*//*
    @Test
    public void testWordFenci2 () {
        String text = "上海市浦东发展银行浦江分行";
        String text2 ="浦发银行上海分行";
        String test3 = "浦发银行";
        String test4 = "普法银行";
        String test5 = "浦发很行";

        TextSimilarity textSimilarity = new SimpleTextSimilarity();
        double v = textSimilarity.similarScore(test3, text);
        double v1 = textSimilarity.similarScore(test3, text2);
        double v2 = textSimilarity.similarScore(test3, test4);
        double v3 = textSimilarity.similarScore(test3, test5);
        double v4 = textSimilarity.similarScore(test3, test3);
        System.out.println(v);
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);

    }


    *//*方式三：编辑距离，通过计算两个字串之间由一个转成另一个所需的最少编辑操作次数来评估他们的相似度*//*
    @Test
    public void testWordFenci3 () {
        String text = "上海市浦东发展银行浦江分行";
        String text2 ="浦发银行上海分行";
        String test3 = "浦发银行";
        String test4 = "普法银行";
        String test5 = "浦发很行";
        Similarity textSimilarity = new EditDistanceTextSimilarity();
        double v = textSimilarity.similarScore(test3, text);
        double v1 = textSimilarity.similarScore(test3, text2);
        double v2 = textSimilarity.similarScore(test3, test4);
        double v3 = textSimilarity.similarScore(test3, test5);
        double v4 = textSimilarity.similarScore(test3, test3);
        System.out.println(v);
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
    }


    *//*方式四：SimHash + 汉明距离，先使用SimHash把不同长度的文本映射为等长文本，然后再计算等长文本的汉明距离*//*
    @Test
    public void testWordFenci4 () {
        String text = "上海市浦东发展银行浦江分行";
        String text2 ="浦发银行上海分行";
        String test3 = "浦发银行";
        String test4 = "普法银行";
        String test5 = "浦发很行";
        TextSimilarity textSimilarity = new SimHashPlusHammingDistanceTextSimilarity();
        double v = textSimilarity.similarScore(test3, text);
        double v1 = textSimilarity.similarScore(test3, text2);
        double v2 = textSimilarity.similarScore(test3, test4);
        double v3 = textSimilarity.similarScore(test3, test5);
        double v4 = textSimilarity.similarScore(test3, test3);
        System.out.println(v);
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
    }

    *//*方式五：Jaccard相似性系数，通过计算两个集合交集的大小除以并集的大小来评估他们的相似度*//*
    @Test
    public void testWordFenci5 () {
        String text = "上海市浦东发展银行浦江分行";
        String text2 ="浦发银行上海分行";
        String test3 = "浦发银行";
        String test4 = "普法银行";
        String test5 = "浦发很行";
        TextSimilarity textSimilarity = new JaccardTextSimilarity();
        double v = textSimilarity.similarScore(test3, text);
        double v1 = textSimilarity.similarScore(test3, text2);
        double v2 = textSimilarity.similarScore(test3, test4);
        double v3 = textSimilarity.similarScore(test3, test5);
        double v4 = textSimilarity.similarScore(test3, test3);
        System.out.println(v);
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
    }

    *//*方式六：欧几里得距离（Euclidean Distance），通过计算两点间的距离来评估他们的相似度*//*
    @Test
    public void testWordFenci6 () {
        String text = "上海市浦东发展银行浦江分行";
        String text2 ="浦发银行上海分行";
        String test3 = "浦发银行";
        String test4 = "普法银行";
        String test5 = "浦发很行";
        TextSimilarity textSimilarity = new EuclideanDistanceTextSimilarity();
        double v = textSimilarity.similarScore(test3, text);
        double v1 = textSimilarity.similarScore(test3, text2);
        double v2 = textSimilarity.similarScore(test3, test4);
        double v3 = textSimilarity.similarScore(test3, test5);
        double v4 = textSimilarity.similarScore(test3, test3);
        System.out.println(v);
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
    }

    *//*方式七：曼哈顿距离（Manhattan Distance），通过计算两个点在标准坐标系上的绝对轴距总和来评估他们的相似度*//*
    @Test
    public void testWordFenci7 () {
        String text = "上海市浦东发展银行浦江分行";
        String text2 ="浦发银行上海分行";
        String test3 = "浦发银行";
        String test4 = "普法银行";
        String test5 = "浦发很行";
        TextSimilarity textSimilarity = new ManhattanDistanceTextSimilarity();
        double v = textSimilarity.similarScore(test3, text);
        double v1 = textSimilarity.similarScore(test3, text2);
        double v2 = textSimilarity.similarScore(test3, test4);
        double v3 = textSimilarity.similarScore(test3, test5);
        double v4 = textSimilarity.similarScore(test3, test3);
        System.out.println(v);
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
        System.out.println(v4);
    }*/
}
package com.hanyuToPinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * Created by Calm on 2019/9/21
 */
public class MainClass {
    public static void main( String[] args )
    {
        try {
            String pinYin = getPinYin("你好世界,git提交");
            System.out.println( pinYin + "Hello World!" );
        }catch (Exception e){
            e.printStackTrace();  
        }


    }

    public static String getPinYin(String str) throws Exception{
        char[] t1 = null;
        t1 = str.toCharArray();
        String[] t2 = new String[t1.length];
        // System.out.println(t2.length);
        // 设置汉字拼音输出的格式
        net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat t3 = new net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat();

        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        for (int i =0; i < t0; i++) {
            // 判断能否为汉字字符
            // System.out.println(t1[i]);
            if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                //PinyinHelper.toHanyuPinyinStringArray(t1[i]);
                t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
                t4 += t2[0]+" ";// 取出该汉字全拼的第一种读音并连接到字符串t4后
            } else {
                // 如果不是汉字字符，间接取出字符并连接到字符串t4后
                t4 += Character.toString(t1[i]);
            }
        }
        return t4;
    }
}

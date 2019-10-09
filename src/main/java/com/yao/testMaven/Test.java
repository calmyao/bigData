package com.yao.testMaven;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * Created by Calm on 2018/7/20
 */
public class Test {
    public static void main(String[] args) throws Exception{
        HanyuPinyinOutputFormat format= new HanyuPinyinOutputFormat();

        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);

        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        String[] pinyinArray = null;
        pinyinArray = PinyinHelper.toHanyuPinyinStringArray('黄', format);
        for(int i = 0; i < pinyinArray.length; ++i)

        {

            System.out.println(pinyinArray[i]);

        }
    }
}

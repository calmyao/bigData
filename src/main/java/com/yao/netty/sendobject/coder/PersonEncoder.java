package com.yao.netty.sendobject.coder;

import com.yao.netty.sendobject.bean.Person;
import com.yao.netty.sendobject.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by Calm on 2019/1/11
 * 序列化
 * 将object转换为Byte[]
 *
 */
public class PersonEncoder extends MessageToByteEncoder<Person>{
    @Override
    protected void encode(ChannelHandlerContext ctx, Person msg, ByteBuf out) throws Exception {
        //工具类:将object转换为byte[]
        byte[] datas = ByteObjConverter.objectToByte(msg);
        out.writeBytes(datas);
        ctx.flush();
    }
}

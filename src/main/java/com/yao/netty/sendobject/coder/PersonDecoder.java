package com.yao.netty.sendobject.coder;

import com.yao.netty.sendobject.utils.ByteBufToBytes;
import com.yao.netty.sendobject.utils.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 反序列化
 * 将Byte[]转换为Object
 * Created by Calm on 2019/02/19
 */
public class PersonDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext cxt, ByteBuf in, List<Object> out) throws Exception {
        //工具类:将ByteBuf转换为byte[]
        ByteBufToBytes read = new ByteBufToBytes();
        byte[] bytes = read.read(in);
        //工具类:将byte[]转换为object
        Object obj = ByteObjConverter.byteToObject(bytes);
        out.add(obj);
    }
}

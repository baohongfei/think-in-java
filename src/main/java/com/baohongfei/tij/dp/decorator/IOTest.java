package com.baohongfei.tij.dp.decorator;

import java.io.*;

/**
 * Created by terry on 05/07/17.
 */
public class IOTest {
    public static void main(String[] args) throws IOException {
        final String filePath = "/Users/terry/Desktop/test.txt";

        //InputStream相当于被装饰的接口或者抽象类，FileInputStream相当于原始的待装饰的对象，FileInputStream无法装饰InputStream
        //另外FileInputStream是以只读方式打开了一个文件,并打开了一个文件的句柄存放在FileDescriptor对象的handle属性
        //所以下面有关回退和重新标记等操作，都是在堆中建立缓冲区所造成的假象,并不是真正的文件流在回退或者重新标记
        InputStream inputStream = new FileInputStream(filePath);

        System.out.println("inputStream.markSupported()：" + inputStream.markSupported());

        System.out.println("下面分别展示三种装饰器的作用BufferedInputStream,DataInputStream,PushbackInputStream,LZ下面做了三个装饰器的功能演示");

        System.out.println("首先装饰成BufferedInputStream，它提供我们mark，reset的功能");

        //装饰成 BufferedInputStream
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        System.out.println("bufferedInputStream.markSupported()：" + bufferedInputStream.markSupported());
        bufferedInputStream.mark(0);
        char c = (char) bufferedInputStream.read();
        System.out.println("文件"+filePath+"的第一个字符：" + c);
        bufferedInputStream.reset();
        c = (char) bufferedInputStream.read();
        System.out.println("重置以后再读一个字符，依然会是第一个字符:" + c);
        bufferedInputStream.reset();

        //装饰成 DataInputStream,我们为了又使用DataInputStream,又使用BufferedInputStream的mark reset功能，所以我们再进行一层包装
        //注意，这里如果不使用BufferedInputStream，而使用原始的InputStream，read方法返回的结果会是-1，即已经读取结束
        //因为BufferedInputStream已经将文本的内容读取完毕，并缓冲到堆上，默认的初始缓冲区大小是8192B
        DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
        //这是BufferedInputStream提供的功能，如果不在这个基础上包装会出错
        dataInputStream.reset();
        System.out.println("DataInputStream现在具有readInt，readChar,readUTF等功能");
        int value = dataInputStream.readInt();//读出来一个int,包含四个字节
        System.out.println("dataInputStream.readInt():"+value);
        //我们转换成字符依次显示出来，可以看到LZ文件的前四个字符
        String binary = Integer.toBinaryString(value);
        int first = binary.length() % 8;
        System.out.print("使用readInt读取的前四个字符：");
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                System.out.print(((char)Integer.valueOf(binary.substring(0, first), 2).intValue()));
            }else {
                System.out.print(((char)Integer.valueOf(binary.substring(( i - 1 ) * 8 + first, i * 8 + first), 2).intValue()));
            }
        }
        System.out.println();

        //PushbackInputStream无法包装BufferedInputStream支持mark reset，因为它覆盖了reset和mark方法
        //因为流已经被读取到末尾，所以我们必须重新打开一个文件的句柄，即FileInputStream
        inputStream = new FileInputStream(filePath);
        System.out.print("装饰成 PushbackInputStream");
        final int len = inputStream.available();
        System.out.println("len:"+len);
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream,len);
        System.out.println("PushbackInputStream装饰以后支持退回操作unread");
        byte[] bytes = new byte[len];
        pushbackInputStream.read(bytes);//读完了整个流
        System.out.println("unread回退前的内容：" + new String(bytes));
        pushbackInputStream.unread(bytes);//再退回去
        bytes = new byte[len];//清空byte数组
        pushbackInputStream.read(bytes);//再读
        System.out.println("unread回退后的内容：" + new String(bytes));

        System.out.println("以上有两个一层装饰和一个两层装饰,下面我们先装饰成Reader，再进行其它装饰");
        //由于之前被PushbackInputStream将流读取到末尾，我们需要再次重新打开文件句柄
        inputStream = new FileInputStream(filePath);
        System.out.println("先装饰成InputStreamReader");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
        System.out.println("InputStreamReader有reader的功能，比如转码.inputStreamReader.getEncoding()：" + inputStreamReader.getEncoding());

        System.out.println("我们进一步在reader的基础上装饰成BufferedReader");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        System.out.println("BufferedReader有readLine等功能：" + bufferedReader.readLine());

        System.out.println("我们进一步在reader的基础上装饰成LineNumberReader");
        LineNumberReader lineNumberReader = new LineNumberReader(inputStreamReader);
        System.out.println("LineNumberReader有设置行号，获取行号等功能（行号从0开始）,当前行号.lineNumberReader.getLineNumber()：" + lineNumberReader.getLineNumber());


        //此处由于刚才被readLine方法将流读取到末尾,所以我们再次重新打开文件句柄,并需要将inputstream再次包装成reader
        inputStreamReader = new InputStreamReader(new FileInputStream(filePath));
        System.out.println("我们进一步在reader的基础上装饰成PushbackReader");
        PushbackReader pushbackReader = new PushbackReader(inputStreamReader,len);
        System.out.println("PushbackReader是拥有退回操作的reader对象");
        char[] chars = new char[len];
        pushbackReader.read(chars);
        System.out.println("unread回退前的内容：" + new String(chars));
        pushbackReader.unread(chars);
        chars = new char[len];
        pushbackReader.read(chars);//再读
        System.out.println("unread回退后的内容：" + new String(chars));
    }
}

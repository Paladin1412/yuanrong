package com.yuanrong.common.util;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

public class ImageUtil {

    /**
     * 获取图片格式，例如：JPEG、GIF等
     */
    public static String  getImageFormat(Object obj){
        ImageInputStream iis = null;
        try {
            iis = ImageIO.createImageInputStream(obj);
            Iterator<ImageReader> iterator = ImageIO.getImageReaders(iis);
            while(iterator.hasNext()){
                ImageReader reader = (ImageReader)iterator.next();
                return reader.getFormatName();
            }
            iis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(iis!=null){
                try {
                    iis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 从内存字节数组中读取图像
     *
     * @param imgBytes
     *            未解码的图像数据
     * @return 返回 {@link BufferedImage}
     * @throws IOException
     *             当读写错误或不识别的格式时抛出
     */
    public static final BufferedImage readMemoryImage(byte[] imgBytes) throws IOException {
        if (null == imgBytes || 0 == imgBytes.length)
            throw new NullPointerException("the argument 'imgBytes' must not be null or empty");
        // 将字节数组转为InputStream，再转为MemoryCacheImageInputStream
        ImageInputStream imageInputstream = null;
        try {
            imageInputstream =  new MemoryCacheImageInputStream(new ByteArrayInputStream(imgBytes));
            // 获取所有能识别数据流格式的ImageReader对象
            Iterator<ImageReader> it = ImageIO.getImageReaders(imageInputstream);
            // 迭代器遍历尝试用ImageReader对象进行解码
            while (it.hasNext()) {
                ImageReader imageReader = it.next();
                // 设置解码器的输入流
                imageReader.setInput(imageInputstream, true, true);
                // 图像文件格式后缀
                String suffix = imageReader.getFormatName().trim().toLowerCase();
                // 图像宽度
                int width = imageReader.getWidth(0);
                // 图像高度
                int height = imageReader.getHeight(0);
                System.out.printf("format %s,%dx%d\n", suffix, width, height);
                try {
                    // 解码成功返回BufferedImage对象
                    // 0即为对第0张图像解码(gif格式会有多张图像),前面获取宽度高度的方法中的参数0也是同样的意思
                    return imageReader.read(0, imageReader.getDefaultReadParam());
                } catch (Exception e) {
                    imageReader.dispose();
                    // 如果解码失败尝试用下一个ImageReader解码
                }
            }
            imageInputstream.close();
            // 没有能识别此数据的图像ImageReader对象，抛出异常
            throw new IOException("unsupported image format");
        }finally {
            //关闭流
            IOUtil.close(imageInputstream);
        }

    }

    public static final BufferedImage readMemoryImage1(byte[] imgBytes) throws IOException {
        if (null == imgBytes || 0 == imgBytes.length)
            throw new NullPointerException("the argument 'imgBytes' must not be null or empty");
        // 将字节数组转为InputStream，再转为MemoryCacheImageInputStream
        ImageInputStream imageInputstream = null;
        try {
            imageInputstream =  new MemoryCacheImageInputStream(new ByteArrayInputStream(imgBytes));
            // 直接调用ImageIO.read方法解码
            BufferedImage bufImg = ImageIO.read(imageInputstream);
            if(null==bufImg)
                // 没有能识别此数据的图像ImageReader对象，抛出异常
                throw new IOException("unsupported image format");
            return bufImg;
        }finally {
            //调用IOUtil关闭IO流
            IOUtil.close(imageInputstream);
        }
    }

    /**
     * 从{@link InputStream}读取字节数组<br>
     * 结束时会关闭{@link InputStream}<br>
     * {@code in}为{@code null}时抛出{@link NullPointerException}
     *
     * @param in
     * @return 字节数组
     * @throws IOException
     */
    public static final byte[] readBytes(InputStream in) throws IOException {
        if (null == in) {
            throw new NullPointerException("the argument 'in' must not be null");
        }
        try {

            int buffSize = Math.max(in.available(), 1024 * 8);
            byte[] temp = new byte[buffSize];
            ByteArrayOutputStream out = new ByteArrayOutputStream(buffSize);
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            return out.toByteArray();
        } finally {
            if(in !=null){
                in.close();
            }
        }
    }

//    public static void main(String[] args) {
//        //测试图像格式
////        File img = new File("E:\\gif.gif");
////        System.out.println(getImageFormat(img));//gif
//
//        //测试图像大小、宽高
//        // 将图像文件加读取到内存成为字节数组
//        File file = new File("E:\\3.jpg");
//        InputStream in = null;
//        BufferedImage bufImg = null;
//        try {
//            in = new FileInputStream(file);
//            byte[] imgBytes = readBytes(in);
//            bufImg = readMemoryImage1(imgBytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(String.format("fileSize:%.1f", file.length()/1024.0));
//        System.out.printf("decode success,width=%d,heigh=%d\n", bufImg.getWidth(), bufImg.getHeight());
//
//    }

    public static void main(String[] args) {

        // 将图像文件加读取到内存成为字节数组
        File file = new File("E:\\gif.gif");
        InputStream  in = null;
        try {
            in = new FileInputStream(file);
            byte[] imgBytes = readBytes(in);
            BufferedImage bufImg = readMemoryImage(imgBytes);
            System.out.printf("decode success,width=%d,heigh=%d\n", bufImg.getWidth(),bufImg.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                IOUtil.close(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

package com.buaa.opencv34dtest1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.FileNotFoundException;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private Bitmap bitmap;
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img= findViewById(R.id.ImgImread);
        Button BtnImreadImg = findViewById(R.id.BtnImreadImg);
        Button BtnGrayscale = findViewById(R.id.BtnGrayscale);
        Button BtnInvColor = findViewById(R.id.BtnInvColor);
        Button BtnFalseColor = findViewById(R.id.BtnFalseColor);
        Button BtnSharpen = findViewById(R.id.BtnSharpen);
        Button BtnSmooth = findViewById(R.id.BtnSmooth);
        Button BtnEmbossed = findViewById(R.id.BtnEmbossed);
        Button BtnLkupTabAdj = findViewById(R.id.BtnLkupTabAdj);
        Button BtnFilter = findViewById(R.id.BtnFilter);
        Button BtnCorrection = findViewById(R.id.BtnCorrection);
        BtnImreadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intent可以应用于广播和发起意图，其中属性有：ComponentName,action,data等
                Intent intent=new Intent();
                intent.setType("image/*");
                //action表示intent的类型，可以是查看、删除、发布或其他情况；我们选择ACTION_GET_CONTENT，系统可以根据Type类型来调用系统程序选择Type
                //类型的内容给你选择
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //如果第二个参数大于或等于0，那么当用户操作完成后会返回到本程序的onActivityResult方法
                startActivityForResult(intent, 1);
            }
        });
        //灰度化
        BtnGrayscale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bit = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                Mat src = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC(3));
                Utils.bitmapToMat(bit, src);
                Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
                Utils.matToBitmap(src, bitmap);
                img.setImageBitmap(bitmap);
            }
        });
        //反色
        BtnInvColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bit = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                Mat src = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC(3));
                Utils.bitmapToMat(bit, src);
                Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
                Core.bitwise_not(src, src);
                Utils.matToBitmap(src, bitmap);
                img.setImageBitmap(bitmap);
            }
        });
        //伪彩色
        BtnFalseColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bit = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                Mat src = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC(3));
                Utils.bitmapToMat(bit, src);
                Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
                Imgproc.applyColorMap(src, src, Imgproc.COLORMAP_JET);
                Utils.matToBitmap(src, bitmap);
                img.setImageBitmap(bitmap);
            }
        });
        //锐化
        BtnSharpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bitmap bit = bitmap.copy(Bitmap.Config.ARGB_8888, false);
//                Mat src = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC(3));
//                Utils.bitmapToMat(bit, src);
////                Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
//
////                Mat blur_img = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC(3));
////                Mat usm = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC(3));
////                Imgproc.GaussianBlur(src, blur_img, new Size(0, 0), 50);
////                Core.addWeighted(src, 1.5, blur_img, -0.5, 0, usm);
//                Mat Shpimg = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC1);
//
//                Mat kernel = new Mat(3, 3, CvType.CV_8UC1);
////                temp.reshape(1, 3);
//
//                Imgproc.filter2D(src, Shpimg, -1, kernel);
////                Imgproc.applyColorMap(src,src,Imgproc.COLORMAP_JET);
//                Utils.matToBitmap(src, bitmap);
//                img.setImageBitmap(bitmap);

                Bitmap bit = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                Mat src = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC(3));
                Utils.bitmapToMat(bit, src);
                Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
                Mat dst = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC1);
                Mat kernel = new Mat(new Size(3, 3), CvType.CV_32FC1, new Scalar(0));
                kernel.put(0, 0, -1, 0, -1);
                kernel.put(1, 0, 0, 5, 0);
                kernel.put(2, 0, -1, 0, -1);
                Imgproc.filter2D(src, dst, src.depth(), kernel);
                Utils.matToBitmap(dst, bitmap);
                img.setImageBitmap(bitmap);
            }
        });
        //平滑
        BtnSmooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bit = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                Mat src = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC(3));
                Utils.bitmapToMat(bit, src);
                Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
//                Imgproc.applyColorMap(src,src,Imgproc.COLORMAP_JET);
                Imgproc.GaussianBlur(src, src, new Size(9, 9), 0, 0, Core.BORDER_DEFAULT);
                Utils.matToBitmap(src, bitmap);
                img.setImageBitmap(bitmap);
            }
        });
        //浮雕化
        BtnEmbossed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bit = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                Mat src = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC(3));
                Utils.bitmapToMat(bit, src);
                Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
                Mat dst = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC1);
                for (int i = 0; i < bit.getHeight() - 5; i++)
                {
                    for (int j = 0; j < bit.getWidth() - 5; j++)
                    {
                        double[] embossedPixel = {0};
                        double[] srcPixelIJ = src.get(i, j);
                        double[] srcPixelIJ5 = src.get(i, j+5);
                        embossedPixel[0] = srcPixelIJ[0] - srcPixelIJ5[0] + 100;
                        if (embossedPixel[0] > 255)    embossedPixel[0] = 255;
                        else if (embossedPixel[0] < 0) embossedPixel[0] = 0;
                        dst.put(i, j, embossedPixel);
                    }
                }
                Utils.matToBitmap(dst, bitmap);
                img.setImageBitmap(bitmap);
            }
        });
        //查找表调整
        BtnLkupTabAdj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bit = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                Mat src = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC(3));
                Utils.bitmapToMat(bit, src);
                Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
//                Core.bitwise_not(src, src);
                Mat dst = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC1);
                Mat lkupLut = new Mat(1, 256, CvType.CV_8UC1);
                for (int i = 1; i < 256; i++)
                {
                    lkupLut.put(0, i, 255 - i);
                }
                Core.LUT(src, lkupLut, dst);
                Utils.matToBitmap(dst, bitmap);
                img.setImageBitmap(bitmap);
            }
        });
        //滤波
        BtnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bit = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                Mat src = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC(3));
                Utils.bitmapToMat(bit, src);
                Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
                Mat dst = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC1);
                Mat kernel = new Mat(new Size(3, 3), CvType.CV_8SC1);
                kernel.put(0, 0, -1, 0, -1);
                kernel.put(1, 0, 0, 4, 0);
                kernel.put(2, 0, -1, 0, -1);
                Imgproc.filter2D(src, dst, -1, kernel, new Point(-1, -1));
                Utils.matToBitmap(dst, bitmap);
                img.setImageBitmap(bitmap);
            }
        });
        //校正
        BtnCorrection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bit = bitmap.copy(Bitmap.Config.ARGB_8888, false);
                Mat src = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC(3));
                Utils.bitmapToMat(bit, src);
                Imgproc.cvtColor(src, src, Imgproc.COLOR_BGR2GRAY);
                Mat dst = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC1);
                Mat dst1 = new Mat(bit.getHeight(), bit.getWidth(), CvType.CV_8UC1);
                src.convertTo(dst, CvType.CV_64F, 1.0 / 255, 0);
                Core.pow(dst, 0.7, dst1);
                dst1.convertTo(dst1, CvType.CV_8U, 255, 0);
                Utils.matToBitmap(dst1, bitmap);
                img.setImageBitmap(bitmap);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //用户操作完成，结果码返回是-1，即RESULT_OK
        if(resultCode==RESULT_OK){
            //获取选中文件的定位符
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            //使用content的接口
            ContentResolver cr = this.getContentResolver();
            try {
                //获取图片
                bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                img.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }else{
            //操作错误或没有选择图片
            Log.i("MainActivtiy", "operation error");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {

            Log.i("cv", "Internal OpenCV library not found. Using OpenCV Manager for initialization");
        } else {
            Log.i("cv", "OpenCV library found inside package. Using it!");
        }
    }
//    Handler handler=new Handler(){
//
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//
//            switch (msg.what)
//            {
//                case 1:img.setImageBitmap(bitmap);break;
//            }
//        }
//    };
}


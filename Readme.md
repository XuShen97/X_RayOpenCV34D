# X_ray 图像处理部分代码

使用Android Studio 开发，这里是项目文件夹。

## packages

- OpenCV 3.4.13(34D for short, not standard expression, standard: 3413).

## Where is the code and MainActivity?

Code: /app/src/main/java/com/buaa/opencv34dtest1/MainActivity.java
MainActivity: app/src/main/res/layout/activity_main.xml

That's all I modified when developing.

## [Important] What features did I implemented?

1. Import and show
2. Convert to gray scale
	With OpenCV function `Imgproc.cvtColor()`
3. Invert color(GrayScale included)
	Function `Core.bitwise_not()`
4. False color(GrayScale included)
	Function `Imgproc.applyColorMap()`
5. Sharpen(GrayScale included)
	USM sharpen given up. Now:

	Convolution with a filter:
```
	[-1, 0, -1
	 0, 5, 0
	 -1, 0, -1]
```
6. Smooth(GrayScale included)
	Smooth can be implement with many ways. Gaussian Blur was used here.

	Function `Imgproc.GaussianBlur()`
7. Embossed(GrayScale included)
	Implement by bitwise operation.

	`newPixel(i, j) = originalPixel(i, j) - originalPixel(i, j) + 100`

8. Look Up Table Adjusting(GrayScale included)
	The Look Up Table(LUT) is fixed here. **When transplanting, the LUT should be modified depending on demandings.**

	Here te LUT is to implement inverting color operation, that is:

	```
	line 193  lkupLut.put(0, i, 255 - i);
	```
9. Filter(GrayScale included)
	Same to Sharpen(5th feature), but different filter kernel. Kernel here is :

	```
	[-1, 0, -1
	 0, 4, 0
	-1, 0, -1]
	```
	
	**Kernel should be modified when transplanting.**

10. Gamma Correction(GrayScale included)
	Gamma = 0.7 here, in line 229. **should be modified when transplanting.**

** Important: Parameters are all fixed in this program, since the main activity has fixed by 10 buttons. But functions are all implemented in this repo. What leave to you are designing the input/output interfaces to meet your software design.**

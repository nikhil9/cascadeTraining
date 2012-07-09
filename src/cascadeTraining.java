import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_core.cvCreateImage;
import static com.googlecode.javacv.cpp.opencv_core.cvEllipse;
import static com.googlecode.javacv.cpp.opencv_core.cvGetSize;
import static com.googlecode.javacv.cpp.opencv_core.cvLoad;
import static com.googlecode.javacv.cpp.opencv_core.cvScalar;
import static com.googlecode.javacv.cpp.opencv_core.cvSize;
import static com.googlecode.javacv.cpp.opencv_highgui.cvLoadImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvShowImage;
import static com.googlecode.javacv.cpp.opencv_highgui.cvWaitKey;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;

import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvPoint;
import com.googlecode.javacv.cpp.opencv_core.CvRect;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_objdetect.CascadeClassifier;

public class cascadeTraining{
	
	public static final String XML_FILE = "cascade.xml";
	
	public static void main(String[] args){
		
		IplImage img = cvLoadImage("test5.bmp");
		//cvShowImage("img", img);
		
		//Loader.load(opencv_objdetect.class); 
		detect(img);
	
	}	
	
	public static void detect(IplImage src){
		
	IplImage gray = cvCreateImage(cvGetSize(src), 8, 1);

	CvRect rects = new CvRect();
	
	CascadeClassifier cascade = new CascadeClassifier();
	cascade.load(XML_FILE);
	
	CvMemStorage storage = CvMemStorage.create();
	
	cvCvtColor(src, gray, CV_BGR2GRAY );
	
	/*CvSeq sign = cvHaarDetectObjects(
			src,
			cascade,
			storage,
			1.5,
			3,
			CV_HAAR_DO_CANNY_PRUNING);*/
	
	cascade.detectMultiScale(src,
			rects,
			1.1,  // scale
			1,   // min neighbours
			0,
			cvSize(20, 20),
			cvSize(100, 100));
	
	cvClearMemStorage(storage);
	
	CvPoint center = new CvPoint(
			rects.x() + (rects.width()/2),
			rects.y() + (rects.height()/2));
	cvEllipse(src,
			center,
			cvSize(rects.width()/2, rects.height()/2),
			0,
			0,
			360,
			CvScalar.RED,
			4,
			8,
			0);
	
	
	/*int total_objects = rects.;
	if(total_objects > 0){
		System.out.println("detected " + total_objects + " objects");
	}
	else{
		System.out.println("not detected");	
	}
	
	for(int i = 0; i < total_objects; i++){
		
		CvRect r = new CvRect(cvGetSeqElem(sign, i));
		cvRectangle (
				src,
				cvPoint(r.x(), r.y()),
				cvPoint(r.width() + r.x(), r.height() + r.y()),
				CvScalar.RED,
				2,
				CV_AA,
				0
				);
	}*/
	
	cvShowImage("Result", src);
	cvWaitKey(0);
	}			
	
		
}
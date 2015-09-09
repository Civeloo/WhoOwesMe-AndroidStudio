package com.civeloo.whoowesme.logic;

/**
 * Created by DeG on 3/12/13.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;

/** CLASE FUNCIONES REUTILIZABLES **/
public class Util {

    /** FUNCION PARA COPIAR UN ARCHIVO **/
    @Deprecated public static void copyFile(File src, File dst) throws IOException {
		FileChannel inChannel = new FileInputStream(src).getChannel();
		FileChannel outChannel = new FileOutputStream(dst).getChannel();
		try {
			inChannel.transferTo(0, inChannel.size(), outChannel);
		} finally {
			if (inChannel != null)
				inChannel.close();
			if (outChannel != null)
				outChannel.close();
		}
    }

    /**
     * Function to convert decimal
     */
    public static double r2Dec(double d){
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    //public static double round(double unrounded, int precision, int roundingMode){
    public static double round(double unrounded){
        if ((0 != unrounded) && (unrounded != 0.00)){
        BigDecimal bd = new BigDecimal(unrounded);
        BigDecimal rounded = bd.setScale(2, RoundingMode.HALF_UP);
        return rounded.doubleValue();}
        else return 0.00;
    }

}

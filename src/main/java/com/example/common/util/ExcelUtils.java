package com.example.common.util;/**
 * Created by Administrator on 2017/8/15 0015.
 */

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author wendongshan
 * @create 2017-08-15 上午 11:27
 * 使用jxls实现导出excel模板,在模板里面使用el表达式,用于批量导入带模板的excel
 *
 *

 **/
public class ExcelUtils {


    /**
     * 依据模板生成简单的xls到outputstream
     *
     * @param templateFileName
     *            在classpath下的exportExcel的文件模板全路径名
     * @param beanParams
     *            封装的Mop数据
     * @param output
     *            输出的output流
     */
    public static void generateXlsByTemplate(String templateFileName,
                                             Map beanParams, OutputStream output,HttpServletRequest request) {
        String srcPath = request.getSession().getServletContext().getRealPath("/") +templateFileName;
        if (StringUtils.isEmpty(srcPath) || output == null)
            return;

        XLSTransformer transformer = new XLSTransformer();

        try {
            transformXLS(transformer, srcPath, beanParams, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void transformXLS(XLSTransformer transformer,
                                     String srcPath, Map beanParams, OutputStream output)
            throws Exception {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new BufferedInputStream(new FileInputStream(srcPath));
            Workbook workbook = transformer
                    .transformXLS(is, beanParams);
            os = new BufferedOutputStream(output);
            workbook.write(os);
            os.flush();
            os.close();
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
    }

    /**
     *
     * @param templateFileName 指定模板项目相对路径的地址
     * @param filename  前台显示名称
     * @param response
     * @throws Exception
     */
    public static void downExcel(String templateFileName, Map beanParams , String filename, HttpServletResponse response, HttpServletRequest request) throws Exception{

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");

        filename=filename+".xls";
        String filenameNew = URLEncoder.encode(filename, "UTF-8");

        response.setHeader("Content-Disposition", "attachment; filename="+ filenameNew);

        setNoCache(response);

        generateXlsByTemplate(templateFileName,beanParams,response.getOutputStream(),request);

    }

    public static void setNoCache(HttpServletResponse response){
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");
    }
}
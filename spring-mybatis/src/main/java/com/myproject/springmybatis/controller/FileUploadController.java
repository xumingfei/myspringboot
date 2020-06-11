package com.myproject.springmybatis.controller;

import com.myproject.springmybatis.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileUploadController {

    @GetMapping("/upload")
    public String toUpload(ModelMap map){
        Person person = new Person();
        person.setPassword("!23456");
        person.setAge(18);
        person.setId(1L);
        person.setUserName("zhangsan");
        map.put("person", person);
        return "fileUpload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(Person person, @RequestParam("file") MultipartFile file,@RequestParam("files") MultipartFile[] files) throws IOException {
        Map<String,Object> map = new HashMap<>();
        String filePath = "D:\\file\\";
        System.out.println(person);
        if (!file.isEmpty()) {
            String contentType = file.getContentType();
            String filename = file.getOriginalFilename();

            file.transferTo(new File(filePath+filename));
            map.put("msg","上传单个成功");
        }else {
            map.put("msg","上传失败");
        }
        for (MultipartFile f :
                files) {
            String fname = f.getOriginalFilename();
            f.transferTo(new File(filePath+fname));
            System.out.println(f.getOriginalFilename());

        }
        map.put("msg1","上传多个附件成功");
        return map;
    }


    @GetMapping("/download")
    public String down(){
        return "download";
    }

    /**
     * 文件下载
     * @param request
     * @param response
     * @param fileName
     * @return
     * @throws IOException
     */
    @RequestMapping("/downloadFile")
    @ResponseBody
    public String download(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileName") String fileName) throws IOException {
        if (StringUtils.isEmpty(fileName)) {
            fileName = "test附件.doc";
        }
        if (fileName != null) {
            File file = new File("/Users/xumingfei/Desktop/test/"+fileName);
            if (file.exists()){
                String userAgent = request.getHeader("User-Agent");
                if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                    fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                } else {
                    // 非IE浏览器的处理：
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
                }
                response.setContentType("application/force-download");
                response.addHeader("Content-Disposition","attachment;fileName="+fileName);
                byte[] buffer = new byte[1024];
                FileInputStream inputStream = null;
                BufferedInputStream bufferedInputStream = null;
                try {
                    inputStream = new FileInputStream(file);
                    bufferedInputStream = new BufferedInputStream(inputStream);
                    OutputStream os = response.getOutputStream();
                    int i = bufferedInputStream.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bufferedInputStream.read(buffer);
                    }
                    return "download success";
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    bufferedInputStream.close();
                    inputStream.close();
                }
            }
        }
        return "failure";
    }
}
